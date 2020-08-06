package com.tubaoapi.api.crm;

import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.API;
import com.tubaoapi.api.base.ParamsCheck;
import com.tubaoapi.api.base.Result;
import com.tubaoapi.api.base.Views;
import com.tubaoapi.helper.data.UserHelper;
import com.tubaoapi.model.User;
import com.tubaoapi.model.so.UserSO;
import com.tubaoapi.modules.persistence.pagination.model.Page;
import com.tubaoapi.modules.persistence.pagination.model.PageRequest;
import com.tubaoapi.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/crm/users")
public class CrmUserRest {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserHelper userHelper;
	
	
	@RequestMapping(value="", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(Views.PublicView.class)
	public Result<User> list(
			String id,
			String number,
			String q,
			String date,
			@RequestParam(defaultValue="1") String page,
			@RequestParam(defaultValue= API.DEFAULT_MAX_RESULTS) String maxResults,
			@RequestParam(defaultValue="false") String autoCount,
			@RequestParam(defaultValue="false") String loadCustomer,
			@RequestParam(defaultValue="false") String loadSupplier){
		
		id = StringUtils.trimToEmpty(id);
		number = StringUtils.trimToEmpty(number);
		page = StringUtils.trimToEmpty(page);
		maxResults = StringUtils.trimToEmpty(maxResults);
		autoCount = StringUtils.trimToEmpty(autoCount);
		
		//ParamsCheck.checkExpectedOne("id,number,q", id,number,q);
		ParamsCheck.checkUnsignedInteger("page", page);
		ParamsCheck.checkUnsignedInteger("maxResults", maxResults);
		ParamsCheck.checkBoolean("autoCount", autoCount);
		ParamsCheck.checkBoolean("loadCustomer", loadCustomer);
		ParamsCheck.checkBoolean("loadSupplier", loadSupplier);
		
		int maxResultsInt = Integer.parseInt(maxResults);
		ParamsCheck.checkRange("maxResults", maxResultsInt, 1, API.MAX_RESULTS);
		
		
		int pageInt = Integer.parseInt(page);
		boolean autoCountBoolean = Boolean.valueOf(autoCount);
		boolean loadCustomerBoolean = Boolean.valueOf(loadCustomer);
		boolean loadSupplierBoolean = Boolean.valueOf(loadSupplier);
		PageRequest pageRequest = new PageRequest(pageInt,maxResultsInt,autoCountBoolean);
		
		UserSO so = new UserSO();
		
		API.setIdOrIdsToBaseSO(so, id);
		so.setNumber(number);
		so.setQ(q);
		so.setLastUpdateTime(date);
		Page<User> pageInfo = userService.findBySO(so,pageRequest);
		Result<User> result = new Result<>();
		result.setKind(Result.KIND_USER_LIST);
		result.setItems(pageInfo.getContent());
		if(autoCountBoolean){
			result.setPageInfo(pageInfo);
		}
		
		//加载customer
		if(loadCustomerBoolean){
			userHelper.setCustomers(pageInfo.getContent());
		}
		
		//加载supplier
		if(loadSupplierBoolean){
			userHelper.setSuppliers(pageInfo.getContent());
		}
		
		return result;
	}
	
	
}
