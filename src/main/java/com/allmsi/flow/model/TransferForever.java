package com.allmsi.flow.model;

import java.util.Date;

import com.allmsi.flow.model.ivo.TransferForeverIVO;

public class TransferForever {

	private String id;

	private String principal;

	private String agent;

	private String flowCode;

	private String nodeId;

	private Integer isHisTransfer;

	private String cUserId;

	private Date cTime;

	public TransferForever() {

	}

	public TransferForever(TransferForeverIVO transferForeverIVo) {
		if (transferForeverIVo != null) {
			this.id = transferForeverIVo.getId();
			this.principal = transferForeverIVo.getPrincipal();
			this.agent = transferForeverIVo.getAgent();
			this.flowCode = transferForeverIVo.getFlowCode();
			this.nodeId = transferForeverIVo.getNodeId();
			this.isHisTransfer = transferForeverIVo.getIsHisTransfer();
			this.cUserId = transferForeverIVo.getcUserId();
		}

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

	public Integer getIsHisTransfer() {
		return isHisTransfer;
	}

	public void setIsHisTransfer(Integer isHisTransfer) {
		this.isHisTransfer = isHisTransfer;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getcUserId() {
		return cUserId;
	}

	public void setcUserId(String cUserId) {
		this.cUserId = cUserId;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

}
