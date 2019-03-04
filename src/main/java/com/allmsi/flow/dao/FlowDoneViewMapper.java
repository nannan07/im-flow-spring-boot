package com.allmsi.flow.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.FlowDoneView;

public interface FlowDoneViewMapper {

	List<FlowDoneView> selectDoneList(Map<String, String> map);

}
