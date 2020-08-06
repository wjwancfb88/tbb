package com.tubaoapi.api.crm;

import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.API;
import com.tubaoapi.api.base.ParamsCheck;
import com.tubaoapi.api.base.Result;
import com.tubaoapi.api.base.Views;
import com.tubaoapi.helper.data.MaterialHelper;
import com.tubaoapi.model.Material;
import com.tubaoapi.model.MaterialGroup;
import com.tubaoapi.model.so.MaterialGroupSO;
import com.tubaoapi.model.so.MaterialSO;
import com.tubaoapi.modules.persistence.pagination.model.Page;
import com.tubaoapi.modules.persistence.pagination.model.PageRequest;
import com.tubaoapi.service.MaterialGroupService;
import com.tubaoapi.service.MaterialService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HP on 2017/7/24.
 */
@RestController
@RequestMapping(value = "/api/crm/materialGroup")
public class CrmMaterialGroupRest {
    @Autowired
    private MaterialGroupService materialService;

    @RequestMapping(value="", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.PublicView.class)
    public Result<MaterialGroup> list(
            String id,
            String groupStandardId,
            String parentId,
            Integer level,
            @RequestParam(defaultValue="1") String page,
            @RequestParam(defaultValue= API.DEFAULT_MAX_RESULTS) String maxResults,
            @RequestParam(defaultValue="false") String autoCount){


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
        PageRequest pageRequest = new PageRequest(pageInt,maxResultsInt,autoCountBoolean);

        MaterialGroupSO so = new  MaterialGroupSO();
        API.setIdOrIdsToBaseSO(so, id);
        so.setGroupStandardId(groupStandardId);
        so.setParentId(parentId);
        so.setLevel(level);
        so.setGroupStandardId("dR8lnQEPEADgAAWKwKgSxZeb4R8=");
        Page<MaterialGroup> pageInfo = materialService.findBySO(so,pageRequest);
        Result<MaterialGroup> result = new Result<>();
        result.setKind(Result.KIND_MATERIAL_GROUP_LIST);
        result.setItems(pageInfo.getContent());

        if(autoCountBoolean){
            result.setPageInfo(pageInfo);
        }

        return result;
    }
}
