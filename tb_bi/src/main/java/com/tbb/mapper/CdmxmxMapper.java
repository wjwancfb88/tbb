package com.tbb.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tbb.entity.Cdmxmx;

import java.util.List;

/**
 * <p>
  * ${table.comment} Mapper 接口
 * </p>
 *
 * @author cfb
 * @since 2020-04-03
 */
public interface CdmxmxMapper extends BaseMapper<Cdmxmx> {
    int deleteAll(String createdOn);
    int insertList(List<Cdmxmx> list);
}