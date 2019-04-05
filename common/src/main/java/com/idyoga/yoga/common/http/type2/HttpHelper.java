/**
 * ****************************** Copyright (c)*********************************\
 * *
 * *                 (c) Copyright 2017, DevKit.vip, china, qd. sd
 * *                          All Rights Reserved
 * *
 * *                           By(K)
 * *
 * *-----------------------------------版本信息------------------------------------
 * * 版    本: V0.1
 * *
 * *------------------------------------------------------------------------------
 * *******************************End of Head************************************\
 */
package com.idyoga.yoga.common.http.type2;

import android.content.Context;

import java.util.Map;

/**
 * 文 件 名: HttpHelper
 * 创 建 人: By k
 * 创建日期: 2018/3/25 15:32
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class HttpHelper {

    private static volatile HttpHelper instance = null;

    private HttpHelper() {
    }

    public static HttpHelper getInstance() {
        if (null == instance) {
            synchronized (HttpHelper.class) {
                if (null == instance) {
                    instance = new HttpHelper();
                }
            }
        }
        return instance;
    }

    /**
     * post 请求
     * @param context
     * @param url
     * @param params
     * @param responseHandler
     */
    public void post(Context context, String url, Map<String, String> params,
                     OkHttpResponseHandler responseHandler) {

        if (context == null)
            return;
//        LoginMsg loginMsg = LoginMsgHelper.getResult(context);
//        if(loginMsg != null) {
//            params.put("token", loginMsg.getToken());
//            params.put("account",loginMsg.getAccount()+"");
//        }
//        params.put("plat","1");

        System.out.println(url);
        System.out.println(params.toString());
        OkHttpClientManager.postAsyn(url, params, responseHandler);

    }


    /**
     * get 请求
     * @param context
     * @param url
     * @param params
     * @param responseHandler
     */
    public void get(Context context, String url, Map<String, String> params,
                    OkHttpResponseHandler responseHandler) {

        if (context == null)
            return;

        if (url == null)
            return;

//        LoginMsg loginMsg = LoginMsgHelper.getResult(context);
//
//        if(loginMsg != null) {
//            params.put("token", loginMsg.getToken());
//            params.put("account",loginMsg.getAccount()+"");
//        }
//        params.put("plat","1");

        System.out.println(url);
        System.out.println(params.toString());
        url +="?";
        for (String key : params.keySet()) {
            String value = params.get(key);
            url += key + "=" + value +"&";
        }
        url = url.substring(0,url.length()-1);
        System.out.println("get请求》" + url);
        OkHttpClientManager.getAsyn(url, responseHandler);

    }
    /**
     * postJson 请求
     * @param context
     * @param url
     * @param json
     * @param responseHandler
     */
    public void postJson(Context context, String url, String json,
                     OkHttpResponseHandler responseHandler) {

        if (context == null)
            return;

//
//        LoginMsg loginMsg = LoginMsgHelper.getResult(context);
//
//        if(loginMsg != null) {
//            params.put("token", loginMsg.getToken());
//            params.put("account",loginMsg.getAccount()+"");
//        }

        OkHttpClientManager.postAsyn(url, json, responseHandler);

    }

}
