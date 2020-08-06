package com.tbb.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * ${table.comment}
 * </p>
 *
 * @author cfb
 * @since 2020-04-03
 */
@TableName("e__mx1")
public class Mx1 extends Model<Mx1> {

    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
	@TableField("createdOn")
	private String createdOn;
    /**
     * 拆单明细id
     */
    @TableId("c__mx1Id")
	private String mx1Id;

	public String getCreatedOn() {
		return createdOn;
	}

	public Mx1 setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
		return this;
	}

	public String getMx1Id() {
		return mx1Id;
	}

	public Mx1 setMx1Id(String mx1Id) {
		this.mx1Id = mx1Id;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.mx1Id;
	}

}
