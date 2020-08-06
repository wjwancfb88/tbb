package com.dhwooden.ep.mapper;

import com.dhwooden.ep.modal.Dealer;
import com.dhwooden.ep.modal.DhDanger;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wk
 * @since 2018-09-20
 */
public interface DhDangerDao extends BaseMapper<DhDanger> {
    void  updateByModal(DhDanger dhDanger);

    List<DhDanger> selectMap(Map danger);

    List<Dealer> selectDealer(String id);

}