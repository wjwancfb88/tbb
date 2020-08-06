package com.tbb.service.impl;

import com.tbb.entity.Dd;
import com.tbb.mapper.DdMapper;
import com.tbb.service.IDdService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class DdServiceImpl extends ServiceImpl<DdMapper, Dd> implements IDdService {

    @Autowired
    private DdMapper ddMapper;

    @Override
    public int deleteAll() {
        return ddMapper.deleteAll();
    }
}
