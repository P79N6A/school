package com.idyoga.yoga.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.common.modle.PostResult;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * @author weilgu
 * @time 2019/3/11 15:18
 * @des 猜你喜欢
 */

public class HomeGuessAdapter extends RecyclerView.Adapter<HomeGuessAdapter.ViewHolder> {

    private Context mContext;
    private final LayoutInflater mFrom;
    private List<String> mPaths;
    private int mShopId;

    public HomeGuessAdapter(Context context) {
        mContext = context;
        mFrom = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mFrom.inflate(R.layout.item_course_rlv_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String path = mPaths.get(position);
        Glide.with(mContext)
                .load(path)
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into(holder.mIvShopImage);
        holder.mCvRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("shopId",String.valueOf(mShopId));
                EventBus.getDefault().post(new PostResult("2ShopImagesActivity",bundle));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPaths != null ? mPaths.size() : 0;
    }

    public void setDatas(ArrayList<String> strings) {
        mPaths = strings;
        notifyDataSetChanged();
    }

    public void setShopId(int shop_id) {
        this.mShopId = shop_id;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIvShopImage;
        private CardView mCvRoot;

        public ViewHolder(View itemView) {
            super(itemView);
            mIvShopImage = itemView.findViewById(R.id.iv_shop_image);
            mCvRoot = itemView.findViewById(R.id.cv_root);
        }
    }
}
