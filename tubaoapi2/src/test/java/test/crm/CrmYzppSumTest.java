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

public class CrmYzppSumTest {
    @Test
    public void testYzppSum() throws PageNotFoundException, HttpException {
        String uri = "/api/crm/yzppSum";
        String client = API.CLIENT_CRM;
        String time = String.valueOf(System.currentTimeMillis());
        String token = API.buildToken(API.CLIENT_CRM, API.CLIENT_CRM_KEY, time);
//		String number = "009991";
//		String q = "杭州兔宝宝";


        Map<String, Object> params = new HashMap<>();
        params.put("client", client);
        params.put("time", time);
        params.put("token", token);

        String url = APITest.buildUrl(uri, params);
        System.out.println(url);
        HttpClient hc = HttpClientFactory.create(false);
        String result = hc.get(url);
        System.out.println(result);
    }

    @Test
    public void testOrderData() throws PageNotFoundException, HttpException {
        String uri = "/api/crm/yzppSum/order";
        String client = API.CLIENT_CRM;
        String time = String.valueOf(System.currentTimeMillis());
        String token = API.buildToken(API.CLIENT_CRM, API.CLIENT_CRM_KEY, time);

        Map<String, Object> params = new HashMap<>();
        params.put("client", client);
        params.put("time", time);
        params.put("token", token);
        params.put("oemsupplierID","1roAAAI9nkM3xn38");
        params.put("createTime", "2019-01-01");

        String url = APITest.buildUrl(uri, params);
        System.out.println(url);
        HttpClient hc = HttpClientFactory.create(false);
        String result = hc.get(url);
        System.out.println(result);
    }
}
