package com.allmsi.flow.model.ovo;

import java.util.Date;

import com.allmsi.flow.model.FlowInstanceLog;

public class FlowStateLogOVO {

	private String instanceId;

	private String stateId;

	private String sufDealName;

	private String dealState;

	private String dealStateName;

	private Date dealTime;

	private String opinion;

	private String nodeId;

	public FlowStateLogOVO() {

	}
	
	public FlowStateLogOVO(String instanceId, String stateId, String sufDealName, String dealState,
			String dealStateName, Date dealTime, String opinion, String nodeId) {
		this.instanceId = instanceId;
		this.stateId = stateId;
		this.sufDealName = sufDealName;
		this.dealState = dealState;
		this.dealStateName = dealStateName;
		this.dealTime = dealTime;
		this.opinion = opinion;
		this.nodeId = nodeId;
	}

	public FlowStateLogOVO(FlowInstanceLog flowInstanceLog) {
		if (flowInstanceLog != null) {
			this.instanceId = flowInstanceLog.getInstanceId();
			this.stateId = flowInstanceLog.getId();
			this.sufDealName = flowInstanceLog.getSufDealName();
			this.dealState = flowInstanceLog.getDealState();
			this.dealTime = flowInstanceLog.getDealTime();
			this.opinion = flowInstanceLog.getOpinion();
			this.nodeId = flowInstanceLog.getNodeId();
		}
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getSufDealName() {
		return sufDealName;
	}

	public void setSufDealName(String sufDealName) {
		this.sufDealName = sufDealName;
	}

	public String getDealState() {
		return dealState;
	}

	public void setDealState(String dealState) {
		this.dealState = dealState;
	}

	public String getDealStateName() {
		return dealStateName;
	}

	public void setDealStateName(String dealStateName) {
		this.dealStateName = dealStateName;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	@Override
	public String toString() {
		return "FlowStateLogOVo [instanceId=" + instanceId + ", stateId=" + stateId + ", sufDealName=" + sufDealName
				+ ", dealState=" + dealState + ", dealStateName=" + dealStateName + ", dealTime=" + dealTime
				+ ", opinion=" + opinion + ", nodeId=" + nodeId + "]";
	}

}
