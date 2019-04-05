package com.idyoga.yoga.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebHistoryItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.baidu.mobstat.StatService;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.user.LoginActivity;
import com.idyoga.yoga.activity.web.YogaWebActivity;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.Constants;
import com.idyoga.yoga.comm.NetWorkConstant;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.common.yogaweb.AgentWeb;
import com.idyoga.yoga.common.yogaweb.DefaultWebClient;
import com.idyoga.yoga.common.yogaweb.IEventHandler;
import com.idyoga.yoga.common.yogaweb.WebCreator;
import com.idyoga.yoga.model.AdvertiBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.WxPayBean;
import com.idyoga.yoga.model.pay.WxPayAction;
import com.idyoga.yoga.utils.ShowAdverti;
import com.idyoga.yoga.utils.ToastUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Request;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * 作者 by K
 * 时间：on 2017/8/29 14:36
 * 邮箱 by  vip@devkit.vip
 * <p/>
 * 类用途：
 * 最后修改： 积分商城
 */
public class CreditFragment extends BaseFragment {
    //String mUrl = "http://player.alicdn.com/video/aliyunmedia.mp4";
    int flag = 0;
    @BindView(R.id.ll_web_root)
    LinearLayout llWebRoot;
    @BindView(R.id.ll_left_back)
    LinearLayout llLeftBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    Unbinder unbinder;
    @BindView(R.id.v_layout_head)
    View mVLayoutHead;
    @BindView(R.id.ll_left_close)
    LinearLayout mLlClose;

    Unbinder unbinder1;
    private AgentWeb bmAgentWeb;
    private static String mUrl = "";
    private AgentWeb.PreAgentWeb mReady;
    private int isLoginOrLoginOut = 0;//1为退出登陆 ,2为登陆
    private IWXAPI api;
    private static String mOldUrl = "";
    private static String mNewUrl = "";
    private String mUrl1;
    //    Unbinder unbinder;isImmersionBarEnabled

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mVLayoutHead).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_video;
    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        api = WXAPIFactory.createWXAPI(getActivity(), Constants.WX_APP_ID);
        api.registerApp(Constants.WX_APP_ID);
        Bundle arguments = getArguments();
        Logcat.i("----------------------------------- arguments = " + arguments);
        if (arguments != null){
            mUrl1 = arguments.getString("url");
        }else{
            mUrl1 = "https://integral.idyoga.cn/yoga_college_mall/index/index.html";
        }
        //        https://integral.idyoga.cn/yoga_college_mall/index/index
