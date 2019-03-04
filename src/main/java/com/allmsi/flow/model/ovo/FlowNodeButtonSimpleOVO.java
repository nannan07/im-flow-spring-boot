package com.allmsi.flow.model.ovo;

public class FlowNodeButtonSimpleOVO {
	private String buttonName;

	private String buttonType;
	
	private Integer sort;

	public FlowNodeButtonSimpleOVO() {
	}

	public FlowNodeButtonSimpleOVO(String buttonName, String buttonType,Integer sort) {
		this.buttonName = buttonName;
		this.buttonType = buttonType;
		this.sort = sort;
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public String getButtonType() {
		return buttonType;
	}

	public void setButtonType(String buttonType) {
		this.buttonType = buttonType;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
