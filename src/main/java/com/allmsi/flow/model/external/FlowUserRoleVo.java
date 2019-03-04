package com.allmsi.flow.model.external;

public class FlowUserRoleVo {

	private String roleId;

	private String roleName;
	
	public FlowUserRoleVo(){
		
	}
	

	public FlowUserRoleVo(String roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
