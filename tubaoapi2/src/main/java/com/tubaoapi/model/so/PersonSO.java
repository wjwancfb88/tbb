package com.tubaoapi.model.so;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public class PersonSO extends BaseSO<String>{
	private Boolean dbOemPinkongUser;
	private Boolean ertjjPinguan;
	private Boolean ertjjYunying;
	private Boolean ertjjPurchasManager;



	public Boolean getDbOemPinkongUser() {
		return dbOemPinkongUser;
	}

	public void setDbOemPinkongUser(Boolean dbOemPinkongUser) {
		this.dbOemPinkongUser = dbOemPinkongUser;
	}

	public Boolean getErtjjPinguan() {
		return ertjjPinguan;
	}

	public void setErtjjPinguan(Boolean ertjjPinguan) {
		this.ertjjPinguan = ertjjPinguan;
	}

	public Boolean getErtjjYunying() {
		return ertjjYunying;
	}

	public void setErtjjYunying(Boolean ertjjYunying) {
		this.ertjjYunying = ertjjYunying;
	}

	public Boolean getErtjjPurchasManager() {
		return ertjjPurchasManager;
	}

	public void setErtjjPurchasManager(Boolean ertjjPurchasManager) {
		this.ertjjPurchasManager = ertjjPurchasManager;
	}

	private String createTime;
	public String getCreateTime() {
		return StringUtils.trimToNull(createTime);
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
