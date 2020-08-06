package com.dhwooden.ep.mapper;

import com.dhwooden.ep.modal.EquipmentType;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wk
 * @since 2018-05-18
 */
public interface EquipmentTypeDao extends BaseMapper<EquipmentType> {
    public void updateByModal(EquipmentType type);
}