package com.allmsi.flow.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.FlowMySelfView;

public interface FlowMySelfViewMapper {

	List<FlowMySelfView> selectMySelfList(Map<String, String> map);

}
