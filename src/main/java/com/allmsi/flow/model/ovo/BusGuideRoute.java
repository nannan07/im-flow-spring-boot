package com.allmsi.flow.model.ovo;

import java.util.List;

import com.allmsi.flow.model.external.FlowUserModel;

public class BusGuideRoute {

	private String type;

	private String msg;
	
	private boolean flag;

	private List<FlowRouteOVO> flowRouteList;
	
	private List<FlowUserModel> routeUserList;

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


	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public List<FlowRouteOVO> getFlowRouteList() {
		return flowRouteList;
	}

	public void setFlowRouteList(List<FlowRouteOVO> flowRouteList) {
		this.flowRouteList = flowRouteList;
	}

	public List<FlowUserModel> getRouteUserList() {
		return routeUserList;
	}

	public void setRouteUserList(List<FlowUserModel> routeUserList) {
		this.routeUserList = routeUserList;
	}

	@Override
	public String toString() {
		return "BusGuideRoute [type=" + type + ", msg=" + msg + ", flag=" + flag + ", flowRouteList=" + flowRouteList
				+ ", routeUserList=" + routeUserList + "]";
	}

}
