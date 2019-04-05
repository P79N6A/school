package com.idyoga.yoga.listener;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

/**
 * 文 件 名: OnVerticalScrollListener
 * 创 建 人: By k
 * 创建日期: 2018/5/22 14:52
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class OnVerticalScrollListener extends RecyclerView.OnScrollListener {
    int x, y;

    @Override
    public final void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        y += dy;
        x += dx;
        if (!recyclerView.canScrollVertically(-1)) {
            onScrolledToTop();
            y=0;x=0;
        } else if (!recyclerView.canScrollVertically(1)) {
            onScrolledToBottom();
        } else if (dy < 0) {
            onScrolledUp();
            onScrolledUp(x, y);
        } else if (dy > 0) {
            onScrolledDown();
            onScrolledDown(x, y);
        }
    }


    /**
     * 向上滑动
     */
    public void onScrolledUp() {
    }

    /**
     * 向下滑动
     */
    public void onScrolledDown() {
    }

    /**
     * 向上滑动
     */
    public void onScrolledUp(int dx, int dy) {
    }

    /**
     * 向下滑动
     */
    public void onScrolledDown(int dx, int dy) {
    }

    /**
     * 滑动顶部
     */
    public void onScrolledToTop() {

    }

    /**
     * 滑动底部
     */
    public void onScrolledToBottom() {
    }
}