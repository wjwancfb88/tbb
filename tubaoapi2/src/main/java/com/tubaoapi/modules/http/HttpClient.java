package com.tubaoapi.modules.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * HTTPS 只有HttpClient 4.3.6才能访问，否则会出错: javax.net.ssl.SSLPeerUnverifiedException: Host name 'www.googleapis.com' does not match the certificate subject provided by the peer (CN=*.googleapis.com, O=Google Inc, L=Mountain View, ST=California, C=US)
 *
 *
 */
public abstract class HttpClient {
	
	private static Logger logger = LoggerFactory.getLogger(HttpClient.class);
	
	public static final String DEFAULT_CHARSET = "utf-8";
	
	private CloseableHttpClient httpClient = HttpClients.createDefault();
	
	private String charset;
	private Map<String,String> headers = new HashMap<String, String>();
	
	public HttpClient(String charset){
		this.charset = charset;
	}
	
	public String get(String url) throws PageNotFoundException, HttpException  {
		return fetchString(createGet(url));
	}
	
	public File getFile(String url,String savePath) throws  PageNotFoundException, HttpException {
		return fetchFile(createGet(url), savePath);
	}
	
	private String fetchString(HttpUriRequest request) throws PageNotFoundException, HttpException  {
		HttpEntity entity = getEntity(request);
		return entryToString(entity);
	}
	
	private File fetchFile(HttpUriRequest request,String savePath) throws PageNotFoundException, HttpException  {
		HttpEntity entity = getEntity(request);
		return entryToFile(entity,savePath);
	}
	
	
	private String entryToString(HttpEntity entity) throws HttpException  {
		try {
			return EntityUtils.toString(entity,charset);
		} catch (Exception e) {
			throw new HttpException(e);
		} 
	}
	
	private File entryToFile(HttpEntity entity,String savePath) throws HttpException  {
		InputStream input = null;
		OutputStream output = null;
		try{
			File file = new File(savePath);
			input = entity.getContent();
			output = new FileOutputStream(file);
			IOUtils.copy(input, output);
			output.flush();
			return file;
		}catch(Exception e){
			throw new HttpException(e);
			
		}finally{
			try{
				if(input!=null){
					input.close();
				}
				if(output!=null){
					output.close();
				}
			}catch(IOException e){
				throw new HttpException(e);
			}
		}
	}
	
	private HttpEntity getEntity(HttpUriRequest request) throws PageNotFoundException, HttpException{
		for(String name:headers.keySet()){
			request.addHeader(name, headers.get(name));
		}
		logger.debug(request.getURI().toString());
		//HttpHost target = new HttpHost("www.googleapis.com", 443, "https"); 
		HttpResponse response;
		try {
			response = httpClient.execute(request);
		} catch (Exception e) {
			throw new HttpException(e);
		} 
		
		int status = response.getStatusLine().getStatusCode();
		if (status==HttpStatus.SC_OK) {
			return response.getEntity();
		}else if (status==HttpStatus.SC_NOT_FOUND) {
			throw new PageNotFoundException("Page not found");
		}else{
			throw new HttpException("http status code:"+status);
		}
		
	}
	



	
	protected abstract HttpHost getProxy() ;
	public void addHeader(String name,String value){
		headers.put(name, value);
	}
	
	private HttpGet createGet(String url){
		HttpGet get = new HttpGet(url);
		if(getProxy()!=null){
			RequestConfig config = RequestConfig.custom().
					setSocketTimeout(10000).
					setConnectTimeout(10000).
					setProxy(getProxy()).build();
			get.setConfig(config);
		}
		return get;
	}
	
	

	
	
	
	
	
}
