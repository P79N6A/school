package com.idyoga.yoga.holder;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.home.AppointmentIntroductionActivity;
import com.idyoga.yoga.activity.shop.ShopDetailActivity;
import com.idyoga.yoga.adapter.HomeFrPagerAdapter;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.fragment.course.CourseDetailFragment;
import com.idyoga.yoga.fragment.course.CourseTagFragment;
import com.idyoga.yoga.model.CoureseDetailInfo;
import com.idyoga.yoga.view.TableVIew;

import java.util.ArrayList;
import java.util.List;

import vip.devkit.view.common.banner.BannerV;
import vip.devkit.view.common.tab.AdvancedPagerSlidingTabStrip;

/**
 * @author weilgu
 * @time 2019/3/13 16:04
 * @des ${TODO}
 */

public class CourseInfoHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private BannerV mShopImages;
    private TextView mShopNames;
    private TextView mSopIntroduce;
    private TextView mIamgeAddress;
    private TableVIew home_tabs;
    private ViewPager home_vp_content;
    private TextView tv_course_time;
    private TextView tv_order_time;
    private TextView tv_require;
    private TextView tv_service;
    private TextView tv_num;


    public CourseInfoHolder(View itemView) {
        this(itemView,null);
    }

    public CourseInfoHolder(View itemView, Context context) {
        super(itemView);
        this.mContext = context;
        mShopImages = itemView.findViewById(R.id.iv_shop_images);
        mShopNames = itemView.findViewById(R.id.tv_shop_names);
        mSopIntroduce = itemView.findViewById(R.id.tv_shop_introduce);
        mIamgeAddress = itemView.findViewById(R.id.tv_iamge_address);
        home_tabs = itemView.findViewById(R.id.home_tabs);
        home_vp_content = itemView.findViewById(R.id.home_vp_content);
        tv_course_time = itemView.findViewById(R.id.tv_course_time);
        tv_order_time = itemView.findViewById(R.id.tv_order_time);
        tv_require = itemView.findViewById(R.id.tv_require);
        tv_service = itemView.findViewById(R.id.tv_service);
        tv_num = itemView.findViewById(R.id.tv_num);
    }

    public void bindView(CoureseDetailInfo infoBean){
        String name = infoBean.getName();
        mShopNames.setText(name + "");
        String introduce = infoBean.getIntroduce();
        mSopIntroduce.setText(introduce + "");
        int isCourse = infoBean.getIsCourse();
        mIamgeAddress.setText(isCourse == 1 ? "已排课" : "时间自选");
        Object appointmentNum = infoBean.getAppointmentNum();
        if (appointmentNum != null){
            tv_num.setVisibility(View.VISIBLE);
            tv_num.setText(appointmentNum.toString());
        }else{
            tv_num.setVisibility(View.GONE);
        }
        //-----------------------------------------
        List<CoureseDetailInfo.Label> labels = infoBean.getLabel();
        String content = infoBean.getContent();
        if (labels != null && content != null){
            ArrayList<String> tabs = new ArrayList<>();
            tabs.add("课程详情");
            tabs.add("功效介绍");
            ArrayList<Fragment> fragments = new ArrayList<>();
            CourseDetailFragment detailFragment = new CourseDetailFragment();
            CourseTagFragment tagFragment = new CourseTagFragment();
            detailFragment.setContent(content);
            tagFragment.setLabels(JSON.toJSONString(labels));
            fragments.add(detailFragment);
            fragments.add(tagFragment);
            home_vp_content.setAdapter(new HomeFrPagerAdapter(((AppointmentIntroductionActivity) mContext).getSupportFragmentManager(),tabs,fragments));
            home_vp_content.setCurrentItem(0);
            home_tabs.setViewPager(home_vp_content);
            home_vp_content.getCurrentItem();

        }
        //-----------------------------------------
        tv_course_time.setText(infoBean.getTime() + "");
//        tv_order_time.setText();
        tv_require.setText(infoBean.getSex_limit() == 1 ? "男女不限" : infoBean.getSex_limit() == 2 ? "仅限女性" : "仅限男性");
        tv_service.setText(infoBean.getVenueServiceItem() + "");
    }
}
