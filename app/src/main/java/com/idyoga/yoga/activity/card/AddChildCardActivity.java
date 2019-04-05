package com.idyoga.yoga.activity.card;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.NetWorkConstant;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.presenter.ICommonRequestPresenter;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.UserInfoBean;
import com.idyoga.yoga.model.address.AddressBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.TimeUtils;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.UserUtil;
import com.idyoga.yoga.utils.YogaViewUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

/**
 * 文 件 名: AddChildCardActivity
 * 创 建 人: By k
 * 创建日期: 2018/5/23 14:46
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class AddChildCardActivity extends BaseActivity implements ICommonViewUi {

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
    @BindView(R.id.tv_card_name)
    TextView mTvCardName;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.rg_view)
    RadioGroup mRgView;
    @BindView(R.id.tv_birthday)
    TextView mTvBirthday;
    @BindView(R.id.et_mobile)
    EditText mEtMobile;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.btn_get_code)
    Button mBtnGetCode;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.tv_type)
    TextView mTvType;
    //    @BindView(R.id.rg_type_view)
//    RadioGroup mRgTypeView;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_sex)
    TextView mTvSex;
    @BindView(R.id.tv_birthday_name)
    TextView mTvBirthdayName;
    @BindView(R.id.tv_mobile)
    TextView mTvMobile;
    @BindView(R.id.ll_layout_new_user)
    LinearLayout mLlLayoutNewUser;
    @BindView(R.id.tv_mobile_1)
    TextView mTvMobile1;
    @BindView(R.id.et_mobile_1)
    EditText mEtMobile1;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.ll_layout_user)
    LinearLayout mLlLayoutUser;
    @BindView(R.id.cb_new_user)
    CheckBox mCbNewUser;
    private String userId, shopId, token, membershipId = "";
    private int type = 2;//1：已经有账号用户 2：新用户
    private String name, smsCode, birthday, address, sex, mobile, cardName, pwd;
    private TimeUtils mTimeUtils;
    private OptionsPickerView pvOptions;
    private ArrayList<AddressBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<AddressBean.CityBean>> options2Items = new ArrayList<>();
    private static final int VIEW_FLAG = 1;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case VIEW_FLAG:
                    pvOptions.setPicker(options1Items, options2Items);
                    pvOptions.show();
                    break;
            }
            return false;
        }

    });

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
    protected void initData() {
        token = (String) SharedPreferencesUtils.getSP(this, "Token", "");
        shopId = (String) SharedPreferencesUtils.getSP(this, "shopId", "");
        UserInfoBean bean = UserUtil.getUserBean(this);
        if (bean != null) {
            mobile = UserUtil.getUserBean(this).getMobile();
        }
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            membershipId = bundle.getString("membershipId");
            cardName = bundle.getString("cardName");
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_crad_add_child;
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("添加附属卡");
        mCbNewUser.setChecked(true);
        mLlLayoutNewUser.setVisibility(View.VISIBLE);
        mLlLayoutUser.setVisibility(View.GONE);
        if (cardName != null) {
            mTvCardName.setText(cardName);
        }
        mTimeUtils = new TimeUtils(mBtnGetCode, "重获邀请码");
        initPvOptions();
    }

    private void initPvOptions() {
        pvOptions = new OptionsPickerView.Builder(this, mOptionsSelectListener)
                .setTitleText("地区选择")
                .setContentTextSize(20)
                .isCenterLabel(false)
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();
    }

    @Override
    protected void setListener() {
        mCbNewUser.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    type = 2;
                    mLlLayoutNewUser.setVisibility(View.VISIBLE);
                    mLlLayoutUser.setVisibility(View.GONE);
                } else {
                    type = 1;
                    mLlLayoutNewUser.setVisibility(View.GONE);
                    mLlLayoutUser.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void toRequest(int eventTag) {
        Map map = new HashMap();
        if (eventTag == ApiConstants.EventTags.CARD_CHILD_ADD) {
            map.put("type", type + "");
            map.put("token", token);
            map.put("membershipId", membershipId);
            map.put("mobile", mobile);
            map.put("smsNumber", smsCode);
            if (type == 2) {
                map.put("userName", name);
                map.put("sex", sex);
                map.put("birthday", birthday);
                map.put("password", pwd);
                map.put("address", "");
            }
            Logcat.i("提交的参数：" + map.toString() + "/" + ApiConstants.Urls.CARD_CHILD_ADD_V2);
            iCommonRequestPresenter.request(eventTag, this, ApiConstants.Urls.CARD_CHILD_ADD_V2, map);
        } else if (eventTag == ApiConstants.EventTags.CARD_CHILD_ADD_GET_CODE) {
            map.put("mobile", UserUtil.getUserBean(this).getMobile() + "");
            iCommonRequestPresenter.request(eventTag, this, ApiConstants.Urls.CARD_CHILD_ADD_GET_CODE, map);
        }
    }

    @Override
    public void getRequestData(int eventTag, String result) {
        Logcat.i("eventTag:" + eventTag + "/" + result);
        dismissLoading();
        ResultBean bean = JSON.parseObject(result, ResultBean.class);
        if (bean.getCode().equals("1")) {
            if (eventTag == ApiConstants.EventTags.CARD_CHILD_ADD) {
                ToastUtil.showToast(bean.getMsg());
                setResult(777);
                finish();
            } else if (eventTag == ApiConstants.EventTags.CARD_CHILD_ADD_GET_CODE) {
                ToastUtil.showToast("发送成功");
            }
        } else {
            ToastUtil.showToast(bean.getMsg());
        }
    }

    @Override
    public void onRequestFailureException(int eventTag, String msg) {

    }


    @OnClick({R.id.ll_title_back, R.id.tv_address, R.id.tv_birthday, R.id.btn_get_code, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.btn_get_code:
                break;
            case R.id.tv_address:
                if (pvOptions != null && pvOptions.isShowing()) {
                    pvOptions.dismiss();
                }
                getAddressData();
                break;
            case R.id.tv_birthday:
                showTimePicker();
                break;
            case R.id.tv_submit:
                name = mEtName.getText().toString().trim();
                address = mTvAddress.getText().toString().trim();
                birthday = mTvBirthday.getText().toString().trim();
                pwd = mEtPwd.getText().toString().trim();
                Logcat.e("type:" + type);
                if (type == 2) {
                    mobile = mEtMobile.getText().toString().trim();
                } else {
                    mobile = mEtMobile1.getText().toString().trim();
                }
                checkData(name, mobile, birthday, pwd);
                break;
        }
    }


    public void submitShowDialog() {
        final Dialog mDialog = new Dialog(this);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = View.inflate(mContext, R.layout.dialog_add_child_card, null);
        mDialog.setContentView(view);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(mDialog.getWindow().getAttributes());
        lp.width = YogaViewUtil.getScreenWidth(this) - YogaViewUtil.dp2px(this, 50);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mDialog.getWindow().setAttributes(lp);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
        final LinearLayout mMobilelayout = view.findViewById(R.id.ll_mobile_layout);
        final EditText editText = view.findViewById(R.id.et_smsCode);
        final TextView mTvMobile = view.findViewById(R.id.tv_mobile);
        Button btnGetCode = view.findViewById(R.id.btn_get_code);
        Button btnCancelAction = view.findViewById(R.id.btn_cancel_action);
        Button btnConfirmAction = view.findViewById(R.id.btn_confirm_action);
        mTimeUtils = new TimeUtils(btnGetCode);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_get_code:
                        mTimeUtils.RunTimer();
                        mMobilelayout.setVisibility(View.VISIBLE);
                        String mobile = CommonUtils.replaceStr(4, 7, "****", UserUtil.getUserBean(mContext).getMobile());
                        mTvMobile.setText(mobile);
                        toRequest(ApiConstants.EventTags.CARD_CHILD_ADD_GET_CODE);
                        break;
                    case R.id.btn_cancel_action:
                        mDialog.dismiss();
                        break;
                    case R.id.btn_confirm_action:
                        smsCode = editText.getText().toString();
                        if (StringUtil.isEmpty(smsCode)) {
                            ToastUtil.showToast("请填写验证码");
                            return;
                        } else {
                            toRequest(ApiConstants.EventTags.CARD_CHILD_ADD);
                            mDialog.dismiss();
                        }
                        break;
                }
            }
        };
        btnGetCode.setOnClickListener(onClickListener);
        btnCancelAction.setOnClickListener(onClickListener);
        btnConfirmAction.setOnClickListener(onClickListener);
    }


    private void checkData(String name, String mobile, String birthday, String pwd) {
        if (type == 2) {
            if (StringUtil.isEmpty(name)) {
                ToastUtil.showToast("请填写真实姓名");
                return;
            }
            if (StringUtil.isEmpty(mobile)) {
                ToastUtil.showToast("请填手机号");
                return;
            }
            if (!CommonUtils.isMobile(mobile)) {
                ToastUtil.showToast("请填正确的手机号码");
                return;
            }
            if (mRgView.getCheckedRadioButtonId() == -1) {
                ToastUtil.showToast("请选择性别");
                return;
            } else {
                RadioButton button = mRgView.findViewById(mRgView.getCheckedRadioButtonId());
                sex = button.getText().toString().trim();
            }
            if (StringUtil.isEmpty(birthday)) {
                ToastUtil.showToast("请选择生日");
                return;
            }
            if (StringUtil.isEmpty(pwd)) {
                ToastUtil.showToast("请输入密码");
                return;
            }
            String regex = "^[0-9A-Za-z]{6,16}$";
            Logcat.e("密码正则：" + Pattern.matches(regex, pwd) + "/" + pwd);
            if (!Pattern.matches(regex, pwd)) {
                ToastUtil.showToast("请设置6-16位数字和字母组成的密码");
                return;
            }
        } else {
            if (StringUtil.isEmpty(mobile)) {
                ToastUtil.showToast("请填手机号");
                return;
            }
            if (!CommonUtils.isMobile(mobile)) {
                ToastUtil.showToast("请填正确的手机号码");
                return;
            }
        }
        submitShowDialog();
    }

    /**
     * 获取地址数据
     */
    private void getAddressData() {
        if (options1Items.size() > 0) {
            options1Items.clear();
        }
        if (options2Items.size() > 0) {
            options2Items.clear();
        }
        if (!StringUtil.isEmpty((String) SharedPreferencesUtils.getSP(this, "address", ""))) {
            String addressJson = (String) SharedPreferencesUtils.getSP(this, "address", "");
            List<AddressBean> addressBeanList = JSON.parseArray(addressJson, AddressBean.class);
            for (int i = 0; i < addressBeanList.size(); i++) {
                options1Items.add(addressBeanList.get(i));
                ArrayList<AddressBean.CityBean> options2Item = new ArrayList<>();
                options2Item.addAll(options1Items.get(i).getCity());
                options2Items.add(options2Item);
            }
            if (options1Items.size() > 0) {
                Message msg = new Message();
                msg.what = VIEW_FLAG;
                msg.obj = new Bundle();
                mHandler.sendMessage(msg);
            } else {
                Logcat.e("地址集合小于1");
            }
        } else {
            showLoading(null, true);
            HttpClient.get(NetWorkConstant.GET_ADDRESS, null, new HttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, String content) {
                    super.onSuccess(statusCode, content);
                    Logcat.i("\n提交的参数：" + 0 + "\n" + "返回码：" + statusCode + "\n" + "返回参数：" + content);
                    if (statusCode == 200) {
                        ResultBean mResultBean = JSON.parseObject(content, ResultBean.class);
                        List<AddressBean> addressBeanList = JSON.parseArray(mResultBean.getData(), AddressBean.class);
                        for (int i = 0; i < addressBeanList.size(); i++) {
                            options1Items.add(addressBeanList.get(i));
                            ArrayList<AddressBean.CityBean> options2Item = new ArrayList<>();
                            options2Item.addAll(options1Items.get(i).getCity());
                            options2Items.add(options2Item);
                        }
                        if (options1Items.size() > 0) {
                            Message msg = new Message();
                            msg.what = VIEW_FLAG;
                            msg.obj = new Bundle();
                            mHandler.sendMessage(msg);
                        } else {
                            Logcat.e("地址集合小于1");
                        }
                    }
                }

                @Override
                public void onFailure(Request request, IOException e) {
                    super.onFailure(request, e);
                }
            });
            dismissLoading();
        }
    }

    private OptionsPickerView.OnOptionsSelectListener mOptionsSelectListener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            String address = options1Items.get(options1).getPickerViewText() + "  "
                    + options2Items.get(options1).get(options2).getName();
            Logcat.i("选择的地址：" + address);
            mTvAddress.setText(address);

        }
    };


    /**
     * 时间选择
     */
    private void showTimePicker() {
        Calendar calendar = Calendar.getInstance();
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        selectedDate.set(2001, 1, 1);
        startDate.set(1950, 1, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH - 1), calendar.get(Calendar.DATE));
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Logcat.i("选择的日期：" + date + "/");
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                String result = sd.format(date);
                mTvBirthday.setText("" + result);
            }
        }).setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定.build();
//                .isDialog(true)
                .setTitleText("生日选择")
                .setType(new boolean[]{true, true, true, false, false, false})
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();
        pvTime.show();
    }

}