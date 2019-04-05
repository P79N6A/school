package com.idyoga.yoga.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.MainActivity;
import com.idyoga.yoga.activity.shop.ShopImagesActivity;
import com.idyoga.yoga.common.modle.PostResult;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * @author weilgu
 * @time 2019/3/12 18:10
 * @des ${TODO}
 */

public class ShopImaAdapter extends RecyclerView.Adapter<ShopImaAdapter.ViewHolder> {

    private List<String> mImages;
    private Context mContext;
    private final LayoutInflater mFrom;
    private int mShopId;

    public ShopImaAdapter(Context context) {
        mContext = context;
        mFrom = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mFrom.inflate(R.layout.item_in_shop_detail_images_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(mImages.get(position))
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into(holder.mShopIma);
        holder.mCvRoot.setTag(position);
        holder.mCvRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((Activity) mContext, ShopImagesActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("shopId",String.valueOf(mShopId));
                //EventBus.getDefault().post(new PostResult("2ShopImagesActivity",bundle));
                ((Activity)mContext).startActivity(intent,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImages != null ? mImages.size() : 0;
    }

    public void setShopId(int id) {
        mShopId = id;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mShopIma;
        private CardView mCvRoot;

        public ViewHolder(View itemView) {
            super(itemView);
            mShopIma = itemView.findViewById(R.id.iv_shop_ima);
            mCvRoot = itemView.findViewById(R.id.cv_root);
        }
    }

    public void setDatas(List<String> images){
        this.mImages = images;
        notifyDataSetChanged();
    }
}
