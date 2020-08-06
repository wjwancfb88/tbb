package com.dhwooden.ep.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dhwooden.ep.modal.WLYY;

/**
 * Created by kaiwang on 2019/6/27.
 */
public interface DhWlyyDao extends BaseMapper<WLYY> {
    WLYY selectWlyyById(String wlyyId);
    void deleteAll();
}
