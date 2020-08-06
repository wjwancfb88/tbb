package com.tubaoapi.model.so;

import org.apache.commons.lang3.StringUtils;



public class UserSO extends BaseSO<String>{

	private String customerId;
	private String lastUpdateTime;
	private String number;

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}



	public String getNumber() {
		return number;
	}

	public String getCustomerId() {
		return StringUtils.trimToNull(customerId);
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setNumber(String number) {
		if(StringUtils.isNotBlank(number)){
			this.number = number;
		}
	}
	private String createTime;
	public String getCreateTime() {
		return StringUtils.trimToNull(createTime);
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
