package com.dhwooden.ep.service.impl;

import com.dhwooden.ep.modal.Department;
import com.dhwooden.ep.mapper.DepartmentDao;
import com.dhwooden.ep.service.DepartmentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wk
 * @since 2018-04-17
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentDao, Department> implements DepartmentService {
    @Autowired
    private DepartmentDao dao;
    @Override
    public void deleteAll() {
        dao.deleteAll();
    }
}
