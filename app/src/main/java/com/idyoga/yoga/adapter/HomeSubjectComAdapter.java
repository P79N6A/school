package com.idyoga.yoga.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.model.HomeExprienceBean;
import com.idyoga.yoga.model.HomeSubjectItemBean;
import com.idyoga.yoga.utils.DateUtils;
import com.idyoga.yoga.utils.GlideImgManager;

import java.util.List;

public class HomeSubjectComAdapter extends BaseQuickAdapter<HomeSubjectItemBean.EquipmentBean, BaseViewHolder> {


    public HomeSubjectComAdapter(int layoutResId, @Nullable List<HomeSubjectItemBean.EquipmentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeSubjectItemBean.EquipmentBean item) {
        ImageView imageView = helper.getView(R.id.iv_home_item_img);
        Glide.with(mContext)
                .load(item.getImage_url())
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into(imageView);
    }
}
