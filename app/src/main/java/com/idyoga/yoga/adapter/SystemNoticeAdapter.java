package com.idyoga.yoga.adapter;

import android.content.Context;

import com.idyoga.yoga.R;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.utils.YogaViewUtil;

import java.util.List;


/**
 * 系统通知
 */
public class SystemNoticeAdapter extends CommonAdapter<Object> {

    Context mContext;
    public SystemNoticeAdapter(Context context, List<Object> mBeanList, int layoutId) {
        super(context, mBeanList, layoutId);
        this.mContext = context;
    }

    @Override
    public void convert(ViewHolder holder, Object o, int i) {
        holder.setText(R.id.tv_title, "系统消息：" + i);
//        .setText(R.id.tv_introduction, "简介：" + i);
        if (i==1){
            YogaViewUtil.initBadgeView(mContext,holder.getView(R.id.ll_layout),"",5);
        }
    }
}
