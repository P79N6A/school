package com.idyoga.yoga.utils;


import android.content.Context;
import android.content.Intent;

import com.idyoga.yoga.activity.user.LoginActivity;

import vip.devkit.library.SharedPreferencesUtils;

/**
 * 作者 by K
 * 时间：on 2018
 * 邮箱 by  vip@devkit.vip
 * <p/>
 * 类用途：
 * 最后修改：
 */
public class LoginUtil {

    public static boolean checkLogin(Context context) {
        String Mobile = (String) SharedPreferencesUtils.getSP(context, "Mobile", "Mobile");
        String Token = (String) SharedPreferencesUtils.getSP(context, "Token", "Token");
        int UserId = (int) SharedPreferencesUtils.getSP(context, "UserId", 0);
        if (UserId == 0 || CommonUtils.isBlank(Token)) {
//            ToastUtil.showToast("您还未登陆");
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkLogin(Context context, boolean isToLogin) {
        String Mobile = (String) SharedPreferencesUtils.getSP(context, "Mobile", "");
        String Token = (String) SharedPreferencesUtils.getSP(context, "Token", "");
        int UserId = (int) SharedPreferencesUtils.getSP(context, "UserId", 0);
        if (UserId == 0 || CommonUtils.isBlank(Token)) {
            if (isToLogin == true) {
//                ToastUtil.showToast("您还未登陆");
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
                return false;
            }
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkIsLogin(Context context) {
        String Mobile = (String) SharedPreferencesUtils.getSP(context, "Mobile", "");
        String Token = (String) SharedPreferencesUtils.getSP(context, "Token", "");
        int UserId = (int) SharedPreferencesUtils.getSP(context, "UserId", 0);
        if (UserId == 0 || CommonUtils.isBlank(Token)) {
            return false;
        } else {
            return true;
        }
    }

}
