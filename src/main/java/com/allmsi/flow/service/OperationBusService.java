package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ivo.NodeDealIVO;
import com.allmsi.flow.model.ovo.BusGuide;
import com.allmsi.flow.model.ovo.FlowInstenceCurrencyOVO;
import com.allmsi.flow.model.ovo.SubGuide;

public interface OperationBusService {

	BusGuide guide(String flowCode, String instanceId, String nodeId, String isback, String routeId, String query,
			String userId);

	SubGuide submit(String preDealId, FlowInstenceCurrencyOVO flowInfoVo, List<NodeDealIVO> nodeDealList);

	SubGuide isRecall(String preDealId, String instanceId);

	BusGuide vote(String instanceId, String nodeId, String userId, String voteState, String opinion, String query);
	
	List<FlowUserModel> transfer(String userId, String nodeId);

}
