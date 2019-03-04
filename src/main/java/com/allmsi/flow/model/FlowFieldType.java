package com.allmsi.flow.model;

public class FlowFieldType {
    private String id;

    private String typeName;

    private Byte del;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Byte getDel() {
        return del;
    }

    public void setDel(Byte del) {
        this.del = del;
    }

	@Override
	public String toString() {
		return "FlowFieldType [id=" + id + ", typeName=" + typeName + ", del=" + del + "]";
	}
}