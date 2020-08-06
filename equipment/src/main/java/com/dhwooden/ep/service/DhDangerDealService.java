package com.dhwooden.ep.service;

import com.dhwooden.ep.modal.DhDangerDeal;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wk
 * @since 2018-09-29
 */
public interface DhDangerDealService extends IService<DhDangerDeal> {
    void  updateByModal();
}
