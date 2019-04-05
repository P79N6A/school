package com.idyoga.yoga.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.model.PopupWindowItemBean;

import java.util.List;

public class PopupWindowSexAdapter extends CommonAdapter<PopupWindowItemBean> {


    public PopupWindowSexAdapter(Context context, List<PopupWindowItemBean> mBeanList, int layoutId) {
        super(context, mBeanList, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, PopupWindowItemBean bean, int position) {
        TextView textView = holder.getView(R.id.tv_city_name);
        ImageView imageView = holder.getView(R.id.iv_img);
        textView.setText(bean.getName());
        if (bean.isVisibility() == true) {
            textView.setTextColor(mContext.getResources().getColor(R.color.theme_1));
            imageView.setVisibility(View.VISIBLE);
        } else {
            textView.setTextColor(mContext.getResources().getColor(R.color.text_color));
            imageView.setVisibility(View.GONE);
        }
    }

    public void setVisibility(int position) {
        for (int i = 0; i < mBeanList.size(); i++) {
            if (position == i) {
                mBeanList.get(i).setVisibility(true);
            } else {
                mBeanList.get(i).setVisibility(false);
            }
        }
        notifyDataSetChanged();
    }
}
