package com.idyoga.yoga.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.lbs.LbsUtil;
import com.idyoga.yoga.model.ExperienceClassCourseBean;
import com.idyoga.yoga.model.ExperienceCourseClassBean;
import com.idyoga.yoga.utils.CommonUtils;

import java.util.List;

import vip.devkit.library.SharedPreferencesUtils;

/**
 * 文 件 名: ExperienceClassCourseAdapter
 * 创 建 人: By k
 * 创建日期: 2018/5/22 15:00
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class ExperienceClassCourseAdapter extends BaseQuickAdapter<ExperienceClassCourseBean, BaseViewHolder> {

    public ExperienceClassCourseAdapter(int layoutResId, @Nullable List<ExperienceClassCourseBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExperienceClassCourseBean item) {
        String longitude = (String) SharedPreferencesUtils.getSP(mContext, "longitude", "");
        String latitude = (String) SharedPreferencesUtils.getSP(mContext, "latitude", "");
        String distance = LbsUtil.getDistance(
                Double.valueOf(longitude),
                Double.valueOf(latitude),
                Double.valueOf(item.getLongitude()),
                Double.valueOf(item.getLatitude()));
        Glide.with(mContext).load(item.getLessonImg()).placeholder(R.drawable.img_course).error(R.drawable.img_course).into((ImageView) helper.getView(R.id.iv_img));
        helper.setText(R.id.tv_course_name, item.getLessonName() + "")
                .setText(R.id.tv_course_tutor, "导师：" + item.getTutorName() + "")
                .setText(R.id.tv_course_time, "时间:" +
                        CommonUtils.getDateStringByTimeSTamp(Long.valueOf(item.getStart()), "MM/dd") + " " +
                        CommonUtils.getDateStringByTimeSTamp(Long.valueOf(item.getStart()), "HH:mm") + "-" +
                        CommonUtils.getDateStringByTimeSTamp(Long.valueOf(item.getEnd()), "HH:mm"))
                .setText(R.id.tv_distance, distance);
        helper.setVisible(R.id.tv_distance, true);
        TextView mTvPrice = helper.getView(R.id.tv_course_price_1);
        if (item.getIs_pay() != 0) {
            mTvPrice.setVisibility(View.GONE);
//            helper.setText(R.id.tv_course_price_1, mContext.getString(R.string.￥,item.getPrice()));
            helper.setText(R.id.tv_course_price_1, "￥" + item.getPrice());
        } else {
            mTvPrice.setVisibility(View.GONE);
        }
    }
}