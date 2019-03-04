package com.allmsi.flow.model.ovo;

import com.allmsi.flow.model.FlowNode;
import com.allmsi.flow.model.external.FlowUserModel;

public class FlowNodeOVO {
	private String id;

	private String flowId;

	private String nodeName;

	private String nodeType;

	private String continuation;
	
	private FlowUserModel user;

	public FlowNodeOVO() {

	}

	public FlowNodeOVO(FlowNode flowNode) {
		if (flowNode != null) {
			this.id = flowNode.getId();
			this.flowId = flowNode.getFlowId();
			this.nodeName = flowNode.getNodeName();
			this.nodeType = flowNode.getNodeType();
			this.continuation = flowNode.getContinuation();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId == null ? null : flowId.trim();
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName == null ? null : nodeName.trim();
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType == null ? null : nodeType.trim();
	}

	public FlowUserModel getUser() {
		return user;
	}

	public void setUser(FlowUserModel user) {
		this.user = user;
	}

	public String getContinuation() {
		return continuation;
	}

	public void setContinuation(String continuation) {
		this.continuation = continuation;
	}
}