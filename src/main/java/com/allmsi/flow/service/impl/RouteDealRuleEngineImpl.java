package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.allmsi.flow.model.OperationMenu;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ovo.BusGuide;
import com.allmsi.flow.model.ovo.BusGuideRoute;
import com.allmsi.flow.model.ovo.FlowInstanceOVO;
import com.allmsi.flow.model.ovo.FlowRouteOVO;
import com.allmsi.flow.reflection.FlowUserServiceReflection;
import com.allmsi.flow.service.FlowInstenceEngine;
import com.allmsi.flow.service.FlowInstenceStateEngine;
import com.allmsi.flow.service.FlowRouteDealQueryService;
import com.allmsi.flow.service.FlowRouteService;
import com.allmsi.flow.service.NodeDealRuleEngine;
import com.allmsi.flow.service.RouteDealRuleEngine;
import com.allmsi.sys.util.StrUtil;

@Service
public class RouteDealRuleEngineImpl implements RouteDealRuleEngine {

	@Resource
	private FlowUserServiceReflection flowUserServiceReflection;

	@Resource
	private FlowRouteService flowRouteService;

	@Resource
	private FlowInstenceEngine flowInstenceEngine;

	@Resource
	private FlowInstenceStateEngine flowInstenceStateEngine;

	@Resource
	private FlowRouteDealQueryService flowRouteDealQueryService;
	
	@Resource
	private NodeDealRuleEngine nodeDealRuleEngine;

	@Override
	public List<FlowRouteOVO> guideRoute(String flowCode, String nodeId, String query, String userId) {
		List<FlowRouteOVO> list = new ArrayList<FlowRouteOVO>();
		List<FlowRouteOVO> flowRouteOVoList = flowRouteService.listRouteListByPreNode(flowCode, nodeId);// 获取到路由列表信息
		if (flowRouteOVoList != null && flowRouteOVoList.size() > 0) {
			for (FlowRouteOVO flowRouteOVo : flowRouteOVoList) {
				String id = flowRouteOVo.getDealId();
				List<String> queryList = flowRouteDealQueryService.queryList(id);
				flowRouteOVo.setQueryList(queryList);
				list.add(flowRouteOVo);
			}
		}
		return list;
	}

	@Override
	public BusGuideRoute ruleJudgment(List<FlowRouteOVO> frList, String instanceId, String userId, String nodeId,
			String query) {
		BusGuideRoute busGuideRoute = new BusGuideRoute();
		List<FlowRouteOVO> selectedRouteList = frList;
		if (StrUtil.isEmpty(query)) {
			String strQuery = getQueryRule(frList);// 判断是否有查询条件
			if (StrUtil.isEmpty(strQuery)) {// 不需要查询条件
				busGuideRoute.setType(OperationMenu.ROUTE);
				busGuideRoute.setFlowRouteList(selectedRouteList);
				return busGuideRoute;// 多条路由
			} else {// 需要查询条件
				if (StrUtil.isEmpty(query)) {// 如果没有传入query
					busGuideRoute.setType(OperationMenu.QUERY);
					busGuideRoute.setMsg(strQuery);
					return busGuideRoute;// 返回给mis
				}
			}
		}
		selectedRouteList = queryRuleJudgment(frList, query);
		if (selectedRouteList == null || selectedRouteList.size() == 0) {// 没有查询到路由返回错误
			busGuideRoute.setType(OperationMenu.ROUTE);
			busGuideRoute.setFlowRouteList(frList);
		}
		if (selectedRouteList.size() > 0) {
			busGuideRoute.setType(OperationMenu.ROUTE);
			busGuideRoute.setFlowRouteList(selectedRouteList);
		}
		return busGuideRoute;
	}

