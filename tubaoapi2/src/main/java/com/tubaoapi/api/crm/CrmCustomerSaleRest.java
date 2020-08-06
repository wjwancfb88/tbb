package com.tubaoapi.api.crm;

import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.API;
import com.tubaoapi.api.base.ParamsCheck;
import com.tubaoapi.api.base.Result;
import com.tubaoapi.api.base.Views;
import com.tubaoapi.model.Customer;
import com.tubaoapi.model.SaleData;
import com.tubaoapi.model.so.CustomerSO;
import com.tubaoapi.model.so.SaleDataSO;
import com.tubaoapi.modules.persistence.pagination.model.Page;
import com.tubaoapi.modules.persistence.pagination.model.PageRequest;
import com.tubaoapi.modules.utils.DateUtil;
import com.tubaoapi.service.CustomerService;
import com.tubaoapi.service.SaleDataService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 经销商每月销量
 * Created by kaiwang on 2017/11/24.
 */
@RestController
@RequestMapping(value = "/api/crm/saleData")
public class CrmCustomerSaleRest {
    @Autowired
    private SaleDataService saleDataService;

    @RequestMapping(value="", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.PublicView.class)
    public Result<SaleData> list(
            String customerId,
            Integer year,
            Integer month,
            String userId){
        customerId = StringUtils.trimToEmpty(customerId);


        SaleDataSO so = new SaleDataSO();
        so.setUserId(userId);
        so.setStartTime(DateUtil.getFirstDayOfMonth(year, month));
        so.setEndTime(DateUtil.getFirstDayOfMonth(year, month+1));
        so.setOrgId("1roAAAAADx/M567U");
        API.setIdOrIdsToBaseSO(so, customerId);
        List<SaleData> saleDatas = saleDataService.findSaleA(so);

        Result<SaleData> result = new Result<>();
        result.setKind(Result.KIND_SaleData_LIST);
        result.setItems(saleDatas);
        return result;

    }
}
