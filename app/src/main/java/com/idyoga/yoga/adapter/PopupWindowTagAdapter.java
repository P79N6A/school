package com.idyoga.yoga.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.model.CourseHeadInfo;
import com.idyoga.yoga.model.realm.ShopExperienceCourseClassTag;

import java.util.List;

import vip.devkit.library.ListUtil;

public class PopupWindowTagAdapter extends CommonAdapter<CourseHeadInfo.LabelListBean.LabelBean> {
    List<CourseHeadInfo.LabelListBean.LabelBean> mBeanList;

    public PopupWindowTagAdapter(Context context, List<CourseHeadInfo.LabelListBean.LabelBean> mBeanList, int layoutId) {
        super(context, mBeanList, layoutId);
        this.mBeanList=mBeanList;
    }

    @Override
    public void convert(ViewHolder holder, CourseHeadInfo.LabelListBean.LabelBean bean, int position) {
        TextView textView = holder.getView(R.id.tv_city_name);
        ImageView imageView = holder.getView(R.id.iv_img);
        textView.setText(bean.getName());
        if (bean.isSelect()) {
            textView.setTextColor(mContext.getResources().getColor(R.color.select_login_mode));
            imageView.setVisibility(View.VISIBLE);
        } else {
            textView.setTextColor(mContext.getResources().getColor(R.color.text_color));
            imageView.setVisibility(View.GONE);
        }
    }

    public void setVisibility(int position) {
        for (CourseHeadInfo.LabelListBean.LabelBean bean : mBeanList) {
            bean.setSelect(false);
        }
        if (position==-1){return;}
        if (!ListUtil.isEmpty(mBeanList) && mBeanList.get(position)!=null){
            mBeanList.get(position).setSelect(true);
        }
        notifyDataSetChanged();
    }
}
