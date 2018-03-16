package io.cattle.platform.servicediscovery.process;


import io.cattle.platform.core.constants.CredentialConstants;
import io.cattle.platform.core.model.AccountLink;
import io.cattle.platform.core.model.Credential;
import io.cattle.platform.core.model.Region;
import io.cattle.platform.engine.handler.HandlerResult;
import io.cattle.platform.engine.handler.ProcessPostListener;
import io.cattle.platform.engine.process.ProcessInstance;
import io.cattle.platform.engine.process.ProcessState;
import io.cattle.platform.process.common.handler.AbstractObjectProcessLogic;
import io.cattle.platform.servicediscovery.service.RegionService;
import io.github.ibuildthecloud.gdapi.condition.Condition;
import io.github.ibuildthecloud.gdapi.condition.ConditionType;
import io.github.ibuildthecloud.gdapi.util.TransformationService;

import static io.cattle.platform.core.model.tables.AccountLinkTable.ACCOUNT_LINK;
import static io.cattle.platform.core.model.tables.RegionTable.REGION;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class ApiKeyActivatePostListener extends AbstractObjectProcessLogic implements ProcessPostListener {

    @Inject
    TransformationService transformationService;
    @Inject
    RegionService regionService;

    @Override
    public HandlerResult handle(ProcessState state, ProcessInstance process) {
        Credential credential = (Credential) state.getResource();
        Object oldObj = state.getData().get("old");
        if(oldObj == null) {
                return null;
        }
            
        final Logger log = LoggerFactory.getLogger(ApiKeyActivatePostListener.class);
        
        if (!getCredentialType().equals(credential.getKind())) {
            return null;
        }
        
        log.info("caaaaalliiiiing activate");
        
        Region region = objectManager.findAny(Region.class, REGION.REMOVED, new Condition(ConditionType.NULL), REGION.LOCAL, true);
        if (region == null) {
            return null;
        }

        String regionPublicValue = region.getPublicValue();
        if(regionPublicValue != null && regionPublicValue.equals(credential.getPublicValue())) {
            List<AccountLink> accountLinks = objectManager.find(AccountLink.class, ACCOUNT_LINK.REMOVED, null, ACCOUNT_LINK.LINKED_REGION_ID,
                    new Condition(ConditionType.NOTNULL), ACCOUNT_LINK.EXTERNAL, false);
            for(AccountLink link: accountLinks) {
                regionService.updateIfExists(link);
            }
        }
        
        return null;
    }
    
    protected String getCredentialType() {
        return CredentialConstants.KIND_API_KEY;
    }

    protected boolean getsHashed() {
        return true;
    }

    @Override
    public String[] getProcessNames() {
        return new String[] { "credential.activate" };
    }

}