	private String getQueryRule(List<FlowRouteOVO> frList) {
		Set<String> keysList = new HashSet<String>();
		for (FlowRouteOVO fr : frList) {
			List<String> queryList = fr.getQueryList();
			if (queryList != null && queryList.size() > 0) {
				for (String queryStr : queryList) {
					keysList.addAll(getQueryParmKey(queryStr));
				}
			}
		}
		String param = "";
		for (String string : keysList) {
			param += string + ";";
		}
		if (param.endsWith(";")) {
			param = param.substring(0, param.length() - 1);
		}
		System.out.println("路由条件---" + param);
		return param;
	}

	private List<FlowRouteOVO> queryRuleJudgment(List<FlowRouteOVO> frList, String query) {
		List<FlowRouteOVO> queryRouteList = new ArrayList<FlowRouteOVO>();
		Map<String, String> queryMap = new HashMap<>();
		if (StrUtil.notEmpty(query)) {
			queryMap = parseQuery2Map(query);
		}
		Set<String> routeIdSet = new HashSet<String>();
		
		for (FlowRouteOVO fr : frList) {
			List<String> queryList = fr.getQueryList();
			if (queryList != null && queryList.size() > 0) {
				System.out.println("路由fr: "+fr.getId()+"--"+queryList.toString());
				for (String queryParm : queryList) {// 进行单个条件完全匹配的校验
					int count = 0;
					List<String> keys = getQueryParmKey(queryParm);// 要匹配的字段(完全匹配的字段)
					for (int i = 0; i < keys.size(); i++) {
						String key = keys.get(i);
						String value = getQueryParmValue(queryParm, key);// 流程中的字段值（‘A,S’或者‘B’或者‘!B’）
						String queryValue = queryMap.get(key);// 业务所对应的值
						String[] values = value.split(",");
						if (queryMap.containsKey(key) && StrUtil.notEmpty(queryValue)) {
							for (int j = 0; j < values.length; j++) {
								String s = values[j];
								if (s.equals(queryValue)) {// 匹配上
									count++;
									break;
								}
							}
						} else {// 没有key，匹配下一个queryParm
							break;
						}
					}
					if (count == keys.size()) {// 完全匹配
						if(!routeIdSet.contains(fr.getId())) {
							routeIdSet.add(fr.getId());
							queryRouteList.add(fr);
						}
					}
				}
			}
			continue;
		}
		return queryRouteList;
	}

	private Map<String, String> parseQuery2Map(String query) {
		Map<String, String> map = new HashMap<>();
		Set<String> keySet = new HashSet<>();
		if (StrUtil.notEmpty(query)) {
//			String[] Strquery = query.split(",");
//			for (int i = 0; i < Strquery.length; i++) {
				String[] strs = query.split(";");
				for (String s : strs) {
					String[] kv = s.split(":");
					if (kv.length == 2) {
						if (!keySet.contains(kv[0])) {
							keySet.add(kv[0]);
						} else {
							String kv1 = map.get(kv[0]).toString();
							kv[1] = kv1 + "," + kv[1];
						}
						map.put(kv[0], kv[1]);
					}
				}
//			}
		}
		return map;
	}

	@Override
	public FlowRouteOVO getFlowRoute(String routeId) {
		return flowRouteService.getRouteInfo(routeId);
	}

