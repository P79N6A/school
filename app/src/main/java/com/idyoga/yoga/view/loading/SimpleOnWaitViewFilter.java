/*
******************************* Copyright (c)*********************************\
**
**                 (c) Copyright 2018, 珠海现联瑜君岚运营管理有限公司, china, qd. sd
**                                All Rights Reserved
**
**                           By(珠海现联瑜君岚运营管理有限公司)
********************************End of Head************************************\
*/
package com.idyoga.yoga.view.loading;

import android.view.View;
import android.view.ViewGroup;

/**
 * File   Name: SimpleOnWaitViewFilter
 * Create Date: 2018/10/22 16:29
 * Describe   :
 * Author     : By k
 * E-mail     : vip@devkit.vip
 * VersionName: 1
 * VersionCode: V 1.0
 * Code Update:（author - time）
 * Update Describe：
 */
public class SimpleOnWaitViewFilter implements OnWaitViewFilter {
    public FilterType onFilter(View view) {
        if (view == null) {
            return FilterType.Ignored;
        }
        // 过滤不可见的
        if (view.getVisibility() != View.VISIBLE) {
            return FilterType.Ignored;
        }
        // 过滤android.view.View
        if (View.class.equals(view.getClass())) {
            return FilterType.Ignored;
        }

        if (view instanceof ViewGroup) {
            return FilterType.Childs;
        } else {
            return FilterType.WaitView;
        }
    }
}