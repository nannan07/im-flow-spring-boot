package com.allmsi.flow.model.ovo;

import java.util.List;

import com.allmsi.flow.model.external.FlowUserModel;

public class BusGuideNode {

	private String type;

	private String msg;

	private String nodeId;
	
	private boolean selfProcessing;
	
	private String processingParam;

	private List<FlowUserModel> flowUserList;

	private List<FlowUserModel> flowReadList;

	private List<FlowRouteOVO> flowRouteList;

	public BusGuideNode() {
	}

	public BusGuideNode(String type, String msg,List<FlowUserModel> flowUserList,
			List<FlowUserModel> flowReadList, List<FlowRouteOVO> flowRouteList) {
		this.type = type;
		this.msg = msg;
		this.flowUserList = flowUserList;
		this.flowReadList = flowReadList;
		this.flowRouteList = flowRouteList;
	}

	public BusGuideNode(String type, String msg, List<FlowUserModel> flowUserList, List<FlowRouteOVO> flowRouteList) {
		this.type = type;
		this.msg = msg;
		this.flowUserList = flowUserList;
		this.flowRouteList = flowRouteList;
	}

	public BusGuideNode(String type, String msg, String nodeId) {
		this.type = type;
		this.msg = msg;
		this.nodeId = nodeId;
	}

	public BusGuideNode(String type, String msg, String nodeId, List<FlowUserModel> flowUserList) {
		this.type = type;
		this.msg = msg;
		this.nodeId = nodeId;
		this.flowUserList = flowUserList;
	}

	public BusGuideNode(String type, String msg, String nodeId, boolean selfProcessing,String processingParam, List<FlowUserModel> flowUserList,
			List<FlowUserModel> flowReadList, List<FlowRouteOVO> flowRouteList) {
		this.type = type;
		this.msg = msg;
		this.nodeId = nodeId;
		this.selfProcessing = selfProcessing;
		this.processingParam = processingParam;
		this.flowUserList = flowUserList;
		this.flowReadList = flowReadList;
		this.flowRouteList = flowRouteList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<FlowUserModel> getFlowUserList() {
		return flowUserList;
	}

	public void setFlowUserList(List<FlowUserModel> flowUserList) {
		this.flowUserList = flowUserList;
	}

	public List<FlowUserModel> getFlowReadList() {
		return flowReadList;
	}

	public void setFlowReadList(List<FlowUserModel> flowReadList) {
		this.flowReadList = flowReadList;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public List<FlowRouteOVO> getFlowRouteList() {
		return flowRouteList;
	}

	public void setFlowRouteList(List<FlowRouteOVO> flowRouteList) {
		this.flowRouteList = flowRouteList;
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
}
