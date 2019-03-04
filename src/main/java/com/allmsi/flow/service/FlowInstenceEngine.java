package com.allmsi.flow.service;

import com.allmsi.flow.model.ivo.FlowInstanceIVO;
import com.allmsi.flow.model.ovo.FlowInstanceOVO;
import com.allmsi.flow.model.ovo.SubGuide;

public interface FlowInstenceEngine {

	String addFlowInstance(FlowInstanceIVO flowInstanceIVo);
	
	SubGuide flowInstanceEngine(String objId, String instanceId);

	String updateFlowInstanceDratf(String instanceId);
	
	String deleteInstence(String userId, String objId);
	
	FlowInstanceOVO getFlowInstanceOVO(String instanceId);

}
