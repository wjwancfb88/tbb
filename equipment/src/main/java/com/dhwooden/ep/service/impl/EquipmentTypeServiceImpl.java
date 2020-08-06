package com.dhwooden.ep.service.impl;

import com.dhwooden.ep.modal.EquipmentType;
import com.dhwooden.ep.mapper.EquipmentTypeDao;
import com.dhwooden.ep.service.EquipmentTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wk
 * @since 2018-05-18
 */
@Service
public class EquipmentTypeServiceImpl extends ServiceImpl<EquipmentTypeDao, EquipmentType> implements EquipmentTypeService {
    @Autowired
    private EquipmentTypeDao equipmentTypeDao;
    @Override
    public void updateByModal(EquipmentType type) {
        equipmentTypeDao.updateByModal(type);
    }
}
