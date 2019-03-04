package com.allmsi.flow.model.ovo;

import java.util.List;

import com.allmsi.flow.model.FlowReadDeal;

public class FlowReadDealOVO {

	private String id;

	private String tab;

	private String tabId;

	private String readId;

	private String readType;

	private List<String> queryList;

	public FlowReadDealOVO() {
	}

	public FlowReadDealOVO(FlowReadDeal flowReadDeal) {
		if (flowReadDeal != null) {
			this.id = flowReadDeal.getId();
			this.tab = flowReadDeal.getTab();
			this.tabId = flowReadDeal.getTabId();
			this.readId = flowReadDeal.getReadId();
			this.readType = flowReadDeal.getReadType();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}

	public String getTabId() {
		return tabId;
	}

	public void setTabId(String tabId) {
		this.tabId = tabId;
	}

	public String getReadId() {
		return readId;
	}

	public void setReadId(String readId) {
		this.readId = readId;
	}

	public String getReadType() {
		return readType;
	}

	public void setReadType(String readType) {
		this.readType = readType;
	}

	public List<String> getQueryList() {
		return queryList;
	}

	public void setQueryList(List<String> queryList) {
		this.queryList = queryList;
	}

}
