package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.model.HomeExperienceBean;
import com.idyoga.yoga.model.HomeExperienceCourseBean;
import com.idyoga.yoga.utils.CommonUtils;

import java.util.List;

public class ExperienceCourseAdapter extends BaseQuickAdapter<HomeExperienceCourseBean, BaseViewHolder> {
    public ExperienceCourseAdapter(int layoutId, List<HomeExperienceCourseBean> mBeanList) {
        super(layoutId, mBeanList);
    }

    public void setData(List<HomeExperienceCourseBean> mBeanList) {
        mData.clear();
        mData.addAll(mBeanList);
        notifyDataSetChanged();
    }


    @Override
    protected void convert(BaseViewHolder helper, HomeExperienceCourseBean bean) {
        Glide.with(mContext).load(bean.getLessonImage()).placeholder(R.drawable.img_course).error(R.drawable.img_course).into((ImageView) helper.getView(R.id.iv_img));
        helper.setText(R.id.tv_course_name, bean.getLessonName())
                .setText(R.id.tv_course_tutor, "导师:" + bean.getTutorName())
                .setText(R.id.tv_course_time, "时间：" + CommonUtils.getDateStringByTimeSTamp(Long.valueOf(bean.getStart_time()), "MM/dd") + " " +
                        CommonUtils.getDateStringByTimeSTamp(Long.valueOf(bean.getStart_time()), "HH:mm") + "-" +
                        CommonUtils.getDateStringByTimeSTamp(Long.valueOf(bean.getEnd_time()), "HH:mm"));
        TextView mTvPrice = helper.getView(R.id.tv_course_price_1);
        TextView mTvPrice2 = helper.getView(R.id.tv_course_price_2);
        mTvPrice2.setVisibility(View.GONE);
        mTvPrice.setVisibility(View.GONE);
//        helper.setText(R.id.tv_course_price_1, mContext.getString(R.string.￥, "0.00"));
        helper.setText(R.id.tv_course_price_1, "￥" + "0.00");

    }
}
