package com.dhwooden.ep.mapper;

import com.dhwooden.ep.modal.DhDangerDeal;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wk
 * @since 2018-09-29
 */
public interface DhDangerDealDao extends BaseMapper<DhDangerDeal> {
    void  updateByModal();
}