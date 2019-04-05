package com.idyoga.yoga.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.model.ShopDetailDataBean;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/13 15:01
 * @des ${TODO}
 */

public class StartOrEndHolder extends RecyclerView.ViewHolder {

    private TextView tv_title;

    public StartOrEndHolder(View itemView) {
        super(itemView);
        tv_title = itemView.findViewById(R.id.tv_title);
    }

    public void bindView(String beans){
        tv_title.setText(beans);
    }
}
