/*
******************************* Copyright (c)*********************************\
**
**                 (c) Copyright 2017, DevKit.vip, china, qd. sd
**                          All Rights Reserved
**
**                           By(K)
********************************End of Head************************************\
*/
package com.idyoga.yoga.activity.user;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.LoginFragmentAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.fragment.login.AccountNumLogFragment;
import com.idyoga.yoga.fragment.login.PhoneLoginFragment;
import com.idyoga.yoga.model.UserInfoBean;
import com.idyoga.yoga.utils.UserUtil;
import com.idyoga.yoga.utils.ViewUtil;
import com.idyoga.yoga.view.CircleImageView;
import com.idyoga.yoga.view.TableVIew;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * 文 件 名: LoginActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/1/24
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.ll_lg_bk)
    LinearLayout llLgBk;
    @BindView(R.id.ll_lg_rg)
    RelativeLayout llLgRg;
    @BindView(R.id.home_tabs)
    TableVIew homeTabs;
    @BindView(R.id.home_vp_content)
    ViewPager homeVpContent;
    @BindView(R.id.civ_head)
    CircleImageView civHead;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;

    private List<String> mTabList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String action;

    @Override
    protected void initBaseLayout() {
        super.initBaseLayout();
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(rlTitleBar).flymeOSStatusBarFontColor("#333333").init();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null){
            action = intent.getStringExtra("action");
            Logcat.e("*******LoginActivity********initData() action " + action);
        }
        mTabList.add("手机登陆");
        mTabList.add("密码登陆");
        PhoneLoginFragment phoneLoginFragment = new PhoneLoginFragment();
        AccountNumLogFragment accountNumLogFragment = new AccountNumLogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("action",action);
        phoneLoginFragment.setArguments(bundle);
        accountNumLogFragment.setArguments(bundle);
        mFragments.add(phoneLoginFragment);
        mFragments.add(accountNumLogFragment);
        homeVpContent.setAdapter(new LoginFragmentAdapter(getSupportFragmentManager(), mTabList, mFragments));
//        homeVpContent.setCurrentItem(0);
        homeTabs.setDicatorWidth(ViewUtil.dp2px(this, 120));
        homeTabs.setViewPager(homeVpContent);
//        UserInfoBean userBean = UserUtil.getUserBean(this);
        String headimgurl = (String) SharedPreferencesUtils.getSP(this, "headimgurl", "");
        String avatar_url = (String) SharedPreferencesUtils.getSP(this, "avatar_url", "");
        if (!TextUtils.isEmpty(headimgurl)) {
            Glide.with(this)
                    .load(headimgurl)
                    .placeholder(R.drawable.def_head)//加载成功前显示的图片
                    .error(R.drawable.def_head)//加载发生错误时显示的图片
                    .into(civHead);
        } else if(TextUtils.isEmpty(headimgurl) && !TextUtils.isEmpty(avatar_url)){
            Glide.with(this)
                    .load(avatar_url)
                    .placeholder(R.drawable.def_head)//加载成功前显示的图片
                    .error(R.drawable.def_head)//加载发生错误时显示的图片
                    .into(civHead);
        }else {
            Glide.with(this).load(R.drawable.def_head).into(civHead);
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mTimeUtils.StopTimer();
    }

    @OnClick({R.id.ll_lg_bk, R.id.ll_lg_rg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_lg_bk:
                finish();
                break;
            case R.id.ll_lg_rg:
                startActivityWithoutExtras(RegisterActivity.class);
                break;
        }
    }

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        if (postResult.getTag().equals("webShop")){
            Logcat.e("*******LoginActivity********onEvent() " + postResult.getTag());
        }
    }
}
