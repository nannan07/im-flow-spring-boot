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
import com.allmsi.flow.model.OperationMenu;
import com.allmsi.flow.model.external.FlowBeforNodeMapKey;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ovo.FlowInstanceOVO;
import com.allmsi.flow.model.ovo.FlowReadDealOVO;
import com.allmsi.flow.reflection.FlowBeforeNodeReflection;
import com.allmsi.flow.reflection.FlowNodeReadUserRegisterQueryReflection;
import com.allmsi.flow.reflection.FlowUserServiceReflection;
import com.allmsi.flow.service.FlowInstenceEngine;
import com.allmsi.flow.service.FlowReadDealService;
import com.allmsi.flow.service.ReadDealRuleEngine;
import com.allmsi.sys.util.StrUtil;

@Service
public class ReadDealRuleEngineImpl implements ReadDealRuleEngine {

	@Resource
	private FlowReadDealService flowReadDealService;

	@Resource
	private FlowUserServiceReflection flowUserServiceReflection;

	@Resource
	private FlowInstenceEngine flowInstenceEngine;

	@Resource
	private FlowBeforeNodeReflection flowBeforeNodeReflection;

	@Resource
	private FlowNodeReadUserRegisterQueryReflection flowNodeReadUserRegisterQueryReflection;

	@SuppressWarnings("unchecked")
	public List<FlowUserModel> getReadDealUser(String nodeId, String instanceId) {
		List<FlowUserModel> readlist = new ArrayList<FlowUserModel>();// 节点待阅人员
		if (StrUtil.notEmpty(nodeId)) {
			List<FlowReadDealOVO> rfdList = flowReadDealService.listFlowReadDealOVo(OperationMenu.TAB_NODE, nodeId);
			List<FlowReadDealOVO> selectedRouteList = new ArrayList<FlowReadDealOVO>();
			String strQuery = getQueryRule(rfdList);// 需匹配的字段
			if (StrUtil.notEmpty(strQuery)) {
				FlowInstanceOVO fi = flowInstenceEngine.getFlowInstanceOVO(instanceId);
				if (fi == null) {
					return null;
				}
				System.out.println("流程节点阅读条件："+strQuery);
				String moduleQuery = flowNodeReadUserRegisterQueryReflection.getFlowNodeReadUserService()
						.getReadQurey(strQuery, fi.getObjectId(), fi.getFlowCode());
				selectedRouteList = queryRuleJudgment(rfdList, moduleQuery);
			} else {
				selectedRouteList = rfdList;
			}
			Set<String> userids = new HashSet<String>();
			for (FlowReadDealOVO flowReadDealOVo : selectedRouteList) {
				String readId = flowReadDealOVo.getReadId();
				String readType = flowReadDealOVo.getReadType();
				if (StrUtil.notEmpty(readType)) {
					switch (readType) {
					case "01":// 查询用户
						FlowUserModel u = flowUserServiceReflection.getFlowExternalService().selectUserInfo(readId);
						if (u != null) {
							userids.add(u.getUserId());
							readlist.add(u);
						}
						break;
					case "02":// 查询部门
						List<FlowUserModel> dl = flowUserServiceReflection.getFlowExternalService()
								.selectUserByDeptId(readId);
						if (dl != null && dl.size() > 0) {
							for (FlowUserModel flowUserModel : dl) {
								if (!userids.contains(flowUserModel.getUserId())) {
									userids.add(flowUserModel.getUserId());
									readlist.add(flowUserModel);
								}
							}
						}
						break;
					case "03":// 查询角色
						List<FlowUserModel> rl = flowUserServiceReflection.getFlowExternalService()
								.selectUserByRoleId(readId);
						if (rl != null && rl.size() > 0) {
							for (FlowUserModel flowUserModel : rl) {
								if (!userids.contains(flowUserModel.getUserId())) {
									userids.add(flowUserModel.getUserId());
									readlist.add(flowUserModel);
								}
							}
						}
						break;
					case "11":// 发起人
						// 查询发起人信息
						FlowInstanceOVO flowInstanceOVo = flowInstenceEngine.getFlowInstanceOVO(instanceId);
						if (flowInstanceOVo != null) {
							FlowUserModel user = flowInstanceOVo.getUser();
							if (user != null) {
								if (!userids.contains(user.getUserId())) {
									userids.add(user.getUserId());
									readlist.add(user);
								}
							}
						}
						break;
					case "99":// 额外人员
						// 查询发起人信息
						FlowInstanceOVO flowInstanceOVo1 = flowInstenceEngine.getFlowInstanceOVO(instanceId);
						if (flowInstanceOVo1 != null) {
							Map<String, Object> map = flowBeforeNodeReflection.getFlowBeforeNodeReflection().flowBeforeNode(FlowBeforNodeMapKey.TYPE_NODE, nodeId,
									flowInstanceOVo1.getFlowCode(),flowInstanceOVo1.getObjectId());
							String key=FlowBeforNodeMapKey.TYPE_NODE_KEY+nodeId;
							if(map != null && map.get(key) != null) {
								List<FlowUserModel> list = (List<FlowUserModel>) map.get(key);
								if (list != null && list.size() > 0) {
									for (FlowUserModel flowUserModel : list) {
										if (!userids.contains(flowUserModel.getUserId())) {
											userids.add(flowUserModel.getUserId());
											readlist.add(flowUserModel);
										}
									}
								}
							}
							
						}
						break;
					}
				}
			}
		}
		return readlist;
	}

