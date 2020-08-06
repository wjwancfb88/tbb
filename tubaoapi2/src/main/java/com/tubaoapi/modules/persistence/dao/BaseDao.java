package com.tubaoapi.modules.persistence.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.tubaoapi.model.so.BaseSO;


public interface BaseDao<T,ID> {
	List<T> findBySO(BaseSO<ID> so);
	List<T> findBySO(BaseSO<ID> so,RowBounds rowBounds);
	T get(ID id);
	void insert(T t);
	void update(T t);
	void delete(ID id);
}
