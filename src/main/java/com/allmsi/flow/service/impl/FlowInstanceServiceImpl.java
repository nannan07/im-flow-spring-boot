package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowInstanceMapper;
import com.allmsi.flow.model.FlowInstance;
import com.allmsi.flow.model.ivo.FlowInstanceIVO;
import com.allmsi.flow.service.FlowInstanceService;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class FlowInstanceServiceImpl implements FlowInstanceService {

	@Resource
	private FlowInstanceMapper flowInstanceDao;

	@Override
	public List<String> listInstanceByObjId(String objId) {
		List<String> list = new ArrayList<String>();
		List<FlowInstance> fiList = flowInstanceDao.listInstanceByObjId(objId);
		for (FlowInstance flowInstance : fiList) {
			list.add(flowInstance.getId());
		}
		return list;
	}

	@Override
	public String insertFlowInstance(FlowInstanceIVO flowInstanceIVo) {
		FlowInstance fi = new FlowInstance(flowInstanceIVo);
		String id = UUIDUtil.getUUID();
		fi.setId(id);
		int msg = flowInstanceDao.insertSelective(fi);// 添加流程实例
		return (msg == 0) ? "" : id;
	}

	@Override
	public String updateFlowInstanceDratf(String instanceId) {
		FlowInstance flowInstance = flowInstanceDao.selectByPrimaryKey(instanceId);
		Integer draft = flowInstance.getDraft();
		if (0 == draft) {// 修改
			int msg = flowInstanceDao.updateDraft(instanceId);
			return (msg == 0) ? "" : instanceId;
		}
		return instanceId;
	}
}
