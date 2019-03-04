package com.allmsi.flow.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowFieldMapper;
import com.allmsi.flow.model.FlowField;
import com.allmsi.flow.model.FlowFieldProperty;
import com.allmsi.flow.model.FlowFieldType;
import com.allmsi.flow.model.ovo.FlowFieldOVO;
import com.allmsi.flow.service.FlowFieldPropertyService;
import com.allmsi.flow.service.FlowFieldService;
import com.allmsi.flow.service.FlowFieldTypeService;

@Service
public class FlowFieldServiceImpl implements FlowFieldService {

	@Resource
	private FlowFieldMapper flowFieldDao;

	@Resource
	private FlowFieldTypeService flowFieldTypeService;

	@Resource
	private FlowFieldPropertyService flowFieldPropertyService;

	@Override
	public FlowFieldOVO getFlowFieldOVo(String id) {
		FlowField flowField = flowFieldDao.getFlowField(id);
		if (flowField != null) {
			FlowFieldOVO flowFieldOVo = new FlowFieldOVO(flowField);
			String fieldTypeId = flowFieldOVo.getFieldTypeId();
			String fieldPropertyId = flowFieldOVo.getFieldPropertyId();
			FlowFieldType flowFieldType = flowFieldTypeService.getFlowFieldType(fieldTypeId);
			FlowFieldProperty flowFieldProperty = flowFieldPropertyService.getFlowFieldProperty(fieldPropertyId);
			if (flowFieldType != null) {
				flowFieldOVo.setTypeName(flowFieldType.getTypeName());
			}
			if (flowFieldProperty != null) {
				flowFieldOVo.setPropertyName(flowFieldProperty.getPropertyName());
			}

			return flowFieldOVo;
		}
		return null;
	}

	@Override
	public boolean deleteByPrimaryKey(String id) {
		int flag = flowFieldDao.deleteByPrimaryKey(id);
		return (flag == 0) ? false : true;
	}

	@Override
	public List<FlowField> listFlowField(String nodeId, List<String> userList) {
		if (userList != null && userList.size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("nodeId", nodeId);
			map.put("authIdList", userList);
			return flowFieldDao.listFlowField(map);
		}
		return null;
	}

}
