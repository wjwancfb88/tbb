package com.tubaoapi.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.Views;
import com.tubaoapi.modules.persistence.model.IdModel;


public class MeasureUnit extends IdModel<String> {

	private static final long serialVersionUID = -9122095087983227269L;
	private String name;

	
	@JsonView(Views.PublicView.class)
	public String getName() {
		return StringUtils.trimToEmpty(name);
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
