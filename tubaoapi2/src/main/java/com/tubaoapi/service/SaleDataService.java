package com.tubaoapi.service;

import com.tubaoapi.dao.CustomerDao;
import com.tubaoapi.dao.SaleDataDao;
import com.tubaoapi.model.Customer;
import com.tubaoapi.model.SaleData;
import com.tubaoapi.model.so.BaseSO;
import com.tubaoapi.model.so.CustomerSO;
import com.tubaoapi.model.so.SaleDataSO;
import com.tubaoapi.modules.persistence.dao.BaseDao;
import com.tubaoapi.modules.persistence.pagination.model.Page;
import com.tubaoapi.modules.persistence.pagination.model.PageRequest;
import com.tubaoapi.modules.persistence.pagination.model.PageRowBounds;
import com.tubaoapi.modules.persistence.service.BaseService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
public class SaleDataService extends BaseService<SaleData, String>{
	private final org.slf4j.Logger logger= LoggerFactory.getLogger(SaleDataService.class);

	@Autowired
	private SaleDataDao saleDao;


	private Map<String,SaleData> allListToMap(List<SaleData> allList){
		Map<String,SaleData> map = new HashMap<>();
		for(SaleData aData:allList){
			map.put(aData.getId(),aData);
			aData.setaTotalAmount(aData.getAmount());//A类总销售额
		}
		return map;
	}

	private void margeBToAll(List<SaleData> allList,Map<String,SaleData> allMap,List<SaleData>... lists ){
		for(List<SaleData> list:lists){
			for(SaleData aData:list){
				if(allMap.get(aData.getId())==null){
					allList.add(aData);
					allMap.put(aData.getId(),aData);
				}
			}
		}
	}

	private void setData(Map<String,SaleData> allMap,List<SaleData> l , String type){
		for(SaleData aData:l){
			SaleData data = allMap.get(aData.getId());
			if(data!=null){
				if(TYPE_BC.equals(type)){
					data.setBc(aData.getAmount());
					data.setBcQty(aData.getQty());
				}else if(TYPE_WJ.equals(type)){
					data.setWj(aData.getAmount());
				}else if(TYPE_JNJ.equals(type)){
					data.setJnj(aData.getAmount());
				}else if(TYPE_TL.equals(type)){
					data.setTl(aData.getAmount());
				}else if(TYPE_QZ.equals(type)){
					data.setQz(aData.getAmount());
				}else if(TYPE_SXB.equals(type)){
					data.setSxb(aData.getAmount());
					data.setSxbQty(aData.getQty());
				}else if(TYPE_B_OEM.equals(type)){
					data.setbOem(aData.getAmount());
					data.setbTotalAmount(aData.getAmount()*16);
				}
//				else if(TYPE_B_YZ.equals(type)){
//					data.setbYzOem(aData.getAmount());
//					System.out.println(aData.getAmount());
//					data.setbYzTotalAmount(aData.getAmount()*16);
//				}
				else if(TYPE_YZ_PT.equals(type)){
					data.setYzPtTotalAmount(aData.getAmount());
				}
			}
		}
	}

	private static String TYPE_BC = "bc";
	private static String TYPE_WJ = "wj";
	private static String TYPE_JNJ = "jnj";
	private static String TYPE_TL = "tl";
	private static String TYPE_QZ = "qz";
	private static String TYPE_SXB = "sxb";
	private static String TYPE_B_OEM = "b_oem";
//	private static String TYPE_B_YZ = "b_yz";//暂时又不用了
	private static String TYPE_YZ_PT = "yz_pt";



	public List<SaleData> findSaleA(SaleDataSO so) {
		List<SaleData> allList =saleDao.findSaleA(so);
		Map<String,SaleData> allMap = allListToMap(allList);

		so.setNumber("11%");
		List<SaleData> l_bc = saleDao.findSaleA(so);

		so.setNumber("19%");
		List<SaleData> l_wj = saleDao.findSaleA(so);

		so.setNumber("200!2003%");
		List<SaleData> l_jnj = saleDao.findSaleA(so);

		so.setNumber("20!2001%");
		List<SaleData> l_tl = saleDao.findSaleA(so);

		so.setNumber("18!1801%");
		List<SaleData> l_qz = saleDao.findSaleA(so);

		so.setNumber("11!1106!110603%");
		List<SaleData> l_sxb = saleDao.findSaleA(so);

		so.setNumber(null);
		List<SaleData> l_b_oem = saleDao.findSaleB(so);

//		so.setYzB(true);//易装B类
//		List<SaleData> l_b_yz = saleDao.findSaleB(so);

		so.setYzB(null);
		so.setYzPt(true);
		List<SaleData> l_yz_pt = saleDao.findSaleB(so);



//		margeBToAll(allList,allMap,l_b_oem,l_b_yz,l_yz_pt);
		margeBToAll(allList,allMap,l_b_oem,l_yz_pt);


		setData(allMap,l_bc,TYPE_BC);
		setData(allMap,l_wj,TYPE_WJ);
		setData(allMap,l_jnj,TYPE_JNJ);
		setData(allMap,l_tl,TYPE_TL);
		setData(allMap,l_qz,TYPE_QZ);
		setData(allMap,l_sxb,TYPE_SXB);
		setData(allMap,l_b_oem,TYPE_B_OEM);
//		setData(allMap,l_b_yz,TYPE_B_YZ);
		setData(allMap,l_yz_pt,TYPE_YZ_PT);

		return allList;
	}


	@Override
	public BaseDao<SaleData, String> getDao() {
		return saleDao;
	}
	

	@Override
	public BaseSO<String> newSO() {
		return new SaleDataSO();
	}
	
	
	
}
