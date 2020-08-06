package com.tubaoapi.api.mes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.API;
import com.tubaoapi.api.base.ParamsCheck;
import com.tubaoapi.api.base.Result;
import com.tubaoapi.api.base.Views;
import com.tubaoapi.helper.data.MaterialHelper;
import com.tubaoapi.model.*;
import com.tubaoapi.model.so.KDBdMultiMeasureUnitSO;
import com.tubaoapi.model.so.MaterialReqBillSO;
import com.tubaoapi.model.so.MaterialSO;
import com.tubaoapi.modules.persistence.pagination.model.Page;
import com.tubaoapi.modules.persistence.pagination.model.PageRequest;
import com.tubaoapi.modules.utils.DateUtil;
import com.tubaoapi.modules.utils.DateUtils2;
import com.tubaoapi.service.MaterialReqBillEntryService;
import com.tubaoapi.service.MaterialReqBillPriceInfoService;
import com.tubaoapi.service.MaterialReqBillService;
import com.tubaoapi.service.MaterialService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

/**
 * 领料出库单
 * Created by wk on 2017/7/24.
 */
@RestController
@RequestMapping(value = "/api/mes/materialreq")
public class MesMaterialReqBillRest {

    @Autowired
    private MaterialReqBillService materialReqBillService;
    @Autowired
    private MaterialReqBillEntryService materialReqBillEntryService;
    @Autowired
    private MaterialReqBillPriceInfoService materialReqBillPriceInfoService;

    @RequestMapping(value="", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public  Map addMaterialReq(
            @RequestBody String materialBill) {
            JSONObject jsStr = JSONObject.parseObject(materialBill);
    String t=jsStr.getString("items");
    JSONArray json = JSONArray.parseArray(t);
    List<BillMaterial> list=json.toJavaList(BillMaterial.class);
    String success =materialReqBillService.insertMaterialReqBill(list);
    Map msg=new HashMap();
    msg.put("msg","fnumber"+success);
    return msg;

}

    @RequestMapping(value="", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.PublicView.class)
    public Result<BillMaterial> list(
            String fnumber){

        fnumber = StringUtils.trimToEmpty(fnumber);
//        page = StringUtils.trimToEmpty(page);
//        maxResults = StringUtils.trimToEmpty(maxResults);
//        autoCount = StringUtils.trimToEmpty(autoCount);
//
//        ParamsCheck.checkUnsignedInteger("page", page);
//        ParamsCheck.checkUnsignedInteger("maxResults", maxResults);
//        ParamsCheck.checkBoolean("autoCount", autoCount);
//        ParamsCheck.checkBoolean("loadUnit", loadUnit);
//        ParamsCheck.checkBoolean("loadGroup", loadGroup);

//        int maxResultsInt = Integer.parseInt(maxResults);
//        ParamsCheck.checkRange("maxResults", maxResultsInt, 1, API.MAX_RESULTS);
        MaterialReqBillSO materialReqBillSO=new MaterialReqBillSO();
        materialReqBillSO.setFnumber(fnumber);
        List<BillMaterial>  bill=materialReqBillService.findBySO(materialReqBillSO);
        Result<BillMaterial> result = new Result<>();
        result.setKind(Result.KIND_MATERIAL_LIST);
        result.setItems(bill);
        return result;
    }

}
