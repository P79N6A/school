package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

import java.util.List;

/**
 * 文 件 名: YogaBaseAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/12
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class YogaBaseAdapter<T> extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {

    //这些属性都是每个适配器中都能用到的，访问控制符设置为protected，以便继承的子类都能访问
    protected LayoutInflater mInflater;
    protected List<T> mBeanList;//数据源
    protected Context mContext;
    protected int layoutId;//item布局文件
    LayoutHelper mLayoutHelper;

    public YogaBaseAdapter(Context context, List<T> mBeanList, LayoutHelper helper, int layoutId) {
        this.mContext = context;
        this.mBeanList = mBeanList;
        mInflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
        mLayoutHelper = helper;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
