package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowInstanceStateMapper;
import com.allmsi.flow.dao.FlowNodeMapper;
import com.allmsi.flow.model.FlowInstanceLog;
import com.allmsi.flow.model.FlowInstanceState;
import com.allmsi.flow.model.FlowNode;
import com.allmsi.flow.model.NodeDealUser;
import com.allmsi.flow.model.OperationMenu;
import com.allmsi.flow.model.external.FlowBeforNodeMapKey;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ivo.NodeDealIVO;
import com.allmsi.flow.model.ovo.BusGuide;
import com.allmsi.flow.model.ovo.BusGuideNode;
import com.allmsi.flow.model.ovo.BusGuideRoute;
import com.allmsi.flow.model.ovo.FlowInstanceDealOVO;
import com.allmsi.flow.model.ovo.FlowInstanceOVO;
import com.allmsi.flow.model.ovo.FlowNodeDealOVO;
import com.allmsi.flow.model.ovo.FlowNodeOVO;
import com.allmsi.flow.model.ovo.FlowRouteOVO;
import com.allmsi.flow.model.ovo.SubGuide;
import com.allmsi.flow.reflection.FlowNodeDealUserRegisterQueryReflection;
import com.allmsi.flow.reflection.FlowUserServiceReflection;
import com.allmsi.flow.service.FlowInstanceDealService;
import com.allmsi.flow.service.FlowInstenceEngine;
import com.allmsi.flow.service.FlowInstenceLogEngine;
import com.allmsi.flow.service.FlowInstenceStateEngine;
import com.allmsi.flow.service.FlowNodeDealService;
import com.allmsi.flow.service.FlowNodeService;
import com.allmsi.flow.service.NodeDealRuleEngine;
import com.allmsi.flow.service.ReadDealRuleEngine;
import com.allmsi.flow.util.LogStatusUtil;
import com.allmsi.flow.util.NodeTypeUtil;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class NodeDealRuleEngineImpl implements NodeDealRuleEngine {

	@Resource
	private FlowNodeDealService flowNodeDealService;

	@Resource
	private ReadDealRuleEngine readDealRuleEngine;

	@Resource
	private FlowInstenceLogEngine flowInstenceLogEngine;

	@Resource
	private FlowInstenceStateEngine flowInstenceStateEngine;

	@Resource
	private FlowUserServiceReflection flowUserServiceReflection;

	@Resource
	private FlowNodeDealUserRegisterQueryReflection flowNodeDealUserRegisterQueryReflection;

	@Resource
	private FlowNodeService flowNodeService;

	@Resource
	private FlowInstenceEngine flowInstenceEngine;

	@Resource
	private FlowInstanceDealService flowInstanceDealService;

	@Resource
	private FlowNodeMapper flowNodeDao;

	@Resource
	private FlowInstanceStateMapper flowInstanceStateDao;

	@Override
	public FlowNodeOVO getTheNextNodeToStart(String flowCode) {
		return flowNodeService.getTheNextNodeToStart(flowCode);
	}

	private List<FlowUserModel> haveUserDeal(String instanceId, String nodeId) {
		List<FlowUserModel> list = new ArrayList<FlowUserModel>();
		FlowInstanceState fis = new FlowInstanceState();
		fis.setInstanceId(instanceId);
		fis.setNodeId(nodeId);
		List<FlowInstanceState> instanceStateList = flowInstanceStateDao.getByFis(fis);
		if (instanceStateList != null && instanceStateList.size() > 2) {
			for (FlowInstanceState flowInstanceState : instanceStateList) {
				list.add(new FlowUserModel(flowInstanceState.getSufId(), flowInstanceState.getSufName(),
						flowInstanceState.getSufDealType(), flowInstanceState.getSufDeptId(),
						flowInstanceState.getSufDeptName()));
			}
		}
		return list;
	}

	public BusGuide isBack(String isback, String nodeId, String instanceId, String userId, String flowCode) {
		if (StrUtil.notEmpty(isback) && StrUtil.notEmpty(nodeId) && StrUtil.notEmpty(instanceId)) {
			BusGuide busGuide = new BusGuide();
			busGuide.setType(OperationMenu.USER);
			busGuide.setMsg("选择回退节点");
			List<FlowUserModel> flowUserList = new ArrayList<FlowUserModel>();
			List<FlowRouteOVO> flowRouteList = new ArrayList<FlowRouteOVO>();
			Map<String, String> map = new HashMap<String, String>();
			map.put("nodeId", nodeId);
			map.put("instanceId", instanceId);
			map.put("sufDealId", userId);
			if ("1".equals(isback)) {// 退回一步
				List<FlowInstanceDealOVO> list = flowInstanceDealService.listPreDealUsersForNodeId(map);
				if (list != null && list.size() > 0) {
					FlowInstanceDealOVO flowInstanceDeal = null;
					for (int i = 0; i < list.size(); i++) {
						FlowInstanceDealOVO flowInstanceDealOVO = list.get(i);
						String nodeIdIndex = flowInstanceDealOVO.getPreNodeId();
						if (!nodeIdIndex.equals(nodeId)) {
							if(0 == i) {
								flowInstanceDeal = list.get(0);
							}
							flowUserList.add(new FlowUserModel(flowInstanceDeal));
							flowRouteList.add(new FlowRouteOVO(flowInstanceDeal));
							break;
						} else {
							flowInstanceDeal = flowInstanceDealOVO;
						}
					}
				}
//				FlowInstanceDealOVO flowInstanceDealOVo = flowInstanceDealService.getPreDealUserForNodeId(map);
//				if (flowInstanceDealOVo != null) {
//					flowUserList.add(new FlowUserModel(flowInstanceDealOVo));
//					flowRouteList.add(new FlowRouteOVO(flowInstanceDealOVo));
//				}
			} else if ("2".equals(isback)) {// 多步
				List<FlowInstanceDealOVO> list = flowInstanceDealService.listPreDealUsersForNodeId(map);
				for (FlowInstanceDealOVO flowInstanceDealOVo : list) {
					flowUserList.add(new FlowUserModel(flowInstanceDealOVo));
					flowRouteList.add(new FlowRouteOVO(flowInstanceDealOVo));
				}
			} else if ("3".equals(isback)) {// 退回到发起人
				FlowNodeOVO flowNodeOVo = flowNodeService.getTheNextNodeToStart(flowCode);
				FlowRouteOVO flowRouteOVo = new FlowRouteOVO();
				flowRouteOVo.setId(UUIDUtil.getUUID());
				flowRouteOVo.setSufNode(flowNodeOVo.getId());
				flowRouteOVo.setSufNodeName(flowNodeOVo.getNodeName());
				flowRouteOVo.setSufNodeType(flowNodeOVo.getNodeType());
				FlowNodeOVO flowNodeOVo1 = flowNodeService.getFlowNodeOVO(nodeId);
				flowRouteOVo.setPreNode(flowNodeOVo1.getId());
				flowRouteOVo.setPreNodeName(flowNodeOVo1.getNodeName());
				flowRouteOVo.setPreNodeType(flowNodeOVo1.getNodeType());
				flowRouteList.add(flowRouteOVo);
				FlowInstanceOVO flowInstanceOVo = flowInstenceEngine.getFlowInstanceOVO(instanceId);
				if (flowInstanceOVo != null) {
					FlowUserModel user = flowInstanceOVo.getUser();
					if (user != null) {
						flowUserList.add(user);
					}
				}
			} else {
				return new BusGuide(OperationMenu.WARN, OperationMenu.DISAGREE_OPERATION_WARN_MSG);
			}
			busGuide.setFlowUserList(flowUserList);
			busGuide.setFlowRouteList(flowRouteList);
			System.out.println("busGuide---" + busGuide.toString());
			return busGuide;
		}
		return new BusGuide(OperationMenu.CONTINUE, OperationMenu.CONTINUE_MSG);
	}

	private boolean isEnquire(String instanceId, String nodeId, String userId) {
		FlowInstanceState flowInstanceState = flowInstenceStateEngine.getInstenceStateNow(instanceId, nodeId, userId,
				"01");
		if (flowInstanceState != null) {
			String preDeal = flowInstanceState.getPreDealId();
			FlowInstanceLog flowInstanceLog = flowInstenceLogEngine.getDoneInfo(instanceId, nodeId, preDeal);
			if (flowInstanceLog != null) {
				if (LogStatusUtil.ENQUIRE.equals(flowInstanceLog.getDealState())) {// 问询
					return true;
				}
			}
		}
		return false;

	}

	@Override
	public BusGuide nodeType(String instanceId, String nodeId, String isback, String userId, String flowCode) {
		if (StrUtil.notEmpty(nodeId)) {
			FlowNode flowNode = flowNodeDao.selectByPrimaryKey(nodeId);
			if (flowNode != null) {
				// 是否是问询
				boolean isEnquire = isEnquire(instanceId, nodeId, userId);
				if (isEnquire) {
					return isBack("1", nodeId, instanceId, userId, flowCode);
				}
				String nodeType = flowNode.getNodeType();
				if (NodeTypeUtil.SINGLE.equals(nodeType)) {// 单人办理

				}
				if (NodeTypeUtil.MULTI_PARALLEL.equals(nodeType)) {// 多人并行办理
					List<FlowUserModel> list = haveUserDeal(instanceId, nodeId);
					if (list != null && list.size() > 1) {// "该节点还有未办理的人员，暂不能继续下一个节点的流程发起"
						BusGuide busGuide = new BusGuide(OperationMenu.MULTI_PERSON_HANDLING, "当前还存在其他办理人");
						busGuide.setFlowUserList(list);
						return busGuide;
					}
				}
				return isBack(isback, nodeId, instanceId, userId, flowCode);
			}
		}
		return new BusGuide(OperationMenu.WARN, OperationMenu.PARAMETER_MSG);
	}

	@Override
	public BusGuideNode guideNode(BusGuideRoute busGuideRoute, String instanceId, String userId) {
		List<FlowRouteOVO> flowRouteList = busGuideRoute.getFlowRouteList();
		boolean flag = busGuideRoute.isFlag();
		List<FlowUserModel> routelist = new ArrayList<FlowUserModel>();// 路由处理人
		routelist.addAll(busGuideRoute.getRouteUserList());
		if (flag) {// 是否要路由人员
			return new BusGuideNode("user", "user", routelist, flowRouteList);
		}
		FlowRouteOVO flowRouteOVo = flowRouteList.get(0);
		String nodeId = flowRouteOVo.getSufNode();
		List<FlowUserModel> readList = readDealRuleEngine.getReadDealUser(nodeId, instanceId);

		boolean isfinsh = flowNodeService.isfinish(nodeId);
		if (isfinsh) {
			List<FlowUserModel> noneUser = new ArrayList<FlowUserModel>();
			return new BusGuideNode("user", "user", noneUser, readList, flowRouteList);
		}

		List<FlowNodeDealOVO> fndList = flowNodeDealService.listFlowNodeDeal(nodeId);
		NodeDealUser nodeDealUser = getNodeDealUser(fndList, instanceId, nodeId);// 节点处理人
		List<FlowUserModel> list = removeRepetition(routelist, nodeDealUser);// 取交集
		FlowNodeOVO flowNodeOVo = flowNodeService.getFlowNodeOVO(nodeId);
		if (list != null && list.size() > 0) {
			if (1 == list.size() && userId.equals(list.get(0).getUserId())
					&& (!"99".equals(flowNodeOVo.getContinuation()))) {
				return new BusGuideNode("nodeId", "nodeId", nodeId);
			}
			return new BusGuideNode("user", "user", list, readList, flowRouteList);
		} else {// 为空
			if (flowNodeOVo != null) {
				if (nodeDealUser != null) {
					boolean isSelfProcessing = nodeDealUser.isSelfProcessing();// 是否需要回调
					if (isSelfProcessing) {// 有回调，交给前端处理
						return new BusGuideNode("user", "前端自行处理", nodeId, isSelfProcessing,
								nodeDealUser.getProcessingParam(), list, readList, flowRouteList);
					}
				}
				// 节点办理人不需要节点回调时
				String isContinuation = flowNodeOVo.getContinuation();
				if ("01".equals(isContinuation)) {// 继续
					return new BusGuideNode("nodeId", "nodeId", nodeId);
				}
			}
			return new BusGuideNode("user", "user", flowUserServiceReflection.getFlowExternalService().selectUserList(),
					readList, flowRouteList);
		}
	}

	private NodeDealUser getNodeDealUser(List<FlowNodeDealOVO> fndList, String instanceId, String nodeId) {
		List<FlowUserModel> nodelist = new ArrayList<FlowUserModel>();// 节点处理人
		boolean flag = false;
		StringBuffer processingParam = new StringBuffer();
		for (FlowNodeDealOVO flowNodeDealOVo : fndList) {
			String nodeDealId = flowNodeDealOVo.getNodeDealId();
			String nodeDealType = flowNodeDealOVo.getNodeDealType();
			switch (nodeDealType) {
			case "01":// 查询用户
				nodelist.add(flowUserServiceReflection.getFlowExternalService().selectUserInfo(nodeDealId));
				break;
			case "02":// 查询部门
				nodelist.addAll(flowUserServiceReflection.getFlowExternalService().selectUserByDeptId(nodeDealId));
				break;
			case "03":// 查询角色
				nodelist.addAll(flowUserServiceReflection.getFlowExternalService().selectUserByRoleId(nodeDealId));
				break;
			case "04":// 回调业务
				flag = true;
				processingParam.append(nodeDealId);
				FlowInstanceOVO flowInstanceOVO = flowInstenceEngine.getFlowInstanceOVO(instanceId);
				if (flowInstanceOVO != null && StrUtil.notEmpty(flowInstanceOVO.getObjectId())) {
					Map<String, List<FlowUserModel>> map = flowNodeDealUserRegisterQueryReflection
							.getFlowNodeDealUserService().listNodeDealUsers(flowInstanceOVO.getObjectId(), nodeId);
					String key = FlowBeforNodeMapKey.TYPE_NODE_KEY + nodeId;
					if (map != null && map.get(key) != null) {
						List<FlowUserModel> dealUserList = map.get(key);
						nodelist.addAll(dealUserList);
					}
				}
				break;
			}
		}
		return new NodeDealUser(nodeId, flag, processingParam.toString(), nodelist);
	}

	private List<FlowUserModel> removeRepetition(List<FlowUserModel> routelist, NodeDealUser nodeDealUser) {
		List<FlowUserModel> list = new ArrayList<FlowUserModel>();
		List<FlowUserModel> nodelist = new ArrayList<FlowUserModel>();
		if (nodeDealUser != null) {
			nodelist.addAll(nodeDealUser.getList());
		}
		if (routelist != null && routelist.size() > 0 && nodelist != null && nodelist.size() > 0) {
			list.addAll(nodelist);
			nodelist.removeAll(routelist);
			list.removeAll(nodelist);
		} else if (routelist != null && routelist.size() > 0 && (nodelist == null || nodelist.size() < 1)) {
			list.addAll(routelist);
		} else if (nodelist != null && nodelist.size() > 0 && (routelist == null || routelist.size() < 1)) {
			list.addAll(nodelist);
		} else {
			list.addAll(nodelist);
			list.addAll(routelist);
		}
		return list;
	}

	@Override
	public SubGuide nodeTypeSubmit(String sufNodeId, List<NodeDealIVO> nodeDealList) {
		FlowNode flowNode = flowNodeDao.selectByPrimaryKey(sufNodeId);
		if (flowNode != null) {
			String type = flowNode.getNodeType();
			if ("11".equals(type)) {// 单人办理
				if (nodeDealList != null && nodeDealList.size() > 1) {
					return new SubGuide(OperationMenu.ERROR, "单人办理，请选择正确办理人！");
				}
			}
			if (type.startsWith("2")) {// 多人办理
				if (nodeDealList != null && nodeDealList.size() < 1) {
					return new SubGuide(OperationMenu.ERROR, "多人办理，请选择正确办理人！");
				}
			}
			return new SubGuide(OperationMenu.SUCCESS, "");
		}
		return new SubGuide(OperationMenu.ERROR, "请选择正确办理人！");
	}
}
