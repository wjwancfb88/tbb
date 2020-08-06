package com.tubaoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tubaoapi.dao.MeasureUnitDao;
import com.tubaoapi.model.MeasureUnit;
import com.tubaoapi.model.so.BaseSO;
import com.tubaoapi.model.so.MeasureUnitSO;
import com.tubaoapi.modules.persistence.dao.BaseDao;
import com.tubaoapi.modules.persistence.service.BaseService;

@Service
public class MeasureUnitService extends BaseService<MeasureUnit, String>{

	@Autowired
	private MeasureUnitDao measureUnitDao;



	@Override
	public BaseDao<MeasureUnit, String> getDao() {
		return measureUnitDao;
	}
	

	@Override
	public BaseSO<String> newSO() {
		return new MeasureUnitSO();
	}
	
	
	
}
