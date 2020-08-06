package com.dhwooden.ep.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WBQEncode_Decoder {
    public static void my_decode_v2(Object object) throws Exception {
        if (object instanceof JSONObject) {// 是对象
            JSONObject jsonObject = (JSONObject) object;
            handleJsonObject(jsonObject);
        } else if (object instanceof JSONArray) {// 是对象
            JSONArray jsonArray = (JSONArray) object;
            int length = jsonArray.size();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                handleJsonObject(jsonObject);
                jsonArray.set(i, jsonObject);
            }

        }
    }

    public static void my_encode_v2(List<Map<String, Object>> list)
            throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (Iterator<Map<String, Object>> iterator = list.iterator(); iterator
                .hasNext();) {
            Map<String, Object> map = iterator.next();
            Set<String> keys = map.keySet();
            for (Iterator<String> iterator2 = keys.iterator(); iterator2
                    .hasNext();) {
                String key = iterator2.next();
                Object object = map.get(key);
                if (object == null || object instanceof String
                        || object instanceof Number) {
                    if (object == null) {
                        object = "";
                    }
                    String value = object.toString();
                    if (value.equals("null") || value.equals("undefined")) {
                        value = "";
                    }
                    value = URLEncoder.encode(value, "utf-8");
                    value = value.replaceAll("\\+", "百分之20(直接写的话博客上看不到)");// 其实+号是URLEncoder.encode后把空格转成+了，在页面上解码的时候是把空格当作了，所以要把+替换成

                    map.put(key, value);
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {

        String value = "http://www.w3school.com.cn/My first /";
        value = value.replaceAll("\\+", " ");
        System.out.println(value);
    }

    @SuppressWarnings("unchecked")
    private static void handleJsonObject(JSONObject jsonObject)
            throws Exception {
        Set<String> keys = jsonObject.keySet();
        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
            String key = iterator.next();
            String objectStr = jsonObject.get(key).toString();
            if ((objectStr.startsWith("{") && objectStr.endsWith("}"))) {
                my_decode_v2(JSONObject.fromObject(objectStr));
            } else if (objectStr.startsWith("[") && objectStr.endsWith("]")) {
                JSONArray jay = JSONArray.fromObject(objectStr);
                my_decode_v2(jay);
                jsonObject.put(key, jay);
            } else {
                objectStr = URLDecoder.decode(objectStr, "utf-8");
                if (objectStr.equals("null") || objectStr.equals("undefined")) {
                    objectStr = "";
                }
                jsonObject.put(key, objectStr);
            }
        }
    }
}
