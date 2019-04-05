package com.idyoga.yoga.activity.web;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.idyoga.yoga.R;
import com.idyoga.yoga.common.yogaweb.AgentWeb;

import butterknife.BindView;
import butterknife.OnClick;
import vip.devkit.library.Logcat;

public class ClassifyChooseActivity extends BaseWebActivity {

    @BindView(R.id.ll_web_layout)
    RelativeLayout mLlWebLayout;
    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;


    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).flymeOSStatusBarFontColor("#333333").init();
    }

    @NonNull
    @Override
    protected ViewGroup getAgentWebParent() {
        return mLlWebLayout;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_web;
    }

    String getUrl;

    @Override
    protected void initData() {
        super.initData();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            getUrl = bundle.getString("getUrl");
            Logcat.e("getUrl:" + getUrl);
        } else {
            Logcat.e("参数为空");
        }
    }

    @Override
    protected void initView() {
        super.initView();
        mTvTitleText.setText("分类筛选");
        mBridgeWebView = new BridgeWebView(mContext);
        mAgentWeb = AgentWeb.with(this)//
                .setAgentWebParent(getAgentWebParent(), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))//
                .closeIndicator()
                .setAgentWebWebSettings(getAgentWebSettings())
                .setWebViewClient(new BridgeWebViewClient(mBridgeWebView))
                .setWebView(mBridgeWebView)
                .setWebChromeClient(getWebChromeClient())
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .createAgentWeb()//
                .ready()//
                .go(getUrl);
        registerJsBridge(this, mBridgeWebView);
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
