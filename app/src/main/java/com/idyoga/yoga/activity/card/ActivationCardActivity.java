package com.idyoga.yoga.activity.card;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.listener.DialogClickListener;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.UserInfoBean;
import com.idyoga.yoga.utils.LoginUtil;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.UserUtil;
import com.idyoga.yoga.view.dialog.CodeDialog;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;

public class ActivationCardActivity extends BaseActivity implements DialogClickListener {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_card_num)
    EditText etCardNum;
    @BindView(R.id.et_activ_code)
    EditText etActivCode;
    @BindView(R.id.btn_activ)
    RelativeLayout btnActiv;
    @BindView(R.id.ll_root)
    LinearLayout mLlRoot;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(ivBack).init();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_activation_card;
    }

    @Override
    protected void initView() {
        //让布局向上移来显示软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        /*FrameLayout content = (FrameLayout) findViewById(android.R.id.content);
        final View childAt = content.getChildAt(0);
        final int screenHeight = childAt.getRootView().getHeight();
        //界面出现变动都会调用这个监听事件
        childAt.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                KeyboardHeightUtil instance = KeyboardHeightUtil.getInstance();
                instance.getVisibleWindowHeight(screenHeight,childAt,btnActiv);
                Log.e("weilgu","--------------------");
                //getVisibleWindowHeight(childAt,btnActiv);
            }
        });*/
    }

    private void getVisibleWindowHeight(View childAt,View btnView) {
        Rect rect = new Rect();
        childAt.getWindowVisibleDisplayFrame(rect);
        //获取跟布局的高度
        int screenHeight = childAt.getRootView().getHeight();
        int mainInvisibleHeight = Math.abs(screenHeight - rect.bottom);
        if (mainInvisibleHeight > screenHeight / 4) {
            //获取view相对于父空间的坐标(左上角的坐标)
            int[] location = new int[2];
            btnView.getLocationInWindow(location);
            //控件相对于父控件的右下角的坐标,y轴
            int rightBtnY = location[1] + btnView.getHeight();
            int srollHeight = rightBtnY - rect.bottom;
            childAt.scrollTo(0,srollHeight);
        } else {
            childAt.scrollTo(0, 0);
        }
    }

    @Override
    protected void setListener() {
        etActivCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = etActivCode.getText().toString();
                if (!TextUtils.isEmpty(s1) && s.length() > 0) {
                    btnActiv.setEnabled(true);
                    btnActiv.setBackground(getResources().getDrawable(R.drawable.activ_btn_zbg));
                    etActivCode.setTextColor(Color.parseColor("#333333"));
                }else{
                    btnActiv.setBackground(getResources().getDrawable(R.drawable.activ_btn_hbg));
                    etActivCode.setTextColor(Color.parseColor("#999999"));
                    btnActiv.setEnabled(false);
                }
            }
        });
        etCardNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = etCardNum.getText().toString();
                if (!TextUtils.isEmpty(s1) && s.length() > 0) {
                        etCardNum.setTextColor(Color.parseColor("#333333"));
                }else{
                    etCardNum.setTextColor(Color.parseColor("#999999"));
                }
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.btn_activ})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                //                startActivityWithoutExtras(ActivationCardActivity.class);
                finish();
                break;
            case R.id.btn_activ:
                activationCard();
                break;
        }
    }

    private void activationCard() {
        String cardNum = etCardNum.getText().toString();
        String code = etActivCode.getText().toString();
        if (TextUtils.isEmpty(cardNum)) {
            CodeDialog cardNumDia = new CodeDialog(this, "请输入正确的卡号");
            cardNumDia.setClickListener(this);
            cardNumDia.show();
            return;
        }
        /*if (cardNum.length() != 14 || cardNum.length() != 13) {
            CodeDialog cardNumDia = new CodeDialog(this, "请输入正确的卡号");
            cardNumDia.show();
            return;
        }*/
        if (TextUtils.isEmpty(code)) {
            CodeDialog cardNumDia = new CodeDialog(this, "请输入正确的激活码");
            cardNumDia.show();
            return;
        }
        /*if (code.length() != 12) {
            CodeDialog cardNumDia = new CodeDialog(this, "请输入正确的激活码");
            cardNumDia.show();
            return;
        }*/
        submission(cardNum, code);

    }

    private void submission(String cardNum, String code) {
        //https://t.p.idyoga.cn/yoga_college/Yoga_college/activatePlatformCard
        if (!LoginUtil.checkLogin(this))
            return;
        UserInfoBean userBean = UserUtil.getUserBean(this);
        String url = ApiConstants.Urls.ACTIVA_CARD;
        HashMap<String, String> parMap = new HashMap<>();
        parMap.put("cardNum", cardNum);
        parMap.put("code", code);
        parMap.put("mobile", userBean.getMobile());
        parMap.put("userName", userBean.getUsername());
        parMap.put("sex", String.valueOf(userBean.getSex() == 0 ? 1 : userBean.getSex()));
        parMap.put("userId", String.valueOf(userBean.getId()));
        showLoading("正在激活");
        HttpClient.post(url, parMap, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                final ResultBean resultBean = JSON.parseObject(content, ResultBean.class);
                dismissLoading();
                if (resultBean != null) {
                    if ("1".equals(resultBean.getCode())) {
                        Intent intent = new Intent(ActivationCardActivity.this, ActivaSuccessActivity.class);
                        startActivityForResult(intent, 10005);
                    } else {
                        ToastUtil.showToast(resultBean.getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                dismissLoading();
                ToastUtil.showToast("激活失败");
            }
        });

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onSure() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10004 && data != null) {
            Intent intent = new Intent();
            setResult(11004, intent);
            finish();
        }
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }*/
}
