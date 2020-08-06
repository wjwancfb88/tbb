package com.tubaoapi.service;

import com.tubaoapi.dao.CityDao;
import com.tubaoapi.dao.RegionDao;
import com.tubaoapi.model.City;
import com.tubaoapi.model.Region;
import com.tubaoapi.model.so.BaseSO;
import com.tubaoapi.model.so.CitySO;
import com.tubaoapi.model.so.RegionSO;
import com.tubaoapi.modules.persistence.dao.BaseDao;
import com.tubaoapi.modules.persistence.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by WK on 2017/8/4.
 */
@Service
public class RegionService extends BaseService<Region, String> {
    @Autowired
    private RegionDao regionDao;

    @Override
    public BaseDao<Region, String> getDao() {
        return regionDao;
    }

    @Override
    public BaseSO<String> newSO() {
        return new RegionSO();
    }
}
