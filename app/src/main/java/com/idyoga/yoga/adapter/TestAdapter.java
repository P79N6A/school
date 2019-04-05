package com.idyoga.yoga.adapter;

import android.content.Context;

import com.alibaba.android.vlayout.LayoutHelper;
import com.idyoga.yoga.adapter.base.BaseDelegateAdapter;

/**
 * 文 件 名: TestAdapter
 * 功能描述: 
 * 创 建 人: By k  
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/15
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public    class TestAdapter extends BaseDelegateAdapter {

    protected TestAdapter(Context context, LayoutHelper layoutHelper, int layoutId, int count, int viewTypeItem) {
        super(context, layoutHelper, layoutId, count, viewTypeItem);
    }
}
