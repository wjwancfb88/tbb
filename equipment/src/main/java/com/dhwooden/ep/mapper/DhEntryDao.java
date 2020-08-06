package com.dhwooden.ep.mapper;

import com.dhwooden.ep.modal.DhEntry;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dhwooden.ep.modal.Test;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wk
 * @since 2018-07-05
 */
public interface DhEntryDao extends BaseMapper<DhEntry> {

    void updateByid(DhEntry entry);

    List<DhEntry> selectMap(Map eq);

   void insertTest(Test test);

}