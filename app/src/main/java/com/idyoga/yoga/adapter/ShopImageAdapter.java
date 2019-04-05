package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idyoga.yoga.R;

/**
 * @author weilgu
 * @time 2019/3/4 10:11
 * @des 店铺详情 头部的店铺图片 recyclerView 的adapter
 */

public class ShopImageAdapter extends RecyclerView.Adapter<ShopImageAdapter.ViewHolder> {

    private Context mContext;
    private final LayoutInflater mFrom;

    public ShopImageAdapter(Context context){
        this.mContext = context;
        mFrom = LayoutInflater.from(context);
    }

    @Override
    public ShopImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1){
            return new ViewHolder(mFrom.inflate(R.layout.item_shop_image_layout,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ShopImageAdapter.ViewHolder holder, int position) {

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
