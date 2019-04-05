/*
 ******************************* Copyright (c)*********************************\
 **
 **                 (c) Copyright 2018, 珠海现联瑜君岚运营管理有限公司, china, qd. sd
 **                                All Rights Reserved
 **
 **                           By(珠海现联瑜君岚运营管理有限公司)
 ********************************End of Head************************************\
 */
package com.idyoga.yoga.view.loading;

import android.content.Context;
import android.graphics.Color;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
import android.support.annotation.IntRange;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.idyoga.yoga.R;

/**
 * File   Name: WaitViewController
 * Create Date: 2018/10/22 16:28
 * Describe   : 显示等待View的控制器
 * Author     : By k
 * E-mail     : vip@devkit.vip
 * VersionName: 1
 * VersionCode: V 1.0
 * Code Update:（author - time）
 * Update Describe：
 * <p>
 * 渲染
 * 单个View
 * WaitViewController.from(mRootView).render();
 * <p>
 * 所有子View
 * WaitViewController.from(mRootView).renderChilds();
 * 移除
 * 单个View
 * WaitViewController.from(mRootView).remove();
 * <p>
 * 所有子View
 * WaitViewController.from(mRootView).removeChilds();
 * 额外的可配置项
 * WaitViewController controller = WaitViewController.from(mRootView);
 * <p>
 * 颜色：@ColorInt
 * controller.color(color);
 * <p>
 * 透明度：@IntRange(from=0, to=255)
 * controller.alpha(alpha);
 * <p>
 * 圆角半径：@Dimension
 * controller.radius(radius);
 * <p>
 * 绘制区域：如 new Rect(0, 0, view.getWidth(), view.getHeight())
 * controller.drawRect(rect);
 * controller.drawRect(width, height);
 * <p>
 * 过滤器：如 new SimpleOnWaitViewFilter()
 * controller.filter(filter);
 */
public class WaitViewController {

    public static WaitViewController from(View view) {
        if (view instanceof WaitView) {
            return ((WaitView) view).getController();
        }

        int width = view.getWidth();
        int height = view.getHeight();
        if (width == 0) {
            int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(spec, spec);
            width = view.getMeasuredWidth();
            height = view.getMeasuredHeight();
        }

        RectF drawRect = new RectF(0, 0, width - view.getPaddingLeft() - view.getPaddingRight(), height - view.getPaddingTop() - view.getPaddingBottom());
        WaitViewController controller = (WaitViewController) view.getTag(R.id.base_wait_view);
        if (controller == null) {
            controller = new WaitViewController(view, drawRect);
            view.setTag(R.id.base_wait_view, controller);
        }
        return controller;
    }

    private static final OnWaitViewFilter DEFAULT_WAITVIEW_FILTER = new SimpleOnWaitViewFilter();

    private final View mWrapperView;
    private OnWaitViewFilter mOnWaitViewFilter = DEFAULT_WAITVIEW_FILTER;

    @ColorInt
    private int mColor = Color.parseColor("#E9E9E9");
    @Dimension
    private int mRadius;
    @IntRange(from = 0, to = 255)
    private int mAlpha = 255;
    private RectF mDrawRect;

    private WaitView mWaitView;

    private WaitViewController(View view, RectF drawRect) {
        this.mWrapperView = view;
        this.mDrawRect = drawRect;
        mRadius = dp2px(view.getContext(), 4);
    }

    public WaitViewController color(@ColorInt int color) {
        mColor = color;
        return this;
    }

    public WaitViewController radius(@Dimension int radius) {
        mRadius = radius;
        return this;
    }

    public WaitViewController alpha(@IntRange(from = 0, to = 255) int alpha) {
        mAlpha = alpha;
        return this;
    }

    public WaitViewController drawRect(RectF drawRect) {
        mDrawRect = drawRect;
        return this;
    }

    public WaitViewController drawRect(int width, int height) {
        mDrawRect = new RectF(0, 0, width, height);
        return this;
    }

    public WaitViewController filter(OnWaitViewFilter filter) {
        mOnWaitViewFilter = filter;
        return this;
    }

    Context getContext() {
        return mWrapperView.getContext();
    }


    public View render() {
        if (mWaitView == null) {
            ViewGroup parent = (ViewGroup) mWrapperView.getParent();
            mWaitView = new WaitView(this);
            mWaitView.setId(mWrapperView.getId());
            mWaitView.color(mColor).radius(mRadius).alpha(mAlpha).drawRect(mDrawRect);
            ViewGroup.LayoutParams params = mWrapperView.getLayoutParams();
            int index = parent.indexOfChild(mWrapperView);
            parent.removeView(mWrapperView);
            parent.addView(mWaitView, index, params);
        } else {
            mWaitView.color(mColor).radius(mRadius).alpha(mAlpha).drawRect(mDrawRect);
        }
        return mWaitView;
    }

    public void renderChilds() {
        render(mWrapperView);
    }

    private void render(View view) {
        FilterType filterType = mOnWaitViewFilter.onFilter(view);
        switch (filterType) {
            case Ignored:
                break;
            case Childs:
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    final int count = viewGroup.getChildCount();
                    for (int i = 0; i < count; i++) {
                        View childView = viewGroup.getChildAt(i);
                        render(childView);
                    }
                }
                break;
            case WaitView:
                WaitViewController.from(view).render();
                break;
        }
    }

    public void remove() {
        if (mWaitView == null) {
            return;
        }
        ViewGroup parent = (ViewGroup) mWaitView.getParent();
        ViewGroup.LayoutParams params = mWaitView.getLayoutParams();
        int index = parent.indexOfChild(mWaitView);
        parent.removeView(mWaitView);
        parent.addView(mWrapperView, index, params);
        mWaitView = null;
    }

    public void removeChilds() {
        remove(mWrapperView);
    }

    private void remove(View view) {
        FilterType filterType = mOnWaitViewFilter.onFilter(view);
        switch (filterType) {
            case Ignored:
                break;
            case Childs:
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    final int count = viewGroup.getChildCount();
                    for (int i = 0; i < count; i++) {
                        View childView = viewGroup.getChildAt(i);
                        remove(childView);
                    }
                }
                break;
            case WaitView:
                WaitViewController.from(view).remove();
                break;
        }
    }


    private static int dp2px(Context context, float dp) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);

        return (int) (dp * dm.density + 0.5f);
    }

}
