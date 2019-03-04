package com.allmsi.flow.model;

public class FlowNodeDeal {
	private String id;

	private String nodeId;

	private String nodeDealId;

	private String nodeDealType;

	private Boolean del;

	private String routeId;

	private String nodeType;
	
	public FlowNodeDeal() {

	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId == null ? null : nodeId.trim();
	}

	public String getNodeDealId() {
		return nodeDealId;
	}

	public void setNodeDealId(String nodeDealId) {
		this.nodeDealId = nodeDealId == null ? null : nodeDealId.trim();
	}

	public String getNodeDealType() {
		return nodeDealType;
	}

	public void setNodeDealType(String nodeDealType) {
		this.nodeDealType = nodeDealType == null ? null : nodeDealType.trim();
	}

	public Boolean getDel() {
		return del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
}