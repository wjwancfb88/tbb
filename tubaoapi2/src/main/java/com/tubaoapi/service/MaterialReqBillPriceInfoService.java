package com.tubaoapi.service;

import com.tubaoapi.dao.MaterialReqBillEntryDao;
import com.tubaoapi.dao.MaterialReqBillPriceInfoDao;
import com.tubaoapi.model.MaterialReqBillEntry;
import com.tubaoapi.model.MaterialReqBillPriceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialReqBillPriceInfoService {


	@Autowired
	private MaterialReqBillPriceInfoDao materialReqBillPriceInfoDao;


	public Integer  insertMaterialReqBillPriceInfo(MaterialReqBillPriceInfo info) {
		Integer  fid=materialReqBillPriceInfoDao.insertSelective(info);
		return fid;
	}

	
}
