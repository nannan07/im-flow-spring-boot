package com.allmsi.flow.service;

import com.allmsi.flow.model.ovo.FlowNodeOVO;

public interface FlowNodeService {

	FlowNodeOVO getTheNextNodeToStart(String flowCode);

	boolean isfinish(String nodeId);
	
	FlowNodeOVO getFlowNodeOVO(String nodeId);

}
