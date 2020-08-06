package com.tubaoapi.model.so;

import org.apache.commons.lang3.StringUtils;



public class SupplierSO extends BaseSO<String>{
	
	private String number;
	public String cfisycsupplier;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		if(StringUtils.isNotBlank(number)){
			this.number = number;
		}
	}

	public String getCfisycsupplier() {
		return cfisycsupplier;
	}

	public void setCfisycsupplier(String cfisycsupplier) {
		this.cfisycsupplier = cfisycsupplier;
	}

	private String createTime;
	public String getCreateTime() {
		return StringUtils.trimToNull(createTime);
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
