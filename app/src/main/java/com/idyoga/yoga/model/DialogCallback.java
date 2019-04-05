package com.idyoga.yoga.model;

import android.app.Dialog;

/**
 * 文 件 名: DialogCallback
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/9/27
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public abstract class DialogCallback {


    public abstract void onSure(Dialog dialog);

    public void onCancel(Dialog dialog) {

    }
}
