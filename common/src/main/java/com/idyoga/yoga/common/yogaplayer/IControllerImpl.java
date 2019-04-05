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
package com.idyoga.yoga.common.yogaplayer;

/**
 * 文 件 名: IControllerImpl
 * 创 建 人: By k
 * 创建日期: 2018/3/27 15:18
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注： 播放功能回调
 */
public interface IControllerImpl {
    /**
     * 播放/暂停按钮被触发时
     */
    void onPlayTurn();

    /**
     * 进度条被用户拖动时触发
     */
    void onProgressChange(int state, int progress);

    /**
     * 触发全屏/退出全屏功能
     */
    void onOrientationChange();
}

