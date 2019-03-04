package com.allmsi.flow.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.FlowField;

public interface FlowFieldMapper {

	FlowField getFlowField(String id);

	int deleteByPrimaryKey(String id);

	List<FlowField> listFlowField(Map<String, Object> map);

}