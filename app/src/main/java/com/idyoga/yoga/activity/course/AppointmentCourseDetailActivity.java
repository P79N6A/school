package com.idyoga.yoga.activity.course;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.home.ExperienceLessonAppointmentActivity;
import com.idyoga.yoga.activity.lbs.LbsActivity;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.Constants;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.model.AppointmentResultBean;
import com.idyoga.yoga.model.ExperienceAppointmentCourseOrder;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.ResultIdBean;
import com.idyoga.yoga.model.TeamCourseDetailBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.DateUtils;
import com.idyoga.yoga.utils.JsonUtil;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.UserUtil;
import com.idyoga.yoga.view.YogaLayoutManager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Request;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import vip.devkit.library.Logcat;
import vip.devkit.library.StringUtil;

/**
 * 文 件 名: AppointmentCourseDetailActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/7/19
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 * 预约详情
 */
public class AppointmentCourseDetailActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.tv_state)
    TextView mTvState;
    @BindView(R.id.iv_state)
    ImageView mIvState;
    @BindView(R.id.tv_course_name)
    TextView mTvCourseName;
    @BindView(R.id.tv_course_time)
    TextView mTvCourseTime;
    @BindView(R.id.tv_shop_name)
    TextView mTvShopName;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.ll_service)
    LinearLayout mLlService;
    @BindView(R.id.sv_view)
    NestedScrollView mSvView;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.tv_follow_title)
    TextView mTvFollowTitle;
    @BindView(R.id.tv_follow)
    TextView mTvFollow;
    @BindView(R.id.tv_result_state)
    TextView mTvResultState;
    @BindView(R.id.tv_result_time)
    TextView mTvResultTime;
    @BindView(R.id.tv_start_state)
    TextView mTvStartState;
    @BindView(R.id.tv_start_time)
    TextView mTvStartTime;
    @BindView(R.id.tv_time_type)
    TextView mTvTimeType;
    @BindView(R.id.ll_start_state)
    RelativeLayout mLlStartState;
    @BindView(R.id.v_state)
    View mVState;
    @BindView(R.id.iv_result_state_right)
    ImageView mIvResultStateRight;
    @BindView(R.id.rlv_head)
    RelativeLayout mRlvHead;

    @BindView(R.id.srl_Refresh)
    SwipeRefreshLayout mSrlRefresh;

    private String courseId, shopId, appointmentId;
    private String mUserId, mLessonId, mLessonType;
