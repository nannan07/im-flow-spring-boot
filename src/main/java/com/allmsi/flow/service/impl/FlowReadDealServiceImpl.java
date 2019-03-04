package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowReadDealMapper;
import com.allmsi.flow.model.FlowReadDeal;
import com.allmsi.flow.model.ovo.FlowReadDealOVO;
import com.allmsi.flow.service.FlowNodeReadDealQueryService;
import com.allmsi.flow.service.FlowReadDealService;

@Service
public class FlowReadDealServiceImpl implements FlowReadDealService {

	@Resource
	private FlowReadDealMapper flowReadDealDao;

	@Resource
	private FlowNodeReadDealQueryService flowNodeReadDealQueryService;

	@Override
	public List<FlowReadDealOVO> listFlowReadDealOVo(String tab, String tabId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("tab", tab);
		map.put("tabId", tabId);
		List<FlowReadDeal> rd = flowReadDealDao.listByTabIdAndTab(map);
		List<FlowReadDealOVO> list = new ArrayList<FlowReadDealOVO>();
		for (FlowReadDeal flowReadDeal : rd) {
			FlowReadDealOVO flowReadDealOVo = new FlowReadDealOVO(flowReadDeal);
			String id = flowReadDeal.getId();
			List<String> queryList = flowNodeReadDealQueryService.listQuery(id);
			if (queryList != null && queryList.size() > 0) {
				flowReadDealOVo.setQueryList(queryList);
			}
			list.add(flowReadDealOVo);
		}
		return list;
	}
}
