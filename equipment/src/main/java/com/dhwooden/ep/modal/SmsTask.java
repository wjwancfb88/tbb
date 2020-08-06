package com.dhwooden.ep.modal;

import java.util.Date;


public class SmsTask {


	private String id;

	private String cell;
	
	private Date createTime;
	
	private String txt;
	
	public SmsTask(){}
	public SmsTask(String id, String cell, String txt){
		this.id = id;
		this.cell = cell;
		this.txt = txt;
		this.createTime = new Date();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}


	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	

}