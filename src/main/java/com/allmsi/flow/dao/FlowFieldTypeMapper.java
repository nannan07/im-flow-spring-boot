package com.allmsi.flow.dao;

import com.allmsi.flow.model.FlowFieldType;

public interface FlowFieldTypeMapper {
	
	int deleteByPrimaryKey(String id);

	FlowFieldType getFlowFieldType(String id);

}