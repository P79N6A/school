package com.idyoga.yoga.listener;

import android.view.View;

/**
 * 文 件 名: OnLoadMoreListener
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/12
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public interface OnLoadMoreListener {
    void onLoadMore(int type, View view, int position);
}
