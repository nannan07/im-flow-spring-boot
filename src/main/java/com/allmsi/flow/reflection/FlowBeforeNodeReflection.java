package com.allmsi.flow.reflection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.allmsi.flow.third.service.FlowExternalBeforeNodeService;
import com.allmsi.sys.config.SpringContextRegister;
import com.allmsi.sys.util.StrUtil;

@Component
public class FlowBeforeNodeReflection {
	
	@Value("${im.flow.endofcc.value:defaultFlowBeforeNodeService}")
	private String imFlowUserList;

	private final String DEFAULT_FLOW_SERVICE = "defaultFlowBeforeNodeService";

	@Autowired
	private SpringContextRegister springContextRegister;

	public FlowExternalBeforeNodeService getFlowBeforeNodeReflection() {
		FlowExternalBeforeNodeService flowEndOfCCService = null;
		String className = imFlowUserList;
		if (StrUtil.isEmpty(className)) {
			flowEndOfCCService = springContextRegister.getServiceImpl(FlowExternalBeforeNodeService.class,
					DEFAULT_FLOW_SERVICE);
		} else {
			flowEndOfCCService = springContextRegister.getServiceImpl(FlowExternalBeforeNodeService.class, className);
		}
		return flowEndOfCCService;
	}


}
