package com.tubaoapi.model.so;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

public class MaterialSO extends BaseSO<String>{
	private String client;
	private String number;
	private Collection<String> materialGroupIds;
	private String lastUpdateTime;
	private String createTime;
	private String fLongNumber;
	private Collection<String> materialIds;
	private String supplierId;
	private Boolean ycptMaterial;

	public Collection<String> getMaterialIds() {
		return materialIds;
	}

	public void setMaterialIds(Collection<String> materialIds) {
		this.materialIds = materialIds;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getfLongNumber() {
		return fLongNumber;
	}

	public void setfLongNumber(String fLongNumber) {
		this.fLongNumber = fLongNumber;
	}

	public String getLastUpdateTime() {
		return StringUtils.trimToNull(lastUpdateTime);
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		if(StringUtils.isNotBlank(number)){
			this.number = number;
		}
	}

	public Collection<String> getMaterialGroupIds() {
		return materialGroupIds;
	}

	public void setMaterialGroupIds(Collection<String> materialGroupIds) {
		if(materialGroupIds!=null && materialGroupIds.size()>0){
			this.materialGroupIds = materialGroupIds;
		}
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Boolean getYcptMaterial() {
		return ycptMaterial;
	}

	public void setYcptMaterial(Boolean ycptMaterial) {
		this.ycptMaterial = ycptMaterial;
	}

	public String getCreateTime() {
		return StringUtils.trimToNull(createTime);
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
