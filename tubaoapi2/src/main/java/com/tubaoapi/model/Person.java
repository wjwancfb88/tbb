package com.tubaoapi.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.Views;
import com.tubaoapi.modules.persistence.model.IdModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * T_BD_Person
 */
public class Person extends IdModel<String> {


	
	private String number;
	
	/**
	 * 中文名（nameL1,nameL3 分别是英文和繁体）
	 */
	private String name;
	
	private String cell;

	private boolean dbOemPinkongUser;

	private boolean ertjjPinguan;
	private boolean ertjjYunying;
	private boolean ertjjPurchasManager;


	@JsonView(Views.PublicView.class)
	public String getCell() {
		return cell;
	}


	public void setCell(String cell) {
		this.cell = cell;
	}

	@JsonView(Views.PublicView.class)
	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}

	@JsonView(Views.PublicView.class)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	@JsonView(Views.PublicView.class)
	public boolean isDbOemPinkongUser() {
		return dbOemPinkongUser;
	}

	public void setDbOemPinkongUser(boolean dbOemPinkongUser) {
		this.dbOemPinkongUser = dbOemPinkongUser;
	}

	@JsonView(Views.PublicView.class)
	public boolean isErtjjPinguan() {
		return ertjjPinguan;
	}

	public void setErtjjPinguan(boolean ertjjPinguan) {
		this.ertjjPinguan = ertjjPinguan;
	}

	@JsonView(Views.PublicView.class)
	public boolean isErtjjYunying() {
		return ertjjYunying;
	}

	public void setErtjjYunying(boolean ertjjYunying) {
		this.ertjjYunying = ertjjYunying;
	}

	@JsonView(Views.PublicView.class)
	public boolean isErtjjPurchasManager() {
		return ertjjPurchasManager;
	}

	public void setErtjjPurchasManager(boolean ertjjPurchasManager) {
		this.ertjjPurchasManager = ertjjPurchasManager;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
