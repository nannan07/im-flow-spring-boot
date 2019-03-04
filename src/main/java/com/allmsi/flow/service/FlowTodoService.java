package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.FlowModuleView;
import com.allmsi.flow.model.ovo.FlowInstenceCurrencyOVO;
import com.allmsi.flow.model.ovo.FlowInstenceStateDoneInfoOVO;
import com.allmsi.flow.model.ovo.FlowStateLogOVO;
import com.allmsi.sys.model.ListBean;

public interface FlowTodoService {

	ListBean listDraft(String userId, String search, String flowCode, String orderField, String orderType, Integer page,
			Integer pageSize);

	ListBean listTodo(String userId, String flowCode, String dealType, String search, String orderField,
			String orderType, Integer page, Integer pageSize);

	ListBean listDone(String userId, String flowCode, String dealType, String search, String orderField,
			String orderType, Integer page, Integer pageSize);

	ListBean listSelf(String userId, String flowCode, String search, String orderField, String orderType, Integer page,
			Integer pageSize);

	ListBean listAllModule(String userId, String flowCode, String search, String orderField, String orderType,
			Integer page, Integer pageSize);
	
	List<FlowStateLogOVO> listInstenceStateLog(String instanceId);
	
	int sCount(String userId);

	boolean read(String instanceId, String userId);
	
	String deleteInstence(String userId, String objId);
	
	FlowInstenceCurrencyOVO getTodoInfo(String objId, String authType, String userId,String flowCode);

	FlowInstenceStateDoneInfoOVO getDoneInfo(String objId,String preDealId);
	
	FlowModuleView getFlowModuleInfo(String objId);

	List<FlowStateLogOVO> listVote(String stateId);
}
