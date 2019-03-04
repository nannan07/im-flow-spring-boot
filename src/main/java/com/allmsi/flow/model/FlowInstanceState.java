package com.allmsi.flow.model;

import java.util.Date;

import com.allmsi.flow.model.ivo.FlowInstanceStateIVO;

public class FlowInstanceState {

	private String id;

	private String stateId;

	private String instanceId;

	private String nodeId;

	private String routeId;

	private String preDealId;

	private String sufDealId;

	private String sufDealType;

	private String sufAuthType;

	private Date uTime;

	private String uUserId;

	private Integer isProxy;

	private String proxyId;

	private String opinion;

	// node
	private String nodeName;

	private String nodeType;

	// route
	private String routeName;

	private Integer isBack;

	private Integer isRecall;

	// flow
	private String flowId;

	private String flowCode;
	
	private String version;

	private String objectId;

	private String sufId;

	private String sufName;

	private String sufDeptId;

	private String sufDeptName;
	
	//
	private String code;

	public FlowInstanceState() {
	}

	public FlowInstanceState(FlowInstanceStateIVO flowInstanceStateIVo) {
		if (flowInstanceStateIVo != null) {
			this.id = flowInstanceStateIVo.getId();
			this.instanceId = flowInstanceStateIVo.getInstanceId();
			this.nodeId = flowInstanceStateIVo.getNodeId();
			this.routeId = flowInstanceStateIVo.getRouteId();
			this.preDealId = flowInstanceStateIVo.getPreDealId();
			this.sufDealId = flowInstanceStateIVo.getSufDealId();
			this.sufDealType = flowInstanceStateIVo.getSufDealType();
			this.sufAuthType = flowInstanceStateIVo.getSufAuthType();
			this.uUserId = flowInstanceStateIVo.getuUserId();
			this.isProxy = flowInstanceStateIVo.getIsProxy();
			this.isRecall = flowInstanceStateIVo.getIsRecall();
		}
	}

	public FlowInstanceState(String id, String instanceId, String nodeId, String routeId, String preDealId,
			String sufDealId, String sufDealType, String sufAuthType, String uUserId, Integer isProxy, String proxyId) {
		this.id = id;
		this.instanceId = instanceId;
		this.nodeId = nodeId;
		this.routeId = routeId;
		this.preDealId = preDealId;
		this.sufDealId = sufDealId;
		this.sufDealType = sufDealType;
		this.sufAuthType = sufAuthType;
		this.uUserId = uUserId;
		this.isProxy = isProxy;
		this.proxyId = proxyId;
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

	public Date getuTime() {
		return uTime;
	}

	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}

	public String getuUserId() {
		return uUserId;
	}

	public void setuUserId(String uUserId) {
		this.uUserId = uUserId == null ? null : uUserId.trim();
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getSufId() {
		return sufId;
	}

	public void setSufId(String sufId) {
		this.sufId = sufId;
	}

	public String getSufName() {
		return sufName;
	}

	public void setSufName(String sufName) {
		this.sufName = sufName;
	}

	public String getSufDeptId() {
		return sufDeptId;
	}

	public void setSufDeptId(String sufDeptId) {
		this.sufDeptId = sufDeptId;
	}

	public String getSufDeptName() {
		return sufDeptName;
	}

	public void setSufDeptName(String sufDeptName) {
		this.sufDeptName = sufDeptName;
	}

	public Integer getIsBack() {
		return isBack;
	}

	public void setIsBack(Integer isBack) {
		this.isBack = isBack;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Integer getIsProxy() {
		return isProxy;
	}

	public void setIsProxy(Integer isProxy) {
		this.isProxy = isProxy;
	}

	public String getProxyId() {
		return proxyId;
	}

	public void setProxyId(String proxyId) {
		this.proxyId = proxyId;
	}

	public Integer getIsRecall() {
		return isRecall;
	}

	public void setIsRecall(Integer isRecall) {
		this.isRecall = isRecall;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "FlowInstanceState [id=" + id + ", stateId=" + stateId + ", instanceId=" + instanceId + ", nodeId="
				+ nodeId + ", routeId=" + routeId + ", preDealId=" + preDealId + ", sufDealId=" + sufDealId
				+ ", sufDealType=" + sufDealType + ", sufAuthType=" + sufAuthType + ", uTime=" + uTime + ", uUserId="
				+ uUserId + ", isProxy=" + isProxy + ", proxyId=" + proxyId + ", opinion=" + opinion + ", nodeName="
				+ nodeName + ", nodeType=" + nodeType + ", routeName=" + routeName + ", isBack=" + isBack
				+ ", isRecall=" + isRecall + ", flowId=" + flowId + ", flowCode=" + flowCode + ", objectId=" + objectId
				+ ", sufId=" + sufId + ", sufName=" + sufName + ", sufDeptId=" + sufDeptId + ", sufDeptName="
				+ sufDeptName + "]";
	}

}