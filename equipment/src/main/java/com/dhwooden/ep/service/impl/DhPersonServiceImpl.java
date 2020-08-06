package com.dhwooden.ep.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dhwooden.ep.mapper.DhPersonDao;
import com.dhwooden.ep.modal.DhPerson;
import com.dhwooden.ep.service.DhPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DhPersonServiceImpl extends ServiceImpl<DhPersonDao, DhPerson> implements DhPersonService {
    @Autowired
    private DhPersonDao personDao;
    @Override
    public DhPerson selectByOpenId(String openId) {
        return personDao.selectByOpenId(openId);
    }
}
