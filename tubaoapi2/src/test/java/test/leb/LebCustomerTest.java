package test.leb;


import com.tubaoapi.api.base.API;
import com.tubaoapi.http.HttpClientFactory;
import com.tubaoapi.modules.http.HttpClient;
import com.tubaoapi.modules.http.HttpException;
import com.tubaoapi.modules.http.PageNotFoundException;
import com.tubaoapi.modules.utils.Encodes;
import org.junit.Test;
import test.APITest;

import java.util.HashMap;
import java.util.Map;

public class LebCustomerTest {
	
	@Test
	public void testCustomers() throws PageNotFoundException, HttpException {
		
		String uri = "/api/leb/customers";
		String client = API.CLIENT_LEB;
		String time = String.valueOf(System.currentTimeMillis());
		String token = API.buildToken(API.CLIENT_LEB, API.CLIENT_LEB_KEY, time);
		String number = "21713025";
//		String q = "杭州兔宝宝";
		
		
		Map<String, Object> params = new HashMap<>();
		params.put("client", client);
		params.put("time", time);
		params.put("token", token);
//		params.put("id", Encodes.urlEncode("1roAAAKFnw6/DAQO"));
		params.put("number", number);
		params.put("jxs", true);
		params.put("loadUsers", true);
//		params.put("q", Encodes.urlEncode(q));

		String url = APITest.buildUrl(uri, params);
		System.out.println(url);
		HttpClient hc = HttpClientFactory.create(false);
		String result = hc.get(url);
		System.out.println(result);
	}


	@Test
	public void testUsers() throws PageNotFoundException, HttpException {

		String uri = "/api/leb/users";
		String client = API.CLIENT_LEB;
		String time = String.valueOf(System.currentTimeMillis());
		String token = API.buildToken(API.CLIENT_LEB, API.CLIENT_LEB_KEY, time);
		String number = "214050521";
//		String q = "杭州兔宝宝";


		Map<String, Object> params = new HashMap<>();
		params.put("client", client);
		params.put("time", time);
		params.put("token", token);
//		params.put("id", Encodes.urlEncode("1roAAAKFnw6/DAQO"));
		params.put("number", number);
//		params.put("q", Encodes.urlEncode(q));

		String url = APITest.buildUrl(uri, params);
		System.out.println(url);
		HttpClient hc = HttpClientFactory.create(false);
		String result = hc.get(url);
		System.out.println(result);
	}
}
