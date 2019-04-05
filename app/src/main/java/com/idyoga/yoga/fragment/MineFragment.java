package com.idyoga.yoga.fragment;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.course.AppointmentVideoCourseActivity;
import com.idyoga.yoga.activity.course.ConsultCourseActivity;
import com.idyoga.yoga.activity.course.CourseActivity;
import com.idyoga.yoga.activity.setting.FeedbackActivity;
import com.idyoga.yoga.activity.setting.SettingActivity;
import com.idyoga.yoga.activity.user.AccountActivity;
import com.idyoga.yoga.activity.card.MembershipCardActivity;
import com.idyoga.yoga.activity.user.FollowShopActivity;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.Constants;
import com.idyoga.yoga.comm.NetWorkConstant;
import com.idyoga.yoga.model.MineInfoBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.UserInfoBean;
import com.idyoga.yoga.utils.DateUtils;
import com.idyoga.yoga.utils.EnumUtil;
import com.idyoga.yoga.utils.GlideImgManager;
import com.idyoga.yoga.utils.LoginUtil;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.UserUtil;
import com.idyoga.yoga.utils.ViewUtil;
import com.idyoga.yoga.view.RoundDrawable;
import com.idyoga.yoga.view.ShadowLayout;
import com.idyoga.yoga.wxapi.WXEntryActivity;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

import static com.idyoga.yoga.utils.UserUtil.getUserBean;


