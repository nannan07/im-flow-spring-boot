package com.allmsi.flow.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowInstanceDealMapper;
import com.allmsi.flow.dao.FlowInstanceLogMapper;
import com.allmsi.flow.dao.FlowInstanceMapper;
import com.allmsi.flow.dao.FlowInstanceOpinionMapper;
import com.allmsi.flow.dao.FlowInstanceStateMapper;
import com.allmsi.flow.model.FlowInstance;
import com.allmsi.flow.model.OperationMenu;
import com.allmsi.flow.model.ivo.FlowInstanceIVO;
import com.allmsi.flow.model.ovo.FlowInstanceOVO;
import com.allmsi.flow.model.ovo.SubGuide;
import com.allmsi.flow.reflection.FlowUserServiceReflection;
import com.allmsi.flow.service.FlowInstanceService;
import com.allmsi.flow.service.FlowInstenceEngine;
import com.allmsi.sys.util.StrUtil;

/**
 * 流程实例引擎
 * 
 * @author sunnannan
 *
 */
@Service
public class FlowInstenceEngineImpl implements FlowInstenceEngine {
	

	@Resource
	private FlowUserServiceReflection flowUserServiceReflection;

	@Resource
	private FlowInstanceService flowInstanceService;
	
	@Resource
	private FlowInstanceDealMapper flowInstanceDealDao;

	@Resource
	private FlowInstanceLogMapper flowInstanceLogDao;
	
	@Resource
	private FlowInstanceMapper flowInstanceDao;
	
	@Resource
	private FlowInstanceStateMapper flowInstanceStateDao;
	
	@Resource
	private FlowInstanceOpinionMapper flowInstanceOpinionDao;

	/**
	 * 添加流程实例
	 * 
	 * @param flowInstanceIVo
	 * @return
	 */
	public String addFlowInstance(FlowInstanceIVO flowInstanceIVo) {
		return flowInstanceService.insertFlowInstance(flowInstanceIVo);
	}

	@Override
	public SubGuide flowInstanceEngine(String objId, String instanceId) {
		if (StrUtil.isEmpty(objId) && StrUtil.isEmpty(instanceId)) {
			return new SubGuide(OperationMenu.ERROR, "业务ID和实例ID不可以同时为空");
		}
		if (StrUtil.isEmpty(objId) && StrUtil.notEmpty(instanceId)) {
			return new SubGuide(OperationMenu.SUCCESS, "", instanceId);
		}
		if (StrUtil.notEmpty(objId) && StrUtil.isEmpty(instanceId)) {
			return new SubGuide(OperationMenu.SUCCESS, "新建实例", instanceId);
		}
		List<String> flowInstanceList = flowInstanceService.listInstanceByObjId(objId);// 存在流程实例
		if (flowInstanceList != null && flowInstanceList.size() > 0) {
			int size = flowInstanceList.size();
			if (size == 1) {
				String flowInstanceId = flowInstanceList.get(0);
//				if (flowInstanceId.equals(instanceId)) {// 业务实例ID和传入的ID不同时，非法请求
					return new SubGuide(OperationMenu.SUCCESS, "", flowInstanceId);
//				}
			}
		}
		return new SubGuide(OperationMenu.ERROR, "实例异常请联系管理员！","");
	}

	@Override
	public String updateFlowInstanceDratf(String instanceId) {
		if (StrUtil.isEmpty(instanceId)) {
			return "";
		}
		return flowInstanceService.updateFlowInstanceDratf(instanceId);
	}
	
	@Override
	public String deleteInstence(String userId, String objId) {
		List<FlowInstance> list = flowInstanceDao.listInstanceByObjId(objId);
		if (list != null && list.size() > 0) {
			for (FlowInstance flowInstance : list) {
				if (0 == flowInstance.getDraft()) {// 草稿
					String instenceId = flowInstance.getId();
					int count = flowInstanceDao.deleteById(instenceId);
					if (count == 0) {
						return "";
					}
					flowInstanceStateDao.deleteByInstenceId(instenceId);
					flowInstanceOpinionDao.deleteByInstenceLogId(instenceId);
					flowInstanceDealDao.deleteByInstenceId(instenceId);
					flowInstanceLogDao.deleteByInstenceId(instenceId);
					return objId;
				}
			}
		}
		return null;
	}
	

	@Override
	public FlowInstanceOVO getFlowInstanceOVO(String instanceId) {
		if (StrUtil.isEmpty(instanceId)) {
			return null;
		}
		FlowInstance flowInstance = flowInstanceDao.selectByPrimaryKey(instanceId);
		FlowInstanceOVO flowInstanceOVo = new FlowInstanceOVO(flowInstance);
		if (flowInstance != null) {
			flowInstanceOVo.setUser(
					flowUserServiceReflection.getFlowExternalService().selectUserInfo(flowInstance.getcUserId()));
		}
		return flowInstanceOVo;
	}
}
