package com.allmsi.flow.model;

import java.util.Date;

public class FlowDoneView {

	private String id;

	private String flowCode;

	private String instanceId;

	private String objId;

	private String objName;

	private Date dealTime;// 发送时间

	private String handlerName;// 创建人

	private String handlerDept;// 创建人部门

	private String nodeId;

	private String nodeName;

	private String auditorId;

	private String auditorName;

	private String objValue1;

	private String objValue2;

	private String objValue3;

	private String objValue4;

	private String objValue5;

	private String code;

	private String version;

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

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public String getHandlerName() {
		return handlerName;
	}

	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}

	public String getHandlerDept() {
		return handlerDept;
	}

	public void setHandlerDept(String handlerDept) {
		this.handlerDept = handlerDept;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(String auditorId) {
		this.auditorId = auditorId;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public String getObjValue1() {
		return objValue1;
	}

	public void setObjValue1(String objValue1) {
		this.objValue1 = objValue1;
	}

	public String getObjValue2() {
		return objValue2;
	}

	public void setObjValue2(String objValue2) {
		this.objValue2 = objValue2;
	}

	public String getObjValue3() {
		return objValue3;
	}

	public void setObjValue3(String objValue3) {
		this.objValue3 = objValue3;
	}

	public String getObjValue4() {
		return objValue4;
	}

	public void setObjValue4(String objValue4) {
		this.objValue4 = objValue4;
	}

	public String getObjValue5() {
		return objValue5;
	}

	public void setObjValue5(String objValue5) {
		this.objValue5 = objValue5;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "FlowDoneView [id=" + id + ", flowCode=" + flowCode + ", instanceId=" + instanceId + ", objId=" + objId
				+ ", objName=" + objName + ", dealTime=" + dealTime + ", handlerName=" + handlerName + ", handlerDept="
				+ handlerDept + ", nodeId=" + nodeId + ", nodeName=" + nodeName + ", auditorId=" + auditorId
				+ ", auditorName=" + auditorName + ", objValue1=" + objValue1 + ", objValue2=" + objValue2
				+ ", objValue3=" + objValue3 + ", objValue4=" + objValue4 + ", objValue5=" + objValue5 + ", code="
				+ code + ", version=" + version + "]";
	}

}
