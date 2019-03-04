package com.allmsi.flow.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowNodeFieldAuthMapper;
import com.allmsi.flow.model.FlowNodeFieldAuth;
import com.allmsi.flow.service.FlowNodeFieldAuthService;

@Service
public class FlowNodeFieldAuthServiceImpl implements FlowNodeFieldAuthService {

	@Resource
	private FlowNodeFieldAuthMapper FlowNodeFieldAuthDao;

	@Override
	public List<FlowNodeFieldAuth> listNodeFieldAuth(String id) {
		List<FlowNodeFieldAuth> nodeFieldAuthList = FlowNodeFieldAuthDao.listNodeFieldAuth(id);
		return nodeFieldAuthList;
	}

	@Override
	public boolean deleteByPrimaryKey(String id) {
		int flag = FlowNodeFieldAuthDao.deleteByPrimaryKey(id);
		return (flag == 0) ? false : true;
	}

}
