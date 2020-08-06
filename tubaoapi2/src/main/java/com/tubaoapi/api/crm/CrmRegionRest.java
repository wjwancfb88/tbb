package com.tubaoapi.api.crm;

import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.API;
import com.tubaoapi.api.base.Result;
import com.tubaoapi.api.base.Views;
import com.tubaoapi.model.Region;
import com.tubaoapi.model.so.RegionSO;
import com.tubaoapi.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by HP on 2017/8/3.
 */
@RestController
@RequestMapping(value = "/api/crm/region")
public class CrmRegionRest {

    @Autowired
    private RegionService regionService;
    @RequestMapping(value="", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.PublicView.class)
    public Result<Region> list(String id,String number,String name,String cityId){
        RegionSO so=new RegionSO();
        so.setName(name);
        so.setNumber(number);
        so.setCityId(cityId);
        API.setIdOrIdsToBaseSO(so, id);
        List<Region> list = regionService.findBySO(so);
        Result<Region> result = new Result<>();
        result.setKind(Result.KIND_REGION_LIST);
        result.setItems(list);
        return result;


    }

}
