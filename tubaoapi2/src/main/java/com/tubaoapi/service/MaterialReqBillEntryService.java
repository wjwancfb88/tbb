package com.tubaoapi.service;

import com.tubaoapi.dao.MaterialReqBillDao;
import com.tubaoapi.dao.MaterialReqBillEntryDao;
import com.tubaoapi.model.MaterialReqBill;
import com.tubaoapi.model.MaterialReqBillEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialReqBillEntryService {


	@Autowired
	private MaterialReqBillEntryDao materialReqBillEntryDao;


	public Integer  insertMaterialReqBillEntry(MaterialReqBillEntry entry) {
		Integer  fid=materialReqBillEntryDao.insertSelective(entry);
		return fid;
	}

	
}
