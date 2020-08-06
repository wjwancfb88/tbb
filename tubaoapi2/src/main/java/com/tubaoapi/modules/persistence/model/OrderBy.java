package com.tubaoapi.modules.persistence.model;

public class OrderBy {
	/*
	 * order by 字段
	 */
	private String orderBy = "FID";
	
	/*
	 * 是否 desc 
	 */
	private boolean desc;
	
	/*
	 * order by sql
	 * 如：order by id asc
	 */
	private String orderSql;
	
	
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getOrder() {
		return desc?"desc":"asc";
	}
	public boolean isDesc() {
		return desc;
	}
	public void setDesc(boolean desc) {
		this.desc = desc;
	}
	
	public String getOrderSql(){
		if(orderSql!=null){
			return orderSql;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(" order by ");
		sb.append(orderBy);
		sb.append(" ");
		sb.append(getOrder());
		return sb.toString();
	}
	public void setOrderSql(String orderSql) {
		this.orderSql = orderSql;
	}
}
