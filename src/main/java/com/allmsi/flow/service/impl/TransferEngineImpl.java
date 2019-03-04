package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.model.TransferForever;
import com.allmsi.flow.model.TransferInterim;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ivo.NodeDealIVO;
import com.allmsi.flow.reflection.FlowUserServiceReflection;
import com.allmsi.flow.service.TransferEngine;
import com.allmsi.flow.service.TransferForeverService;
import com.allmsi.flow.service.TransferInterimService;
import com.allmsi.sys.util.StrUtil;

@Service
public class TransferEngineImpl implements TransferEngine {

	@Resource
	private TransferForeverService transferForeveServicer;

	@Resource
	private TransferInterimService transferInterimService;

	@Resource
	private FlowUserServiceReflection flowUserServiceReflection;

	/**
	 * 获取代理人
	 */
	@Override
	public List<NodeDealIVO> selectTransferUser(String flowCode, String nodeId, String principal) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("principal", principal);
		map.put("flowCode", flowCode);
		List<String> agents = new ArrayList<String>();
		// 查询权限永久转移
		List<TransferForever> foreveAgents = transferForeveServicer.listAgentsForPrincipal(map);
		for (TransferForever transferForever : foreveAgents) {
			String tNodeId = transferForever.getNodeId();
			String agent = transferForever.getAgent();
			if (StrUtil.isEmpty(tNodeId) || (StrUtil.notEmpty(tNodeId) && nodeId.equals(tNodeId))) {// flowCode的全部
				if (!agents.contains(agent)) {
					agents.add(agent);
				}
			}
		}
		// 查询临时权限转移
		map.put("today", new Date());
		List<TransferInterim> interimAgents = transferInterimService.listAgentsForPrincipal(map);
		for (TransferInterim transferInterim : interimAgents) {
			String tNodeId = transferInterim.getNodeId();
			String agent = transferInterim.getAgent();
			Integer isRetain = transferInterim.getIsRetain();
			if (StrUtil.isEmpty(tNodeId) || (StrUtil.notEmpty(tNodeId) && nodeId.equals(tNodeId))) {// flowCode的全部
				if (!agents.contains(agent)) {
					agents.add(agent);
				}
			}
			if (isRetain != null && 1 == isRetain) {// 保留权限
				if (!agents.contains(principal)) {
					agents.add(principal);
				}
			}
		}

		if ((foreveAgents == null || foreveAgents.size() == 0)
				&& (interimAgents == null || interimAgents.size() == 0)) {
			agents.add(principal);
		}

		List<NodeDealIVO> list = new ArrayList<NodeDealIVO>();
		if (agents != null && agents.size() > 0) {
			Map<String, FlowUserModel> map1 = flowUserServiceReflection.getFlowExternalService()
					.getFlowUserInfoList(agents);
			if(map1 != null) {
				for (String key : map1.keySet()) {
					FlowUserModel index = map1.get(key);
					if (index != null) {
					NodeDealIVO nd = new NodeDealIVO();
						nd.setDealId(index.getUserId());
						nd.setDealName(index.getName());
						nd.setDeptId(index.getDeptId());
						nd.setDeptName(index.getDeptName());
						if (!principal.equals(nd.getDealId())) {// 授权人员
							nd.setIsProxy(1);
							nd.setProxyId(principal);
						}
						list.add(nd);
					}
				}
			}
		}
		return list;
	}

}
