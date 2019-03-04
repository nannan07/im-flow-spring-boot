package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowInstanceLogMapper;
import com.allmsi.flow.dao.FlowInstanceStateMapper;
import com.allmsi.flow.model.FlowInstanceLog;
import com.allmsi.flow.model.FlowInstanceState;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ovo.FlowStateLogOVO;
import com.allmsi.flow.reflection.FlowUserServiceReflection;
import com.allmsi.flow.service.FlowInstenceLogEngine;
import com.allmsi.flow.util.LogStatusUtil;
import com.allmsi.flow.util.NodeTypeUtil;
import com.allmsi.sys.util.StrUtil;
import com.github.pagehelper.PageInfo;

@Service
public class FlowInstenceLogEngineImpl implements FlowInstenceLogEngine {

	@Resource
	private FlowInstanceLogMapper flowInstanceLogDao;

	@Resource
	private FlowInstanceStateMapper flowInstanceStateDao;

	@Resource
	private FlowUserServiceReflection flowUserServiceReflection;

	@Override
	public String insertFlowInstenceLog(FlowInstanceState fs, String remark, String dealState) {
		FlowInstanceLog fil = new FlowInstanceLog(fs);
		fil.setRemark(remark);
		fil.setDealState(dealState);
		int msg = flowInstanceLogDao.insertSelective(fil);// 添加已办
		Integer isProxy = fs.getIsProxy();
		if (isProxy != null && 1 == isProxy) {// 代理办理
			String proxyId = fs.getProxyId();// 被代理的人
			// 删除待办表中的被代理人信息
			FlowInstanceState fs1 = new FlowInstanceState();
			fs1.setInstanceId(fs.getInstanceId());
			fs1.setNodeId(fs.getNodeId());
			fs1.setRouteId(fs.getRouteId());
			fs1.setPreDealId(fs.getPreDealId());
			fs1.setSufDealId(proxyId);
			fs1.setSufDealType("01");
			flowInstanceStateDao.deleteBantchByFis(fs1);
		} else {// 授权人
			FlowInstanceState fs1 = new FlowInstanceState();
			fs1.setInstanceId(fs.getInstanceId());
			fs1.setNodeId(fs.getNodeId());
			fs1.setRouteId(fs.getRouteId());
			fs1.setPreDealId(fs.getPreDealId());
			fs1.setProxyId(fs.getSufDealId());
			fs1.setSufAuthType(fs.getSufAuthType());
			flowInstanceStateDao.deleteBantchByFis(fs1);
		}
		return (msg == 0) ? "" : fil.getId();
	}

	@Override
	public String insertFlowInstenceLog(FlowInstanceLog record) {
		int msg = flowInstanceLogDao.insertSelective(record);
		return (msg == 0) ? "" : record.getId();
	}

	@Override
	public FlowInstanceLog getDoneInfo(String instanceId, String nodeId, String sufDealId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("instanceId", instanceId);
		map.put("nodeId", nodeId);
		map.put("sufDealId", sufDealId);
		FlowInstanceLog flowInstanceLog = flowInstanceLogDao.getDoneInfo(map);
		return flowInstanceLog;
	}

	@Override
	public boolean insertBantch(List<FlowInstanceLog> list) {
		int msg = 0;
		if (list != null && list.size() > 0) {// 添加principal_log
			msg = flowInstanceLogDao.insertBantch(list);
		}
		return (msg == 0) ? false : true;
	}

