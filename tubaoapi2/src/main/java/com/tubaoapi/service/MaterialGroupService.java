package com.tubaoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tubaoapi.dao.MaterialGroupDao;
import com.tubaoapi.model.MaterialGroup;
import com.tubaoapi.model.so.BaseSO;
import com.tubaoapi.model.so.MaterialGroupSO;
import com.tubaoapi.modules.persistence.dao.BaseDao;
import com.tubaoapi.modules.persistence.service.BaseService;

@Service
public class MaterialGroupService extends BaseService<MaterialGroup, String>{


	@Autowired
	private MaterialGroupDao materialGroupDao;


	@Override
	public BaseDao<MaterialGroup, String> getDao() {
		return materialGroupDao;
	}
	

	@Override
	public BaseSO<String> newSO() {
		return new MaterialGroupSO();
	}
	
	
	
}
