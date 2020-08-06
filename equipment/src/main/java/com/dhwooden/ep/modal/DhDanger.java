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
 * @since 2018-09-20
 */
@TableName("dh_danger")
public class DhDanger extends Model<DhDanger> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private String id;
    /**
     * 图片路径
     */
	private String imgUrl;
    /**
     * 类型
     */
	private String type;
    /**
     * 责任部门
     */
	private String dutyDept;
    /**
     * 部门id
     */
	private String deptId;
    /**
     * 隐患描述
     */
	private String dangerDescript;
    /**
     * 整改人id
     */
	private String dangerDealer;
    /**
     * 整改人电话
     */
	private String dangerPhone;
    /**
     * 整改结果
     */
	private String dealResult;
    /**
     * 隐患状态
     */
	private String dangerStatus;
    /**
     * 整改人姓名
     */
	private String dealerName;

	private String  isNine;

	private String nowManager;


	public String getNowManager() {
		return nowManager;
	}

	public void setNowManager(String nowManager) {
		this.nowManager = nowManager;
	}

	public String getIsNine() {
		return isNine;
	}

	public void setIsNine(String isNine) {
		this.isNine = isNine;
	}

	/**
	 *
	 * @return
	 */
   private String dealUrl;


	private String dealWay;
	private String dealManger;

	private String  isActive;


	private String isHq;


	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsHq() {
		return isHq;
	}

	public void setIsHq(String isHq) {
		this.isHq = isHq;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getDealManger() {
		return dealManger;
	}

	public void setDealManger(String dealManger) {
		this.dealManger = dealManger;
	}

	public String getDealUrl() {
		return dealUrl;
	}

	public void setDealUrl(String dealUrl) {
		this.dealUrl = dealUrl;
	}

	public String getDealWay() {
		return dealWay;
	}

	public void setDealWay(String dealWay) {
		this.dealWay = dealWay;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDutyDept() {
		return dutyDept;
	}

	public void setDutyDept(String dutyDept) {
		this.dutyDept = dutyDept;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDangerDescript() {
		return dangerDescript;
	}

	public void setDangerDescript(String dangerDescript) {
		this.dangerDescript = dangerDescript;
	}

	public String getDangerDealer() {
		return dangerDealer;
	}

	public void setDangerDealer(String dangerDealer) {
		this.dangerDealer = dangerDealer;
	}

	public String getDangerPhone() {
		return dangerPhone;
	}

	public void setDangerPhone(String dangerPhone) {
		this.dangerPhone = dangerPhone;
	}

	public String getDealResult() {
		return dealResult;
	}

	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
	}

	public String getDangerStatus() {
		return dangerStatus;
	}

	public void setDangerStatus(String dangerStatus) {
		this.dangerStatus = dangerStatus;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
