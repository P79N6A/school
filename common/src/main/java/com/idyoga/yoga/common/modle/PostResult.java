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
package com.idyoga.yoga.common.modle;


import com.baidu.mapapi.OpenLogUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.io.Serializable;

/**
 * 文 件 名: PostResult
 * 创 建 人: By k
 * 创建日期: 2018/3/25 15:43
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class PostResult implements Serializable {

    public String tag;//eventbus标签，用来区分

    public Object result;//eventbus 传递的结果

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public PostResult(){

    }
    public PostResult(String tag){
        setTag(tag);
        setResult("");
    }
    public PostResult(String tag,Object result){
        setTag(tag);
        setResult(result);

    }

    @Override
    public String toString() {
        return "PostResult{" +
                "tag='" + tag + '\'' +
                ", result=" + result +
                '}';
    }
}
