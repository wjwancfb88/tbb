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

public class ThinkerUserTest {
	
	@Test
	public void testUsers() throws PageNotFoundException, HttpException {
		
		String uri = "/api/thinker/users";
		String client = API.CLIENT_THINKER;
		String time = String.valueOf(System.currentTimeMillis());
		String token = API.buildToken(API.CLIENT_THINKER, API.CLIENT_THINKER_KEY, time);
		String number = "238581";
		String q = "迪";
		//String id = "I/OjjhJWRI6t/ump07OLNRO33n8=,r3qnqNeJTbCFzXeCxOX+6RO33n8=";
		
		
		Map<String, Object> params = new HashMap<>();
		params.put("client", client);
		params.put("time", time);
		params.put("token", token);
		//params.put("number", number);
		//params.put("id", Encodes.urlEncode(id));
		//params.put("q", Encodes.urlEncode(q));
		
		
		String url = APITest.buildUrl(uri, params);
		System.out.println(url);
		HttpClient hc = HttpClientFactory.create(false);
		String result = hc.get(url);
		System.out.println(result);
	}
	
}
