package com.idyoga.yoga.activity.web;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.idyoga.yoga.R;
import com.idyoga.yoga.common.yogaweb.AgentWeb;
import com.idyoga.yoga.model.YogaMenuBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.DialogUtil;
import com.idyoga.yoga.utils.ShareUtil;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.view.YogaContextMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import vip.devkit.library.Logcat;
import vip.devkit.library.NetUtils;
import vip.devkit.library.RegUtil;
import vip.devkit.library.StringUtil;

public class YogaWebActivity extends BaseWebActivity {
    protected AgentWeb mAgentWeb;
    @BindView(R.id.container)
    LinearLayout mLlLayout;
    @BindView(R.id.ll_web_back)
    LinearLayout mLlWebBack;
    @BindView(R.id.ll_web_close)
    LinearLayout mLlWebClose;
    @BindView(R.id.tv_web_title)
    TextView mTvWebTitle;
    @BindView(R.id.ll_web_more)
    LinearLayout mLlWebMore;
    @BindView(R.id.ll_common_layout)
    LinearLayout mLlCommonLayout;
    private AlertDialog mAlertDialog;
    protected View mErrorView;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).flymeOSStatusBarFontColor("#333333").statusBarDarkFont(true).init();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected int setLayoutId() {
        return R.layout.home_activity_web;
    }

    @NonNull
    @Override
    protected ViewGroup getAgentWebParent() {
        return mLlLayout;
    }

    @Override
    protected void initView() {
        mTvWebTitle.setText("瑜伽学院");
        mErrorView = View.inflate(this, R.layout.yoga_layout_net_error, null);
        mBridgeWebView = new BridgeWebView(this);
        mAgentWeb = AgentWeb.with(this)//
                .setAgentWebParent(getAgentWebParent(), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))//
                .closeIndicator()
                .setAgentWebWebSettings(getAgentWebSettings())
                .setWebViewClient(getWebViewClient())
                .setWebChromeClient(getWebChromeClient())
                .createAgentWeb()//
                .ready()//
                .go(getUrl());
        long n = System.currentTimeMillis();
    }


    public String getUrl() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String url = bundle.getString("getUrl");
