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
package com.idyoga.yoga.common.http.type2.listeners;

import com.zhy.http.okhttp.OkHttpUtils;

/**
 * 文 件 名: IRequestListener
 * 创 建 人: By k
 * 创建日期: 2018/3/25 15:17
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public interface IRequestListener {

    /**
     * when data call back success
     *
     * @param data
     */
    void onSuccess(int eventTag, String data);

    /**
     * when data call back error
     *
     * @param msg
     */
    void onError(int eventTag, String msg);

    /**
     * 是否正在刷新
     * @param status
     * @return
     */
//    void isRequesting(int eventTag, boolean status);

}
