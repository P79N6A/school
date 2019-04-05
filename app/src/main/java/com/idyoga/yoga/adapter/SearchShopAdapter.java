package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.baidu.location.BDLocation;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.lbs.LbsUtil;
import com.idyoga.yoga.model.SearchResultBean;

import java.util.List;

import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;

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
public class SearchShopAdapter extends BaseQuickAdapter<SearchResultBean.ShopListBean,BaseViewHolder>{

    OnItemClickListener mOnItemClickListener;
    Context mContext;

    public SearchShopAdapter(int layoutResId, @Nullable List<SearchResultBean.ShopListBean> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, SearchResultBean.ShopListBean item) {

    }



}
