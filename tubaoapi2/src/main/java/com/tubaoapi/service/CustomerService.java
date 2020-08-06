package com.tubaoapi.service;

import com.tubaoapi.modules.persistence.pagination.model.Page;
import com.tubaoapi.modules.persistence.pagination.model.PageRequest;
import com.tubaoapi.modules.persistence.pagination.model.PageRowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tubaoapi.dao.CustomerDao;
import com.tubaoapi.model.Customer;
import com.tubaoapi.model.so.BaseSO;
import com.tubaoapi.model.so.CustomerSO;
import com.tubaoapi.modules.persistence.dao.BaseDao;
import com.tubaoapi.modules.persistence.service.BaseService;

import java.util.List;

@Service
public class CustomerService extends BaseService<Customer, String>{


	@Autowired
	private CustomerDao userDao;



	public Customer getByNumber(String number) {
		return userDao.getByNumber(number);
	}


	@Override
	public BaseDao<Customer, String> getDao() {
		return userDao;
	}
	

	@Override
	public BaseSO<String> newSO() {
		return new CustomerSO();
	}

	public Page<Customer> findBySaler( CustomerSO so,PageRequest pageRequest){
		PageRowBounds rowBounds = new PageRowBounds(pageRequest);
		List<Customer> l = userDao.findBySaler(so,rowBounds);
		return new Page<Customer>(rowBounds, l);
	}

	
}
