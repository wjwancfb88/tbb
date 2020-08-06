package com.dhwooden.ep.modal;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author wk
 * @since 2018-05-11
 */
@TableName("dh_equipment_work")
public class EquipmentWork extends Model<EquipmentWork> {

    private static final long serialVersionUID = 1L;

	private String id;
	private String equipmentCode;
	private Date date;
	private String problemMessage;
	private String remark;
	private String creator;
	private String dealer;

	public String getProblemMessage() {
		return problemMessage;
	}

	public void setProblemMessage(String problemMessage) {
		this.problemMessage = problemMessage;
	}

	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}



	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDealer() {
		return dealer;
	}

	public void setDealer(String dealer) {
		this.dealer = dealer;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "EquipmentWork{" +
			"id=" + id +
			", equipment_code=" + equipmentCode +
			", date=" + date +
			", problem_message=" + problemMessage +
			", remark=" + remark +
			", creator=" + creator +
			", dealer=" + dealer +
			"}";
	}
}
