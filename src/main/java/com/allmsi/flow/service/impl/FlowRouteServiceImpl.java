package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowRouteMapper;
import com.allmsi.flow.model.FlowRoute;
import com.allmsi.flow.model.ovo.FlowRouteOVO;
import com.allmsi.flow.service.FlowRouteService;
import com.allmsi.sys.util.StrUtil;

@Service
public class FlowRouteServiceImpl implements FlowRouteService {

	@Resource
	private FlowRouteMapper flowRouteDao;


	@Override
	public FlowRouteOVO getRouteInfo(String id) {
		if (StrUtil.isEmpty(id)) {
			return null;
		}
		FlowRoute flowRoute = flowRouteDao.selectByPrimaryKey(id);
		if (flowRoute != null) {
			FlowRouteOVO flowRouteOVo = new FlowRouteOVO(flowRoute);
			return flowRouteOVo;
		}
		return null;
	}

	@Override
	public List<FlowRouteOVO> listRouteListByPreNode(String flowCode, String preNode) {
		List<FlowRouteOVO> flowRouteVoList = new ArrayList<FlowRouteOVO>();
		if (StrUtil.isEmpty(flowCode) || StrUtil.isEmpty(preNode)) {
			return flowRouteVoList;
		}
		FlowRoute flowRoute = new FlowRoute();
		flowRoute.setFlowCode(flowCode);
		flowRoute.setPreNode(preNode);
		List<FlowRoute> flowRouteList = flowRouteDao.listRouteByPreNode(flowRoute);
		for (FlowRoute flowRoute2 : flowRouteList) {
			flowRouteVoList.add(new FlowRouteOVO(flowRoute2));
		}
		return flowRouteVoList;
	}

	@Override
	public FlowRouteOVO getStartNodeRoute(String flowId) {
		if (StrUtil.isEmpty(flowId)) {
			return null;
		}
		return new FlowRouteOVO(flowRouteDao.getStartNodeRoute(flowId));
	}
}
