package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowInstanceStateMapper;
import com.allmsi.flow.model.FlowInstanceDeal;
import com.allmsi.flow.model.FlowInstanceLog;
import com.allmsi.flow.model.FlowInstanceState;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.reflection.FlowUserServiceReflection;
import com.allmsi.flow.service.FlowInstanceDealService;
import com.allmsi.flow.service.FlowInstanceStateService;
import com.allmsi.flow.service.FlowInstenceLogEngine;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class FlowInstanceStateServiceImpl implements FlowInstanceStateService {
	
	@Resource
	private FlowInstenceLogEngine flowInstenceLogEngine;

	@Resource
	private FlowUserServiceReflection flowUserServiceReflection;

	@Resource
	private FlowInstanceDealService flowInstanceDealService;

	@Resource
	private FlowInstanceStateMapper flowInstanceStateDao;

	@Override
	public boolean transfer(String principal, String agent) {
		if (StrUtil.isEmpty(principal) || StrUtil.isEmpty(agent)) {
			return false;
		}
		FlowInstanceState fis = new FlowInstanceState();
		fis.setSufDealId(principal);
		fis.setSufDealType("01");
		List<FlowInstanceState> fsList = flowInstanceStateDao.getByFis(fis);// 授权人当前所有待办信息
		if (fsList == null || fsList.size() < 1) {// 当前没有待办信息转移
			return true;
		}
		// 添加授权人已办信息
		List<FlowInstanceState> newFsList = new ArrayList<FlowInstanceState>();
		List<FlowInstanceDeal> newFdList = new ArrayList<FlowInstanceDeal>();
		FlowUserModel preUserModel = flowUserServiceReflection.getFlowExternalService().selectUserInfo(principal);
		FlowUserModel sufUserModel = flowUserServiceReflection.getFlowExternalService().selectUserInfo(agent);
		if (preUserModel == null || sufUserModel == null) {
			return false;
		}
		List<String> allStateId = new ArrayList<String>();
		List<FlowInstanceLog> principalLogList = new ArrayList<FlowInstanceLog>();
		for (FlowInstanceState flowInstanceState : fsList) {
			principalLogList.add(new FlowInstanceLog(flowInstanceState));
			String stateId = flowInstanceState.getId();
			allStateId.add(stateId);

			// agent_state
			String newStateId = UUIDUtil.getUUID();
			flowInstanceState.setId(newStateId);
			flowInstanceState.setSufDealId(agent);
			newFsList.add(flowInstanceState);
			// agent_deal
			FlowInstanceDeal fdeal = flowInstanceDealService.getFlowInstanceDealByState(stateId);
			if (fdeal != null) {
				fdeal.setId(UUIDUtil.getUUID());
				fdeal.setStateId(newStateId);
				if (preUserModel != null) {
					fdeal.setPreId(principal);
					fdeal.setPreName(preUserModel.getName());
					fdeal.setPreDeptId(preUserModel.getDeptId());
					fdeal.setPreDeptName(preUserModel.getDeptName());
				}
				if (sufUserModel != null) {
					fdeal.setSufId(agent);
					fdeal.setSufName(sufUserModel.getName());
					fdeal.setSufDeptId(sufUserModel.getDeptId());
					fdeal.setSufDeptName(sufUserModel.getDeptName());
				}

				newFdList.add(fdeal);
			}
		}
		if (newFsList != null && newFsList.size() > 0) {// 添加待办
			flowInstanceStateDao.insertBantch(newFsList);
		}
		if (newFdList != null && newFdList.size() > 0) {// 添加deal
			flowInstanceDealService.insertBatch(newFdList);
		}
		if (principalLogList != null && principalLogList.size() > 0) {// 添加principal_log
			flowInstenceLogEngine.insertBantch(principalLogList);
		}

		if (allStateId != null && allStateId.size() > 0) {// 删除principal_state
			flowInstanceStateDao.deleteByIds(allStateId);
		}
		return true;
	}

	public boolean isRecall(String instanceId, String preDealId) {
		boolean isRecall = false;
		List<FlowInstanceState> flowInstanceStatelist = listInstanceStateByInstance(instanceId, preDealId);
		if (flowInstanceStatelist != null && flowInstanceStatelist.size() > 0) {// 存在待办信息
			// 判断是否可以撤回
			for (FlowInstanceState flowInstanceState : flowInstanceStatelist) {
				if (flowInstanceState.getIsRecall() != null && 1 == flowInstanceState.getIsRecall()) {// 存在可撤回的信息
					isRecall = true;
				} else {
					break;
				}
			}
		}
		return isRecall;
	}

	@Override
	public List<FlowInstanceState> listInstanceStateByInstance(String instanceId, String preDealId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("instanceId", instanceId);
		map.put("preDealId", preDealId);
		List<FlowInstanceState> flowInstanceStatelist = flowInstanceStateDao.selectRouteListByInstanceId(map);
		return flowInstanceStatelist;
	}
}
