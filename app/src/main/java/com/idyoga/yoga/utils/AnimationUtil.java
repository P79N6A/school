package com.idyoga.yoga.utils;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * 文 件 名: AnimationUtil
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/4/26
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class AnimationUtil  {
    /**
     * View渐隐动画效果
     */
    public static void setHideAnimation(final View view, int duration) {
        if (null == view || duration < 0) {
            return;
        }
        AlphaAnimation mHideAnimation = null;

        if (null != mHideAnimation) {
            mHideAnimation.cancel();
        }
        // 监听动画结束的操作
        mHideAnimation = new AlphaAnimation(1.0f, 0.0f);
        mHideAnimation.setDuration(duration);
        mHideAnimation.setFillAfter(true);
        mHideAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                view.setVisibility(View.GONE);
            }
        });
        view.startAnimation(mHideAnimation);
    }

    /**
     * View渐现动画效果
     */
    public static void setShowAnimation(final View view, int duration) {
        if (null == view || duration < 0) {
            return;
        }
        AlphaAnimation mShowAnimation = null;
        if (null != mShowAnimation) {
            mShowAnimation.cancel();
        }
        mShowAnimation = new AlphaAnimation(0.0f, 1.0f);
        mShowAnimation.setDuration(duration);
        mShowAnimation.setFillAfter(true);
        mShowAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
                view.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationRepeat(Animation arg0) {
            }
            @Override
            public void onAnimationEnd(Animation arg0) {
            }
        });
        view.startAnimation(mShowAnimation);
    }
}
