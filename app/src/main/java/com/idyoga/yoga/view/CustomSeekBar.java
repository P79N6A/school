/**
 * ****************************** Copyright (c)*********************************\
 * *
 * *                 (c) Copyright 2017, DevKit.vip, china, qd. sd
 * *                          All Rights Reserved
 * *
 * *                           By(K)
 * *
 * *-----------------------------------版本信息------------------------------------
 * * 版    本: V0.1
 * *
 * *------------------------------------------------------------------------------
 * *******************************End of Head************************************\
 */
package com.idyoga.yoga.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

/**
 * 文 件 名: CustomSeekBar
 * 创 建 人: By k
 * 创建日期: 2018/3/27 15:17
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class CustomSeekBar extends android.support.v7.widget.AppCompatSeekBar {
    private boolean mSeekable = true;

    public CustomSeekBar(Context context) {
        super(context);
    }

    public CustomSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setSeekable(boolean seekable) {
        this.mSeekable = seekable;
    }

    public boolean isSeekable() {
        return mSeekable;
    }

    public boolean onTouchEvent(MotionEvent event) {
        return !mSeekable ? false : super.onTouchEvent(event);
    }
}

