package com.tubaoapi.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.Views;
import com.tubaoapi.modules.persistence.model.IdModel;

public class Supplier extends IdModel<String>{

	private static final long serialVersionUID = -8930204016574898052L;
	private String number;
	private String name;
	private String contactName;//联系人
	private String contactNumber;//联系电话
	public String cityid;
	public String cfisycsupplier;
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

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getCfisycsupplier() {
		return cfisycsupplier;
	}

	public void setCfisycsupplier(String cfisycsupplier) {
		this.cfisycsupplier = cfisycsupplier;
	}

	@JsonView(Views.PublicView.class)
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	@JsonView(Views.PublicView.class)
	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
}
