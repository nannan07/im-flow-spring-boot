package com.allmsi.flow.model.ovo;

import com.allmsi.flow.model.FlowField;

public class FlowFieldOVO {
	private String id;

	private String flowId;

	private String fieldName;// 字段名

	private String cnName;// 注释

	private String fieldTypeId;// 字段类型id

	private Integer fieldLength;// 字段长度

	private Integer point;// 小数点

	private String fieldPropertyId;// 字段属性id

	private String defaultValue;// 默认值

	private String typeName;// 类型名称

	private String propertyName;// 属性名称

	public FlowFieldOVO() {

	}

	public FlowFieldOVO(FlowField flowField) {
		if (flowField != null) {
			this.id = flowField.getId();
			this.flowId = flowField.getFlowId();
			this.fieldName = flowField.getFieldName();
			this.fieldTypeId = flowField.getFieldTypeId();
			this.typeName = flowField.getTypeName();
			this.fieldLength = flowField.getFieldLength();
			this.point = flowField.getPoint();
			this.fieldPropertyId = flowField.getFieldPropertyId();
			this.propertyName = flowField.getPropertyName();
			this.defaultValue = flowField.getDefaultValue();
			this.cnName = flowField.getCnName();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldTypeId() {
		return fieldTypeId;
	}

	public void setFieldTypeId(String fieldTypeId) {
		this.fieldTypeId = fieldTypeId;
	}

	public Integer getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(Integer fieldLength) {
		this.fieldLength = fieldLength;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public String getFieldPropertyId() {
		return fieldPropertyId;
	}

	public void setFieldPropertyId(String fieldPropertyId) {
		this.fieldPropertyId = fieldPropertyId;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
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
}
