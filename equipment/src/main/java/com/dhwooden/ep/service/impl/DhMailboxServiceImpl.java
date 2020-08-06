package com.dhwooden.ep.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.dhwooden.ep.modal.DhMailbox;
import com.dhwooden.ep.mapper.DhMailboxDao;
import com.dhwooden.ep.service.DhMailboxService;
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
 * @since 2018-08-27
 */
@Service
public class DhMailboxServiceImpl extends ServiceImpl<DhMailboxDao, DhMailbox> implements DhMailboxService {
    @Autowired
    private DhMailboxDao dhMailboxDao;
    @Override
    public Page<DhMailbox> selectByPage(Page<DhMailbox> page, Map eq) {
        page.setRecords(dhMailboxDao.selectByMap(eq));
        return page;
    }
}
