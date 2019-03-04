package com.allmsi.flow.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.FlowInstanceLog;

public interface FlowInstanceLogMapper {

	int insertSelective(FlowInstanceLog record);

	int insertBantch(List<FlowInstanceLog> list);

	List<FlowInstanceLog> listInstenceStateLog(String instanceId);

	int isFinsh(String objId);

	List<FlowInstanceLog> doneFlow(List<String> objIds);// 1

	int deleteByInstenceId(String instenceId);

	FlowInstanceLog getDoneInfo(Map<String, String> map);

	List<String> listDealState(Map<String, String> map);
	
	List<String> listVoteDealState(Map<String, String> map);

	int updateDeatState(Map<String, String> map);

	List<FlowInstanceLog> listVote(String id);

}