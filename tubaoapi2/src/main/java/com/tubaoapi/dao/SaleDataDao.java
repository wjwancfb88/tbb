package com.tubaoapi.dao;

import com.tubaoapi.model.City;
import com.tubaoapi.model.SaleData;
import com.tubaoapi.model.so.BaseSO;
import com.tubaoapi.model.so.SaleDataSO;
import com.tubaoapi.modules.persistence.dao.BaseDao;

import java.util.List;

/**
 * Created by kaiwang on 2017/8/4.
 */
public interface SaleDataDao extends BaseDao<SaleData, String> {
    List<SaleData> findSaleA(SaleDataSO so);
    List<SaleData> findSaleB(SaleDataSO so);
}
