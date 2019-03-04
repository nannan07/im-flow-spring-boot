package com.allmsi.flow.model.ivo;

import com.allmsi.flow.model.FlowInstanceState;

public class FlowInstanceStateIVO {
	private String id;

	private String instanceId;

	private String nodeId;

	private String routeId;

	private String preDealId;

	private String sufDealId;

	private String sufDealType;

	private String sufAuthType;

	private String uUserId;
	
	private Integer isProxy;

	private String remark;
	
	private Integer isRecall;
	
	public FlowInstanceStateIVO(String id, String instanceId, String nodeId, String routeId, String preDealId,
			String sufDealId, String sufDealType, String sufAuthType, String uUserId) {
		this.id = id;
		this.instanceId = instanceId;
		this.nodeId = nodeId;
		this.routeId = routeId;
		this.preDealId = preDealId;
		this.sufDealId = sufDealId;
		this.sufDealType = sufDealType;
		this.sufAuthType = sufAuthType;
		this.uUserId = uUserId;
	}

	public FlowInstanceStateIVO(String id, String instanceId, String nodeId, String routeId, String preDealId,
			String sufDealId, String sufDealType, String sufAuthType, String uUserId, String remark) {
		this.id = id;
		this.instanceId = instanceId;
		this.nodeId = nodeId;
		this.routeId = routeId;
		this.preDealId = preDealId;
		this.sufDealId = sufDealId;
		this.sufDealType = sufDealType;
		this.sufAuthType = sufAuthType;
		this.uUserId = uUserId;
		this.remark = remark;
	}

	public FlowInstanceStateIVO() {
	}

	public FlowInstanceStateIVO(FlowInstanceState flowInstanceState) {
		if (flowInstanceState != null) {	
			
			this.id=flowInstanceState.getId();
			this.instanceId=flowInstanceState.getInstanceId();
			this.isProxy=flowInstanceState.getIsProxy();
			this.isRecall=flowInstanceState.getIsRecall();
			this.nodeId=flowInstanceState.getNodeId();
			this.preDealId=	flowInstanceState.getPreDealId();
			this.routeId=flowInstanceState.getRouteId();
			this.sufAuthType=flowInstanceState.getSufAuthType();
			this.sufDealId=flowInstanceState.getSufDealId();
			this.sufDealType=flowInstanceState.getSufDealType();
			this.uUserId=flowInstanceState.getuUserId();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId == null ? null : instanceId.trim();
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId == null ? null : nodeId.trim();
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId == null ? null : routeId.trim();
	}

	public String getPreDealId() {
		return preDealId;
	}

	public void setPreDealId(String preDealId) {
		this.preDealId = preDealId == null ? null : preDealId.trim();
	}

	public String getSufDealId() {
		return sufDealId;
	}

	public void setSufDealId(String sufDealId) {
		this.sufDealId = sufDealId == null ? null : sufDealId.trim();
	}

	public String getSufDealType() {
		return sufDealType;
	}

	public void setSufDealType(String sufDealType) {
		this.sufDealType = sufDealType == null ? null : sufDealType.trim();
	}

	public String getSufAuthType() {
		return sufAuthType;
	}

	public void setSufAuthType(String sufAuthType) {
		this.sufAuthType = sufAuthType == null ? null : sufAuthType.trim();
	}

	public String getuUserId() {
		return uUserId;
	}

	public void setuUserId(String uUserId) {
		this.uUserId = uUserId == null ? null : uUserId.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsProxy() {
		return isProxy;
	}

	public void setIsProxy(Integer isProxy) {
		this.isProxy = isProxy;
	}

	public Integer getIsRecall() {
		return isRecall;
	}

	public void setIsRecall(Integer isRecall) {
		this.isRecall = isRecall;
	}

	
	
}