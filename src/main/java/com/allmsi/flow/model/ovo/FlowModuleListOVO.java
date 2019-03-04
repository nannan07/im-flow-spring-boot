package com.allmsi.flow.model.ovo;

import java.util.Date;

import com.allmsi.flow.model.FlowModuleView;

public class FlowModuleListOVO {

	private String objId;

	private String objName;

	private String handlerId;

	private String handlerName;

	private String handlerDeptId;

	private String handlerDept;

	private String objValue1;

	private String objValue2;

	private String objValue3;

	private String objValue4;

	private String objValue5;

	private String code;

	private Date createTime;

	public FlowModuleListOVO() {

	}

	public FlowModuleListOVO(FlowModuleView flowModuleView) {
		if (flowModuleView != null) {
			this.objId = flowModuleView.getObjId();
			this.objName = flowModuleView.getObjName();
			this.handlerId = flowModuleView.getHandlerId();
			this.handlerName = flowModuleView.getHandlerName();
			this.handlerDeptId = flowModuleView.getHandlerDeptId();
			this.handlerDept = flowModuleView.getHandlerDept();
			this.objValue1 = flowModuleView.getObjValue1();
			this.objValue2 = flowModuleView.getObjValue2();
			this.objValue3 = flowModuleView.getObjValue3();
			this.objValue4 = flowModuleView.getObjValue4();
			this.objValue5 = flowModuleView.getObjValue5();
			this.code = flowModuleView.getCode();
			this.createTime = flowModuleView.getCreateTime();
		}
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public String getHandlerId() {
		return handlerId;
	}

	public void setHandlerId(String handlerId) {
		this.handlerId = handlerId;
	}

	public String getHandlerName() {
		return handlerName;
	}

	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}

	public String getHandlerDeptId() {
		return handlerDeptId;
	}

	public void setHandlerDeptId(String handlerDeptId) {
		this.handlerDeptId = handlerDeptId;
	}

	public String getHandlerDept() {
		return handlerDept;
	}

	public void setHandlerDept(String handlerDept) {
		this.handlerDept = handlerDept;
	}

	public String getObjValue1() {
		return objValue1;
	}

	public void setObjValue1(String objValue1) {
		this.objValue1 = objValue1;
	}

	public String getObjValue2() {
		return objValue2;
	}

	public void setObjValue2(String objValue2) {
		this.objValue2 = objValue2;
	}

	public String getObjValue3() {
		return objValue3;
	}

	public void setObjValue3(String objValue3) {
		this.objValue3 = objValue3;
	}

	public String getObjValue4() {
		return objValue4;
	}

	public void setObjValue4(String objValue4) {
		this.objValue4 = objValue4;
	}

	public String getObjValue5() {
		return objValue5;
	}

	public void setObjValue5(String objValue5) {
		this.objValue5 = objValue5;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
