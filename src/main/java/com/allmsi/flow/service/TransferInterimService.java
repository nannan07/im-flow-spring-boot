package com.allmsi.flow.service;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.TransferInterim;
import com.allmsi.flow.model.ivo.TransferInterimIVO;

public interface TransferInterimService {
	
	public boolean insertSelective(TransferInterimIVO record);

	public List<TransferInterim> listAgentsForPrincipal(Map<String, Object> map);

	public boolean deleteById(String id, String userId);

}