//             url="https://blog.csdn.net";
            if (!CommonUtils.isBlank(url)) {
                String regex = "^(?=^.{3,255}$)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$";
                if (RegUtil.checkURL(url)&&Pattern.matches(regex, url)) {
                        url = "http://" + url;
                }
                Logcat.i("url：" + url + "/" + RegUtil.checkURL(url)+"/"+Pattern.matches(regex, url));
                return url;
            } else {
                return "http://www.hq-xl.com";
            }
        } else {
            return "http://www.hq-xl.com";
        }
    }


    @Nullable
    @Override
    public WebChromeClient getWebChromeClient() {
        return new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                String getTitle = title;
                if (mTvWebTitle != null && !StringUtil.isEmpty(getTitle)) {
                    if (getTitle.length() > 12) {
                        mTvWebTitle.setText(getTitle.substring(0, 12).concat("..."));
                    } else {
                        mTvWebTitle.setText(title);
                    }
                }
            }
        };
    }

    @Nullable
    @Override
    public WebViewClient getWebViewClient() {
        return new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Logcat.i("shouldOverrideUrlLoading：" + checkURL("http://baidu.com") + "/"
                        + RegUtil.checkURL("https://baidu.com") + "/" + checkURL("http://dev.baidu.com"));
                Logcat.i("shouldOverrideUrlLoading：" + checkURL(url) + "/" + url);
                if (RegUtil.checkURL(url)) {
                    view.loadUrl(url);
                }
                return true;
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (RegUtil.checkURL(request.getUrl().toString())) {
                    view.loadUrl(request.getUrl().toString());
                }
                return true;
            }
        };
    }


    private void showDialog() {
        if (mAlertDialog == null) {
            mAlertDialog = new AlertDialog.Builder(this)
                    .setMessage("您确定要关闭该页面吗?")
                    .setNegativeButton("再逛逛", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (mAlertDialog != null) {
                                mAlertDialog.dismiss();
                            }
                        }
                    })//
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (mAlertDialog != null) {
                                mAlertDialog.dismiss();
                            }
                            YogaWebActivity.this.finish();
                        }
                    }).create();
        }
        mAlertDialog.show();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("Info", "onResult:" + requestCode + " onResult:" + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.ll_web_back, R.id.ll_web_close, R.id.ll_web_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_web_back:
                if (!mAgentWeb.back()) {
                    this.finish();
                }
                break;
            case R.id.ll_web_close:
                finish();
                break;
            case R.id.ll_web_more:
                final List<YogaMenuBean> menuItems = new ArrayList<>();
                menuItems.add(new YogaMenuBean(R.drawable.icon_refresh, "刷新"));
                menuItems.add(new YogaMenuBean(R.drawable.icon_copy, "复制链接"));
                menuItems.add(new YogaMenuBean(R.drawable.icon_browser, "其它浏览器打开"));
                menuItems.add(new YogaMenuBean(R.drawable.ic_bar_blank_share, "分享"));
                YogaContextMenu contextMenu = new YogaContextMenu(this)
                        .addMenuList(menuItems)
                        .dimBackground(true)
                        .setOnItemSelectListener(new YogaContextMenu.OnItemSelectListener() {
                            @Override
                            public void onItemSelect(int position) {
                                Logcat.i("点击了菜单：" + menuItems.get(position));
                                menuItemClicked(menuItems.get(position));
                            }
                        });
                contextMenu.showMenu(mLlWebMore);
                break;
        }
    }

    private void menuItemClicked(YogaMenuBean yogaMenuBean) {
        switch (yogaMenuBean.getText()) {
            case "刷新":
                if (mAgentWeb != null) {
                    mAgentWeb.getUrlLoader().reload(); // 刷新
                }
                break;
            case "复制链接":
                if (mAgentWeb != null) {
                    toCopy(this, mAgentWeb.getWebCreator().getWebView().getUrl());
                    ToastUtil.showToast("复制成功");
                }
                break;
            case "其它浏览器打开":
                if (mAgentWeb != null) {
                    openBrowser(mAgentWeb.getWebCreator().getWebView().getUrl());
                }
                break;
            case "分享":
                ShareUtil.with(this)
                        .setShareTitle("爱的瑜伽")
                        .setShareDescription("")
                        .setShareUrl(mAgentWeb.getWebCreator().getWebView().getUrl())
                        .build()
                        .share();
                break;
        }
    }

    /**
     * 打开浏览器
     *
     * @param targetUrl 外部浏览器打开的地址
     */
    private void openBrowser(String targetUrl) {
        if (TextUtils.isEmpty(targetUrl) || targetUrl.startsWith("file://")) {
            ToastUtil.showToast("该链接无法使用浏览器打开");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri mUri = Uri.parse(targetUrl);
        intent.setData(mUri);
        startActivity(intent);
    }

    /**
     * 复制字符串
     *
     * @param context
     * @param text
     */
    private void toCopy(Context context, String text) {
        ClipboardManager mClipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        mClipboardManager.setPrimaryClip(ClipData.newPlainText(null, text));
    }

    //(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]

    /**
     * 验证URL地址
     *
     * @param url 格式：http://blog.csdn.net:80/xyang81/article/details/7705960? 或 http://www.csdn.net:80
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkURL(String url) {
        String regex = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]w";
        return Pattern.matches(regex, url);
    }

    @Override
    protected void setListener() {
        super.setListener();
        if (mErrorView != null) {
            mErrorView.findViewById(R.id.tv_retry).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (NetUtils.isConnected(YogaWebActivity.this)) {
                        onRetry();
                    } else {
                        DialogUtil.wrap(YogaWebActivity.this)
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

    /**
     * 刷新
     */
    protected void onRetry() {
        if (mAgentWeb != null) {
            mAgentWeb.getUrlLoader().reload();
        }
    }
}
