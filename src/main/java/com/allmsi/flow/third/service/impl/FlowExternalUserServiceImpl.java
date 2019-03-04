package com.allmsi.flow.third.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.auth.model.vo.DeptVo;
import com.allmsi.auth.model.vo.RoleVo;
import com.allmsi.auth.model.vo.UserDeptVo;
import com.allmsi.auth.model.vo.UserInfoVo;
import com.allmsi.auth.model.vo.UserRoleVo;
import com.allmsi.auth.service.DeptService;
import com.allmsi.auth.service.RoleService;
import com.allmsi.auth.service.UserService;
import com.allmsi.flow.model.external.FlowDealIdATypeModel;
import com.allmsi.flow.model.external.FlowUserDeptVo;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.external.FlowUserRoleVo;
import com.allmsi.flow.third.service.FlowExternalUserService;
import com.allmsi.sys.model.ListBean;
import com.allmsi.sys.util.StrUtil;

@Service("defaultFlowExternalUserService")
public class FlowExternalUserServiceImpl implements FlowExternalUserService {

	@Resource
	private UserService userService;

	@Resource
	private DeptService deptService;

	@Resource
	private RoleService roleService;

	@Override
	public FlowUserModel selectUserInfo(String nodeDealId) {
		return getUserInfo(nodeDealId).get(nodeDealId);
	}

	@Override
	public Map<String, FlowUserModel> getUserInfo(String id) {
		Map<String, FlowUserModel> map = new HashMap<String, FlowUserModel>();
		if (StrUtil.isEmpty(id)) {
			return map;
		}
		UserInfoVo userInfoVo = userService.selectByPrimaryKey(id);
		map.put(id, new FlowUserModel(userInfoVo.getId(), userInfoVo.getUserName(), "01", userInfoVo.getSort(),
				userInfoVo.getDeptId(), userInfoVo.getDeptName()));
		return map;
	}

	public Map<String, FlowUserModel> getFlowUserInfoList(List<String> ids) {
		Map<String, FlowUserModel> map = new HashMap<String, FlowUserModel>();
		if (ids != null && ids.size() > 0) {
			List<UserInfoVo> uList = userService.listUserInfo(ids);
			for (UserInfoVo userInfoVo : uList) {
				map.put(userInfoVo.getId(), new FlowUserModel(userInfoVo.getId(), userInfoVo.getUserName(), "01",
						userInfoVo.getSort(), userInfoVo.getDeptId(), userInfoVo.getDeptName()));
			}
		}
		return map;
	}

	@Override
	public List<FlowUserModel> FlowUserInfoList(List<String> userIds) {
		List<FlowUserModel> list = new ArrayList<FlowUserModel>();
		if (userIds != null && userIds.size() > 0) {
			List<UserInfoVo> uList = userService.listUserInfo(userIds);
			for (UserInfoVo userInfoVo : uList) {
				list.add(new FlowUserModel(userInfoVo.getId(), userInfoVo.getUserName(), "01", userInfoVo.getSort(),
						userInfoVo.getDeptId(), userInfoVo.getDeptName()));
			}
		}
		return list;
	}

	public Map<String, String> getDealNameByDealIdAndType(List<FlowDealIdATypeModel> dealIdAndDealType) {
		Map<String, String> map = new HashMap<String, String>();

		List<String> userList = new ArrayList<String>();
		List<String> deptList = new ArrayList<String>();
		List<String> roleList = new ArrayList<String>();

		for (FlowDealIdATypeModel model : dealIdAndDealType) {
			if ("01".equals(model.getDealType())) {
				userList.add(model.getDealId());
			}
			if ("02".equals(model.getDealType())) {
				deptList.add(model.getDealId());
			}
			if ("03".equals(model.getDealType())) {
				roleList.add(model.getDealId());
			}
		}

		// 批量查询用户
		if (userList != null && userList.size() > 0) {
			Map<String, FlowUserModel> map1 = getFlowUserInfoList(userList);
			for (String key : map1.keySet()) {
				FlowUserModel value = map1.get(key);
				map.put(key, value.getName());
			}
		}

		// 批量查询部门
		if (deptList != null && deptList.size() > 0) {
			List<DeptVo> dList = deptService.listDeptList(deptList);
			for (DeptVo deptVo : dList) {
				map.put(deptVo.getId(), deptVo.getDeptName());
			}
		}
		if (roleList != null && roleList.size() > 0) {
			// 批量查询角色
			List<RoleVo> rList = roleService.listRoles(roleList);
			for (RoleVo roleVo : rList) {
				map.put(roleVo.getId(), roleVo.getRoleName());
			}
		}
		return map;
	}

