package com.tubaoapi.service;

import com.tubaoapi.dao.KDBdMultiMeasureUnitDao;
import com.tubaoapi.dao.ManufactureRecBillEntryDao;
import com.tubaoapi.model.Inventory;
import com.tubaoapi.model.KDBdMultiMeasureUnit;
import com.tubaoapi.model.ManufactureRecBillEntry;
import com.tubaoapi.model.so.BaseSO;
import com.tubaoapi.model.so.KDBdMultiMeasureUnitSO;
import com.tubaoapi.modules.persistence.dao.BaseDao;
import com.tubaoapi.modules.persistence.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KDBdMultiMeasureUnitService extends BaseService<KDBdMultiMeasureUnit, String> {


	@Autowired
	private KDBdMultiMeasureUnitDao kdBdMultiMeasureUnitDao;


	@Override
	public BaseDao<KDBdMultiMeasureUnit, String> getDao() {
		return kdBdMultiMeasureUnitDao;
	}

	@Override
	public BaseSO<String> newSO() {
		return new KDBdMultiMeasureUnitSO();
	}
}
