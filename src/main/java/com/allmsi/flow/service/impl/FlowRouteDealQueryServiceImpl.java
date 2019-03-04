package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowRouteDealQueryMapper;
import com.allmsi.flow.service.FlowRouteDealQueryService;

@Service
public class FlowRouteDealQueryServiceImpl implements FlowRouteDealQueryService {

	@Resource
	private FlowRouteDealQueryMapper flowRouteDealQueryDao;

	@Override
	public List<String> queryList(String id) {
		List<String> queryList = flowRouteDealQueryDao.queryList(id);
		List<String> list = new ArrayList<>();
		if (queryList != null && queryList.size() > 0) {
			for (String queryStr : queryList) {
				list.add(queryStr.replace("\"", ""));
			}
		} 
		return list;
	}
}
