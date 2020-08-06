package com.dhwooden.ep.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * ҳ�湤����
 */
public class HtmlUtil {

    /**
     *
     * <br>
     * <b>���ܣ�</b>���json��ʽ<br>
     * @param response
     * @param jsonStr
     * @throws Exception
     */
    public static void writerJson(HttpServletResponse response, String jsonStr) {
        writer(response, jsonStr);
    }

    public static void writerJson(HttpServletResponse response, Object object) {
        try {
            response.setContentType("application/json;charset=UTF-8");
            writer(response, JSON.toJSONString(object));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void writerJsontoLowerCase(HttpServletResponse response, Object object) {
        try {
            response.setContentType("application/json;charset=UTF-8");
            writer(response, JSON.toJSONString(object).toLowerCase());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    /**
     *
     * <br>
     * <b>���ܣ�</b>���HTML����<br>
     * @param response
     * @param htmlStr
     * @throws Exception
     */
    public static void writerHtml(HttpServletResponse response, String htmlStr) {
        writer(response, htmlStr);
    }

    private static void writer(HttpServletResponse response, String str) {
        try {
            //����ҳ�治����
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            PrintWriter out = null;
            out = response.getWriter();
            out.print(str);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * дjson���� SPMS3.0 ADDED BY LWS 20160415
     *
     * @param response
     * @param str
     */
    public static void writeGson(HttpServletResponse response, Map<String, Object> contentMap) {
        PrintWriter out = null;
        try {
            //����ҳ�治����
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setContentType("charset=UTF-8");
            out = response.getWriter();
            String content = JSON.toJSONString(contentMap);
            out.write(content);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
