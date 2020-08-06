package com.tbb.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tbb.entity.Mx1;

/**
 * <p>
  * ${table.comment} Mapper 接口
 * </p>
 *
 * @author cfb
 * @since 2020-04-03
 */
public interface Mx1Mapper extends BaseMapper<Mx1> {
    int deleteAll();
}