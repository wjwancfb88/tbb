package com.dhwooden.ep.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dhwooden.ep.modal.DhPerson;
import com.dhwooden.ep.modal.User;

public interface DhPersonDao extends BaseMapper<DhPerson> {
    DhPerson selectByOpenId(String openId);
}
