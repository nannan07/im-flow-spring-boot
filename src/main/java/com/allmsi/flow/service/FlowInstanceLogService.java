package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.ivo.FlowInstanceLogIVO;

public interface FlowInstanceLogService {
	
	String insertFlowInstanceLog(FlowInstanceLogIVO flowInstanceLogIVo);
	
	String isFinsh(String objId);
	
	List<String> doneFlow(List<String> objIds);
	
}
