package com.dhwooden.ep.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.dhwooden.ep.modal.DhEntry;
import com.dhwooden.ep.mapper.DhEntryDao;
import com.dhwooden.ep.modal.Equipment;
import com.dhwooden.ep.modal.Test;
import com.dhwooden.ep.service.DhEntryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wk
 * @since 2018-07-05
 */
@Service
public class DhEntryServiceImpl extends ServiceImpl<DhEntryDao, DhEntry> implements DhEntryService {
    @Autowired
    private DhEntryDao dhEntryDao;
    @Override
    public Page<DhEntry> selectByPage(Page<DhEntry> page, Map eq) {
        page.setRecords(dhEntryDao.selectMap(eq));
        return page;
    }

    @Override
    public void updateByid(DhEntry dhEntry) {
         dhEntryDao.updateByid(dhEntry);
    }

    @Override
    public void insertTest(Test test) {
        dhEntryDao.insertTest(test);
    }
}
