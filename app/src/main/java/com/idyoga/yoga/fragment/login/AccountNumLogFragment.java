package com.idyoga.yoga.fragment.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.user.UpdatePwdActivity;
import com.idyoga.yoga.api.HttpUrlUtil;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.fragment.BaseLoginFragment;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.UserInfoBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.UserUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import okhttp3.Request;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

/**
 * Created by mjh on 2019/1/4.
 * 账号登陆
 */

public class AccountNumLogFragment extends BaseLoginFragment {

    @BindView(R.id.et_input_phone)
    EditText etInputPhone;
    @BindView(R.id.iv_is_show_pwd)
    ImageView ivIsShowPwd;
    @BindView(R.id.et_input_pwd)
    EditText etInputPwd;
    @BindView(R.id.btn_forget_prw)
    Button btnForgetPrw;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.iv_wx_login)
    ImageView ivWxLogin;
    @BindView(R.id.rl_show_pwd)
    RelativeLayout rlShowPwd;
    private boolean isChecked = false;
    private String mAction;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        mAction = arguments.getString("action");
        Logcat.e("*******AccountNumLogFragment********onCreate action " + mAction);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_num_login, container, false);
        ButterKnife.bind(this, view);

        etInputPwd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        ivIsShowPwd.setSelected(false);
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
                String pwd = etInputPwd.getText().toString();
                if (!TextUtils.isEmpty(pwd)) {
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
                String pwd = etInputPhone.getText().toString();
                if (!TextUtils.isEmpty(pwd)) {
                    etInputPhone.setTextColor(Color.parseColor("#333333"));
                } else {
                    etInputPhone.setTextColor(Color.parseColor("#999999"));
                }
            }
        });

    }

    private void login() {
        String phoneNum = etInputPhone.getText().toString();
        String pwd = etInputPwd.getText().toString();
        if (StringUtil.isEmpty(phoneNum)) {
            ToastUtil.showToast("请输入手机号");
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
        if (StringUtil.isEmpty(pwd)) {
            ToastUtil.showToast("密码不能为空");
            return;
        }
        String regex = "^[0-9A-Za-z]{6,16}$";
        if (!Pattern.matches(regex, pwd)) {
            ToastUtil.showToast("请输入6-16位数字和字母组成的密码");
            return;
        }
        ExecuteLogin(phoneNum, pwd);
    }

    private void ExecuteLogin(String phoneNum, String pwd) {
        String url = HttpUrlUtil.PWD_NUMBER_LOG;
        Map map = new HashMap();
        map.put("mobile", phoneNum);
        map.put("password", pwd);
        map.put("appType","1");
        final String urls = url;
        showLoading("正在登陆...");
        HttpClient.post(url, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                final ResultBean resultBean = JSON.parseObject(content, ResultBean.class);
                dismissLoading();
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
                    dismissLoading();
                    Logcat.e("" + resultBean.getMsg());
                    ToastUtil.showToast(resultBean.getMsg());
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

    private void wxLogin() {
        sendWxLoginRequest();
        //getActivity().finish();
    }

    int flag = 0;

    @OnClick({R.id.tv_forget_pwd, R.id.btn_login, R.id.iv_wx_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.iv_wx_login:
                wxLogin();
                break;
            case R.id.tv_forget_pwd:
                Bundle bundle1 = new Bundle();
                bundle1.putString("tag", "login");
                Intent intent1 = new Intent(getActivity(), UpdatePwdActivity.class);
                intent1.putExtras(bundle1);
                getActivity().startActivity(intent1);
                break;
        }
    }

    @OnClick(R.id.rl_show_pwd)
    public void onViewClicked() {
        String text = etInputPwd.getText().toString();
        if (flag == 0) {
            ivIsShowPwd.setSelected(true);
            etInputPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//            etInputPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);// 输入为密码且可见
        } else if (flag == 1) {
            ivIsShowPwd.setSelected(false);
            etInputPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
//            etInputPwd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);// 设置文本类密码（默认不可见），这两个属性必须同时设置
        }
        etInputPwd.setSelection(text.length());
        flag = (flag + 1) % 2;
    }

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        Logcat.e("*******AccountFragment********onEvent() " + postResult.getTag());
    }
}
