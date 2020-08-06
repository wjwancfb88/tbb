package com.tubaoapi.dao;

import com.tubaoapi.model.Customer;
import com.tubaoapi.model.so.BaseSO;
import com.tubaoapi.model.so.CustomerSO;
import com.tubaoapi.modules.persistence.dao.BaseDao;
import com.tubaoapi.modules.persistence.pagination.model.PageRequest;
import com.tubaoapi.modules.persistence.pagination.model.PageRowBounds;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CustomerDao extends BaseDao<Customer, String> {

	Customer getByNumber(String number);

	List<Customer>  findBySaler(CustomerSO so,PageRowBounds pageRequest);

}
