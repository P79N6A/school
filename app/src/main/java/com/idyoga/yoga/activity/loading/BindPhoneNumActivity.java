package com.idyoga.yoga.activity.loading;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.Constants;
import com.idyoga.yoga.comm.NetWorkConstant;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.UserInfoBean;
import com.idyoga.yoga.model.WxLoginResultBean;
import com.idyoga.yoga.model.WxUserInfoBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.TimeUtils;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.UserUtil;
import com.idyoga.yoga.wxapi.WXEntryActivity;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import okhttp3.Request;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * Created by mjh on 2019/1/9.
 */

public class BindPhoneNumActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private WxUserInfoBean wxUserInfoBean;
    private TimeUtils mTimeUtils;
    private WxLoginResultBean wxLoginResultBean;
    private String unionid;
    private boolean isBindPhone = false;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(ivBack).flymeOSStatusBarFontColor("#333333").init();
    }

    @Override
    protected void initData() {
        mTimeUtils = new TimeUtils(btnGetCode);
        Intent intent = getIntent();
        String wxLoginResultStr = intent.getStringExtra("wxLoginResultBean");
        unionid = intent.getStringExtra("unionid");
        wxLoginResultBean = JSON.parseObject(wxLoginResultStr, WxLoginResultBean.class);
        getWxUserInfo(wxLoginResultBean);
    }

    //获取微信用户信息
    private void getWxUserInfo(WxLoginResultBean wxLoginResultBean) {
        String getUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo";
        LinkedHashMap<String, String> parMap = new LinkedHashMap<>();
        parMap.put("access_token",wxLoginResultBean.getAccess_token());
        parMap.put("openid",wxLoginResultBean.getOpenid());
        parMap.put("lang","zh_CN");
        HttpClient.get(getUserInfoUrl,parMap,new HttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                wxUserInfoBean = JSON.parseObject(content, WxUserInfoBean.class);
                if (isBindPhone){
                    bindPhoneNum();
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_bind_phone_num;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = etCode.getText().toString();
                if (!TextUtils.isEmpty(string)){
                    btnSubmit.setEnabled(true);
                    btnSubmit.setBackground(getResources().getDrawable(R.drawable.login_bg));
                    etCode.setTextColor(Color.parseColor("#333333"));
                }else{
                    btnSubmit.setEnabled(false);
                    btnSubmit.setBackground(getResources().getDrawable(R.drawable.bg_default_01));
                    etCode.setTextColor(Color.parseColor("#999999"));
                }
            }
        });
        etPhoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = etPhoneNum.getText().toString();
                if (!TextUtils.isEmpty(string)){
                    etPhoneNum.setTextColor(Color.parseColor("#333333"));
                }else{
                    etPhoneNum.setTextColor(Color.parseColor("#999999"));
                }
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.btn_get_code, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_get_code:
                String phoneNum = etPhoneNum.getText().toString();
                if (checkPhoneNum(phoneNum)) return;
                mTimeUtils.RunTimer();
                RegPhone(phoneNum);
                break;
            case R.id.btn_submit:
                String phone = etPhoneNum.getText().toString();
                String code = etCode.getText().toString();
                if (checkPhoneNum(phone)) return;
                if (TextUtils.isEmpty(code)){
                    ToastUtil.showToast("请输入验证码");
                    return;
                }
                if (wxUserInfoBean == null){
                    isBindPhone = true;
                    getWxUserInfo(wxLoginResultBean);
                }else{
                    bindPhoneNum();
                }
                break;
        }
    }

    private void RegPhone(String mobile) {
        Map<String, String> map = new HashMap();
        map.put("mobile", mobile);
        final String str = map.toString();
        HttpClient.post(ApiConstants.Urls.BIND_PHONE_SEND_CODE, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                ResultBean resultBean = JSON.parseObject(content, ResultBean.class);
                if (resultBean.getCode().equals("1")) {
                    ToastUtil.showToast(resultBean.getMsg());
                } else {
                    if (mTimeUtils != null) {
                        mTimeUtils.StopTimer();
                    }
                    ToastUtil.showToast(resultBean.getMsg());
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                if (mTimeUtils != null) {
                    mTimeUtils.StopTimer();
                }
            }
        });
    }

    private boolean checkPhoneNum(String phone) {
        if (TextUtils.isEmpty(phone)){
            ToastUtil.showToast("请输入手机号");
            return true;
        }
        if (phone.length() != 11){
            ToastUtil.showToast("请输入正确的手机号");
            return true;
        }
        if (!CommonUtils.isMobile(phone)){
            ToastUtil.showToast("请输入正确的手机号");
            return true;
        }
        return false;
    }

    //绑定手机号
    private void bindPhoneNum() {
        //http://testyogabook.hq-xl.com/mall/user_unionid/sendSms
        String getUserInfoUrl = ApiConstants.Urls.BIND_PHONE;
        String phone = etPhoneNum.getText().toString();
        String code = etCode.getText().toString();

        final LinkedHashMap<String, String> parMap = new LinkedHashMap<>();
        parMap.put("unionid",unionid);
        parMap.put("openid",wxUserInfoBean.getOpenid());
        parMap.put("appid", Constants.WX_APP_ID);
        parMap.put("name",wxUserInfoBean.getNickname());
        parMap.put("sex",String.valueOf(wxUserInfoBean.getSex()));
        parMap.put("mobile",phone);
        parMap.put("headimgurl",wxUserInfoBean.getHeadimgurl());
        parMap.put("smsCode",code);

        HttpClient.get(getUserInfoUrl,parMap,new HttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                isBindPhone = false;
                ResultBean resultBean = JSON.parseObject(content, ResultBean.class);
//                Toast.makeText(BindPhoneNumActivity.this,"parMap.toString = " + parMap.toString() + "=========== resultBean = " + resultBean.toString(),Toast.LENGTH_LONG).show();
                if (resultBean != null && "1".equals(resultBean.getCode())){
                    String data = resultBean.getData();
                    UserInfoBean userInfoBean = JSON.parseObject(data, UserInfoBean.class);
                    SharedPreferencesUtils.setSP(BindPhoneNumActivity.this, "UserId", userInfoBean.getId());
                    SharedPreferencesUtils.setSP(BindPhoneNumActivity.this, "Token", userInfoBean.getToken());
                    SharedPreferencesUtils.setSP(BindPhoneNumActivity.this, "Mobile", userInfoBean.getMobile());
                    UserUtil.setUserBean(BindPhoneNumActivity.this, resultBean.getData());
                    UserInfoBean userInfoBean1 = UserUtil.getUserBean(BindPhoneNumActivity.this);
                    if (userInfoBean1 != null) {
                        Logcat.i("数据插入成功：" + "/\n" + userInfoBean1.toString());
                    }
                    ToastUtil.showToast("登录成功");
                    EventBus.getDefault().post(new PostResult("loginIn", userInfoBean));
                    finish();
                }else{
                    String msg = resultBean.getMsg();
                    ToastUtil.showToast(msg);
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);

            }
        });
    }
}
