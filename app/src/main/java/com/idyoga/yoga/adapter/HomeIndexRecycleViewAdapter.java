package com.idyoga.yoga.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.model.HomeExprienceBean;

import java.util.List;

/**
 * Created by domain on 2018-03-21.
 * Email:sunlongyue@foxmail.com
 * describe:
 */

public class HomeIndexRecycleViewAdapter extends BaseQuickAdapter<HomeExprienceBean.DataBean.TagListBean,BaseViewHolder> {
    public HomeIndexRecycleViewAdapter(int layoutResId, @Nullable List<HomeExprienceBean.DataBean.TagListBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, HomeExprienceBean.DataBean.TagListBean item) {
        helper.setIsRecyclable(false);
        helper.setText(R.id.tv,item.getName());
        Glide.with(mContext).load(item.getImage_url()).crossFade().into((ImageView) helper.getView(R.id.iv));
    }


}
