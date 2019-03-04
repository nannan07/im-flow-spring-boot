package com.allmsi.flow.reflection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.allmsi.flow.third.service.FlowExternalUserService;
import com.allmsi.sys.config.SpringContextRegister;
import com.allmsi.sys.util.StrUtil;

@Component
public class FlowUserServiceReflection {

	@Value("${im.flow.userList.value:defaultFlowExternalUserService}")
	private String imFlowUserList;

	@Autowired
	private SpringContextRegister springContextRegister;

	private final String DEFAULT_FLOW_SERVICE = "defaultFlowExternalUserService";

	public FlowExternalUserService getFlowExternalService() {
		FlowExternalUserService flowExternalService = null;
		String className = imFlowUserList;
		if (StrUtil.isEmpty(className)) {
			flowExternalService = springContextRegister.getServiceImpl(FlowExternalUserService.class,
					DEFAULT_FLOW_SERVICE);
		} else {
			flowExternalService = springContextRegister.getServiceImpl(FlowExternalUserService.class, className);
		}
		return flowExternalService;
	}

}
