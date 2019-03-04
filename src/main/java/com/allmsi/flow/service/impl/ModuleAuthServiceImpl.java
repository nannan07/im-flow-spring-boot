package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.ModuleAuthMapper;
import com.allmsi.flow.model.FlowModuleAuth;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.reflection.FlowUserServiceReflection;
import com.allmsi.flow.service.ModuleAuthService;
import com.allmsi.sys.util.StrUtil;

@Service
public class ModuleAuthServiceImpl implements ModuleAuthService {

	@Resource
	private ModuleAuthMapper moduleAuthDao;

	@Resource
	private FlowUserServiceReflection flowUserServiceReflection;

	@Override
	public List<FlowUserModel> listModuleAuth(String flowId) {
		if (StrUtil.isEmpty(flowId)) {
			return null;
		}
		List<FlowUserModel> moduleAuthList = new ArrayList<>();
		List<FlowModuleAuth> authList = moduleAuthDao.listModuleAuth(flowId);
		if (authList != null && authList.size() > 0) {
			for (FlowModuleAuth flowModuleAuth : authList) {
				String authId = flowModuleAuth.getAuthId();
				String authType = flowModuleAuth.getAuthType();
				Set<String> userids = new HashSet<String>();
				if (StrUtil.notEmpty(authType)) {
					switch (authType) {
					case "01":// 查询用户
						FlowUserModel u = flowUserServiceReflection.getFlowExternalService().selectUserInfo(authId);
						if (u != null) {
							userids.add(u.getUserId());
							moduleAuthList.add(u);
						}
						break;
					case "02":// 查询部门
						List<FlowUserModel> dl = flowUserServiceReflection.getFlowExternalService()
								.selectUserByDeptId(authId);
						if (dl != null && dl.size() > 0) {
							for (FlowUserModel flowUserModel : dl) {
								if (!userids.contains(flowUserModel.getUserId())) {
									userids.add(flowUserModel.getUserId());
									moduleAuthList.add(flowUserModel);
								}
							}
						}
						break;
					case "03":// 查询角色
						List<FlowUserModel> rl = flowUserServiceReflection.getFlowExternalService()
								.selectUserByRoleId(authId);
						if (rl != null && rl.size() > 0) {
							for (FlowUserModel flowUserModel : rl) {
								if (!userids.contains(flowUserModel.getUserId())) {
									userids.add(flowUserModel.getUserId());
									moduleAuthList.add(flowUserModel);
								}
							}
						}
						break;
					}
				}
			}
		}
		return moduleAuthList;
	}

	@Override
	public boolean isAdmin(String flowId, String userId) {
		if (StrUtil.isEmpty(userId)) {
			return false;
		}
		List<FlowUserModel> moduleAuthList = listModuleAuth(flowId);
		if (moduleAuthList != null && moduleAuthList.size() > 0) {
			for (FlowUserModel flowUserModel : moduleAuthList) {
				if (userId.equals(flowUserModel.getUserId())) {
					return true;
				}
			}
		}
		return false;
	}
}
