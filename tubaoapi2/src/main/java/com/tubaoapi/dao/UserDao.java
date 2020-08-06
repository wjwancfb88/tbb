package com.tubaoapi.dao;

import com.tubaoapi.model.User;
import com.tubaoapi.model.so.UserSO;
import com.tubaoapi.modules.persistence.dao.BaseDao;
import com.tubaoapi.modules.persistence.pagination.model.PageRowBounds;

import java.util.List;

public interface UserDao extends BaseDao<User, String> {

	User getByNumber(String number);

	List<User> findByStaff(UserSO so,PageRowBounds pageRequest);

}
