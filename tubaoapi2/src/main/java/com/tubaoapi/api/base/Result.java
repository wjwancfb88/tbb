package com.tubaoapi.api.base;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.Lists;
import com.tubaoapi.modules.persistence.pagination.model.Page;


public class Result<T> {
	public static final String KIND_USER_LIST = "userList";
	public static final String KIND_CUSTOMER_LIST = "customerList";
	public static final String KIND_PERSON_LIST = "personList";
	public static final String KIND_SUPPLIER_LIST = "supplierList";
	public static final String KIND_MATERIAL_LIST = "materialList";
	public static final String KIND_MATERIAL_GROUP_LIST = "materialGroupList";
	public static final String KIND_PROVINCE_LIST = "provinceList";
	public static final String KIND_CITY_LIST = "cityList";
	public static final String KIND_REGION_LIST = "regioneList";
	public static final String KIND_SaleData_LIST = "saleDataList";
	public static final String KIND_YZPPSUM_LIST = "yzppSumList";
	public static final String KIND_MATERIAL_SUPPLIER_LIST = "materialIds";
	public static final String KIND_INVENTORY_LIST = "inventory";

	
	private String kind;
	private List<T> items = Lists.newArrayList();
	private T data;
	private Page pageInfo;
	
	@JsonView(Views.PublicView.class)
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	@JsonInclude(Include.NON_NULL)
	@JsonView(Views.PublicView.class)
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	
	@JsonInclude(Include.NON_NULL)
	@JsonView(Views.PublicView.class)
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	@JsonInclude(Include.NON_NULL)
	@JsonView(Views.PublicView.class)
	public Page getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(Page pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	
	
	
	
	
}
