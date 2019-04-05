package com.idyoga.yoga.activity.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.HomeFrPagerAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.fragment.child.FragmentExperienceC;
import com.idyoga.yoga.fragment.child.FragmentHomeRecommend;
import com.idyoga.yoga.fragment.child.FragmentShop;
import com.idyoga.yoga.fragment.child.FragmentTutor;
import com.idyoga.yoga.fragment.shop.CourseImageFragment;
import com.idyoga.yoga.fragment.shop.ShopImageFragment;
import com.idyoga.yoga.fragment.shop.StudentImageFragment;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.view.YogaLayoutManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.view.common.tab.AdvancedPagerSlidingTabStrip;

/**
 * @author weilgu
 * @time 2019/3/15 17:27
 * @des 馆, 学员, 课程 照片集
 */

public class ShopImagesActivity extends BaseActivity {

    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.home_tabs)
    AdvancedPagerSlidingTabStrip mHomeTabs;
    @BindView(R.id.home_vp_content)
    ViewPager mHomeVpContent;
    private List<String> mTabList = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String mShopId;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).init();
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null){
            mShopId = extras.getString("shopId");
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_shop_images;
    }

    @Override
    protected void initView() {
        mTabList.add("场馆图");
        mTabList.add("课程图");
        mTabList.add("学员风采");
        ShopImageFragment shopImageFragment = new ShopImageFragment();
        shopImageFragment.setShopId(mShopId);
        CourseImageFragment courseImageFragment = new CourseImageFragment();
        courseImageFragment.setShopId(mShopId);
        StudentImageFragment studentImageFragment = new StudentImageFragment();
        studentImageFragment.setShopId(mShopId);
        fragments.add(shopImageFragment);
        fragments.add(courseImageFragment);
        fragments.add(studentImageFragment);
        mHomeVpContent.setAdapter(new HomeFrPagerAdapter(getSupportFragmentManager(), mTabList, fragments));
        mHomeTabs.setViewPager(mHomeVpContent);
    }

    @Override
    protected void setListener() {

    }

    @OnClick(R.id.ll_title_back)
    public void onViewClicked() {
        finish();
    }

}