	public String getDealNameByDealIdAndType(String dealId, String dealType) {
		String dealName = null;

		if ("01".equals(dealType)) {// 查询用户信息
			UserInfoVo userInfoVo = userService.selectByPrimaryKey(dealId);
			if (userInfoVo != null) {
				dealName = userInfoVo.getUserName();
			}

		}
		if ("02".equals(dealType)) {
			DeptVo deptVo = deptService.selectByPrimaryKey(dealId);
			dealName = deptVo.getDeptName();
		}
		if ("03".equals(dealType)) {
			RoleVo roleVo = roleService.selectByPrimaryKey(dealId);
			dealName = roleVo.getRoleName();
		}
		return dealName;
	}

	public Map<String, List<String>> getUserAuthIdSort(String userId) {
		Map<String, List<String>> authMap = new HashMap<String, List<String>>();
		// 查询用户的基本信息
		UserInfoVo userInfoVo = userService.selectByPrimaryKey(userId);
		List<String> userList = new ArrayList<String>();
		userList.add(userInfoVo.getId());
		if (userList != null && userList.size() > 0) {
			authMap.put("01", userList);
		}

		// 查询用户部门信息
		List<UserDeptVo> udVoList = deptService.listUserDeptVo(userId);
		List<String> udList = new ArrayList<String>();
		if (udVoList != null && udVoList.size() > 0) {
			for (UserDeptVo userDeptVo : udVoList) {
				udList.add(userDeptVo.getDeptId());
			}
			authMap.put("02", udList);
		}

		// 查询用户角色信息
		List<UserRoleVo> uRInfoList = roleService.listUserRole(userId);
		List<String> urList = new ArrayList<String>();
		if (uRInfoList != null && uRInfoList.size() > 0) {
			for (UserRoleVo userRoleVo : uRInfoList) {
				urList.add(userRoleVo.getRoleId());
			}
			authMap.put("03", urList);
		}
		return authMap;
	}

	@Override
	public List<FlowUserDeptVo> selectUserDept(String userId) {
		if (StrUtil.isEmpty(userId)) {
			return null;
		}
		List<UserDeptVo> udList = deptService.listUserDeptVo(userId);
		List<FlowUserDeptVo> udInfoList = new ArrayList<FlowUserDeptVo>();
		for (UserDeptVo userDeptVo : udList) {
			udInfoList.add(new FlowUserDeptVo(userDeptVo.getDeptId(), userDeptVo.getUdType(), userDeptVo.getDeptName(),
					userDeptVo.getDeptType()));
		}
		return udInfoList;
	}

