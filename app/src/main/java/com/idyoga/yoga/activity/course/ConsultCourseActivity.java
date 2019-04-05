package com.idyoga.yoga.activity.course;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.HomeFrPagerAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.fragment.child.FragmentConsult;
import com.idyoga.yoga.view.YogaLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import vip.devkit.library.Logcat;
import vip.devkit.view.common.tab.AdvancedPagerSlidingTabStrip;

/**
 * 文 件 名: ConsultCourseActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/26
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ConsultCourseActivity extends BaseActivity {
    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.tabs)
    AdvancedPagerSlidingTabStrip mTabs;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;
    private String tag = "0";
    private String UserId, Token, ShopId;
    private List<String> mTabList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).flymeOSStatusBarFontColor("#333333").statusBarDarkFont(true).init();
    }

    @Override
    protected void initData() {
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            tag = mBundle.getString("tag");
        }
        Logcat.i("tag:"+tag+"");
    }
    @Override
    protected int setLayoutId() {
        return R.layout.user_activity_course;
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(mVpContent);
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("我的咨询");
        mFragments.clear();
        mTabList.clear();
        mTabList.add("待上课");
        mTabList.add("咨询中");
        mTabList.add("已关闭");
        mFragments.add(FragmentConsult.getInstance("2"));
        mFragments.add(FragmentConsult.getInstance("1"));
        mFragments.add(FragmentConsult.getInstance("3"));
        mVpContent.setAdapter(new HomeFrPagerAdapter(getSupportFragmentManager(), mTabList, mFragments));
        mVpContent.setOffscreenPageLimit(3);
        if (null!=tag&&tag.equals("0")) {
            mVpContent.setCurrentItem(0);
        } else if (null!=tag&&tag.equals("1")){
            mVpContent.setCurrentItem(1);
        }else if (null!=tag&&tag.equals("2")){
            mVpContent.setCurrentItem(2);
        }
        mTabs.setViewPager(mVpContent);

    }

    @Override
    protected void setListener() {
        mVpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mTabs.setSelectItem(position);
            }

            @Override
            public void onPageSelected(int position) {
                mTabs.setSelectItem(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


    }


    @OnClick(R.id.ll_title_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
    }

}