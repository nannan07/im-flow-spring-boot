package com.allmsi.flow.dao;

import java.util.List;

import com.allmsi.flow.model.FlowNodeField;

public interface FlowNodeFieldMapper {
	
	int deleteByPrimaryKey(String id);

	FlowNodeField selectByPrimaryKey(String id);

	List<FlowNodeField> listNodeField(String nodeId);
	
}