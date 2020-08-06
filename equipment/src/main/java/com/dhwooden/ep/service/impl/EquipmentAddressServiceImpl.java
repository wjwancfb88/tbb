package com.dhwooden.ep.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dhwooden.ep.mapper.EquipmentAddressDao;
import com.dhwooden.ep.modal.EquipmentAddress;
import com.dhwooden.ep.service.EquipmentAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentAddressServiceImpl extends ServiceImpl<EquipmentAddressDao, EquipmentAddress> implements EquipmentAddressService {
    @Autowired
    private EquipmentAddressDao equipmentAddressDao;

    @Override
    public void updateByModal(EquipmentAddress rq) {
        equipmentAddressDao.updateByModal(rq);
    }
}
