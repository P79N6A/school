package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idyoga.yoga.holder.LoadMoreHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/30 15:42
 * @des ${TODO}
 */

public abstract class BaseLoadMoreAdapter<T,K extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<K> {

    protected List<T> mDatas ;
    private static final int SHOW_LOAD_MORE = 1024;
    private Context mContext ;
    private final LayoutInflater mFrom;

    public BaseLoadMoreAdapter(Context context){
        this.mContext = context;
        mDatas = new ArrayList<>();
        mFrom = LayoutInflater.from(mContext);
    }

    @Override
    public K onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == SHOW_LOAD_MORE){
            return (K) new LoadMoreHolder(loadMoreView(parent));
        } else {
            return childAdapterContentHolder(parent,viewType);
        }
    }

    @Override
    public void onBindViewHolder(K holder, int position) {
        if (holder instanceof LoadMoreHolder){

        } else {
            bindView(holder,position);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() <= 0 ? 0 : mDatas.size() + 1 : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= mDatas.size()){
            return SHOW_LOAD_MORE;
        }else{
            return childAdapterItemType(position);
        }
    }

    //子类adapter 返回viewholder
    protected abstract K childAdapterContentHolder(ViewGroup parent,int viewType);

    //子类adapter 添加加载更多的布局
    protected abstract View loadMoreView(ViewGroup parent);

    //子类adapter绑定内容
    protected abstract void bindView(K holder, int position);

    //子类返回itemViewType
    protected abstract int childAdapterItemType(int position);
}
