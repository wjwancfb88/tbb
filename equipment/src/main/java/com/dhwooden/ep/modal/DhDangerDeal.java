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
 * @since 2018-09-29
 */
@TableName("dh_danger_deal")
public class DhDangerDeal extends Model<DhDangerDeal> {

    private static final long serialVersionUID = 1L;

    /**
     * 隐患记录id
     */
	private String dangerId;
    /**
     * 图片路径
     */
	private String dealUrl;
    /**
     * 整改方式
     */
	private String dealWay;
    /**
     * 整改人
     */
	private String dealer;
    /**
     * 整改时间
     */
	private LocalDateTime dealDate;


	public String getDangerId() {
		return dangerId;
	}

	public void setDangerId(String dangerId) {
		this.dangerId = dangerId;
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

	public String getDealer() {
		return dealer;
	}

	public void setDealer(String dealer) {
		this.dealer = dealer;
	}

	public LocalDateTime getDealDate() {
		return dealDate;
	}

	public void setDealDate(LocalDateTime dealDate) {
		this.dealDate = dealDate;
	}

	@Override
	protected Serializable pkVal() {
		return null;
	}
}
