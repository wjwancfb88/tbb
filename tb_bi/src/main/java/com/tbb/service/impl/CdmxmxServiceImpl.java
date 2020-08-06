package com.tbb.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tbb.entity.Cdmxmx;
import com.tbb.mapper.CdmxmxMapper;
import com.tbb.service.ICdmxmxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * ${table.comment} 服务实现类
 * </p>
 *
 * @author cfb
 * @since 2020-04-03
 */
@Service
public class CdmxmxServiceImpl extends ServiceImpl<CdmxmxMapper, Cdmxmx> implements ICdmxmxService {

    @Autowired
    private CdmxmxMapper cdmxmxMapper;

    @Override
    public int deleteAll(String createdOn) {
        return cdmxmxMapper.deleteAll(createdOn);
    }

    @Override
    public int insertList(List<Cdmxmx> list) {
        return cdmxmxMapper.insertList(list);
    }
}
