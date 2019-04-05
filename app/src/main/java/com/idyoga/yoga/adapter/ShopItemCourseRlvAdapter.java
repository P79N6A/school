package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idyoga.yoga.R;

/**
 * @author weilgu
 * @time 2019/2/28 17:13
 * @des 权益课页面 店铺图片adapter
 */

public class ShopItemCourseRlvAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private final LayoutInflater mFrom;

    public ShopItemCourseRlvAdapter(Context context){
        this.mContext = context;
        mFrom = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mFrom.inflate(R.layout.item_course_rlv_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
