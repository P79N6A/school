package com.idyoga.yoga.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.loading.BindPhoneNumActivity;
import com.idyoga.yoga.activity.user.WelcomeActivity;
import com.idyoga.yoga.api.HttpUrlUtil;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.Constants;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.UserInfoBean;
import com.idyoga.yoga.model.WxLoginResultBean;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.UserUtil;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import de.greenrobot.event.EventBus;
import okhttp3.Request;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * Created by mjh on 2019/1/8.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);//隐藏标题栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        setContentView(R.layout.activity_wx_entry_layout);
        api = WXAPIFactory.createWXAPI(this, Constants.WX_APP_ID, false);
        api.registerApp(Constants.WX_APP_ID);
        //注意：
        //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        try {
            api.handleIntent(getIntent(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }


    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                if (baseResp instanceof SendAuth.Resp) {
                    SendAuth.Resp newResp = (SendAuth.Resp) baseResp;
                    String code = newResp.code;
                    String url = HttpUrlUtil.WX_LOGIN_GET_ACCESS_TOKEN;
                    LinkedHashMap<String, String> parsMap = new LinkedHashMap<>();
                    parsMap.put("appid",Constants.WX_APP_ID );
                    parsMap.put("secret",Constants.WX_SECRET_ID);
                    parsMap.put("code",code);
                    parsMap.put("grant_type","authorization_code");
                    HttpClient.get(url,parsMap,new HttpResponseHandler(){
                        @Override
                        public void onSuccess(int statusCode, String content) {
                            super.onSuccess(statusCode, content);
                            WxLoginResultBean resultBean = JSON.parseObject(content, WxLoginResultBean.class);
                            SharedPreferencesUtils.setSP(WXEntryActivity.this, "unionid", resultBean.getUnionid());
                            if (resultBean != null){
                                isBuindWx(resultBean);
                            }
                        }

                        @Override
                        public void onFailure(Request request, IOException e) {
                            super.onFailure(request, e);
                        }
                    });
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
                finish();
                break;
            default:
                break;
        }
    }

    //检查当前unionid是否已经绑定用户
    private void isBuindWx(final WxLoginResultBean resultBean) {
        final String unionid = resultBean.getUnionid();
        String checkUnionidBind = ApiConstants.Urls.CHECK_USER_UNIONID_BIND;
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("unionid",unionid);
        hashMap.put("openid",resultBean.getOpenid());
        hashMap.put("appid",Constants.WX_APP_ID);
        hashMap.put("appType","1");
        HttpClient.get(checkUnionidBind,hashMap,new HttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                final ResultBean bean = JSON.parseObject(content, ResultBean.class);
                if ("1".equals(bean.getCode())){
                    if ("未绑定用户".equals(bean.getMsg())){
                        //跳转到绑定手机号界面,绑定手机号再绑定wx用户信息到后台
                        toBindPhoneNum(resultBean,unionid);
                    }else if("操作成功".equals(bean.getMsg())){
                        //已登录
                        againLogin(bean);
                    }
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
            }
        });
    }

    private void againLogin(ResultBean bean) {
        if (bean != null && "1".equals(bean.getCode())){
            String data = bean.getData();
            UserInfoBean userInfoBean = JSON.parseObject(data, UserInfoBean.class);
            SharedPreferencesUtils.setSP(WXEntryActivity.this, "UserId", userInfoBean.getId());
            SharedPreferencesUtils.setSP(WXEntryActivity.this, "Token", userInfoBean.getToken());
            SharedPreferencesUtils.setSP(WXEntryActivity.this, "Mobile", userInfoBean.getMobile());
            UserUtil.setUserBean(WXEntryActivity.this, bean.getData());
            UserInfoBean userInfoBean1 = UserUtil.getUserBean(WXEntryActivity.this);
            if (userInfoBean1 != null) {
                Logcat.i("数据插入成功：" + "/\n" + userInfoBean1.toString());
            }
            ToastUtil.showToast("登录成功");
            EventBus.getDefault().post(new PostResult("loginIn", userInfoBean));
            toWelcomeAgainLogin(userInfoBean.getHeadimgurl());
        }else{
            String msg = bean.getMsg();
            ToastUtil.showToast(msg);
            finish();
        }
    }

    private void toWelcomeAgainLogin(String headUrl) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra("headUrl",headUrl);
        startActivity(intent);
        PostResult postResult = new PostResult();
        postResult.setTag("FinishLoginActivity");
        EventBus.getDefault().post(postResult);
        finish();
    }

    private void toBindPhoneNum(WxLoginResultBean resultBean,String unionid) {
        Intent intent = new Intent(this, BindPhoneNumActivity.class);
        intent.putExtra("wxLoginResultBean",JSON.toJSONString(resultBean));
        intent.putExtra("unionid",unionid);
        startActivity(intent);
        PostResult postResult = new PostResult();
        postResult.setTag("FinishLoginActivity");
        EventBus.getDefault().post(postResult);
        finish();
    }

    private void bindWxUser(final WxLoginResultBean resultBean) {
        //https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID
    }

    public void onEvent(PostResult postResult){
        Logcat.e("*******WXEntryActivity********onEvent() " + postResult.getTag());
    }

    /*private void bindWxUserInfo(WxLoginResultBean resultBean, WxUserInfoBean wxUserInfoBean) {
         String checkUnionidBind = ApiConstants.Urls.CHECK_USER_UNIONID_BIND;
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("unionid",resultBean.getUnionid());
        hashMap.put("openid",resultBean.getOpenid());
        hashMap.put("appid",Constants.WX_APP_ID);
        hashMap.put("sex",wxUserInfoBean.getSex() == 1 ? "男" : "女");
        hashMap.put("sex",wxUserInfoBean.getSex() == 1 ? "男" : "女");
        HttpClient.get(checkUnionidBind,hashMap,new HttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                ResultBean bean = JSON.parseObject(content, ResultBean.class);
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
            }
        });
    }*/
}
