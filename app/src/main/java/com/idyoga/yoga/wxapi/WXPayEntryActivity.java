package com.idyoga.yoga.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.comm.Constants;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.common.yogaweb.LogUtils;
import com.idyoga.yoga.utils.LoginUtil;
import com.idyoga.yoga.utils.ToastUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import com.idyoga.yoga.model.pay.WxPayAction;

import de.greenrobot.event.EventBus;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "WXPay";
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.idyoga.yoga.R.layout.activity_main);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        api = WXAPIFactory.createWXAPI(this, Constants.WX_APP_ID);
        api.registerApp(Constants.WX_APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        api.handleIntent(data, this);
    }

    @Override
    public void onReq(BaseReq req) {
        Logcat.e(TAG, req.openId + "");
        Logcat.e(TAG, req.transaction + "");
    }

    @Override
    public void onResp(BaseResp resp) {
        Logcat.e(TAG, resp.errCode + "");
        Logcat.e(TAG, resp.errStr + "");
        Logcat.i("==========onResp=========" + resp.errCode);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            String payUseing = WxPayAction.getPayUseing();
            String wwwType = WxPayAction.getWwwType();
            String OrderNo = WxPayAction.getOrderNo();
            if ("Pay".equals(payUseing)) {
                if (resp.errCode == 800) {
                    ToastUtil.showToast("请重新操作、谢谢");
                    finish();
                } else if (resp.errCode == 0) {
                    ToastUtil.showToast("支付成功");
                } else if (resp.errCode == -1) {
                    ToastUtil.showToast("支付失败");
                    finish();
                } else if (resp.errCode == -2) {
                    ToastUtil.showToast("取消支付");
                    finish();
                }
            } else if (payUseing.equals("cz")){
                ToastUtil.showToast("充值成功");
            } else if("shopPay".equals(payUseing)){ //积分商城的微信支付
                Logcat.i("==========errCode=========" + resp.errCode);
                if (resp.errCode == 800) {
                    ToastUtil.showToast("请重新操作、谢谢");
                    finish();
                } else if (resp.errCode == 0) {
                    Logcat.i("==========支付成功=========" + resp.errCode);
                    EventBus.getDefault().post(new PostResult("wxPaySuccess"));
                    ToastUtil.showToast("支付成功");
                } else if (resp.errCode == -1) {
                    ToastUtil.showToast("支付失败");
                    finish();
                } else if (resp.errCode == -2) {
                    ToastUtil.showToast("取消支付");
                    EventBus.getDefault().post(new PostResult("wxPayCancel"));
                    finish();
                }
            }
        }
        finish();
    }

    public void onEvent(PostResult postResult) {

    }
}