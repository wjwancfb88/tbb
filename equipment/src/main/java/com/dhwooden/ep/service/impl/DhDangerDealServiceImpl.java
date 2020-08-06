package com.dhwooden.ep.service.impl;

import com.dhwooden.ep.modal.DhDangerDeal;
import com.dhwooden.ep.mapper.DhDangerDealDao;
import com.dhwooden.ep.service.DhDangerDealService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wk
 * @since 2018-09-29
 */
@Service
public class DhDangerDealServiceImpl extends ServiceImpl<DhDangerDealDao, DhDangerDeal> implements DhDangerDealService {
    @Autowired
    private DhDangerDealDao dhDangerDealDao;
    @Override
    public void updateByModal() {
        dhDangerDealDao.updateByModal();
    }
}
