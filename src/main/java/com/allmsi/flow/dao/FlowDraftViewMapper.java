package com.allmsi.flow.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.FlowDraftView;

public interface FlowDraftViewMapper {

	List<FlowDraftView> selectFlowDraft(Map<String, String> map);

}
