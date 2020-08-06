package com.dhwooden.ep.service;

import com.dhwooden.ep.modal.Department;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wk
 * @since 2018-04-17
 */
public interface DepartmentService extends IService<Department> {
    void deleteAll();
}
