package com.idyoga.yoga.view.layoutmanager;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.idyoga.yoga.view.YogaLayoutManager;

/**
 * 文 件 名: LViewHolder
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/7/20
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class LViewHolder {
    Context mContext;
    private SparseArray<View> mViews;
    private View mConvertView;

    public LViewHolder(Context context, View itemView) {
        this.mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<View>();
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }


    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    public LViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }


    public LViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public LViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public LViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public LViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public LViewHolder setTextColorRes(int viewId, int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    /**
     * @param viewId
     * @param visible  0 View.VISIBLE / 8=View.GONE / 4=View.INVISIBLE
     * @return
     */
    public LViewHolder setVisible(int viewId,int visible) {
        View view = getView(viewId);
        view.setVisibility(visible);
        return this;
    }
}