	@Override
	public List<FlowUserRoleVo> selectUserRoleList(String userId) {
		if (StrUtil.isEmpty(userId)) {
			return null;
		}
		List<UserRoleVo> urJson = roleService.listUserRole(userId);
		List<FlowUserRoleVo> uRInfoList = new ArrayList<FlowUserRoleVo>();
		for (UserRoleVo userRoleVo : urJson) {
			uRInfoList.add(new FlowUserRoleVo(userRoleVo.getRoleId(), userRoleVo.getRoleName()));
		}
		return uRInfoList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FlowUserModel> selectUserByDeptId(String deptId) {
		if (StrUtil.isEmpty(deptId)) {
			return null;
		}
		ListBean listBean = userService.listUserInfoByDeptId(deptId, 1, Integer.MAX_VALUE);
		List<UserInfoVo> userVoList = (List<UserInfoVo>) listBean.getList();
		List<FlowUserModel> list = new ArrayList<FlowUserModel>();
		for (UserInfoVo UserInfoVo : userVoList) {
			FlowUserModel flowUserModel = new FlowUserModel();
			flowUserModel.setUserId(UserInfoVo.getId());
			flowUserModel.setName(UserInfoVo.getUserName());
			flowUserModel.setDeptId(deptId);
			flowUserModel.setDeptName(UserInfoVo.getDeptName());
			list.add(flowUserModel);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FlowUserModel> selectUserByRoleId(String roleId) {
		if (StrUtil.isEmpty(roleId)) {
			return null;
		}
		ListBean listBean = userService.listUserInfoByRoleId(roleId, 1, Integer.MAX_VALUE);
		List<UserInfoVo> userVoList = (List<UserInfoVo>) listBean.getList();
		List<FlowUserModel> list = new ArrayList<FlowUserModel>();
		for (UserInfoVo userInfoVo : userVoList) {
			list.add(new FlowUserModel(userInfoVo.getId(), userInfoVo.getUserName(), "01", userInfoVo.getDeptId(),
					userInfoVo.getDeptName()));
		}
		return list;
	}

	@Override
	public List<FlowUserModel> deptUserByUser(String userId) {
		if (StrUtil.isEmpty(userId)) {
			return null;
		}
		List<UserInfoVo> uList = userService.listDeptUsersByUserId(userId);
		List<FlowUserModel> list = new ArrayList<FlowUserModel>();
		for (UserInfoVo userInfoVo : uList) {
			list.add(new FlowUserModel(userInfoVo.getId(), userInfoVo.getUserName(), "01", userInfoVo.getDeptId(),
					userInfoVo.getDeptName()));
		}
		return list;
	}

	//
	@Override
	public List<FlowUserModel> allDeptUserByUser(String userId) {
		if (StrUtil.isEmpty(userId)) {
			return null;
		}
		List<UserInfoVo> uList = userService.listParentsDeptUsersByUserId(userId);
		List<FlowUserModel> list = new ArrayList<FlowUserModel>();
		for (UserInfoVo userInfoVo : uList) {
			list.add(new FlowUserModel(userInfoVo.getId(), userInfoVo.getUserName(), "01", userInfoVo.getDeptId(),
					userInfoVo.getDeptName()));
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FlowUserModel> selectUserList() {
		ListBean listBean = userService.getAllUser(1, Integer.MAX_VALUE);
		List<UserInfoVo> uList = (List<UserInfoVo>) listBean.getList();
		List<FlowUserModel> list = new ArrayList<FlowUserModel>();
		for (UserInfoVo userInfoVo : uList) {
			list.add(new FlowUserModel(userInfoVo.getId(), userInfoVo.getUserName(), "01", userInfoVo.getDeptId(),
					userInfoVo.getDeptName()));
		}
		return list;
	}

	@Override
	public List<String> getUserMap(String id) {
		List<String> userList = new ArrayList<>();
		if (StrUtil.isEmpty(id)) {
			return userList;
		}
		userList.add(id);
		UserInfoVo userInfoVo = userService.selectByPrimaryKey(id);
		if (userInfoVo != null) {
			List<UserRoleVo> roleList = userInfoVo.getuRoleVoList();

			if (roleList != null && roleList.size() > 0) {
				for (UserRoleVo userRoleVo : roleList) {
					String roleId = userRoleVo.getRoleId();
					userList.add(roleId);
				}
			}
			List<UserDeptVo> deptList = userInfoVo.getuDeptVoList();
			if (deptList != null && deptList.size() > 0) {
				for (UserDeptVo userDeptVo : deptList) {
					String deptId = userDeptVo.getDeptId();
					userList.add(deptId);
				}
			}
		}
		return userList;
	}

}
