package com.allmsi.flow.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowNodeMapper;
import com.allmsi.flow.model.FlowNode;
import com.allmsi.flow.model.ovo.FlowNodeOVO;
import com.allmsi.flow.service.FlowNodeService;
import com.allmsi.sys.util.StrUtil;

@Service
public class FlowNodeServiceImpl implements FlowNodeService {

	@Resource
	private FlowNodeMapper flowNodeDao;

	@Override
	public FlowNodeOVO getTheNextNodeToStart(String flowCode) {
		if (StrUtil.isEmpty(flowCode)) {
			return null;
		}
		FlowNode flowNode = flowNodeDao.selectTheNextOneToStart(flowCode);
		if (flowNode != null) {
			return new FlowNodeOVO(flowNode);
		}
		return null;
	}

	@Override
	public boolean isfinish(String nodeId) {
		int count = flowNodeDao.isfinish(nodeId);
		return (count == 0) ? false : true;
	}

	@Override
	public FlowNodeOVO getFlowNodeOVO(String nodeId) {
		FlowNode flowNode = flowNodeDao.selectByPrimaryKey(nodeId);
		if (flowNode == null) {
			return null;
		}
		return new FlowNodeOVO(flowNode);
	}
}
