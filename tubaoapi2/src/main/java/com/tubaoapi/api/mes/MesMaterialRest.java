package com.tubaoapi.api.mes;

import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.API;
import com.tubaoapi.api.base.ParamsCheck;
import com.tubaoapi.api.base.Result;
import com.tubaoapi.api.base.Views;
import com.tubaoapi.helper.data.MaterialHelper;
import com.tubaoapi.model.Material;
import com.tubaoapi.model.so.MaterialSO;
import com.tubaoapi.modules.persistence.pagination.model.Page;
import com.tubaoapi.modules.persistence.pagination.model.PageRequest;
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
@RequestMapping(value = "/api/mes/materials")
public class MesMaterialRest {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private MaterialHelper materialHelper;



    @RequestMapping(value="", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.PublicView.class)
    public Result<Material> list(
            String id,
            String number,
            String q,
            String date,
            @RequestParam(defaultValue="1") String page,
            @RequestParam(defaultValue= API.DEFAULT_MAX_RESULTS) String maxResults,
            @RequestParam(defaultValue="false") String autoCount,
            @RequestParam(defaultValue="false") String loadUnit,
            @RequestParam(defaultValue="false") String loadGroup){


        id = StringUtils.trimToEmpty(id);
        number = StringUtils.trimToEmpty(number);
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
        MaterialSO so = new  MaterialSO();
        so.setClient(API.CLIENT_MES);
        API.setIdOrIdsToBaseSO(so, id);
        so.setNumber(number);
        so.setQ(q);
        so.setLastUpdateTime(date);
        //分页查询数据
        Page<Material> pageInfo = materialService.findBySO(so,pageRequest);
        Result<Material> result = new Result<>();
        result.setKind(Result.KIND_MATERIAL_LIST);
        result.setItems(pageInfo.getContent());

        if(autoCountBoolean){
            result.setPageInfo(pageInfo);
        }

        if(loadUnitBoolean){
            materialHelper.setMeasureUnits(pageInfo.getContent());
        }

        if(loadGroupBoolean){
            materialHelper.setGroups(pageInfo.getContent());
        }
        return result;
    }


    
}
