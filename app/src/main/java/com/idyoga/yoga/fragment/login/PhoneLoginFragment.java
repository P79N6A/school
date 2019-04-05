package com.idyoga.yoga.fragment.login;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.loading.BindPhoneNumActivity;
import com.idyoga.yoga.api.HttpUrlUtil;
import com.idyoga.yoga.comm.NetWorkConstant;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.fragment.BaseLoginFragment;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.UserInfoBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.TimeUtils;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.UserUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import okhttp3.Request;
import vip.devkit.common.share.LoginUtil;
import vip.devkit.common.share.login.LoginListener;
import vip.devkit.common.share.login.LoginPlatform;
import vip.devkit.common.share.login.LoginResult;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

/**
 * Created by mjh on 2019/1/4.
 * 手机号码登陆
 */

public class PhoneLoginFragment extends BaseLoginFragment {


    TimeUtils mTimeUtils;
    @BindView(R.id.et_input_phone)
    EditText etInputPhone;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;
    @BindView(R.id.et_input_pwd)
    EditText etInputPwd;
    @BindView(R.id.btn_forget_prw)
    Button btnForgetPrw;
    @BindView(R.id.iv_wx_login)
    ImageView ivWxLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private String mAction;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        mAction = arguments.getString("action");
        Logcat.e("*******PhoneLoginFragment********onCreate action " + mAction);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phone_login_layout, container, false);
        ButterKnife.bind(this, view);
        mTimeUtils = new TimeUtils(btnGetCode);
        mTimeUtils.setTextColor("#999999");
        initListener();
        return view;
    }

    private void initListener() {
        //让布局向上移来显示软键盘
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        etInputPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String code = etInputPwd.getText().toString();
                if (!TextUtils.isEmpty(code)) {
                    btnLogin.setEnabled(true);
                    btnLogin.setBackground(getResources().getDrawable(R.drawable.login_bg));
                    etInputPwd.setTextColor(Color.parseColor("#333333"));
                } else {
                    btnLogin.setEnabled(false);
                    btnLogin.setBackground(getResources().getDrawable(R.drawable.bg_default_01));
                    etInputPwd.setTextColor(Color.parseColor("#999999"));
                }
            }
        });
        etInputPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String code = etInputPhone.getText().toString();
                if (!TextUtils.isEmpty(code)) {
                    etInputPhone.setTextColor(Color.parseColor("#333333"));
                } else {
                    etInputPhone.setTextColor(Color.parseColor("#999999"));
                }
            }
        });
    }

    private void wxLogin() {
        sendWxLoginRequest();
        //getActivity().finish();
    }

    private void login() {
        String phoneNum = etInputPhone.getText().toString();
        String smsNum = etInputPwd.getText().toString();
        if (StringUtil.isEmpty(phoneNum)) {
            ToastUtil.showToast("手机号不能为空");
            return;
        }
        if (!CommonUtils.isMobile(phoneNum)) {
            ToastUtil.showToast("请输入正确的手机号");
            return;
        }
        if (phoneNum.length() != 11) {
            ToastUtil.showToast("请输入正确的手机号");
            return;
        }
        if (StringUtil.isEmpty(smsNum)) {
            ToastUtil.showToast("请输入验证码");
            return;
        }
        ExecuteLogin(phoneNum, smsNum);
    }

    public void getVerificationCode() {
        String phoneNum = etInputPhone.getText().toString();
        if (StringUtil.isEmpty(phoneNum)) {
            ToastUtil.showToast("手机号不能为空");
            return;
        }
        if (!CommonUtils.isMobile(phoneNum)) {
            ToastUtil.showToast("请输入正确的手机号");
            return;
        }
        mTimeUtils.RunTimer();
        RegPhone(phoneNum);
    }

    private void RegPhone(String mobile) {
        Map<String, String> map = new HashMap();
        map.put("mobile", mobile);
        final String str = map.toString();
        HttpClient.post(NetWorkConstant.GET_SMS_FOR_LONGIN, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                //Logcat.i("\n接口地址：" + NetWorkConstant.GET_SMS_FOR_LONGIN + "\n提交的参数：" + str + "\n" + "返回码：" + statusCode + "\n" + "返回参数：" + content);
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

    /**
     * 验证码密码登录
     *
     * @param mobile
     * @param pwdOrCode 验证码、密码
     */
    private void ExecuteLogin(String mobile, String pwdOrCode) {
        final String url = HttpUrlUtil.SMS_NUMBER_LOG;
        Map map = new HashMap();
        map.put("mobile", mobile);
        map.put("smsNumber", pwdOrCode);
        map.put("appType","1");
        final String str = map.toString();
        final String urls = url;
        showLoading("正在登陆...");
        HttpClient.post(url, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                //Logcat.i("\n接口地址：" + urls + "\n提交的参数：" + str + "\n" + "返回码：" + statusCode + "\n" + "返回参数：" + content);
                final ResultBean resultBean = JSON.parseObject(content, ResultBean.class);
                dismissLoading();
                if (resultBean != null) {
                    if ("1".equals(resultBean.getCode())) {
                        UserInfoBean userInfoBean = JSON.parseObject(resultBean.getData(), UserInfoBean.class);
                        Logcat.e("原始数据" + userInfoBean.toString());
                        SharedPreferencesUtils.setSP(getContext(), "UserId", userInfoBean.getId());
                        SharedPreferencesUtils.setSP(getContext(), "Token", userInfoBean.getToken());
                        SharedPreferencesUtils.setSP(getContext(), "Mobile", userInfoBean.getMobile());
                        SharedPreferencesUtils.setSP(getContext(), "unionid", userInfoBean.getUnion_id());
                        UserUtil.setUserBean(getContext(), resultBean.getData());
                        UserInfoBean userInfoBean1 = UserUtil.getUserBean(getContext());
                        if (userInfoBean1 != null) {
                            Logcat.i("数据插入成功：" + "/\n" + userInfoBean1.toString());
                        }
                        ToastUtil.showToast("登录成功");
                        EventBus.getDefault().post(new PostResult("loginIn", userInfoBean));
                        getActivity().finish();
                    } else {
                        Logcat.e("" + resultBean.getMsg());
                        ToastUtil.showToast(resultBean.getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                ToastUtil.showToast("登陆失败,请重新登陆");
                dismissLoading();
            }
        });
    }


    @OnClick({R.id.btn_get_code, R.id.btn_forget_prw, R.id.iv_wx_login,R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_code:
                getVerificationCode();
                break;
            case R.id.btn_forget_prw:
                break;
            case R.id.iv_wx_login:
                wxLogin();
                break;
            case R.id.btn_login:
                login();
                break;
        }
    }

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        Logcat.e("*******PhoneFragment********onEvent() " + postResult.getTag());
    }
}
