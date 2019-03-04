package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.external.FlowUserModel;

public interface ReadDealRuleEngine {

	List<FlowUserModel> getReadDealUser(String nodeId, String instanceId);

}
