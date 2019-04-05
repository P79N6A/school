package com.idyoga.yoga.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.model.HomeSubjectBean;
import com.idyoga.yoga.utils.GlideImgManager;

import java.util.List;

public class HomeCourseAdapter extends BaseQuickAdapter<HomeSubjectBean.VideoBean, BaseViewHolder> {


    public HomeCourseAdapter(int layoutResId, @Nullable List<HomeSubjectBean.VideoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeSubjectBean.VideoBean item) {
        helper.setText(R.id.tv_number,item.getNumber()+"人观看");
        ImageView imageView = helper.getView(R.id.iv_home_course);
        GlideImgManager.glideLoader(mContext,item.getImage_url(),R.drawable.default_course_img,imageView);
    }

    public void setNotifyDataSetChanged(){
        this.notifyDataSetChanged();
    }
}
