package com.idyoga.yoga.activity.user;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.TimeUtils;
import com.idyoga.yoga.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

/**
 * 文 件 名: ForgetPwdActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/3/21
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ForgetPwdActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.et_handset)
    EditText mEtHandset;
    @BindView(R.id.et_in_pwd_1)
    EditText mEtInPwd1;
    @BindView(R.id.iv_switch_1)
    ImageView mIvSwitch1;
    @BindView(R.id.ll_switch_1)
    LinearLayout mLlSwitch1;
    @BindView(R.id.et_in_pwd_2)
    EditText mEtInPwd2;
    @BindView(R.id.iv_switch_2)
    ImageView mIvSwitch2;
    @BindView(R.id.ll_switch_2)
    LinearLayout mLlSwitch2;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.btn_get_code)
    Button mBtnGetCode;
    @BindView(R.id.btn_submit)
    Button mBtnSubmit;
    int flag_1 = 0, flag_2 = 0;
    TimeUtils mTimeUtils;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    String UserId, Token;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mIvBack).flymeOSStatusBarFontColor("#333333").init();
    }

    @Override
    protected void initData() {
        UserId = (String) SharedPreferencesUtils.getSP(this, "UserId","");
        Token = (String) SharedPreferencesUtils.getSP(this, "Token","");
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_register;
    }

    @Override
    protected void initView() {
        mTvTitle.setText("重置密码");
        mTimeUtils = new TimeUtils(mBtnGetCode);
    }

    @Override
    protected void setListener() {

    }


    @OnClick({R.id.iv_back, R.id.ll_switch_1, R.id.ll_switch_2, R.id.btn_get_code, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_switch_1:
                if (flag_1 == 0) {
                    mIvSwitch1.setImageResource(R.drawable.ic_user_pwd_open);
                    mEtInPwd1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else if (flag_1 == 1) {
                    mIvSwitch1.setImageResource(R.drawable.ic_user_pwd_close);
                    mEtInPwd1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                flag_1 = (flag_1 + 1) % 2;
                break;
            case R.id.ll_switch_2:
                if (flag_2 == 0) {
                    mIvSwitch2.setImageResource(R.drawable.ic_user_pwd_open);
                    mEtInPwd2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else if (flag_2 == 1) {
                    mIvSwitch2.setImageResource(R.drawable.ic_user_pwd_close);
                    mEtInPwd2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                flag_2 = (flag_2 + 1) % 2;
                break;
            case R.id.btn_get_code:
                RegPhone(mEtHandset.getText().toString().trim());
                break;
            case R.id.btn_submit:
                RegData(UserId, Token, mEtInPwd1.getText().toString(), mEtInPwd2.getText().toString());
                break;
        }
    }

    private void RegData(String userId, String token, String s, String s1) {
        if (!s.equals(s)) ToastUtil.showToast("两次密码不一致");
        if (CommonUtils.isBlank(userId)||CommonUtils.isBlank(token)){
            Logcat.i("");
        }

    }

    private void RegPhone(String tell) {
        if (!StringUtil.isEmpty(tell)) {
            if (!CommonUtils.isMobile(tell)) {
                mTimeUtils.RunTimer();
                getRegisterCode(tell);
            } else {
                ToastUtil.showToast("请填正确的手机号码");
            }
        } else {
            ToastUtil.showToast("请填写手机号码");
        }
    }

    private void getRegisterCode(String tell) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimeUtils.StopTimer();
    }
}
