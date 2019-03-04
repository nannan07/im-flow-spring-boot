package com.allmsi.flow.model.ivo;

public class FlowInstanceDealIVO {
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

	public FlowInstanceDealIVO() {

	}

	public FlowInstanceDealIVO(String id, String stateId, String preId, String preName, String preDeptId,
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
		this.id = id == null ? null : id.trim();
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

}