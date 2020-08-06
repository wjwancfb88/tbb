package com.tubaoapi.model.so;

import org.apache.commons.lang3.StringUtils;



public class CustomerSO extends BaseSO<String>{
	
	private String number;

	private String lastUpdateTime;

	private String customerKind;

	private String cfisycobj;

	private String saleOrgID;

	private String endUpdateTime;


	private String agentType;

	/**
	 * 是否是经销商
	 */
	private Boolean jxs;

	/**
	 * 是否加载该经销商的用户
	 */
	private Boolean loadUsers;



	public String getAgentType() {
		return agentType;
	}


	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}

	public String getEndUpdateTime() {
		return endUpdateTime;
	}

	public void setEndUpdateTime(String endUpdateTime) {
		this.endUpdateTime = endUpdateTime;
	}

	public String getSaleOrgID() {
		return saleOrgID;
	}

	public void setSaleOrgID(String saleOrgID) {
		this.saleOrgID = saleOrgID;
	}

	public String getCfisycobj() {
		return cfisycobj;
	}

	public void setCfisycobj(String cfisycobj) {
		this.cfisycobj = cfisycobj;
	}

	public String getCustomerKind() {
		return customerKind;
	}

	public void setCustomerKind(String customerKind) {
		this.customerKind = customerKind;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		if(StringUtils.isNotBlank(number)){
			this.number = number;
		}
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Boolean getJxs() {
		return jxs;
	}

	public void setJxs(Boolean jxs) {
		this.jxs = jxs;
	}

	public Boolean getLoadUsers() {
		return loadUsers;
	}

	public void setLoadUsers(Boolean loadUsers) {
		this.loadUsers = loadUsers;
	}


	private String createTime;
	public String getCreateTime() {
		return StringUtils.trimToNull(createTime);
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
