package com.tubaoapi.model.so;

public class MaterialGroupSO extends BaseSO<String> {
	private String groupStandardId;
	private String parentId;
	private Integer level;
	
	public String getGroupStandardId() {
		return groupStandardId;
	}
	public void setGroupStandardId(String groupStandardId) {
		this.groupStandardId = groupStandardId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	
	
}
