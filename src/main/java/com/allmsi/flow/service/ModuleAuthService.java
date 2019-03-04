package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.external.FlowUserModel;

public interface ModuleAuthService {

	List<FlowUserModel> listModuleAuth(String flowId);
	
	boolean isAdmin(String flowId,String userId);

}
