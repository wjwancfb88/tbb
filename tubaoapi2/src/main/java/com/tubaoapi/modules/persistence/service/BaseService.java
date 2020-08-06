package com.tubaoapi.modules.persistence.service;

import java.util.Collection;
import java.util.List;

import com.tubaoapi.model.so.BaseSO;
import com.tubaoapi.modules.persistence.dao.BaseDao;
import com.tubaoapi.modules.persistence.pagination.model.Page;
import com.tubaoapi.modules.persistence.pagination.model.PageRequest;
import com.tubaoapi.modules.persistence.pagination.model.PageRowBounds;

public abstract class BaseService<T,ID> {
	

	/**
	 * 根据SO查找唯一记录，如果不唯一则报错
	 */
	public T getBySO(BaseSO<ID> so){
		List<T> l = findBySO(so);
		if(l.size()==0){
			return null;
		}
		
		if(l.size()>1){
			throw new ServiceException("记录不唯一");
		}
		
		return l.get(0);
	}
	/**
	 * 查询所有
	 * 默认排序：order by id asc
	 */
	public List<T> findAll(){
		return findBySO(newSO());
	}
	
	/**
	 * 查询所有
	 * @param orderBy 排序字段
	 * @param desc 是否倒序
	 * 
	 */
	public List<T> findAll(String orderBy,boolean desc){
		BaseSO<ID> so = newSO();
		so.setOrderBy(orderBy);
		so.setDesc(desc);
		return findBySO(so);
	}
	public List<T> findByIds(Collection<ID> ids){
		BaseSO<ID> so = newSO();
		so.setIds(ids);
		return findBySO(so);
	}
	
	
	// BaseDao 基础实现  -------------------------------------------------------
	
	public Page<T> findBySO(BaseSO<ID> so,PageRequest pageRequest) {
		PageRowBounds rowBounds = new PageRowBounds(pageRequest);
		List<T> l = getDao().findBySO(so,rowBounds);
		return new Page<T>(rowBounds, l);
	}
	
	public List<T> findBySO(BaseSO<ID> so){
		return getDao().findBySO(so);
	}
	
	public T get(ID id){
		return getDao().get(id);
	}
	
	public void insert(T t) {
		getDao().insert(t);
	}
	
	public void update(T t) {
		getDao().update(t);
	}
	
	public void update(List<T> l) {
		for(T t:l){
			update(t);
		}
	}
	
	public void delete(ID id) {
		getDao().delete(id);
	}
	
	public void delete(Collection<ID> ids) {
		for(ID id:ids){
			delete(id);
		}
	}
	
	public abstract BaseDao<T,ID> getDao();
	public abstract BaseSO<ID> newSO();
	
}
