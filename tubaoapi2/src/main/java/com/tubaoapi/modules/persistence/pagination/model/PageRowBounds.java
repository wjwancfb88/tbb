package com.tubaoapi.modules.persistence.pagination.model;

import org.apache.ibatis.session.RowBounds;

public class PageRowBounds extends RowBounds {
	
	private PageRequest pageRequest; 
	private long count;
	
	public PageRowBounds(PageRequest pageRequest){
		super(pageRequest.getOffset(),pageRequest.getSize());
		this.pageRequest = pageRequest;
	}

	public PageRequest getPageRequest() {
		return pageRequest;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
	
}
