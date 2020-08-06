package com.tubaoapi.helper.data;

import com.tubaoapi.model.KDBdMultiMeasureUnit;
import com.tubaoapi.model.Material;
import com.tubaoapi.model.so.KDBdMultiMeasureUnitSO;
import com.tubaoapi.model.so.MaterialSO;
import com.tubaoapi.service.KDBdMultiMeasureUnitService;
import com.tubaoapi.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kaiwang on 2018/7/16.
 */
@Component
public class AssistunitHelper {
    @Autowired
    private MaterialService materialService;

    @Autowired
    private KDBdMultiMeasureUnitService kdBdMultiMeasureUnitService;

    public Map getUnit(String materialId,BigDecimal qty){
        String assistunit="";
        String baseunit="";
        MaterialSO materialSO=new MaterialSO();
        if(materialId!=null&&materialId!=""){
            materialSO.setId(materialId);
            List<Material>  material=materialService.findBySO(materialSO);
            if(material!=null&&material.size()>0){
                assistunit=material.get(0).getAssistUnitId();
                baseunit=material.get(0).getBaseUnitId();
            }
        }
        KDBdMultiMeasureUnitSO so = new KDBdMultiMeasureUnitSO();
        so.setMaterialId(materialId);
        so.setMeasureUnitId(baseunit);
        List<KDBdMultiMeasureUnit> l = kdBdMultiMeasureUnitService.findBySO(so);
        BigDecimal baseCovsRate=l.size()>0?l.get(0).getBaseConvsRate():null;
        BigDecimal assistQty= BigDecimal.valueOf(0);
        if(l.size()>0){
            KDBdMultiMeasureUnit multiUnit=l.get(0);
            if(multiUnit!=null &&  multiUnit.getBaseConvsRate()!=null && multiUnit.getBaseConvsRate().doubleValue()>0){
                assistQty = qty.divide(multiUnit.getBaseConvsRate(), multiUnit.getQtyPrecision(), BigDecimal.ROUND_HALF_UP);
            }
        }
        Map map=new HashMap();
        map.put("assistQty",assistQty);
        map.put("baseCovsRate",baseCovsRate);
        map.put("assistunit",assistunit);
        map.put("baseunit",baseunit);
        return map;

    }



}
