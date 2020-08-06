package com.tubaoapi.dao;


import com.tubaoapi.model.Person;
import com.tubaoapi.model.so.PersonSO;
import com.tubaoapi.modules.persistence.dao.BaseDao;
import com.tubaoapi.modules.persistence.pagination.model.PageRowBounds;

import java.util.List;

public interface PersonDao extends BaseDao<Person, String> {

	List<Person> findBySO(PersonSO so, PageRowBounds rowBounds);
	List<Person> findBySO(PersonSO so);
}
