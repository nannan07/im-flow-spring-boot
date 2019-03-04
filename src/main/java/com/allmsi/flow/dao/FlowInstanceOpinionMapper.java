package com.allmsi.flow.dao;

import com.allmsi.flow.model.FlowInstanceOpinion;

public interface FlowInstanceOpinionMapper {

	FlowInstanceOpinion getByInstanceLogId(String instanceLogId);

	int insertSelective(FlowInstanceOpinion record);

	int deleteByPrimaryKey(String id);

	int deleteByInstenceLogId(String instanceLogId);
}