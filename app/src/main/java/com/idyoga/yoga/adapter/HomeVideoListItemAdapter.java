package com.idyoga.yoga.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.model.HomeSubjectBean;
import com.idyoga.yoga.utils.GlideImgManager;

import java.util.List;

public class HomeVideoListItemAdapter extends CommonAdapter<HomeSubjectBean.VideoBean> {

    Context mContext;

    public HomeVideoListItemAdapter(Context context, List<HomeSubjectBean.VideoBean> mStringList, int layoutId) {
        super(context, mStringList, layoutId);
        this.mContext = context;
    }


    @Override
    public void convert(ViewHolder holder, final HomeSubjectBean.VideoBean bean, int i) {
        ImageView imageView =holder.getView(R.id.iv_subject_img);
        GlideImgManager.glideLoaderBlur(mContext,bean.getImage_url(),imageView,30,15);
        holder.setText(R.id.tv_subject_title, bean.getTitle()).setText(R.id.tv_subject_remarks, bean.getNumber() + "");
    }

}
