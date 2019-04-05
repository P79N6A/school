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
package com.idyoga.yoga.common.http.type2.presenter;

import android.content.Context;

import java.util.Map;

/**
 * 文 件 名: ICommonRequestPresenter
 * 创 建 人: By k
 * 创建日期: 2018/3/25 15:15
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注： 做页面请求控制
 */
public interface ICommonRequestPresenter {

    void request(int eventTag, Context context, String url, Map<String, String> params);

    void requestGet(int eventTag, Context context, String url, Map<String, String> params);

}
