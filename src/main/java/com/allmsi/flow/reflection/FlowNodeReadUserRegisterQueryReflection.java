package com.allmsi.flow.reflection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.allmsi.flow.third.service.FlowExternalNodeReadUserService;
import com.allmsi.sys.config.SpringContextRegister;
import com.allmsi.sys.util.StrUtil;

@Component
public class FlowNodeReadUserRegisterQueryReflection {

	@Value("${im.flow.node.read.value:defaultFlowNodeReadUserRegisterQuery}")
	private String imFlowUserList;

	private final String DEFAULT_FLOW_SERVICE = "defaultFlowNodeReadUserRegisterQuery";

	@Autowired
	private SpringContextRegister springContextRegister;

	public FlowExternalNodeReadUserService getFlowNodeReadUserService() {
		FlowExternalNodeReadUserService flowNodeReadUserService = null;
		String className = imFlowUserList;
		if (StrUtil.isEmpty(className)) {
			flowNodeReadUserService = springContextRegister.getServiceImpl(FlowExternalNodeReadUserService.class,
					DEFAULT_FLOW_SERVICE);
		} else {
			flowNodeReadUserService = springContextRegister.getServiceImpl(FlowExternalNodeReadUserService.class,
					className);
		}
		return flowNodeReadUserService;
	}

}
