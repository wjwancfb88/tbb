package com.tubaoapi.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tubaoapi.api.base.Views;
import com.tubaoapi.modules.persistence.model.IdModel;

public class User extends IdModel<String>{

	private static final long serialVersionUID = -1724374630750024844L;
	private String number;
	private String name;
	private String personId;
	private String customerId;
	private String supplierId;
	
	private Customer customer;
	private Supplier supplier;
	private String lastUpdateTime;
	private String type;

	private String org;

	private String phone;

	@JsonView(Views.PublicView.class)
	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	@JsonView(Views.PublicView.class)
	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

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
	
	@JsonInclude(Include.NON_NULL)
	@JsonView(Views.PublicView.class)
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	
	@JsonInclude(Include.NON_NULL)
	@JsonView(Views.PublicView.class)
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	@JsonInclude(Include.NON_NULL)
	@JsonView(Views.PublicView.class)
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@JsonInclude(Include.NON_NULL)
	@JsonView(Views.PublicView.class)
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@JsonView(Views.PublicView.class)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@JsonView(Views.PublicView.class)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