	@Override
	public BusGuideRoute getFlowDealModelList(FlowRouteOVO flowRouteOVo, String instanceId, String userId,String flowCode) {
		BusGuideRoute busGuideRoute = new BusGuideRoute();
		String routeDealType = flowRouteOVo.getRouteDealType();
		String routeDealId = flowRouteOVo.getRouteDealId();
		List<FlowUserModel> list = new ArrayList<FlowUserModel>();// 路由处理人
		if (StrUtil.notEmpty(routeDealType)) {
			switch (routeDealType) {
			case "21":// 本部门
//				boolean flag = false;
				List<FlowUserModel> ulist = flowUserServiceReflection.getFlowExternalService().deptUserByUser(userId);
				if (ulist != null && ulist.size() > 0) {
					list.addAll(ulist);
				}
//				for (FlowUserModel flowUserModel : ulist) {
//					String deptId = flowUserModel.getDeptId();
//					if ("30169926".equals(deptId)) {
//						flag = true;
//						break;
//					}
//				}
//				if (flag) {
//					list.add(flowUserService.selectUserInfo("2831544335238090"));
//					busGuideRoute.setFlag(true);
//				} else {
//					if (ulist != null && ulist.size() > 0) {
//						list.addAll(ulist);
//					}
//				}
				break;
			case "22":// 大部门
//				if ("2831544335238090".equals(userId)) {// 谭宏斌
//					list.add(flowUserService.selectUserInfo("01295238232274"));// 李珂
//					busGuideRoute.setFlag(true);
//				} else {
//					List<FlowUserModel> auList = flowUserService.allDeptUserByUser(userId);
//					if (auList != null && auList.size() > 0) {
//						list.addAll(auList);
//					}
//				}
				List<FlowUserModel> auList = flowUserServiceReflection.getFlowExternalService()
						.allDeptUserByUser(userId);
				if (auList != null && auList.size() > 0) {
					list.addAll(auList);
				}
				break;
			case "11":// 发起人
				// 查询发起人信息
				FlowInstanceOVO flowInstanceOVo = flowInstenceEngine.getFlowInstanceOVO(instanceId);
				if (flowInstanceOVo != null) {
					FlowUserModel user = flowInstanceOVo.getUser();
					if (user != null) {
						list.add(user);
					}
				}
				busGuideRoute.setFlag(true);
				break;
			case "01":// 用户
				FlowUserModel user = flowUserServiceReflection.getFlowExternalService().selectUserInfo(routeDealId);
				list.add(user);
				break;
			case "02":// 部门
				List<FlowUserModel> dlist = flowUserServiceReflection.getFlowExternalService()
						.selectUserByDeptId(routeDealId);
				if (dlist != null && dlist.size() > 0) {
					list.addAll(dlist);
				}
				break;
			case "03":// 角色
				List<FlowUserModel> rlist = flowUserServiceReflection.getFlowExternalService()
						.selectUserByRoleId(routeDealId);
				if (rlist != null && rlist.size() > 0) {
					list.addAll(rlist);
				}
				break;
			case "12":// 上一节点办理人
//				FlowInstanceState flowInstanceState = new FlowInstanceState();
//				flowInstanceState.setNodeId(flowRouteOVo.getPreNode());
//				flowInstanceState.setSufDealId(userId);
//				flowInstanceState.setInstanceId(instanceId);
//				flowInstanceState.setSufAuthType("01");
//				FlowInstanceState fs = flowInstenceStateEngine.getInstenceStateNow(flowInstanceState);
//				if (fs != null) {
//					FlowUserModel r1list = flowUserServiceReflection.getFlowExternalService()
//							.selectUserInfo(fs.getPreDealId());
//					
//				}
				BusGuide busGuide = nodeDealRuleEngine.isBack("1", flowRouteOVo.getPreNode(), instanceId, userId, flowCode);
				list.addAll(busGuide.getFlowUserList());
				busGuideRoute.setFlag(true);
				break;
			}
		}
		busGuideRoute.setType(OperationMenu.USER);
		busGuideRoute.setRouteUserList(list);
		List<FlowRouteOVO> flowRouteList = new ArrayList<FlowRouteOVO>();
		flowRouteList.add(flowRouteOVo);
		busGuideRoute.setFlowRouteList(flowRouteList);
		return busGuideRoute;
	}

	private List<String> getQueryParmKey(String queryParm) {
		JSONObject jsonx = JSON.parseObject(queryParm);
		List<String> keys = new ArrayList<String>();
		for (String key : jsonx.keySet()) {
			String[] keyArray = key.split(",");
			for (String keyStr : keyArray) {
				keys.add(keyStr);
			}
		}
		return keys;
	}

	private String getQueryParmValue(String queryParm, String key) {
		HashMap<String,String> map = JSON.parseObject(queryParm, new TypeReference<HashMap<String,String>>() {});
		return map.get(key).toString();
	}

}
