package com.idyoga.yoga.utils;

import android.content.Context;

import com.idyoga.yoga.model.AdvertiBean;
import com.idyoga.yoga.view.dialog.AdvertiDialog;

import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * @author weilgu
 * @time 2019/4/2 10:25
 * @des ${TODO}
 */

public class ShowAdverti {

    private static ShowAdverti mInstance = null;

    public static ShowAdverti getInstance(){
        if (mInstance == null){
            mInstance = new ShowAdverti();
        }
        return mInstance;
    }

    //显示广告弹窗
    public void showAdverti(AdvertiBean adverti, Context context,String key) {
        if (adverti == null) return;
        String adverit  = (String) SharedPreferencesUtils.getSP(context,key,"");
        String todayDateTime = DateUtils.getTodayTime();
        String[] split = todayDateTime.split(" ");
        String currentH = null;
        if (split != null && split.length > 1){
            String[] subSplit = split[1].split(":");
            if (subSplit != null && subSplit.length > 0){
                //当前时间的小时
                currentH = subSplit[0];
            }
        }
        if (adverit != null && !adverit.isEmpty() && split != null && split.length > 1){
            //上次弹出广告日期相等,则不再弹出广告
            if (adverit.equals(split[0])){
                return;
            }
        }
        String start = DateUtils.time(String.valueOf(adverti.getStart_time()));
        String end = DateUtils.time(String.valueOf(adverti.getEnd_time()));
        String startH = null;
        String[] startSplit = start.split(" ");
        if (startSplit != null && startSplit.length > 1){
            String[] subSplit = startSplit[1].split(":");
            if (subSplit != null && subSplit.length > 0){
                //当前时间的小时
                startH = subSplit[0];
            }
        }
        String endH = null;
        String[] endSplit = end.split(" ");
        if (endSplit != null && endSplit.length > 1){
            String[] subSplit = endSplit[1].split(":");
            if (subSplit != null && subSplit.length > 0){
                //当前时间的小时
                endH = subSplit[0];
            }
        }
        Logcat.i("--------advertiTime current = " + currentH + " startH = " + startH + " endH = " + endH);
        if (Integer.valueOf(currentH) >= Integer.valueOf(startH) && Integer.valueOf(currentH) < Integer.valueOf(endH)){
            new AdvertiDialog(context,adverti).show();
            //保存弹出广告的日期
            SharedPreferencesUtils.setSP(context, key, split[0]);
        }
    }
}
