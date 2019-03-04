package com.allmsi.flow.model;

import java.util.List;

import com.allmsi.flow.model.external.FlowUserModel;

public class NodeDealUser {

	private String nodeId;

	private boolean selfProcessing;

	private String processingParam;

	private List<FlowUserModel> list;

	public NodeDealUser(String nodeId, boolean selfProcessing, String processingParam, List<FlowUserModel> list) {
		this.nodeId = nodeId;
		this.selfProcessing = selfProcessing;
		this.processingParam = processingParam;
		this.list = list;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public boolean isSelfProcessing() {
		return selfProcessing;
	}

	public void setSelfProcessing(boolean selfProcessing) {
		this.selfProcessing = selfProcessing;
	}

	public String getProcessingParam() {
		return processingParam;
	}

	public void setProcessingParam(String processingParam) {
		this.processingParam = processingParam;
	}

	public List<FlowUserModel> getList() {
		return list;
	}

	public void setList(List<FlowUserModel> list) {
		this.list = list;
	}

}
