package com.tubaoapi.dao;

import com.tubaoapi.model.Material;
import com.tubaoapi.model.so.MaterialSO;
import com.tubaoapi.modules.persistence.dao.BaseDao;
import com.tubaoapi.modules.persistence.pagination.model.PageRowBounds;

import java.util.List;

public interface MaterialDao extends BaseDao<Material, String> {

	Material getByNumber(String number);

	List<Material> findByGroup(MaterialSO so,PageRowBounds pageRowBounds);
	List<String> findBySupplier(MaterialSO so,PageRowBounds pageRowBounds);
}
