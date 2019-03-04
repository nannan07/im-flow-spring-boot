package com.allmsi.flow.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowFieldPropertyMapper;
import com.allmsi.flow.model.FlowFieldProperty;
import com.allmsi.flow.service.FlowFieldPropertyService;

@Service
public class FlowFieldPropertyServiceImpl implements FlowFieldPropertyService {

	@Resource
	private FlowFieldPropertyMapper FlowFieldPropertyDao;

	@Override
	public FlowFieldProperty getFlowFieldProperty(String id) {
		FlowFieldProperty flowFieldProperty = FlowFieldPropertyDao.getFlowFieldProperty(id);
		if (flowFieldProperty == null) {
			return null;
		}
		return flowFieldProperty;
	}

	@Override
	public boolean deleteByPrimaryKey(String id) {
		int flag = FlowFieldPropertyDao.deleteByPrimaryKey(id);
		return (flag == 0) ? false : true;
	}

}
