package com.idyoga.yoga.utils.sp;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.model.CourseHeadInfo;
import com.idyoga.yoga.model.realm.ShopExperienceCourseClass;
import com.idyoga.yoga.model.realm.ShopExperienceHeadBeanV2;

import java.util.List;

import vip.devkit.library.ListUtil;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

public class ExperienceClassTagUtil {

    public static void setClassData(Context context, ShopExperienceHeadBeanV2 beanV2) {
        if (beanV2 == null) return;
        String json = JSON.toJSONString(beanV2);
        SharedPreferencesUtils.setSP(context, "experienceClass", json);
    }

    public static void setClassData(Context context, CourseHeadInfo bean) {
        if (bean == null) return;
        String json = JSON.toJSONString(bean);
        SharedPreferencesUtils.setSP(context, "courseHeadInfo", json);
    }

    public static ShopExperienceHeadBeanV2 getClassData(Context context) {
        String json = (String) SharedPreferencesUtils.getSP(context, "experienceClass", "");
        if (!StringUtil.isEmpty(json)) {
            return JSON.parseObject(json, ShopExperienceHeadBeanV2.class);
        }
        return null;
    }

    public static CourseHeadInfo getClassData(Context context,String key) {
        String json = (String) SharedPreferencesUtils.getSP(context, "courseHeadInfo", "");
        if (!StringUtil.isEmpty(json)) {
            return JSON.parseObject(json, CourseHeadInfo.class);
        }
        return null;
    }

    public static void setTagData(Context context, List<ShopExperienceCourseClass> tagList) {
        if (ListUtil.isEmpty(tagList)) return;
        String json = JSON.toJSONString(tagList);
        SharedPreferencesUtils.setSP(context, "experienceTag", json);
    }

    public static List<ShopExperienceCourseClass> getTagData(Context context) {
        String json = (String) SharedPreferencesUtils.getSP(context, "experienceTag", "");
        if (!StringUtil.isEmpty(json)) {
            return JSON.parseArray(json, ShopExperienceCourseClass.class);
        }
        return null;
    }
}



