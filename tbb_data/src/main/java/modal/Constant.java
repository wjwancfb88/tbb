package modal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by HP on 2017/6/27.
 */
@Component
public class Constant {
    @Value("${appid}")
    private String appid;

    @Value("${appsecret}")
    private String appsecret;

    @Value("${eid}")
    private String eid;

    @Value("${pubid}")
    private String pubid;

    @Value("${pubsecret}")
    private String pubsecret;

    @Value("${local}")
    private String local;

    private final Map<String,String> cache = new ConcurrentHashMap<String,String>();



    public Map<String, String> getCache() {
        return cache;
    }

    public String getLocal() {
        return local;
    }

    public String getEid() {
        return eid;
    }

    public String getPubid() {
        return pubid;
    }

    public String getPubsecret() {
        return pubsecret;
    }

    public String getAppid() {
        return appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

}
