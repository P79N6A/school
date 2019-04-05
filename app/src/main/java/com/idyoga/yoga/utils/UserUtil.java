package com.idyoga.yoga.utils;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.idyoga.yoga.activity.user.LoginActivity;
import com.idyoga.yoga.model.UserInfoBean;

import java.util.List;

import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

/**
 * 文 件 名: UserUtil
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/11
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class UserUtil {

    public static void setUserBean(Context context,String s) {
        SharedPreferencesUtils.setSP(context, "UserInfoBean", s);
    }

    public static UserInfoBean getUserBean(Context context) {
        String getBean = (String) SharedPreferencesUtils.getSP(context,"UserInfoBean", "");
        if (!StringUtil.isEmpty(getBean)){
            UserInfoBean userInfoBean= JSON.parseObject(getBean,UserInfoBean.class);
            return userInfoBean;
        }else {
            return null;
        }
    }

}
