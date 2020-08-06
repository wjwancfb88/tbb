package com.tbb.mapper;

import com.tbb.entity.Dd;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * ${table.comment} Mapper 接口
 * </p>
 *
 * @author cfb
 * @since 2020-04-03
 */
public interface DdMapper extends BaseMapper<Dd> {
    int deleteAll();
}