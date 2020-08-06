package com.dhwooden.ep.mapper;

import com.dhwooden.ep.modal.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wk
 * @since 2018-04-18
 */
public interface UserDao extends BaseMapper<User> {
    public List<User> selectEmpByName(Map eq);
    void updateByModal(User user);

    void deleteAll();

    User selectByOpenId(String openId);
}
