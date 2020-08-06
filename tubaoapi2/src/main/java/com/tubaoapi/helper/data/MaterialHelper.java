package com.tubaoapi.helper.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tubaoapi.model.Material;
import com.tubaoapi.model.MaterialGroup;
import com.tubaoapi.model.MeasureUnit;
import com.tubaoapi.model.so.MaterialGroupSO;
import com.tubaoapi.model.so.MeasureUnitSO;
import com.tubaoapi.modules.utils.Collections3;
import com.tubaoapi.service.MaterialGroupService;
import com.tubaoapi.service.MeasureUnitService;

@Component
public class MaterialHelper {
	
	@Autowired
	private MaterialGroupService materialGroupService;
	
	@Autowired
	private MeasureUnitService measureUnitService;
	
	
	public void setGroups(List<Material> materials){
		
		Set<String> groupIds  = Collections3.extractToSet(materials, "materialGroupId");
		if(groupIds.size()==0){
			return;
		}
		MaterialGroupSO groupSO = new MaterialGroupSO();
		groupSO.setIds(groupIds);
		List<MaterialGroup> groups = materialGroupService.findBySO(groupSO);
		if(groups.size()>0){
			Map<String,MaterialGroup> map = new HashMap<>();
			for(MaterialGroup group:groups){
				map.put(group.getId(), group);
			}
			
			for(Material material:materials){
				material.setMaterialGroup(map.get(material.getMaterialGroupId()));
			}
		}
	}
	
	
	public void setMeasureUnits(List<Material> materials){
		Set<String> unitIds  = Collections3.extractToSet(materials, "baseUnitId");
		unitIds.addAll(Collections3.extractToSet(materials, "assistUnitId"));
		
		if(unitIds.size()==0){
			return;
		}
		
		MeasureUnitSO unitSO = new MeasureUnitSO();
		unitSO.setIds(unitIds);
		List<MeasureUnit> units = measureUnitService.findBySO(unitSO);
		
		if(units.size()>0){
			Map<String,MeasureUnit> unitMap = new HashMap<>();
			for(MeasureUnit unit:units){
				unitMap.put(unit.getId(), unit);
			}
			
			for(Material material:materials){
				material.setBaseUnit(unitMap.get(material.getBaseUnitId()));
				material.setAssistUnit(unitMap.get(material.getAssistUnitId()));
			}
		}
	}
}
