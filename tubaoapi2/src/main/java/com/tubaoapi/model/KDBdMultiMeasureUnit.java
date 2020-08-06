package com.tubaoapi.model;

import java.math.BigDecimal;

public class KDBdMultiMeasureUnit {
	private String id;
	private String materialId;
	private String measureUnitId;
	private BigDecimal baseConvsRate;
	/**
	 * 计算单位QTY精度
	 */
	private int qtyPrecision;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getMeasureUnitId() {
		return measureUnitId;
	}
	public void setMeasureUnitId(String measureUnitId) {
		this.measureUnitId = measureUnitId;
	}
	public BigDecimal getBaseConvsRate() {
		return baseConvsRate;
	}
	public void setBaseConvsRate(BigDecimal baseConvsRate) {
		this.baseConvsRate = baseConvsRate;
	}
	public int getQtyPrecision() {
		return qtyPrecision;
	}
	public void setQtyPrecision(int qtyPrecision) {
		this.qtyPrecision = qtyPrecision;
	}
}
