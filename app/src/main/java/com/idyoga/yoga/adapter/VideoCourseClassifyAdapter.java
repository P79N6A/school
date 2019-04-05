package com.idyoga.yoga.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.model.VideoCourseListBean;

import java.util.List;

import vip.devkit.view.common.suklib.view.FlowLayout.FlowLayout;
import vip.devkit.view.common.suklib.view.FlowLayout.TagAdapter;
import vip.devkit.view.common.suklib.view.FlowLayout.TagFlowLayout;

/**
 * 文 件 名: VideoCourseClassifyAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/28
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class VideoCourseClassifyAdapter extends CommonAdapter<VideoCourseListBean> {
    public VideoCourseClassifyAdapter(Context context, List<VideoCourseListBean> mBeanList, int layoutId) {
        super(context, mBeanList, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, VideoCourseListBean bean, int position) {
        Glide.with(mContext).load(bean.getImage_url()).placeholder(R.drawable.img_course).error(R.drawable.img_course).into((ImageView) holder.getView(R.id.iv_img));
        holder.setText(R.id.tv_course_name, bean.getTitle())
                .setText(R.id.tv_course_tutor, "导师：")
                .setText(R.id.tv_course_time, "时间："+bean.getTime())
//                .setText(R.id.tv_course_price_1, mContext.getString(R.string.￥, bean.getPrice()));
                .setText(R.id.tv_course_price_1, "￥" + bean.getPrice());
        holder.setVisibility(R.id.tv_course_price_2, false);
        TagFlowLayout flowLayout = holder.getView(R.id.tag_view);
        flowLayout.setAdapter(new TagAdapter<VideoCourseListBean.VideoLabelBean>(bean.getVideo_label()) {
            @Override
            public View getView(FlowLayout parent, int position, VideoCourseListBean.VideoLabelBean videoLabelBean) {
                LayoutInflater mInflater = LayoutInflater.from(mContext);
                TextView tv = (TextView) mInflater.inflate(R.layout.layout_option_tag, parent, false);
                tv.setText(videoLabelBean.getName());
                tv.setTextColor(Color.parseColor("#333333"));
                tv.setBackgroundResource(R.drawable.bg_serach_list_course_tag);
                return tv;
            }
        });
    }
}
