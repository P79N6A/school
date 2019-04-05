package com.idyoga.yoga.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.model.CourseHeadInfo;
import com.idyoga.yoga.model.realm.ShopExperienceCourseClass;
import com.idyoga.yoga.model.realm.ShopExperienceCourseClassTag;

import java.util.List;

//public class PopupWindowClassAdapter extends CommonAdapter<ShopExperienceCourseClass> {
public class PopupWindowClassAdapter extends CommonAdapter<CourseHeadInfo.LabelListBean> {
    //List<CourseHeadInfo.LabelListBean>
    /*public PopupWindowClassAdapter(Context context, List<ShopExperienceCourseClass> mBeanList, int layoutId) {
        super(context, mBeanList, layoutId);
    }*/

    public PopupWindowClassAdapter(Context context, List<CourseHeadInfo.LabelListBean> mBeanList, int layoutId) {
        super(context, mBeanList, layoutId);
    }


    @Override
    public void convert(ViewHolder holder, CourseHeadInfo.LabelListBean bean, int position) {
        holder.setText(R.id.tv_city_name,bean.getName());
        View view = holder.getView(R.id.v_tag);
        if (bean.isSelect()){
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.white));
            ((TextView)holder.getView(R.id.tv_city_name)).setTextColor(mContext.getResources().getColor(R.color.select_login_mode));
            view.setVisibility(View.VISIBLE);
        }else {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.bg_f8));
            ((TextView)holder.getView(R.id.tv_city_name)).setTextColor(mContext.getResources().getColor(R.color.text_color));
            view.setVisibility(View.GONE);
        }
    }

    public void setVisibility(int position) {
        if (position==-1){
            return;
        }
        for (int i = 0; i < mBeanList.size(); i++) {
            if (position == i) {
                mBeanList.get(i).setSelect(true);
            } else {
                mBeanList.get(i).setSelect(false);
            }
        }
        notifyDataSetChanged();
    }

}
