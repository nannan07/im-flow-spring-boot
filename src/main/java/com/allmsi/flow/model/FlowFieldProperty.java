package com.allmsi.flow.model;

public class FlowFieldProperty {
    private String id;

    private String propertyName;

    private Byte del;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName == null ? null : propertyName.trim();
    }

    public Byte getDel() {
        return del;
    }

    public void setDel(Byte del) {
        this.del = del;
    }

	@Override
	public String toString() {
		return "FlowFieldProperty [id=" + id + ", propertyName=" + propertyName + ", del=" + del + "]";
	}
}