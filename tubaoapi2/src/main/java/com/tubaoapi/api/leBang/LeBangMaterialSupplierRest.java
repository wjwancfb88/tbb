package com.tubaoapi.api.leBang;

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
@RequestMapping(value = "/api/leb/msconnection")
public class LeBangMaterialSupplierRest {


    @Autowired
    private MaterialService materialService;

    @Autowired
    private MaterialHelper materialHelper;


    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.LebView.class)
    public Result<String> list(
            String id,
            String supplierId,
            String date,
            @RequestParam(defaultValue = "1") String page,
            @RequestParam(defaultValue = API.DEFAULT_MAX_RESULTS) String maxResults,
            @RequestParam(defaultValue = "false") String autoCount){


        id = StringUtils.trimToEmpty(id);
        page = StringUtils.trimToEmpty(page);
        maxResults = StringUtils.trimToEmpty(maxResults);
        autoCount = StringUtils.trimToEmpty(autoCount);

        ParamsCheck.checkUnsignedInteger("page", page);
        ParamsCheck.checkUnsignedInteger("maxResults", maxResults);
        ParamsCheck.checkBoolean("autoCount", autoCount);

        int maxResultsInt = Integer.parseInt(maxResults);
        ParamsCheck.checkRange("maxResults", maxResultsInt, 1, API.MAX_RESULTS);


        int pageInt = Integer.parseInt(page);
        boolean autoCountBoolean = Boolean.valueOf(autoCount);
        PageRequest pageRequest = new PageRequest(pageInt, maxResultsInt, autoCountBoolean);
        //设置过滤条件
        MaterialSO so = new MaterialSO();
        so.setClient(API.CLIENT_LEB);
        API.setIdOrIdsToBaseSO(so, id);
        so.setSupplierId(supplierId);
        so.setLastUpdateTime(date);
        //分页查询数据
        Page<String> pageInfo = materialService.findBySupplier(so, pageRequest);
        Result<String> result = new Result<>();
        result.setKind(Result.KIND_MATERIAL_SUPPLIER_LIST);
        result.setItems(pageInfo.getContent());

        if (autoCountBoolean) {
            result.setPageInfo(pageInfo);
        }

        return result;
    }

    @RequestMapping(value="/update", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.LebView.class)
    public String update(String id){
        Material material=new Material();
        material.setId(id);
        if(!id.equals(null)&&!id.equals("")) {
            materialService.update(material);
        }else{
            return "id error ";
        }
        return null;
    }

}