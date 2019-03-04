package com.allmsi.flow.model.ovo;

import java.util.List;

import com.allmsi.flow.model.external.FlowUserModel;

public class BusGuide {

	private String type;

	private String msg;

	private String selfDefined;

	private List<FlowUserModel> flowUserList;

	private List<FlowUserModel> flowReadList;

	private List<FlowRouteOVO> flowRouteList;

	public BusGuide() {
	}

	public BusGuide(String type, String msg) {
		this.type = type;
		this.msg = msg;
	}

	public BusGuide(String type, String msg, List<FlowUserModel> flowUserList, List<FlowUserModel> flowReadList,
			List<FlowRouteOVO> flowRouteList) {
		this.type = type;
		this.msg = msg;
		this.flowUserList = flowUserList;
		this.flowReadList = flowReadList;
		this.flowRouteList = flowRouteList;
	}

	public BusGuide(String type, String msg, List<FlowUserModel> flowUserList, List<FlowRouteOVO> flowRouteList) {
		this.type = type;
		this.msg = msg;
		this.flowUserList = flowUserList;
		this.flowRouteList = flowRouteList;
	}

	public BusGuide(String type, String msg,String selfDefined,
			List<FlowUserModel> flowUserList, List<FlowUserModel> flowReadList, List<FlowRouteOVO> flowRouteList) {
		this.type = type;
		this.msg = msg;
		this.selfDefined = selfDefined;
		this.flowUserList = flowUserList;
		this.flowReadList = flowReadList;
		this.flowRouteList = flowRouteList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<FlowUserModel> getFlowUserList() {
		return flowUserList;
	}

	public void setFlowUserList(List<FlowUserModel> flowUserList) {
		this.flowUserList = flowUserList;
	}

	public List<FlowRouteOVO> getFlowRouteList() {
		return flowRouteList;
	}

	public void setFlowRouteList(List<FlowRouteOVO> flowRouteList) {
		this.flowRouteList = flowRouteList;
	}

	public List<FlowUserModel> getFlowReadList() {
		return flowReadList;
	}

	public void setFlowReadList(List<FlowUserModel> flowReadList) {
		this.flowReadList = flowReadList;
	}
	
	public String getSelfDefined() {
		return selfDefined;
	}

	public void setSelfDefined(String selfDefined) {
		this.selfDefined = selfDefined;
	}

	@Override
	public String toString() {
		return "BusGuide [type=" + type + ", msg=" + msg + ", flowUserList=" + flowUserList + ", flowReadList="
				+ flowReadList + ", flowRouteList=" + flowRouteList + "]";
	}

}
