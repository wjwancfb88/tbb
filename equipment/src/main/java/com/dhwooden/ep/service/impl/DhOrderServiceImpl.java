package com.dhwooden.ep.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dhwooden.ep.mapper.DhOrderDao;
import com.dhwooden.ep.modal.DhOrder;
import com.dhwooden.ep.service.DhOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by kaiwang on 2019/6/27.
 */
@Service
public class DhOrderServiceImpl extends ServiceImpl<DhOrderDao, DhOrder> implements DhOrderService{
    @Autowired
    private DhOrderDao dao;
//    @Override
//    public List<DhOrder> selectEmpByName(Map eq) {
//        return dao.selectEmpByName(eq);
//    }
//
//    @Override
//    public void updateByModal(DhOrder dhOrder) {
//        dao.updateByModal(dhOrder);
//    }

    @Override
    public DhOrder selectOrderById(String orderId) {
        return dao.selectOrderById(orderId);
    }
}
