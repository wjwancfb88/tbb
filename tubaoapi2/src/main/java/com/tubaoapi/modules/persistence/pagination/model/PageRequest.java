package com.tubaoapi.modules.persistence.pagination.model;




public class PageRequest 
{
	private static final int DEFAULT_PAGE = 1;
	private static final int DEFAULT_SIZE = 20;
	
	private boolean autoCount = true;
	private int page;
	private int size;
	

	public PageRequest(){
		this(DEFAULT_PAGE, DEFAULT_SIZE);
	}
	
	public PageRequest(int page){
		this(page, DEFAULT_SIZE, true);
	}
	
	public PageRequest(int page,int size){
		this(page, size, true);
	}
	
	public PageRequest(int page,int size,boolean autoCount){
		setSize(size);
		setPage(page);
		setAutoCount(autoCount);
	}
	
	
	public void setPage(int page){
		if(page<=0){
			page = DEFAULT_PAGE;
		}
		this.page = page;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setSize(int size){
		if(size<=0){
			size = DEFAULT_SIZE;
		}
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}

	public boolean isAutoCount() {
		return autoCount;
	}

	public void setAutoCount(boolean autoCount) {
		this.autoCount = autoCount;
	}
	
	
	public int getOffset() {
		return (page-1) * size;
	}
	
}
