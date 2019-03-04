package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.FlowInstanceState;
import com.allmsi.flow.model.ivo.FlowInstanceDealIVO;
import com.allmsi.flow.model.ivo.FlowInstanceStateIVO;
import com.allmsi.flow.model.ivo.NodeDealIVO;
import com.allmsi.flow.model.ovo.FlowInstenceCurrencyOVO;
import com.allmsi.flow.model.ovo.FlowInstenceStateDoneInfoOVO;

public interface FlowInstenceStateEngine {

	public String insertFlowInstanceState(FlowInstanceStateIVO flowInstanceStateIVo);

	public List<String> insertFlowInstanceStateForNodeDealList(String instanceId, String nodeId, String routeId,
			String preDealId, List<NodeDealIVO> nodeDealList);

	public String insertFlowInstenceDeal(FlowInstanceDealIVO flowInstanceDealIVo);

	public boolean deleteBantchForInstanceAndNodeId(String instanceId, String nodeId, String sufAuthType);

	public boolean deleteBantchByInstenceId(String instanceId);

	public FlowInstanceState getInstenceStateNow(String instanceId, String nodeId, String sufDealId, String sufAuthType);
	
	public FlowInstanceState getInstenceStateNow(FlowInstanceState flowInstanceState);

	public boolean deleteInstenceNow(String id);

	public List<FlowInstanceState> listByFis(String instanceId, String nodeId, String sufAuthType);

	public boolean isRecall(String instanceId, String preDealId);

	public boolean recall(String preDealId, String instanceId);

	public boolean updateAuthType(String instanceId, String nodeId, String sufAuthType);
	
	public boolean updateRead(String instanceId, String userId);
	
	public int sCount(String userId);
	
	FlowInstenceCurrencyOVO getTodoInfo(String objId, String authType, String userId,String flowCode);

	FlowInstenceStateDoneInfoOVO getDoneInfo(String objId,String preDealId);
	
}
