package com.idyoga.yoga.api;

/**
 * Created by mjh on 2019/1/7.
 */

public class HttpUrlUtil {

    //微信登陆的baseUrl
    private static String WX_LGOIN_BASE_URL = "https://api.weixin.qq.com/sns/oauth2/";

    private static String BASE_URL = "http://testyogabook.hq-xl.com/mall/";
    //验证码登陆
    public static String SMS_NUMBER_LOG = BASE_URL + "App_user_data/SmsloginUser";
    //账号密码登陆
    public static String PWD_NUMBER_LOG = BASE_URL + "App_user_data/login";
    //微信授权登陆  https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
    public static String WX_LOGIN_GET_ACCESS_TOKEN = WX_LGOIN_BASE_URL + "access_token";
    //微信授权登陆 刷新token  https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN
    public static String WX_LOGIN_REFRESH_TOKEN = WX_LGOIN_BASE_URL + "access_token";

}
