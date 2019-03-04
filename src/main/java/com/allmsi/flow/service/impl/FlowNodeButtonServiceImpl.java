package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowNodeButtonMapper;
import com.allmsi.flow.model.FlowNodeButton;
import com.allmsi.flow.model.ovo.FlowNodeButtonSimpleOVO;
import com.allmsi.flow.service.FlowNodeButtonService;

@Service
public class FlowNodeButtonServiceImpl implements FlowNodeButtonService {

	@Resource
	private FlowNodeButtonMapper flowNodeButtonDao;

	@Override
	public List<FlowNodeButtonSimpleOVO> listNodeButtons(String nodeId) {
		List<FlowNodeButtonSimpleOVO> flowNodeButtonVList = new ArrayList<FlowNodeButtonSimpleOVO>();
		List<FlowNodeButton> flowNodeButtonPList = flowNodeButtonDao.listFlowNodeButton(nodeId);
		for (FlowNodeButton flowNodeButton : flowNodeButtonPList) {
			flowNodeButtonVList.add(new FlowNodeButtonSimpleOVO(flowNodeButton.getButtonName(),
					flowNodeButton.getButtonType(), flowNodeButton.getSort()));
		}
		return flowNodeButtonVList;
	}

}
