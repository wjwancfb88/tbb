package com.tubaoapi.api.leBang;

import com.fasterxml.jackson.annotation.JsonView;
import com.tubaoapi.api.base.API;
import com.tubaoapi.api.base.ParamsCheck;
import com.tubaoapi.api.base.Result;
import com.tubaoapi.api.base.Views;
import com.tubaoapi.model.Person;
import com.tubaoapi.model.User;
import com.tubaoapi.model.so.PersonSO;
import com.tubaoapi.model.so.UserSO;
import com.tubaoapi.modules.persistence.pagination.model.Page;
import com.tubaoapi.modules.persistence.pagination.model.PageRequest;
import com.tubaoapi.service.PersonService;
import com.tubaoapi.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api/leb/persons")
public class LeBangPersonRest {

	@Autowired
	private PersonService personService;


	
	@RequestMapping(value="", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(Views.LebView.class)
	public Result<Person> list(
			String id,
			String number,
			String q,
			String createTime,
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
		
		PersonSO so = new PersonSO();
		API.setIdOrIdsToBaseSO(so, id);
		so.setQ(q);
		so.setCreateTime(createTime);
		PageRequest pageRequest = new PageRequest(pageInt,maxResultsInt,autoCountBoolean);
		Page<Person> pageInfo = personService.findBySO(so,pageRequest);
		Result<Person> result = new Result<>();

		result.setKind(Result.KIND_PERSON_LIST);
		result.setItems(pageInfo.getContent());
		if(autoCountBoolean){
			result.setPageInfo(pageInfo);
		}



		return result;


	}
	
	
}
