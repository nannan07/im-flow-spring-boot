package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowDoneViewMapper;
import com.allmsi.flow.dao.FlowDraftViewMapper;
import com.allmsi.flow.dao.FlowMySelfViewMapper;
import com.allmsi.flow.dao.FlowTodoViewMapper;
import com.allmsi.flow.dao.ModuleMapper;
import com.allmsi.flow.model.FlowDoneView;
import com.allmsi.flow.model.FlowDraftView;
import com.allmsi.flow.model.FlowModuleView;
import com.allmsi.flow.model.FlowMySelfView;
import com.allmsi.flow.model.FlowTodoView;
import com.allmsi.flow.model.ovo.FlowInstenceCurrencyOVO;
import com.allmsi.flow.model.ovo.FlowInstenceStateDoneInfoOVO;
import com.allmsi.flow.model.ovo.FlowModuleListOVO;
import com.allmsi.flow.model.ovo.FlowStateLogOVO;
import com.allmsi.flow.service.FlowInstenceEngine;
import com.allmsi.flow.service.FlowInstenceLogEngine;
import com.allmsi.flow.service.FlowInstenceStateEngine;
import com.allmsi.flow.service.FlowTodoService;
import com.allmsi.flow.service.ModuleAuthService;
import com.allmsi.sys.model.ListBean;
import com.allmsi.sys.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class FlowTodoServiceImpl implements FlowTodoService {

	@Resource
	private FlowInstenceStateEngine flowInstenceStateEngine;

	@Resource
	private FlowInstenceLogEngine flowInstenceLogEngine;

	@Resource
	private FlowInstenceEngine flowInstenceEngine;

	@Resource
	private FlowDraftViewMapper flowDraftDao;

	@Resource
	private FlowTodoViewMapper todoViewDao;

	@Resource
	private FlowDoneViewMapper doneViewDao;

	@Resource
	private FlowMySelfViewMapper mySelfDao;

	@Resource
	private ModuleMapper moduleDao;

	@Resource
	private ModuleAuthService moduleAuthService;

	private static final Set<String> TODO_ORDER_FIELD = new HashSet<>(Arrays.asList("objValue1", "senderTime"));

	private static final Set<String> DONE_ORDER_FIELD = new HashSet<>(Arrays.asList("objValue1", "dealTime"));

	private static final Set<String> SELF_ORDER_FIELD = new HashSet<>(Arrays.asList("objValue1", "createTime"));

	private static final Set<String> MODULE_ORDER_FIELD = new HashSet<>(Arrays.asList("objValue1", "createTime"));

	private static final Set<String> DRAFT_ORDER_FIELD = new HashSet<>(Arrays.asList("objValue1"));

	private static final Set<String> ORDER_TYPE = new HashSet<>(Arrays.asList("asc", "desc"));

	@Override
	public ListBean listDraft(String userId, String search, String flowCode, String orderField, String orderType,
			Integer page, Integer pageSize) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("cUserId", userId);
		map.put("search", search);
		map.put("flowCode", flowCode);
		String orderFields = "";
		if (StrUtil.notEmpty(orderField)) {
			String[] orderFieldArray = orderField.split(",");
			for (String orderFieldStr : orderFieldArray) {
				if (DRAFT_ORDER_FIELD.contains(orderFieldStr)) {
					orderFields += orderFieldStr + ",";
				}
			}
			if (orderFields.endsWith(",")) {
				orderFields = orderFields.substring(0, orderFields.length() - 1);
			}
			map.put("orderField", orderFields);
		}
		if (StrUtil.notEmpty(orderField) && ORDER_TYPE.contains(orderType.toLowerCase())) {
			map.put("orderType", orderType);
		}
		if (page == null) {
			page = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		List<FlowDraftView> list = new ArrayList<FlowDraftView>();
		PageHelper.startPage(page, pageSize);
		list = flowDraftDao.selectFlowDraft(map);
		PageInfo<FlowDraftView> pageInfo = new PageInfo<FlowDraftView>(list);
		int total = (int) pageInfo.getTotal();
		return new ListBean(total, list);
	}

	@Override
	public ListBean listTodo(String userId, String flowCode, String dealType, String search, String orderField,
			String orderType, Integer page, Integer pageSize) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("auditorId", userId);
		map.put("sufDealType", "01");
		map.put("flowCode", flowCode);
		map.put("sufAuthType", dealType);
		map.put("search", search);
		String orderFields = "";
		if (StrUtil.notEmpty(orderField)) {
			String[] orderFieldArray = orderField.split(",");
			for (String orderFieldStr : orderFieldArray) {
				if (TODO_ORDER_FIELD.contains(orderFieldStr)) {
					orderFields += orderFieldStr + ",";
				}
			}
			if (orderFields.endsWith(",")) {
				orderFields = orderFields.substring(0, orderFields.length() - 1);
			}
			map.put("orderField", orderFields);
		}
		if (StrUtil.notEmpty(orderField) && ORDER_TYPE.contains(orderType.toLowerCase())) {
			map.put("orderType", orderType);
		}
		if (page == null) {
			page = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		if (StrUtil.notEmpty(dealType)) {
			switch (dealType) {
			case "01":
				map.put("sufAuthType", "01");
				break;
			case "02":
				map.put("sufAuthType", "02");
				break;

			default:
				break;
			}

		}
		PageHelper.startPage(page, pageSize);
		List<FlowTodoView> list = todoViewDao.selectTodoList(map);
		PageInfo<FlowTodoView> pageInfo = new PageInfo<FlowTodoView>(list);
		int total = (int) pageInfo.getTotal();
		return new ListBean(total, list);
	}

	@Override
	public ListBean listDone(String userId, String flowCode, String dealType, String search, String orderField,
			String orderType, Integer page, Integer pageSize) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("sufDealId", userId);
		map.put("sufDealType", "01");
		map.put("flowCode", flowCode);
		map.put("sufAuthType", dealType);
		map.put("search", search);
		String orderFields = "";
		if (StrUtil.notEmpty(orderField)) {
			String[] orderFieldArray = orderField.split(",");
			for (String orderFieldStr : orderFieldArray) {
				if (DONE_ORDER_FIELD.contains(orderFieldStr)) {
					orderFields += orderFieldStr + ",";
				}
			}
			if (orderFields.endsWith(",")) {
				orderFields = orderFields.substring(0, orderFields.length() - 1);
			}
			map.put("orderField", orderFields);
		}
		if (StrUtil.notEmpty(orderField) && ORDER_TYPE.contains(orderType.toLowerCase())) {
			map.put("orderType", orderType);
		}
		if (page == null) {
			page = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		if (StrUtil.notEmpty(dealType)) {
			switch (dealType) {
			case "01":
				map.put("sufAuthType", "01");
				break;
			case "02":
				map.put("sufAuthType", "02");
				break;

			default:
				break;
			}

		}
		PageHelper.startPage(page, pageSize);
		List<FlowDoneView> list = doneViewDao.selectDoneList(map);
		PageInfo<FlowDoneView> pageInfo = new PageInfo<FlowDoneView>(list);
		int total = (int) pageInfo.getTotal();
		return new ListBean(total, list);
	}

	@Override
	public ListBean listSelf(String userId, String flowCode, String search, String orderField, String orderType,
			Integer page, Integer pageSize) {
		if (page == null) {
			page = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("cUserId", userId);
		map.put("flowCode", flowCode);
		map.put("search", search);
		String orderFields = "";
		if (StrUtil.notEmpty(orderField)) {
			String[] orderFieldArray = orderField.split(",");
			for (String orderFieldStr : orderFieldArray) {
				if (SELF_ORDER_FIELD.contains(orderFieldStr)) {
					orderFields += orderFieldStr + ",";
				}
			}
			if (orderFields.endsWith(",")) {
				orderFields = orderFields.substring(0, orderFields.length() - 1);
			}
			map.put("orderField", orderFields);
		}
		if (StrUtil.notEmpty(orderField) && ORDER_TYPE.contains(orderType.toLowerCase())) {
			map.put("orderType", orderType);
		}
		PageHelper.startPage(page, pageSize);

		List<FlowMySelfView> flowList = mySelfDao.selectMySelfList(map);

		PageInfo<FlowMySelfView> pageInfo = new PageInfo<FlowMySelfView>(flowList);
		int total = (int) pageInfo.getTotal();
		return new ListBean(total, flowList);
	}

	@Override
	public ListBean listAllModule(String userId, String flowCode, String search, String orderField, String orderType,
			Integer page, Integer pageSize) {
		boolean flag = false;
		List<FlowModuleListOVO> moduleList = new ArrayList<FlowModuleListOVO>();
		Map<String, Object> map = new HashMap<String, Object>();
		flag = moduleAuthService.isAdmin(flowCode, userId);
		if (flag) {
			map.put("sufDealId", userId);
		}
		map.put("flowCode", flowCode);
		map.put("search", search);
		String orderFields = "";
		if (StrUtil.notEmpty(orderField)) {
			String[] orderFieldArray = orderField.split(",");
			for (String orderFieldStr : orderFieldArray) {
				if (MODULE_ORDER_FIELD.contains(orderFieldStr)) {
					orderFields += orderFieldStr + ",";
				}
			}
			if (orderFields.endsWith(",")) {
				orderFields = orderFields.substring(0, orderFields.length() - 1);
			}
			map.put("orderField", orderFields);
		}
		if (StrUtil.notEmpty(orderField) && ORDER_TYPE.contains(orderType.toLowerCase())) {
			map.put("orderType", orderType);
		}
		if (page == null) {
			page = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		PageHelper.startPage(page, pageSize);
		List<FlowModuleView> list = moduleDao.allModuleList(map);
		PageInfo<FlowModuleView> pageInfo = new PageInfo<FlowModuleView>(list);
		int total = (int) pageInfo.getTotal();
		for (FlowModuleView flowModuleView : list) {
			moduleList.add(new FlowModuleListOVO(flowModuleView));
		}
		return new ListBean(total, moduleList);
	}

	@Override
	public boolean read(String instanceId, String userId) {
		return flowInstenceStateEngine.updateRead(instanceId, userId);
	}

	@Override
	public int sCount(String userId) {
		return flowInstenceStateEngine.sCount(userId);
	}

	@Override
	public String deleteInstence(String userId, String objId) {
		return flowInstenceEngine.deleteInstence(userId, objId);
	}

	@Override
	public List<FlowStateLogOVO> listInstenceStateLog(String instanceId) {
		return flowInstenceLogEngine.listFlowLog(instanceId);
	}

	@Override
	public FlowInstenceCurrencyOVO getTodoInfo(String objId, String authType, String userId, String flowCode) {
		return flowInstenceStateEngine.getTodoInfo(objId, authType, userId, flowCode);
	}

	@Override
	public FlowInstenceStateDoneInfoOVO getDoneInfo(String objId, String preDealId) {
		return flowInstenceStateEngine.getDoneInfo(objId, preDealId);
	}

	@Override
	public FlowModuleView getFlowModuleInfo(String objId) {
		if (StrUtil.isEmpty(objId)) {
			return null;
		}
		return moduleDao.getFlowModuleInfo(objId);
	}

	@Override
	public List<FlowStateLogOVO> listVote(String stateId) {
		if (StrUtil.isEmpty(stateId)) {
			return null;
		}
		return flowInstenceLogEngine.listVote(stateId);
	}

}
