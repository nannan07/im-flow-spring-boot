package com.allmsi.flow.model;

import java.util.Date;

import com.allmsi.flow.model.ivo.FlowInstanceDealIVO;

public class FlowInstanceDeal {
	private String id;

	private String stateId;

	private String preId;

	private String preName;

	private String preDeptId;

	private String preDeptName;

	private String sufId;

	private String sufName;

	private String sufDeptId;

	private String sufDeptName;

	// 回退时的返回参数
	private String flowId;

	// 路由处理
	private String preRouteId;
	private String preRouteName;
	private String routeDealType;
	private String query;
	private Integer preRouteIsBack;

	private String preNodeId;
	private String preNodeName;
	private String preNodeType;

	private String sufNodeId;
	private String sufNodeName;
	private String sufNodeType;

	private Date uTime;

	public FlowInstanceDeal() {

	}

	public FlowInstanceDeal(FlowInstanceDealIVO flowInstanceDealIVo) {
		if (flowInstanceDealIVo != null) {
			this.id = flowInstanceDealIVo.getId();
			this.stateId = flowInstanceDealIVo.getStateId();
			this.preId = flowInstanceDealIVo.getPreId();
			this.preName = flowInstanceDealIVo.getPreName();
			this.preDeptId = flowInstanceDealIVo.getPreDeptId();
			this.preDeptName = flowInstanceDealIVo.getPreDeptName();
			this.sufId = flowInstanceDealIVo.getSufId();
			this.sufName = flowInstanceDealIVo.getSufName();
			this.sufDeptId = flowInstanceDealIVo.getSufDeptId();
			this.sufDeptName = flowInstanceDealIVo.getSufDeptName();
		}
	}

	

	public FlowInstanceDeal(String id, String stateId, String preId, String preName, String preDeptId,
			String preDeptName, String sufId, String sufName, String sufDeptId, String sufDeptName) {
		this.id = id;
		this.stateId = stateId;
		this.preId = preId;
		this.preName = preName;
		this.preDeptId = preDeptId;
		this.preDeptName = preDeptName;
		this.sufId = sufId;
		this.sufName = sufName;
		this.sufDeptId = sufDeptId;
		this.sufDeptName = sufDeptName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getPreId() {
		return preId;
	}

	public void setPreId(String preId) {
		this.preId = preId;
	}

	public String getPreName() {
		return preName;
	}

	public void setPreName(String preName) {
		this.preName = preName;
	}

	public String getPreDeptId() {
		return preDeptId;
	}

	public void setPreDeptId(String preDeptId) {
		this.preDeptId = preDeptId;
	}

	public String getPreDeptName() {
		return preDeptName;
	}

	public void setPreDeptName(String preDeptName) {
		this.preDeptName = preDeptName;
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

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getPreRouteId() {
		return preRouteId;
	}

	public void setPreRouteId(String preRouteId) {
		this.preRouteId = preRouteId;
	}

	public String getPreRouteName() {
		return preRouteName;
	}

	public void setPreRouteName(String preRouteName) {
		this.preRouteName = preRouteName;
	}

	public String getPreNodeId() {
		return preNodeId;
	}

	public void setPreNodeId(String preNodeId) {
		this.preNodeId = preNodeId;
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

	public Integer getPreRouteIsBack() {
		return preRouteIsBack;
	}

	public void setPreRouteIsBack(Integer preRouteIsBack) {
		this.preRouteIsBack = preRouteIsBack;
	}

	public String getRouteDealType() {
		return routeDealType;
	}

	public void setRouteDealType(String routeDealType) {
		this.routeDealType = routeDealType;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getSufNodeId() {
		return sufNodeId;
	}

	public void setSufNodeId(String sufNodeId) {
		this.sufNodeId = sufNodeId;
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

	public Date getuTime() {
		return uTime;
	}

	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}

}