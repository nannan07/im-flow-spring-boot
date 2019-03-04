package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowNodeReadDealQueryMapper;
import com.allmsi.flow.service.FlowNodeReadDealQueryService;

@Service
public class FlowNodeReadDealQueryServiceImpl implements FlowNodeReadDealQueryService {

	@Resource
	private FlowNodeReadDealQueryMapper flowNodeReadDealQueryDao;

	@Override
	public List<String> listQuery(String id) {
		List<String> queryList = flowNodeReadDealQueryDao.queryList(id);
		List<String> list=new ArrayList<>();
		if (queryList != null && queryList.size() > 0) {
			for(String queryStr:queryList) {
				list.add(queryStr.replace("\"",""));
			}
		}
		return list;
	}
}
