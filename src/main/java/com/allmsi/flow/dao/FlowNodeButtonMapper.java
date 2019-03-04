package com.allmsi.flow.dao;

import java.util.List;

import com.allmsi.flow.model.FlowNodeButton;

public interface FlowNodeButtonMapper {

    List<FlowNodeButton> listFlowNodeButton(String nodeId);
}