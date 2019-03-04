package com.allmsi.flow.dao;

public interface FlowMapper {

	String getFlowIdByCode(String flowCode);
	
	String getFlowCodeById(String flowId);
}