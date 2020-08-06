package com.dhwooden.ep.base;

import java.io.Serializable;

public class Message   implements Serializable {
	public Message(){
		
	}
	
	public Message(int code, String message){
		this.code = code;
		this.message = message;
	}

	private int code;
	private String message;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
