package com.tubaoapi.api.crm;

import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.API;
import com.tubaoapi.api.base.Result;
import com.tubaoapi.api.base.Views;
import com.tubaoapi.model.Province;
import com.tubaoapi.model.so.ProvinceSO;
import com.tubaoapi.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by WK on 2017/8/3.
 */
@RestController
@RequestMapping(value = "/api/crm/province")
public class CrmProvinceRest {
    @Autowired
    private ProvinceService provinceService;
    @RequestMapping(value="", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.PublicView.class)
    public Result<Province> list(String id,String number,  String name){
        ProvinceSO so=new ProvinceSO();
        so.setName(name);
        so.setNumber(number);
        API.setIdOrIdsToBaseSO(so, id);
        List<Province> list = provinceService.findBySO(so);
        Result<Province> result = new Result<>();
        result.setKind(Result.KIND_PROVINCE_LIST);
        result.setItems(list);
        return result;


    }


}
