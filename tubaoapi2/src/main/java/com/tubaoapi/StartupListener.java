package com.tubaoapi;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoaderListener;

import com.tubaoapi.modules.utils.FileUtils2;

public class StartupListener extends ContextLoaderListener  implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
	}

	public void contextInitialized(ServletContextEvent event){
		ServletContext servletContext = event.getServletContext();
		String ctx = servletContext.getContextPath();
		Config config = Config.createInstance(servletContext);
		
		if(StringUtils.isNotBlank(config.getTmpDir())){
			FileUtils2.mkDirs(config.getTmpDir());
		}
		
		servletContext.setAttribute("ctx", ctx);
		servletContext.setAttribute("cfg", config);
		//servletContext.setAttribute("seoUrl", SeoUrl.getInstance());
		
		
		//TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
		//servletContext.setAttribute("res", new StaticResources(config));
		//WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		//MetaService metaService  = (MetaService)wac.getBean("metaService");
		//metaService.clearAndMerge();
		
	}

}
