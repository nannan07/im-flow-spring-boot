package com.allmsi.flow.model;

import java.util.Date;

public class FlowMySelfView {

	private String id;

	private String instanceId;

	private String flowCode;

	private String objId;

	private String objName;

	private String handlerId;

	private String handlerName;

	private String handlerDeptId;

	private String handlerDept;

	private String objValue1;

	private String objValue2;

	private String objValue3;

	private String objValue4;

	private String objValue5;

	private String code;

	private String auditorId;

	private String auditorName;

	private String sufDeptId;

	private String sufDeptName;

	private String nodeId;

	private String nodeName;

	private Date createTime;// 发送时间

	private String cUserId;

	private String version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getcUserId() {
		return cUserId;
	}

	public void setcUserId(String cUserId) {
		this.cUserId = cUserId;
	}

	public String getObjValue1() {
		return objValue1;
	}

	public void setObjValue1(String objValue1) {
		this.objValue1 = objValue1;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getHandlerId() {
		return handlerId;
	}

	public void setHandlerId(String handlerId) {
		this.handlerId = handlerId;
	}

	public String getHandlerName() {
		return handlerName;
	}

	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}

	public String getHandlerDeptId() {
		return handlerDeptId;
	}

	public void setHandlerDeptId(String handlerDeptId) {
		this.handlerDeptId = handlerDeptId;
	}

	public String getHandlerDept() {
		return handlerDept;
	}

	public void setHandlerDept(String handlerDept) {
		this.handlerDept = handlerDept;
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

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "FlowMySelfView [id=" + id + ", instanceId=" + instanceId + ", flowCode=" + flowCode + ", objId=" + objId
				+ ", objName=" + objName + ", handlerId=" + handlerId + ", handlerName=" + handlerName
				+ ", handlerDeptId=" + handlerDeptId + ", handlerDept=" + handlerDept + ", objValue1=" + objValue1
				+ ", objValue2=" + objValue2 + ", objValue3=" + objValue3 + ", objValue4=" + objValue4 + ", objValue5="
				+ objValue5 + ", code=" + code + ", auditorId=" + auditorId + ", auditorName=" + auditorName
				+ ", sufDeptId=" + sufDeptId + ", sufDeptName=" + sufDeptName + ", nodeId=" + nodeId + ", nodeName="
				+ nodeName + ", createTime=" + createTime + ", cUserId=" + cUserId + ", version=" + version + "]";
	}
	

}
