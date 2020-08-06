package com.tubaoapi.service;

import com.tubaoapi.dao.ProvinceDao;
import com.tubaoapi.model.Province;
import com.tubaoapi.model.so.BaseSO;
import com.tubaoapi.model.so.ProvinceSO;
import com.tubaoapi.modules.persistence.dao.BaseDao;
import com.tubaoapi.modules.persistence.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HP on 2017/8/4.
 */
@Service
public class ProvinceService  extends BaseService<Province, String> {
    @Autowired
    private ProvinceDao provinceDao;

    @Override
    public BaseDao<Province, String> getDao() {
        return provinceDao;
    }

    @Override
    public BaseSO<String> newSO() {
        return new ProvinceSO();
    }
}
