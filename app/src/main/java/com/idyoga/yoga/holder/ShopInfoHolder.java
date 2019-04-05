package com.idyoga.yoga.holder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.ShopImaAdapter;
import com.idyoga.yoga.model.ShopDetailDataBean;

import java.util.ArrayList;

/**
 * @author weilgu
 * @time 2019/3/12 17:47
 * @des ${TODO}
 */

public class ShopInfoHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private TextView tv_shop_name;
    private ImageView iv_v;
    private RecyclerView rlv_ima;
    private TextView rv_business_time;
    private TextView tv_acreage;
    private TextView rv_address;
    private LinearLayout mMoreShopInfo;
    private RelativeLayout mSimpleShopInfo;
    private ImageView mShopImage;
    private TextView mShopNames;
    private TextView mIamgeAddress;
    private ShopImaAdapter mAdapter;


    public ShopInfoHolder(View itemView) {
        this(itemView,null);
    }

    public ShopInfoHolder(View itemView, Context context) {
        super(itemView);
        this.mContext = context;
        tv_shop_name = itemView.findViewById(R.id.tv_shop_name);
        rv_business_time = itemView.findViewById(R.id.rv_business_time);
        tv_acreage = itemView.findViewById(R.id.tv_acreage);
        rv_address = itemView.findViewById(R.id.rv_address);
        iv_v = itemView.findViewById(R.id.iv_v);
        rlv_ima = itemView.findViewById(R.id.rlv_ima);
        mMoreShopInfo = itemView.findViewById(R.id.ll_more_shop_info);

        mSimpleShopInfo = itemView.findViewById(R.id.rl_simple_shop_info);
        mShopImage = itemView.findViewById(R.id.iv_shop_image);
        mShopNames = itemView.findViewById(R.id.tv_shop_names);
        mIamgeAddress = itemView.findViewById(R.id.tv_iamge_address);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mAdapter = new ShopImaAdapter(mContext);
        rlv_ima.setLayoutManager(layoutManager);
        rlv_ima.setAdapter(mAdapter);
    }

    public void bindView(ShopDetailDataBean bean){
        ArrayList<String> images = new ArrayList<>();
        String lessonImage = bean.getLessonImage();
        String detailImage = bean.getDetail_image();
        String studentImage = bean.getStudentImage();
        if (detailImage == null && studentImage == null){ //只显示场馆图
            images.add(lessonImage);
        }else{ //显示场馆图,课程图,学员风采
            images.add(lessonImage);
            images.add(detailImage);
            images.add(studentImage);
        }
        if (images.size() == 1){
            mMoreShopInfo.setVisibility(View.GONE);
            mSimpleShopInfo.setVisibility(View.VISIBLE);
            Glide.with(mContext)
                    .load(lessonImage)
                    .placeholder(R.drawable.img_course)
                    .error(R.drawable.img_course)
                    .into(mShopImage);
            mShopNames.setText(bean.getName() != null ? bean.getName() : "");
            mIamgeAddress.setText(bean.getAddress() != null ? bean.getAddress() : "");
        }else{
            mMoreShopInfo.setVisibility(View.VISIBLE);
            mSimpleShopInfo.setVisibility(View.GONE);
            tv_shop_name.setText(bean.getName() != null ? bean.getName() : "");
            rv_address.setText(bean.getAddress() != null ? bean.getAddress() : "");
            Object business_hours = bean.getBusiness_hours();
            rv_business_time.setText(business_hours != null ? "营业时间: " + business_hours.toString() : "营业时间: ");
            String floor_area = bean.getFloor_area();
            int capacity = bean.getCapacity();
            tv_acreage.setText(floor_area != null ? "面积: " + floor_area + " (可容纳" +capacity + "人)": "面积: 未知");
            mAdapter.setShopId(bean.getId());
            mAdapter.setDatas(images);
        }
    }
}
