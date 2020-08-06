package tb_inspection.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tb_inspection.entity.User;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/dh_inspection/list").setViewName("dh_inspection/list");
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/welcome").setViewName("welcome");
		registry.addViewController("/login").setViewName("login");
	}

	/**
	 * 拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new HandlerInterceptor() {
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {

				// 预请求
                if ("OPTIONS".equals(request.getMethod())) {
                    return true;
                }

				HttpSession session = request.getSession();

				User user = (User) session.getAttribute("user");

				if (user == null) {
					response.sendRedirect(request.getServletContext().getContextPath() + "/login");
                    return false;
				}
				return true;

			}
		}).addPathPatterns("/**").excludePathPatterns("/login", "/register", "/login/doLogin", "/user/register",
				"/mystatic/**", "/druid/**");
	}
}
