package com.dhwooden.ep.yzj;


import com.dhwooden.ep.yzj.mapper.JsonMapper;

public class YzjResult<D> {
	private boolean success;
	private String error;
	private String errorCode;
	private D data;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public D getData() {
		return data;
	}
	public void setData(D data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return JsonMapper.nonDefaultMapper().toJson(this);
	}
}
