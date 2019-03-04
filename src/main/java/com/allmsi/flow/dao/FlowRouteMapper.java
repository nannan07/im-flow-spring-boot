package com.allmsi.flow.dao;

import java.util.List;

import com.allmsi.flow.model.FlowRoute;

public interface FlowRouteMapper {
	
	FlowRoute selectByPrimaryKey(String id);

	List<FlowRoute> listRouteByPreNode(FlowRoute flowRoute);

	FlowRoute getStartNodeRoute(String flowId);

	int isfinish(String nodeId);
}