package com.idyoga.yoga.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.model.HomeExprienceTypeClassBean;
import com.idyoga.yoga.utils.DateUtils;

import java.util.List;

/**
 * Created by domain on 2018-03-21.
 * Email:sunlongyue@foxmail.com
 * describe:
 */

public class HomeTypeExprienceCourseRecycleViewAdapter extends BaseQuickAdapter<HomeExprienceTypeClassBean.DataBean.CourseListBean,BaseViewHolder> {


    public HomeTypeExprienceCourseRecycleViewAdapter(int layoutResId, @Nullable List<HomeExprienceTypeClassBean.DataBean.CourseListBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, HomeExprienceTypeClassBean.DataBean.CourseListBean item) {
        helper.setIsRecyclable(false);
        helper.setText(R.id.tv_limitNum,"还可预约 "+(item.getNumber()-item.getAllAppNum())+" 人")
                .setText(R.id.tv_name,item.getLessonName()+"")
                .setText(R.id.tv_introduction,item.getTagName()+"")
                .setText(R.id.tv_time, DateUtils.times1(item.getStart()+"")+"至"+DateUtils.times1(item.getEnd()+""))
                .setText(R.id.tv_shop_name,item.getShopName()+"")
                .addOnClickListener(R.id.btn_appointment)
                .setText(R.id.tv_shop_address,item.getShopAddress()+"");

        if (!item.getLessonImg().isEmpty()) {
            Glide.with(mContext).load(item.getLessonImg()).crossFade().into((ImageView) helper.getView(R.id.iv));
        } else {
            helper.setImageResource(R.id.iv,R.drawable.default_course_img);
        }
    }
}
