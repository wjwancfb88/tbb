package sso.test;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sso.Yxt.YxtToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kaiwang on 2018/3/20.
 */
@Controller
public class mylogintest {

    @ResponseBody
    @RequestMapping(value="/login.do", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public String sso(HttpServletRequest request,HttpServletResponse response,String username,String password) throws Exception {
        request.setAttribute("data", "12345");
        if(username.equals("admin")&&password.equals("123456")){
            return "1";
        }else{
            return "false";
        }


    }
}
