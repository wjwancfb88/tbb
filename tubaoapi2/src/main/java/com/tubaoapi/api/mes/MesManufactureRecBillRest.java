package com.tubaoapi.api.mes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.Views;
import com.tubaoapi.helper.data.AssistunitHelper;
import com.tubaoapi.model.*;
import com.tubaoapi.model.so.KDBdMultiMeasureUnitSO;
import com.tubaoapi.model.so.MaterialSO;
import com.tubaoapi.model.so.MeasureUnitSO;
import com.tubaoapi.modules.utils.DateUtils2;
import com.tubaoapi.modules.utils.RadomFour;
import com.tubaoapi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生产入库单
 * Created by kaiwang on 2018/6/26.
 */
@RestController
@RequestMapping(value = "/api/mes/manufacture")
public class MesManufactureRecBillRest {
    @Autowired
    private ManufactureRecBillService manufactureRecBillService;
    @Autowired
    private ManufactureRecBillEntryService manufactureRecBillEntryService;
    @Autowired
    private MeasureUnitService measureUnitService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private KDBdMultiMeasureUnitService kdBdMultiMeasureUnitService;

    @RequestMapping(value="", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public  Map addmanufactureRec(
            @RequestBody String materialBill) {
        JSONObject jsStr = JSONObject.parseObject(materialBill);
        String t=jsStr.getString("items");
        JSONArray json = JSONArray.parseArray(t);
        List<BillFacture> list=json.toJavaList(BillFacture.class);;
        Integer success=manufactureRecBillService.insertManufactureRecBill(list);
        Map msg=new HashMap();
        msg.put("msg","create_success:"+success);
        return msg;
    }


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
