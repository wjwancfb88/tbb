package com.tubaoapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.Views;
import com.tubaoapi.modules.persistence.model.IdModel;

import java.util.List;

public class Customer extends IdModel<String>{

	private static final long serialVersionUID = -8930204016574898052L;
	private String number;
	private String name;
	private String simpleName;
	@JsonView(Views.PublicView.class)
	private String provinceId;
	@JsonView(Views.PublicView.class)
	private String cityId;
	@JsonView(Views.PublicView.class)
	private String regionId;
	@JsonView(Views.PublicView.class)
	private String customerKind;
	@JsonView(Views.PublicView.class)
	private String usedStatus;

	@JsonView(Views.PublicView.class)
	private String saler;
	@JsonView(Views.PublicView.class)
	private String org;

	@JsonView(Views.PublicView.class)
	private String agentType;
	@JsonView(Views.PublicView.class)
	 private String cfisycobj;

	@JsonView(Views.PublicView.class)
	private String  personid;
	@JsonView(Views.PublicView.class)
	private String parentID;
	@JsonView(Views.PublicView.class)
	private String staCustomerID;

	@JsonView(Views.PublicView.class)
	private String cfTxtContactNumber;

	private String contactPerson;
	@JsonView(Views.PublicView.class)
	private String fMnemonicCode;


	private List<User> users;

	public String getfMnemonicCode() {
		return fMnemonicCode;
	}

	public void setfMnemonicCode(String fMnemonicCode) {
		this.fMnemonicCode = fMnemonicCode;
	}

	public String getCfTxtContactNumber() {
		return cfTxtContactNumber;
	}

	public void setCfTxtContactNumber(String cfTxtContactNumber) {
		this.cfTxtContactNumber = cfTxtContactNumber;
	}

	@JsonView(Views.PublicView.class)
	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}



	@JsonView(Views.PublicView.class)
	public String getStaCustomerID() {
		return staCustomerID;
	}

	public void setStaCustomerID(String staCustomerID) {
		this.staCustomerID = staCustomerID;
	}
	@JsonView(Views.PublicView.class)
	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getAgentType() {
		return agentType;
	}

	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	@JsonView(Views.PublicView.class)
	public String getCustomerKind() {
		return customerKind;
	}

	public void setCustomerKind(String customerKind) {
		this.customerKind = customerKind;
	}

	private String lastUpdateTime;

	@JsonView(Views.PublicView.class)
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@JsonView(Views.PublicView.class)
	public String getNumber() {
		return StringUtils.trimToEmpty(number);
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	@JsonView(Views.PublicView.class)
	public String getName() {
		return StringUtils.trimToEmpty(name);
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonView(Views.PublicView.class)
	public String getSimpleName() {
		return StringUtils.trimToEmpty(simpleName);
	}
	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getUsedStatus() {
		return usedStatus;
	}

	public void setUsedStatus(String usedStatus) {
		this.usedStatus = usedStatus;
	}

	public String getSaler() {
		return saler;
	}
	@JsonView(Views.PublicView.class)
	public void setSaler(String saler) {
		this.saler = saler;
	}

	public String getCfisycobj() {
		return cfisycobj;
	}

	public void setCfisycobj(String cfisycobj) {
		this.cfisycobj = cfisycobj;
	}

	@JsonView(Views.PublicView.class)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@JsonView(Views.PublicView.class)
	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}


	private String createTime;

	@JsonView(Views.PublicView.class)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
