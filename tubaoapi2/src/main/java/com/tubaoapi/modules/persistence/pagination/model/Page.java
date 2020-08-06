package com.tubaoapi.modules.persistence.pagination.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.Views;


public class Page<T> {
	private long page;
	private int pageOfElements;
	private int size;
	private long totalElements;
	private long totalPages;
	private List<T> content;
	
	public Page(PageRowBounds rowBounds,List<T> content){
		this(rowBounds.getPageRequest(),content,rowBounds.getCount());
	}
	
	
	public Page(PageRequest pageRequest,List<T> content,long count){
		this.content = content;
		this.page = pageRequest.getPage();
		this.pageOfElements = content.size();
		this.size = pageRequest.getSize();
		this.totalElements = count;
		this.totalPages = totalElements/size;
		if(totalElements%size!=0){
			this.totalPages++;	
		}
	}
	
	/*
	public Page(Page sourcePage,List<T> content){
		this.content = content;
		this.page = sourcePage.getPage();
		this.pageOfElements = sourcePage.getPageOfElements();
		this.size = sourcePage.getSize();
		this.totalElements = sourcePage.getTotalElements();
		this.totalPages = sourcePage.getTotalPages();
	}*/
	
	public void add(List<T> l){
		content.addAll(l);
		pageOfElements = pageOfElements + l.size();
	}
	

	@JsonView(Views.PublicView.class)
	public long getPage() {
		return page;
	}

	public int getPageOfElements() {
		return pageOfElements;
	}

	@JsonView(Views.PublicView.class)
	public int getSize() {
		return size;
	}

	@JsonView(Views.PublicView.class)
	public long getTotalElements() {
		return totalElements;
	}

	@JsonView(Views.PublicView.class)
	public long getTotalPages() {
		return totalPages;
	}

	
	public List<T> getContent() {
		return content;
	}
	
	public boolean hasPreviousPage(){
		return page>1;
	}
	
	public boolean hasNextPage(){
		return page<totalPages;
	}
}
