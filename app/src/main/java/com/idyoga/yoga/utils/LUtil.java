/*
******************************* Copyright (c)*********************************\
**
**                 (c) Copyright 2018, 珠海现联瑜君岚运营管理有限公司, china, qd. sd
**                                All Rights Reserved
**
**                           By(珠海现联瑜君岚运营管理有限公司)
********************************End of Head************************************\
*/
package com.idyoga.yoga.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Window;

import java.util.Formatter;
import java.util.List;
import java.util.Locale;

/**
 * File   Name: LUtil
 * Create Date: 2018/11/1 16:16
 * Describe   :
 * Author     : By k
 * E-mail     :vip@devkit.vip
 * VersionName: 1
 * VersionCode: V 1.0
 * Code Update:（author - time）
 * Update Describe：
 */
public class LUtil {

    public static final String TAG = "VD";

    public static String stringForTime(long timeMs) {
        if (timeMs <= 0 || timeMs >= 24 * 60 * 60 * 1000) {
            return "00:00";
        }
        long totalSeconds = timeMs / 1000;
        int seconds = (int) (totalSeconds % 60);
        int minutes = (int) ((totalSeconds / 60) % 60);
        int hours = (int) (totalSeconds / 3600);
        StringBuilder stringBuilder = new StringBuilder();
        Formatter mFormatter = new Formatter(stringBuilder, Locale.getDefault());
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    /**
     * This method requires the caller to hold the permission ACCESS_NETWORK_STATE.
     *
     * @param context context
     * @return if wifi is connected,return true
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * Get activity from context object
     *
     * @param context context
     * @return object of Activity or null if it is not Activity
     */
    public static Activity scanForActivity(Context context) {
        if (context == null) return null;

        if (context instanceof Activity) {
            return (Activity) context;
        } else if (context instanceof ContextWrapper) {
            return scanForActivity(((ContextWrapper) context).getBaseContext());
        }

        return null;
    }

    /**
     * Get AppCompatActivity from context
     *
     * @param context context
     * @return AppCompatActivity if it's not null
     */
    public static AppCompatActivity getAppCompActivity(Context context) {
        if (context == null) return null;
        if (context instanceof AppCompatActivity) {
            return (AppCompatActivity) context;
        } else if (context instanceof ContextThemeWrapper) {
            return getAppCompActivity(((ContextThemeWrapper) context).getBaseContext());
        }
        return null;
    }

    public static void setRequestedOrientation(Context context, int orientation) {
        if (LUtil.getAppCompActivity(context) != null) {
            LUtil.getAppCompActivity(context).setRequestedOrientation(
                    orientation);
        } else {
            LUtil.scanForActivity(context).setRequestedOrientation(
                    orientation);
        }
    }

    public static Window getWindow(Context context) {
        if (LUtil.getAppCompActivity(context) != null) {
            return LUtil.getAppCompActivity(context).getWindow();
        } else {
            return LUtil.scanForActivity(context).getWindow();
        }
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static void saveProgress(Context context, Object url, long progress) {
        Log.i(TAG, "saveProgress: " + progress);
        if (progress < 5000) {
            progress = 0;
        }
        SharedPreferences spn = context.getSharedPreferences("JZVD_PROGRESS",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spn.edit();
        editor.putLong("newVersion:" + url.toString(), progress).apply();
    }

    public static long getSavedProgress(Context context, Object url) {
        SharedPreferences spn = context.getSharedPreferences("JZVD_PROGRESS",
                Context.MODE_PRIVATE);
        return spn.getLong("newVersion:" + url.toString(), 0);
    }

    /**
     * if url == null, clear all progress
     *
     * @param context context
     * @param url     if url!=null clear this url progress
     */
    public static void clearSavedProgress(Context context, Object url) {
        if (url == null) {
            SharedPreferences spn = context.getSharedPreferences("LVD_PROGRESS",
                    Context.MODE_PRIVATE);
            spn.edit().clear().apply();
        } else {
            SharedPreferences spn = context.getSharedPreferences("JZVD_PROGRESS",
                    Context.MODE_PRIVATE);
            spn.edit().putLong("newVersion:" + url.toString(), 0).apply();
        }
    }


    public static <V> boolean isListEmpty(List<V> sourceList) {
        return (sourceList == null || sourceList.size() == 0);
    }

}
