package com.allmsi.flow.model;

public class FlowField {
	private String id;

	private String flowId;

	private String fieldName;

	private String cnName;

	private String fieldTypeId;

	private String typeName;

	private Integer fieldLength;

	private Integer point;

	private String fieldPropertyId;

	private String propertyName;

	private String defaultValue;

	private Integer del;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId == null ? null : flowId.trim();
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName == null ? null : fieldName.trim();
	}

	public String getFieldTypeId() {
		return fieldTypeId;
	}

	public void setFieldTypeId(String fieldTypeId) {
		this.fieldTypeId = fieldTypeId == null ? null : fieldTypeId.trim();
	}

	public Integer getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(Integer fieldLength) {
		this.fieldLength = fieldLength;
	}

	public String getFieldPropertyId() {
		return fieldPropertyId;
	}

	public void setFieldPropertyId(String fieldPropertyId) {
		this.fieldPropertyId = fieldPropertyId == null ? null : fieldPropertyId.trim();
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue == null ? null : defaultValue.trim();
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
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

}