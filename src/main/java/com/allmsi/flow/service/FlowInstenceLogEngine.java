package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.FlowInstanceLog;
import com.allmsi.flow.model.FlowInstanceState;
import com.allmsi.flow.model.ovo.FlowStateLogOVO;

public interface FlowInstenceLogEngine {
	
	public String insertFlowInstenceLog(FlowInstanceState fs,String remark,String dealState);
	
	public String insertFlowInstenceLog(FlowInstanceLog record);
	
	FlowInstanceLog getDoneInfo (String instanceId,String nodeId,String sufDealId);

	public boolean insertBantch(List<FlowInstanceLog> list);
	
//	List<FlowInstanceLog> listInstenceStateLog(String instanceId);
	
	List<FlowStateLogOVO> listFlowLog(String instanceId);
	
	List<String> listVote(String instanceId, String nodeId);
	
	boolean updateDealState(String instanceId,String nodeId,String sufDealId,String dealState);
	
	List<FlowStateLogOVO> listVote(String id);
}