	private List<FlowReadDealOVO> queryRuleJudgment(List<FlowReadDealOVO> rfdList, String query) {
		List<FlowReadDealOVO> queryReadList = new ArrayList<FlowReadDealOVO>();
		Map<String, String> queryMap = new HashMap<>();
		if (StrUtil.notEmpty(query)) {
			queryMap = parseQuery2Map(query);
		}
		for (FlowReadDealOVO fr : rfdList) {
			List<String> queryList = fr.getQueryList();
			if (queryList != null && queryList.size() > 0) {
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
								if (s.equals(queryValue) || queryValue.contains(s)) {// 匹配上
									count++;
								}
							}
						} else {// 没有key，匹配下一个queryParm
							break;
						}
					}
					if (count == keys.size()) {// 完全匹配
						queryReadList.add(fr);
					}
				}
			} else {
				queryReadList.add(fr);
			}
			continue;
		}
		return queryReadList;
	}

	private String getQueryParmValue(String queryParm, String key) {
		JSONObject jsonx = JSON.parseObject(queryParm);
		return jsonx.get(key).toString();
	}

	private Map<String, String> parseQuery2Map(String query) {
		Map<String, String> map = new HashMap<>();
		Set<String> keySet = new HashSet<>();
		if (StrUtil.notEmpty(query)) {
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
		}
		return map;
	}

	private String getQueryRule(List<FlowReadDealOVO> rfdList) {
		Set<String> keySet = new HashSet<>();
		for (FlowReadDealOVO fr : rfdList) {
			List<String> queryList = fr.getQueryList();
			if (queryList != null && queryList.size() > 0) {
				for (String queryStr : queryList) {
					List<String> keysList = getQueryParmKey(queryStr);
					for (String string : keysList) {
						if (!keySet.contains(string)) {
							keySet.add(string);
						}
					}
				}

			}
		}
		String param = "";
		for (String string : keySet) {
			param += string + ";";
		}
		if (param.endsWith(";")) {
			param = param.substring(0, param.length() - 1);
		}
		return param;
	}

	private List<String> getQueryParmKey(String queryParm) {
		JSONObject jsonx = JSON.parseObject(queryParm);
		List<String> keys = new ArrayList<>();
		for (String key : jsonx.keySet()) {
			String[] keyArray = key.split(",");
			for (String keyStr : keyArray) {
				keys.add(keyStr);
			}
		}
		return keys;
	}
}
