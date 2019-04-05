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
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.idyoga.yoga.R;
import com.idyoga.yoga.common.yogaweb.AgentWeb;
import com.idyoga.yoga.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import vip.devkit.library.Logcat;

/**
 * 文 件 名: YogaGymDetailsActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/10
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class YogaGymClassifyActivity extends BaseWebActivity {

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
        mTvTitleText.setText("瑜伽馆分类");
        mTvTitleRight.setText("发送数据");
        mTvTitleRight.setVisibility(View.VISIBLE);
        mBridgeWebView = new BridgeWebView(mContext);
        mAgentWeb = AgentWeb.with(this)//
                .setAgentWebParent(getAgentWebParent(), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))//
                .closeIndicator()
                .setAgentWebWebSettings(getAgentWebSettings())
                .setWebViewClient(new BridgeWebViewClient(mBridgeWebView))
                .setWebView(mBridgeWebView)
                .setWebChromeClient(getWebChromeClient())
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setMainFrameErrorView(mErrorView)
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
                mBridgeWebView.callHandler("functionInJs", "java to web ", new CallBackFunction() {
                    @Override
                    public void onCallBack(String data) {
                        Logcat.i("发送数据给HTML回调:" + data);
                        ToastUtil.showToast("发送数据给HTML回调:" + data);
                    }
                });
                break;
        }
    }
}