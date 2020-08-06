package com.tubaoapi.modules.persistence.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.Views;




public abstract class IdModel<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	protected T id;
	

	@JsonView(Views.PublicView.class)
	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}


	
	
}
