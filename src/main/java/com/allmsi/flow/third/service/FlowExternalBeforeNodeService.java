package com.allmsi.flow.third.service;

import java.util.Map;

public interface FlowExternalBeforeNodeService {

//	List<FlowUserModel> getEndOfCCDealUser(String flowCode,String objId);
	
	Map<String, Object> flowBeforeNode(String type, String typeId,String flowCode, String moduleId);

}
