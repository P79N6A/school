package com.idyoga.yoga.adapter;

import android.content.Context;
import android.opengl.Visibility;

import java.util.List;

import com.idyoga.yoga.R;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.common.view.Label.LabelLinearLayout;
import com.idyoga.yoga.model.MsgBean;


public class ScrollAdAdapter extends CommonAdapter<MsgBean> {

    int type;

    public ScrollAdAdapter(Context context, List<MsgBean> mBeanList, int layoutId, int type) {
        super(context, mBeanList, layoutId);
        this.type = type;
    }

    @Override
    public void convert(ViewHolder holder, MsgBean msgBean, int i) {
        LabelLinearLayout  mLabelL = holder.getView(R.id.ll_label);
        if (type == 1) {
            mLabelL.setTextContent("未使用");
        } else if (type == 2) {
            mLabelL.setTextContent("已过期");
        } else {
            mLabelL.setLabelVisable(false);
        }
        holder.setText(R.id.tv_name, msgBean.getClassName());

    }
}