//    private ExperienceAppointmentCourseOrder mDetailBean;
    private AppointmentResultBean mDetailBean;
    private CountDownTimer mTimer;
    private String stateType = "";

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mUserId = bundle.getString("userId");
            mLessonId = bundle.getString("lessonId");
            mLessonType = bundle.getString("lessonType"); //0 为未排课 1 未已排课
            /*stateType = bundle.getString("stateType");
            shopId = bundle.getString("shopId");
            courseId = bundle.getString("courseId");
            appointmentId = bundle.getString("appointmentId");*/
            Logcat.i("获取到的值：" + shopId + "/" + courseId + "/" + appointmentId + "/" + stateType);
            getData(courseId);
        }
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).init();
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(mSvView);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_appnintment_course_detail;
    }

    @Override
    protected void initView() {
        //mTvTitleText.setText("预约详情");
        mLlCommonLayout.setBackgroundColor(getResources().getColor(R.color.white));
        mLayoutManager.showLoading();
    }

    /**
     * @param courseDetailBean
     */
    private void initViewData(AppointmentResultBean courseDetailBean) {
        if (courseDetailBean == null) {
            mLayoutManager.showEmpty();
            return;
        }
        this.mDetailBean = courseDetailBean;
        this.mTvCourseName.setText(mDetailBean.getLessonName());
        mTvCourseTime.setText(DateUtils.timet(String.valueOf(mDetailBean.getStart_time())));
        mTvShopName.setText(mDetailBean.getShopName());
        mTvAddress.setText(mDetailBean.getAddress());
        AppointmentResultBean.CopywritingBean.BottomBean bottom = mDetailBean.getCopywriting().getBottom();
        //注意事项
        List<String> texts = bottom.getText();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < texts.size(); i++) {
            sb.append(texts.get(i));
            if (i < texts.size() -1){
                sb.append("\n");
            }
        }
        mTvFollow.setText(sb.toString());
        //订单状态
        List<String> tops = mDetailBean.getCopywriting().getTop().getText();
            StringBuilder top = new StringBuilder();
            for (int i = 0; i < tops.size(); i++) {
                top.append(tops.get(i).trim());
            }
        String content = top.toString();
        int lineMaxLength = 11;
        StringBuilder builder = new StringBuilder();
        interceptStr(content, lineMaxLength, builder);
        mTvState.setText(builder.toString());
        // 1 为已排课  2 为未排课
        if (mDetailBean.getLessonType() == 2){
            mTvTimeType.setText("自选时间");
        }else{
            mTvTimeType.setText("开课时间");
        }
        List<AppointmentResultBean.TaillistBean> taillist = mDetailBean.getTaillist();
        mIvResultStateRight.setTag(false);
        if (taillist != null){
            if (taillist.size() <= 1 && taillist.size() > 0){
                AppointmentResultBean.TaillistBean bean = taillist.get(0);
                mLlStartState.setVisibility(View.GONE);
                mVState.setVisibility(View.GONE);
                mIvResultStateRight.setVisibility(View.INVISIBLE);
                mTvResultState.setText(bean.getContent());
                mTvResultTime.setText(DateUtils.timet(String.valueOf(bean.getTime())));
            }else{
                mLlStartState.setVisibility(View.GONE);
                mVState.setVisibility(View.GONE);
                AppointmentResultBean.TaillistBean bean = taillist.get(taillist.size() - 1);
                AppointmentResultBean.TaillistBean startBean = taillist.get(0);
                mTvResultState.setText(bean.getContent());
                mTvResultTime.setText(DateUtils.timet(String.valueOf(bean.getTime())));
                mTvStartState.setText(startBean.getContent());
                mTvStartTime.setText(DateUtils.timet(String.valueOf(startBean.getTime())));
                mIvResultStateRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean show = (boolean) mIvResultStateRight.getTag();
                        if (!show){
                            mIvResultStateRight.setImageDrawable(getResources().getDrawable(R.drawable.right_up));
                            mLlStartState.setVisibility(View.VISIBLE);
                            mVState.setVisibility(View.VISIBLE);
                            mIvResultStateRight.setTag(true);
                        }else{
                            mIvResultStateRight.setImageDrawable(getResources().getDrawable(R.drawable.right_down));
                            mLlStartState.setVisibility(View.GONE);
                            mVState.setVisibility(View.GONE);
                            mIvResultStateRight.setTag(false);
                        }
                    }
                });
            }
        }
        // confirm_status 订单确认状态 0：未确认 1：已确认 2：店铺拒绝 3：用户取消 4：自动取消

        //cancel_source 取消来源 0：未取消 1：用户取消 2：店铺取消 3：客服取消 4：自动取消
        //state_code 0处理超时，1待确定，2待上课，3已完成，4已取消
        String status = mDetailBean.getState_code();
        if ("1".equals(status)) { /**待确定*/
            mTvSubmit.setText("取消预约");
            mTvSubmit.setBackground(getResources().getDrawable(R.color.text_color_5));
            mRlvHead.setBackground(getResources().getDrawable(R.color.rlv_head));
            mTvSubmit.setTextColor(getResources().getColor(R.color.white));
            mIvState.setImageDrawable(getResources().getDrawable(R.drawable.course_icon_success_default));
            mTvSubmit.setVisibility(View.VISIBLE);
        } else if (status.equals("0")) {/**处理超时*/
            mTvSubmit.setText("再次预约");
            mTvSubmit.setBackground(getResources().getDrawable(R.color.again_appiontment));
            mIvState.setImageDrawable(getResources().getDrawable(R.drawable.course_icon_cancel_default));
            mRlvHead.setBackground(getResources().getDrawable(R.color.rlv_head_h));
            mTvSubmit.setTextColor(getResources().getColor(R.color.white));
            mTvSubmit.setVisibility(View.VISIBLE);
        } else if ("2".equals(status)) { //待上课
            String qrUrl = mDetailBean.getQrUrl();//二维码
            Glide.with(this)
                    .load(qrUrl)
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            mIvState.setImageBitmap(resource);
                        }
                    });
            mTvSubmit.setText("取消预约");
            mTvSubmit.setBackground(getResources().getDrawable(R.color.text_color_5));
            mRlvHead.setBackground(getResources().getDrawable(R.color.rlv_head));
            mTvSubmit.setTextColor(getResources().getColor(R.color.white));
            mTvSubmit.setVisibility(View.VISIBLE);
        } else if ("3".equals(status)) { //已完成
            mTvSubmit.setBackground(getResources().getDrawable(R.color.text_color_5));
            mRlvHead.setBackground(getResources().getDrawable(R.color.rlv_head));
            mTvSubmit.setTextColor(getResources().getColor(R.color.white));
            mIvState.setImageDrawable(getResources().getDrawable(R.drawable.course_icon_success_default));
            mTvSubmit.setText("再次预约");
            mTvSubmit.setVisibility(View.VISIBLE);
        } else if ("4".equals(status)) { //已取消
            if (mDetailBean.getCancel_source() == 1){
                mTvSubmit.setBackground(getResources().getDrawable(R.color.text_color_5));
                mRlvHead.setBackground(getResources().getDrawable(R.color.rlv_head));
                mTvSubmit.setTextColor(getResources().getColor(R.color.white));
                mIvState.setImageDrawable(getResources().getDrawable(R.drawable.course_icon_success_default));
            }else{
                mTvSubmit.setBackground(getResources().getDrawable(R.color.again_appiontment));
                mIvState.setImageDrawable(getResources().getDrawable(R.drawable.course_icon_cancel_default));
                mRlvHead.setBackground(getResources().getDrawable(R.color.rlv_head_h));
                mTvSubmit.setTextColor(getResources().getColor(R.color.white));
            }
            mTvSubmit.setText("再次预约");
            mTvSubmit.setVisibility(View.GONE);
        }
        mLayoutManager.showContent();
    }

    //截取提示文案字符串
    private void interceptStr(String content, int lineMaxLength, StringBuilder builder) {
        if (content.length() > lineMaxLength){
            builder.append(content.substring(0,lineMaxLength));
            builder.append("\n");
            interceptStr(content.substring(lineMaxLength,content.length()),lineMaxLength,builder);
        }else{
            builder.append(content);
        }
    }

    @Override
    protected void setListener() {
        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(courseId);
                mSrlRefresh.setRefreshing(false);
            }
        });
        mSvView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollX == 0) {
                    mSrlRefresh.setEnabled(true);
                } else {
                    mSrlRefresh.setEnabled(false);
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        EventBus.getDefault().post(new PostResult("600", "s"));
        finish();
    }

    /**
     * 请求CAMERA权限码
     */
    public static final int REQUEST_CAMERA_PERM = 101;

    @AfterPermissionGranted(REQUEST_CAMERA_PERM)
    @OnClick({R.id.ll_title_back, R.id.tv_address, R.id.ll_service, R.id.tv_submit})
    public void onViewClicked(View view) {
        Bundle bundle;
        switch (view.getId()) {
            case R.id.ll_title_back:
                if (mTimer != null) {
                    mTimer.cancel();
                    mTimer.onFinish();
                }
                EventBus.getDefault().post(new PostResult("600", "s"));
                finish();
                break;
            case R.id.tv_address:
                bundle = new Bundle();
//                if (mDetailBean != null && mDetailBean.getShopInfo() != null) {
//                    bundle.putString("address", mDetailBean.getShopInfo().getAddress());
//                    bundle.putString("latitude", mDetailBean.getShopInfo().getLatitude());
//                    bundle.putString("longitude", mDetailBean.getParentShopInfo().getLongitude());
//                    bundle.putString("shopName", mDetailBean.getParentShopInfo().getName());
//                    bundle.putString("shopAddress", mDetailBean.getShopInfo().getAddress());
//                    startActivity(LbsActivity.class, bundle);
//                }
                break;
            case R.id.ll_service:
                if (mDetailBean != null) {
                    initPermission();
                    callPhone(Constants.SERVICE_TELL);
                }
                break;
            case R.id.tv_submit:
                String stateCode = mDetailBean.getState_code();
                if ("1".equals(stateCode)){ //待确定 ,取消预约
                    cancelAppointment();
                } else if("2".equals(stateCode)){ //待上课 ,取消预约
                    if (mDetailBean.getLessonType() == 1){//已排课
                        cancelReservationCourse();
                    }else{
                        cancelAppointment();
                    }
                } else{ //再次预约
                    Bundle again = new Bundle();
                    again.putString("shopId",mDetailBean.getShopId() + "");
                    again.putString("lessonId",mLessonId);
                    startActivity(ExperienceLessonAppointmentActivity.class,again);
                    finish();
                }
                break;
        }
    }

    //取消预约
    private void cancelAppointment() {
        int shopId = mDetailBean.getShopId();
        Map map = new HashMap();
        map.put("userId", mUserId);
        map.put("id", mLessonId);
        map.put("shopId", shopId + "");
        map.put("cancelType", "2"); //1客服取消2用户取消
        Logcat.e("提交的数据：" + map.toString() + "/" + ApiConstants.Urls.CANCEL_USER_LESSON_CONSULT);
        OkHttpUtils.post().url(ApiConstants.Urls.CANCEL_USER_LESSON_CONSULT).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Logcat.e("Exception返回的数据：" + e);
                mLayoutManager.showNetError();
                dismissLoading();
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoading();
                Logcat.e("返回的数据：" + response);
                ResultBean bean = JSON.parseObject(response, ResultBean.class);
                if (bean.getCode().equals("1")) {
                    ToastUtil.showToast("取消预约成功");
                    finish();
                } else {
                    ToastUtil.showToast(bean.getMsg());
                }
            }
        });
    }

    //取消待上课中已排课的预约
    private void cancelReservationCourse() {
        int shopId = mDetailBean.getShopId();
        Map map = new HashMap();
        map.put("userId", mUserId);
        map.put("id", mLessonId);
        map.put("shopId", shopId + "");
        map.put("cancelType", "2"); //1客服取消2用户取消
        Logcat.e("提交的数据：" + map.toString() + "/" + ApiConstants.Urls.CANCEL_RESERVATION_COURSE);
        OkHttpUtils.post().url(ApiConstants.Urls.CANCEL_USER_LESSON_CONSULT).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Logcat.e("Exception返回的数据：" + e);
                mLayoutManager.showNetError();
                dismissLoading();
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoading();
                Logcat.e("返回的数据：" + response);
                ResultBean bean = JSON.parseObject(response, ResultBean.class);
                if (bean.getCode().equals("1")) {
                    ToastUtil.showToast("取消预约成功");
                    finish();
                } else {
                    ToastUtil.showToast(bean.getMsg());
                }
            }
        });
    }

    private void getData(String courseId) {
        Map map = new HashMap();
        map.put("userId", mUserId);
        map.put("id", mLessonId);
        map.put("lessonType", 2 + "");

        Logcat.e("提交的数据：" + map.toString() + "/" + ApiConstants.Urls.MAKE_DETAIL);
        OkHttpUtils.post().url(ApiConstants.Urls.MAKE_DETAIL).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Logcat.e("Exception返回的数据：" + e);
                mLayoutManager.showNetError();
                dismissLoading();
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoading();
                Logcat.e("返回的数据：" + response);
                ResultBean bean = JSON.parseObject(response, ResultBean.class);
                if (bean.getCode().equals("1")) {
                    AppointmentResultBean object = JSON.parseObject(bean.getData(), AppointmentResultBean.class);
                    if (object != null) {
                        initViewData(object);
                    }
                } else {
                    ToastUtil.showToast(bean.getMsg());
                }
            }
        });
    }

    /**
     * @param status
     * @param orderId
     * @param shopId
     */
    private void cancelCourse(int status, String orderId, String shopId) {
        /*Map map = new HashMap();
        map.put("userId", UserUtil.getUserBean(this).getId() + "");
        map.put("token", UserUtil.getUserBean(this).getToken() + "");
        map.put("appointmentId", orderId + "");
        map.put("shopId", shopId + "");
        map.put("isUser", "1");
        String url = ApiConstants.Urls.USER_CANCEL_COURSE_V2_1;
        if (status == 0) {
            url = ApiConstants.Urls.USER_CANCEL_COURSE_V2_2;
        }
        Logcat.e("提交的参数：" + map.toString() + "/" + status);
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
                    getData(courseId);
                } else {
                    ToastUtil.showToast(resultBean.getMsg());
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {

            }
        });*/
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
        callPhone(Constants.SERVICE_TELL);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        ToastUtil.showToast("去开启APP拨打电话权限");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer.onFinish();
        }
    }

    /**
     * @param s
     * @return 获取上午、中午 晚上、
     */
    public String getAm_Pm(int s) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis((long) s);
        int apm = calendar.get(Calendar.AM_PM);// 0 上午/1 下午
        int hours = Integer.valueOf(CommonUtils.getDateStringByTimeSTamp((long) s, "HH"));
        if (hours < 11) {
            return "早上";
        } else if (hours < 13) {
            return "中午";
        } else if (hours < 18) {
            return "下午";
        } else if (hours < 24) {
            return "下午";
        } else {
            return "";
        }
    }
}
