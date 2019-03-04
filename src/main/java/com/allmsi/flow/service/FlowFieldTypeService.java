package com.allmsi.flow.service;

import com.allmsi.flow.model.FlowFieldType;

public interface FlowFieldTypeService {

	FlowFieldType getFlowFieldType(String id);

	boolean deleteByPrimaryKey(String id);
}
