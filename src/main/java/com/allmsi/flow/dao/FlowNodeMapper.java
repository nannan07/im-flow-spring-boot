package com.allmsi.flow.dao;

import com.allmsi.flow.model.FlowNode;

public interface FlowNodeMapper {
	FlowNode selectByPrimaryKey(String id);

	FlowNode selectTheNextOneToStart(String flowId);

	int isfinish(String nodeId);

	FlowNode selectStopNodeByFlowId(String flowId);

}