//        String url = "https://cmzb.idyoga.cn/yoga_college_mall/index/index";//测试环境
//        String url = "https://integral.idyoga.cn/yoga_college_mall/index/index";
        mOldUrl = mUrl1;
        getAdvertiInfo();
        getUrl(mUrl1);
        loadWebView(mUrl);
        Logcat.i("--------------------- if " + getUserVisibleHint() + "/" + isHidden());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Logcat.i(" isVisible:" + hidden);
        Logcat.i("=====onVisible==aa===" + isLoad + "/" + mUrl);
        if (hidden) {

        } else {
            if (isLoad) {
                getUrl(mUrl);
                bmAgentWeb.getUrlLoader().loadUrl(mUrl);
                mOldUrl = mUrl;
                Logcat.e("----------onHiddenChanged----------------");
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
        Logcat.i(" isVisible:" + isVisible);
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        Logcat.i("=====onVisible=====" + isLoad + "/" + mUrl);
        if (isLoad) {
            getUrl(mUrl);
            bmAgentWeb.getUrlLoader().loadUrl(mUrl);
            mOldUrl = mUrl;
        }
    }

    private void getUrl(String baseUrl) {
        Logcat.i("--------------------- baseUrl " + baseUrl);
        String mobile = (String) SharedPreferencesUtils.getSP(getContext(), "Mobile", "Mobile");
        int userId = (int) SharedPreferencesUtils.getSP(getContext(), "UserId", 0);
        if (userId != 0 && !"Mobile".equals(mobile)) {
            if (baseUrl.contains("type=0")) {
                String urlPars = "user_id=" + userId + "&mobile=" + mobile + "&isLogin=1";
                String replace = baseUrl.replace("type=0", urlPars);
                mUrl = replace;
            } else if(baseUrl.contains("type=2")){
                String urlPars = "user_id=" + userId + "&mobile=" + mobile + "&isLogin=1";
                String replace = baseUrl.replace("type=2", urlPars);
                mUrl = replace;
            }else {
                if (baseUrl.contains("?")) {
                    mUrl = baseUrl + "&user_id=" + userId + "&mobile=" + mobile + "&isLogin=1";
                } else {
                    mUrl = baseUrl + "?user_id=" + userId + "&mobile=" + mobile + "&isLogin=1";
                }
            }
        } else {
            //未登陆不进入下一页
            if (mOldUrl.contains("type=1")) {
                mOldUrl.replace("type=1", "isLogin=0");
            } else {
                if (mOldUrl.contains("?")) {
                    mUrl = mOldUrl + "&isLogin=0";
                } else {
                    mUrl = mOldUrl + "?isLogin=0";
                }
            }
        }
        Logcat.i("--------------------- getUrl " + mUrl);
    }

    private void loadWebView(String url) {
        bmAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(llWebRoot, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT))
                .useDefaultIndicator()
                .setWebViewClient(mWebViewClient)
                .setWebChromeClient(mWebChromeClient)
                .setMainFrameErrorView(R.layout.yogaweb_error_page, -1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                //                .setWebLayout(new WebLayout(this))
                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                .go(url);
        StatService.trackWebView(getActivity(), bmAgentWeb.getWebCreator().getWebView(), mWebChromeClient);
        //        bmAgentWeb = mReady
    }

    @Override
    protected void setListener() {
        super.setListener();
        if (flag == 0) {
        } else if (flag == 1) {
        }
        flag = (flag + 1) % 2;//其余得到循环执行上面2个不同的功能
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //unbinder1.unbind();
    }

    private WebViewClient mWebViewClient = new WebViewClient() {

        /**
         * @param view
         * @param url
         * @return 拦截url 在本地打开
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Logcat.i("--------------------- TAG: WebViewClient shouldOverrideUrlLoading(WebView view, String url) " + url);
            HashMap<String, String> parType = getParType(url);
            mUrl = url;
            mNewUrl = url;
            if (parType.size() > 0) {
                String type = parType.get("type");
                String paymentType = parType.get("paymentType");
                if (type != null) { //判断是否有登陆
                    if (!"1".equals(type)) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.putExtra("action", "webShop");
                        getActivity().startActivity(intent);
                    } else {
                        if (paymentType != null) {
                            getPayInfo(parType);
                        } else {
                            view.loadUrl(url);
                            mOldUrl = url;
                        }
                    }
                } else {
                    view.loadUrl(url);
                    mOldUrl = url;
                }
            } else {
                view.loadUrl(url);
                mOldUrl = url;
            }
            return true;
            //https://cmzb.idyoga.cn/yoga_college_mall/order/order.html?goodsId=68&goodsPropertyId=60&num=1&type=0
        }

        public void sendEventBusMsg() {
            PostResult webShop = new PostResult("webShop");
            EventBus.getDefault().post(webShop);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String url = request.getUrl().toString();
            mUrl = url;
            mNewUrl = url;
            Logcat.i("--------------------- TAG: WebViewClient shouldOverrideUrlLoading " + url);
            HashMap<String, String> parType = getParType(url);
            if (parType.size() > 0) {
                String type = parType.get("type");
                String paymentType = parType.get("paymentType");
                if (type != null) { //判断是否有登陆
                    if (!"1".equals(type)) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.putExtra("action", "webShop");
                        getActivity().startActivity(intent);
                    } else {
                        if (paymentType != null) {
                            getPayInfo(parType);
                        } else {
                            view.loadUrl(url);
                            mOldUrl = url;
                        }
                    }
                } else {
                    view.loadUrl(url);
                    mOldUrl = url;
                }
            } else {
                view.loadUrl(url);
                mOldUrl = url;
            }
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //Logcat.i("--------------------- TAG: WebViewClient onPageStarted");
        }
    };

    private void getPayInfo(HashMap<String, String> parType) {
        Logcat.i("--------------------- getPayInfo " + parType.toString());
        final HashMap<String, String> parMap = new HashMap<>();
        String paymentType = parType.get("paymentType");
        String orderId = parType.get("orderId");
        int userId = (int) SharedPreferencesUtils.getSP(getContext(), "UserId", 0);
        if (!TextUtils.isEmpty(paymentType) && !TextUtils.isEmpty(orderId)) {
            parMap.put("paymentType", paymentType);
            parMap.put("orderId", orderId);
            parMap.put("userId", String.valueOf(userId));
        } else {
            Logcat.i("---------------支付类型或者订单id为空------------------");
            return;
        }
        Logcat.i("--------------------- getPayInfo " + parMap.toString());
        showLoading(null, true);
        HttpClient.get(NetWorkConstant.SHOP_PLAY_URL, parMap, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                dismissLoading();
                super.onSuccess(statusCode, content);
                Logcat.i("\n提交的参数：" + 0 + "\n" + "返回码：" + statusCode + "\n" + "返回参数：" + content);
                if (statusCode == 200) {
                    ResultBean resultBean = JSON.parseObject(content, ResultBean.class);
                    String code = resultBean.getCode();
                    String msg = resultBean.getMsg();
                    String data = resultBean.getData();
                    if (resultBean.getCode().equals("1")) {
                        String payType = parMap.get("paymentType");
                        if ("1".equals(payType)) {//积分支付
                            getUrl(mUrl);
                            bmAgentWeb.getUrlLoader().loadUrl(mUrl);
                            Logcat.i("=================== getPayInfo (1.equals(payType)" + mUrl);
                        } else if ("2".equals(payType)) {//微信支付
                            Logcat.i("=================== getPayInfo (2.equals(payType)" + " --getCode-- " + resultBean.getCode() + " -- " + mUrl);
                            if ("1".equals(resultBean.getCode())) {
                                sendWxPay(resultBean);
                            }
                        } else if ("3".equals(payType)) {//积分+微信支付
                            Logcat.i("=================== getPayInfo (3.equals(payType)" + " --getCode-- " + resultBean.getCode() + " -- " + mUrl);
                            if ("1".equals(resultBean.getCode())) {
                                sendWxPay(resultBean);
                            }
                        }
                    }else {
                        ToastUtil.showToast(resultBean.getMsg());
                        getUrl(mOldUrl);
                        bmAgentWeb.getUrlLoader().loadUrl(mUrl);
                    }
                    Logcat.i("---- code --- " + code + " ---- msg ----- " + msg + "--- data ----" + data);
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                dismissLoading();
                Logcat.i("=================== onFailure");
            }
        });

    }

    private void sendWxPay(ResultBean mResultBean) {

        WxPayBean wxPayBean = JSON.parseObject(mResultBean.getData(), WxPayBean.class);
        new WxPayAction("shopPay", "shopPay");
        PayReq request = new PayReq(); //调起微信APP的对象
        //下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明
        request.appId = wxPayBean.getKey().getAppid();
        request.partnerId = wxPayBean.getKey().getPartnerid();
        request.prepayId = wxPayBean.getKey().getPrepayid();
        request.packageValue = "Sign=WXPay";
        request.nonceStr = wxPayBean.getKey().getNoncestr();
        request.timeStamp = String.valueOf(wxPayBean.getKey().getTimestamp());
        request.sign = wxPayBean.getKey().getSign();
        api.sendReq(request);//发送调起微信的请求

    }

    private HashMap<String, String> getParType(String url) {
        //https://cmzb.idyoga.cn/yoga_college_mall/order/orderSuccess.html?paymentType=1&orderId=622&type=1
        HashMap<String, String> map = new HashMap<>();
        if (url.contains("?")) {
            int index = url.indexOf("?");
            String temp = url.substring(index + 1);
            String[] keyValue = temp.split("&"); //"type = 0"
            StringBuilder sb = new StringBuilder(); //?user_id=46244
            for (String str : keyValue) {
                String[] result = str.split("=");
                if (result.length == 2){
                    map.put(result[0], result[1]);
                }
            }
        }
        Logcat.i("--------------------- TAG: getParType result = " + mUrl);
        return map;
    }

    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            //Logcat.i("--------------------- TAG: WebChromeClient onReceivedTitle");
            if (tvTitle != null) {
                if ("IDyoga商城".equals(title)){
                    tvTitle.setText("积分商城");
                }
            }
        }
    };

    @Override
    public void onPause() {
        bmAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    public void onResume() {
        bmAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bmAgentWeb.clearWebCache();
        bmAgentWeb.getWebLifeCycle().onDestroy();
    }

    @OnClick({R.id.ll_left_back,R.id.ll_left_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_left_back:
                Logcat.i("-----------onViewClicked (webView.canGoBack()----- ");
                if (!bmAgentWeb.back()){
                    FragmentActivity activity = getActivity();
                    if (activity != null){
                        activity.finish();
                    }
                }
                break;
            case R.id.ll_title_right:
                getActivity().finish();
                break;
        }


    }

    boolean isLoad = false;

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        Logcat.i("------Tag:" + postResult.getTag());
        if ("showCreditFragment".equals(postResult.getTag())) {
            isLoad = true;
            bmAgentWeb.clearWebCache();
            Logcat.i("--------------------- mUrl " + mUrl); //https://cmzb.idyoga.cn/yoga_college_mall/index/index?
//            String baseUrl = "https://cmzb.idyoga.cn/yoga_college_mall/index/index";
            String baseUrl = "https://integral.idyoga.cn/yoga_college_mall/index/index";
            if (mUrl.contains(baseUrl)) {
                mUrl = baseUrl;
            }
        } else if ("wxPaySuccess".equals(postResult.getTag())) {//支付成功
            Logcat.i("----------支付成功----------- mUrl " + mUrl);
            isLoad = true;
            getUrl(mUrl);
            bmAgentWeb.getUrlLoader().loadUrl(mUrl);
        } else if ("loginIn".equals(postResult.getTag())) {
            Logcat.i("----------登陆成功----------- mUrl " + mUrl);
            isLoad = true;
            getUrl(mUrl);
            bmAgentWeb.getUrlLoader().loadUrl(mUrl);
        } else if ("onKeyDown".equals(postResult.getTag())) {
            HashMap result = (HashMap) postResult.getResult();
            IEventHandler iEventHandler = bmAgentWeb.getIEventHandler();
            int keyCode = (int) result.get("keyCode");
            KeyEvent event = (KeyEvent) result.get("event");
            Logcat.i("------onKeyDown-----keyCode " + keyCode + " event " + event);
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                if (bmAgentWeb != null && bmAgentWeb.handleKeyEvent(keyCode, event)) {
                    Logcat.i("-----------onKeyDown (webView.canGoBack()----- ");
                }
            }
        }else if("wxPayCancel".equals(postResult.getTag())){
            mUrl = mOldUrl;
        } else if(postResult.getTag().equals("advertiDetail")){
            //跳去广告页
            Bundle bundle = new Bundle();
            bundle.putString("getUrl",(String) postResult.getResult());
            startActivityWithExtras(YogaWebActivity.class,bundle);
        }
    }

    /**
     * 获取广告信息
     */
    private void getAdvertiInfo() {
        HashMap<String, String> map = new HashMap<>();
        String cityId = (String) SharedPreferencesUtils.getSP(mActivity, "cityId", "");
        map.put("cityId", cityId + "");
        map.put("type", "2");
        OkHttpUtils.get().url(ApiConstants.Urls.YOGA_ADVERTI).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                ResultBean resultBean = JSON.parseObject(response, ResultBean.class);
                if (resultBean.getCode().equals("1")){
                    if (resultBean.getData() != null){
                        AdvertiBean adverti = JSON.parseObject(resultBean.getData(), AdvertiBean.class);
                        ShowAdverti.getInstance().showAdverti(adverti,getActivity(),"ShoppingPage");
                    }
                }
            }
        });
    }
}
