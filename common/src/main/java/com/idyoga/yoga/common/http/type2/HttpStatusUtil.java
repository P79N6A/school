package com.idyoga.yoga.common.http.type2;



import org.json.JSONObject;

import vip.devkit.library.StringUtil;

/**
 */
public class HttpStatusUtil {

    // 得到状态码
    public static boolean getStatus(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            int statusCode = jsonObject.getInt("code");
            if (statusCode == 1 || statusCode == 200) {
                return true;
            }
        } catch (Exception ex) {
        }
        return false;
    }

    /**
     * 得到状态提示
     *
     * @param json
     * @return
     */
    public static String getStatusMsg(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String message = jsonObject.getString("msg");

            if (!StringUtil.isEmpty(message)) {
                return message;
            }else {
                return json;
            }
        } catch (Exception ex) {

            return json;
        }
    }

    /**
     * 得到状态异常码
     *
     * @param json
     * @return
     */
    public static int getStatusError(String json) {

        try {
            JSONObject jsonObject = new JSONObject(json);
            int error = jsonObject.getInt("code");

            return error;
        } catch (Exception ex) {

            return 0;
        }
    }

    /**
     * 判断状态是否需要重新登录
     * @param json
     * @return
     */
    public static boolean isRelogin(String json) {

        int error = getStatusError(json);

        if (error == 5000) {

            return true;
        }

        return false;
    }


    /**
     * 判断状态是否需要登录才做控制
     * @param json
     * @return
     */
    public static boolean isLoginUser(String json) {

        int error = getStatusError(json);

        if (error == 6000) {

            return true;
        }

        return false;
    }

    /**
     * 判断状态是否需要登录才做控制
     * @return
     */
    public static String isShowToastStr(int eventTag,String data) {
//        if(eventTag == ApiContants.EventTags.LOGIN || eventTag == ApiContants.EventTags.REG ||
//                eventTag == ApiContants.EventTags.SMS || eventTag == ApiContants.EventTags.FINDPWD_SMS ||
//                eventTag == ApiContants.EventTags.FINDWD || eventTag == ApiContants.EventTags.UPDATEPWD ||
//                eventTag == ApiContants.EventTags.LOGINTOKEN || eventTag == ApiContants.EventTags.APPLYSTATE ||
//                eventTag == ApiContants.EventTags.BINGTELTHIRD )
//            return getStatusMsg(data);
        return "服务器异常";
    }
}
