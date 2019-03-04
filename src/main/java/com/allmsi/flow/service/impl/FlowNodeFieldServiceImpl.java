package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowNodeFieldMapper;
import com.allmsi.flow.model.FlowField;
import com.allmsi.flow.model.ovo.FlowNodeFieldOVO;
import com.allmsi.flow.reflection.FlowUserServiceReflection;
import com.allmsi.flow.service.FlowFieldService;
import com.allmsi.flow.service.FlowNodeFieldAuthService;
import com.allmsi.flow.service.FlowNodeFieldService;

@Service
public class FlowNodeFieldServiceImpl implements FlowNodeFieldService {
	@Resource
	private FlowNodeFieldMapper flowNodeFieldDao;

	@Resource
	private FlowFieldService flowFieldService;

	@Resource
	private FlowNodeFieldAuthService flowNodeFieldAuthService;

	@Resource
	private FlowUserServiceReflection flowUserServiceReflection;

	@Override
	public List<FlowNodeFieldOVO> listNodeField(String userId, String nodeId) {
		List<String> userList = flowUserServiceReflection.getFlowExternalService().getUserMap(userId);
		List<FlowNodeFieldOVO> list = new ArrayList<FlowNodeFieldOVO>();
		List<FlowField> fieldList = flowFieldService.listFlowField(nodeId, userList);
		if (fieldList != null && fieldList.size() > 0) {
			for (FlowField flowField : fieldList) {
				list.add(new FlowNodeFieldOVO(flowField));
			}
		}
		return list;
	}

	@Override
	public boolean deleteByPrimaryKey(String id) {
		int msg = flowNodeFieldDao.deleteByPrimaryKey(id);
		return (msg == 0) ? false : true;
	}

}
