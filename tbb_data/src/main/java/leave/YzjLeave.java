package leave;

import modal.UserContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sso.BI.BIToken;
import yzjCommon.YzjDataApi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kaiwang on 2017/11/13.
 */
@Controller
public class YzjLeave {
    private String jumpIndex="http://172.16.0.130:8080/DAP/open_loginProxy.do";

    @RequestMapping(value="/leave/sso.do")
    public void sso(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String ticket = request.getParameter("ticket");
        response.sendRedirect("http://www.yunzhijia.com/cloudflow-mobile/apply/create/5154d766b17948d68d99119bc4f35097?ticket="+ticket);
    }
    }

