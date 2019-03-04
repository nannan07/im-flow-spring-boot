package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.ivo.NodeDealIVO;
import com.allmsi.flow.model.ovo.BusGuide;
import com.allmsi.flow.model.ovo.BusGuideNode;
import com.allmsi.flow.model.ovo.BusGuideRoute;
import com.allmsi.flow.model.ovo.FlowNodeOVO;
import com.allmsi.flow.model.ovo.SubGuide;

public interface NodeDealRuleEngine {

	BusGuideNode guideNode(BusGuideRoute busGuideRoute,String instanceId,String userId);

	FlowNodeOVO getTheNextNodeToStart(String flowCode);

	BusGuide nodeType(String instanceId, String nodeId,String isback,String userId,String flowCode);

	SubGuide nodeTypeSubmit(String sufNodeId, List<NodeDealIVO> nodeDealList);
	
	public BusGuide isBack(String isback, String nodeId, String instanceId, String userId, String flowCode);
}
