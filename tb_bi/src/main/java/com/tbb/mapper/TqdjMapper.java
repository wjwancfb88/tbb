package com.tbb.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tbb.entity.Tqdj;

/**
 * <p>
  * ${table.comment} Mapper 接口
 * </p>
 *
 * @author cfb
 * @since 2020-04-03
 */
public interface TqdjMapper extends BaseMapper<Tqdj> {
    int deleteAll();
}