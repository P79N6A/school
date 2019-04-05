package com.idyoga.yoga.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import vip.devkit.view.common.badge.QBadgeView;

public class YogaViewUtil {

    /**
     * @param number
     * @return 返回  BadgeView方便 隐藏显示
     */
    public static QBadgeView initBadgeView(Context context, View view, int number) {
        QBadgeView mBadgeView = new QBadgeView(context);
        mBadgeView.bindTarget(view)
                .setBadgeGravity(Gravity.END | Gravity.TOP)
                .setGravityOffset(5, 5, true)
                .setBadgePadding(5, true)
                .setBadgeTextSize(10, true)
                .setBadgeNumber(number);
        return mBadgeView;
    }
    /**
     * @return 返回  BadgeView方便 隐藏显示
     */
    public static QBadgeView initBadgeView(Context context, View view) {
        QBadgeView mBadgeView = new QBadgeView(context);
        mBadgeView.bindTarget(view)
                .setBadgeGravity(Gravity.END | Gravity.CENTER)
                .setGravityOffset(5, 5, true)
                .setBadgePadding(5, true)
                .setBadgeTextSize(10, true);
        return mBadgeView;
    }
    /**
     * @return 返回  BadgeView方便 隐藏显示
     */
    public static QBadgeView initBadgeView(Context context, View view,String text) {
        QBadgeView mBadgeView = new QBadgeView(context);
        mBadgeView.bindTarget(view)
                .setBadgeGravity(Gravity.END | Gravity.CENTER)
                .setGravityOffset(5, 5, true)
                .setBadgePadding(5, true)
                .setBadgeTextSize(3, true)
                .setBadgeText(text);
        return mBadgeView;
    }
    /**
     * @return 返回  BadgeView方便 隐藏显示
     */
    public static QBadgeView initBadgeView(Context context, View view,String text,int size) {
        QBadgeView mBadgeView = new QBadgeView(context);
        mBadgeView.bindTarget(view)
                .setBadgeGravity(Gravity.END | Gravity.CENTER)
                .setBadgeTextSize(size, true)
                .setBadgeText(text);
        return mBadgeView;
    }

    /**
     * 获取屏幕尺寸 - 宽
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;
        return widthPixels;
    }
    /**
     * 获取屏幕尺寸 - GAO
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;
        return heightPixels;
    }

    /**
     * 测量view
     * @param view
     */
    public static void measureView(View view) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight, View.MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }
        view.measure(childWidthSpec, childHeightSpec);
    }

    /**
     * 获取view 的宽度
     * @param view
     * @return
     */
    public static int getViewWidth(View view) {
        measureView(view);
        return view.getMeasuredWidth();
    }

    /** 获取View 的高度
     * @param view
     * @return
     */
    public static int getViewHeight(View view) {
        measureView(view);
        return view.getMeasuredHeight();
    }


    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context
     * @param spVal
     * @return
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

}
