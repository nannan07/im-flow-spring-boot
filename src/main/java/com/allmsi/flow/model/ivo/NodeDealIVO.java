package com.allmsi.flow.model.ivo;

import com.allmsi.sys.util.StrUtil;

public class NodeDealIVO {
	private String dealId;

	private String dealType;

	private String authType;

	private String deptId;

	private String deptName;

	private String dealName;

	private Integer isProxy;

	private String proxyId;

	public NodeDealIVO() {
	}

	public NodeDealIVO(String dealId, String dealType, String authType, String deptId, String deptName,
			String dealName) {
		this.dealId = dealId;
		this.dealType = dealType;
		this.authType = authType;
		this.deptId = deptId;
		this.deptName = deptName;
		this.dealName = dealName;
	}

	public NodeDealIVO(String dealId, String dealType, String authType, String deptId, String deptName, String dealName,
			Integer isProxy, String proxyId) {
		this.dealId = dealId;
		this.dealType = dealType;
		this.authType = authType;
		this.deptId = deptId;
		this.deptName = deptName;
		this.dealName = dealName;
		this.isProxy = isProxy;
		this.proxyId = proxyId;
	}

	public NodeDealIVO(String dealId) {
		if (StrUtil.notEmpty(dealId)) {
			this.dealId = dealId;
		}
	}

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDealName() {
		return dealName;
	}

	public void setDealName(String dealName) {
		this.dealName = dealName;
	}

	public Integer getIsProxy() {
		return isProxy;
	}

	public void setIsProxy(Integer isProxy) {
		this.isProxy = isProxy;
	}

	public String getProxyId() {
		return proxyId;
	}

	public void setProxyId(String proxyId) {
		this.proxyId = proxyId;
	}

}
