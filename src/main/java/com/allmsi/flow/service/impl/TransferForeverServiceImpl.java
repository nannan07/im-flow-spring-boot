package com.allmsi.flow.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.TransferForeverMapper;
import com.allmsi.flow.model.TransferForever;
import com.allmsi.flow.model.ivo.TransferForeverIVO;
import com.allmsi.flow.service.TransferForeverService;

@Service
public class TransferForeverServiceImpl implements TransferForeverService {

	@Resource
	private TransferForeverMapper transferForeverDao;

	@Override
	public boolean insertSelective(TransferForeverIVO record) {
		int count = 0;
		if (record != null) {
			count = transferForeverDao.insertSelective(new TransferForever(record));
		}
		return (count == 0) ? false : true;
	}

	@Override
	public List<TransferForever> listAgentsForPrincipal(Map<String, Object> map) {
		return transferForeverDao.listPrincipal(map);
	}

	@Override
	public List<String> listtPrincipalByAgent(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
}
