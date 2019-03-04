package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.FlowInstanceState;

public interface FlowInstanceStateService {

	boolean transfer(String principal, String agent);

	public List<FlowInstanceState> listInstanceStateByInstance(String instanceId, String preDealId);
}
