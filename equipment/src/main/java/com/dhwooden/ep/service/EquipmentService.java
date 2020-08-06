package com.dhwooden.ep.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.dhwooden.ep.modal.Equipment;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wk
 * @since 2018-04-17
 */
public interface EquipmentService extends IService<Equipment> {
    Page<Equipment> queryAll(Page<Equipment> page,Map eq);

    void updateDepartment();

    void updateUser();
}
