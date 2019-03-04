package com.allmsi.flow.dao;

import java.util.List;

import com.allmsi.flow.model.FlowModuleAuth;

public interface ModuleAuthMapper {

	List<FlowModuleAuth> listModuleAuth(String flowId);

}