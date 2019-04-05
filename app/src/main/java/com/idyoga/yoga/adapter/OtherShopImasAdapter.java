package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/19 9:30
 * @des ${TODO}
 */

public class OtherShopImasAdapter extends RecyclerView.Adapter<OtherShopImasAdapter.ViewHolder> {

    private Context mContext;
    private final LayoutInflater mFrom;
    private List<String> mDatas;

    public OtherShopImasAdapter(Context context) {
        mContext = context;
        mFrom = LayoutInflater.from(mContext);
    }

    public void setDatas(List<String> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mFrom.inflate(R.layout.item_in_shop_detail_images_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String imaPath = mDatas.get(position);
        Glide.with(mContext)
                .load(imaPath)
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into(holder.iv_shop_ima);
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_shop_ima;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_shop_ima = itemView.findViewById(R.id.iv_shop_ima);
        }
    }
}
