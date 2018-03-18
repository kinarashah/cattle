package io.cattle.platform.servicediscovery.service.impl;

import io.cattle.platform.core.constants.AgentConstants;
import io.cattle.platform.core.constants.CommonStatesConstants;
import io.cattle.platform.core.model.Account;
import io.cattle.platform.core.model.Agent;
import io.cattle.platform.core.model.Region;
import io.cattle.platform.json.JsonMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegionUtil {
    public static final String EXTERNAL_AGENT_URI_PREFIX = "event:///external=";
    
    private static final Logger log = LoggerFactory.getLogger(RegionUtil.class);
    
    public static ExternalAccountLink createExternalAccountLink(Region targetRegion, Map<String, Object> params, JsonMapper jsonMapper) throws IOException {
        String uri = String.format("%s/v2-beta/accountLinks", getUrl(targetRegion));
        Request req = Request.Post(uri);
        setHeaders(req, targetRegion);
        req.bodyString(jsonMapper.writeValueAsString(params), ContentType.APPLICATION_JSON);
        return req.execute().handleResponse(new ResponseHandler<ExternalAccountLink>() {
            @Override
            public ExternalAccountLink handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                if (response.getStatusLine().getStatusCode() >= 300) {
                    throw new IOException(
                            String.format("Failed to create external account link: response error code %s",
                                    response.getStatusLine().getReasonPhrase()));
                }
                return jsonMapper.readValue(response.getEntity().getContent(), ExternalAccountLink.class);
            }
        });
    }
    
    public static ExternalAccountLink updateExternalAccountLink(Region targetRegion, Map<String, Object> params, String id, 
        JsonMapper jsonMapper) throws IOException {
        String uri = String.format("%s/v2-beta/accountLinks/%s", getUrl(targetRegion), id);
        log.info(String.format("uri %s", uri));
        Request req = Request.Put(uri);
        setHeaders(req, targetRegion);
        req.bodyString(jsonMapper.writeValueAsString(params), ContentType.APPLICATION_JSON);
        return req.execute().handleResponse(new ResponseHandler<ExternalAccountLink>() {
            @Override
            public ExternalAccountLink handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                if (response.getStatusLine().getStatusCode() >= 300) {
                    throw new IOException(
                            String.format("Failed to update external account link: response error code %s",
                                    response.getStatusLine().getReasonPhrase()));
                }
                return jsonMapper.readValue(response.getEntity().getContent(), ExternalAccountLink.class);
            }
        });
    }

    private static Request setHeaders(Request req, Region region) {
        String publicKey = region.getPublicValue();
        String secretKey = region.getSecretValue();
        String encodedKeys = Base64.encodeBase64String(String.format("%s:%s", publicKey, secretKey).getBytes());
        String auth = String.format("Basic %s", encodedKeys);
        req.addHeader("Authorization", auth);
        req.addHeader("X-ENFORCE-AUTHENTICATION", "true");
        req.addHeader("Content-Type", "application/json");
        req.addHeader("Accept", "application/json");
        return req;
    }

    public static ExternalAccountLinkResponse getExternalAccountLink(Region targetRegion, ExternalProject targetResourceAccount, Account localAccount,
            JsonMapper jsonMapper) throws IOException {
        String uri = String.format("%s/v2-beta/accountLinks?accountId=%s&linkedAccount=%s&external=true",
                getUrl(targetRegion),
                targetResourceAccount.getId(),
                localAccount.getName());
        Request req = Request.Get(uri);
        setHeaders(req, targetRegion);
        return req.execute().handleResponse(new ResponseHandler<ExternalAccountLinkResponse>() {
            @Override
            public ExternalAccountLinkResponse handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                ExternalAccountLinkResponse externalAccLinkResponse = new ExternalAccountLinkResponse();
                externalAccLinkResponse.statusCode = response.getStatusLine().getStatusCode();
                externalAccLinkResponse.externalAccountLink = null;
                if (response.getStatusLine().getStatusCode() != 200) {
                    return externalAccLinkResponse;
                }
                for (ExternalAccountLink link : jsonMapper.readValue(response.getEntity().getContent(), ExternalAccountLinkData.class).data) {
                    List<String> invalidStates = Arrays.asList(CommonStatesConstants.REMOVED, CommonStatesConstants.REMOVING);
                    if (invalidStates.contains(link.getState())) {
                        continue;
                    }

                    if (link.getLinkedAccountUuid().equalsIgnoreCase(localAccount.getUuid())) {
                            externalAccLinkResponse.externalAccountLink = link;
                        return externalAccLinkResponse;
                    }
                }
                return externalAccLinkResponse;
            }
        });
    }
    
    public static ExternalAccountLinkResponse getAccountLink(Region targetRegion, ExternalProject targetResourceAccount, Account localAccount,
            JsonMapper jsonMapper, boolean external) throws IOException {
            String uri = String.format("%s/v2-beta/accountLinks?accountId=%s&linkedAccount=%s&external=%s",
                        getUrl(targetRegion),
                        targetResourceAccount.getId(),
                        localAccount.getName(), String.valueOf(external));
        Request req = Request.Get(uri);
        setHeaders(req, targetRegion);
        return req.execute().handleResponse(new ResponseHandler<ExternalAccountLinkResponse>() {
            @Override
            public ExternalAccountLinkResponse handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                    ExternalAccountLinkResponse externalAccLinkResponse = new ExternalAccountLinkResponse();
                    externalAccLinkResponse.statusCode = response.getStatusLine().getStatusCode();
                    externalAccLinkResponse.externalAccountLink = null;
                if (response.getStatusLine().getStatusCode() != 200) {
                    return externalAccLinkResponse;
                }

                for (ExternalAccountLink link : jsonMapper.readValue(response.getEntity().getContent(), ExternalAccountLinkData.class).data) {
                    if (link.getLinkedAccountUuid().equalsIgnoreCase(localAccount.getUuid())) {
                            externalAccLinkResponse.externalAccountLink = link;
                        return externalAccLinkResponse;
                    }
                }
                return externalAccLinkResponse;
            }
        });
    }
    
    public static boolean deleteExternalAccountLink(Region targetRegion, ExternalAccountLink externalLink) throws IOException {
        String uri = String.format("%s/v2-beta/accountLinks/%s", getUrl(targetRegion), externalLink.getId());
        Request req = Request.Delete(uri);
        setHeaders(req, targetRegion);
        req.execute().handleResponse(new ResponseHandler<ExternalAccountLink>() {
            @Override
            public ExternalAccountLink handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode >= 300) {
                    throw new IOException(String.format("Failed to delete external accountLink [%s], response error code [%s]", externalLink.getId(),
                            response.getStatusLine().getReasonPhrase()));
                }

                return null;
            }
        });
        return true;

    }
    
    public static String getTargetAgentUri(String localRegionName, String localEnvironmentName, String agentUuid, String targetResourceAccountUuid) {
        return String.format("%s%s_%s_%s_%s", EXTERNAL_AGENT_URI_PREFIX, localRegionName, localEnvironmentName, agentUuid, targetResourceAccountUuid);
    }

    public static ExternalProjectResponse getTargetProjectByName(Region targetRegion, String accountName, JsonMapper jsonMapper) throws IOException {
        String uri = String.format("%s/v2-beta/projects?name=%s&all=true",
                getUrl(targetRegion),
                accountName);
        log.info("true uri", uri);
        Request req = Request.Get(uri);
        setHeaders(req, targetRegion);
        return req.execute().handleResponse(new ResponseHandler<ExternalProjectResponse>() {
            @Override
            public ExternalProjectResponse handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                ExternalProjectResponse externalProjectResponse = new ExternalProjectResponse();
                externalProjectResponse.statusCode = response.getStatusLine().getStatusCode();
                externalProjectResponse.externalProject = null;
                if (externalProjectResponse.statusCode != 200) {
                    return externalProjectResponse;
                }

                ExternalProjectData data = jsonMapper.readValue(response.getEntity().getContent(), ExternalProjectData.class);
                if(data.data.size() > 0) {
                    externalProjectResponse.externalProject = data.data.get(0);
                }
                return externalProjectResponse;
            }
        });
    }
    
    public static ExternalProject getTargetProjectByUUID(Region targetRegion, String UUID, JsonMapper jsonMapper) throws IOException {
        String uri = String.format("%s/v2-beta/projects?uuid=%s&all=true",
                getUrl(targetRegion),
                UUID);
        Request req = Request.Get(uri);
        setHeaders(req, targetRegion);
        return req.execute().handleResponse(new ResponseHandler<ExternalProject>() {
            @Override
            public ExternalProject handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                if (response.getStatusLine().getStatusCode() != 200) {
                    return null;
                }

                ExternalProjectData data = jsonMapper.readValue(response.getEntity().getContent(), ExternalProjectData.class);
                return data.data.size() == 0 ? null : data.data.get(0); 
            }
        });
        
    }

    public static ExternalAgent createExternalAgent(Region targetRegion, String targetEnvName, Map<String, Object> params,
            JsonMapper jsonMapper) throws IOException {
//            ExternalAgent externalAgent = RegionUtil.getExternalAgentByURI(targetRegion, params.get(AgentConstants.FIELD_URI).toString(), jsonMapper);
//            if(externalAgent != null) {
//                log.info(String.format("got externalAgent %s", externalAgent.getId()));
//                RegionUtil.deleteExternalAgent(null, targetRegion, externalAgent);
//            }
        String uri = String.format("%s/v2-beta/agents", getUrl(targetRegion));
        log.info(String.format("uri %s", uri));
        Request req = Request.Post(uri);
        setHeaders(req, targetRegion);
        req.bodyString(jsonMapper.writeValueAsString(params), ContentType.APPLICATION_JSON);
        return req.execute().handleResponse(new ResponseHandler<ExternalAgent>() {
            @Override
            public ExternalAgent handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                if (response.getStatusLine().getStatusCode() >= 300) {
                    throw new IOException(
                            String.format("Failed to create external agent with uri [%s] in environment [%s] in region [%s]: response error code %s",
                                    params.get(AgentConstants.FIELD_URI),
                                    targetEnvName,
                                    targetRegion.getName(),
                                    response.getStatusLine().getReasonPhrase()));
                }
                return jsonMapper.readValue(response.getEntity().getContent(), ExternalAgent.class);
            }
        });
    }

    public static void deleteExternalAgent(Agent agent, Region targetRegion, ExternalAgent externalAgent) throws ClientProtocolException, IOException {
        String uri = String.format("%s/v2-beta/agents/%s", getUrl(targetRegion), externalAgent.getId());
        Request req = Request.Delete(uri);
        setHeaders(req, targetRegion);
        req.execute().handleResponse(new ResponseHandler<ExternalAgent>() {
            @Override
            public ExternalAgent handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode >= 300) {
                    throw new IOException(String.format("Failed to delete external agent externalId=%s, response error code %s", agent.getUuid(),
                            response.getStatusLine().getReasonPhrase()));
                }
                log.info("returning null external agent");
                return null;
            }
        });
    }

    public static ExternalRegionResponse getExternalRegion(Region targetRegion, String regionName, JsonMapper jsonMapper)
            throws IOException {
        String uri = String.format("%s/v2-beta/regions?name=%s",
                getUrl(targetRegion),
                regionName);
        Request req = Request.Get(uri);
        setHeaders(req, targetRegion);
        return req.execute().handleResponse(new ResponseHandler<ExternalRegionResponse>() {
            @Override
            public ExternalRegionResponse handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                ExternalRegionResponse externalRegionResponse = new ExternalRegionResponse();
                externalRegionResponse.statusCode = response.getStatusLine().getStatusCode();
                externalRegionResponse.externalRegion = null;
                if (response.getStatusLine().getStatusCode() != 200) {
                    return externalRegionResponse;
                }
                for (ExternalRegion region : jsonMapper.readValue(response.getEntity().getContent(), ExternalRegionData.class).data) {
                    externalRegionResponse.externalRegion = region;
                    return externalRegionResponse;
                }
                return externalRegionResponse;
            }
        });
    }

    private static String getUrl(Region region) {
        return region.getUrl();
    }

    public static class ExternalAgentData {
        List<ExternalAgent> data;

        public List<ExternalAgent> getData() {
            return data;
        }

        public void setData(List<ExternalAgent> data) {
            this.data = data;
        }
    }

    public static class ExternalAgent {
        String id;
        String uri;
        String name;
        String state;
        String uuid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
    }

    public static class ExternalAccountLinkData {
        List<ExternalAccountLink> data;

        public List<ExternalAccountLink> getData() {
            return data;
        }

        public void setData(List<ExternalAccountLink> data) {
            this.data = data;
        }
    }

    public static class ExternalAccountLink {
        String accountId;
        String linkedRegion;
        String linkedAccount;
        String linkedRegionId;
        boolean external;
        String state;
        String id;
        String linkedAccountUuid;

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getLinkedRegion() {
            return linkedRegion;
        }

        public void setLinkedRegion(String linkedRegion) {
            this.linkedRegion = linkedRegion;
        }

        public String getLinkedAccount() {
            return linkedAccount;
        }

        public void setLinkedAccount(String linkedAccount) {
            this.linkedAccount = linkedAccount;
        }

        public String getLinkedRegionId() {
            return linkedRegionId;
        }

        public void setLinkedRegionId(String linkedRegionId) {
            this.linkedRegionId = linkedRegionId;
        }

        public boolean isExternal() {
            return external;
        }

        public void setExternal(boolean external) {
            this.external = external;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLinkedAccountUuid() {
            return linkedAccountUuid;
        }

        public void setLinkedAccountUuid(String linkedAccountUuid) {
            this.linkedAccountUuid = linkedAccountUuid;
        }

    }

    public static class ExternalProjectData {
        List<ExternalProject> data;

        public List<ExternalProject> getData() {
            return data;
        }

        public void setData(List<ExternalProject> data) {
            this.data = data;
        }
    }

    public static class ExternalProject {
        String id;
        String name;
        String uuid;
        String state;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

    public static class ExternalRegionData {
        List<ExternalRegion> data;

        public List<ExternalRegion> getData() {
            return data;
        }

        public void setData(List<ExternalRegion> data) {
            this.data = data;
        }
    }
    
    public static class ExternalProjectResponse {
        ExternalProject externalProject; 
        int statusCode;

        public ExternalProject getExternalProject() {
            return this.externalProject;
        }

        public int getStatusCode() {
            return this.statusCode;
        }
    }
    
    public static class ExternalRegionResponse {
            ExternalRegion externalRegion;
            int statusCode;
            
            public ExternalRegion getExternalRegion() {
                return this.externalRegion;
            }
            
            public int getStatusCode() {
                return this.statusCode;
            }
    }
    
    public static class ExternalAccountLinkResponse {
        ExternalAccountLink externalAccountLink;
        int statusCode;
        
        public ExternalAccountLink getExternalAccountLink() {
            return this.externalAccountLink;
        }
        
        public int getStatusCode() {
            return this.statusCode;
        }
    }

    public static class ExternalRegion {
        String id;
        String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static ExternalAgent getExternalAgent(Region targetRegion, String uuid, JsonMapper jsonMapper)
            throws IOException {
        String uri = String.format("%s/v2-beta/agents?uuid=%s",
                getUrl(targetRegion),
                uuid);
        Request req = Request.Get(uri);
        setHeaders(req, targetRegion);
        return req.execute().handleResponse(new ResponseHandler<ExternalAgent>() {
            @Override
            public ExternalAgent handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                if (response.getStatusLine().getStatusCode() != 200) {
                    return null;
                }
                for (ExternalAgent agent : jsonMapper.readValue(response.getEntity().getContent(), ExternalAgentData.class).data) {
                    List<String> invalidStates = Arrays.asList(CommonStatesConstants.REMOVED, CommonStatesConstants.REMOVING);
                    if (invalidStates.contains(agent.getState())) {
                        continue;
                    }
                    return agent;
                }
                return null;
            }
        });
    }
    
    public static ExternalAgent getExternalAgentByURI(Region targetRegion, String urii, JsonMapper jsonMapper)
            throws IOException {
        String uri = String.format("%s/v2-beta/agents?uri=%s",
                getUrl(targetRegion),
                urii);
        Request req = Request.Get(uri);
        setHeaders(req, targetRegion);
        return req.execute().handleResponse(new ResponseHandler<ExternalAgent>() {
            @Override
            public ExternalAgent handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                if (response.getStatusLine().getStatusCode() != 200) {
                    return null;
                }
                for (ExternalAgent agent : jsonMapper.readValue(response.getEntity().getContent(), ExternalAgentData.class).data) {
                    List<String> invalidStates = Arrays.asList(CommonStatesConstants.REMOVED, CommonStatesConstants.REMOVING);
                    if (invalidStates.contains(agent.getState())) {
                        continue;
                    }
                    return agent;
                }
                return null;
            }
        });
    }

}
