/*
 ******************************* Copyright (c)*********************************\
 **
 **                 (c) Copyright 2017, DevKit.vip, china, qd. sd
 **                          All Rights Reserved
 **
 **                           By(K)
 ********************************End of Head************************************\
 */
package com.idyoga.yoga.activity.user;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.NetWorkConstant;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.UserInfoBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.TimeUtils;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.UserUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;
import vip.devkit.library.Logcat;
import vip.devkit.library.RegUtil;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

/**
 * 文 件 名: LoginActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/1/24
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.et_handset)
    EditText mEtHandset;
    @BindView(R.id.et_in_pwd_1)
    EditText mEtInPwd1;
    @BindView(R.id.ll_switch_1)
    LinearLayout mLlSwitch1;
    @BindView(R.id.et_in_pwd_2)
    EditText mEtInPwd2;
    @BindView(R.id.ll_switch_2)
    LinearLayout mLlSwitch2;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.btn_get_code)
    Button mBtnGetCode;
    @BindView(R.id.iv_switch_1)
    ImageView mIvSwitch1;
    @BindView(R.id.iv_switch_2)
    ImageView mIvSwitch2;
    @BindView(R.id.btn_submit)
    Button mBtnSubmit;
    int flag_1 = 0, flag_2 = 0;
    TimeUtils mTimeUtils;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mIvBack).flymeOSStatusBarFontColor("#333333").init();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_register;
    }

    @Override
    protected void initView() {
        mTimeUtils = new TimeUtils(mBtnGetCode);
        mEtHandset.clearFocus();
        mEtInPwd1.clearFocus();
        mEtInPwd2.clearFocus();
        mEtCode.clearFocus();
//        addLayoutListener();
    }

    @Override
    protected void setListener() {
        //让布局向上移来显示软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        /*FrameLayout content = (FrameLayout) findViewById(android.R.id.content);
        final View childAt = content.getChildAt(0);
        //界面出现变动都会调用这个监听事件
        childAt.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                KeyboardHeightUtil instance = KeyboardHeightUtil.getInstance();
                instance.getVisibleWindowHeight(RegisterActivity.this,childAt,mBtnSubmit);
            }
        });*/
        mEtCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = mEtCode.getText().toString();
                if (!TextUtils.isEmpty(string)) {
                    mBtnSubmit.setEnabled(true);
                    mBtnSubmit.setBackground(getResources().getDrawable(R.drawable.login_bg));
                    mEtCode.setTextColor(Color.parseColor("#333333"));
                } else {
                    mBtnSubmit.setEnabled(false);
                    mBtnSubmit.setBackground(getResources().getDrawable(R.drawable.bg_default_01));
                    mEtCode.setTextColor(Color.parseColor("#999999"));
                }
            }
        });
        mEtHandset.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = mEtHandset.getText().toString();
                if (!TextUtils.isEmpty(string)) {
                    mEtHandset.setTextColor(Color.parseColor("#333333"));
                } else {
                    mEtHandset.setTextColor(Color.parseColor("#999999"));
                }
            }
        });
        mEtInPwd1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = mEtInPwd1.getText().toString();
                if (!TextUtils.isEmpty(string)) {
                    mEtInPwd1.setTextColor(Color.parseColor("#333333"));
                } else {
                    mEtInPwd1.setTextColor(Color.parseColor("#999999"));
                }
            }
        });
        mEtInPwd2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = mEtInPwd2.getText().toString();
                if (!TextUtils.isEmpty(string)) {
                    mEtInPwd2.setTextColor(Color.parseColor("#333333"));
                } else {
                    mEtInPwd2.setTextColor(Color.parseColor("#999999"));
                }
            }
        });
    }


    @OnClick({R.id.iv_back, R.id.ll_switch_1, R.id.ll_switch_2, R.id.btn_get_code, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_switch_1:
                String pwd = mEtInPwd1.getText().toString();
                if (flag_1 == 0) {
                    mIvSwitch1.setImageResource(R.drawable.show_pwd);
                    mEtInPwd1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else if (flag_1 == 1) {
                    mIvSwitch1.setImageResource(R.drawable.hide_pwd);
                    mEtInPwd1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                mEtInPwd1.setSelection(pwd.length());
                flag_1 = (flag_1 + 1) % 2;
                break;
            case R.id.ll_switch_2:
                String pwd2 = mEtInPwd2.getText().toString();
                if (flag_2 == 0) {
                    mIvSwitch2.setImageResource(R.drawable.show_pwd);
                    mEtInPwd2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else if (flag_2 == 1) {
                    mIvSwitch2.setImageResource(R.drawable.hide_pwd);
                    mEtInPwd2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                mEtInPwd2.setSelection(pwd2.length());
                flag_2 = (flag_2 + 1) % 2;
                break;
            case R.id.btn_get_code:
                String mobileNum = mEtHandset.getText().toString().trim();
                if (!StringUtil.isEmpty(mobileNum)) {
                    if (CommonUtils.isMobile(mobileNum) && mobileNum.length() == 11) {
                        RegPhone(mEtHandset.getText().toString().trim());
                    } else {
                        ToastUtil.showToast("请填正确的手机号码");
                    }
                } else {
                    ToastUtil.showToast("手机号不能为空");
                }
                break;
            case R.id.btn_submit:
                String mobile = mEtHandset.getText().toString().trim();
                String pwd1 = mEtInPwd1.getText().toString().trim();
                String pwd3 = mEtInPwd2.getText().toString().trim();
                String code = mEtCode.getText().toString().trim();
                RegSubmitData(mobile, pwd1, pwd3, code, "");
                break;
        }
    }

    private void RegSubmitData(String mobile, String pwd1, String pwd2, String code, String app) {
        if (StringUtil.isEmpty(mobile)) {
            ToastUtil.showToast("请输入手机号");
            return;
        }
        if (mobile.length() != 11) {
            ToastUtil.showToast("请输入正确的手机号");
            return;
        }
        if (!CommonUtils.isMobile(mobile)) {
            ToastUtil.showToast("请输入正确的手机号");
            return;
        }
        if (StringUtil.isEmpty(pwd1) || StringUtil.isEmpty(pwd2)) {
            ToastUtil.showToast("请输入密码");
            return;
        }
//        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        String regex = "^[0-9A-Za-z]{6,16}$";
        Logcat.e("密码正则：" + Pattern.matches(regex, pwd1) + "/" + pwd1);
        if (!Pattern.matches(regex, pwd1)) {
            ToastUtil.showToast("请设置6-16位数字和字母组成的密码");
            return;
        }
        if (!pwd1.equals(pwd2)) {
            ToastUtil.showToast("两次密码不一致");
            return;
        }
        if (StringUtil.isEmpty(code)) {
            ToastUtil.showToast("请填写验证码");
            return;
        }
        Logcat.e("reg:" + RegUtil.isNumeric(pwd1) + "/" + RegUtil.isNumericString(pwd1));
        ExecuteNext(mobile, pwd1, code);
    }

    private void ExecuteNext(String mobile, String pwd, String code) {
        /*Bundle mBundle = new Bundle();
        mBundle.putString("mobile", mobile);
        mBundle.putString("pwd", pwd);
        mBundle.putString("code", code);*/
        Map map = new HashMap();
        map.put("mobile", mobile);
        map.put("password", pwd);
        map.put("smsNumber", code);

        HttpClient.post(NetWorkConstant.USER_REISTER, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                final ResultBean mResultBean = JSON.parseObject(content, ResultBean.class);
                if (mResultBean.getCode().equals("1")) {
                    ToastUtil.showToast("注册成功");
                    finish();
                } else {
                    Logcat.e("" + mResultBean.getMsg());
                    ToastUtil.showToast(mResultBean.getMsg());
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                ToastUtil.showToast("网络错误，请重试");
            }
        });
        //startActivity(MineMakeInfoActivity.class,null);
//        startActivity(MineMakeInfoActivity.class, mBundle);
    }

    private void RegPhone(String mobile) {
        Map<String, String> map = new HashMap();
        map.put("mobile", mobile);
        final String str = map.toString();
        HttpClient.post(NetWorkConstant.GET_SMS_FOR_REGISTER, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
//                Logcat.i("\n接口地址：" + NetWorkConstant.GET_SMS_FOR_REGISTER + "\n提交的参数：" + str + "\n" + "返回码：" + statusCode + "\n" + "返回参数：" + content);
                ResultBean resultBean = JSON.parseObject(content, ResultBean.class);
                if (resultBean.getCode().equals("1")) {
                    if (mTimeUtils != null) {
                        mTimeUtils.RunTimer();
                    }
                    ToastUtil.showToast(resultBean.getMsg());
                } else {
                    ToastUtil.showToast(resultBean.getMsg());
                    if (mTimeUtils != null) {
                        mTimeUtils.StopTimer();
                    }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimeUtils.StopTimer();
    }

    /**
     * 显示错误提示，并获取焦点
     *
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputLayout textInputLayout, String error) {
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

    public void addLayoutListener(final View main, final View scroll) {
        //getViewTreeObserver().addOnGlobalLayoutListener
        main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                /*Rect rect = new Rect();
                //1、获取main在窗体的可视区域
                main.getWindowVisibleDisplayFrame(rect);
                //2、获取main在窗体的不可视区域高度，在键盘没有弹起时，main.getRootView().getHeight()调节度应该和rect.bottom高度一样
                int mainInvisibleHeight = main.getRootView().getHeight() - rect.bottom;
                int screenHeight = main.getRootView().getHeight();//屏幕高度
                //3、不可见区域大于屏幕本身高度的1/4：说明键盘弹起了
                if (mainInvisibleHeight > screenHeight / 4) {
                    int[] location = new int[2];
                    scroll.getLocationInWindow(location);
                    // 4､获取Scroll的窗体坐标，算出main需要滚动的高度
                    int srollHeight = (location[1] + scroll.getHeight()) - rect.bottom;
                    //5､让界面整体上移键盘的高度
                    main.scrollTo(0, srollHeight);
                } else {
                    //3、不可见区域小于屏幕高度1/4时,说明键盘隐藏了，把界面下移，移回到原有高度
                    main.scrollTo(0, 0);
                }*/
            }
        });
    }
}
