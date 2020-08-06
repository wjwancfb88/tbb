package com.dhwooden.ep.service;

import com.dhwooden.ep.modal.EquipmentType;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wk
 * @since 2018-05-18
 */
public interface EquipmentTypeService extends IService<EquipmentType> {
   void  updateByModal(EquipmentType type);
	
}
