package com.idyoga.yoga.view.vlayout;

import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.LayoutManagerHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.BaseLayoutHelper;
import com.alibaba.android.vlayout.layout.LayoutChunkResult;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;

/**
 * 文 件 名: OnePlusNLayoutHelperX
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/7/18
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class OnePlusNLayoutHelperX extends BaseLayoutHelper {
    @Override
    public void layoutViews(RecyclerView.Recycler recycler, RecyclerView.State state, VirtualLayoutManager.LayoutStateWrapper layoutState, LayoutChunkResult result, LayoutManagerHelper helper) {
        if (isOutOfRange(layoutState.getCurrentPosition())) {
            return;
        }


    }


}
