package com.dhwooden.ep.yzj;

public class YzjApp {
	private String id;
	private String secret;
	
	public YzjApp(String id ,String secret){
		this.id = id;
		this.secret = secret;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	
	public YzjResult<AccessTokenData> getAccessToken(){
		return YzjUtils.getAccessToken(id, secret);
	}
}
