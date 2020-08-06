package com.dhwooden.ep.modal;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author wk
 * @since 2018-11-08
 */
@TableName("dh_manager")
public class DhManager extends Model<DhManager> {

    private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String phone;
	private String jobNo;
	private String dept;
	private  String role;




	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
