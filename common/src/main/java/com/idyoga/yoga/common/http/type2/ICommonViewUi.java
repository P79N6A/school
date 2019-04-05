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
package com.idyoga.yoga.common.http.type2;

/**
 * 文 件 名: ICommonViewUi
 * 创 建 人: By k
 * 创建日期: 2018/3/25 15:23
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public interface ICommonViewUi {

    /**
     *去请求数据
     */
    void toRequest(int eventTag);

    /**
     * 得到请求的结果
     * @param result
     */
    void getRequestData(int eventTag, String result);
    /**
     * 请求失败
     * @param eventTag
     * @param msg
     */
    void onRequestFailureException(int eventTag, String msg);



}
