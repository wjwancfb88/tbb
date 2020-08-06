package com.tubaoapi.service;

import com.tubaoapi.modules.persistence.pagination.model.Page;
import com.tubaoapi.modules.persistence.pagination.model.PageRequest;
import com.tubaoapi.modules.persistence.pagination.model.PageRowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tubaoapi.dao.UserDao;
import com.tubaoapi.model.User;
import com.tubaoapi.model.so.BaseSO;
import com.tubaoapi.model.so.UserSO;
import com.tubaoapi.modules.persistence.dao.BaseDao;
import com.tubaoapi.modules.persistence.service.BaseService;

import java.util.List;

@Service
public class UserService extends BaseService<User, String>{


	@Autowired
	private UserDao userDao;



	public User getByNumber(String number) {
		return userDao.getByNumber(number);
	}


	@Override
	public BaseDao<User, String> getDao() {
		return userDao;
	}
	

	@Override
	public BaseSO<String> newSO() {
		return new UserSO();
	}

	public Page<User> findByStaff( UserSO so,PageRequest pageRequest){
		PageRowBounds rowBounds = new PageRowBounds(pageRequest);
		List<User> l = userDao.findByStaff(so,rowBounds);
		return new Page<User>(rowBounds, l);
	}
	
	
	
}