/**
 * 作者 by K
 * 时间：on 2017/8/29 14:34
 * 邮箱 by  vip@devkit.vip
 * <p/>
 * 类用途：
 * 最后修改：
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.iv_hp)
    ImageView mIvHp;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.iv_sex)
    ImageView mIvSex;
    @BindView(R.id.tv_mobile)
    TextView mTvMobile;
    @BindView(R.id.rl_user_state)
    RelativeLayout mRlUserState;
    @BindView(R.id.tv_go_login)
    TextView mTvGoLogin;
    /*@BindView(R.id.rl_for_login)
    RelativeLayout mRlForLogin;*/
    @BindView(R.id.iv_qr_code)
    ImageView mIvQrCode;
    @BindView(R.id.rl_user_state_1)
    LinearLayout mRlUserState1;
    @BindView(R.id.rl_appointment_course)
    RelativeLayout mRlAppointmentCourse;
    @BindView(R.id.rl_consult_course)
    RelativeLayout mRlConsultCourse;
    @BindView(R.id.rl_video_course)
    RelativeLayout mRlVideoCourse;
    @BindView(R.id.rl_card_a)
    RelativeLayout mRlCardA;
    @BindView(R.id.rl_card_b)
    RelativeLayout mRlCardB;
    @BindView(R.id.rl_feedback)
    RelativeLayout mRlFeedback;
    @BindView(R.id.rl_setting)
    RelativeLayout mRlSetting;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.v_line1)
    View mLine1;
    @BindView(R.id.cl_view)
    ConstraintLayout mClView;
    @BindView(R.id.tv_course_name)
    TextView mCourseName;
    @BindView(R.id.tv_shop_name)
    TextView mShopName;
    @BindView(R.id.tv_course_time)
    TextView mCourseTime;
    @BindView(R.id.cv_user)
    ShadowLayout mCvUser;

    Unbinder unbinder;
    private UserInfoBean mUserInfoBean;
    private Bitmap mQrCode;
    private String Mobile, Token;
    private int UserId;
    protected IWXAPI mWxapi;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        //titleBar(mRlUserState1).
        mImmersionBar.flymeOSStatusBarFontColor("#ffffff").init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_user_account;
    }

    @Override
    protected void initData() {
        super.initData();
        Mobile = (String) SharedPreferencesUtils.getSP(mActivity, "Mobile", "");
        Token = (String) SharedPreferencesUtils.getSP(mActivity, "Token", "");
        //创建微信api并注册到微信
        mWxapi = WXAPIFactory.createWXAPI(getContext(), Constants.WX_APP_ID);
        mWxapi.registerApp(Constants.WX_APP_ID);
    }

    @Override
    protected void onVisible() {
        super.onVisible();

    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        initHp();
        mCvUser.setBackground(new RoundDrawable(ViewUtil.dp2px(getContext(),10),0.2f));
    }

    private void initHp() {
        Map map = new HashMap();
    }

    @Override
    public void onStart() {
        super.onStart();
        initViewData();
    }

    private void updateUserInfo(int id) {
        final Map map = new HashMap();
        String unionid = (String) SharedPreferencesUtils.getSP(getContext(), "unionid", "");
        map.put("userId", id + "");
        map.put("unionId", unionid);
        OkHttpUtils.get().url(ApiConstants.Urls.MINE_INFO_DATA)
                .params(map)
                .build()
                .execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Logcat.i("update user info onError：" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                Logcat.i("update user info response：" + response);
                final ResultBean mResultBean = JSON.parseObject(response, ResultBean.class);
                if (mResultBean.getCode().equals("1")) {
                    MineInfoBean userInfoBean = JSON.parseObject(mResultBean.getData(), MineInfoBean.class);
                    if (userInfoBean != null) {
                        Logcat.i("update user info success：" + "/\n" + userInfoBean.toString());
                        serViewData(userInfoBean);
                    }
                } else {
                    Logcat.e("" + mResultBean.getMsg());
                }
            }
        });
        /*final Map map = new HashMap();
        map.put("userId", id + "");
        map.put("token", token + "");//
        OkHttpUtils.get().url(NetWorkConstant.BASE_URL_2 + "/User/getUserInfoById").params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Logcat.i("update user info onError：" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                Logcat.i("update user info response：" + response);
                final ResultBean mResultBean = JSON.parseObject(response, ResultBean.class);
                if (mResultBean.getCode().equals("1")) {
                    UserInfoBean userInfoBean = JSON.parseObject(mResultBean.getData(), UserInfoBean.class);
                    SharedPreferencesUtils.setSP(mActivity, "UserId", userInfoBean.getId());
                    SharedPreferencesUtils.setSP(mActivity, "Token", userInfoBean.getToken());
                    UserUtil.setUserBean(mActivity, JSON.toJSONString(userInfoBean));
                    UserInfoBean userInfoBean1 = UserUtil.getUserBean(mActivity);
                    if (userInfoBean1 != null) {
                        Logcat.i("update user info success：" + "/\n" + userInfoBean1.toString());
                        serViewData(userInfoBean1);
                    }
                } else {
                    Logcat.e("" + mResultBean.getMsg());
                }
            }
        });*/
    }

    private void serViewData(MineInfoBean mineInfoBean) {
        if (mineInfoBean != null) {
            //.setVisibility(View.VISIBLE);
            mTvName.setVisibility(View.VISIBLE);
            mIvSex.setVisibility(View.VISIBLE);
            mTvMobile.setVisibility(View.VISIBLE);
            mTvGoLogin.setVisibility(View.GONE);
            if (mineInfoBean.getIsBindingWeChat() == 0){
                mView.setVisibility(View.VISIBLE);
                mTvLogin.setVisibility(View.VISIBLE);
            }else{
                mTvLogin.setVisibility(View.GONE);
                mView.setVisibility(View.GONE);
            }
            Logcat.i("MineInfoBean          123:" + mineInfoBean.toString());
            mTvName.setText(mineInfoBean.getName());
            mTvMobile.setText(mineInfoBean.getMobile());
            initUserSex(mineInfoBean.getSex());
            GlideImgManager.glideLoader(mActivity, mineInfoBean.getHead_pic(), mIvHp);//头像
            if (mineInfoBean.getAboutAppointment() != null){
                mClView.setVisibility(View.VISIBLE);
                mLine1.setVisibility(View.VISIBLE);
                mCourseName.setText(mineInfoBean.getAboutAppointment().getLessonName());
                mShopName.setText(mineInfoBean.getAboutAppointment().getShopName());
                mCourseTime.setText(DateUtils.timesTwo2(String.valueOf(mineInfoBean.getAboutAppointment().getStart_time())));
            }else{
                mClView.setVisibility(View.GONE);
                mLine1.setVisibility(View.GONE);
            }
        }else{
            mIvHp.setImageResource(R.drawable.img_06);
            mClView.setVisibility(View.GONE);
            mLine1.setVisibility(View.GONE);
            mTvName.setVisibility(View.GONE);
            mIvSex.setVisibility(View.GONE);
            mTvMobile.setVisibility(View.GONE);
//            mTvLogin.setVisibility(View.VISIBLE);
//            mView.setVisibility(View.VISIBLE);
            mTvGoLogin.setVisibility(View.VISIBLE);
        }
    }

    private void initViewData() {
        Logcat.i("initViewData:" + 1);
        UserId = (int) SharedPreferencesUtils.getSP(mActivity, "UserId", 0);
        mUserInfoBean = getUserBean(mActivity);
        if (mUserInfoBean != null) {
            Logcat.i("UserInfoBean:" + mUserInfoBean.toString());
            updateUserInfo(UserId);
            /*serViewData(mUserInfoBean);
            updateUserInfo(mUserInfoBean.getId(), mUserInfoBean.getToken());*/
        } else {
            mIvHp.setImageResource(R.drawable.img_06);
            //mRlUserState.setVisibility(View.GONE);
            mTvName.setVisibility(View.GONE);
            mIvSex.setVisibility(View.GONE);
            mTvMobile.setVisibility(View.GONE);
            mClView.setVisibility(View.GONE);
            mTvGoLogin.setVisibility(View.VISIBLE);
        }
    }

    public void initUserSex(int sex) {
        if (sex == 1) {
            mIvSex.setImageResource(R.drawable.ic_user_boy);
        } else {
            mIvSex.setImageResource(R.drawable.ic_user_girl);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (mUserInfoBean != null) {
                GlideImgManager.glideLoader(mActivity, mUserInfoBean.getAvatar_url(), mIvHp);//头像
            }
            if (mImmersionBar != null && mRlUserState1 != null) ;
            mImmersionBar.titleBar(mRlUserState1).flymeOSStatusBarFontColor("#ffffff").init();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mUserInfoBean != null) {
            GlideImgManager.glideLoader(mActivity, mUserInfoBean.getAvatar_url(), mIvHp);//头像
        }
        initViewData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @OnClick({R.id.iv_hp, R.id.rl_user_state, R.id.rl_appointment_course,
            R.id.rl_consult_course, R.id.rl_video_course, R.id.rl_card_a, R.id.rl_card_b,
            R.id.rl_feedback, R.id.rl_setting,R.id.tv_go_login,R.id.tv_login})//R.id.rl_for_login,
    public void onViewClicked(View view) {
        Bundle mBundle;
        switch (view.getId()) {
            case R.id.iv_hp:
                if (!LoginUtil.checkLogin(mActivity)) return;
                mBundle = new Bundle();
                startActivityWithExtras(AccountActivity.class, 900, mBundle);
                break;
            case R.id.rl_user_state:
                if (!LoginUtil.checkLogin(mActivity)) return;
                mBundle = new Bundle();
                startActivityWithExtras(AccountActivity.class, 900, mBundle);
            case R.id.tv_go_login://rl_for_login:
                LoginUtil.checkLogin(mActivity);
                break;
            case R.id.rl_appointment_course://我的预约
                if (!LoginUtil.checkLogin(mActivity)) return;
                mBundle = new Bundle();
                mBundle.putString("tag", "4");
                startActivityWithExtras(CourseActivity.class, mBundle);
                break;
            case R.id.rl_consult_course:
                //原来我的咨询入口改为我关注的瑜伽馆
                if (!LoginUtil.checkLogin(mActivity)) return;
                mBundle = new Bundle();
                startActivityWithExtras(FollowShopActivity.class,mBundle);
                /*mBundle = new Bundle();
                mBundle.putString("tag", "0");
                startActivityWithExtras(ConsultCourseActivity.class, mBundle);*/
                break;
            case R.id.rl_video_course:
                if (!LoginUtil.checkLogin(mActivity)) return;
                startActivityWithoutExtras(AppointmentVideoCourseActivity.class);
                break;
            case R.id.rl_card_a:
                if (!LoginUtil.checkLogin(mActivity)) return;
                Intent intent = new Intent(getActivity(),MembershipCardActivity.class);
                startActivityForResult(intent,10002);
                break;
            case R.id.rl_card_b:
                ToastUtil.showToast("敬请期待");
                break;
            case R.id.rl_setting:
                mBundle = new Bundle();
                startActivityWithExtras(SettingActivity.class, mBundle);
                break;
            case R.id.rl_feedback:
                if (!LoginUtil.checkLogin(mActivity)) return;
                startActivityWithoutExtras(FeedbackActivity.class);
                break;
            case R.id.tv_login:
                wxLogin();
                /*if (!LoginUtil.checkLogin(mActivity)) return;
                startActivityWithoutExtras(FeedbackActivity.class);*/
                break;
        }
    }

    private void wxLogin() {
        sendWxLoginRequest();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 900) {
            initViewData();
        }
    }

    protected void sendWxLoginRequest(){
        //先判断是否安装微信APP,按照微信的说法，目前移动应用上微信登录只提供原生的登录方式，需要用户安装微信客户端才能配合使用。
        if (!checkInstalledWx()){
            ToastUtil.showToast("未安装微信,请先安装微信");
            return ;
        }
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo";
        mWxapi.sendReq(req);
        //getActivity().finish();
    }

    //    判断是否安装微信APP,
    public boolean checkInstalledWx(){//isWXAppSupportAPI()
        if(mWxapi.isWXAppInstalled()) {
            return true;
        } else {
            final PackageManager packageManager = getContext().getPackageManager();// 获取packagemanager
            List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
            if (pinfo != null) {
                for (int i = 0; i < pinfo.size(); i++) {
                    String pn = pinfo.get(i).packageName;
                    if (pn.equalsIgnoreCase("com.tencent.mm")) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
