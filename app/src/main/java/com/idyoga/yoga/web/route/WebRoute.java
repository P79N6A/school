package com.idyoga.yoga.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.google.gson.Gson;
import com.idyoga.yoga.activity.MainActivity;
import com.idyoga.yoga.activity.home.AppointmentIntroductionActivity;
import com.idyoga.yoga.activity.home.SubjectListActivity;
import com.idyoga.yoga.activity.shop.ShopDetailActivity;
import com.idyoga.yoga.activity.web.ClassifyChooseActivity;
import com.idyoga.yoga.activity.web.ExperienceCourseClassifyActivity;
import com.idyoga.yoga.activity.web.ExperienceCourseClassifyDetailsActivity;
import com.idyoga.yoga.activity.web.ExperienceCourseDetailsActivity;
import com.idyoga.yoga.activity.web.OfflineCourseDetailsActivity;
import com.idyoga.yoga.activity.web.TutorDetailsActivity;
import com.idyoga.yoga.activity.web.UserCardDetailsActivity;
import com.idyoga.yoga.activity.web.UserCardListActivity;
import com.idyoga.yoga.activity.web.VideoCourseDetailsActivity;
import com.idyoga.yoga.activity.web.YogaGymClassifyActivity;
import com.idyoga.yoga.activity.web.YogaGymCourseDetailsActivity;
import com.idyoga.yoga.activity.web.YogaGymDetailsActivity;
import com.idyoga.yoga.activity.web.YogaWebActivity;
import com.idyoga.yoga.utils.JsonUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

/**
 *
 */
public class WebRoute {

    Context mContext;

    public static WebRoute init(Context context) {
        return new WebRoute(context);
    }

    public WebRoute(Context context) {
        this.mContext = context;
    }


    public void goToNext(String data) {
        String type = JsonUtil.parserSingleData(data, "type");
        String url = JsonUtil.parserSingleData(data, "url");
        Logcat.i("json:" + data + "type:" + type + " url:" + url);
        startActivity(mContext, type, url, data);
    }


    /**
     * 1			分类
     * 2			瑜伽馆详情
     * 3			瑜伽馆课程详情
     * 4			筛选
     * 5			导师详情
     * 6			线下课程详情
     * 7			视频课程详情
     * 8			权益课程详情
     * 9			权益课程分类列表
     * 10			权益课程分类详情
     * 11			会员卡列表
     * 12			会员卡详情
     * 13			Banner图跳转
     * @param context
     * @param type
     * @param url
     */
    private void startActivity(Context context, String type, String url, String data) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("getUrl", url);
        switch (type) {
            case "1":
                intent.setClass(context, YogaGymClassifyActivity.class);
                break;
            case "2":
                Uri uri = Uri.parse(url);
                bundle.putString("shopId",uri.getQueryParameter("shopId")+"");
                bundle.putString("name","");
                intent.setClass(context, ShopDetailActivity.class);
//                intent.setClass(context, YogaGymDetailsActivity.class);
                break;
            case "3":
                intent.setClass(context, YogaGymCourseDetailsActivity.class);
                break;
            case "4":
//                intent.setClass(context,com.idyoga.yoga.activity.course.ClassifyChoiceActivity.class);
                intent.setClass(context, com.idyoga.yoga.activity.tutor.TutorSelectActivity.class);
//                intent.setClass(context, ClassifyChooseActivity.class);
                break;
            case "5":
                intent.setClass(context, TutorDetailsActivity.class);
                break;
            case "6":
                bundle.putString("courseId", JsonUtil.parserSingleData(data, "courseId"));
                intent.setClass(context, OfflineCourseDetailsActivity.class);
                break;
            case "7"://视频ID
                bundle.putString("videoId", JsonUtil.parserSingleData(data, "videoId"));
                intent.setClass(context, SubjectListActivity.class);
                break;
            case "8":
                String shopId = (String) SharedPreferencesUtils.getSP(mContext, "shopId", "");
                bundle.putString("shopId", shopId+ "");
                bundle.putString("lessonId", JsonUtil.parserSingleData(data, "courseId"));
                intent.setClass(context, AppointmentIntroductionActivity.class);
//                intent.setClass(context, ExperienceCourseDetailsActivity.class);
                break;
            case "9":
                intent.setClass(context, ExperienceCourseClassifyActivity.class);
                break;
            case "10":
                intent.setClass(context, ExperienceCourseClassifyDetailsActivity.class);
                break;
            case "11":
                intent.setClass(context, UserCardListActivity.class);
                break;
            case "12":
                intent.setClass(context, UserCardDetailsActivity.class);
                break;
            case "13":
                intent.setClass(context, YogaWebActivity.class);
                break;
        }
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }


}
