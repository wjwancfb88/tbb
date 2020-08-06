package com.dhwooden.ep.service;

import com.baomidou.mybatisplus.service.IService;
import com.dhwooden.ep.modal.DhPerson;

public interface DhPersonService extends IService<DhPerson> {
    DhPerson selectByOpenId(String openId);
}
