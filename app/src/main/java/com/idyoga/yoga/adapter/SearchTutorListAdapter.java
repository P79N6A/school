package com.idyoga.yoga.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.common.modle.CircleTransform;
import com.idyoga.yoga.model.SearchResultBean;
import com.idyoga.yoga.view.csstv.CSSTextView;

import java.util.List;

import vip.devkit.library.StringUtil;
import vip.devkit.view.common.suklib.view.FlowLayout.FlowLayout;
import vip.devkit.view.common.suklib.view.FlowLayout.TagAdapter;
import vip.devkit.view.common.suklib.view.FlowLayout.TagFlowLayout;

/**
 * 文 件 名: SearchTutorListAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/12
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class SearchTutorListAdapter extends BaseQuickAdapter<SearchResultBean.TutorListBean, BaseViewHolder> {


    String keyword="";
    OnItemClickListener mItemClickListener;
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public SearchTutorListAdapter(int layoutResId, @Nullable List<SearchResultBean.TutorListBean> data, String keyword) {
        super(layoutResId, data);
        this.keyword = keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    protected void convert(final BaseViewHolder helper, SearchResultBean.TutorListBean bean) {
        CSSTextView cssTextView = helper.getView(R.id.tv_name);
        cssTextView.setText(bean.getName());
        cssTextView.setTextArrColor(keyword, Color.parseColor("#ba6bb1"));
        Glide.with(mContext).load(bean.getImage()).placeholder(R.drawable.img_teacher).error(R.drawable.img_teacher).into((ImageView) helper.getView(R.id.iv_img));
        helper.setText(R.id.tv_type, StringUtil.isEmpty(bean.getMainstreamName()) ? "所属流派：暂未填写" : "所属流派：" + bean.getMainstreamName())
                .setText(R.id.tv_excellent, StringUtil.isEmpty(bean.getLessonName()) ? "擅长领域：暂未填写" : "擅长领域：" + bean.getLessonName());
        TagFlowLayout tagLayout = helper.getView(R.id.tag_view);
        tagLayout.setAdapter(new TagAdapter<SearchResultBean.TutorListBean.TutorLabelBean>(bean.getTutorLabel()) {
            @Override
            public View getView(FlowLayout parent, int position, SearchResultBean.TutorListBean.TutorLabelBean labelListBean) {
                LayoutInflater mInflater = LayoutInflater.from(mContext);
                TextView tv = (TextView) mInflater.inflate(R.layout.layout_option_tag, parent, false);
                tv.setText(labelListBean.getName());
                tv.setTextSize(10f);
                tv.setPadding(0, 0, 0, 0);
                tv.setTextColor(Color.parseColor("#b86caf"));
                tv.setBackgroundResource(R.drawable.bg_shop_01);
                return tv;
            }
        });
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener!=null){
                    mItemClickListener.onItemClick(SearchTutorListAdapter.this,view, helper.getLayoutPosition());
                }
            }
        });
    }


}
