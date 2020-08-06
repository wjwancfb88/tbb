package com.dhwooden.ep.modal;

import com.baomidou.mybatisplus.activerecord.Model;

import java.time.LocalDate;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author wk
 * @since 2018-11-20
 */
@TableName("dh_canteen")
public class DhCanteen extends Model<DhCanteen> {

    private static final long serialVersionUID = 1L;

	private String id;
	private Integer pnum;
	private String dept;
	private LocalDate orderTime;
	private String type;
	private String seat;
	private Date createTime;
	private String creator;
	private String deleted;
	private String remark;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getPnum() {
		return pnum;
	}

	public void setPnum(Integer pnum) {
		this.pnum = pnum;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public LocalDate getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalDate orderTime) {
		this.orderTime = orderTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
