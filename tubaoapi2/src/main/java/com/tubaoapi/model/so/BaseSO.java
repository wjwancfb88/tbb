package com.tubaoapi.model.so;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import com.tubaoapi.modules.persistence.model.OrderBy;

public class BaseSO<ID> extends OrderBy {
	
	private ID id;
	
	/*
	 * where id in () 
	 */
	private Collection<ID> ids;
	
	/*
	 * where id not in () 
	 */
	private Collection<ID> notInIds ;
	
	/*
	 * 模糊查询字符串
	 */
	private String q;
	
	
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public Collection<ID> getIds() {
		return ids;
	}
	public void setIds(Collection<ID> ids) {
		if(ids!=null && ids.size()>0){
			this.ids = ids;
		}
	}
	
	public Collection<ID> getNotInIds() {
		return notInIds;
	}
	public void setNotInIds(Collection<ID> notInIds) {
		this.notInIds = notInIds;
	}
	
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		if(StringUtils.isNotBlank(q)){
			this.q = q;
		}
	}
	
	
	
	

	
}
