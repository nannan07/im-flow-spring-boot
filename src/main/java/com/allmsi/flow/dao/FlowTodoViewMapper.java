package com.allmsi.flow.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.FlowTodoView;

public interface FlowTodoViewMapper {

	List<FlowTodoView> selectTodoList(Map<String, String> map);

	List<FlowTodoView> flowInstenceDept(Map<String, Object> map);

}
