package com.idyoga.yoga.activity.card;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.MemberPagerAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.fragment.EffectiveCardFragment;
import com.idyoga.yoga.fragment.NoEffectiveCardFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import vip.devkit.view.common.tab.AdvancedPagerSlidingTabStrip;

public class MembershipCardActivity extends BaseActivity {
    @BindView(R.id.tabs)
    AdvancedPagerSlidingTabStrip mTabs;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;
    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.fl_tabs)
    FrameLayout mFlTabs;
    private List<String> mTabList = new ArrayList<>();
    private ArrayList<Fragment> mFragList = new ArrayList<>();

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).statusBarDarkFont(true).init();
    }

    @Override
    protected void initBaseLayout() {
        super.initBaseLayout();
//        mLayoutManager.setEmpty();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.user_activity_vip_card;
    }

    @Override
    protected void initView() {
        mTabList.clear();
        mFragList.clear();
        mTvTitleText.setText("我的权益卡");
        mTabList.add("有效的");
        mTabList.add("无效的");

        EffectiveCardFragment effectiveCardFragment = new EffectiveCardFragment();
        NoEffectiveCardFragment noEffectiveCardFragment = new NoEffectiveCardFragment();
        mFragList.add(effectiveCardFragment);
        mFragList.add(noEffectiveCardFragment);
        MemberPagerAdapter memberPagerAdapter = new MemberPagerAdapter(getSupportFragmentManager(), mTabList, mFragList);
        mVpContent.setAdapter(memberPagerAdapter);
        mVpContent.setCurrentItem(0);
        mTabs.setViewPager(mVpContent);
    }

    @Override
    protected void setListener() {

    }

    @OnClick(R.id.ll_title_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10003 && data != null) {
            Intent intent = new Intent();
            setResult(11003, intent);
            finish();
        }
    }
}
