package com.allmsi.flow.model;

public class FlowNodeButton {
	private String id;

	private String nodeId;

	private String buttonName;

	private String buttonType;
	
	private Integer sort;

	private Boolean del;

	public FlowNodeButton() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId == null ? null : nodeId.trim();
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName == null ? null : buttonName.trim();
	}

	public String getButtonType() {
		return buttonType;
	}

	public void setButtonType(String buttonType) {
		this.buttonType = buttonType == null ? null : buttonType.trim();
	}
	
	public Boolean getDel() {
		return del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}