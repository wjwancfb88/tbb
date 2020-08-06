package com.dhwooden.ep.yzj;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AccessTokenData {
	private String expires_in;
	private String access_token;
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
