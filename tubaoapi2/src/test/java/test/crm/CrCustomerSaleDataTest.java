package test.crm;


import com.tubaoapi.api.base.API;
import com.tubaoapi.http.HttpClientFactory;
import com.tubaoapi.modules.http.HttpClient;
import com.tubaoapi.modules.http.HttpException;
import com.tubaoapi.modules.http.PageNotFoundException;
import org.junit.Test;
import test.APITest;

import java.util.HashMap;
import java.util.Map;

public class CrCustomerSaleDataTest {
	
	@Test
	public void testCustomers() throws PageNotFoundException, HttpException {
		
		String uri = "/api/crm/saleData";
		String client = API.CLIENT_CRM;
		String time = String.valueOf(System.currentTimeMillis());
		String token = API.buildToken(API.CLIENT_CRM, API.CLIENT_CRM_KEY, time);

		
		
		Map<String, Object> params = new HashMap<>();
		params.put("client", client);
		params.put("time", time);
		params.put("token", token);

		params.put("customerId", "1roAAAEZ4Fy/DAQO");
		params.put("year", "2019");
		params.put("month", "3");
//		params.put("userId", token);


		String url = APITest.buildUrl(uri, params);
		System.out.println(url);
		HttpClient hc = HttpClientFactory.create(false);
		String result = hc.get(url);
		System.out.println(result);
	}
	
}
