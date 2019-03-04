package com.allmsi.flow.model;

public class FlowNodeReadDealQuery {
    private String id;

    private String readDealId;

    private String queryName;

    private String queryValue;

    private String queryGroup;

    private Byte del;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getReadDealId() {
        return readDealId;
    }

    public void setReadDealId(String readDealId) {
        this.readDealId = readDealId == null ? null : readDealId.trim();
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName == null ? null : queryName.trim();
    }

    public String getQueryValue() {
        return queryValue;
    }

    public void setQueryValue(String queryValue) {
        this.queryValue = queryValue == null ? null : queryValue.trim();
    }

    public String getQueryGroup() {
        return queryGroup;
    }

    public void setQueryGroup(String queryGroup) {
        this.queryGroup = queryGroup == null ? null : queryGroup.trim();
    }

    public Byte getDel() {
        return del;
    }

    public void setDel(Byte del) {
        this.del = del;
    }
}