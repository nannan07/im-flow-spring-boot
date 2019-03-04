package com.allmsi.flow.model;

public class FlowNode {
    private String id;

    private String flowId;

    private String nodeName;

    private String nodeType;

    private Boolean del;

	private String continuation;
	
    public FlowNode() {
	}
    
  
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

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType == null ? null : nodeType.trim();
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }


	public String getContinuation() {
		return continuation;
	}


	public void setContinuation(String continuation) {
		this.continuation = continuation;
	}
}