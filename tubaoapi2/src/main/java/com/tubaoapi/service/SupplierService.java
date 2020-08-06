package com.tubaoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tubaoapi.dao.SupplierDao;
import com.tubaoapi.model.Supplier;
import com.tubaoapi.model.so.BaseSO;
import com.tubaoapi.model.so.SupplierSO;
import com.tubaoapi.modules.persistence.dao.BaseDao;
import com.tubaoapi.modules.persistence.service.BaseService;

@Service
public class SupplierService extends BaseService<Supplier, String>{

	@Autowired
	private SupplierDao userDao;



	public Supplier getByNumber(String number) {
		return userDao.getByNumber(number);
	}


	@Override
	public BaseDao<Supplier, String> getDao() {
		return userDao;
	}
	

	@Override
	public BaseSO<String> newSO() {
		return new SupplierSO();
	}
	
	
	
}
