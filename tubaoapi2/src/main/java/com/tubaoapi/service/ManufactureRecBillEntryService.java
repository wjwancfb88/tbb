package com.tubaoapi.service;

import com.tubaoapi.dao.ManufactureRecBillDao;
import com.tubaoapi.dao.ManufactureRecBillEntryDao;
import com.tubaoapi.model.ManufactureRecBill;
import com.tubaoapi.model.ManufactureRecBillEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManufactureRecBillEntryService {


	@Autowired
	private ManufactureRecBillEntryDao manufactureRecBillEntryDao;


	public Integer  insertManufactureRecBillentry(ManufactureRecBillEntry entry) {
		Integer  fid=manufactureRecBillEntryDao.insertSelective(entry);
		return fid;
	}

	
}
