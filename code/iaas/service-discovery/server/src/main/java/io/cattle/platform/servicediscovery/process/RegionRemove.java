package io.cattle.platform.servicediscovery.process;

import static io.cattle.platform.core.model.tables.AccountLinkTable.*;

import java.util.Map;

import io.cattle.platform.core.constants.CommonStatesConstants;
import io.cattle.platform.core.model.AccountLink;
import io.cattle.platform.core.model.Region;
import io.cattle.platform.engine.handler.HandlerResult;
import io.cattle.platform.engine.process.ProcessInstance;
import io.cattle.platform.engine.process.ProcessState;
import io.cattle.platform.json.JsonMapper;
import io.cattle.platform.lock.LockManager;
import io.cattle.platform.object.process.StandardProcess;
import io.cattle.platform.process.common.handler.AbstractObjectProcessHandler;
import io.cattle.platform.servicediscovery.service.RegionService;
import io.cattle.platform.util.type.CollectionUtils;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class RegionRemove extends AbstractObjectProcessHandler {
    @Inject
    LockManager lockManager;
    @Inject
    JsonMapper jsonMapper;
    @Inject
    RegionService regionService;

    @Override
    public String[] getProcessNames() {
        return new String[] { "region.remove", "region.update"};
    }

    @Override
    public HandlerResult handle(ProcessState state, ProcessInstance process) {
        Region region = (Region) state.getResource();
        Object oldObj = state.getData().get("old");
        if(oldObj == null || regionUpdated(oldObj, region)) {
        	cleanupExternalLinks(region);
        }
        System.out.println("testing");
        return null;
    }

    private boolean regionUpdated(Object oldObj, Region region) {
		Map<String, Object> old = CollectionUtils.toMap(oldObj);
		String newURL = (String) old.get("url");
		if(newURL!=null && !newURL.equals(region.getUrl())) {
			return true;
		}
		String newPublicValue = (String) old.get("publicValue");
		if(newPublicValue!=null && !newPublicValue.equals(region.getPublicValue())) {
			return true;
		}
		String newSecretValue = (String) old.get("secretValue");
		if(newSecretValue!=null && !newSecretValue.equals(region.getSecretValue())) {
			return true;
		}
		return false; 
    }
    
    private void cleanupExternalLinks(Region region) {
        for (AccountLink link : objectManager.find(AccountLink.class, ACCOUNT_LINK.REMOVED, null, ACCOUNT_LINK.LINKED_REGION_ID, region.getId())) {
            if (!link.getState().equalsIgnoreCase(CommonStatesConstants.REMOVING)) {
                objectProcessManager.executeStandardProcess(StandardProcess.REMOVE, link, null);
            }
        }
    }
}