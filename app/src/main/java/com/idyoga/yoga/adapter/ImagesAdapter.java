package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.model.ImagesBean;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/15 17:50
 * @des ${TODO}
 */

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    private Context mContext;
    private final LayoutInflater mFrom;
    private List<ImagesBean> mImagesBeans;

    public ImagesAdapter(Context context) {
        mContext = context;
        mFrom = LayoutInflater.from(mContext);
    }

    @Override
    public ImagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mFrom.inflate(R.layout.item_images_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(ImagesAdapter.ViewHolder holder, int position) {
        ImagesBean imagesBean = mImagesBeans.get(position);
        Glide.with(mContext)
                .load(imagesBean.getImage())
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into(holder.mIv);
    }

    @Override
    public int getItemCount() {
        return mImagesBeans != null ? mImagesBeans.size() : 0;
    }

    public void setDatas(List<ImagesBean> imagesBeans) {
        this.mImagesBeans = imagesBeans;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIv;

        public ViewHolder(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv);
        }
    }
}
