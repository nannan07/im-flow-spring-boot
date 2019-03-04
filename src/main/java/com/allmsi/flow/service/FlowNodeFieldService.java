package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.ovo.FlowNodeFieldOVO;

public interface FlowNodeFieldService {

	List<FlowNodeFieldOVO> listNodeField(String userId, String nodeId);

	boolean deleteByPrimaryKey(String id);

}
