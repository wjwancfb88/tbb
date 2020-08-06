package com.tubaoapi.service;

import com.tubaoapi.dao.CityDao;
import com.tubaoapi.dao.ProvinceDao;
import com.tubaoapi.model.City;
import com.tubaoapi.model.Province;
import com.tubaoapi.model.so.BaseSO;
import com.tubaoapi.model.so.CitySO;
import com.tubaoapi.model.so.ProvinceSO;
import com.tubaoapi.modules.persistence.dao.BaseDao;
import com.tubaoapi.modules.persistence.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by WK on 2017/8/4.
 */
@Service
public class CityService extends BaseService<City, String> {
    @Autowired
    private CityDao cityDao;

    @Override
    public BaseDao<City, String> getDao() {
        return cityDao;
    }

    @Override
    public BaseSO<String> newSO() {
        return new CitySO();
    }
}
