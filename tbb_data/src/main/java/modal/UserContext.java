package modal;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by HP on 2017/6/27.
 */

public class UserContext {
    private String userid;

    private String eid;

    private String uid;

    private String tid;

    private String username;

    private String openid;

    public String getUserid() {
        return userid;
    }

    public String getEid() {
        return eid;
    }

    public String getUid() {
        return uid;
    }

    public String getTid() {
        return tid;
    }

    public String getUsername() {
        return username;
    }

    public String getOpenid() {
        return openid;
    }

    public UserContext(JSONObject data){
        this.userid = data.getString("userid");
        this.eid = data.getString("eid");
        this.uid = data.getString("uid");
        this.tid = data.getString("tid");
        this.openid = data.getString("openid");
        this.username = data.getString("username");
    }

    public UserContext(String userid, String eid, String uid, String tid, String username, String openid) {
        this.userid = userid;
        this.eid = eid;
        this.uid = uid;
        this.tid = tid;
        this.username = username;
        this.openid = openid;
    }
}
