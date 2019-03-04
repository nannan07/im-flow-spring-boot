package com.allmsi.flow.dao;

import com.allmsi.flow.model.FlowFieldProperty;

public interface FlowFieldPropertyMapper {
	
	int deleteByPrimaryKey(String id);

	FlowFieldProperty getFlowFieldProperty(String id);

}