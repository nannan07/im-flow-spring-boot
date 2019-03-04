package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowInstanceDealMapper;
import com.allmsi.flow.model.FlowInstanceDeal;
import com.allmsi.flow.model.ovo.FlowInstanceDealOVO;
import com.allmsi.flow.service.FlowInstanceDealService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class FlowInstanceDealServiceImpl implements FlowInstanceDealService {

	@Resource
	private FlowInstanceDealMapper flowInstanceDealDao;

	@Override
	public FlowInstanceDealOVO getPreDealUserForNodeId(Map<String, String> map) {
		FlowInstanceDeal flowInstanceDeal = flowInstanceDealDao.getPreDealUserForNodeId(map);
		if (flowInstanceDeal != null) {
			return new FlowInstanceDealOVO(flowInstanceDeal);
		}
		return null;
	}

	@Override
	public List<FlowInstanceDealOVO> listPreDealUsersForNodeId(Map<String, String> map) {
		List<FlowInstanceDealOVO> list = new ArrayList<FlowInstanceDealOVO>();
		List<FlowInstanceDeal> flowInstanceDeal = flowInstanceDealDao.listPreDealUsersForNodeId(map);
		for (FlowInstanceDeal flowInstanceDeal2 : flowInstanceDeal) {
			list.add(new FlowInstanceDealOVO(flowInstanceDeal2));
		}
		return list;
	}

	@Override
	public FlowInstanceDeal getFlowInstanceDealByState(String stateId) {
		if (StrUtil.isEmpty(stateId)) {
			return null;
		}
		return flowInstanceDealDao.getFlowInstanceDealByState(stateId);
	}

	@Override
	public boolean insertBatch(List<FlowInstanceDeal> list) {
		int msg = 0;
		if (list != null && list.size() > 0) {
			msg = flowInstanceDealDao.insertBatch(list);
		}
		return (msg == 0) ? false : true;
	}

	@Override
	public int insertSelective(FlowInstanceDeal flowInstanceDeal) {
		if(flowInstanceDeal == null) {
			return 0;
		}
		if(StrUtil.isEmpty(flowInstanceDeal.getId())) {
			flowInstanceDeal.setId(UUIDUtil.getUUID());
		}
		return flowInstanceDealDao.insertSelective(flowInstanceDeal);
	}
}
