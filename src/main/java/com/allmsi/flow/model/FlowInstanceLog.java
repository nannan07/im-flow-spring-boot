package com.allmsi.flow.model;

import java.util.Date;

import com.allmsi.flow.model.ivo.FlowInstanceLogIVO;
import com.allmsi.flow.model.ivo.FlowInstanceStateIVO;

public class FlowInstanceLog {
	private String id;

	private String instanceId;

	private String nodeId;

	private String routeId;

	private String preDealId;

	private String sufDealId;

	private String sufDealType;

	private String sufAuthType;

	private String remark;

	private String dealState;

	private Date cTime;

	private String cUserId;

	private Date dealTime;

	private String nodeName;
	
	private String nodeType;

	private String flowCode;

	private String sufDealDeptId;

	private String sufDealDeptName;

	private String sufDealName;

	private String objId;

	private String objName;

	private String opinion;

	private Integer isProxy;

	private String proxyId;

	public FlowInstanceLog() {
	}

	public FlowInstanceLog(FlowInstanceLogIVO flowInstanceLogIVo) {
		if (flowInstanceLogIVo != null) {
			this.instanceId = flowInstanceLogIVo.getInstanceId();
			this.nodeId = flowInstanceLogIVo.getNodeId();
			this.routeId = flowInstanceLogIVo.getRouteId();
			this.preDealId = flowInstanceLogIVo.getPreDealId();
			this.sufDealId = flowInstanceLogIVo.getSufDealId();
			this.sufAuthType = flowInstanceLogIVo.getSufAuthType();
			this.sufDealType = flowInstanceLogIVo.getSufDealType();
			this.cUserId = flowInstanceLogIVo.getcUserId();
		}
	}

	public FlowInstanceLog(FlowInstanceStateIVO flowInstanceStateIVo) {
		this.instanceId = flowInstanceStateIVo.getInstanceId();
		this.nodeId = flowInstanceStateIVo.getNodeId();
		this.routeId = flowInstanceStateIVo.getRouteId();
		this.preDealId = flowInstanceStateIVo.getPreDealId();
		this.sufDealId = flowInstanceStateIVo.getSufDealId();
		this.sufAuthType = flowInstanceStateIVo.getSufAuthType();
		this.sufDealType = flowInstanceStateIVo.getSufDealType();
		this.cUserId = flowInstanceStateIVo.getuUserId();
	}

	public FlowInstanceLog(FlowInstanceState fs) {
		if (fs != null) {
			this.id = fs.getId();
			this.instanceId = fs.getInstanceId();
			this.nodeId = fs.getNodeId();
			this.routeId = fs.getRouteId();
			this.preDealId = fs.getPreDealId();
			this.sufDealId = fs.getSufDealId();
			this.sufAuthType = fs.getSufAuthType();
			this.sufDealType = fs.getSufDealType();
			this.cUserId = fs.getSufDealId();
			this.cTime = fs.getuTime();
			this.isProxy = fs.getIsProxy();
			this.proxyId = fs.getProxyId();
			
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public String getcUserId() {
		return cUserId;
	}

	public void setcUserId(String cUserId) {
		this.cUserId = cUserId == null ? null : cUserId.trim();
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

	public String getSufDealDeptId() {
		return sufDealDeptId;
	}

	public void setSufDealDeptId(String sufDealDeptId) {
		this.sufDealDeptId = sufDealDeptId;
	}

	public String getSufDealDeptName() {
		return sufDealDeptName;
	}

	public void setSufDealDeptName(String sufDealDeptName) {
		this.sufDealDeptName = sufDealDeptName;
	}

	public String getSufDealName() {
		return sufDealName;
	}

	public void setSufDealName(String sufDealName) {
		this.sufDealName = sufDealName;
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

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getDealState() {
		return dealState;
	}

	public void setDealState(String dealState) {
		this.dealState = dealState;
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

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
}