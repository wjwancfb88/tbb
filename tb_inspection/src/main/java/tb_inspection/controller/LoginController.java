package tb_inspection.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tb_inspection.constant.ResultConstant;
import tb_inspection.entity.CommonResult;
import tb_inspection.entity.User;

@RestController
@CrossOrigin(origins = "*",allowCredentials = "true",allowedHeaders = "*")
@RequestMapping("/login")
public class LoginController {



	@RequestMapping("/doLogin")
	public CommonResult doLogin(@RequestBody User user, HttpSession session) {
//		session.setAttribute("user", user);
//		return new CommonResult(ResultConstant.SUCCCSS_CODE, ResultConstant.SUCCESS_MSG);

		if (("admin".equals(user.getUserName()) && "123456".equals(user.getPassword()))) {

			session.setAttribute("user", user);
			return new CommonResult(ResultConstant.SUCCCSS_CODE, ResultConstant.SUCCESS_MSG);
		}
		return new CommonResult(ResultConstant.LOGIN_FAIL_CODE, ResultConstant.FAIL_MSG);

	}
	
	@RequestMapping("/doLogOut")
	public CommonResult doLogOut(HttpSession session) {

		session.removeAttribute("user");
		return new CommonResult(ResultConstant.SUCCCSS_CODE, ResultConstant.SUCCESS_MSG);

	}

}
