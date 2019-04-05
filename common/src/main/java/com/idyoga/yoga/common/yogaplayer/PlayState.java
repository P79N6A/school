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
 * 文 件 名: PlayState
 * 创 建 人: By k
 * 创建日期: 2018/3/27 15:15
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注： 播放状态
 */
public class PlayState {

    public static final int IDLE = 0x00;
    public static final int PREPARE = 0x01;
    public static final int PLAY = 0x02;
    public static final int PAUSE = 0x03;
    public static final int STOP = 0x04;
    public static final int COMPLETE = 0x05;
    public static final int ERROR = 0x06;
}
