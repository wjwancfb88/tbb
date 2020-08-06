package com.tubaoapi.api.crm;

import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.API;
import com.tubaoapi.api.base.Result;
import com.tubaoapi.api.base.Views;
import com.tubaoapi.model.City;
import com.tubaoapi.model.Province;
import com.tubaoapi.model.so.CitySO;
import com.tubaoapi.model.so.ProvinceSO;
import com.tubaoapi.service.CityService;
import com.tubaoapi.service.ProvinceService;
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
@RequestMapping(value = "/api/crm/city")
public class CrmCityRest {
    @Autowired
    private CityService cityService;
    @RequestMapping(value="", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.PublicView.class)
    public Result<City> list(String id,String number,String name,String provinceId){
        CitySO so=new CitySO();
        so.setName(name);
        so.setNumber(number);
        so.setProvinceId(provinceId);
        API.setIdOrIdsToBaseSO(so, id);
        List<City> list = cityService.findBySO(so);
        Result<City> result = new Result<>();
        result.setKind(Result.KIND_CITY_LIST);
        result.setItems(list);
        return result;


    }
}
