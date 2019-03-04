package com.allmsi.flow.service;

import com.allmsi.flow.model.FlowFieldProperty;

public interface FlowFieldPropertyService {

	FlowFieldProperty getFlowFieldProperty(String id);

	boolean deleteByPrimaryKey(String id);

}
