package com.idyoga.yoga.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.shop.ShopOfflineCourseActivity;
import com.idyoga.yoga.model.HomeExperienceBean;
import com.idyoga.yoga.model.HomeExperienceShopBean;
import com.idyoga.yoga.utils.CommonUtils;

import java.util.List;

public class TestShopAdapter extends BaseQuickAdapter<HomeExperienceShopBean,BaseViewHolder>{
    public TestShopAdapter(int layoutResId, @Nullable List<HomeExperienceShopBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, final HomeExperienceShopBean bean) {
//        holder.setImageUrl(R.id.iv_img, bean.getLogopath());
        holder.setText(R.id.tv_shop_name, bean.getName())
                .setText(R.id.tv_comment, bean.getMerid() + "条评价")
                .setText(R.id.tv_address, bean.getAddress());
        if (bean.getCourse().size() == 1) {
            holder.getView(R.id.rl_course_1).setVisibility(View.VISIBLE);
            holder.getView(R.id.rl_all).setVisibility(View.GONE);
            holder.setText(R.id.tv_course_name_1, bean.getCourse().get(0).getLessonName())
                    .setText(R.id.tv_time_1,
                            CommonUtils.getDateStringByTimeSTamp(
                                    Long.valueOf(bean.getCourse().get(0).getStart_time()), "MM/dd") + " " +
                                    CommonUtils.getDateStringByTimeSTamp(
                                            Long.valueOf(bean.getCourse().get(0).getStart_time()), "HH:mm") + "-" +
                                    CommonUtils.getDateStringByTimeSTamp(
                                            Long.valueOf(bean.getCourse().get(0).getEnd_time()), "HH:mm"));
        } else if (bean.getCourse().size() >= 2) {
            holder.getView(R.id.rl_course_1).setVisibility(View.VISIBLE);
            holder.getView(R.id.rl_course_2).setVisibility(View.VISIBLE);
            holder.getView(R.id.rl_all).setVisibility(View.VISIBLE);
            holder.setText(R.id.tv_course_name_1, bean.getCourse().get(0).getLessonName())
                    .setText(R.id.tv_time_1,
                            CommonUtils.getDateStringByTimeSTamp(
                                    Long.valueOf(bean.getCourse().get(0).getStart_time()), "MM/dd") + " " +
                                    CommonUtils.getDateStringByTimeSTamp(
                                            Long.valueOf(bean.getCourse().get(0).getStart_time()), "HH:mm") + "-" +
                                    CommonUtils.getDateStringByTimeSTamp(
                                            Long.valueOf(bean.getCourse().get(0).getEnd_time()), "HH:mm"))
                    .setText(R.id.tv_course_name_2, bean.getCourse().get(1).getLessonName())
                    .setText(R.id.tv_time_2,
                            CommonUtils.getDateStringByTimeSTamp(
                                    Long.valueOf(bean.getCourse().get(1).getStart_time()), "MM/dd") + " " +
                                    CommonUtils.getDateStringByTimeSTamp(
                                            Long.valueOf(bean.getCourse().get(1).getStart_time()), "HH:mm") + "-" +
                                    CommonUtils.getDateStringByTimeSTamp(
                                            Long.valueOf(bean.getCourse().get(1).getEnd_time()), "HH:mm"));
        } else {

        }
        holder.setOnClickListener(R.id.rl_course_1, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("shopId",bean.getId()+"");
                bundle.putString("shopName",bean.getName()+"");
                Intent intent = new Intent(mContext, ShopOfflineCourseActivity.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        holder.setOnClickListener(R.id.rl_all, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("shopId",bean.getId()+"");
                bundle.putString("shopName",bean.getName()+"");
                Intent intent = new Intent(mContext, ShopOfflineCourseActivity.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }
}
