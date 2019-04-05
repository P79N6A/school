package com.idyoga.yoga.web;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;

/**
 * 文 件 名: YogaBridgeWebViewClient
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/10
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class YogaBridgeWebViewClient extends BridgeWebViewClient {



    public YogaBridgeWebViewClient(BridgeWebView webView) {
        super(webView);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }

}
