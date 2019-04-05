package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;

import java.util.List;

import vip.devkit.library.Logcat;

/**
 * 文 件 名: UserStudyCourseAdapter
 * 创 建 人: By k
 * 创建日期: 2018/5/9 09:45
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class UserStudyCourseAdapter extends DelegateAdapter.Adapter<BaseViewHolder> {

    int HEAD_VIEW;
    int FOOTER_VIEW;
    int EMPTY_VIEW;
    int PROGRESS_VIEW;

    private List<String> mList;
    private Context mContext;
    private int mViewTypeItem = 1;
    private LayoutHelper mHelper;


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mHelper;
    }

    public UserStudyCourseAdapter(Context context, List<String> list, LayoutHelper helper, int viewTypeItem) {
        Logcat.i("TAG" + list.size());
        this.mList = list;
        this.mContext = context;
        this.mHelper = helper;
        this.mViewTypeItem = viewTypeItem;
    }

    @Override
    public int getItemViewType(int position) {
        return mViewTypeItem;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == mViewTypeItem) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_user_study_course, parent, false);
            return new BaseViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {


    }


    @Override
    public int getItemCount() {
        return mList.size() == 0 ? 0 : mList.size();
    }

}