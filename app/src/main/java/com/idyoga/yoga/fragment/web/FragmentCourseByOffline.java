package com.idyoga.yoga.fragment.web;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.idyoga.yoga.R;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.common.yogaweb.AgentWeb;
import com.idyoga.yoga.common.yogaweb.IAgentWebSettings;
import com.idyoga.yoga.fragment.BaseWebFragment;
import com.idyoga.yoga.model.address.AddressBean;
import com.idyoga.yoga.utils.UserAgentUtil;

import butterknife.BindView;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * 文 件 名: FragmentCourseByVideo
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/9
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class FragmentCourseByOffline extends BaseWebFragment {

    @BindView(R.id.ll_web_layout)
    LinearLayout mLlWebLayout;

    private String shopId = "19287727";
    private String cityId = "";

    @Override
    protected void initData() {
        super.initData();
        Logcat.i("Item:" + getUserVisibleHint());
        cityId = (String) SharedPreferencesUtils.getSP(mActivity,"cityId","");
        shopId = (String) SharedPreferencesUtils.getSP(mActivity, "shopId", "");
    }

    @NonNull
    @Override
    protected ViewGroup getAgentWebParent() {
        return mLlWebLayout;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_web;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        if (getUserVisibleHint()) {
//            showLoading("加载中...", true);
            initWebView(view);
        }
    }

    private void initWebView(View view) {
//        shopId = (String) SharedPreferencesUtils.getSP(mActivity, "ShopId", "");
        BridgeWebView mBridgeWebView = new BridgeWebView(AppContext.getInstance());
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mLlWebLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))//
                .closeIndicator()
                .setAgentWebWebSettings(getAgentWebSettings())
                .setWebViewClient(new BridgeWebViewClient(mBridgeWebView))
                .setWebView(mBridgeWebView)
                .setWebChromeClient(getWebChromeClient())
                .setSecurityType(AgentWeb.SecurityType.DEFAULT_CHECK)
                .createAgentWeb()//
                .ready()//
                .go(ApiConstants.Html5Urls.HOME_YOGA_OFFLINE + "?cityId=" + cityId);
        registerJsBridge(mActivity, mBridgeWebView);
        Logcat.i("URL:"+ApiConstants.Html5Urls.HOME_YOGA_OFFLINE + "?cityId=" + cityId);
        Logcat.i("浏览器 UserAgent ：" + mAgentWeb.getWebCreator().getWebView().getSettings().getUserAgentString());
    }


    @Nullable
    @Override
    protected IAgentWebSettings getAgentWebSettings() {
        return new IAgentWebSettings() {
            @Override
            public IAgentWebSettings toSetting(WebView webView) {
                WebSettings settings = webView.getSettings();
                settings.setUserAgentString(UserAgentUtil.init(mActivity).getUserAgent().toString());
                return this;
            }

            @Override
            public WebSettings getWebSettings() {
                return null;
            }

        };
    }


    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        if (postResult.getTag().equals("address")) {
            AddressBean.CityBean bean = (AddressBean.CityBean) postResult.getResult();
            if (getUserVisibleHint()) {
                cityId = bean.getId() + "";
                String url =ApiConstants.Html5Urls.HOME_YOGA_OFFLINE + "?cityId=" +cityId;
                mAgentWeb.getUrlLoader().loadUrl(url);
            }
        }
    }

}