package com.allmsi.flow.model.ivo;

public class TransferInterimIVO {
	
	private String id;

	private String principal;

	private String agent;

	private String flowCode;

	private String nodeId;

	private String startTime;

	private String endTime;

	private Integer isRetain;//是否保留权限

	private String cUserId;

	private String uUserId;
	
	public TransferInterimIVO() {
		
	}

	public TransferInterimIVO(String id, String principal, String agent, String flowCode, String nodeId,
			String startTime, String endTime, Integer isRetain, String cUserId, String uUserId) {
		this.id = id;
		this.principal = principal;
		this.agent = agent;
		this.flowCode = flowCode;
		this.nodeId = nodeId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isRetain = isRetain;
		this.cUserId = cUserId;
		this.uUserId = uUserId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getIsRetain() {
		return isRetain;
	}

	public void setIsRetain(Integer isRetain) {
		this.isRetain = isRetain;
	}

	public String getcUserId() {
		return cUserId;
	}

	public void setcUserId(String cUserId) {
		this.cUserId = cUserId;
	}

	public String getuUserId() {
		return uUserId;
	}

	public void setuUserId(String uUserId) {
		this.uUserId = uUserId;
	}
}
