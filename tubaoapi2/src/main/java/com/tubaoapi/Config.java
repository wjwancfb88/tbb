package com.tubaoapi;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class Config {

	private static Logger logger = LoggerFactory.getLogger(Config.class);
	private static final String PROFILE_PROD = "prod";//正式环境
	private static final String PROFILE_DEV = "dev";//开发环境
	private static Config instance;

	private String projectName = "TubaoAPI";
	private String projectUrl = "http://www.tubaoapi.com";
	
	private String profile;
	
	private String baseUrl; 
	private String baseDir;
	private String staticUrl;
	private String tmpDir;
	
	private String ctx;
	
	private String imagemagickConvert;
	private String imagemagickComposite;

	private Config() {
	}

	public static Config createInstance(ServletContext servletContext) {
		if (instance == null) {
			instance = new Config();
			instance.init(servletContext);
		}
		return instance;
	}
	
	public static Config getInstance() {
		return instance;
	}
	
	private void init(ServletContext servletContext){
		profile = StringUtils.trimToEmpty(servletContext.getInitParameter("spring.profiles.default"));
		String filePath = servletContext.getRealPath("/WEB-INF/classes/application."+profile+".properties");
		ctx = servletContext.getContextPath();
		baseDir = servletContext.getRealPath("/");
		
		InputStream is = null;
		try {
			is = new BufferedInputStream(new FileInputStream(filePath));
			Properties p = new Properties();
			p.load(is);
			baseUrl = p.getProperty("app.base_url");
			staticUrl = p.getProperty("app.static_url");
			tmpDir = p.getProperty("app.tmp_dir");
			imagemagickConvert = p.getProperty("imagemagick.convert");
			imagemagickComposite = p.getProperty("imagemagick.composite");
			
			logger.info("baseUrl:{}",baseUrl);
			logger.info("baseDir:{}",baseDir);
			logger.info("staticUrl:{}",staticUrl);
			logger.info("imagemagickConvert:{}",imagemagickConvert);
			logger.info("imagemagickComposite:{}",imagemagickComposite);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("加载"+filePath+"出错");
		} finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	

	public String getCtx() {
		return ctx;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public String getStaticUrl() {
		return staticUrl;
	}
	



	public String getProjectName() {
		return projectName;
	}
	
	
	public String getProjectUrl() {
		return projectUrl;
	}

	public String getTmpDir() {
		return tmpDir;
	}

	public String getImagemagickConvert() {
		return imagemagickConvert;
	}

	public String getImagemagickComposite() {
		return imagemagickComposite;
	}
	

	public String getBaseDir() {
		return baseDir;
	}
	

	
	public boolean isProd(){
		return PROFILE_PROD.equals(profile);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
