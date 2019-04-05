package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.model.Aaaaa;
import com.idyoga.yoga.model.FollowShopBean;
import com.idyoga.yoga.model.FollowShopTime;
import com.idyoga.yoga.model.LessonListBean;
import com.idyoga.yoga.utils.DateUtils;
import com.idyoga.yoga.utils.ViewUtil;
import com.idyoga.yoga.view.RoundDrawable;
import com.idyoga.yoga.view.ShadowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/7 16:35
 * @des 我关注的瑜伽馆 adapter
 */

public class FollowAdater extends BaseMultiItemQuickAdapter<FollowShopBean,BaseViewHolder>{ // BaseLoadMoreAdapter<Object,FollowAdater.ViewHolder>{ //RecyclerView.Adapter<FollowAdater.ViewHolder> {

    private Context mContext;
    private LayoutInflater mFrom;
    //关注的时间
    private static final int FOLLOW_SHOP_TIME = 0;
    //店铺数据
    private static final int FOLLOW_SHOP = 1;

    public FollowAdater(List<FollowShopBean> data,Context context) {
        super(data);
        this.mContext = context;
        addItemType(FOLLOW_SHOP_TIME,R.layout.item_follow_shop_time_layout);
        addItemType(FOLLOW_SHOP,R.layout.item_follow_shop_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, FollowShopBean item) {
        int itemType = item.getItemType();
        if (itemType == FOLLOW_SHOP_TIME) {
            helper.setText(R.id.tv_follow_time,item.getTime());
        } else if (itemType == FOLLOW_SHOP){
            helper.setText(R.id.tv_shop_name,item.getShopName())
                    .setText(R.id.tv_address,item.getAddress());
            Glide.with(mContext)
                    .load(item.getLogopath())
                    .placeholder(R.drawable.img_course)
                    .error(R.drawable.img_course)
                    .into((ImageView)helper.getView(R.id.iv_shop_image));
            int is_verify = item.getIs_verify();
            if (is_verify == 0){ //未认证
                helper.getView(R.id.iv_vip).setVisibility(View.GONE);
            }else if (is_verify == 2){
                helper.getView(R.id.iv_vip).setVisibility(View.VISIBLE);
            }
        }
    }

    /*public FollowAdater(Context context) {
        super(context);
        this.mContext = context;
        this.mFrom = LayoutInflater.from(mContext);
    }*/

    /*public void setDatas(HashMap<String, List<FollowShopBean>> data) {
        mDatas.clear();
        for (String key:data.keySet()){
            FollowShopBean bean = new FollowShopBean();
            bean.setTime(key);
            mDatas.add(bean);
            mDatas.addAll(data.get(key));
        }
        notifyDataSetChanged();
    }*/

   /* @Override
    protected ViewHolder childAdapterContentHolder(ViewGroup parent, int viewType) {
        if (viewType == FOLLOW_SHOP_TIME) {
            return new ViewHolder(mFrom.inflate(R.layout.item_follow_shop_time_layout, parent, false), viewType);
        } else {
            return new ViewHolder(mFrom.inflate(R.layout.item_follow_shop_layout, parent, false));
        }
    }


    @Override
    protected View loadMoreView(ViewGroup parent) {
        return mFrom.inflate(R.layout.a_item_test_layout,parent,false);
    }

    @Override
    protected void bindView(ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == FOLLOW_SHOP_TIME) {
            String day = (String) mDatas.get(position);
            holder.bindFollowTimeView(day);
        } else {
            FollowShopBean shop = (FollowShopBean) mDatas.get(position);
            holder.bindFollowShopView(shop);
        }
    }

    @Override
    protected int childAdapterItemType(int position) {
        if ((mDatas.get(position) instanceof String)) {
            return FOLLOW_SHOP_TIME;
        } else {
            return FOLLOW_SHOP;
        }
    }*/

    /*class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIvShopImage;
        private TextView mtvShopName;
        private TextView mTvGroupBuy;
        private ImageView mIvVip;
        private TextView mTvSpike;
        private TextView mTvAddress;
        private TextView mTvFollowTime;
        private ShadowLayout mSl;

        public ViewHolder(View itemView) {
            this(itemView, -1);
        }

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == FOLLOW_SHOP_TIME) {
                mTvFollowTime = itemView.findViewById(R.id.tv_follow_time);
            } else {
                mIvShopImage = itemView.findViewById(R.id.iv_shop_image);
                mtvShopName = itemView.findViewById(R.id.tv_shop_name);
                mIvVip = itemView.findViewById(R.id.iv_vip);
                mTvGroupBuy = itemView.findViewById(R.id.tv_group_buy);
                mTvSpike = itemView.findViewById(R.id.tv_spike);
                mTvAddress = itemView.findViewById(R.id.tv_address);
                mSl = itemView.findViewById(R.id.sl);
            }
        }

        public void bindFollowTimeView(String day) {
            mTvFollowTime.setText("关注时间: " + day);
        }

        public void bindFollowShopView(FollowShopBean shop) {
            mSl.setBackground(new RoundDrawable(ViewUtil.dp2px(mContext,5),0.2f));
            Glide.with(mContext)
                    .load(shop.getLogopath())
                    .placeholder(R.drawable.img_course)
                    .error(R.drawable.img_course)
                    .into(mIvShopImage);
            mtvShopName.setText(shop.getShopName());
            mTvAddress.setText(shop.getAddress());
            int is_verify = shop.getIs_verify();
            if (is_verify == 0){ //未认证
                mIvVip.setVisibility(View.GONE);
            }else if (is_verify == 2){
                mIvVip.setVisibility(View.VISIBLE);
            }

        }
    }*/
}
