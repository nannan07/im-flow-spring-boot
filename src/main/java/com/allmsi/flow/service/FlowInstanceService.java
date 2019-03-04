package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.ivo.FlowInstanceIVO;

public interface FlowInstanceService {
	
	List<String> listInstanceByObjId(String objId);

	String insertFlowInstance(FlowInstanceIVO flowInstanceIVo);

//	FlowInstanceOVO getFlowInstanceOVO(String instanceId);

	String updateFlowInstanceDratf(String instanceId);

}
