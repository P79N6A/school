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
import com.idyoga.yoga.model.ShopDetailBean;
import com.idyoga.yoga.model.TutorDetailBean;
import com.idyoga.yoga.utils.DateUtils;

import java.util.List;

import vip.devkit.library.StringUtil;
import vip.devkit.view.common.suklib.view.FlowLayout.FlowLayout;
import vip.devkit.view.common.suklib.view.FlowLayout.TagAdapter;
import vip.devkit.view.common.suklib.view.FlowLayout.TagFlowLayout;

/**
 * 文 件 名: TutorVideoCourseAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/21
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class TutorVideoCourseAdapter extends BaseQuickAdapter<TutorDetailBean.VideoBean,BaseViewHolder> {
    public TutorVideoCourseAdapter(int layoutResId, @Nullable List<TutorDetailBean.VideoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TutorDetailBean.VideoBean item) {
        Glide.with(mContext)
                .load(item.getImage_url()).placeholder(R.drawable.img_course).error(R.drawable.img_course)
                .into((ImageView) helper.getView(R.id.iv_img));
        String tutor = "";
        if (!StringUtil.isEmpty(item.getTutor())) {
            tutor = item.getTutor();
        }
        helper.setText(R.id.tv_course_name, item.getTitle())
                .setText(R.id.tv_course_tutor, "导师：" + tutor)
                .setText(R.id.tv_course_time, "时长：" + DateUtils.secToTime(item.getTime()) + "");
        TagFlowLayout flowLayout = helper.getView(R.id.tag_view);
        flowLayout.setAdapter(new TagAdapter<TutorDetailBean.VideoBean.VideoLabelBean>(item.getVideoLabel()) {
            @Override
            public View getView(FlowLayout parent, int position, TutorDetailBean.VideoBean.VideoLabelBean videoLabelBean) {
                LayoutInflater mInflater = LayoutInflater.from(mContext);
                TextView tv = (TextView) mInflater.inflate(R.layout.option_item, parent, false);
                tv.setText(videoLabelBean.getName());
                tv.setTextColor(Color.parseColor("#b86caf"));
                tv.setBackgroundResource(R.drawable.bg_shop_01);
                return tv;
            }
        });

    }
}
