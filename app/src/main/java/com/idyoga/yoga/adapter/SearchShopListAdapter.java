package com.idyoga.yoga.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.listener.OnRvItemClickListener;
import com.idyoga.yoga.model.SearchResultBean;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.view.csstv.CSSTextView;

import java.util.List;

/**
 * 文 件 名: SearchShopListAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/12
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class SearchShopListAdapter extends BaseQuickAdapter<SearchResultBean.ShopListBean, BaseViewHolder> {

    private String keyword;
    private OnItemClickListener mItemClickListener;

    public SearchShopListAdapter(int layoutResId, @Nullable List<SearchResultBean.ShopListBean> data) {
        super(layoutResId, data);
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    protected void convert(final BaseViewHolder helper, SearchResultBean.ShopListBean item) {
        CSSTextView cssTextView = helper.getView(R.id.tv_shop_name);
        cssTextView.setText(item.getName());
        cssTextView.setTextArrColor(keyword, Color.parseColor("#ba6bb1"));
        helper.setText(R.id.tv_comment_num, 0 + "评价")
                .setText(R.id.tv_comment_num, item.getName())
                .setText(R.id.tv_address, item.getAddress())
                .setText(R.id.tv_distance, item.getCompare() + "km");
        Glide.with(mContext).load(item.getLogopath()).placeholder(R.drawable.img_course).error(R.drawable.img_course).into((ImageView) helper.getView(R.id.iv_img));
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener!=null){
                    mItemClickListener.onItemClick(SearchShopListAdapter.this,view, helper.getLayoutPosition());
                }
            }
        });
    }
}
