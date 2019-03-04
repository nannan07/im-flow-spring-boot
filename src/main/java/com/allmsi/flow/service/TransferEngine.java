package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.ivo.NodeDealIVO;

public interface TransferEngine {

	List<NodeDealIVO> selectTransferUser(String flowCode, String nodeId, String principal);

}
