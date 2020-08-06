package com.dhwooden.ep.modal;

import com.baomidou.mybatisplus.activerecord.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
@TableName("dh_opinion")
public class DhOpinion extends Model<DhOpinion> {

    private static final long serialVersionUID = 1L;

	private String id;
	private String dangerId;
	private String opinionManager;
	private String view;
	private LocalDateTime viewTime;
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDateTime getViewTime() {
		return viewTime;
	}

	public void setViewTime(LocalDateTime viewTime) {
		this.viewTime = viewTime;
	}




	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDangerId() {
		return dangerId;
	}

	public void setDangerId(String dangerId) {
		this.dangerId = dangerId;
	}

	public String getOpinionManager() {
		return opinionManager;
	}

	public void setOpinionManager(String opinionManager) {
		this.opinionManager = opinionManager;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}



	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
