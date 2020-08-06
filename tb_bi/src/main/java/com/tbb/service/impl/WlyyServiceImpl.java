package com.tbb.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tbb.entity.Wlyy;
import com.tbb.mapper.WlyyMapper;
import com.tbb.service.IWlyyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cfb
 * @since 2020-04-02
 */
@Service
public class WlyyServiceImpl extends ServiceImpl<WlyyMapper, Wlyy> implements IWlyyService {

    @Autowired
    private WlyyMapper wlyy2Mapper;

    @Override
    public int deleteAll() {
        return wlyy2Mapper.deleteAll();
    }
}
