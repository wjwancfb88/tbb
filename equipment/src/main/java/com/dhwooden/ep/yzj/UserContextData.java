package com.dhwooden.ep.yzj;


import com.dhwooden.ep.yzj.mapper.JsonMapper;

public class UserContextData {
	/**
	 * 轻应用id
	 */
	private String appid;
	/**
	 * 云之家工作圈id
	 */
	private String eid;
	/**
	 * 用户id
	 */
	private String openid;
	/**
	 * 用户姓名
	 */
	private String username;
	/**
	 * 云平台用户id
	 */
	private String uid;
	/**
	 * 云平台工作圈id
	 */
	private String tid;
	/**
	 * 云之家用户id
	 */
	private String userid;
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Override
	public String toString() {
		return JsonMapper.nonDefaultMapper().toJson(this);
	}
}
