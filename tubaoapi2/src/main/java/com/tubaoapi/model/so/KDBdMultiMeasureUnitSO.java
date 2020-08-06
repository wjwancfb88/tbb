package com.tubaoapi.model.so;

public class KDBdMultiMeasureUnitSO extends BaseSO<String> {
	private String materialId;
	private String measureUnitId;
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
}
