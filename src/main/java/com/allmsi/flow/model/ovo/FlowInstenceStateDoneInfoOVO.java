package com.allmsi.flow.model.ovo;

import java.util.Date;

import com.allmsi.flow.model.FlowInstanceState;

public class FlowInstenceStateDoneInfoOVO {

	private String id;

	private String flowCode;

	private String instanceId;

	private String nodeId;

	private String routeId;

	// node
	private String nodeName;

	private String nodeType;

	// route
	private String routeName;

	private Integer isRecall;

	// flow

	private String objectId;

	private String sufId;

	private String sufName;

	private String sufDeptId;

	private String sufDeptName;

	private Date uTime;
	
	private String code;

	public FlowInstenceStateDoneInfoOVO() {
	}

	public FlowInstenceStateDoneInfoOVO(FlowInstanceState flowInstanceState) {
		if (flowInstanceState != null) {
			this.id=flowInstanceState.getId();
			this.flowCode = flowInstanceState.getFlowCode();
			this.instanceId = flowInstanceState.getInstanceId();
			this.objectId = flowInstanceState.getObjectId();
			this.nodeId = flowInstanceState.getNodeId();
			this.nodeName = flowInstanceState.getNodeName();
			this.nodeType = flowInstanceState.getNodeType();
			this.routeId = flowInstanceState.getRouteId();
			this.routeName = flowInstanceState.getRouteName();
			this.sufId = flowInstanceState.getSufId();
			this.sufName = flowInstanceState.getSufName();
			this.sufDeptId = flowInstanceState.getSufDeptId();
			this.sufDeptName = flowInstanceState.getSufDeptName();
			this.uTime = flowInstanceState.getuTime();
			this.isRecall = flowInstanceState.getIsRecall();
			this.code = flowInstanceState.getCode();
		}
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
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

	public Date getuTime() {
		return uTime;
	}

	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}

	public Integer getIsRecall() {
		return isRecall;
	}

	public void setIsRecall(Integer isRecall) {
		this.isRecall = isRecall;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
