package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.ovo.BusGuideRoute;
import com.allmsi.flow.model.ovo.FlowRouteOVO;

public interface RouteDealRuleEngine {

	public BusGuideRoute ruleJudgment(List<FlowRouteOVO> frList,String instanceId, String userId, String nodeId, String query);

	public List<FlowRouteOVO> guideRoute(String flowId, String nodeId, String query, String userId);

	public FlowRouteOVO getFlowRoute(String routeId);

	public BusGuideRoute getFlowDealModelList(FlowRouteOVO flowRouteOVo,String instanceId, String userId,String flowCode);

}
