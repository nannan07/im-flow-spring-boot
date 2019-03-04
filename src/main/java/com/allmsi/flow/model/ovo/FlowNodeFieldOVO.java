package com.allmsi.flow.model.ovo;

import com.allmsi.flow.model.FlowField;
import com.allmsi.flow.model.FlowNodeField;

public class FlowNodeFieldOVO {

	private String fieldName;

	private String cnName;

	private String typeName;

	private String propertyName;

	private Integer fieldLength;

	private Integer sort;

	private Integer status;

	public FlowNodeFieldOVO() {

	}

	public FlowNodeFieldOVO(FlowNodeField flowNodeField) {
		if (flowNodeField != null) {
			this.status = flowNodeField.getStatus();
			this.sort = flowNodeField.getSort();
		}
	}

	public FlowNodeFieldOVO(FlowFieldOVO flowFieldOVO) {
		if (flowFieldOVO != null) {
			this.fieldName = flowFieldOVO.getFieldName();
			this.cnName = flowFieldOVO.getCnName();
			this.typeName = flowFieldOVO.getTypeName();
			this.propertyName = flowFieldOVO.getPropertyName();
			this.fieldLength = flowFieldOVO.getFieldLength();
		}
	}

	public FlowNodeFieldOVO(FlowField flowField) {
		if (flowField != null) {
			this.fieldName = flowField.getFieldName();
			this.cnName = flowField.getCnName();
			this.typeName = flowField.getTypeName();
			this.propertyName = flowField.getPropertyName();
			this.fieldLength = flowField.getFieldLength();
		}
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Integer getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(Integer fieldLength) {
		this.fieldLength = fieldLength;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	@Override
	public String toString() {
		return "FlowNodeFieldOVO [fieldName=" + fieldName + ", cnName=" + cnName + ", typeName=" + typeName
				+ ", propertyName=" + propertyName + ", fieldLength=" + fieldLength + ", sort=" + sort + ", status="
				+ status + "]";
	}

}
