package com.allmsi.flow.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowInstanceOpinionMapper;
import com.allmsi.flow.model.FlowInstanceOpinion;
import com.allmsi.flow.service.FlowOpinionEngine;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class FlowOpinionEngineImpl implements FlowOpinionEngine {

	@Resource
	private FlowInstanceOpinionMapper opinionDao;

	public String insertOpinion(String userId, String opinion, String instanceLogId) {
		if (StrUtil.isEmpty(instanceLogId)) {
			return "";
		}
		FlowInstanceOpinion op = opinionDao.getByInstanceLogId(instanceLogId);
		if (op != null) {
			opinionDao.deleteByPrimaryKey(op.getId());
		}
		FlowInstanceOpinion record = new FlowInstanceOpinion(UUIDUtil.getUUID(), instanceLogId, opinion, userId);
		int count = opinionDao.insertSelective(record);
		return (count == 0) ? "" : instanceLogId;
	}
}
