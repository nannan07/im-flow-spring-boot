package com.allmsi.flow.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.FlowInstanceState;

public interface FlowInstanceStateMapper {

	int insertBantch(List<FlowInstanceState> fiList);

	int deleteBantch(String instanceId);

	FlowInstanceState getInstenceStateInfo(Map<String, String> map);

	FlowInstanceState getInstenceStateDoneInfo(Map<String, String> map);

	FlowInstanceState getInstenceNow(FlowInstanceState flowInstanceState);

	int deleteById(String id);

	int deleteBantchByFis(FlowInstanceState fis);

	List<FlowInstanceState> getByFis(FlowInstanceState fis);

	int deleteByInstenceId(String instenceId);

	int deleteByIds(List<String> allStateId);
	
	int hasForInstanceIdAndNodeId(Map<String,Object> map);
	
	List<FlowInstanceState> selectRouteListByInstanceId(Map<String,Object> map);

	int updateAuthType(Map<String, Object> map);

	int sCount(String userId);


}