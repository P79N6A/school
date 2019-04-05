package com.idyoga.yoga.adapter;

import android.content.Context;

import com.idyoga.yoga.R;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.utils.YogaViewUtil;

import java.util.List;

/**
 *  咨询
 */
public class ConsultAdapter extends CommonAdapter<Object> {

    public ConsultAdapter(Context context, List<Object> mBeanList, int layoutId) {
        super(context, mBeanList, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, Object o, int i) {
        holder.setText(R.id.tv_title, "咨询 TITLE：" + i)
        .setText(R.id.tv_introduction, "咨询 内容：" + i);
        if (i==1){
            YogaViewUtil.initBadgeView(mContext,holder.getView(R.id.ll_layout),"",5);
        }
    }
}
