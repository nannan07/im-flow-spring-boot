package com.allmsi.flow.model.ovo;

import java.util.ArrayList;
import java.util.List;

public class SubGuide {

	private String type;

	private String msg;

	private Object data;

	private boolean flag;

	private List<String> userIds = new ArrayList<String>();
	
	private List<String> todoUserIds = new ArrayList<String>();
	
	private List<String> toreadUserIds = new ArrayList<String>();

	public SubGuide() {
	}

	public SubGuide(String type, String msg) {
		this(type, msg, false, null);
	}

	public SubGuide(String type, String msg, Object data) {
		this(type, msg, false, data);
	}

	public SubGuide(String type, String msg, boolean flag) {
		this(type, msg, flag, null);
	}

	public SubGuide(String type, String msg, boolean flag, Object data) {
		this.type = type;
		this.msg = msg;
		this.flag = flag;
		this.data = data;
	}

	public SubGuide(String type, String msg, boolean flag, Object data, List<String> userIds) {
		this.type = type;
		this.msg = msg;
		this.data = data;
		this.flag = flag;
		this.userIds = userIds;
	}
	
	public SubGuide(String type, String msg, boolean flag, Object data, List<String> userIds, List<String> todoUserIds, List<String> toreadUserIds) {
		this.type = type;
		this.msg = msg;
		this.data = data;
		this.flag = flag;
		this.userIds = userIds;
		this.todoUserIds = todoUserIds;
		this.toreadUserIds = toreadUserIds;
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
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
		return "SubGuide [type=" + type + ", msg=" + msg + ", data=" + data + ", flag=" + flag + ", userIds=" + userIds
				+ ", todoUserIds=" + todoUserIds + ", toreadUserIds=" + toreadUserIds + "]";
	}
}
