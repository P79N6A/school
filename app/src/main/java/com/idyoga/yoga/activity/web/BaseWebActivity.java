package com.idyoga.yoga.activity.web;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.common.yogaweb.AbsAgentWebSettings;
import com.idyoga.yoga.common.yogaweb.AgentWeb;
import com.idyoga.yoga.common.yogaweb.AgentWebUIControllerImplBase;
import com.idyoga.yoga.common.yogaweb.DefaultWebClient;
import com.idyoga.yoga.common.yogaweb.IAgentWebSettings;
import com.idyoga.yoga.common.yogaweb.IWebLayout;
import com.idyoga.yoga.common.yogaweb.MiddlewareWebChromeBase;
import com.idyoga.yoga.common.yogaweb.MiddlewareWebClientBase;
import com.idyoga.yoga.common.yogaweb.PermissionInterceptor;
import com.idyoga.yoga.utils.DialogUtil;
import com.idyoga.yoga.utils.UserAgentUtil;
import com.idyoga.yoga.web.route.WebRoute;

import vip.devkit.library.Logcat;
import vip.devkit.library.NetUtils;

/**
 * 文 件 名: BaseWebActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/10
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public abstract class BaseWebActivity extends BaseActivity {

    protected BridgeWebView mBridgeWebView;
    protected AgentWeb mAgentWeb;
    private AgentWebUIControllerImplBase mAgentWebUIController;
    private ErrorLayoutEntity mErrorLayoutEntity;
    private MiddlewareWebChromeBase mMiddleWareWebChrome;
    private MiddlewareWebClientBase mMiddleWareWebClient;
    protected View mErrorView;

    @Override
    protected void initData() {
    }

    @Override
    protected int setLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {
        mErrorView = View.inflate(this,R.layout.yoga_layout_net_error,null);
    }

    @Override
    protected void setListener() {
        if (mErrorView != null) {
            mErrorView.findViewById(R.id.tv_retry).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (NetUtils.isConnected(BaseWebActivity.this)) {
                        onRetry();
                    } else {
                        DialogUtil.wrap(BaseWebActivity.this)
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


    protected @NonNull
    ErrorLayoutEntity getErrorLayoutEntity() {
        if (this.mErrorLayoutEntity == null) {
            this.mErrorLayoutEntity = new ErrorLayoutEntity();
        }
        return mErrorLayoutEntity;
    }

    protected AgentWeb getAgentWeb() {
        return this.mAgentWeb;
    }


    protected static class ErrorLayoutEntity {
        private int layoutRes = R.layout.yogaweb_error_page;
        private int reloadId;

        public int getLayoutRes() {
            return layoutRes;
        }

        public int getReloadId() {
            return reloadId;
        }

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
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mAgentWeb != null && mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onPause();
        }
        super.onPause();

    }

    @Override
    protected void onResume() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onResume();
        }
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onDestroy();
        }
        super.onDestroy();
    }


    protected @Nullable
    DownloadListener getDownloadListener() {
        return null;
    }


    protected void setTitle(WebView view, String title) {

    }

    protected
    @Nullable
    String getUrl() {
        return null;
    }

    public @Nullable
    IAgentWebSettings getAgentWebSettings() {
        return new AbsAgentWebSettings() {
            @Override
            protected void bindAgentWebSupport(AgentWeb agentWeb) {
            }

            @Override
            public IAgentWebSettings toSetting(WebView webView) {
                WebSettings settings = webView.getSettings();
                settings.setUserAgentString(settings.getUserAgentString().concat(" " + UserAgentUtil.init(mContext).getUserAgent().toString()));
                return this;
            }
        };
    }

    protected abstract @NonNull
    ViewGroup getAgentWebParent();

    protected @Nullable
    WebChromeClient getWebChromeClient() {
        return new WebChromeClient() {
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
                super.onReceivedTitle(view, title);
                Logcat.i("TITLE:" + title);
            }
        };
    }

    protected @ColorInt
    int getIndicatorColor() {
        return -1;
    }

    protected int getIndicatorHeight() {
        return -1;
    }

    protected @Nullable
    WebViewClient getWebViewClient() {
        return new BridgeWebViewClient(mBridgeWebView) {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                super.shouldOverrideUrlLoading(view, request);
                return true;
            }
        };
    }


    protected @Nullable
    WebView getWebView() {
        return mBridgeWebView;
    }

    protected @Nullable
    IWebLayout getWebLayout() {
        return null;
    }

    protected @Nullable
    PermissionInterceptor getPermissionInterceptor() {
        return null;
    }

    public @Nullable
    AgentWebUIControllerImplBase getAgentWebUIController() {
        return null;
    }

    public @Nullable
    DefaultWebClient.OpenOtherPageWays getOpenOtherAppWay() {
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

    /**
     * 刷新
     */
    protected void onRetry() {
        if (mAgentWeb != null) {
            mAgentWeb.getUrlLoader().reload();
        }
    }
}
