package com.allmsi.flow.service;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.TransferForever;
import com.allmsi.flow.model.ivo.TransferForeverIVO;

public interface TransferForeverService {

	public boolean insertSelective(TransferForeverIVO record);

	public List<TransferForever> listAgentsForPrincipal(Map<String, Object> map);
	
	public List<String> listtPrincipalByAgent(Map<String, Object> map);
	
}
