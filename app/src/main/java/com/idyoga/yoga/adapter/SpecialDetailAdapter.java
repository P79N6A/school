package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.listener.OnRecyclerItemClickListener;
import com.idyoga.yoga.model.SpecialDetailData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/21 10:07
 * @des ${TODO}
 */

public class SpecialDetailAdapter extends RecyclerView.Adapter<SpecialDetailAdapter.ViewHolder> {

    private Context mContext;
    private SpecialDetailData mBean;
    private List<SpecialDetailData.ShopListBean> mDatas;
    private final LayoutInflater mFrom;
    private OnRecyclerItemClickListener mListener;

    public SpecialDetailAdapter(Context context) {
        mContext = context;
        mFrom = LayoutInflater.from(mContext);
    }

    public void setDatas(SpecialDetailData bean) {
        this.mBean = bean;
        if (mBean != null){
            mDatas = bean.getShopList();
        }
        notifyDataSetChanged();
    }

    public void setRecyclerItemListener(OnRecyclerItemClickListener listener){
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mFrom.inflate(R.layout.item_special_head_ima,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        SpecialDetailData.ShopListBean bean = mDatas.get(position);
        if (position == 0){
            holder.ll_head.setVisibility(View.VISIBLE);
            String image_url = mBean.getImage_url();
            Glide.with(mContext)
                    .load(image_url)
                    .placeholder(R.drawable.img_course)
                    .error(R.drawable.img_course)
                    .into(holder.iv_special_head);
        }else{
            holder.ll_head.setVisibility(View.GONE);
        }
        Glide.with(mContext)
                .load(bean.getLogopath())
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into(holder.iv_img);
        holder.tv_shop_name.setText(bean.getShopName());
        holder.tv_address.setText(bean.getAddress());
        ArrayList<String> imagePaths = new ArrayList<>();
        String lessonImage = bean.getLessonImage();
        String shopImage = bean.getShopImage();
        String studentImage= bean.getStudentImage();
        imagePaths.add(lessonImage);
        imagePaths.add(shopImage);
        imagePaths.add(studentImage);
        holder.mAdapter.setDatas(imagePaths);
        holder.rl_itemView.setTag(position);
        holder.rl_itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.onClickListener(v ,(Integer) holder.rl_itemView.getTag());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_img;
        private TextView tv_shop_name;
        private TextView tv_address;
        private TextView tv_distance;
        private RecyclerView rlv_shop_image;
        private final ShopImaAdapter mAdapter;
        private LinearLayout ll_head;
        private ImageView iv_special_head;
        private RelativeLayout rl_itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_shop_name = itemView.findViewById(R.id.tv_shop_name);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_distance = itemView.findViewById(R.id.tv_distance);
            tv_distance.setVisibility(View.GONE);
            rlv_shop_image = itemView.findViewById(R.id.rlv_shop_image);
            ll_head = itemView.findViewById(R.id.ll_head);
            iv_special_head = itemView.findViewById(R.id.iv_special_head);
            rl_itemView = itemView.findViewById(R.id.rl_itemView);
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            rlv_shop_image.setLayoutManager(layoutManager);
            mAdapter = new ShopImaAdapter(mContext);
            rlv_shop_image.setAdapter(mAdapter);
        }
    }
}
