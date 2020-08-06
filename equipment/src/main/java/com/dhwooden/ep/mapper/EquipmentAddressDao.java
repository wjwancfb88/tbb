package com.dhwooden.ep.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dhwooden.ep.modal.EquipmentAddress;

public interface EquipmentAddressDao extends BaseMapper<EquipmentAddress> {
    void updateByModal(EquipmentAddress rq);
}
