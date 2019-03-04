package com.allmsi.flow.model.external;

import com.allmsi.flow.model.FlowInstance;
import com.allmsi.flow.model.FlowNodeDeal;
import com.allmsi.flow.model.ovo.FlowInstanceDealOVO;

public class FlowUserModel {

	private String userId;

	private String name;

	private String type;
	
	private Integer sort;

	// 用户部门
	private String deptId;

	private String deptName;

	public FlowUserModel() {

	}

	public FlowUserModel(FlowUserModel flowUserModel) {
		this.userId = flowUserModel.getUserId();
		this.name = flowUserModel.getName();
		this.type = flowUserModel.getType();
		this.deptId = flowUserModel.getDeptId();
		this.deptName = flowUserModel.getDeptName();
	}

	public FlowUserModel(String userId, String name, String type) {
		this.userId = userId;
		this.name = name;
		this.type = type;
	}

	public FlowUserModel(String userId, String name, String type, String deptId, String deptName) {
		this.userId = userId;
		this.name = name;
		this.type = type;
		this.deptId = deptId;
		this.deptName = deptName;
	}
	
	public FlowUserModel(String userId, String name, String type, Integer sort, String deptId, String deptName) {
		this.userId = userId;
		this.name = name;
		this.type = type;
		this.sort = sort;
		this.deptId = deptId;
		this.deptName = deptName;
	}

	public FlowUserModel(FlowInstance flowInstancePo) {
		this.userId = flowInstancePo.getcUserId();
	}

	public FlowUserModel(FlowNodeDeal flowNodeDeal2) {
		this.userId = flowNodeDeal2.getNodeDealId();
		this.type = flowNodeDeal2.getNodeDealType();
	}

	public FlowUserModel(FlowInstanceDealOVO flowInstanceDealOVo) {
		if (flowInstanceDealOVo != null) {
			this.userId = flowInstanceDealOVo.getPreId();
			this.name = flowInstanceDealOVo.getPreName();
			this.type = "01";
			this.deptId = flowInstanceDealOVo.getPreDeptId();
			this.deptName =flowInstanceDealOVo.getPreDeptName();
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "FlowUserModel [userId=" + userId + ", name=" + name + ", type=" + type + ", deptId=" + deptId
				+ ", deptName=" + deptName + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof FlowUserModel) {
			FlowUserModel flowUserModel = (FlowUserModel) obj;
			if (this.getUserId().equals(flowUserModel.getUserId())) {
				return true;
			} else
				return false;
		} else
			return false;
	}
}
