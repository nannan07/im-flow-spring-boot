package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowNodeDealMapper;
import com.allmsi.flow.model.FlowNodeDeal;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ovo.FlowNodeDealOVO;
import com.allmsi.flow.reflection.FlowUserServiceReflection;
import com.allmsi.flow.service.FlowNodeDealService;
import com.allmsi.sys.util.StrUtil;

@Service
public class FlowNodeDealServiceImpl implements FlowNodeDealService {

	@Resource
	private FlowNodeDealMapper flowNodeDealDao;

	@Resource
	private FlowUserServiceReflection flowUserServiceReflection;

	@Override
	public List<FlowNodeDealOVO> listFlowNodeDeal(String nodeId) {
		List<FlowNodeDealOVO> list = new ArrayList<FlowNodeDealOVO>();
		List<FlowNodeDeal> fndList = flowNodeDealDao.listNodeDeals(nodeId);
		for (FlowNodeDeal flowNodeDeal : fndList) {
			list.add(new FlowNodeDealOVO(flowNodeDeal));
		}
		return list;
	}

	@Override
	public List<FlowUserModel> listNodeUsers(String userId,String nodeId) {
		List<FlowUserModel> list = new ArrayList<FlowUserModel>();
		if (StrUtil.isEmpty(nodeId)) {
			return null;
		}
		List<FlowNodeDeal> fndList = flowNodeDealDao.listNodeDeals(nodeId);
		for (FlowNodeDeal flowNodeDeal : fndList) {
			String nodeType = flowNodeDeal.getNodeDealType();
			String dealId = flowNodeDeal.getNodeDealId();
			if (StrUtil.notEmpty(nodeType)) {
				switch (nodeType) {
				case "01":
					list.add(flowUserServiceReflection.getFlowExternalService().selectUserInfo(dealId));
					break;
				case "02":
					list.addAll(flowUserServiceReflection.getFlowExternalService().selectUserByDeptId(dealId));
					break;
				case "03":
					list.addAll(flowUserServiceReflection.getFlowExternalService().selectUserByRoleId(dealId));
					break;
				default:
					break;
				}
			}
		}
		return list;
	}

}
