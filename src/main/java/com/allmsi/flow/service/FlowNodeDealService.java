package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ovo.FlowNodeDealOVO;

public interface FlowNodeDealService {

	List<FlowNodeDealOVO> listFlowNodeDeal(String nodeId);

	List<FlowUserModel> listNodeUsers(String userId,String nodeId);

}
