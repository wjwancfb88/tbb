package com.dhwooden.ep.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dhwooden.ep.modal.DhOrder;

import java.util.List;
import java.util.Map;

/**
 * Created by kaiwang on 2019/6/27.
 */
public interface DhOrderDao extends BaseMapper<DhOrder> {
    DhOrder selectOrderById(String orderId);
//    public List<DhOrder> selectEmpByName(Map eq);
//    void updateByModal(DhOrder dhOrder);
}
