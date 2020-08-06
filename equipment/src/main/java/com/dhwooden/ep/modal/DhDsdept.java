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
 * @since 2018-11-21
 */
@TableName("dh_dsdept")
public class DhDsdept extends Model<DhDsdept> {

    private static final long serialVersionUID = 1L;

	private Integer id;
	private String deptName;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
