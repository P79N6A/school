package com.idyoga.yoga.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {

    //解析单个数据
    public static String parserSingleData(String json, String info) {
        try {
            JSONObject obj = new JSONObject(json);
            return obj.getString(info);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }
}
