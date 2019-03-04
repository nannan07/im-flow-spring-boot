package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowInstanceMapper;
import com.allmsi.flow.dao.FlowMapper;
import com.allmsi.flow.dao.FlowNodeMapper;
import com.allmsi.flow.model.AuthGuide;
import com.allmsi.flow.model.FlowInstance;
import com.allmsi.flow.model.FlowInstanceLog;
import com.allmsi.flow.model.FlowInstanceState;
import com.allmsi.flow.model.FlowNode;
import com.allmsi.flow.model.OperationMenu;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ivo.FlowInstanceDealIVO;
import com.allmsi.flow.model.ivo.FlowInstanceIVO;
import com.allmsi.flow.model.ivo.FlowInstanceStateIVO;
import com.allmsi.flow.model.ivo.NodeDealIVO;
import com.allmsi.flow.model.ovo.BusGuide;
import com.allmsi.flow.model.ovo.BusGuideNode;
import com.allmsi.flow.model.ovo.BusGuideRoute;
import com.allmsi.flow.model.ovo.FlowInstenceCurrencyOVO;
import com.allmsi.flow.model.ovo.FlowNodeOVO;
import com.allmsi.flow.model.ovo.FlowRouteOVO;
import com.allmsi.flow.model.ovo.SubGuide;
import com.allmsi.flow.reflection.FlowUserServiceReflection;
import com.allmsi.flow.service.FlowInstanceStateService;
import com.allmsi.flow.service.FlowInstenceEngine;
import com.allmsi.flow.service.FlowInstenceLogEngine;
import com.allmsi.flow.service.FlowInstenceStateEngine;
import com.allmsi.flow.service.FlowNodeService;
import com.allmsi.flow.service.FlowOpinionEngine;
import com.allmsi.flow.service.FlowRouteService;
import com.allmsi.flow.service.NodeDealRuleEngine;
import com.allmsi.flow.service.OperationBusService;
import com.allmsi.flow.service.ReadDealRuleEngine;
import com.allmsi.flow.service.RouteDealRuleEngine;
import com.allmsi.flow.service.TransferEngine;
import com.allmsi.flow.util.LogStatusUtil;
import com.allmsi.flow.util.NodeTypeUtil;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class OperationBusServiceImpl implements OperationBusService {

	@Resource
	private RouteDealRuleEngine routeDealRuleEngine;

	@Resource
	private NodeDealRuleEngine nodeDealRuleEngine;

	@Resource
	private ReadDealRuleEngine readDealRuleEngine;

	@Resource
	private FlowInstenceEngine flowInstenceEngine;

	@Resource
	private FlowInstenceStateEngine flowInstenceStateEngine;

	@Resource
	private FlowOpinionEngine flowOpinionEngine;

	@Resource
	private FlowInstenceLogEngine flowInstenceLogEngine;

	@Resource
	private TransferEngine transferEngine;

	@Resource
	private FlowRouteService flowRouteService;

	@Resource
	private FlowNodeService flowNodeService;

	@Resource
	private FlowUserServiceReflection flowUserServiceReflection;

	@Resource
	private FlowInstanceStateService flowInstanceStateService;

	@Resource
	private FlowMapper flowDao;

	@Resource
	private FlowNodeMapper flowNodeDao;

	@Resource
	private FlowInstanceMapper flowInstanceDao;

	@Override
	public BusGuide guide(String flowCode, String instanceId, String nodeId, String isback, String routeId,
			String query, String userId) {
		BusGuide busGuide = new BusGuide();
		if (StrUtil.isEmpty(flowCode)) {
			busGuide.setType(OperationMenu.ERROR);
			busGuide.setMsg("error");
			return busGuide;
		}
		if (StrUtil.isEmpty(instanceId) && StrUtil.isEmpty(nodeId)) {
			FlowNodeOVO flowNodeOVo = nodeDealRuleEngine.getTheNextNodeToStart(flowCode);
			if (flowNodeOVo != null) {
				nodeId = flowNodeOVo.getId();
			} else {
				busGuide.setType(OperationMenu.ERROR);
				busGuide.setMsg("路由节点配置错误，请联系管理员");
				return busGuide;
			}
		}
		BusGuide busGuide1 = nodeDealRuleEngine.nodeType(instanceId, nodeId, isback, userId, flowCode);
		if (busGuide1 == null || !OperationMenu.CONTINUE.equals(busGuide1.getType())) {
			return busGuide1;
		}
		BusGuideRoute busGuideRoute = routeGuide(flowCode, instanceId, nodeId, routeId, query, userId);
		String type = busGuideRoute.getType();
		if (!OperationMenu.USER.equals(type)) {
			busGuide.setType(type);
			busGuide.setMsg(busGuideRoute.getMsg());
			busGuide.setFlowRouteList(busGuideRoute.getFlowRouteList());
			return busGuide;
		}
		return nodeGuide(flowCode, instanceId, userId, busGuideRoute, isback, query);
	}

	private BusGuideRoute routeGuide(String flowCode, String instanceId, String nodeId, String routeId, String query,
			String userId) {
		BusGuideRoute busGuideRoute = new BusGuideRoute();
		if (StrUtil.notEmpty(routeId)) {// 路由已选定
			FlowRouteOVO flowRouteOVo = routeDealRuleEngine.getFlowRoute(routeId);
			System.out.println("选定的路由：" + flowRouteOVo.toString());
			if (flowRouteOVo != null) {
				busGuideRoute.setType(OperationMenu.ROUTE);
				busGuideRoute = routeDealRuleEngine.getFlowDealModelList(flowRouteOVo, instanceId, userId,flowCode);// 查询路由人员信息
				busGuideRoute.setType(OperationMenu.USER);
			}
		} else {// 查询路由,筛选出最终的一条路由
			List<FlowRouteOVO> routeList = routeDealRuleEngine.guideRoute(flowCode, nodeId, query, userId);
			if (routeList != null && routeList.size() > 0) {// 存在路由
				int size = routeList.size();
				if (1 == size) {// 路由已选定
					busGuideRoute.setType(OperationMenu.ROUTE);
					busGuideRoute = routeGuide(flowCode, instanceId, nodeId, routeList.get(0).getId(), query, userId);// 查询路由人员信息
				} else {
					BusGuideRoute br = routeDealRuleEngine.ruleJudgment(routeList, instanceId, userId, nodeId, query);
					if (OperationMenu.ROUTE.equals(br.getType()) && br.getFlowRouteList() != null
							&& br.getFlowRouteList().size() == 1) {
						busGuideRoute.setType(OperationMenu.ROUTE);
						busGuideRoute = routeGuide(flowCode, instanceId, nodeId, br.getFlowRouteList().get(0).getId(),
								query, userId);// 查询路由人员信息
					} else {
						return br;
					}
				}
			} else {
				busGuideRoute.setType(OperationMenu.ERROR);
				busGuideRoute.setMsg("error");
			}
		}
		return busGuideRoute;
	}

	private BusGuide nodeGuide(String flowCode, String instanceId, String userId, BusGuideRoute busGuideRoute,
			String isback, String query) {
		BusGuideNode busGuideNode = nodeDealRuleEngine.guideNode(busGuideRoute, instanceId, userId);
		String type = busGuideNode.getType();
		if ("user".equals(type)) {
			return new BusGuide(OperationMenu.USER, busGuideNode.getMsg(), busGuideNode.getProcessingParam(),
					busGuideNode.getFlowUserList(), busGuideNode.getFlowReadList(), busGuideNode.getFlowRouteList());
		}
		if ("nodeId".equals(type)) {
			return guide(flowCode, instanceId, busGuideNode.getNodeId(), isback, null, query, userId);
		}
		return new BusGuide(OperationMenu.ERROR, "error");
	}

	@Override
	public SubGuide submit(String preDealId, FlowInstenceCurrencyOVO flowInfoVo, List<NodeDealIVO> nodeDealList) {
		if (flowInfoVo == null) {
			return new SubGuide(OperationMenu.WARN, "flowInfo不可为空");
		}

		String flowId = flowInfoVo.getFlowId();
		String instanceId = flowInfoVo.getInstanceId();
		String preNodeId = flowInfoVo.getPreNodeId();
		String sufNodeId = flowInfoVo.getSufNodeId();
		String remark = flowInfoVo.getRemark();
		String opinion = flowInfoVo.getOpinion();
		String routeId = flowInfoVo.getRouteId();
		String dealState = flowInfoVo.getDealState();
		String objId = flowInfoVo.getObjectId();
		if (StrUtil.isEmpty(objId)) {
			objId = flowInfoVo.getId();
		}
		if (StrUtil.isEmpty(instanceId) && StrUtil.isEmpty(objId)) {
			return new SubGuide(OperationMenu.WARN, "instanceId和objId不可同时为空");
		}

		if ((StrUtil.isEmpty(flowId) && StrUtil.isEmpty(instanceId)) || StrUtil.isEmpty(preDealId)
				|| StrUtil.isEmpty(sufNodeId)) {
			return new SubGuide(OperationMenu.WARN, OperationMenu.PARAMETER_MSG);
		}
		if (StrUtil.notEmpty(sufNodeId)) {// 判断节点人员的办理类型,匹配人员是否符合
			SubGuide subGuide = nodeDealRuleEngine.nodeTypeSubmit(sufNodeId, nodeDealList);
			if (subGuide == null || OperationMenu.ERROR.equals(subGuide.getType())) {
				return subGuide;
			}
		}
		SubGuide subGuide = flowInstenceEngine.flowInstanceEngine(objId, instanceId);
		if (OperationMenu.SUCCESS.equals(subGuide.getType())) {// 合法
			instanceId = (String) subGuide.getData();
			if (StrUtil.isEmpty(instanceId)) {// 初始提交
				FlowInstanceState flowInstanceState = addFlowInstence(flowId, objId, preDealId, remark, opinion, "01");
				if (flowInstanceState == null || StrUtil.isEmpty(flowInstanceState.getInstanceId())) {
					return new SubGuide(OperationMenu.ERROR, "添加流程实例失败");
				}
				instanceId = flowInstanceState.getInstanceId();
				preNodeId = flowInstanceState.getNodeId();
			}

			// 添加待办待阅
			return addTodo(flowId, instanceId, preNodeId, sufNodeId, preDealId, remark, dealState, opinion, routeId,
					nodeDealList);
		}
		return subGuide;
	}

	@Override
	public BusGuide vote(String instanceId, String nodeId, String userId, String voteState, String opinion,
			String query) {
		if (StrUtil.isEmpty(instanceId) || StrUtil.isEmpty(userId) || StrUtil.isEmpty(voteState)) {
			return new BusGuide(OperationMenu.WARN, OperationMenu.PARAMETER_MSG);
		}
		FlowInstanceState fs = flowInstenceStateEngine.getInstenceStateNow(instanceId, nodeId, userId,
				OperationMenu.TODO);// 当前人的待办信息
		if (fs == null || StrUtil.isEmpty(fs.getId())) {
			return new BusGuide(OperationMenu.WARN, "实例状态不存在");
		}
		String dealState = "";
		if ("1".equals(voteState)) {// 同意
			dealState = LogStatusUtil.AGREE;
		} else if ("0".equals(voteState)) {
			dealState = LogStatusUtil.DISAGREE;
		}
		String stateId = fs.getId();
		boolean isLastOne = isLastOne(instanceId, nodeId);
		List<NodeDealIVO> list = new ArrayList<NodeDealIVO>();
		FlowRouteOVO flowRouteOVO = new FlowRouteOVO();// 路由
		if (isLastOne) {// 最后一个人
			List<FlowUserModel> fuList = new ArrayList<FlowUserModel>();// 人员
			boolean isPass = isPass(instanceId, nodeId, voteState);
			if (isPass) {
				BusGuideRoute busGuideRoute = routeGuide(fs.getFlowCode(), instanceId, nodeId, "", query, userId);
				String type = busGuideRoute.getType();
				if (!OperationMenu.USER.equals(type)) {
					return new BusGuide(type, busGuideRoute.getMsg(), null, busGuideRoute.getFlowRouteList());
				}
				flowRouteOVO = busGuideRoute.getFlowRouteList().get(0);
				BusGuide nodeGuide = nodeGuide(fs.getFlowCode(), instanceId, userId, busGuideRoute, "", query);
				fuList.addAll(nodeGuide.getFlowUserList());
			} else {
				BusGuide busGuide = nodeDealRuleEngine.isBack("3", nodeId, instanceId, userId, fs.getFlowCode());
				if (busGuide != null && OperationMenu.USER.equals(busGuide.getType())) {
					fuList.addAll(busGuide.getFlowUserList());
					List<FlowRouteOVO> frList = busGuide.getFlowRouteList();
					flowRouteOVO = frList.get(0);
				}
			}
			for (FlowUserModel flowUserModel : fuList) {
				list.add(new NodeDealIVO(flowUserModel.getUserId(), "01", "01", flowUserModel.getDeptId(),
						flowUserModel.getDeptName(), flowUserModel.getName()));
			}
		}
		flowOpinionEngine.insertOpinion(userId, opinion, stateId);// 添加意见
		flowInstenceLogEngine.insertFlowInstenceLog(fs, "", dealState);// 添加已办
		flowInstenceStateEngine.deleteInstenceNow(fs.getId());// 删除自己待办
		if (list != null && list.size() > 0) {
			AuthGuide authGuide = addAuthTypeState(fs.getFlowId(), instanceId, flowRouteOVO.getSufNode(), userId,
					flowRouteOVO.getId(), list);
			return (authGuide != null && authGuide.getStateIdList() != null && authGuide.getStateIdList().size() > 0)
					? new BusGuide(OperationMenu.SUCCESS, "")
					: new BusGuide(OperationMenu.WARN, "");
		}
		return new BusGuide(OperationMenu.SUCCESS, "");
	}

	@Override
	public List<FlowUserModel> transfer(String userId, String nodeId) {
		if (StrUtil.isEmpty(userId) || StrUtil.isEmpty(nodeId)) {
			return null;
		}
		return flowUserServiceReflection.getFlowExternalService().deptUserByUser(userId);
	}

	@Override
	public SubGuide isRecall(String preDealId, String instanceId) {
		// 判断流程实例是上一节点是当前人的待办信息，并且路由可以撤回
		boolean flag = flowInstenceStateEngine.isRecall(instanceId, preDealId);
		FlowInstanceLog flowInstanceLog = flowInstenceLogEngine.getDoneInfo(instanceId, null, null);
		if (flowInstanceLog != null && preDealId.equals(flowInstanceLog.getSufDealId())) {
			if (flag) {
				flag = flowInstenceStateEngine.recall(preDealId, instanceId);
				return flag ? new SubGuide(OperationMenu.SUCCESS, "撤回成功") : new SubGuide(OperationMenu.WARN, "路由不可以撤回");
			}
		}
		return new SubGuide(OperationMenu.WARN, "路由不可以撤回");
	}

	private SubGuide addTodo(String flowId, String instanceId, String preNodeId, String sufNodeId, String preDealId,
			String remark, String dealState, String opinion, String routeId, List<NodeDealIVO> list) {
		if (StrUtil.isEmpty(instanceId) || StrUtil.isEmpty(routeId) || StrUtil.isEmpty(preDealId)
				|| StrUtil.isEmpty(preNodeId) || StrUtil.isEmpty(sufNodeId)) {
			return new SubGuide(OperationMenu.ERROR, "流程实例参数不合法");
		}
		FlowInstanceState fs = flowInstenceStateEngine.getInstenceStateNow(instanceId, preNodeId, preDealId,
				OperationMenu.TODO);
		if (fs == null) {
			return new SubGuide(OperationMenu.ERROR, "实例状态不存在");
		}

		FlowNode flowNode = flowNodeDao.selectByPrimaryKey(preNodeId);
		if (flowNode == null) {
			System.err.println("节点传入错误");
			return new SubGuide(OperationMenu.ERROR, "节点传入错误");
		}
		AuthGuide authGuide = new AuthGuide();
		if (flowNode != null) {
			String nodeType = flowNode.getNodeType();
			if (fs != null) {// 更改待办对应的todo信息
				String stateId = fs.getId();
				flowOpinionEngine.insertOpinion(preDealId, opinion, stateId);
				flowInstenceLogEngine.insertFlowInstenceLog(fs, remark, dealState);// 添加已办
			}
			if (NodeTypeUtil.SINGLE.equals(nodeType)) {// 单人办理
				flowInstenceStateEngine.deleteBantchForInstanceAndNodeId(instanceId, preNodeId, OperationMenu.TODO);// 删除待办信息
				authGuide = addAuthTypeState(flowId, instanceId, sufNodeId, preDealId, routeId, list);
			}
			if (NodeTypeUtil.MULTI_PARALLEL.equals(nodeType) || NodeTypeUtil.MULTI_PERSON_VOTE_WHOLE.equals(nodeType)) {// 多人并行办理|多人完全投票
				List<FlowInstanceState> InstanceStateList = flowInstenceStateEngine.listByFis(instanceId, preNodeId,
						OperationMenu.TODO);
				if (InstanceStateList != null && InstanceStateList.size() > 1) {// 还有其他办理人
					boolean flag = flowInstenceStateEngine.deleteInstenceNow(fs.getId());// 删除自己待办
					if (flag) {
						return new SubGuide(OperationMenu.SUCCESS, "", false, "");
					} else {
						return new SubGuide(OperationMenu.ERROR, "办理失败");
					}
				} else {// 最后一个办理人
					flowInstenceStateEngine.deleteBantchForInstanceAndNodeId(instanceId, preNodeId, OperationMenu.TODO);// 删除待办信息
					authGuide = addAuthTypeState(flowId, instanceId, sufNodeId, preDealId, routeId, list);
				}
			}
			if (NodeTypeUtil.MULTI_PERSON_PREEMPTIVE_HANDLING.equals(nodeType)) {// 多人抢占办理
				flowInstenceStateEngine.deleteInstenceNow(fs.getId());// 删除自己待办
				flowInstenceStateEngine.updateAuthType(instanceId, preNodeId, OperationMenu.TOREAD);
				authGuide = addAuthTypeState(flowId, instanceId, sufNodeId, preDealId, routeId, list);
			}
		}
		boolean flag1 = doFinish(sufNodeId, instanceId);// 结束
		if ((!flag1)
				&& (authGuide == null || authGuide.getStateIdList() == null || authGuide.getStateIdList().size() < 1)) {// 没结束
			return new SubGuide(OperationMenu.ERROR, "添加流程实例状态失败");
		}
		List<String> stateIdList = new ArrayList<String>();
		List<String> userIds = new ArrayList<String>();
		List<String> todoUserIds = new ArrayList<String>();
		List<String> readUserIds = new ArrayList<String>();
		if (authGuide != null && authGuide.getStateIdList() != null && authGuide.getStateIdList().size() > 0) {
			stateIdList.addAll(authGuide.getStateIdList());
			userIds.addAll(authGuide.getUserIds());// 待办待阅人员
			todoUserIds.addAll(authGuide.getTodoUserIds());
			readUserIds.addAll(authGuide.getToreadUserIds());
		}
		return new SubGuide(OperationMenu.SUCCESS, "", flag1, instanceId, userIds, todoUserIds, readUserIds);
	}

	// 添加待办和待阅人员
	private AuthGuide addAuthTypeState(String flowId, String instanceId, String sufNodeId, String preDealId,
			String routeId, List<NodeDealIVO> list) {
		if (StrUtil.isEmpty(sufNodeId) || StrUtil.isEmpty(instanceId)) {
			return null;
		}
		List<String> stateIdList = new ArrayList<String>();
		List<FlowUserModel> rlist = readDealRuleEngine.getReadDealUser(sufNodeId, instanceId);
		Set<String> set = new HashSet<String>();
		if (list != null && list.size() > 0) {
			for (NodeDealIVO nodeDealIVo : list) {
				set.add(nodeDealIVo.getDealId());
			}
		}
		if (rlist != null && rlist.size() > 0) {
			for (FlowUserModel flowUserModel : rlist) {
				String rUserId = flowUserModel.getUserId();
				if (!set.contains(rUserId)) {// 除去下一节点所选的待办人员
					list.add(new NodeDealIVO(flowUserModel.getUserId(), "01", OperationMenu.TOREAD,
							flowUserModel.getDeptId(), flowUserModel.getDeptName(), flowUserModel.getName()));
				}
			}
		}

		if (StrUtil.isEmpty(flowId)) {
			FlowInstance flowInstance = flowInstanceDao.selectByPrimaryKey(instanceId);
			if (flowInstance != null) {
				flowId = flowInstance.getFlowId();
			}
		}
		// 获取权限转移后的人员
		List<NodeDealIVO> list1 = new ArrayList<NodeDealIVO>();
		if (StrUtil.notEmpty(flowId)) {
			list1.addAll(getNodeDealList(flowId, sufNodeId, list));
		} else {
			list1.addAll(list);
		}
		stateIdList = flowInstenceStateEngine.insertFlowInstanceStateForNodeDealList(instanceId, sufNodeId, routeId,
				preDealId, list1);
		if (stateIdList == null || stateIdList.size() < 1) {
			return null;
		}
		List<String> userIds = new ArrayList<String>();
		List<String> todoUserIds = new ArrayList<String>();
		List<String> toreadUserIds = new ArrayList<String>();

		for (NodeDealIVO nodeDealIVo : list1) {
			String authType = nodeDealIVo.getAuthType();
			if (OperationMenu.TOREAD.equals(authType)) {
				toreadUserIds.add(nodeDealIVo.getDealId());
			} else {
				todoUserIds.add(nodeDealIVo.getDealId());
			}
			userIds.add(nodeDealIVo.getDealId());
		}
		AuthGuide authGuide = new AuthGuide();
		authGuide.setStateIdList(stateIdList);
		authGuide.setUserIds(userIds);
		authGuide.setTodoUserIds(todoUserIds);
		authGuide.setToreadUserIds(toreadUserIds);
		return authGuide;
	}

	// 获取权限转移后的人员
	private List<NodeDealIVO> getNodeDealList(String flowId, String nodeId, List<NodeDealIVO> list) {
		if (StrUtil.isEmpty(flowId)) {
			return null;
		}
		String flowCode = flowDao.getFlowCodeById(flowId);
		if (StrUtil.isEmpty(flowCode)) {
			return null;
		}
		List<NodeDealIVO> ndList = new ArrayList<NodeDealIVO>();
		if (list != null && list.size() > 0) {
			for (NodeDealIVO nodeDealIVo : list) {
				String dealId = nodeDealIVo.getDealId();
				String authType = nodeDealIVo.getAuthType();
				List<NodeDealIVO> index = transferEngine.selectTransferUser(flowCode, nodeId, dealId);
				for (NodeDealIVO nodeDealIVo1 : index) {
					NodeDealIVO nd = new NodeDealIVO(nodeDealIVo1.getDealId(), "01", authType, nodeDealIVo1.getDeptId(),
							nodeDealIVo1.getDeptName(), nodeDealIVo1.getDealName(), nodeDealIVo1.getIsProxy(),
							nodeDealIVo1.getProxyId());
					ndList.add(nd);
				}
			}
		}
		return ndList;
	}

	private FlowInstanceState addFlowInstence(String flowId, String objId, String preDealId, String remark,
			String opinion, String dealState) {
		if (StrUtil.isEmpty(objId) || StrUtil.isEmpty(preDealId)) {
			return null;
		}
		FlowInstanceState flowInstanceState = new FlowInstanceState();
		String instanceId = null;
		instanceId = flowInstenceEngine.addFlowInstance(new FlowInstanceIVO(flowId, objId, preDealId, 1));// 添加流程实例
		if (StrUtil.notEmpty(instanceId)) { // 添加流程已办信息
			FlowRouteOVO flowRouteOVo = flowRouteService.getStartNodeRoute(flowId);
			FlowUserModel flowUserModel = flowUserServiceReflection.getFlowExternalService().selectUserInfo(preDealId);
			if (flowUserModel != null && flowRouteOVo != null) {
				FlowInstanceDealIVO fidvo = new FlowInstanceDealIVO();
				fidvo.setId(UUIDUtil.getUUID());
				fidvo.setStateId(instanceId);
				fidvo.setPreId(preDealId);
				fidvo.setPreName(flowUserModel.getName());
				fidvo.setPreDeptId(flowUserModel.getDeptId());
				fidvo.setPreDeptName(flowUserModel.getDeptName());
				flowInstenceStateEngine.insertFlowInstenceDeal(fidvo);// 实例对应的处理人信息
				String routeId1 = flowRouteOVo.getId();
				String nodeId = flowRouteOVo.getSufNode();
				FlowInstanceStateIVO flowInstanceStateIVo = new FlowInstanceStateIVO(UUIDUtil.getUUID(), instanceId,
						nodeId, routeId1, null, preDealId, "01", OperationMenu.TODO, preDealId);
				String stateId = flowInstenceStateEngine.insertFlowInstanceState(flowInstanceStateIVo);// 添加开始节点的办理状态
				FlowInstanceDealIVO vo = new FlowInstanceDealIVO();
				vo.setId(UUIDUtil.getUUID());
				vo.setStateId(stateId);
				vo.setSufId(preDealId);
				vo.setSufName(flowUserModel.getName());
				vo.setSufDeptId(flowUserModel.getDeptId());
				vo.setSufDeptName(flowUserModel.getDeptName());
				flowInstenceStateEngine.insertFlowInstenceDeal(vo);// 添加开始节点的处理信息
				flowOpinionEngine.insertOpinion(preDealId, opinion, stateId);// 添加意见
				flowInstanceState.setInstanceId(instanceId);
				flowInstanceState.setNodeId(flowRouteOVo.getSufNode());
				return flowInstanceState;
			}
		}
		return null;
	}

	private boolean doFinish(String nodeId, String instanceId) {
		boolean flag = flowNodeService.isfinish(nodeId);
		if (flag) {
			FlowInstanceLog record = new FlowInstanceLog();
			record.setId(UUIDUtil.getUUID());
			record.setInstanceId(instanceId);
			record.setNodeId(nodeId);
			record.setcTime(new Date());
			record.setDealState("05");
			flowInstenceLogEngine.insertFlowInstenceLog(record);
		}
		return flag;
	}

	private boolean isLastOne(String instanceId, String nodeId) {
		List<FlowInstanceState> instanceStateList = flowInstenceStateEngine.listByFis(instanceId, nodeId,
				OperationMenu.TODO);
		return (instanceStateList != null && instanceStateList.size() > 1) ? false : true;
	}

	private boolean isPass(String instanceId, String nodeId, String voteState) {
		// 查询投票结果
		List<String> dealStateList = flowInstenceLogEngine.listVote(instanceId, nodeId);
		int agreeCount = 0;
		int disAgreeCount = 0;
		if ("1".equals(voteState)) {// 同意
			agreeCount = 1;
		} else if ("0".equals(voteState)) {
			disAgreeCount = 1;
		}
		if (dealStateList != null && dealStateList.size() > 0) {
			for (String string : dealStateList) {
				if (LogStatusUtil.AGREE.equals(string)) {// 通过
					agreeCount += 1;
				} else if (LogStatusUtil.DISAGREE.equals(string)) {// 不通过
					disAgreeCount += 1;
				}

			}
		}
		return (agreeCount > disAgreeCount) ? true : false;
	}
}
