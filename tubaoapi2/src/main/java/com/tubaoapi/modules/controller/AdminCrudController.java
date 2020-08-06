package com.tubaoapi.modules.controller;


public abstract class AdminCrudController extends AdminBaseController{

	
	public static final String ACTION = "action";
	public static final String CREATE = "create";
	public static final String EDIT = "edit";
	public static final String DELETE = "delete";
	
	

	

	
	public boolean isCreate(String action){
		return CREATE.equals(action);
	}
	
	public boolean isEdit(String action){
		return EDIT.equals(action);
	}

	

	
}
