package com.tubaoapi.dao;

import com.tubaoapi.model.MaterialGroup;
import com.tubaoapi.modules.persistence.dao.BaseDao;

public interface MaterialGroupDao extends BaseDao<MaterialGroup, String> {

	MaterialGroup getByNumber(String number);

}
