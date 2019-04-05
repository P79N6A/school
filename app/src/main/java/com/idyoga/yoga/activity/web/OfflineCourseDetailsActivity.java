package com.idyoga.yoga.activity.web;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.course.OfflineCourseBuyActivity;
import com.idyoga.yoga.common.yogaweb.AgentWeb;
import com.idyoga.yoga.utils.ShareUtil;

import butterknife.BindView;
import butterknife.OnClick;
import vip.devkit.library.Logcat;

/**
 * 文 件 名: OfflineCourseDetailsActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/10
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class OfflineCourseDetailsActivity extends BaseWebActivity {

    @BindView(R.id.ll_web_layout)
    LinearLayout mLlWebLayout;
    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.ll_title_right)
    LinearLayout mLlTitleRight;
    @BindView(R.id.iv_share)
    ImageView mIvShare;
    @BindView(R.id.tv_consult)
    ImageView mTvConsult;
    @BindView(R.id.tv_next)
    TextView mTvNext;
    @BindView(R.id.ll_foot_layout)
    RelativeLayout mLlFootLayout;


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
        return R.layout.activity_web_offilne_course_detail;
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
        mTvTitleText.setText("线下课程详情");
        mLlFootLayout.setVisibility(View.GONE);
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


    @OnClick({R.id.ll_title_back, R.id.iv_share, R.id.tv_consult, R.id.tv_next})
    public void onViewClicked(View view) {
        Bundle bundle;
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.iv_share:
                ShareUtil.with(this)
                        .setShareTitle("分享标题")
                        .setShareDescription("测试分享")
                        .setShareUrl("http://baidu.com")
                        .build()
                        .share();
                break;
            case R.id.tv_consult:
                bundle = new Bundle();
                bundle.putString("getUrl", "http://wxyoga.hq-xl.com/contact/index");
                startActivity(ConsultActivity.class, bundle);
                break;
            case R.id.tv_next:
                bundle = new Bundle();
                bundle.putString("getUrl", "http://wxyoga.hq-xl.com/contact/index");
                startActivity(OfflineCourseBuyActivity.class, bundle);
                break;
        }
    }
}
