package io.cattle.platform.servicediscovery.service.impl;

import static io.cattle.platform.core.model.tables.AccountLinkTable.*;
import static io.cattle.platform.core.model.tables.RegionTable.*;
import static io.cattle.platform.core.model.tables.ServiceConsumeMapTable.*;
import static io.cattle.platform.core.model.tables.ServiceTable.*;

import io.cattle.platform.agent.instance.dao.AgentInstanceDao;
import io.cattle.platform.core.addon.ExternalCredential;
import io.cattle.platform.core.addon.LbConfig;
import io.cattle.platform.core.addon.PortRule;
import io.cattle.platform.core.constants.AccountConstants;
import io.cattle.platform.core.constants.AgentConstants;
import io.cattle.platform.core.constants.CommonStatesConstants;
import io.cattle.platform.core.constants.CredentialConstants;
import io.cattle.platform.core.constants.InstanceConstants;
import io.cattle.platform.core.constants.ServiceConstants;
import io.cattle.platform.core.model.Account;
import io.cattle.platform.core.model.AccountLink;
import io.cattle.platform.core.model.Agent;
import io.cattle.platform.core.model.Region;
import io.cattle.platform.core.model.Service;
import io.cattle.platform.core.model.ServiceConsumeMap;
import io.cattle.platform.core.util.SystemLabels;
import io.cattle.platform.iaas.api.filter.apikey.ApiKeyFilter;
import io.cattle.platform.json.JsonMapper;
import io.cattle.platform.lock.LockCallbackNoReturn;
import io.cattle.platform.lock.LockManager;
import io.cattle.platform.object.ObjectManager;
import io.cattle.platform.object.process.ObjectProcessManager;
import io.cattle.platform.object.process.StandardProcess;
import io.cattle.platform.object.util.DataAccessor;
import io.cattle.platform.process.common.lock.AccountLinksUpdateLock;
import io.cattle.platform.servicediscovery.service.RegionService;
import io.cattle.platform.servicediscovery.service.impl.RegionUtil.ExternalAccountLink;
import io.cattle.platform.servicediscovery.service.impl.RegionUtil.ExternalAccountLinkResponse;
import io.cattle.platform.servicediscovery.service.impl.RegionUtil.ExternalAgent;
import io.cattle.platform.servicediscovery.service.impl.RegionUtil.ExternalProject;
import io.cattle.platform.servicediscovery.service.impl.RegionUtil.ExternalProjectResponse;
import io.cattle.platform.servicediscovery.service.impl.RegionUtil.ExternalRegion;
import io.cattle.platform.servicediscovery.service.impl.RegionUtil.ExternalRegionResponse;
import io.github.ibuildthecloud.gdapi.condition.Condition;
import io.github.ibuildthecloud.gdapi.condition.ConditionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RegionServiceImpl implements RegionService {
    private static final Logger log = LoggerFactory.getLogger(RegionServiceImpl.class);
    private static final List<String> INVALID_STATES = Arrays.asList(CommonStatesConstants.REMOVING, CommonStatesConstants.REMOVED);

    @Inject
    ObjectManager objectManager;
    @Inject
    JsonMapper jsonMapper;
    @Inject
    ObjectProcessManager objectProcessManager;
    @Inject
    AgentInstanceDao agentInstanceDao;
    @Inject
    LockManager lockManager;

    @Override
    public void reconcileExternalLinks(long accountId) {
        lockManager.lock(new AccountLinksUpdateLock(accountId),
                new LockCallbackNoReturn() {
                    @Override
                    public void doWithLockNoResult() {
                        List<Region> regions = objectManager.find(Region.class, REGION.REMOVED, new Condition(ConditionType.NULL));
                        if (regions.size() == 0) {
                            return;
                        }
                        Account localAccount = objectManager.loadResource(Account.class, accountId);
                        if (INVALID_STATES.contains(localAccount.getState())) {
                            return;
                        }

                        Map<String, Region> regionsMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
                        Region localRegion = null;
                        for (Region region : regions) {
                            regionsMap.put(region.getName(), region);
                            if (region.getLocal()) {
                                localRegion = region;
                            }
                        }

                        List<AccountLink> toRemove = new ArrayList<>();
                        List<AccountLink> toUpdate = new ArrayList<>();
                        Set<String> toCreate = new HashSet<>();

                        fetchAccountLinks(accountId, regionsMap, localRegion, toRemove, toUpdate, toCreate);
                        reconcileAccountLinks(accountId, regionsMap, toRemove, toUpdate, toCreate);
                    }
                });
    }

    private void reconcileAccountLinks(long accountId, Map<String, Region> regionsMap, List<AccountLink> toRemove, List<AccountLink> toUpdate,
            Set<String> toCreate) {
        for (AccountLink item : toRemove) {
            if (!item.getState().equalsIgnoreCase(CommonStatesConstants.REMOVING)) {
                objectProcessManager.scheduleStandardProcess(StandardProcess.REMOVE, item, null);
            }
        }
        
        Set<String> invalidAccounts = new HashSet<>();

        for (String item : toCreate) {
            String[] splitted = item.split(":");
            String regionName = splitted[0];
            String envName = splitted[1];
            Region region = regionsMap.get(regionName);
            ExternalProject externalProject = null;
            if (region != null && !INVALID_STATES.contains(region.getState())) {
                try {
                    String externalProjectKey = String.format("%s:%s", regionName, envName);
                    if(invalidAccounts.contains(externalProjectKey)) {
                        continue;
                    }
                    ExternalProjectResponse externalProjectResponse = RegionUtil.getTargetProjectByName(region, envName, jsonMapper);
                    externalProject = externalProjectResponse.externalProject;
                    if(externalProject == null) {
                        invalidAccounts.add(externalProjectKey);
                        continue;
                    } 
                AccountLink link = objectManager.create(AccountLink.class, ACCOUNT_LINK.ACCOUNT_ID,
                        accountId, ACCOUNT_LINK.LINKED_ACCOUNT, envName, ACCOUNT_LINK.LINKED_REGION, regionName,
                        ACCOUNT_LINK.LINKED_REGION_ID, region.getId(), "linkedAccountUuid", externalProject.getUuid());
                toUpdate.add(link);
                } catch(Exception ex) {
                    log.warn(String.format("Failed to find account for %s - %s", envName, ex));
                }
            }
        }
        for (AccountLink item : toUpdate) {
            if (item.getState().equalsIgnoreCase(CommonStatesConstants.REQUESTED)) {
                objectProcessManager.scheduleStandardProcessAsync(StandardProcess.CREATE, item, null);
            }
        }
    }

    private void fetchAccountLinks(long accountId, Map<String, Region> regionsMap, Region localRegion, List<AccountLink> toRemove, List<AccountLink> toUpdate,
            Set<String> toCreate) {
        List<? extends ServiceConsumeMap> links = objectManager.find(ServiceConsumeMap.class, SERVICE_CONSUME_MAP.ACCOUNT_ID, accountId,
                SERVICE_CONSUME_MAP.REMOVED, null, SERVICE_CONSUME_MAP.CONSUMED_SERVICE, new Condition(ConditionType.NOTNULL));
        
        Set<String> toAdd = new HashSet<>();
        for (ServiceConsumeMap link : links) {
            if (INVALID_STATES.contains(link.getState())) {
                continue;
            }
            if (link.getConsumedService() == null) {
                continue;
            }
            String[] splitted = link.getConsumedService().split("/");
            if (splitted.length < 3) {
                continue;
            }
            if (splitted.length == 4) {
                if (regionsMap.containsKey(splitted[0])) {
                    toAdd.add(getUUID(splitted[0], splitted[1]));
                }
            } else if (splitted.length == 3) {
                toAdd.add(getUUID(localRegion.getName(), splitted[0]));
            }
        }

        List<? extends Service> lbs = objectManager.find(Service.class, SERVICE.ACCOUNT_ID, accountId,
                SERVICE.REMOVED, null, SERVICE.KIND, ServiceConstants.KIND_LOAD_BALANCER_SERVICE);
        for (Service lb : lbs) {
            if (INVALID_STATES.contains(lb.getState())) {
                continue;
            }
            LbConfig lbConfig = DataAccessor.field(lb, ServiceConstants.FIELD_LB_CONFIG, jsonMapper,
                    LbConfig.class);
            if (lbConfig != null && lbConfig.getPortRules() != null) {
                for (PortRule rule : lbConfig.getPortRules()) {
                    String rName = rule.getRegion();
                    String eName = rule.getEnvironment();
                    if (StringUtils.isEmpty(eName)) {
                        continue;
                    }
                    if (StringUtils.isEmpty(rName)) {
                        rName = localRegion.getName();
                    }
                    if (regionsMap.containsKey(rName)) {
                        toAdd.add(getUUID(rName, eName));
                    }
                }
            }
        }

        List<? extends AccountLink> existingLinks = objectManager.find(AccountLink.class, ACCOUNT_LINK.ACCOUNT_ID, accountId,
                ACCOUNT_LINK.REMOVED, null, ACCOUNT_LINK.LINKED_ACCOUNT, new Condition(ConditionType.NOTNULL), ACCOUNT_LINK.LINKED_REGION,
                new Condition(ConditionType.NOTNULL),
                ACCOUNT_LINK.EXTERNAL, false);
        Set<String> existingLinksKeys = new HashSet<>();
        for (AccountLink existingLink : existingLinks) {
            existingLinksKeys.add(getUUID(existingLink.getLinkedRegion(), existingLink.getLinkedAccount()));
        }

        for (AccountLink link : existingLinks) {
            if (!toAdd.contains(getUUID(link.getLinkedRegion(), link.getLinkedAccount()))) {
                toRemove.add(link);
            } else {
                toUpdate.add(link);
            }
        }
        for (String item : toAdd) {
            if (!existingLinksKeys.contains(item)) {
                toCreate.add(item);
            }
        }
    }

    private String getUUID(String regionName, String envName) {
        return String.format("%s:%s", regionName, envName);
    }
    
    @Override
    public boolean isRegionsEmpty(Agent agent, Account account, Map<String, Long> externalLinks,  Map<String, ExternalProject> projects, 
            Map<Long, Region> regionsIds, Map<String, Region> regionNameToRegion) {
            List<Region> regions = objectManager.find(Region.class, REGION.REMOVED, (Object) null);
            for(Region region : regions) {
                regionsIds.put(region.getId(), region);
                regionNameToRegion.put(region.getName(), region);
            }
            
        getLinkedEnvironments(account.getId(), externalLinks, regionsIds, projects);

        boolean noExternalLinks = externalLinks.isEmpty();
        List<? extends ExternalCredential> existing = DataAccessor.fieldObjectList(agent, AccountConstants.FIELD_EXTERNAL_CREDENTIALS, ExternalCredential.class,
                jsonMapper);
        boolean noExternalCreds = existing.isEmpty();
        if (noExternalLinks && noExternalCreds) {
            return true;
        }
            
            return false;
    }

    @Override
    public boolean reconcileAgentExternalCredentials(Agent agent, Account account, Map<String, Long> externalLinks, Map<String, ExternalProject> projects, 
            Map<Long, Region> regionsIds, Map<String, Region> regionNameToRegion) {
        Region localRegion = null;

        for (Region region : regionsIds.values()) {
            if (region.getLocal()) {
                localRegion = region;
            }
        }
        boolean success = true;

        // 2. Reconcile agent's credentials
        try {
            reconcileExternalCredentials(account, agent, localRegion, externalLinks, projects, regionNameToRegion);
        } catch (Exception ex) {
            success = false;
            log.error(String.format("Fail to reconcile credentials for agent [%d]", agent.getId()), ex);
        }
        return success;
    }

    protected void reconcileExternalCredentials(Account account, Agent agent, Region localRegion, Map<String, Long> externalLinks,
            Map<String, ExternalProject> externalProjects, Map<String, Region> regionNameToRegion) {
        
        // 1. Set credentials
        Map<String, ExternalCredential> toAdd = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        Map<String, ExternalCredential> toRemove = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        Map<String, ExternalCredential> toRetain = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        setCredentials(agent, externalLinks, toAdd, toRemove, toRetain);

        // 2. Reconcile agents
        reconcileExternalAgents(account, agent, localRegion, regionNameToRegion, toAdd, toRemove, toRetain, externalProjects);
    }

    private ExternalAgent createExternalAgent(Agent agent, Account account, Region localRegion, Region targetRegion,
            ExternalCredential cred, Map<String, ExternalProject> externalProjects) {
        // Create external agent with local credentials  
        try {
            String UUID = getUUID(targetRegion.getName(), cred.getEnvironmentName());
            ExternalProject targetResourceAccount = null;
            if (externalProjects.containsKey(UUID)) {
                targetResourceAccount = externalProjects.get(UUID);
            } else {
                    ExternalProjectResponse externalProjectResponse = RegionUtil.getTargetProjectByName(targetRegion, cred.getEnvironmentName(), jsonMapper);
                targetResourceAccount = externalProjectResponse.externalProject;
                if (targetResourceAccount == null) {
                    throw new RuntimeException(String.format("Failed to find target environment by name [%s] in region [%s]",
                            cred.getEnvironmentName(), targetRegion.getName()));
                }
                externalProjects.put(UUID, targetResourceAccount);
            }

            String targetAgentUri = RegionUtil.getTargetAgentUri(localRegion.getName(), account.getName(), agent.getUuid(), targetResourceAccount.getUuid());
            log.info(String.format("Creating external agent with uri [%s] in environment [%s] in region [%s]",
                    targetAgentUri,
                    cred.getEnvironmentName(),
                    cred.getRegionName()));
            Map<String, Object> data = new HashMap<>();
            data.put(AgentConstants.DATA_AGENT_RESOURCES_ACCOUNT_ID, targetResourceAccount.getId());
            data.put(CredentialConstants.PUBLIC_VALUE, cred.getPublicValue());
            data.put(CredentialConstants.SECRET_VALUE, cred.getSecretValue());
            data.put(AgentConstants.FIELD_URI, targetAgentUri);
            data.put(AgentConstants.FIELD_EXTERNAL_ID, agent.getUuid());
            Map<String, String> labels = new HashMap<>();
            labels.put(SystemLabels.LABEL_AGENT_SERVICE_METADATA, "true");
            data.put(InstanceConstants.FIELD_LABELS, labels);
            data.put("activateOnCreate", true);
            return RegionUtil.createExternalAgent(targetRegion, cred.getEnvironmentName(), data, jsonMapper);
        } catch (Exception e) {
            log.error("Failed to create external agent", e);
            return null;
        }
    }

    @Override
    /**
     * This method creates an external account link in the target environment as ipsec service
     * would need two way links
     */
    public void createExternalAccountLink(AccountLink link) {
        if (link.getExternal()) {
            return;
        }
        try {
                log.info("creating external link");
            Region targetRegion = objectManager.loadResource(Region.class, link.getLinkedRegionId());
            Region localRegion = objectManager.findAny(Region.class, REGION.LOCAL, true, REGION.REMOVED, null);
            if(localRegion == null) {
                    log.warn("No local region present");
                    return;
            }
            ExternalRegionResponse externalRegionResponse = RegionUtil.getExternalRegion(targetRegion, localRegion.getName(), jsonMapper);
            ExternalRegion externalRegion = externalRegionResponse.getExternalRegion();
            if (externalRegion == null) {
                throw new RuntimeException(String.format("Failed to find local region [%s] in external region [%s]",
                        localRegion.getName(), targetRegion.getName()));
            }
            // log.info(String.format("got externalRegion how? %s %s ",externalRegion.getName(), externalRegionResponse.getStatusCode()));
            ExternalProjectResponse externalProjectResponse = RegionUtil.getTargetProjectByName(targetRegion, link.getLinkedAccount(), jsonMapper);
            ExternalProject targetResourceAccount = externalProjectResponse.externalProject;
            if (targetResourceAccount == null) {
                throw new RuntimeException(String.format("Failed to find target environment by name [%s] in region [%s]",
                        link.getLinkedAccount(), localRegion.getName()));
            }
            Account localAccount = objectManager.loadResource(Account.class, link.getAccountId());
            ExternalAccountLinkResponse externalLinkResponse = RegionUtil.getExternalAccountLink(targetRegion,
                    targetResourceAccount, localAccount, jsonMapper);
            if (externalLinkResponse.externalAccountLink != null) {
                return;
            }

            Map<String, Object> data = new HashMap<>();
            data.put("accountId", targetResourceAccount.getId());
            data.put("external", "true");
            data.put("linkedAccount", localAccount.getName());
            data.put("linkedRegion", externalRegion.getName());
            data.put("linkedRegionId", externalRegion.getId());
            data.put("linkedAccountUuid", localAccount.getUuid());
            RegionUtil.createExternalAccountLink(targetRegion, data, jsonMapper);
        } catch (Exception ex) {
            throw new RuntimeException(String.format("Failed to create external account link for accountLink [%d]", link.getId()), ex);
        }
    }
    
    @Override
    public boolean deleteExternalAccountLink(AccountLink link) {
        if (link.getExternal()) {
            return true;
        }
        try {
            Region targetRegion = objectManager.loadResource(Region.class, link.getLinkedRegionId());
            if(targetRegion == null) {
                return true;
            }
            ExternalProjectResponse externalProjectResponse = RegionUtil.getTargetProjectByName(targetRegion, link.getLinkedAccount(), jsonMapper);
            ExternalProject targetResourceAccount = externalProjectResponse.externalProject;
            if (targetResourceAccount == null) {
                String UUID = DataAccessor.fieldString(link, "linkedAccountUuid");
                targetResourceAccount = RegionUtil.getTargetProjectByUUID(targetRegion, UUID, jsonMapper);
                if (targetResourceAccount == null) {
                    log.info(String.format("Failed to find target environment by UUID [%s] in region [%s]",
                            UUID, targetRegion.getName()));
                    return true;
                }
            }
            Account localAccount = objectManager.loadResource(Account.class, link.getAccountId());
            ExternalAccountLinkResponse externalLinkResponse = RegionUtil.getExternalAccountLink(targetRegion, targetResourceAccount,
                    localAccount, jsonMapper);
            ExternalAccountLink externalLink = externalLinkResponse.externalAccountLink;
            if (externalLink == null) {
                return true;
            }
            RegionUtil.deleteExternalAccountLink(targetRegion, externalLink);
            return true;
        } catch (Exception ex) {
                log.error(String.format("Failed to delete external account link for accountLink [%d]", link.getId()), ex);
                return false;
        }
    }

    private void reconcileExternalAgents(Account account, Agent agent,
            Region localRegion,
            Map<String, Region> regions,
            Map<String, ExternalCredential> toAdd,
            Map<String, ExternalCredential> toRemove,
            Map<String, ExternalCredential> toRetain,
            Map<String, ExternalProject> externalProjects) {

        // 1. Add missing agents
        boolean changed = false;
        for (String key : toAdd.keySet()) {
            ExternalCredential value = toAdd.get(key);
            ExternalAgent externalAgent = createExternalAgent(agent, account, localRegion, regions.get(value.getRegionName()), toAdd.get(key),
                    externalProjects);
            if (externalAgent != null) {
                value.setAgentUuid(externalAgent.getUuid());
                // only add credential of the agent which got created successfully
                toRetain.put(key, value);
                changed = true;
            }
        }

        // 2. Remove extra agents.
        for (String key : toRemove.keySet()) {
            ExternalCredential value = toRemove.get(key);
            deactivateAndRemoveExternalAgent(agent, value);
            changed = true;
        }
        if (changed) {
            objectManager.setFields(agent, AccountConstants.FIELD_EXTERNAL_CREDENTIALS, toRetain.values());
        }
    }

    protected boolean deactivateAndRemoveExternalAgent(Agent agent, ExternalCredential cred) {
        Region targetRegion = objectManager.loadResource(Region.class, cred.getRegionId());
        if (targetRegion == null) {
            log.info(String.format("Failed to find target region by name [%s]", cred.getRegionName()));
            return true;
        }

        String regionName = cred.getRegionName();
        String envName = cred.getEnvironmentName();
        try {
            log.info(String.format("Removing agent with externalId [%s] in environment [%s] and region [%s]", agent.getUuid(), regionName,
                    envName));
            ExternalAgent externalAgent = RegionUtil.getExternalAgent(targetRegion, cred.getAgentUuid(), jsonMapper);
            if (externalAgent == null) {
                log.info(String.format("Failed to find agent by externalId [%s] in environment [%s] and region [%s]", agent.getUuid(), regionName,
                        envName));
                return true;
            }
            RegionUtil.deleteExternalAgent(agent, targetRegion, externalAgent);
            return true;
        } catch (Exception e) {
            log.error(
                    String.format("Failed to deactivate agent with externalId [%s] in environment [%s] and region [%s]", agent.getUuid(), regionName, envName),
                    e);
            return false;
        }
    }


    private void setCredentials(Agent agent, Map<String, Long> externalLinks,
            Map<String, ExternalCredential> toAdd, Map<String, ExternalCredential> toRemove, Map<String, ExternalCredential> toRetain) {
        List<? extends ExternalCredential> existing = DataAccessor.fieldObjectList(agent, AccountConstants.FIELD_EXTERNAL_CREDENTIALS, ExternalCredential.class,
                jsonMapper);

        Map<String, ExternalCredential> existingCredentials = new HashMap<>();
        for (ExternalCredential cred : existing) {
            existingCredentials.put(getUUID(cred.getRegionName(), cred.getEnvironmentName()), cred);
        }

        for (String key : externalLinks.keySet()) {
            String[] splitted = key.split(":");
            String regionName = splitted[0];
            String envName = splitted[1];
            String uuid = getUUID(regionName, envName);
            if (existingCredentials.containsKey(uuid)) {
                toRetain.put(uuid, existingCredentials.get(uuid));
            } else {
                String[] keys = ApiKeyFilter.generateKeys();
                toAdd.put(uuid, new ExternalCredential(envName, regionName, keys[0], keys[1], externalLinks.get(key)));
            }
        }
        
        for (String key : existingCredentials.keySet()) {
            if (!(toAdd.containsKey(key) || toRetain.containsKey(key))) {
                toRemove.put(key, existingCredentials.get(key));
            }
        }
    }

    private void getLinkedEnvironments(long accountId, Map<String, Long> links, Map<Long, Region> regionsIds,
            Map<String, ExternalProject> externalProjects) {
        List<AccountLink> accountLinks = objectManager.find(AccountLink.class, ACCOUNT_LINK.ACCOUNT_ID,
                accountId, ACCOUNT_LINK.REMOVED, null, ACCOUNT_LINK.LINKED_REGION_ID, new Condition(ConditionType.NOTNULL));

        for (AccountLink link : accountLinks) {
                if (link.getState().equalsIgnoreCase(CommonStatesConstants.REMOVING)) {
                    continue;
                }
                if(link.getState().equalsIgnoreCase(CommonStatesConstants.REMOVED)) {
                    continue;
                }
            Region targetRegion = regionsIds.get(link.getLinkedRegionId());
            if (targetRegion == null) {
                continue;
            }
            String UUID = getUUID(targetRegion.getName(), link.getLinkedAccount());
            if (!externalProjects.containsKey(UUID)) {
                links.put(UUID, targetRegion.getId());
            }
        }
    }

    @Override
    public boolean deactivateAndRemoveExternalAgent(Agent agent) {
        List<? extends ExternalCredential> creds = DataAccessor.fieldObjectList(agent, AccountConstants.FIELD_EXTERNAL_CREDENTIALS, ExternalCredential.class,
                jsonMapper);
        if (creds.isEmpty()) {
            return true;
        }

        Map<String, Region> regions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (Region region : objectManager.find(Region.class, REGION.REMOVED, new Condition(ConditionType.NULL))) {
            regions.put(region.getName(), region);
        }
        for (ExternalCredential cred : creds) {
            if (!deactivateAndRemoveExternalAgent(agent, cred)) {
                return false;
            }
        }
        return true;
    }
    
    public void updateIfExists(AccountLink link) {
         try {
             log.info("creating external link");
             Region targetRegion = objectManager.loadResource(Region.class, link.getLinkedRegionId());
             Region localRegion = objectManager.findAny(Region.class, REGION.LOCAL, true, REGION.REMOVED, null);
             if(localRegion == null) {
                 log.warn("No local region present");
                 return;
             }
             ExternalRegionResponse externalRegionResponse = RegionUtil.getExternalRegion(targetRegion, localRegion.getName(), jsonMapper);
             ExternalRegion externalRegion = externalRegionResponse.getExternalRegion();
             if (externalRegion == null) {
                 throw new RuntimeException(String.format("Failed to find local region [%s] in external region [%s]",
                         localRegion.getName(), targetRegion.getName()));
             }
             // log.info(String.format("got externalRegion how? %s %s ",externalRegion.getName(), externalRegionResponse.getStatusCode()));
             ExternalProjectResponse externalProjectResponse = RegionUtil.getTargetProjectByName(targetRegion, link.getLinkedAccount(), jsonMapper);
             ExternalProject targetResourceAccount = externalProjectResponse.externalProject;
             if (targetResourceAccount == null) {
                 throw new RuntimeException(String.format("Failed to find target environment by name [%s] in region [%s]",
                         link.getLinkedAccount(), localRegion.getName()));
             }
             Account localAccount = objectManager.loadResource(Account.class, link.getAccountId());
             ExternalAccountLinkResponse externalLinkResponse = RegionUtil.getExternalAccountLink(targetRegion,
                     targetResourceAccount, localAccount, jsonMapper);
             if (externalLinkResponse.externalAccountLink != null && externalLinkResponse.statusCode == 200) {
                 ExternalAccountLink externalAccLink = externalLinkResponse.externalAccountLink;
                    Map<String, Object> data = new HashMap<>();
//                    data.put("accountId", externalAccLink.getId());
//                    data.put("external", "true");
//                    data.put("linkedAccount", externalAccLink.getLinkedAccount());
//                    data.put("linkedRegion", externalAccLink.getLinkedRegion());
//                    data.put("linkedRegionId", externalAccLink.getLinkedRegionId());
//                    data.put("linkedAccountUuid", externalAccLink.getLinkedAccountUuid());
                    data.put("description", UUID.randomUUID().toString());
                    log.info("updateIfExists calling update external link");
                 RegionUtil.updateExternalAccountLink(targetRegion, data, jsonMapper);
             }
            return;
         } catch(Exception ex) {
             log.warn(String.format("Failed to get external account link for %s - %s ", link.getId(), ex));
             return;
         }
   }    

}
