package com.idyoga.yoga.activity.web;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.devkit.library.Base64;
import vip.devkit.library.encrypt.EncryptUtils;

public class WebActivity extends BaseActivity {

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
    @BindView(R.id.wv_view)
    WebView mWvView;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).init();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.view_web;
    }

    @Override
    protected void initView() {
        String formData=Base64.encode("1".getBytes());
        mWvView.loadUrl(getUrl());
        mWvView.postUrl("",formData.getBytes());
        WebSettings webSettings = mWvView.getSettings();
//        webSettings.setDefaultFontSize(10);        // 默认文字尺寸，默认值16，取值范围1-72
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWvView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    protected void setListener() {

    }

    public String getUrl() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String getUrl = bundle.getString("getUrl");
            if (getUrl != null && getUrl().contains(".jpg")) {
                return "http://www.hq-xl.com/";
            } else {
                return getUrl;
            }
        }
        return "http://www.hq-xl.com/";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @OnClick(R.id.ll_title_back)
    public void onViewClicked() {
        finish();
    }
}
