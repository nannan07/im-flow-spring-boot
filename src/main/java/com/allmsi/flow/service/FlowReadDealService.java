package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.ovo.FlowReadDealOVO;

public interface FlowReadDealService {

	List<FlowReadDealOVO> listFlowReadDealOVo(String tab, String tabId);
}
