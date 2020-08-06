package com.dhwooden.ep.service.impl;

import com.dhwooden.ep.modal.User;
import com.dhwooden.ep.mapper.UserDao;
import com.dhwooden.ep.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wk
 * @since 2018-04-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    private  UserDao dao;
    @Override
    public List<User> selectEmpByName(Map eq) {
        return dao.selectEmpByName(eq);
    }

    @Override
    public User selectByOpenId(String openId) {
        return dao.selectByOpenId(openId);
    }

    @Override
    public void updateByModal(User user) {
        dao.updateByModal(user);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }
}
