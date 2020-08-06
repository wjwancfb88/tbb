package com.dhwooden.ep.service;

import com.baomidou.mybatisplus.service.IService;
import com.dhwooden.ep.modal.DhOrder;

import java.util.List;
import java.util.Map;

/**
 * Created by kaiwang on 2019/6/27.
 */
public interface DhOrderService extends IService<DhOrder> {
    DhOrder selectOrderById(String orderId);
//    public List<DhOrder> selectEmpByName(Map eq);
//
//    void updateByModal(DhOrder dhOrder);
}
