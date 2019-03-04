package com.allmsi.flow.model.ovo;

import java.util.Date;
import java.util.List;

import com.allmsi.flow.model.FlowInstanceState;

public class FlowInstenceCurrencyOVO {

	private String id;

	private String stateId;

	private String instanceId;

	private String preNodeId;

	private String sufNodeId;

	private String routeId;

	private String preDealId;

	private String sufDealId;

	private String sufDealType;

	private String sufAuthType;

	private String sufName;

	private String sufDeptId;

	private String sufDeptName;

	private Date uTime;

	private String uUserId;

	// node
	private String preNodeName;

	private String preNodeType;

	// route
	private String routeName;

	private Integer isBack;

	private Integer isRecall;

	// flow
	private String flowId;

	private String flowCode;

	private String objectId;

	private String sufDealName;

	private String remark;

	private String opinion;

	private String dealState;

	//
	private String code;

	// button
	private List<FlowNodeButtonSimpleOVO> nodeButtonList;
	// field
	private List<FlowNodeFieldOVO> nodeFieldList;

	public FlowInstenceCurrencyOVO() {
	}

	public FlowInstenceCurrencyOVO(FlowInstanceState flowInstanceState) {
		if (flowInstanceState != null) {
			this.id = flowInstanceState.getId();
			this.stateId = flowInstanceState.getStateId();
			this.instanceId = flowInstanceState.getInstanceId();
			this.preNodeId = flowInstanceState.getNodeId();
			this.routeId = flowInstanceState.getRouteId();
			this.preDealId = flowInstanceState.getPreDealId();
			this.sufDealId = flowInstanceState.getSufDealId();
			this.sufDealType = flowInstanceState.getSufDealType();
			this.sufAuthType = flowInstanceState.getSufAuthType();

			this.sufDealName = flowInstanceState.getSufName();
			this.sufDeptId = flowInstanceState.getSufDeptId();
			this.sufDeptName = flowInstanceState.getSufDeptName();

			this.uTime = flowInstanceState.getuTime();
			this.uUserId = flowInstanceState.getuUserId();
			this.opinion = flowInstanceState.getOpinion();

			this.preNodeName = flowInstanceState.getNodeName();
			this.preNodeType = flowInstanceState.getNodeType();
			this.routeName = flowInstanceState.getRouteName();
			this.isBack = flowInstanceState.getIsBack();
			this.isRecall = flowInstanceState.getIsRecall();

			this.flowId = flowInstanceState.getFlowId();
			this.flowCode = flowInstanceState.getFlowCode();
			this.objectId = flowInstanceState.getObjectId();
			this.code = flowInstanceState.getCode();
		}
	}

	public FlowInstenceCurrencyOVO(FlowInstenceStateDoneInfoOVO flowInfo) {
		if (flowInfo != null) {
			this.id = flowInfo.getId();
			this.instanceId = flowInfo.getInstanceId();
			this.sufNodeId = flowInfo.getNodeId();
			this.sufDealId = flowInfo.getSufId();
			this.routeId = flowInfo.getRouteId();
			this.preNodeName = flowInfo.getNodeName();
			this.preNodeType = flowInfo.getNodeType();
			this.routeName = flowInfo.getRouteName();
			this.flowCode = flowInfo.getFlowCode();
			this.objectId = flowInfo.getObjectId();
			this.sufDealName = flowInfo.getSufName();
			this.uTime = flowInfo.getuTime();
			this.isRecall = flowInfo.getIsRecall();
		}
	}

	public FlowInstenceCurrencyOVO(String id, String flowId, String instanceId, String preNodeId, String sufNodeId,
			String routeId, String opinion, String dealState, String objectId) {
		this.id = id;
		this.instanceId = instanceId;
		this.preNodeId = preNodeId;
		this.sufNodeId = sufNodeId;
		this.routeId = routeId;
		this.flowId = flowId;
		this.objectId = objectId;
		this.opinion = opinion;
		this.dealState = dealState;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getPreNodeId() {
		return preNodeId;
	}

	public void setPreNodeId(String preNodeId) {
		this.preNodeId = preNodeId;
	}

	public String getSufNodeId() {
		return sufNodeId;
	}

	public void setSufNodeId(String sufNodeId) {
		this.sufNodeId = sufNodeId;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getPreDealId() {
		return preDealId;
	}

	public void setPreDealId(String preDealId) {
		this.preDealId = preDealId;
	}

	public String getSufDealId() {
		return sufDealId;
	}

	public void setSufDealId(String sufDealId) {
		this.sufDealId = sufDealId;
	}

	public String getSufDealType() {
		return sufDealType;
	}

	public void setSufDealType(String sufDealType) {
		this.sufDealType = sufDealType;
	}

	public String getSufAuthType() {
		return sufAuthType;
	}

	public void setSufAuthType(String sufAuthType) {
		this.sufAuthType = sufAuthType;
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
		this.uUserId = uUserId;
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

	public List<FlowNodeButtonSimpleOVO> getNodeButtonList() {
		return nodeButtonList;
	}

	public void setNodeButtonList(List<FlowNodeButtonSimpleOVO> nodeButtonList) {
		this.nodeButtonList = nodeButtonList;
	}

	public Integer getIsBack() {
		return isBack;
	}

	public void setIsBack(Integer isBack) {
		this.isBack = isBack;
	}

	public String getSufDealName() {
		return sufDealName;
	}

	public void setSufDealName(String sufDealName) {
		this.sufDealName = sufDealName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Integer getIsRecall() {
		return isRecall;
	}

	public void setIsRecall(Integer isRecall) {
		this.isRecall = isRecall;
	}

	public List<FlowNodeFieldOVO> getNodeFieldList() {
		return nodeFieldList;
	}

	public void setNodeFieldList(List<FlowNodeFieldOVO> nodeFieldList) {
		this.nodeFieldList = nodeFieldList;
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

	@Override
	public String toString() {
		return "FlowInstenceCurrencyOVo [id=" + id + ", stateId=" + stateId + ", instanceId=" + instanceId
				+ ", preNodeId=" + preNodeId + ", sufNodeId=" + sufNodeId + ", routeId=" + routeId + ", preDealId="
				+ preDealId + ", sufDealId=" + sufDealId + ", sufDealType=" + sufDealType + ", sufAuthType="
				+ sufAuthType + ", sufName=" + sufName + ", sufDeptId=" + sufDeptId + ", sufDeptName=" + sufDeptName
				+ ", uTime=" + uTime + ", uUserId=" + uUserId + ", preNodeName=" + preNodeName + ", preNodeType="
				+ preNodeType + ", routeName=" + routeName + ", isBack=" + isBack + ", isRecall=" + isRecall
				+ ", flowId=" + flowId + ", flowCode=" + flowCode + ", objectId=" + objectId + ", sufDealName="
				+ sufDealName + ", remark=" + remark + ", opinion=" + opinion + ", dealState=" + dealState
				+ ", nodeButtonList=" + nodeButtonList + ", nodeFieldList=" + nodeFieldList + "]";
	}

}
