package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.FlowField;
import com.allmsi.flow.model.ovo.FlowFieldOVO;

public interface FlowFieldService {

	FlowFieldOVO getFlowFieldOVo(String id);
	
	boolean deleteByPrimaryKey(String id);

	List<FlowField> listFlowField(String nodeId,List<String> userList);


}
