package com.allmsi.flow.model;

public class FlowRoute {
	private String id;

	private String flowId;

	private String flowCode;

	private String routeName;

	private String preNode;

	private String sufNode;

	private Integer isBack;

	private Integer isRecall;

	private Boolean del;

	private String preNodeName;

	private String preNodeType;

	private String sufNodeName;

	private String sufNodeType;

	// 路由处理
	private String routeDealType;

	private String routeDealId;

	private String dealId;

	public FlowRoute() {

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

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName == null ? null : routeName.trim();
	}

	public String getPreNode() {
		return preNode;
	}

	public void setPreNode(String preNode) {
		this.preNode = preNode == null ? null : preNode.trim();
	}

	public String getSufNode() {
		return sufNode;
	}

	public void setSufNode(String sufNode) {
		this.sufNode = sufNode == null ? null : sufNode.trim();
	}

	public Integer getIsBack() {
		return isBack;
	}

	public void setIsBack(Integer isBack) {
		this.isBack = isBack;
	}

	public Boolean getDel() {
		return del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	public String getPreNodeName() {
		return preNodeName;
	}

	public void setPreNodeName(String preNodeName) {
		this.preNodeName = preNodeName;
	}

	public String getPreNodeType() {
		return preNodeType;
	}

	public void setPreNodeType(String preNodeType) {
		this.preNodeType = preNodeType;
	}

	public String getSufNodeName() {
		return sufNodeName;
	}

	public void setSufNodeName(String sufNodeName) {
		this.sufNodeName = sufNodeName;
	}

	public String getSufNodeType() {
		return sufNodeType;
	}

	public void setSufNodeType(String sufNodeType) {
		this.sufNodeType = sufNodeType;
	}

	public String getRouteDealType() {
		return routeDealType;
	}

	public void setRouteDealType(String routeDealType) {
		this.routeDealType = routeDealType;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

	public String getRouteDealId() {
		return routeDealId;
	}

	public void setRouteDealId(String routeDealId) {
		this.routeDealId = routeDealId;
	}

	public Integer getIsRecall() {
		return isRecall;
	}

	public void setIsRecall(Integer isRecall) {
		this.isRecall = isRecall;
	}

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

}