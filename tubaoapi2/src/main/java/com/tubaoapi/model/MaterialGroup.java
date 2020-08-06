package com.tubaoapi.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.Views;
import com.tubaoapi.modules.persistence.model.IdModel;

public class MaterialGroup extends IdModel<String> {
	

	private static final long serialVersionUID = 1636080189716711773L;

	public static final String GROUP_STANDARD_BASE = "dR8lnQEPEADgAAWKwKgSxZeb4R8="; 

	/**
	 * 分类代码
	 */
	@JsonView(Views.PublicView.class)
	private String number;

	/**
	 * 分类长代码
	 */
	@JsonView(Views.PublicView.class)
	private String longNumber;

	/**
	 * 商品分类名称
	 */
	@JsonView(Views.PublicView.class)
	private String name;

	/**
	 * 是否是叶子
	 */
	@JsonView(Views.PublicView.class)
	private boolean leaf;

	/**
	 * 层级
	 */
	@JsonView(Views.PublicView.class)
	private int level;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getLongNumber() {
		return longNumber;
	}

	public void setLongNumber(String longNumber) {
		this.longNumber = longNumber;
	}


	public String getName() {
		return StringUtils.trimToEmpty(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
