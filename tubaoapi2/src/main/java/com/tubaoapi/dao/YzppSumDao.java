package com.tubaoapi.dao;

import com.tubaoapi.model.SaleData;
import com.tubaoapi.model.dto.YzppSum;
import com.tubaoapi.model.so.SaleDataSO;
import com.tubaoapi.modules.persistence.dao.BaseDao;

import java.util.List;

/**
 * 经销商物料汇总dao
 */
public interface YzppSumDao {
//    List<YzppSum> findOtherIssueBillData(List<String> wlId);
    List<YzppSum> findOtherIssueBillData();
    List<YzppSum> findOrderData(String oemsupplierID,String createTime);
}
