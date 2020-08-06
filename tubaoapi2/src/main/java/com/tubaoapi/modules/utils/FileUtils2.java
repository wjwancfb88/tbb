package com.tubaoapi.modules.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils2 {
	
	public static final String CHARSET_NAME = "utf-8";
	 /**
     *  get extend name of filename
     * @param fileName String
     * @return String
     */
    public static String getExtName(String fileName) {
        if (fileName == null || fileName.equals("")) {
            return "";
        }

        int pos = fileName.lastIndexOf(".");
        if (pos < 0 || pos == fileName.length() - 1) {
            return "";
        }
        else {
            String ext =  fileName.substring(pos);
            int a = ext.indexOf("?");
            if(a!=-1){
            	ext = ext.substring(0,a);
            }
            return ext;
        }
    }

    /**
     * is file exist
     * @param path String
     * @return boolean
     */
    public static boolean isExist(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     * make dirs of path
     * @param path String
     */
    public static void mkDirs(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }



    //----------------------- delete file -----------------------

    public static void delete(File file) {
        if (file.exists()) {
            file.delete();
        }
        file = null;
    }
    

    public static void delete(String path) {
        delete(new File(path));
    }
    
    
    //withLine 是否分行
    public static String readToString(File file,boolean withLine){
    	InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try{
	
			isr = new InputStreamReader(new FileInputStream(file), "utf-8");
			br = new BufferedReader(isr);
			
			String line = null;
            while((line = br.readLine()) != null){
            	sb.append(StringUtils.trimToEmpty(line));
            	if(withLine){
            		sb.append("\r\n");
            	}
            }
            
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			try {
				br.close();
				isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
    }
    
    public static String readToString(File file){
    	return readToString(file, false);
    }
    
    private static final String CHARSET = "utf-8";
    

    
    
    public static List<String> readToStringList(File file) throws FileNotFoundException{
    	return readToStringList(file,CHARSET);
    }
    
    public static List<String> readToStringList(File file,String charsetName) throws FileNotFoundException{
    	InputStream input = new FileInputStream(file);
    	try {
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return readToStringList(input,charsetName);
    }
    
    public static List<String> readToStringList(MultipartFile mfile) throws IOException{
    	return readToStringList(mfile,CHARSET);
    }
    
    public static List<String> readToStringList(MultipartFile mfile,String charsetName) throws IOException{
    	return readToStringList(mfile.getInputStream(),charsetName);
    }
    
    public static List<String> readToStringList(InputStream input,String charsetName){
    	List<String> l = new ArrayList<String>();
    	
    	InputStreamReader reader = null;
		BufferedReader buffered = null;
		try{
	
			reader = new InputStreamReader(input, charsetName);
			buffered = new BufferedReader(reader);
			
			String line = null;
            while((line = buffered.readLine()) != null){
            	l.add(line);
            }
            
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(buffered!=null){
					buffered.close();
				}
				if(reader!=null){
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return l;
    }
    
    
	public static void write(String s,String filePath){
		
		try {
			File file = new File(filePath);
			OutputStream os = new FileOutputStream(file, false);
			OutputStreamWriter osr =  new OutputStreamWriter(os, CHARSET_NAME);
			BufferedWriter bw = new BufferedWriter(osr);
			bw.write(s);
			bw.flush();
			osr.close();
			os.close();
			bw.close();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} 
	}
}
