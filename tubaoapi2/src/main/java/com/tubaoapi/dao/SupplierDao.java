package com.tubaoapi.dao;

import com.tubaoapi.model.Supplier;
import com.tubaoapi.modules.persistence.dao.BaseDao;

public interface SupplierDao extends BaseDao<Supplier, String> {

	Supplier getByNumber(String number);

}
