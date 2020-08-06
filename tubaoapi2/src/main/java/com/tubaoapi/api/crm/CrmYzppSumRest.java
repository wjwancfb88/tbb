package com.tubaoapi.api.crm;

import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.Result;
import com.tubaoapi.api.base.Views;
import com.tubaoapi.model.dto.YzppSum;
import com.tubaoapi.service.CrmYzppSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 经销商物料汇总
 * */
@RestController
@RequestMapping(value = "/api/crm/yzppSum")
public class CrmYzppSumRest {
    @Autowired
    private CrmYzppSumService crmOtherIssueBillEntryService;

    @RequestMapping(value="", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.PublicView.class)
    public Result<YzppSum> findOtherIssueBillData(){
        List<String> list = new ArrayList<String>();
        list.add("1roAAAIUFSpECefw");
        list.add("1roAAAJ2XcNECefw");
        list.add("1roAAALJHXpECefw");
//        List<YzppSum> otherIssueBillData = crmOtherIssueBillEntryService.findOtherIssueBillData(list);
        List<YzppSum> otherIssueBillData = crmOtherIssueBillEntryService.findOtherIssueBillData();
        Result<YzppSum> result = new Result<>();
//        for (YzppSum y:otherIssueBillData) {
//            System.out.println(y.getfCustomerID() + ": " + y.getfMaterialID() + ": " + y.getSum());
//        }
        result.setKind(Result.KIND_YZPPSUM_LIST);
        result.setItems(otherIssueBillData);
        return result;
    }

    @RequestMapping(value="/order", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.PublicView.class)
    public Result<YzppSum> findOrderData(String oemsupplierID,String createTime){
//        oemsupplierID = "1roAAAI9nkM3xn38";
//        createTime = "2019-01-01 00:00:00";
        List<YzppSum> orderData = crmOtherIssueBillEntryService.findOrderData(oemsupplierID,createTime);
        Result<YzppSum> result = new Result<>();
        result.setKind(Result.KIND_YZPPSUM_LIST);
        result.setItems(orderData);
        return result;
    }
}
