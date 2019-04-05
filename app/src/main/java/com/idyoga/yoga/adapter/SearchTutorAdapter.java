package com.idyoga.yoga.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.common.modle.CircleTransform;
import com.idyoga.yoga.listener.OnItemClickListener;
import com.idyoga.yoga.model.SearchResultBean;
import com.idyoga.yoga.view.csstv.CSSTextView;

import java.util.ArrayList;
import java.util.List;

import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.view.common.suklib.view.FlowLayout.FlowLayout;
import vip.devkit.view.common.suklib.view.FlowLayout.TagAdapter;
import vip.devkit.view.common.suklib.view.FlowLayout.TagFlowLayout;

/**
 * 文 件 名: SearchCourseAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/12
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class SearchTutorAdapter extends BaseQuickAdapter<SearchResultBean.TutorListBean, BaseViewHolder> {


    String keyword="";
    public SearchTutorAdapter(int layoutResId, @Nullable List<SearchResultBean.TutorListBean> data) {
        super(layoutResId, data);
    }
    public SearchTutorAdapter(int layoutResId, @Nullable List<SearchResultBean.TutorListBean> data,String keyword) {
        super(layoutResId, data);
        this.keyword = keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchResultBean.TutorListBean bean) {
        Glide.with(mContext).load(bean.getImage()).placeholder(R.drawable.img_02)
                .error(R.drawable.img_02).transform(new CircleTransform(mContext))
                .into((ImageView) helper.getView(R.id.iv_img));
        CSSTextView cssTextView = helper.getView(R.id.tv_name);
        cssTextView.setText(bean.getName());
        cssTextView.setTextArrColor(keyword, Color.parseColor("#b66bb1"));
    }


}
