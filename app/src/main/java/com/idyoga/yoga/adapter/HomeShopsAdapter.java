package com.idyoga.yoga.adapter;

import android.content.Context;
import android.os.Bundle;
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
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.model.HomePageData;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * @author weilgu
 * @time 2019/3/20 17:46
 * @des ${TODO}
 */

public class HomeShopsAdapter extends RecyclerView.Adapter<HomeShopsAdapter.ViewHolder> {

    private Context mContext;
    private final LayoutInflater mFrom;
    private List<HomePageData.ShopListBean> mDatas;

    public HomeShopsAdapter(Context context) {
        mContext = context;
        mFrom = LayoutInflater.from(mContext);
    }

    @Override
    public HomeShopsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mFrom.inflate(R.layout.item_shop_info_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(HomeShopsAdapter.ViewHolder holder, int position) {
        final HomePageData.ShopListBean listBean = mDatas.get(position);
        int shop_id = listBean.getShop_id();
        String logopath = listBean.getLogopath();
        String name = listBean.getName();
        String address = listBean.getAddress();
        String compare = listBean.getCompare();//距离
        int is_verify = listBean.getIs_verify();//是否认证 0否1是
        String shopImage = listBean.getShopImage();
        String lessonImage = listBean.getLessonImage();
        String studentMienImage = listBean.getStudentMienImage();
        ArrayList<String> strings = new ArrayList<>();
        strings.add(shopImage);
        strings.add(lessonImage);
        strings.add(studentMienImage);
        holder.mShopName.setText(name);
        holder.mAddress.setText(address);
        if (compare != null){
            holder.mDistance.setText(compare + " km");
        }else{
            holder.mDistance.setVisibility(View.GONE);
        }
        Glide.with(mContext)
                .load(logopath)
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into(holder.mImage);
        holder.mAdapter.setShopId(shop_id);
        holder.mAdapter.setDatas(strings);
        holder.mRTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("shopId",String.valueOf(listBean.getShop_id()));
                bundle.putString("shopName",listBean.getName());
                EventBus.getDefault().post(new PostResult("2ShopDetailActivity",bundle));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public void setDatas(List<HomePageData.ShopListBean> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImage;
        private TextView mShopName;
        private TextView mAddress;
        private TextView mDistance;
        private RecyclerView mRlv;
        private LinearLayout mLlGroup;
        private TextView mObjectName;
        private TextView mNewPic;
        private TextView mOldPic;
        private LinearLayout mLlSpike;
        private TextView mSpikeName;
        private TextView mSpikePic;
        private RelativeLayout mRlRoot;
        private RelativeLayout mRlItemView;
        private final HomeGuessAdapter mAdapter;
        private RelativeLayout mRTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mRlRoot = (RelativeLayout) itemView.findViewById(R.id.rl_root);
            mRlItemView = (RelativeLayout) itemView.findViewById(R.id.rl_itemView);
            mImage = (ImageView) itemView.findViewById(R.id.iv_img);
            mShopName = (TextView) itemView.findViewById(R.id.tv_shop_name);
            mAddress = (TextView) itemView.findViewById(R.id.tv_address);
            mDistance = (TextView) itemView.findViewById(R.id.tv_distance);
            mRlv = (RecyclerView) itemView.findViewById(R.id.rlv_shop_image);
            mLlGroup = (LinearLayout) itemView.findViewById(R.id.rl_group);
            mObjectName = (TextView) itemView.findViewById(R.id.tv_object_name);
            mNewPic = (TextView) itemView.findViewById(R.id.tv_new_pic);
            mOldPic = (TextView) itemView.findViewById(R.id.tv_old_pic);
            mLlSpike = (LinearLayout) itemView.findViewById(R.id.rl_spike);
            mSpikeName = (TextView) itemView.findViewById(R.id.tv_spike_object_name);
            mSpikePic = (TextView) itemView.findViewById(R.id.tv_spike_new_pic);
            mRTitle = (RelativeLayout) itemView.findViewById(R.id.rl_title);
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRlv.setLayoutManager(layoutManager);
            mAdapter = new HomeGuessAdapter(mContext);
            mRlv.setAdapter(mAdapter);
        }
    }
}