	@Override
	public List<FlowStateLogOVO> listFlowLog(String instanceId) {
		List<FlowStateLogOVO> logList = new ArrayList<FlowStateLogOVO>();
		List<FlowInstanceLog> list = flowInstanceLogDao.listInstenceStateLog(instanceId);
		PageInfo<FlowInstanceLog> pageInfo = new PageInfo<FlowInstanceLog>(list);
		int total = (int) pageInfo.getTotal();
		int agreeCount = 0;
		int disAgreeCount = 0;
		int editCount = 1;
		if (list != null && list.size() > 0) {
			FlowStateLogOVO indexVote = null;

			// 开始节点
			FlowInstanceLog flowInstanceLog = list.get(0);
			FlowStateLogOVO vo = new FlowStateLogOVO(flowInstanceLog);
			String startNodeId = vo.getNodeId();
			vo.setDealState(LogStatusUtil.START);
			vo.setDealStateName(LogStatusUtil.START_MSG);
			logList.add(vo);
			for (int i = 1; i < total; i++) {
				FlowInstanceLog fil = list.get(i);
				String nodeId = fil.getNodeId();
				String nodeType = fil.getNodeType();
				String dealState = fil.getDealState();
				if (NodeTypeUtil.MULTI_PERSON_VOTE_WHOLE.equals(nodeType)) {// 多人完全投票
					indexVote = new FlowStateLogOVO(fil);
					indexVote.setSufDealName("");
					if (LogStatusUtil.AGREE.equals(dealState)) {
						agreeCount += 1;
					} else if (LogStatusUtil.DISAGREE.equals(dealState)) {
						disAgreeCount += 1;
					}
					if (i == total - 1) {// 投票中
						indexVote.setDealState(LogStatusUtil.VOTE_DOING);
						indexVote.setDealStateName(LogStatusUtil.VOTE_DOING_MSG);
						logList.add(indexVote);
						agreeCount = 0;
						disAgreeCount = 0;
					}
				} else {
					FlowStateLogOVO index = new FlowStateLogOVO(fil);
					if (startNodeId.equals(nodeId)) {// 修改
						index.setDealState(LogStatusUtil.CHANGE);
						index.setDealStateName(LogStatusUtil.CHANGE_MSG.replace("n", String.valueOf(editCount)));
						editCount = editCount + 1;
					} else {
						if (StrUtil.notEmpty(dealState)) {
							switch (dealState) {
							case LogStatusUtil.AGREE:// 同意
								index.setDealStateName(LogStatusUtil.AGREE_MSG);
								break;
							case LogStatusUtil.DISAGREE:// 拒绝
								index.setDealStateName(LogStatusUtil.DISAGREE_MSG);
								break;
							case LogStatusUtil.TRANSFER:// 转交
								index.setDealStateName(LogStatusUtil.TRANSFER_MSG);
								break;
							case LogStatusUtil.RECAL:// 撤回
								index.setDealStateName(LogStatusUtil.RECAL_MSG);
								break;
							case LogStatusUtil.DOING:// 审核中
								index.setDealStateName(LogStatusUtil.DOING_MSG);
								break;
							case LogStatusUtil.END:// 结束
								index.setDealStateName(LogStatusUtil.END_MSG);
								break;
							default:
								index.setDealStateName(LogStatusUtil.OTHER_MSG);
								break;
							}
						}
					}
					if (indexVote != null) {
						indexVote.setSufDealName("");
						indexVote.setOpinion("");
						if (agreeCount > disAgreeCount) {// 投票通过
							indexVote.setDealState(LogStatusUtil.VOTE_PASS);
							indexVote.setDealStateName(LogStatusUtil.VOTE_PASS_MSG);
							logList.add(indexVote);
						} else {// 投票不通过
							indexVote.setDealState(LogStatusUtil.VOTE_REFUSE);
							indexVote.setDealStateName(LogStatusUtil.VOTE_REFUSE_MSG);
							logList.add(indexVote);
						}
						agreeCount = 0;
						disAgreeCount = 0;
						indexVote = null;
					}
					logList.add(index);
				}
			}
		}
		return logList;
	}

	@Override
	public List<String> listVote(String instanceId, String nodeId) {
		if (StrUtil.isEmpty(instanceId) || StrUtil.isEmpty(nodeId)) {
			return null;
		}
		Map<String, String> map = new HashMap<>();
		map.put("instanceId", instanceId);
		map.put("nodeId", nodeId);
		List<String> list = flowInstanceLogDao.listVoteDealState(map);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public boolean updateDealState(String instanceId, String nodeId, String sufDealId, String dealState) {
		if (StrUtil.isEmpty(instanceId) || StrUtil.isEmpty(nodeId) || StrUtil.isEmpty(dealState)) {
			return false;
		}
		Map<String, String> map = new HashMap<>();
		map.put("instanceId", instanceId);
		map.put("nodeId", nodeId);
		map.put("sufDealId", sufDealId);
		map.put("dealState", dealState);
		int msg = flowInstanceLogDao.updateDeatState(map);
		return (msg == 0) ? false : true;
	}

	@Override
	public List<FlowStateLogOVO> listVote(String id) {
		if (StrUtil.isEmpty(id)) {
			return null;
		}
		List<FlowStateLogOVO> list = new ArrayList<FlowStateLogOVO>();
		List<FlowInstanceLog> flowInstanceLogList = flowInstanceLogDao.listVote(id);
		if (flowInstanceLogList != null && flowInstanceLogList.size() > 0) {
			List<String> userIds = new ArrayList<String>();
			Map<String, FlowInstanceLog> mapLog = new HashMap<String, FlowInstanceLog>();
			for (FlowInstanceLog flowInstanceLog : flowInstanceLogList) {
				userIds.add(flowInstanceLog.getSufDealId());
				mapLog.put(flowInstanceLog.getSufDealId(), flowInstanceLog);
			}
			if (userIds != null && userIds.size() > 0) {
				List<FlowUserModel> fums = flowUserServiceReflection.getFlowExternalService().FlowUserInfoList(userIds);
				if (fums != null && fums.size() > 0) {
					for (FlowUserModel flowUserModel : fums) {
						String sufDealName = flowUserModel.getName();
						String key = flowUserModel.getUserId();
						FlowInstanceLog flowInstanceLog = mapLog.get(key);
						String dealState = flowInstanceLog.getDealState();
						String dealStateName = "";
						if (StrUtil.notEmpty(dealState)) {
							switch (dealState) {
							case LogStatusUtil.AGREE:// 同意
								dealStateName = LogStatusUtil.AGREE_MSG;
								break;
							case LogStatusUtil.DISAGREE:// 拒绝
								dealStateName = "不同意";
								break;
							default:
								break;
							}
						}
						list.add(new FlowStateLogOVO(flowInstanceLog.getInstanceId(), flowInstanceLog.getId(),
								sufDealName, dealState, dealStateName, flowInstanceLog.getDealTime(),
								flowInstanceLog.getOpinion(), flowInstanceLog.getNodeId()));
					}
				}
			}
		}
		return list;
	}
}
