package com.allmsi.flow.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.allmsi.flow.model.FlowInstanceDeal;
@Mapper
public interface FlowInstanceDealMapper {

    int insertSelective(FlowInstanceDeal record);

	int insertBatch(List<FlowInstanceDeal> list);

	FlowInstanceDeal getPreDealUserForNodeId(Map<String, String> map);

	List<FlowInstanceDeal> listPreDealUsersForNodeId(Map<String, String> map);

	int deleteByInstenceId(String instenceId);

	FlowInstanceDeal getFlowInstanceDealByState(String stateId);

}