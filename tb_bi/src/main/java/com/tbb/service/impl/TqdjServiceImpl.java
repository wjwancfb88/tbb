package com.tbb.service.impl;

import com.tbb.entity.Tqdj;
import com.tbb.mapper.TqdjMapper;
import com.tbb.service.ITqdjService;
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
public class TqdjServiceImpl extends ServiceImpl<TqdjMapper, Tqdj> implements ITqdjService {
    @Autowired
    private TqdjMapper tqdjMapper;

    @Override
    public int deleteAll() {
        return tqdjMapper.deleteAll();
    }
}
