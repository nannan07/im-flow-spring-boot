package com.allmsi.flow.service;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.FlowInstanceDeal;
import com.allmsi.flow.model.ovo.FlowInstanceDealOVO;

public interface FlowInstanceDealService {

	FlowInstanceDealOVO getPreDealUserForNodeId(Map<String, String> map);

	List<FlowInstanceDealOVO> listPreDealUsersForNodeId(Map<String, String> map);
	
	FlowInstanceDeal getFlowInstanceDealByState(String stateId);
	
	boolean insertBatch(List<FlowInstanceDeal> list);
	
	int insertSelective(FlowInstanceDeal flowInstanceDeal);
}
