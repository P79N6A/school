package com.idyoga.yoga.adapter.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名: BaseDelegateAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/15
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class BaseDelegateAdapter extends DelegateAdapter.Adapter<BaseViewHolder> {

    protected LayoutHelper mLayoutHelper;
    protected int mCount = -1;
    protected int mLayoutId = -1;
    protected Context mContext;
    protected int mViewTypeItem = -1;

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    protected BaseDelegateAdapter(Context context, LayoutHelper layoutHelper, int layoutId, int count, int viewTypeItem) {
        this.mContext = context;
        this.mCount = count;
        this.mLayoutHelper = layoutHelper;
        this.mLayoutId = layoutId;
        this.mViewTypeItem = viewTypeItem;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == mViewTypeItem) {
            return new BaseViewHolder(LayoutInflater.from(mContext).inflate(mLayoutId, parent, false));
        }
        return null;
    }

    /**
     * 子类adapter实现
     *
     * @param holder   holder
     * @param position 索引
     */
    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(0,holder.getConvertView(),position);
                }
            }
        });

    }


    /**
     * 必须重写不然会出现滑动不流畅的情况
     */
    @Override
    public int getItemViewType(int position) {
        return mViewTypeItem;
    }

    /**
     * 条目数量
     */
    @Override
    public int getItemCount() {
        return mCount;
    }


    public BaseDelegateAdapter setCount(int count) {
        this.mCount = count;
        notifyDataSetChanged();
        return this;
    }
}