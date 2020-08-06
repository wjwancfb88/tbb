package com.tubaoapi.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tubaoapi.api.base.Views;
import com.tubaoapi.modules.persistence.model.IdModel;

/**
 *
 */
public class Material extends IdModel<String>{
	private static final long serialVersionUID = -7220320853322328753L;

	/**
	 * 状态 
	 * 1-启用
	 */
	private int status;

	private String number;

	private String name;
	
	/**
	 * 规格型号
	 */
	private String model;

	private String materialGroupId;
	
	private MaterialGroup materialGroup;

	private String baseUnitId;
	/**
	 * 基本单位
	 */
	private MeasureUnit baseUnit;
	
	private int thinkerStatus;


	private String lastUpdateTime;

	/**
	 * 辅助单位ID
	 */
	private String assistUnitId;
	
	private MeasureUnit assistUnit;

	/**
	 * 默认供应商id
	 */
	private String cfDefaultSupplierId;

	/**
	 * 是否是易装物料 - 供应易采平台使用
	 */
	private boolean yzMaterial;
	/**
	 * 是否是易采平台物料 - 供应易采平台使用
	 * 易采平台物料包含易装物料
	 */
	private boolean ycptMaterial;




	@JsonView(Views.LebView.class)
	public String getCfDefaultSupplierId() {
		return cfDefaultSupplierId;
	}

	public void setCfDefaultSupplierId(String cfDefaultSupplierId) {
		this.cfDefaultSupplierId = cfDefaultSupplierId;
	}

	@JsonView(Views.PublicView.class)
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@JsonView(Views.PublicView.class)
	public String getNumber() {
		return StringUtils.trimToEmpty(number);
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@JsonView(Views.PublicView.class)
	public String getName() {
		return StringUtils.trimToEmpty(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonView(Views.PublicView.class)
	public String getModel() {
		return StringUtils.trimToEmpty(model);
	}

	public void setModel(String model) {
		this.model = model;
	}

	@JsonView(Views.PublicView.class)
	@JsonInclude(Include.NON_NULL)
	public String getMaterialGroupId() {
		return materialGroupId;
	}

	public void setMaterialGroupId(String materialGroupId) {
		this.materialGroupId = materialGroupId;
	}

	@JsonView(Views.PublicView.class)
	@JsonInclude(Include.NON_NULL)
	public String getBaseUnitId() {
		return baseUnitId;
	}

	public void setBaseUnitId(String baseUnitId) {
		this.baseUnitId = baseUnitId;
	}

	@JsonView(Views.PublicView.class)
	@JsonInclude(Include.NON_NULL)
	public String getAssistUnitId() {
		return assistUnitId;
	}

	public void setAssistUnitId(String assistUnitId) {
		this.assistUnitId = assistUnitId;
	}

	@JsonView(Views.PublicView.class)
	@JsonInclude(Include.NON_NULL)
	public MeasureUnit getBaseUnit() {
		return baseUnit;
	}

	public void setBaseUnit(MeasureUnit baseUnit) {
		this.baseUnit = baseUnit;
	}

	@JsonView(Views.PublicView.class)
	@JsonInclude(Include.NON_NULL)
	public MeasureUnit getAssistUnit() {
		return assistUnit;
	}

	public void setAssistUnit(MeasureUnit assistUnit) {
		this.assistUnit = assistUnit;
	}

	
	@JsonView(Views.PublicView.class)
	@JsonInclude(Include.NON_NULL)
	public MaterialGroup getMaterialGroup() {
		return materialGroup;
	}

	public void setMaterialGroup(MaterialGroup materialGroup) {
		this.materialGroup = materialGroup;
	}
	
	
	@JsonView(Views.ThinkerView.class)
	public int getThinkerStatus() {
		return thinkerStatus;
	}

	public void setThinkerStatus(int thinkerStatus) {
		this.thinkerStatus = thinkerStatus;
	}

	@JsonView(Views.PublicView.class)
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@JsonView(Views.PublicView.class)
	public boolean isYzMaterial() {
		return yzMaterial;
	}

	public void setYzMaterial(boolean yzMaterial) {
		this.yzMaterial = yzMaterial;
	}

	@JsonView(Views.PublicView.class)
	public boolean isYcptMaterial() {
		return ycptMaterial;
	}

	public void setYcptMaterial(boolean ycptMaterial) {
		this.ycptMaterial = ycptMaterial;
	}
}
