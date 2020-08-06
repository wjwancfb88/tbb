package com.tbb.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tbb.entity.Mx1;
import com.tbb.mapper.Mx1Mapper;
import com.tbb.service.IMx1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment} 服务实现类
 * </p>
 *
 * @author cfb
 * @since 2020-04-03
 */
@Service
public class Mx1ServiceImpl extends ServiceImpl<Mx1Mapper, Mx1> implements IMx1Service {

    @Autowired
    private Mx1Mapper mx1Mapper;

    @Override
    public int deleteAll() {
        return mx1Mapper.deleteAll();
    }
}
