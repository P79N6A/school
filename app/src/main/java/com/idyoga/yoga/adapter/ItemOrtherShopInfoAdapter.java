package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.model.NearbyShopListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/18 18:58
 * @des ${TODO}
 */

public class ItemOrtherShopInfoAdapter extends RecyclerView.Adapter<ItemOrtherShopInfoAdapter.ViewHolder> {

    private List<NearbyShopListBean> mBeans;
    private Context mContext;
    private final LayoutInflater mFrom;
    private List<String> mOtherShopImas;

    public ItemOrtherShopInfoAdapter(Context context) {
        mContext = context;
        mFrom = LayoutInflater.from(context);
        mOtherShopImas = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mFrom.inflate(R.layout.item_shop_course_list_v2,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mOtherShopImas.clear();
        NearbyShopListBean listBean = mBeans.get(position);
        String address = listBean.getAddress();
        String name = listBean.getName();
        String logopath = listBean.getLogopath();
        String compare = listBean.getCompare();
        holder.tv_shop_name.setText(name != null ? name : "");
        holder.tv_address.setText(address != null ? address : "");
        holder.tv_distance.setText(compare != null ? compare : "");
        Glide.with(mContext)
                .load(logopath)
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into(holder.iv_img);
        mOtherShopImas.add(listBean.getLessonImage());
        mOtherShopImas.add(listBean.getShopImage());
        mOtherShopImas.add(listBean.getStudentImage());
        holder.mImasAdapter.setDatas(mOtherShopImas);
    }

    @Override
    public int getItemCount() {
        return mBeans != null ? mBeans.size() : 0;
    }

    public void setDatas(List<NearbyShopListBean> beans){
        this.mBeans = beans;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_head_name;
        private ImageView iv_img;
        private TextView tv_shop_name;
        private TextView tv_address;
        private TextView tv_distance;
        private RecyclerView rlv_shop_image;
        private TextView tv_object_name;
        private TextView tv_new_pic;
        private TextView tv_spike_object_name;
        private TextView tv_spike_new_pic;
        private final OtherShopImasAdapter mImasAdapter;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_head_name = itemView.findViewById(R.id.tv_head_name);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_shop_name = itemView.findViewById(R.id.tv_shop_name);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_distance = itemView.findViewById(R.id.tv_distance);
            rlv_shop_image = itemView.findViewById(R.id.rlv_shop_image);
            tv_object_name = itemView.findViewById(R.id.tv_object_name);
            tv_object_name = itemView.findViewById(R.id.tv_object_name);
            tv_new_pic = itemView.findViewById(R.id.tv_new_pic);
            tv_spike_object_name = itemView.findViewById(R.id.tv_spike_object_name);
            tv_spike_new_pic = itemView.findViewById(R.id.tv_spike_new_pic);
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            rlv_shop_image.setLayoutManager(layoutManager);
            mImasAdapter = new OtherShopImasAdapter(mContext);
            rlv_shop_image.setAdapter(mImasAdapter);

        }
    }
}
