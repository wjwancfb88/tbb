package test.thinker;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import test.APITest;

import com.tubaoapi.api.base.API;
import com.tubaoapi.http.HttpClientFactory;
import com.tubaoapi.modules.http.HttpClient;
import com.tubaoapi.modules.http.HttpException;
import com.tubaoapi.modules.http.PageNotFoundException;
import com.tubaoapi.modules.utils.Encodes;

public class ThinkerCustomerTest {
	
	@Test
	public void testCustomers() throws PageNotFoundException, HttpException {
		
		String uri = "/api/thinker/customers";
		String client = API.CLIENT_THINKER;
		String time = String.valueOf(System.currentTimeMillis());
		String token = API.buildToken(API.CLIENT_THINKER, API.CLIENT_THINKER_KEY, time);
//		String number = "009991";
//		String q = "杭州兔宝宝";
		
		
		Map<String, Object> params = new HashMap<>();
		params.put("client", client);
		params.put("time", time);
		params.put("token", token);
//		params.put("number", number);
//		params.put("q", Encodes.urlEncode(q));

		String url = APITest.buildUrl(uri, params);
		System.out.println(url);
		HttpClient hc = HttpClientFactory.create(false);
		String result = hc.get(url);
		System.out.println(result);
	}
	
}
