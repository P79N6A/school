package com.idyoga.yoga.adapter.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.idyoga.yoga.R;

/**
 * 作者 by K
 * 时间：on 2017/9/4 10:55
 * 邮箱 by  vip@devkit.vip
 * <p/>
 * 类用途：
 * 最后修改：
 */
public class EmptyViewHolder extends RecyclerView.ViewHolder {


    public EmptyViewHolder(View itemView, boolean isVisibility) {
        super(itemView);
        ImageView mEmpty = (ImageView) itemView.findViewById(R.id.img_foot);
        if (isVisibility == false) {
            mEmpty.setVisibility(View.GONE);
        } else {
            mEmpty.setVisibility(View.VISIBLE);
        }
    }
}
