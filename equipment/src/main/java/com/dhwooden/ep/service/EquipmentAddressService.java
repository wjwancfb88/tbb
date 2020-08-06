package com.dhwooden.ep.service;

import com.baomidou.mybatisplus.service.IService;
import com.dhwooden.ep.modal.EquipmentAddress;

public interface EquipmentAddressService extends IService<EquipmentAddress> {
    void updateByModal(EquipmentAddress rq);
}
