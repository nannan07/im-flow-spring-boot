package com.allmsi.flow.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.TransferInterimMapper;
import com.allmsi.flow.model.TransferInterim;
import com.allmsi.flow.model.ivo.TransferInterimIVO;
import com.allmsi.flow.service.TransferInterimService;
import com.allmsi.sys.util.StrUtil;

@Service
public class TransferInterimServiceImpl implements TransferInterimService {

	@Resource
	private TransferInterimMapper transferInterimDao;

	@Override
	public boolean insertSelective(TransferInterimIVO record) {
		int count = 0;
		if (record != null) {
			count = transferInterimDao.insertSelective(new TransferInterim(record));
		}
		return (count == 0) ? false : true;
	}

	@Override
	public List<TransferInterim> listAgentsForPrincipal(Map<String, Object> map) {
		return transferInterimDao.listByPrincipal(map);
	}

	@Override
	public boolean deleteById(String id, String userId) {
		if(StrUtil.isEmpty(id) || StrUtil.isEmpty(userId)) {
			return false;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("uUserId", userId);
		int count = transferInterimDao.deleteByParemerKey(map);
		return (count == 0) ? false : true;
	}

}
