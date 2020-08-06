package com.tubaoapi.dao;


import com.tubaoapi.model.Inventory;
import com.tubaoapi.model.KDBdMultiMeasureUnit;
import com.tubaoapi.model.so.KDBdMultiMeasureUnitSO;
import com.tubaoapi.modules.persistence.dao.BaseDao;

import java.util.List;


public interface KDBdMultiMeasureUnitDao extends BaseDao<KDBdMultiMeasureUnit, String> {
	List<KDBdMultiMeasureUnit> findBySO(KDBdMultiMeasureUnitSO so);

}
