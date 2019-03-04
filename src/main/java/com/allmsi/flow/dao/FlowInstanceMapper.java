package com.allmsi.flow.dao;

import java.util.List;

import com.allmsi.flow.model.FlowInstance;

public interface FlowInstanceMapper {
	
	int insertSelective(FlowInstance record);

	FlowInstance selectByPrimaryKey(String id);

	List<FlowInstance> listInstanceByObjId(String objId);

	int updateDraft(String instanceId);

	int deleteById(String objId);
}