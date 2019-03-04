package com.allmsi.flow.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.TransferInterim;

public interface TransferInterimMapper {

	public int insertSelective(TransferInterim record);

	public int deleteByParemerKey(Map<String,Object> map);

	public List<TransferInterim> listByPrincipal(Map<String,Object> map);

}
