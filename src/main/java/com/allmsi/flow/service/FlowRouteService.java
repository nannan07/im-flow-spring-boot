package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.ovo.FlowRouteOVO;

public interface FlowRouteService {
	
	FlowRouteOVO getRouteInfo(String id);

	List<FlowRouteOVO> listRouteListByPreNode(String flowCode, String preNode);

	FlowRouteOVO getStartNodeRoute(String flowId);

}
