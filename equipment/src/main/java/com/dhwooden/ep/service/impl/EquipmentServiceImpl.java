package com.dhwooden.ep.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.dhwooden.ep.modal.Equipment;
import com.dhwooden.ep.mapper.EquipmentDao;
import com.dhwooden.ep.service.EquipmentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wk
 * @since 2018-04-17
 */
@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentDao, Equipment> implements EquipmentService {
    @Autowired
    private EquipmentDao equipmentDao;
    @Override
    public Page<Equipment> queryAll(Page<Equipment> page,Map equipment) {
        Object code = equipment.get("code");
        if (code != null && !"".equals(code)){
            page.setRecords(equipmentDao.selectLeave(equipment));
        }else{
            page.setRecords(equipmentDao.selectEquipmenttList(equipment));
        }

        return page;

    }

    @Override
    public void updateUser() {
        equipmentDao.updateUser();
    }

    @Override
    public void updateDepartment() {
        equipmentDao.updateDepartment();
    }

}
