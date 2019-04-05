package com.idyoga.yoga.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.common.yogaweb.AbsAgentWebSettings;
import com.idyoga.yoga.common.yogaweb.AgentWeb;
import com.idyoga.yoga.common.yogaweb.AgentWebUIControllerImplBase;
import com.idyoga.yoga.common.yogaweb.IAgentWebSettings;
import com.idyoga.yoga.common.yogaweb.IWebLayout;
import com.idyoga.yoga.common.yogaweb.MiddlewareWebChromeBase;
import com.idyoga.yoga.common.yogaweb.MiddlewareWebClientBase;
import com.idyoga.yoga.common.yogaweb.PermissionInterceptor;
import com.idyoga.yoga.lbs.YogaLocationListener;
import com.idyoga.yoga.utils.DialogUtil;
import com.idyoga.yoga.utils.UserAgentUtil;
import com.idyoga.yoga.web.route.WebRoute;

import vip.devkit.library.Logcat;
import vip.devkit.library.NetUtils;

/**
 * 文 件 名: BaseWebFragment
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/9
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public abstract class BaseWebFragment extends BaseFragment {

    protected BridgeWebView mBridgeWebView;
    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;
    private boolean isPrepared;
    protected AgentWeb mAgentWeb;
    private MiddlewareWebChromeBase mMiddleWareWebChrome;
    private MiddlewareWebClientBase mMiddleWareWebClient;
    private ErrorLayoutEntity mErrorLayoutEntity;
    private AgentWebUIControllerImplBase mAgentWebUIController;
    protected LocationClient mLocationClient = null;
    protected YogaLocationListener mLocationListener = new YogaLocationListener();

    protected View mErrorView;
    @Override
    protected void initData() {
        super.initData();
        mLocationClient = new LocationClient(mActivity);

        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//可选，设置定位模式，默认高精度
//LocationMode.Hight_Accuracy：高精度；
//LocationMode. Battery_Saving：低功耗；
//LocationMode. Device_Sensors：仅使用设备；

        option.setCoorType("bd09ll");
//可选，设置返回经纬度坐标类型，默认gcj02
//gcj02：国测局坐标；
//bd09ll：百度经纬度坐标；
//bd09：百度墨卡托坐标；
//海外地区定位，无需设置坐标类型，统一返回wgs84类型坐标

        option.setScanSpan(1000);
//可选，设置发起定位请求的间隔，int类型，单位ms
//如果设置为0，则代表单次定位，即仅定位一次，默认为0
//如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
//可选，设置是否使用gps，默认false
//使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
//可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
//可选，定位SDK内部是一个service，并放到了独立进程。
//设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
//可选，设置是否收集Crash信息，默认收集，即参数为false

        option.setWifiCacheTimeOut(5 * 60 * 1000);
//可选，7.2版本新增能力
//如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位

        option.setEnableSimulateGps(false);
//可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(mLocationListener);
        dbStart();
        dbStop();
    }

    public void dbStart() {
        if (mLocationClient != null) {
            mLocationClient.registerLocationListener(mLocationListener);
            mLocationClient.start();
        }
    }

    public void dbStop() {
        if (mLocationClient != null) {
            mLocationClient.stop();
        }
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ErrorLayoutEntity mErrorLayoutEntity = getErrorLayoutEntity();
//        mBridgeWebView = new BridgeWebView(mActivity);
//        mAgentWeb = AgentWeb.with(this)
//                .setAgentWebParent(getAgentWebParent(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
//                .useDefaultIndicator(getIndicatorColor(), getIndicatorHeight())
//                .setWebViewClient(new BridgeWebViewClient(mBridgeWebView))
//                .setWebView(mBridgeWebView)
//                .setWebLayout(getWebLayout())
//                .setAgentWebWebSettings(getAgentWebSettings())
//                .setPermissionInterceptor(getPermissionInterceptor())
//                .setWebChromeClient(getWebChromeClient())
//                .interceptUnkownUrl()
//                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
//                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
//                .setAgentWebUIController(getAgentWebUIController())
//                .setMainFrameErrorView(mErrorLayoutEntity.layoutRes, mErrorLayoutEntity.reloadId)
//                .useMiddlewareWebChrome(getMiddleWareWebChrome())
//                .useMiddlewareWebClient(getMiddleWareWebClient())
//                .createAgentWeb()//
//                .ready()//
//                .go(getUrl());
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_web;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mErrorView = View.inflate(mActivity,R.layout.yoga_layout_net_error,null);
    }

    @Override
    protected void setListener() {
        super.setListener();
        if (mErrorView!=null){
            mErrorView.findViewById(R.id.tv_retry).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (NetUtils.isConnected(mActivity)) {
                        onRetry();
                    } else {
                        DialogUtil.wrap(mActivity)
                                .setData("设置网络", "是否去设置网络")
                                .setActionClickListener(new DialogUtil.onActionClickListener() {
                                    @Override
                                    public void action(int viewType, Dialog dialog, View view) {
                                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
                                        startActivity(intent);
                                        dialog.dismiss();
                                    }
                                })
                                .init()
                                .show();
                    }
                }
            });
        }
    }

    protected void registerJsBridge(final Context context, BridgeWebView bridgeWebView) {
        if (bridgeWebView == null) return;
        bridgeWebView.send("test");
        bridgeWebView.registerHandler("getOS", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Logcat.i("接收的数据：" + data);
                WebRoute.init(context).goToNext(data);
            }
        });
        bridgeWebView.setDefaultHandler(new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Logcat.i("接收的数据：" + data);
            }
        });

        bridgeWebView.callHandler("jsbridge_getJsMessage", "JSON", new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                Logcat.i("发送的数据：" + data);
            }
        });
    }


    protected void setTitle(WebView view, String title) {

    }

    protected void onRetry(){
        if (mAgentWeb!=null){
            mAgentWeb.getUrlLoader().reload();
        }
    }

    protected @NonNull
    ErrorLayoutEntity getErrorLayoutEntity() {
        if (this.mErrorLayoutEntity == null) {
            this.mErrorLayoutEntity = new ErrorLayoutEntity();
        }
        return mErrorLayoutEntity;
    }

    protected @Nullable
    AgentWebUIControllerImplBase getAgentWebUIController() {
        return mAgentWebUIController;
    }

    protected static class ErrorLayoutEntity {
        private int layoutRes = R.layout.yogaweb_error_page;
        private int reloadId;

        public void setLayoutRes(int layoutRes) {
            this.layoutRes = layoutRes;
            if (layoutRes <= 0) {
                layoutRes = -1;
            }
        }

        public void setReloadId(int reloadId) {
            this.reloadId = reloadId;
            if (reloadId <= 0) {
                reloadId = -1;
            }
        }
    }

    @Override
    public void onPause() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onPause();
        }
        super.onPause();

    }

    @Override
    public void onResume() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onResume();
        }
        super.onResume();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    protected @Nullable
    String getUrl() {
        return "";
    }

    @Override
    public void onDestroy() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onDestroy();
        }
        super.onDestroy();
    }

    protected @Nullable
    IAgentWebSettings getAgentWebSettings() {
        return new AbsAgentWebSettings() {
            @Override
            protected void bindAgentWebSupport(AgentWeb agentWeb) {
            }

            @Override
            public IAgentWebSettings toSetting(WebView webView) {
                WebSettings settings = webView.getSettings();
                settings.setUserAgentString(settings.getUserAgentString() + " " + UserAgentUtil.init(mActivity).getUserAgent().toString());
                return this;
            }

        };
    }

    ;


    protected @Nullable
    BridgeWebViewClient getBridgeWebViewClient(final BridgeWebView mBridgeWebView) {
        return new BridgeWebViewClient(mBridgeWebView) {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                super.shouldOverrideUrlLoading(view, url);
                return true;
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                super.shouldOverrideUrlLoading(view, request);
                return true;
            }
        };
    }

    protected @Nullable
    DownloadListener getDownloadListener() {
        return null;
    }

    protected @Nullable
    WebChromeClient getWebChromeClient() {
        return new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress > 70) {
                    dismissLoading();
                }
            }
        };
    }

    protected abstract @NonNull
    ViewGroup getAgentWebParent();

    protected @ColorInt
    int getIndicatorColor() {
        return -1;
    }

    protected int getIndicatorHeight() {
        return -1;
    }

    protected @Nullable
    WebViewClient getWebViewClient() {
        return null;
    }

    protected @Nullable
    WebView getWebView() {
        return null;
    }

    protected @Nullable
    IWebLayout getWebLayout() {
        return null;
    }

    protected @Nullable
    PermissionInterceptor getPermissionInterceptor() {
        return null;
    }

    protected @NonNull
    MiddlewareWebChromeBase getMiddleWareWebChrome() {
        return this.mMiddleWareWebChrome = new MiddlewareWebChromeBase() {
        };
    }

    protected @NonNull
    MiddlewareWebClientBase getMiddleWareWebClient() {
        return this.mMiddleWareWebClient = new MiddlewareWebClientBase() {
        };
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        lazyLoad();
    }

    /**
     * 延迟加载    子类必须重写此方法
     */

    protected abstract void lazyLoad();


}