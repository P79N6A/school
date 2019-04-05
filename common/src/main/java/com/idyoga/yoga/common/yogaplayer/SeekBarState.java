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
 * 文 件 名: SeekBarState
 * 创 建 人: By k
 * 创建日期: 2018/3/27 15:16
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注： 进度条状态
 */
public class SeekBarState {
    /**
     * 用户开始触摸
     */
    int START_TRACKING = 0X01;
    /**
     * 拖拽过程中
     */
    int IN_PROGRESS = 0X02;
    /**
     * 用户结束拖拽
     */
    int STOP_TRACKING = 0X03;
}
