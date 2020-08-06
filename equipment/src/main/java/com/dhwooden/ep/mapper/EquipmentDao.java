package com.dhwooden.ep.mapper;

import com.dhwooden.ep.modal.Equipment;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wk
 * @since 2018-04-17
 */
public interface EquipmentDao extends BaseMapper<Equipment> {
    List<Equipment> selectEquipmenttList(Map eq);
    List<Equipment> selectLeave(Map eq);

    void updateDepartment();

    void updateUser();
}