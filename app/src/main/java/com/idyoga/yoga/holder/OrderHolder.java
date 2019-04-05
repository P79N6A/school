package com.idyoga.yoga.holder;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.HomeGuessAdapter;
import com.idyoga.yoga.adapter.HomeShopsAdapter;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.model.HomePageData;
import com.idyoga.yoga.model.HomeRecommendBean;
import com.idyoga.yoga.utils.ViewUtil;
import com.idyoga.yoga.view.ItemListView;
import com.sina.weibo.sdk.utils.UIUtils;
import com.zzhoujay.markdown.parser.Line;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import vip.devkit.library.Logcat;

/**
 * @author weilgu
 * @time 2019/3/11 15:40
 * @des home 猜你喜欢
 */

public class OrderHolder extends BaseHolder{//RecyclerView.ViewHolder {
    private Context mContext;
    private TextView mHeadName;
//    private RecyclerView mShopList;
    private ItemListView mShopList;
    private HomeShopsAdapter mShopsAdapter;
    private List<HomePageData.ShopListBean> mBeans;
    private LayoutInflater mFrom;
    private LikeAdapter mLikeAdapter;
    //private View mInclude;

    public OrderHolder(View itemView) {
        this(itemView,null);
    }

    @Override
    public void bindView(Object data) {
        List<HomePageData.ShopListBean> beans = (List<HomePageData.ShopListBean>) data;
        mBeans = beans;
        mHeadName.setText("猜你喜欢");
        if (mLikeAdapter != null){
            mLikeAdapter.notifyDataSetChanged();
        }
    }

    public OrderHolder(View itemView, Context context){
        super(itemView);
        this.mContext = context;
        mFrom = LayoutInflater.from(context);
        initView(itemView);
    }

    private void initView(View itemView) {
        mHeadName = (TextView)itemView.findViewById(R.id.tv_head_name);
        mShopList = itemView.findViewById(R.id.rlv);
        mLikeAdapter = new LikeAdapter();
        mShopList.setAdapter(mLikeAdapter);
        /*mShopList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HomePageData.ShopListBean listBean = mBeans.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("shopId",String.valueOf(listBean.getShop_id()));
                bundle.putString("shopName",listBean.getName());
                EventBus.getDefault().post(new PostResult("2ShopDetailActivity",bundle));
            }
        });*/
       /*
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mShopList.setLayoutManager(layoutManager);
        mShopsAdapter = new HomeShopsAdapter(mContext);
        mShopList.setAdapter(mShopsAdapter);*/
        //mInclude = itemView.findViewById(R.id.i_head);
    }

    class LikeAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mBeans != null ? mBeans.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null){
                holder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_shop_info_layout, parent, false);
                Logcat.i("----------------------convertView = " + convertView);
                holder.mRlItemView = (RelativeLayout) convertView.findViewById(R.id.rl_itemView);
                holder.mImage = (ImageView) convertView.findViewById(R.id.iv_img);
                holder.mShopName = (TextView) convertView.findViewById(R.id.tv_shop_name);
                holder.mAddress = (TextView) convertView.findViewById(R.id.tv_address);
                holder.mDistance = (TextView) convertView.findViewById(R.id.tv_distance);
                holder.mRlv = (RecyclerView) convertView.findViewById(R.id.rlv_shop_image);
                holder.mLlLayoutItem = (LinearLayout) convertView.findViewById(R.id.ll_layout_item);
                /*holder.mLlGroup = (LinearLayout) convertView.findViewById(R.id.rl_group);
                holder.mObjectName = (TextView) convertView.findViewById(R.id.tv_object_name);
                holder.mNewPic = (TextView) convertView.findViewById(R.id.tv_new_pic);
                holder.mOldPic = (TextView) convertView.findViewById(R.id.tv_old_pic);
                holder.mLlSpike = (LinearLayout) convertView.findViewById(R.id.rl_spike);
                holder.mSpikeName = (TextView) convertView.findViewById(R.id.tv_spike_object_name);
                holder.mSpikePic = (TextView) convertView.findViewById(R.id.tv_spike_new_pic);*/
                holder.mRTitle = (RelativeLayout) convertView.findViewById(R.id.rl_title);
                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                holder.mRlv.setLayoutManager(layoutManager);
                holder.mAdapter = new HomeGuessAdapter(mContext);
                holder.mRlv.setAdapter(holder.mAdapter);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            int paddingTop = ViewUtil.dp2px(mContext, 15);
            if (position == 0){
                holder.mLlLayoutItem.setPadding(0,0,0,paddingTop);
            }else{
                holder.mLlLayoutItem.setPadding(0,paddingTop,0,paddingTop);
            }
            addData2View(position, holder);
            holder.mRlItemView.setTag(position);
            final ViewHolder finalHolder = holder;
            holder.mRlItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HomePageData.ShopListBean listBean = mBeans.get((int) finalHolder.mRlItemView.getTag());
                    Bundle bundle = new Bundle();
                    bundle.putString("shopId",String.valueOf(listBean.getShop_id()));
                    bundle.putString("shopName",listBean.getName());
                    EventBus.getDefault().post(new PostResult("2ShopDetailActivity",bundle));
                }
            });
            return convertView;
        }

        private void addData2View(int position, ViewHolder holder) {
            final HomePageData.ShopListBean listBean = mBeans.get(position);
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
            /*holder.mRTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("shopId",String.valueOf(listBean.getShop_id()));
                    bundle.putString("shopName",listBean.getName());
                    EventBus.getDefault().post(new PostResult("2ShopDetailActivity",bundle));
                }
            });*/
        }

        class ViewHolder {
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
            private HomeGuessAdapter mAdapter;
            private RelativeLayout mRTitle;
            private LinearLayout mLlLayoutItem;
        }
    }
}
