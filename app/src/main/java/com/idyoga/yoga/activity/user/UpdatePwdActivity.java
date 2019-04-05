package com.idyoga.yoga.activity.user;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.presenter.ICommonRequestPresenter;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.UserInfoBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.KeybordS;
import com.idyoga.yoga.utils.LoginUtil;
import com.idyoga.yoga.utils.TextTimeUtils;
import com.idyoga.yoga.utils.TimeUtils;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.UserUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.devkit.library.Logcat;
import vip.devkit.library.RegUtil;
import vip.devkit.library.StringUtil;

/**
 * 文 件 名: MineMakeInfoActivity
 * 创 建 人: By k
 * 创建日期: 2018/3/20 17:33
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class UpdatePwdActivity extends BaseActivity implements ICommonViewUi {
    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R.id.ll_title_right)
    LinearLayout mLlTitleRight;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;

    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.iv_switch)
    ImageView mIvSwitch;
    @BindView(R.id.et_smsCode)
    EditText mEtSmsCode;
    @BindView(R.id.btn_get_code)
    TextView mBtnGetCode;
    @BindView(R.id.tv_mobile)
    TextView mTvMobile;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.et_mobile)
    EditText mEtMobile;
    @BindView(R.id.ll_layout_mobile)
    RelativeLayout mLlLayoutMobile;
    @BindView(R.id.tv_code_tag)
    TextView mTvCodeTag;
    private String userId = "", mobile, password, smsCode;
    private UserInfoBean mUserInfoBean;
    private boolean isOpen;
    private TextTimeUtils mTimeUtils;
    private String tag;

    @Override
    protected void initData() {
        mUserInfoBean = UserUtil.getUserBean(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            tag = bundle.getString("tag");
        }
    }

    @Override
    protected ICommonRequestPresenter initICommonViewUi() {
        return iCommonRequestPresenter = new CommonRequestPresenterImpl(this, this);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).flymeOSStatusBarFontColor("#333333").init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_update_pwd;
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("设置密码");
        if (mUserInfoBean != null) {
            mTvTitleText.setText("修改密码");
            userId = mUserInfoBean.getId() + "";
            mLlLayoutMobile.setVisibility(View.GONE);
            mTvMobile.setText("您的登录手机号码是" + CommonUtils.replaceStr(3, 7, "****", mUserInfoBean.getMobile()) + "请点击“获取验证码”");
        }
        if (!StringUtil.isEmpty(tag) && tag.equals("login")) {
            mTvTitleText.setText("找回密码");
            mLlLayoutMobile.setVisibility(View.VISIBLE);
        } else {
            mLlLayoutMobile.setVisibility(View.GONE);
        }
        mTimeUtils = new TextTimeUtils(mBtnGetCode);
        mEtSmsCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String string = mEtSmsCode.getText().toString();

            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = mEtSmsCode.getText().toString();
                if (!TextUtils.isEmpty(string)){
                    mTvSubmit.setEnabled(true);
                    mTvSubmit.setBackground(getResources().getDrawable(R.drawable.login_bg));
                }else{
                    mTvSubmit.setEnabled(false);
                    mTvSubmit.setBackground(getResources().getDrawable(R.drawable.bg_default_01));
                }
            }
        });
    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.ll_title_back, R.id.iv_switch, R.id.btn_get_code, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.iv_switch:
                Logcat.i("isOpen:" + isOpen);
                if (isOpen) {
                    mEtPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    KeybordS.closeKeybord(mEtPwd, this);
                    mIvSwitch.setImageResource(R.drawable.show_pwd);
                    isOpen = false;
                } else {
                    mEtPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mIvSwitch.setImageResource(R.drawable.hide_pwd);
                    KeybordS.closeKeybord(mEtPwd, this);
                    isOpen = true;
                }
                break;
            case R.id.btn_get_code:
                if (!StringUtil.isEmpty(tag) && tag.equals("login")) {
                    mLlLayoutMobile.setVisibility(View.VISIBLE);
                    mobile = mEtMobile.getText().toString();
                    if (StringUtil.isEmpty(mobile)) {
                        ToastUtil.showToast("请输入手机号");
                        return;
                    }
                    if (!CommonUtils.isMobile(mobile)) {
                        ToastUtil.showToast("请输入正确的手机号");
                        return;
                    }
                    mTimeUtils.RunTimer();
                    toRequest(ApiConstants.EventTags.CARD_CHILD_ADD_GET_CODE);
                } else {
                    mobile = mUserInfoBean.getMobile();
                    if (StringUtil.isEmpty(mobile)) {
                        ToastUtil.showToast("请输入手机号");
                        return;
                    }
                    mLlLayoutMobile.setVisibility(View.GONE);
                    mTimeUtils.RunTimer();
                    toRequest(ApiConstants.EventTags.CARD_CHILD_ADD_GET_CODE);
                }
                break;
            case R.id.tv_submit:
                mobile = mEtMobile.getText().toString();
                smsCode = mEtSmsCode.getText().toString();
                password = mEtPwd.getText().toString();
                RegParams(smsCode, password);
                break;
        }
    }

    private void RegParams(String smsCode, String pwd) {
        if (StringUtil.isEmpty(password)) {
            ToastUtil.showToast("请输入密码");
            return;
        }
//        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        String regex = "^[0-9A-Za-z]{6,16}$";
        Logcat.e("密码正则：" + Pattern.matches(regex, pwd)+"/"+pwd);
        if (!Pattern.matches(regex, pwd)) {
            ToastUtil.showToast("请设置6-16位数字和字母组成的密码");
            return;
        }
        if (StringUtil.isEmpty(smsCode)) {
            ToastUtil.showToast("请输入验证码");
            return;
        }
        toRequest(ApiConstants.EventTags.USER_UPDATE_PWD);
    }

    @Override
    public void toRequest(int eventTag) {
        Map map = new HashMap();
        if (eventTag == ApiConstants.EventTags.USER_UPDATE_PWD) {
            map.put("password", password + "");
            map.put("smsNumber", smsCode + "");
            map.put("mobile", mobile + "");
            map.put("userId", userId + "");
            Logcat.i("修改密码提交的参数：" + map.toString());
            iCommonRequestPresenter.request(eventTag, this, ApiConstants.Urls.USER_UPDATE_PWD, map);
        } else if (eventTag == ApiConstants.EventTags.CARD_CHILD_ADD_GET_CODE) {
            map.put("mobile", mobile + "");
            iCommonRequestPresenter.request(eventTag, this, ApiConstants.Urls.CARD_CHILD_ADD_GET_CODE, map);
        }

    }

    @Override
    public void getRequestData(int eventTag, String result) {
        Logcat.i("eventTag:" + eventTag + "/" + result);
        dismissLoading();
        ResultBean bean = JSON.parseObject(result, ResultBean.class);
        if (bean.getCode().equals("1")) {
            if (eventTag == ApiConstants.EventTags.USER_UPDATE_PWD) {
                ToastUtil.showToast("修改成功");
                finish();
            } else if (eventTag == ApiConstants.EventTags.CARD_CHILD_ADD_GET_CODE) {
                ToastUtil.showToast("发送成功");
            }
        } else {
            ToastUtil.showToast(bean.getMsg());
            mTimeUtils.StopTimer();
        }

    }


    @Override
    public void onRequestFailureException(int eventTag, String msg) {

    }


    @Override
    protected void onDestroy() {
        mTimeUtils.StopTimer();
        super.onDestroy();
    }
}
