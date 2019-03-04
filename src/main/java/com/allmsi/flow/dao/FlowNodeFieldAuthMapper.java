package com.allmsi.flow.dao;

import java.util.List;

import com.allmsi.flow.model.FlowNodeFieldAuth;

public interface FlowNodeFieldAuthMapper {
	
    int deleteByPrimaryKey(String id);

    FlowNodeFieldAuth selectByPrimaryKey(String id);
    
    List<FlowNodeFieldAuth> listNodeFieldAuth(String id);

}