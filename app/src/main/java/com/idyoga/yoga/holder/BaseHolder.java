package com.idyoga.yoga.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author weilgu
 * @time 2019/3/30 17:12
 * @des ${TODO}
 */

public abstract class BaseHolder extends RecyclerView.ViewHolder {

    public BaseHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindView(Object data);
}
