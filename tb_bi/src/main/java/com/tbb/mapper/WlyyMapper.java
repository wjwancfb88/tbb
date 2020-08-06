package com.tbb.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tbb.entity.Wlyy;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author cfb
 * @since 2020-04-02
 */
public interface WlyyMapper extends BaseMapper<Wlyy> {
    int deleteAll();
}