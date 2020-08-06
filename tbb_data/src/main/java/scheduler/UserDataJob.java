package scheduler;


/**
 * Created by HP on 2017/7/5.
 */
public class UserDataJob {

    public void run() {

        yzjUserInfo info=new yzjUserInfo();
        try {
            info.getUserInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
