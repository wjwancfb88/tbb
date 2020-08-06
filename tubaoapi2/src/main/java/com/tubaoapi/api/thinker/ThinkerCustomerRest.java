package com.tubaoapi.api.thinker;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.API;
import com.tubaoapi.api.base.ParamsCheck;
import com.tubaoapi.api.base.Result;
import com.tubaoapi.api.base.Views;
import com.tubaoapi.model.Customer;
import com.tubaoapi.model.so.CustomerSO;
import com.tubaoapi.modules.persistence.pagination.model.Page;
import com.tubaoapi.modules.persistence.pagination.model.PageRequest;
import com.tubaoapi.service.CustomerService;


@RestController
@RequestMapping(value = "/api/thinker/customers")
public class ThinkerCustomerRest{

	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(Views.PublicView.class)
	public Result<Customer> list(
			String id,
			String number,
			String q,
			@RequestParam(defaultValue="1") String page,
			@RequestParam(defaultValue= API.DEFAULT_MAX_RESULTS) String maxResults,
			@RequestParam(defaultValue="false") String autoCount){
		
		id = StringUtils.trimToEmpty(id);
		number = StringUtils.trimToEmpty(number);
		page = StringUtils.trimToEmpty(page);
		maxResults = StringUtils.trimToEmpty(maxResults);
		autoCount = StringUtils.trimToEmpty(autoCount);
		
		//ParamsCheck.checkExpectedOne("id,number,q", id,number,q);
		ParamsCheck.checkUnsignedInteger("page", page);
		ParamsCheck.checkUnsignedInteger("maxResults", maxResults);
		ParamsCheck.checkBoolean("autoCount", autoCount);
		int maxResultsInt = Integer.parseInt(maxResults);
		ParamsCheck.checkRange("maxResults", maxResultsInt, 1, API.MAX_RESULTS);
		
		int pageInt = Integer.parseInt(page);
		boolean autoCountBoolean = Boolean.valueOf(autoCount);
		
		CustomerSO so = new CustomerSO();
		API.setIdOrIdsToBaseSO(so, id);
		so.setNumber(number);
		so.setQ(q);
		
		PageRequest pageRequest = new PageRequest(pageInt,maxResultsInt,autoCountBoolean);
		Page<Customer> pageInfo = customerService.findBySO(so,pageRequest);
		Result<Customer> result = new Result<>();
		result.setKind(Result.KIND_CUSTOMER_LIST);
		result.setItems(pageInfo.getContent());
		if(autoCountBoolean){
			result.setPageInfo(pageInfo);
		}
		
		return result;
	}
	
	
}
