package com.allmsi.flow.third.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.allmsi.flow.third.service.FlowExternalBeforeNodeService;

@Service("defaultFlowBeforeNodeService")
public class FlowExternalBeforeNodeServiceImpl implements FlowExternalBeforeNodeService {

	@Override
	public Map<String, Object> flowBeforeNode(String type, String typeId,String flowCode, String moduleId) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<FlowUserModel> getEndOfCCDealUser(String flowCode, String objId) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
