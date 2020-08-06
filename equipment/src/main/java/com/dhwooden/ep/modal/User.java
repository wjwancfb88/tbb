package com.dhwooden.ep.modal;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wk
 * @since 2018-04-18
 */
@TableName("dh_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

	private String id;
	private String department;
	private String status;
	private String orgId;
	private String name;
	private String jobTitle;
	private String orgUserType;
	private String email;
	private String phone;
	private String openId;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getOrgUserType() {
		return orgUserType;
	}

	public void setOrgUserType(String orgUserType) {
		this.orgUserType = orgUserType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", department=" + department +
			", status=" + status +
			", orgId=" + orgId +
			", name=" + name +
			", jobTitle=" + jobTitle +
			", orgUserType=" + orgUserType +
			", email=" + email +
			", phone=" + phone +
			", openId=" + openId +
			"}";
	}
}
