package com.allmsi.flow.model;

import java.util.ArrayList;
import java.util.List;

public class AuthGuide {

	private List<String> stateIdList = new ArrayList<String>();

	private List<String> userIds = new ArrayList<String>();

	private List<String> todoUserIds = new ArrayList<String>();

	private List<String> toreadUserIds = new ArrayList<String>();

	public List<String> getStateIdList() {
		return stateIdList;
	}

	public void setStateIdList(List<String> stateIdList) {
		this.stateIdList = stateIdList;
	}

	public List<String> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}

	public List<String> getTodoUserIds() {
		return todoUserIds;
	}

	public void setTodoUserIds(List<String> todoUserIds) {
		this.todoUserIds = todoUserIds;
	}

	public List<String> getToreadUserIds() {
		return toreadUserIds;
	}

	public void setToreadUserIds(List<String> toreadUserIds) {
		this.toreadUserIds = toreadUserIds;
	}

	@Override
	public String toString() {
		return "AuthGuide [stateIdList=" + stateIdList + ", userIds=" + userIds + ", todoUserIds=" + todoUserIds
				+ ", toreadUserIds=" + toreadUserIds + "]";
	}
	
}
