package com.allmsi.flow.model.ivo;

public class TransferForeverIVO {
	
	private String id;

	private String principal;//授权人

	private String agent;//代理人

	private String flowCode;

	private String nodeId;
	
	private Integer isHisTransfer;//历史数据是否授权

	private String cUserId;

	public TransferForeverIVO() {
	}

	public TransferForeverIVO(String id, String principal, String agent, String flowCode, String nodeId,
			Integer isHisTransfer, String cUserId) {
		this.id = id;
		this.principal = principal;
		this.agent = agent;
		this.flowCode = flowCode;
		this.nodeId = nodeId;
		this.isHisTransfer = isHisTransfer;
		this.cUserId = cUserId;
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

	public Integer getIsHisTransfer() {
		return isHisTransfer;
	}

	public void setIsHisTransfer(Integer isHisTransfer) {
		this.isHisTransfer = isHisTransfer;
	}

	public String getcUserId() {
		return cUserId;
	}

	public void setcUserId(String cUserId) {
		this.cUserId = cUserId;
	}
}
