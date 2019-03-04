package com.allmsi.flow.model.ovo;

import java.util.List;

import com.allmsi.flow.model.FlowRoute;
import com.allmsi.flow.model.external.FlowUserModel;

public class FlowRouteOVO {

	private String id;

	private String flowId;

	private String routeName;

	private String preNode;

	private String sufNode;

	private Integer isBack;

	private String preNodeName;

	private String preNodeType;

	private String sufNodeName;

	private String sufNodeType;

	private FlowUserModel user;

	// 路由处理
	private String routeDealType;

	private String routeDealId;

	private List<String> queryList;

	private Integer isRecall;

	private String dealId;

	public FlowRouteOVO() {

	}

	public FlowRouteOVO(FlowRoute flowRoute) {
		if (flowRoute != null) {
			this.id = flowRoute.getId();
			this.routeName = flowRoute.getRouteName();
			this.flowId = flowRoute.getFlowId();
			this.preNode = flowRoute.getPreNode();
			this.sufNode = flowRoute.getSufNode();
			this.isBack = flowRoute.getIsBack();
			this.preNodeName = flowRoute.getPreNodeName();
			this.preNodeType = flowRoute.getPreNodeType();
			this.sufNodeName = flowRoute.getSufNodeName();
			this.sufNodeType = flowRoute.getSufNodeType();
			this.routeDealType = flowRoute.getRouteDealType();
			this.routeDealId = flowRoute.getRouteDealId();
			this.isRecall = flowRoute.getIsRecall();
			this.dealId=flowRoute.getDealId();
		}
	}

	public FlowRouteOVO(FlowInstanceDealOVO flowInstanceDealOVo) {
		if (flowInstanceDealOVo != null) {
			this.id = flowInstanceDealOVo.getPreRouteId();
			this.routeName = flowInstanceDealOVo.getPreRouteName();
			this.flowId = flowInstanceDealOVo.getFlowId();
			this.isBack = flowInstanceDealOVo.getPreRouteIsBack();
			this.routeDealType = flowInstanceDealOVo.getRouteDealType();

			this.sufNode = flowInstanceDealOVo.getSufNodeId();
			this.sufNodeName = flowInstanceDealOVo.getSufNodeName();
			this.sufNodeType = flowInstanceDealOVo.getSufNodeType();

			this.preNode = flowInstanceDealOVo.getPreNodeId();
			this.preNodeName = flowInstanceDealOVo.getPreNodeName();
			this.preNodeType = flowInstanceDealOVo.getPreNodeType();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public Integer getIsBack() {
		return isBack;
	}

	public void setIsBack(Integer isBack) {
		this.isBack = isBack;
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

	public String getPreNode() {
		return preNode;
	}

	public void setPreNode(String preNode) {
		this.preNode = preNode;
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

	public FlowUserModel getUser() {
		return user;
	}

	public void setUser(FlowUserModel user) {
		this.user = user;
	}

	public String getSufNode() {
		return sufNode;
	}

	public void setSufNode(String sufNode) {
		this.sufNode = sufNode;
	}

	public String getRouteDealType() {
		return routeDealType;
	}

	public void setRouteDealType(String routeDealType) {
		this.routeDealType = routeDealType;
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

	public List<String> getQueryList() {
		return queryList;
	}

	public void setQueryList(List<String> queryList) {
		this.queryList = queryList;
	}

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	@Override
	public String toString() {
		return "FlowRouteOVo [id=" + id + ", flowId=" + flowId + ", routeName=" + routeName + ", preNode=" + preNode
				+ ", sufNode=" + sufNode + ", isBack=" + isBack + ", preNodeName=" + preNodeName + ", preNodeType="
				+ preNodeType + ", sufNodeName=" + sufNodeName + ", sufNodeType=" + sufNodeType + ", routeDealType="
				+ routeDealType + ", routeDealId=" + routeDealId + ", queryList=" + queryList + ", isRecall=" + isRecall
				+ ", dealId=" + dealId + "]";
	}

}
