package com.idyoga.yoga.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.model.AddressBean;
import com.idyoga.yoga.model.CourseHeadInfo;
import com.idyoga.yoga.model.PopupWindowItemBean;

import java.util.List;

public class PopupWindowAddressAdapter extends CommonAdapter<CourseHeadInfo.AreaListBean> {


    public PopupWindowAddressAdapter(Context context, List<CourseHeadInfo.AreaListBean> mBeanList, int layoutId) {
        super(context, mBeanList, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, CourseHeadInfo.AreaListBean bean, int position) {
        TextView textView = holder.getView(R.id.tv_city_name);
        ImageView imageView = holder.getView(R.id.iv_img);
        textView.setText(bean.getName());
        if (bean.isSelect() == true) {
            textView.setTextColor(mContext.getResources().getColor(R.color.select_login_mode));
            imageView.setVisibility(View.VISIBLE);
        } else {
            textView.setTextColor(mContext.getResources().getColor(R.color.text_color));
            imageView.setVisibility(View.GONE);
        }
    }

    public void setVisibility(int position) {
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
