package com.tubaoapi.api.fcs;

import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.API;
import com.tubaoapi.api.base.ParamsCheck;
import com.tubaoapi.api.base.Result;
import com.tubaoapi.api.base.Views;
import com.tubaoapi.helper.data.MaterialHelper;
import com.tubaoapi.model.Inventory;
import com.tubaoapi.model.Material;
import com.tubaoapi.model.so.InventorySO;
import com.tubaoapi.model.so.MaterialSO;
import com.tubaoapi.modules.persistence.pagination.model.Page;
import com.tubaoapi.modules.persistence.pagination.model.PageRequest;
import com.tubaoapi.service.InventoryService;
import com.tubaoapi.service.MaterialService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wk on 2017/7/24.
 */
@RestController
@RequestMapping(value = "/api/fcs/inventory")
public class FcsInventoryRest {


    @Autowired
    private InventoryService inventoryService;



    @RequestMapping(value="", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.PublicView.class)
    public Result<Inventory> list(
            String fStorageOrgUnitID,
            String date,
            @RequestParam(defaultValue="1") String page,
            @RequestParam(defaultValue= API.DEFAULT_MAX_RESULTS) String maxResults,
            @RequestParam(defaultValue="false") String autoCount,
            @RequestParam(defaultValue="false") String loadUnit,
            @RequestParam(defaultValue="false") String loadGroup){
        page = StringUtils.trimToEmpty(page);
        maxResults = StringUtils.trimToEmpty(maxResults);
        autoCount = StringUtils.trimToEmpty(autoCount);

        ParamsCheck.checkUnsignedInteger("page", page);
        ParamsCheck.checkUnsignedInteger("maxResults", maxResults);
        ParamsCheck.checkBoolean("autoCount", autoCount);
        ParamsCheck.checkBoolean("loadUnit", loadUnit);
        ParamsCheck.checkBoolean("loadGroup", loadGroup);

        int maxResultsInt = Integer.parseInt(maxResults);
        ParamsCheck.checkRange("maxResults", maxResultsInt, 1, API.MAX_RESULTS);


        int pageInt = Integer.parseInt(page);
        boolean autoCountBoolean = Boolean.valueOf(autoCount);
        boolean loadUnitBoolean = Boolean.valueOf(loadUnit);
        boolean loadGroupBoolean = Boolean.valueOf(loadGroup);
        PageRequest pageRequest = new PageRequest(pageInt,maxResultsInt,autoCountBoolean);
        //设置过滤条件
        InventorySO so = new  InventorySO();
        so.setLastUpdateTime(date);
        so.setfStorageOrgUnitID(fStorageOrgUnitID);
        //分页查询数据
        Page<Inventory> pageInfo = inventoryService.findBySO(so,pageRequest);
        Result<Inventory> result = new Result<>();
        result.setKind(Result.KIND_MATERIAL_LIST);
        result.setItems(pageInfo.getContent());

        if(autoCountBoolean){
            result.setPageInfo(pageInfo);
        }




        return result;
    }
}
