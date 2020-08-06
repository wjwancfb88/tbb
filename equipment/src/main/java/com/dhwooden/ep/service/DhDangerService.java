package com.dhwooden.ep.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.dhwooden.ep.modal.Dealer;
import com.dhwooden.ep.modal.DhDanger;
import com.baomidou.mybatisplus.service.IService;
import com.dhwooden.ep.modal.DhEntry;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wk
 * @since 2018-09-20
 */
public interface DhDangerService extends IService<DhDanger> {
    void  updateByModal(DhDanger danger);

    public Page<DhDanger> selectByPage(Page<DhDanger> page,Map danger);

    public List<Dealer> selectDealer(String id);
}
