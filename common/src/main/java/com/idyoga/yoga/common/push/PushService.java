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
package com.idyoga.yoga.common.push;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;


import vip.devkit.library.Logcat;
import vip.devkit.library.StringUtil;


/**
 * 文 件 名: PushService
 * 创 建 人: By k
 * 创建日期: 2017/11/8 11:36
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class PushService extends Service {


    private String Token, MemberCode;
    private MyBinder mBinder = new MyBinder();
    private Context mContext;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
//        Token = SharedPreferencesUtils.getSP(mContext, "Token");
//        MemberCode = SharedPreferencesUtils.getString(mContext, "MemberCode");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logcat.i("onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logcat.i("onDestroy() executed");
    }

    public class MyBinder extends Binder {

        public void startPushService() {
            Logcat.i("TAG", "startDownload() executed");
            // 执行具体的任务
            if (StringUtil.isEmpty(MemberCode) || StringUtil.isEmpty(Token)) {
                Logcat.i("用户未登录");
            } else {
                getMessageWarn(MemberCode, Token);
            }
        }
    }
    private void getMessageWarn(String memberCode,String token) {
    }

}
