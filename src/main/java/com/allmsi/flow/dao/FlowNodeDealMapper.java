package com.allmsi.flow.dao;

import java.util.List;

import com.allmsi.flow.model.FlowNodeDeal;

public interface FlowNodeDealMapper {
	
    List<FlowNodeDeal> listNodeDeals(String nodeId);

}