package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowInstanceLogMapper;
import com.allmsi.flow.model.FlowInstanceLog;
import com.allmsi.flow.model.ivo.FlowInstanceLogIVO;
import com.allmsi.flow.service.FlowInstanceLogService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class FlowInstanceLogServiceImpl implements FlowInstanceLogService {

	@Resource
	private FlowInstanceLogMapper flowInstanceLogDao;

	@Override
	public String insertFlowInstanceLog(FlowInstanceLogIVO flowInstanceLogIVo) {
		if (flowInstanceLogIVo == null || StrUtil.isEmpty(flowInstanceLogIVo.getInstanceId())) {
			return null;
		}
		FlowInstanceLog flowInstanceLog = new FlowInstanceLog(flowInstanceLogIVo);
		String id = UUIDUtil.getUUID();
		flowInstanceLog.setId(id);
		return (flowInstanceLogDao.insertSelective(flowInstanceLog) == 0) ? null : id;
	}

	@Override
	public String isFinsh(String objId) {
		if (StrUtil.isEmpty(objId)) {
			return "";
		}
		int count = flowInstanceLogDao.isFinsh(objId);
		return (count == 0) ? "" : objId;
	}

	@Override
	public List<String> doneFlow(List<String> objIds) {
		List<String> list = new ArrayList<String>();
		if (objIds != null && objIds.size() > 0) {
			List<FlowInstanceLog> filList = flowInstanceLogDao.doneFlow(objIds);// 未完结的项目
			for (FlowInstanceLog flowInstanceLog : filList) {
				String objId = flowInstanceLog.getObjId();
				list.add(objId);
			}
			return list;
		}
		return null;
	}
}
