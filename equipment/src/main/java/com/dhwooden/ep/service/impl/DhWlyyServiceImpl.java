package com.dhwooden.ep.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dhwooden.ep.mapper.DhOrderDao;
import com.dhwooden.ep.mapper.DhWlyyDao;
import com.dhwooden.ep.modal.DhOrder;
import com.dhwooden.ep.modal.WLYY;
import com.dhwooden.ep.service.DhOrderService;
import com.dhwooden.ep.service.DhWlyyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kaiwang on 2019/6/27.
 */
@Service
public class DhWlyyServiceImpl extends ServiceImpl<DhWlyyDao, WLYY> implements DhWlyyService{
    @Autowired
    private DhWlyyDao dao;

    @Override
    public WLYY selectWlyyById(String wlyyId) {
        return dao.selectWlyyById(wlyyId);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }
}
