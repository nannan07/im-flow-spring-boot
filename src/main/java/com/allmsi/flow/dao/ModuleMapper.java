package com.allmsi.flow.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.FlowModuleView;

public interface ModuleMapper {

	List<FlowModuleView> allModuleList(Map<String, Object> map);
	
	FlowModuleView getFlowModuleInfo(String objId);

}
