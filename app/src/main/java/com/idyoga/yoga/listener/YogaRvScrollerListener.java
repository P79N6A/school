package com.idyoga.yoga.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @author weilgu
 * @time 2019/4/1 9:37
 * @des ${TODO}
 */

public abstract class YogaRvScrollerListener extends RecyclerView.OnScrollListener {

    private int mLastPosition;

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int totalItemCount = manager.getItemCount();
        int visibleItemCount = manager.getChildCount();
        if (visibleItemCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE && mLastPosition >= totalItemCount - 1){
            onLoadMore();
        }
    }

    public abstract void onLoadMore();

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        mLastPosition = layoutManager.findLastVisibleItemPosition();
    }
}
