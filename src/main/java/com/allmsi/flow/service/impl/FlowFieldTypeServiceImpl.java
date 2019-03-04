package com.allmsi.flow.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowFieldTypeMapper;
import com.allmsi.flow.model.FlowFieldType;
import com.allmsi.flow.service.FlowFieldTypeService;

@Service
public class FlowFieldTypeServiceImpl implements FlowFieldTypeService {

	@Resource
	private FlowFieldTypeMapper FlowFieldTypeDao;

	@Override
	public FlowFieldType getFlowFieldType(String id) {
		FlowFieldType flowFieldType = FlowFieldTypeDao.getFlowFieldType(id);
		if (flowFieldType == null) {
			return null;
		}
		return flowFieldType;
	}

	@Override
	public boolean deleteByPrimaryKey(String id) {
		int flag = FlowFieldTypeDao.deleteByPrimaryKey(id);
		return (flag == 0) ? false : true;
	}

}
