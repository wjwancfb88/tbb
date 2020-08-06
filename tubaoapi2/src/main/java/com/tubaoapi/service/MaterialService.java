package com.tubaoapi.service;

import com.tubaoapi.modules.persistence.pagination.model.Page;
import com.tubaoapi.modules.persistence.pagination.model.PageRequest;
import com.tubaoapi.modules.persistence.pagination.model.PageRowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tubaoapi.dao.MaterialDao;
import com.tubaoapi.model.Material;
import com.tubaoapi.model.so.BaseSO;
import com.tubaoapi.model.so.MaterialSO;
import com.tubaoapi.modules.persistence.dao.BaseDao;
import com.tubaoapi.modules.persistence.service.BaseService;

import java.util.List;

@Service
public class MaterialService extends BaseService<Material, String>{


	@Autowired
	private MaterialDao materialDao;



	public Material getByNumber(String number) {
		return materialDao.getByNumber(number);
	}


	@Override
	public BaseDao<Material, String> getDao() {
		return materialDao;
	}


	@Override
	public BaseSO<String> newSO() {
		return new MaterialSO();
	}

	public Page<Material> findByGroup(MaterialSO so,PageRequest pageRequest) {
		PageRowBounds rowBounds = new PageRowBounds(pageRequest);
		List<Material> l = materialDao.findByGroup(so, rowBounds);
		return new Page<Material>(rowBounds, l);
	}
	public Page<String> findBySupplier(MaterialSO so,PageRequest pageRequest) {
		PageRowBounds rowBounds = new PageRowBounds(pageRequest);
		List<String> l = materialDao.findBySupplier(so,rowBounds);
		return new Page<String>(rowBounds, l);
	}
	
}
