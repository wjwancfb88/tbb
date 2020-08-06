package com.dhwooden.ep.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.dhwooden.ep.modal.Dealer;
import com.dhwooden.ep.modal.DhDanger;
import com.dhwooden.ep.mapper.DhDangerDao;
import com.dhwooden.ep.service.DhDangerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wk
 * @since 2018-09-20
 */
@Service
public class DhDangerServiceImpl extends ServiceImpl<DhDangerDao, DhDanger> implements DhDangerService {
    @Autowired
    private DhDangerDao dhDangerDao;
    @Override
    public void updateByModal(DhDanger danger) {
        dhDangerDao.updateByModal(danger);
    }

    @Override
    public Page<DhDanger> selectByPage(Page<DhDanger> page, Map danger) {
        page.setRecords(dhDangerDao.selectMap(danger));
        return page;
    }

    @Override
    public List<Dealer> selectDealer(String id) {
        return dhDangerDao.selectDealer(id);
    }

}
