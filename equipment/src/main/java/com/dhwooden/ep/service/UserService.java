package com.dhwooden.ep.service;

import com.dhwooden.ep.modal.User;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wk
 * @since 2018-04-18
 */
public interface UserService extends IService<User> {

    public List<User> selectEmpByName(Map eq);
    public User selectByOpenId(String openId);

    void updateByModal(User user);

    void deleteAll();
}
