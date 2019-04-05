package com.idyoga.yoga.activity.news;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 文 件 名: NewsDetailsActivity
 * 创 建 人: By k
 * 创建日期: 2018/5/11 15:43
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class NewsDetailsActivity extends BaseActivity {
    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R.id.ll_title_right)
    LinearLayout mLlTitleRight;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).flymeOSStatusBarFontColor("#333333").init();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_news_details;
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("系统消息");
    }

    @Override
    protected void setListener() {

    }


    @OnClick({R.id.ll_title_back, R.id.tv_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.tv_title_right:
                break;
        }
    }
}