package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.ovo.FlowNodeButtonSimpleOVO;

public interface FlowNodeButtonService {
	
	List<FlowNodeButtonSimpleOVO> listNodeButtons(String nodeId);
	
}
