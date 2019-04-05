package com.idyoga.yoga.fragment;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.idyoga.yoga.R;
import com.idyoga.yoga.common.modle.PostResult;

import butterknife.BindView;
import vip.devkit.library.Logcat;

/**
 * 文 件 名: WebFragment
 * 创 建 人: By k
 * 创建日期: 2018/5/8 17:32
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class WebFragment extends BaseWebFragment {

    @BindView(R.id.ll_web_layout)
    LinearLayout mLlWebLayout;

    @NonNull
    @Override
    protected ViewGroup getAgentWebParent() {
        return mLlWebLayout;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_web;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected void initData() {
        super.initData();
        Logcat.i("Item:"+getUserVisibleHint());
    }

    @Override
    public void onStart() {
        super.onStart();
        setUrl("http://baidu.com");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            setUrl("http://baidu.com");
        }
    }


    String setUrl;
    protected WebFragment setUrl(String url) {
        this.setUrl = url;
        return this;
    }

    @Nullable
    @Override
    protected String getUrl() {
        return setUrl;
    }

    @Nullable
    @Override
    protected WebViewClient getWebViewClient() {
        return new WebViewClient(){
            /**
             * @param view
             * @param url
             * @return 拦截url
             */
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Logcat.d("WebViewClient :", url);
                view.loadUrl(url);
                return true;
            }
        };
    }

    @Override
    protected void lazyLoad() {

    }

}