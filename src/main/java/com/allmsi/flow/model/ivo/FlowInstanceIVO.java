package com.allmsi.flow.model.ivo;

import com.allmsi.sys.util.UUIDUtil;

public class FlowInstanceIVO {

	private String id;

	private String flowId;

	private String objectId;

	private String cUserId;
	
	private Integer draft;
	
	public FlowInstanceIVO() {
		
	}

	public FlowInstanceIVO(String id, String flowId, String objectId, String cUserId,Integer draft) {
		this.id = id;
		this.flowId = flowId;
		this.objectId = objectId;
		this.cUserId = cUserId;
		this.draft = draft;
	}

	public FlowInstanceIVO(String flowId, String objectId, String cUserId) {
		this.flowId = flowId;
		this.objectId = objectId;
		this.cUserId = cUserId;
	}
	
	public FlowInstanceIVO(String flowId, String objectId, String cUserId,Integer draft) {
		this.id = UUIDUtil.getUUID();
		this.flowId = flowId;
		this.objectId = objectId;
		this.cUserId = cUserId;
		this.draft = draft;
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

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getcUserId() {
		return cUserId;
	}

	public void setcUserId(String cUserId) {
		this.cUserId = cUserId;
	}

	public Integer getDraft() {
		return draft;
	}

	public void setDraft(Integer draft) {
		this.draft = draft;
	}
}
