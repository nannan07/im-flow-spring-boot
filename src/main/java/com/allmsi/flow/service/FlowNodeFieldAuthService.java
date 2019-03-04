package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.FlowNodeFieldAuth;

public interface FlowNodeFieldAuthService {

	List<FlowNodeFieldAuth> listNodeFieldAuth(String id);
	
	boolean deleteByPrimaryKey(String id);

}
