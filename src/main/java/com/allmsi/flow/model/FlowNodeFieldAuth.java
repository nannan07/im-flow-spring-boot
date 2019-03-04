package com.allmsi.flow.model;

public class FlowNodeFieldAuth {
	private String id;

	private String nodeFieldId;

	private String authId;

	private String authType;

	private Integer status;

	private Integer del;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getNodeFieldId() {
		return nodeFieldId;
	}

	public void setNodeFieldId(String nodeFieldId) {
		this.nodeFieldId = nodeFieldId == null ? null : nodeFieldId.trim();
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId == null ? null : authId.trim();
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType == null ? null : authType.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}

}