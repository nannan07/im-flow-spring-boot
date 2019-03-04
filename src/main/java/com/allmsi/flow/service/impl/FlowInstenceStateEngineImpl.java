package com.allmsi.flow.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowInstanceStateMapper;
import com.allmsi.flow.model.FlowInstanceDeal;
import com.allmsi.flow.model.FlowInstanceLog;
import com.allmsi.flow.model.FlowInstanceState;
import com.allmsi.flow.model.OperationMenu;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ivo.FlowInstanceDealIVO;
import com.allmsi.flow.model.ivo.FlowInstanceStateIVO;
import com.allmsi.flow.model.ivo.NodeDealIVO;
import com.allmsi.flow.model.ovo.FlowInstenceCurrencyOVO;
import com.allmsi.flow.model.ovo.FlowInstenceStateDoneInfoOVO;
import com.allmsi.flow.model.ovo.FlowNodeButtonSimpleOVO;
import com.allmsi.flow.model.ovo.FlowNodeFieldOVO;
import com.allmsi.flow.model.ovo.FlowNodeOVO;
import com.allmsi.flow.reflection.FlowUserServiceReflection;
import com.allmsi.flow.service.FlowInstanceDealService;
import com.allmsi.flow.service.FlowInstanceStateService;
import com.allmsi.flow.service.FlowInstenceLogEngine;
import com.allmsi.flow.service.FlowInstenceStateEngine;
import com.allmsi.flow.service.FlowNodeButtonService;
import com.allmsi.flow.service.FlowNodeFieldService;
import com.allmsi.flow.service.NodeDealRuleEngine;
import com.allmsi.flow.util.LogStatusUtil;
import com.allmsi.sys.util.DateUtil;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class FlowInstenceStateEngineImpl implements FlowInstenceStateEngine {

	@Resource
	private FlowInstanceStateService flowInstanceStateService;

	@Resource
	private FlowInstenceLogEngine flowInstenceLogEngine;

	@Resource
	private FlowUserServiceReflection flowUserServiceReflection;

	@Resource
	private FlowInstanceStateMapper flowInstanceStateDao;

	@Resource
	private FlowInstanceDealService flowInstanceDealService;

	@Resource
	private FlowNodeFieldService flowNodeFieldService;

	@Resource
	private FlowNodeButtonService flowNodeButtonService;

	@Resource
	private NodeDealRuleEngine nodeDealRuleEngine;

	@Value("${im.flow.recall.hour:2}")
	private String RECALL_HOUR;

	public String insertFlowInstanceState(FlowInstanceStateIVO flowInstanceStateIVo) {

		FlowInstanceState flowInstanceState = new FlowInstanceState(flowInstanceStateIVo);
		List<FlowInstanceState> fiList = new ArrayList<FlowInstanceState>();
		if (flowInstanceState != null) {
			fiList.add(flowInstanceState);
		}
		if (fiList != null && fiList.size() > 0) {
			int msg = flowInstanceStateDao.insertBantch(fiList);
			return (msg == 0) ? "" : flowInstanceStateIVo.getId();
		}
		return null;
	}

	@Override
	public List<String> insertFlowInstanceStateForNodeDealList(String instanceId, String nodeId, String routeId,
			String preDealId, List<NodeDealIVO> nodeDealList) {
		if (StrUtil.isEmpty(instanceId) || StrUtil.isEmpty(nodeId) || StrUtil.isEmpty(preDealId) || nodeDealList == null
				|| nodeDealList.size() < 1) {
			return null;
		}
		FlowUserModel flowUserModel = flowUserServiceReflection.getFlowExternalService().selectUserInfo(preDealId);
		List<FlowInstanceState> list = new ArrayList<FlowInstanceState>();// 待办表
		List<FlowInstanceDeal> dealList = new ArrayList<FlowInstanceDeal>();// 实例状态办理表
		List<String> strList = new ArrayList<String>();// 实例状态ID
		if (flowUserModel != null) {
			String preId = flowUserModel.getUserId();
			String preName = flowUserModel.getName();
			String preDeptId = flowUserModel.getDeptId();
			String preDeptName = flowUserModel.getDeptName();
			for (NodeDealIVO nodeDealVo : nodeDealList) {
				String sufDealId = nodeDealVo.getDealId();
				String sufDealType = nodeDealVo.getDealType();
				String sufAuthType = nodeDealVo.getAuthType();
				String stateId = UUIDUtil.getUUID();
				Integer isProxy = nodeDealVo.getIsProxy();
				String proxyId = nodeDealVo.getProxyId();

				list.add(new FlowInstanceState(stateId, instanceId, nodeId, routeId, preDealId, sufDealId, sufDealType,
						sufAuthType, preDealId, isProxy, proxyId));
				strList.add(stateId);
				dealList.add(new FlowInstanceDeal(UUIDUtil.getUUID(), stateId, preId, preName, preDeptId, preDeptName,
						sufDealId, nodeDealVo.getDealName(), nodeDealVo.getDeptId(), nodeDealVo.getDeptName()));
			}
		}

		int msg = 0;
		if (list != null && list.size() > 0) {
			msg = flowInstanceStateDao.insertBantch(list);
		}
		if (dealList != null && dealList.size() > 0) {
			flowInstanceDealService.insertBatch(dealList);
		}
		return (msg == 0) ? null : strList;
	}

	@Override
	public String insertFlowInstenceDeal(FlowInstanceDealIVO flowInstanceDealIVo) {
		FlowInstanceDeal FlowInstanceDeal = new FlowInstanceDeal(flowInstanceDealIVo);
		int msg = flowInstanceDealService.insertSelective(FlowInstanceDeal);
		return (msg == 0) ? "" : FlowInstanceDeal.getId();
	}

	@Override
	public boolean deleteBantchForInstanceAndNodeId(String instanceId, String nodeId, String sufAuthType) {
		if (StrUtil.isEmpty(instanceId) || StrUtil.isEmpty(nodeId)) {
			return false;
		}
		FlowInstanceState fis = new FlowInstanceState();
		fis.setInstanceId(instanceId);
		fis.setNodeId(nodeId);
		fis.setSufAuthType(sufAuthType);
		fis.setSufDealType("01");
		int msg = flowInstanceStateDao.deleteBantchByFis(fis);// 删除实例状态信息
		return (msg == 0) ? false : true;
	}

	@Override
	public FlowInstanceState getInstenceStateNow(String instanceId, String nodeId, String sufDealId,
			String sufAuthType) {
		FlowInstanceState flowInstanceState = new FlowInstanceState();
		flowInstanceState.setInstanceId(instanceId);
		flowInstanceState.setSufDealId(sufDealId);
		flowInstanceState.setNodeId(nodeId);
		flowInstanceState.setSufAuthType(sufAuthType);
//		FlowInstanceState fs = flowInstanceStateDao.getInstenceNow(flowInstanceState);
		return getInstenceStateNow(flowInstanceState);
	}

	@Override
	public FlowInstanceState getInstenceStateNow(FlowInstanceState flowInstanceState) {
		return flowInstanceStateDao.getInstenceNow(flowInstanceState);
	}

	@Override
	public boolean deleteInstenceNow(String id) {
		if (StrUtil.isEmpty(id)) {
			return false;
		}
		int msg = flowInstanceStateDao.deleteById(id);// 删除本人的办理实例
		return (msg == 0) ? false : true;
	}

	@Override
	public boolean deleteBantchByInstenceId(String instanceId) {
		int msg = flowInstanceStateDao.deleteBantch(instanceId);
		return (msg == 0) ? false : true;
	}

	@Override
	public List<FlowInstanceState> listByFis(String instanceId, String nodeId, String sufAuthType) {
		FlowInstanceState fis = new FlowInstanceState();
		fis.setInstanceId(instanceId);
		fis.setNodeId(nodeId);
		fis.setSufAuthType(sufAuthType);
		return flowInstanceStateDao.getByFis(fis);
	}

	@Override
	public boolean isRecall(String instanceId, String preDealId) {
		boolean isRecall = false;
		List<FlowInstanceState> list = flowInstanceStateService.listInstanceStateByInstance(instanceId, preDealId);
		if (list != null && list.size() > 0) {// 存在待办信息
			// 判断是否可以撤回
			for (FlowInstanceState flowInstanceState : list) {
				if (flowInstanceState.getIsRecall() != null && 1 == flowInstanceState.getIsRecall()) {// 存在可撤回的信息&&
																										// flowInstanceState.getuTime()
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String uTime = format.format(flowInstanceState.getuTime());
					String date = format.format(new Date());
					BigDecimal number = new BigDecimal(
							Double.toString((DateUtil.strConvertSecond(date) - DateUtil.strConvertSecond(uTime))));// 秒数
					BigDecimal recallHr = new BigDecimal(Double.toString(Double.valueOf(RECALL_HOUR) * 3600));
					if (recallHr.subtract(number).doubleValue() > 0) {
						isRecall = true;
						return isRecall;
					} else {
						break;
					}
				}
			}
		}
		return isRecall;
	}

	@Override
	public boolean recall(String preDealId, String instanceId) {
		List<FlowInstanceState> list = flowInstanceStateService.listInstanceStateByInstance(instanceId, preDealId);
		if (list != null && list.size() > 0) {
			FlowInstanceState flowInstanceState = list.get(0);
			if (flowInstanceState != null) {
				// log表用户前一步办理信息得到nodeId,routeId
				FlowInstanceLog flowInstanceLog = flowInstenceLogEngine.getDoneInfo(instanceId, null, preDealId);
				if (flowInstanceLog != null) {
					String nodeId = flowInstanceLog.getNodeId();
					String routeId = flowInstanceLog.getRouteId();
					// 添加待办,instance_deal
					List<NodeDealIVO> nodeDealList = new ArrayList<NodeDealIVO>();
					FlowUserModel flowUserModel = flowUserServiceReflection.getFlowExternalService()
							.selectUserInfo(preDealId);
					nodeDealList.add(new NodeDealIVO(preDealId, "01", "01", flowUserModel.getDeptId(),
							flowUserModel.getDeptName(), flowUserModel.getName()));
					List<String> states = insertFlowInstanceStateForNodeDealList(instanceId, nodeId, routeId, preDealId,
							nodeDealList);
					if (states != null && states.size() > 0) {
						// log
						String logId = UUIDUtil.getUUID();
						FlowInstanceState flog = new FlowInstanceState(logId, instanceId, flowInstanceState.getNodeId(),
								flowInstanceState.getRouteId(), preDealId, preDealId, "01", "01", preDealId, null,
								null);
						flowInstenceLogEngine.insertFlowInstenceLog(flog, LogStatusUtil.RECAL_MSG, "08");
						insertFlowInstenceDeal(new FlowInstanceDealIVO(UUIDUtil.getUUID(), logId, preDealId,
								flowUserModel.getName(), flowUserModel.getDeptId(), flowUserModel.getDeptName(),
								preDealId, flowUserModel.getName(), flowUserModel.getDeptId(),
								flowUserModel.getDeptName()));
						// 删除其它待办
						for (FlowInstanceState fisInfo : list) {
							String id = fisInfo.getId();
							deleteInstenceNow(id);
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean updateAuthType(String instanceId, String nodeId, String sufAuthType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("instanceId", instanceId);
		map.put("nodeId", nodeId);
		map.put("sufAuthType", "02");
		flowInstanceStateDao.updateAuthType(map);
		return true;
	}

	@Override
	public boolean updateRead(String instanceId, String userId) {
		FlowInstanceState fis = new FlowInstanceState();
		fis.setInstanceId(instanceId);
		fis.setSufAuthType(OperationMenu.TOREAD);
		fis.setSufDealId(userId);
		fis.setSufDealType("01");
		List<FlowInstanceState> list1 = flowInstanceStateDao.getByFis(fis);
		List<FlowInstanceLog> list = new ArrayList<FlowInstanceLog>();
		for (FlowInstanceState flowInstanceState : list1) {
			Integer isProxy = flowInstanceState.getIsProxy();
			if (isProxy != null && 1 == isProxy) {// 代理办理
				String proxyId = flowInstanceState.getProxyId();// 被代理的人
				// 删除待办表中的被代理人信息
				FlowInstanceState fs1 = new FlowInstanceState();
				fs1.setInstanceId(flowInstanceState.getInstanceId());
				fs1.setNodeId(flowInstanceState.getNodeId());
				fs1.setRouteId(flowInstanceState.getRouteId());
				fs1.setPreDealId(flowInstanceState.getPreDealId());
				fs1.setSufDealId(proxyId);
				fs1.setSufDealType("01");
				flowInstanceStateDao.deleteBantchByFis(fs1);
			} else {
				FlowInstanceState fs1 = new FlowInstanceState();
				fs1.setInstanceId(flowInstanceState.getInstanceId());
				fs1.setNodeId(flowInstanceState.getNodeId());
				fs1.setRouteId(flowInstanceState.getRouteId());
				fs1.setPreDealId(flowInstanceState.getPreDealId());
				fs1.setProxyId(flowInstanceState.getSufDealId());
				fs1.setSufAuthType(flowInstanceState.getSufAuthType());
				flowInstanceStateDao.deleteBantchByFis(fs1);
			}
			FlowInstanceLog record = new FlowInstanceLog(flowInstanceState);
			record.setDealTime(new Date());
			list.add(record);
		}
		if (list != null && list.size() > 0) {
			flowInstenceLogEngine.insertBantch(list);
		}
		int count = flowInstanceStateDao.deleteBantchByFis(fis);
		return (count == 0) ? false : true;
	}

	@Override
	public int sCount(String userId) {
		return flowInstanceStateDao.sCount(userId);
	}

	@Override
	public FlowInstenceCurrencyOVO getTodoInfo(String objId, String authType, String userId, String flowCode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("objId", objId);
		map.put("sufDealId", userId);
		map.put("sufDealType", "01");
		map.put("sufAuthType", authType);
		FlowInstanceState flowInstanceState = flowInstanceStateDao.getInstenceStateInfo(map);
		if (flowInstanceState == null && StrUtil.isEmpty(flowCode)) {// 不存在，草稿
			return null;
		}

		FlowInstenceCurrencyOVO flowInstenceCurrencyOVO = new FlowInstenceCurrencyOVO(flowInstanceState);
		String nodeId = "";
		if (flowInstanceState != null) {
			nodeId = flowInstanceState.getNodeId();
		} else {
			FlowNodeOVO flowNodeOVo = nodeDealRuleEngine.getTheNextNodeToStart(flowCode);
			if (flowNodeOVo != null) {
				nodeId = flowNodeOVo.getId();
			}
		}
		if (StrUtil.notEmpty(nodeId)) {
			List<FlowNodeButtonSimpleOVO> list = flowNodeButtonService.listNodeButtons(nodeId);
			List<FlowNodeFieldOVO> fieldList = flowNodeFieldService.listNodeField(userId, nodeId);
			flowInstenceCurrencyOVO.setNodeButtonList(list);
			flowInstenceCurrencyOVO.setNodeFieldList(fieldList);
		}
		return flowInstenceCurrencyOVO;
	}

	@Override
	public FlowInstenceStateDoneInfoOVO getDoneInfo(String objId, String preDealId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("objId", objId);
		FlowInstanceState flowInstanceState = flowInstanceStateDao.getInstenceStateDoneInfo(map);
		if (flowInstanceState != null) {
			boolean flag = isRecall(flowInstanceState.getInstanceId(), preDealId);
			if (flag) {
				flowInstanceState.setIsRecall(1);
			}
		}
		FlowInstenceStateDoneInfoOVO flowInstenceInfoForObjOvo = new FlowInstenceStateDoneInfoOVO(flowInstanceState);
		return flowInstenceInfoForObjOvo;
	}

}
