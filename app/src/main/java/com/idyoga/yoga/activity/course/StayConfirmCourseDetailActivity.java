package com.idyoga.yoga.activity.course;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.lbs.LbsActivity;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.lbs.LbsUtil;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.TeamCourseDetailBean;
import com.idyoga.yoga.model.UserCourseBean;
import com.idyoga.yoga.model.UserInfoBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.UserUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import okhttp3.Request;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;
import vip.devkit.view.common.suklib.view.FlowLayout.TagFlowLayout;

/**
 * 文 件 名: StayConfirmCourseDetailActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/13
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class StayConfirmCourseDetailActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.iv_img)
    ImageView mIvImg;
    @BindView(R.id.tv_course_name)
    TextView mTvCourseName;
    @BindView(R.id.tv_course_date)
    TextView mTvCourseDate;
    @BindView(R.id.tv_course_time)
    TextView mTvCourseTime;
    @BindView(R.id.rl_itemView)
    RelativeLayout mRlItemView;
    @BindView(R.id.tag_view)
    TagFlowLayout mTagView;
    @BindView(R.id.ll_layout_item)
    LinearLayout mLlLayoutItem;
    @BindView(R.id.tv_shop_name_tag)
    TextView mTvShopNameTag;
    @BindView(R.id.tv_shop_name)
    TextView mTvShopName;
    @BindView(R.id.iv_call)
    ImageView mIvCall;
    @BindView(R.id.tv_shop_address)
    TextView mTvShopAddress;
    @BindView(R.id.iv_address)
    ImageView mIvAddress;
    @BindView(R.id.iv_qr_code)
    ImageView mIvQrCode;
    @BindView(R.id.ll_course_qrCode)
    LinearLayout mLlCourseQrCode;
    @BindView(R.id.ll_success_layout)
    LinearLayout mLlSuccessLayout;
    @BindView(R.id.tv_cancel)
    TextView mTvCancel;
    @BindView(R.id.tv_state)
    TextView mTvState;
    @BindView(R.id.tv_sign_state)
    TextView mTvSignState;
    private UserCourseBean mUserCourseBean;
    private String UserId, Token, ShopId;
    private int stateType;
    private UserInfoBean mUserInfoBean;
    private TeamCourseDetailBean mCourseDetailBean;

    @Override
    protected void initData() {
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mUserCourseBean = mBundle.getParcelable("bean");
            stateType = mBundle.getInt("stateType");
        }
        mUserInfoBean = UserUtil.getUserBean(this);
        ShopId = (String) SharedPreferencesUtils.getSP(this, "shopId", "");
        getDetail(mUserCourseBean.getCourseId() + "", mUserCourseBean.getShop_id() + "");
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_stay_confim_course;
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("我预约的课程");
        if (stateType == 1) {
            mTvState.setText("已预约");
            mTvState.setBackgroundColor(Color.parseColor("#b96bb0"));
        } else if (stateType == 2) {
            mTvState.setText("已取消");
            mLlCourseQrCode.setVisibility(View.GONE);
            mTvCancel.setVisibility(View.GONE);
            mTvState.setBackgroundColor(Color.parseColor("#989898"));
        } else if (stateType == 3) {
            mTvState.setText("已结束");
            mTvSignState.setText("已上课");
            mIvQrCode.setImageResource(R.drawable.icon_course_study);
            mLlCourseQrCode.setVisibility(View.VISIBLE);
            mTvCancel.setVisibility(View.GONE);
            mTvState.setBackgroundColor(Color.parseColor("#989898"));
        }
        if (mUserCourseBean != null) {
            mTvTitleText.setText(mUserCourseBean.getLessonName());
            mTvCourseName.setText(mUserCourseBean.getLessonName());
            mTvCourseDate.setText("日期：" + CommonUtils.getDateStringByTimeSTamp(mUserCourseBean.getStart(), "yyyy-MM-dd"));
            mTvCourseTime.setText("时间：" + CommonUtils.getDateStringByTimeSTamp(mUserCourseBean.getStart(), "HH:mm") + "-"
                    + CommonUtils.getDateStringByTimeSTamp(mUserCourseBean.getEnd(), "HH:mm"));
            mTvShopName.setText(mUserCourseBean.getShopName());
            mTvShopAddress.setText("地址：" + mUserCourseBean.getShopAddress());
            Glide.with(this).load(mUserCourseBean.getLessonImg())
                    .placeholder(R.drawable.img_course).error(R.drawable.img_course).into(mIvImg);
        }
    }

    private void initViewData(TeamCourseDetailBean courseDetailBean) {
        if (courseDetailBean != null) {
            if (!StringUtil.isEmpty(courseDetailBean.getCourseDetail().getLessonName())) {
                mTvCourseName.setText(courseDetailBean.getCourseDetail().getLessonName() + "");
            }
            mTvCourseDate.setText("日期：" + CommonUtils.getDateStringByTimeSTamp((long) courseDetailBean.getCourseDetail().getStart(), "yyyy-MM-dd"));
            mTvCourseTime.setText("时间：" + CommonUtils.getDateStringByTimeSTamp((long) courseDetailBean.getCourseDetail().getStart(), "HH:mm") + "-"
                    + CommonUtils.getDateStringByTimeSTamp((long) courseDetailBean.getCourseDetail().getEnd(), "HH:mm"));
            mTvShopName.setText(courseDetailBean.getParentShopInfo().getName());
            String longitude = (String) SharedPreferencesUtils.getSP(mContext, "longitude", "");
            String latitude = (String) SharedPreferencesUtils.getSP(mContext, "latitude", "");
            String distances = LbsUtil.getDistance(
                    Double.valueOf(longitude),
                    Double.valueOf(latitude),
                    Double.valueOf(courseDetailBean.getParentShopInfo().getLongitude()),
                    Double.valueOf(courseDetailBean.getParentShopInfo().getLatitude()));
            String distance = "(距您" + distances + ")";
            mTvShopAddress.setText("地址：" + courseDetailBean.getParentShopInfo().getAddress() + distance);
            Glide.with(this).load(courseDetailBean.getCourseDetail().getLessonImg()).placeholder(R.drawable.img_course)
                    .error(R.drawable.img_course).into(mIvImg);
            long totalMilliSeconds = System.currentTimeMillis();
            long totalSeconds = totalMilliSeconds / 1000;
            if (stateType == 1) {
                Glide.with(this)
                        .load(courseDetailBean.getUserInfo().getQrCodeUrl()).placeholder(R.drawable.img_course)
                        .error(R.drawable.img_course).into(mIvQrCode);
                if (courseDetailBean.getUserInfo().getIsSign() == 1) {
                    mTvSignState.setText("已签到");
                    mIvQrCode.setImageResource(R.drawable.icon_course_study);
                    mTvCancel.setVisibility(View.GONE);
                    mTvState.setText("已签到");
                } else {
                    Glide.with(this)
                            .load(courseDetailBean.getUserInfo().getQrCodeUrl()).placeholder(R.drawable.img_course)
                            .error(R.drawable.img_course).into(mIvQrCode);
                }
                if (courseDetailBean.getCourseDetail().getEnd() < totalSeconds) {
                    mTvState.setText("已结束");
                    mLlSuccessLayout.setVisibility(View.VISIBLE);
                    mLlCourseQrCode.setVisibility(View.GONE);
                    mTvCancel.setVisibility(View.GONE);
                }
            } else if (stateType == 2) {
            } else if (stateType == 3) {
                if (courseDetailBean.getUserInfo().getIsSign()==1){
                    mTvState.setText("已上课");
                    mIvQrCode.setImageResource(R.drawable.icon_course_study);
                }else {
                    mTvSignState.setText("已过期");
                    mTvState.setText("已过期");
                    mIvQrCode.setImageResource(R.drawable.icon_course_study);
                }
            }
        }
    }

    @Override
    protected void setListener() {
    }

    /**
     * @param status
     * @param orderId
     * @param shopId
     */
    private void cancelCourse(int status, String orderId, String shopId) {
        Map map = new HashMap();
        map.put("token", UserUtil.getUserBean(this).getToken() + "");
        map.put("appointmentId", orderId + "");
        map.put("shopId", shopId + "");
        String url = ApiConstants.Urls.USER_CANCEL_COURSE_V2_1;
        if (status == 1) {
            map.put("isUser", "1");
            url = ApiConstants.Urls.USER_CANCEL_COURSE_V2_2;
        }
        Logcat.e("提交的参数：" + map.toString());
        HttpClient.post(url, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                dismissLoading();
                Logcat.e("取消预定课程：" + content);
                ResultBean resultBean = JSON.parseObject(content, ResultBean.class);
                if (resultBean.getCode().equals("1")) {
                    ToastUtil.showToast("取消成功");
                    EventBus.getDefault().post(new PostResult("600", "刷新课程内容"));
                    showLoading("刷新中...");
//                    getData(courseId);
                } else {
                    ToastUtil.showToast(resultBean.getMsg());
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {

            }
        });
    }

    private void getDetail(String courseId, String shopId) {
        Map map = new HashMap();
        map.put("token", mUserInfoBean.getToken() + "");
        map.put("courseId", courseId + "");
        map.put("shopId", shopId + "");
        Logcat.e("提交的参数：" + map.toString());
        HttpClient.post(ApiConstants.Urls.USER_COURSE_DETAIL, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                Logcat.e("课程详情：" + content);
                ResultBean resultBean = JSON.parseObject(content, ResultBean.class);
                if (resultBean.getCode().equals("1")) {
                    mCourseDetailBean = JSON.parseObject(resultBean.getData(), TeamCourseDetailBean.class);
                    if (mCourseDetailBean != null) {
                        initViewData(mCourseDetailBean);
                    }
                } else {
                    ToastUtil.showToast(resultBean.getMsg());
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {

            }
        });
    }

    /**
     * 请求CAMERA权限码
     */
    public static final int REQUEST_CAMERA_PERM = 101;

    @AfterPermissionGranted(REQUEST_CAMERA_PERM)
    @OnClick({R.id.ll_title_back, R.id.iv_call, R.id.iv_address, R.id.tv_cancel, R.id.tv_state})
    public void onViewClicked(View view) {
        Bundle bundle;
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.iv_call:
                if (mCourseDetailBean != null) {
                    initPermission();
                    if (!StringUtil.isEmpty(mCourseDetailBean.getParentShopInfo().getMobile())) {
                        callPhone(mCourseDetailBean.getParentShopInfo().getMobile());
                    } else {
                        ToastUtil.showToast("暂未提供联系电话，请联系客服");
                    }
                }
                break;
            case R.id.iv_address:
                bundle = new Bundle();
                if (mCourseDetailBean != null && mCourseDetailBean.getParentShopInfo() != null) {
                    bundle.putString("address", mCourseDetailBean.getParentShopInfo().getAddress());
                    bundle.putString("latitude", mCourseDetailBean.getParentShopInfo().getLatitude());
                    bundle.putString("longitude", mCourseDetailBean.getParentShopInfo().getLongitude());
                    bundle.putString("shopName",mCourseDetailBean.getParentShopInfo().getName());
                    bundle.putString("shopAddress",mCourseDetailBean.getParentShopInfo().getAddress());
                    startActivity(LbsActivity.class, bundle);
                }
                break;
            case R.id.tv_cancel:
//                showLoading("取消处理中...");
//                int status = mCourseDetailBean.getCourseAppList().get();
//                cancelCourse(status, mDetailBean.getAppointmentInfo().getMembership_id() + "", mDetailBean.getAppointmentInfo()
//                        .getShop_id() + "");
                break;
            case R.id.tv_state:
                break;
        }
    }

    /**
     * 请求CALL权限码
     */
    public static final int REQUEST_CALL_PERM = 101;

    @AfterPermissionGranted(REQUEST_CALL_PERM)
    private void callPhone(String mobile) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Intent phoneIntent = new Intent("android.intent.action.CALL",
                    Uri.parse("tel:" + mobile));
            startActivity(phoneIntent);
        } else {
            if (EasyPermissions.hasPermissions(this, Manifest.permission.CALL_PHONE)) {
                Intent phoneIntent = new Intent("android.intent.action.CALL",
                        Uri.parse("tel:" + mobile));
                startActivity(phoneIntent);
            }
        }
    }
    private static String[] permissions = new String[]{Manifest.permission.CALL_PHONE};
    void initPermission() {
        for (String permission : permissions) {
            int checkSelfPermission = ContextCompat.checkSelfPermission(this, permission);
            if (checkSelfPermission == PackageManager.PERMISSION_DENIED) {//未申请
                ActivityCompat.requestPermissions(this, permissions, 100);
                Logcat.i("initPermission:1");
            }
        }
        Logcat.i("initPermission:2");
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Logcat.i("执行onPermissionsGranted()...");
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        ToastUtil.showToast("去开启APP拨打电话权限");
    }

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        if (postResult.getTag().equals("signIn")) {
            Logcat.i("签到广播");
            mTvSignState.setText("已签到");
            mIvQrCode.setImageResource(R.drawable.icon_course_study);
            mTvCancel.setVisibility(View.GONE);
            mTvState.setText("已签到");
        }
    }
}
