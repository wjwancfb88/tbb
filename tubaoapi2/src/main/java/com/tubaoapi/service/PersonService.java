package com.tubaoapi.service;


import com.tubaoapi.dao.PersonDao;
import com.tubaoapi.model.Person;
import com.tubaoapi.model.so.BaseSO;
import com.tubaoapi.model.so.PersonSO;
import com.tubaoapi.modules.persistence.dao.BaseDao;
import com.tubaoapi.modules.persistence.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends BaseService<Person, String> {

	@Autowired
	private PersonDao personDao;


	@Override
	public BaseDao<Person, String> getDao() {
		return personDao;
	}

	@Override
	public BaseSO<String> newSO() {
		return new PersonSO();
	}



}
