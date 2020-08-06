package com.dhwooden.ep.mapper;

import com.dhwooden.ep.modal.Department;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wk
 * @since 2018-04-17
 */
public interface DepartmentDao extends BaseMapper<Department> {
    void deleteAll();
}