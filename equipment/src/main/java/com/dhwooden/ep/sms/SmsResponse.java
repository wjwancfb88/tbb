package com.dhwooden.ep.sms;

public class SmsResponse {
	private int code =-1;
	private String msg;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	public boolean isSuccess(){
		return code ==0;
	}
	
	
}
