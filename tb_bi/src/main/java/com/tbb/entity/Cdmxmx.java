package com.tbb.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * ${table.comment}
 * </p>
 *
 * @author cfb
 * @since 2020-04-03
 */
@TableName("E__CDMXMX")
public class Cdmxmx extends Model<Cdmxmx> {

    private static final long serialVersionUID = 1L;

    /**
     * 板面积
     */
	@TableField("C__BANMIANJIM")
	private BigDecimal banmianjim;
	/**
	 * 面板颜色
	 */
	@TableField("C__MIANBANYANSE")
	private String mianbanyanse;
	/**
	 * 板厚
	 */
	@TableField("C__BANHOUMM")
	private String banhoumm;
	/**
	 * 拆单明细明细id
	 */
	@TableField("C__CDMXMXID")
	private String cdmxmxId;
    /**
     * 拆单明细id
     */
    @TableId("C__MX1ID")
	private String mx1Id;
	/**
	 * 创建时间
	 */
	@TableField("CREATEDON")
	private String createdOn;

	public String getMx1Id() {
		return mx1Id;
	}

	public Cdmxmx setMx1Id(String mx1Id) {
		this.mx1Id = mx1Id;
		return this;
	}
	public String getCreatedOn() {
		return createdOn;
	}

	public Cdmxmx setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
		return this;
	}

	public BigDecimal getBanmianjim() {
		return banmianjim;
	}

	public void setBanmianjim(BigDecimal banmianjim) {
		this.banmianjim = banmianjim;
	}

	public String getMianbanyanse() {
		return mianbanyanse;
	}

	public void setMianbanyanse(String mianbanyanse) {
		this.mianbanyanse = mianbanyanse;
	}
	public String getBanhoumm() {
		return banhoumm;
	}

	public void setBanhoumm(String banhoumm) {
		this.banhoumm = banhoumm;
	}

	public String getCdmxmxId() {
		return cdmxmxId;
	}

	public void setCdmxmxId(String cdmxmxId) {
		this.cdmxmxId = cdmxmxId;
	}

	@Override
	protected Serializable pkVal() {
		return this.cdmxmxId;
	}

	@Override
	public String toString() {
		return "Cdmxmx{" +
				"banmianjim=" + banmianjim +
				", mianbanyanse='" + mianbanyanse + '\'' +
				", banhoumm='" + banhoumm + '\'' +
				", cdmxmxId='" + cdmxmxId + '\'' +
				", mx1Id='" + mx1Id + '\'' +
				", createdOn='" + createdOn + '\'' +
				'}';
	}
}
