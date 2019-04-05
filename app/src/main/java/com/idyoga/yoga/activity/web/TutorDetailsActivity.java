package com.idyoga.yoga.activity.web;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
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

/**
 * 文 件 名: TutorDetailsActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/10
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class TutorDetailsActivity extends BaseWebActivity {

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
        showLoading("加载中...");
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
        Logcat.i("导师详情");
        mTvTitleText.setText("导师详情");
        mBridgeWebView = new BridgeWebView(this);
        mAgentWeb = AgentWeb.with(this)//
                .setAgentWebParent(getAgentWebParent(), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))//
                .closeIndicator()
                .setAgentWebWebSettings(getAgentWebSettings())
                .setWebViewClient(new BridgeWebViewClient(mBridgeWebView))
                .setWebView(mBridgeWebView)
                .setWebChromeClient(mWebChromeClient)
                .setSecurityType(AgentWeb.SecurityType.DEFAULT_CHECK)
                .setMainFrameErrorView(mErrorView)
                .createAgentWeb()//
                .ready()//
                .go(getUrl);
        registerJsBridge(this, mBridgeWebView);
    }


    public WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            Logcat.i("onProgressChanged:" + newProgress + "  view:" + view);
            if (newProgress > 70) {
                dismissLoading();
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            Logcat.i("onReceivedTitle:" + title + "  view:" + view);
            Logcat.i("TITLE:" + title);
            if (mTvTitleText != null && !TextUtils.isEmpty(title)) {
                if (title.length() > 8) {
                    title = title.substring(0, 8).concat("...");
                }
                mTvTitleText.setText(title+"");
            }
        }
    };

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