package com.allmsi.flow.third.service;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.external.FlowUserModel;

public interface FlowExternalNodeDealUserService {
	
	Map<String,List<FlowUserModel>> listNodeDealUsers(String objId,String nodeId);

}
