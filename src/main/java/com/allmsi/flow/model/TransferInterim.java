package com.allmsi.flow.model;

import java.util.Date;

import com.allmsi.flow.model.ivo.TransferInterimIVO;
import com.allmsi.sys.util.DateUtil;
import com.allmsi.sys.util.StrUtil;

public class TransferInterim {

	private String id;

	private String principal;

	private String agent;

	private String flowCode;

	private String nodeId;

	private Date startTime;

	private Date endTime;

	private Integer isRetain;

	private String cUserId;

	private Date cTime;

	private String uUserId;

	private Date uTime;

	private Boolean del;
	
	public TransferInterim() {
	}
	

	public TransferInterim(TransferInterimIVO transferInterimIVo) {
		if(transferInterimIVo != null) {
			this.id = transferInterimIVo.getId();
			this.principal = transferInterimIVo.getPrincipal();
			this.agent = transferInterimIVo.getAgent();
			this.flowCode = transferInterimIVo.getFlowCode();
			this.nodeId = transferInterimIVo.getNodeId();
			String startTimeStr = transferInterimIVo.getStartTime();
			if(StrUtil.notEmpty(startTimeStr)) {
				this.startTime = DateUtil.stringConvertDateTime(startTimeStr);
			}
			String endTimeStr = transferInterimIVo.getEndTime();
			if(StrUtil.notEmpty(endTimeStr)) {
				this.endTime = DateUtil.stringConvertDateTime(endTimeStr);
			}
			this.isRetain = transferInterimIVo.getIsRetain();
			this.cUserId = transferInterimIVo.getcUserId();
			this.uUserId = transferInterimIVo.getuUserId();
		}
		
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public Integer getIsRetain() {
		return isRetain;
	}


	public void setIsRetain(Integer isRetain) {
		this.isRetain = isRetain;
	}


	public String getcUserId() {
		return cUserId;
	}

	public void setcUserId(String cUserId) {
		this.cUserId = cUserId;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public String getuUserId() {
		return uUserId;
	}

	public void setuUserId(String uUserId) {
		this.uUserId = uUserId;
	}

	public Date getuTime() {
		return uTime;
	}

	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}

	public Boolean getDel() {
		return del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}
}
