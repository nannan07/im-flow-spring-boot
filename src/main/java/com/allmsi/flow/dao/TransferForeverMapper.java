package com.allmsi.flow.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.TransferForever;

public interface TransferForeverMapper {

	public int insertSelective(TransferForever record);

	public List<TransferForever> listPrincipal(Map<String, Object> map);
}
