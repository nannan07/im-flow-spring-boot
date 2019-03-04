package com.allmsi.flow.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.FlowReadDeal;

public interface FlowReadDealMapper {

	List<FlowReadDeal> listByTabIdAndTab(Map<String, String> map);

}
