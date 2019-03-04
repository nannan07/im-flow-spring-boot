package com.allmsi.flow.reflection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.allmsi.flow.third.service.FlowExternalNodeDealUserService;
import com.allmsi.sys.config.SpringContextRegister;
import com.allmsi.sys.util.StrUtil;

@Component
public class FlowNodeDealUserRegisterQueryReflection {

	@Value("${im.flow.node.deal.value:defaultFlowNodeDealUserRegisterQuery}")
	private String imFlowUserList;

	private final String DEFAULT_FLOW_SERVICE = "defaultFlowNodeDealUserRegisterQuery";

	@Autowired
	private SpringContextRegister springContextRegister;

	public FlowExternalNodeDealUserService getFlowNodeDealUserService() {
		FlowExternalNodeDealUserService flowNodeDealUserService = null;
		String className = imFlowUserList;
		if (StrUtil.isEmpty(className)) {
			flowNodeDealUserService = springContextRegister.getServiceImpl(FlowExternalNodeDealUserService.class,
					DEFAULT_FLOW_SERVICE);
		} else {
			flowNodeDealUserService = springContextRegister.getServiceImpl(FlowExternalNodeDealUserService.class,
					className);
		}
		return flowNodeDealUserService;
	}
}
