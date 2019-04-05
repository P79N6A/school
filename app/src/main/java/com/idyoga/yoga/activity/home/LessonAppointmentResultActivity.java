package com.idyoga.yoga.activity.home;

import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.NetWorkConstant;
import com.idyoga.yoga.model.ExperienceAppointmentResultInfoBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.utils.DateUtils;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.view.YogaLayoutManager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import vip.devkit.library.SharedPreferencesUtils;


public class LessonAppointmentResultActivity extends BaseActivity {

    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.tv_order_id)
    TextView tvOrderId;
    @BindView(R.id.tv_lesson_name)
    TextView tvLessonName;
    @BindView(R.id.tv_classroom_name)
    TextView tvClassroomName;
    @BindView(R.id.tv_lesson_teacher)
    TextView tvLessonTeacher;
    @BindView(R.id.tv_lesson_time)
    TextView tvLessonTime;
    @BindView(R.id.tv_lesson_attention)
    TextView tvLessonAttention;
    @BindView(R.id.tv_lesson_address)
    TextView tvLessonAddress;
    @BindView(R.id.tv_lesson_phone)
    TextView tvLessonPhone;
    @BindView(R.id.btn_return)
    Button btnReturn;
    @BindView(R.id.ll_layout)
    LinearLayout mLayout;
    String shopId = "";
    String appointmentId = "";


    @Override
    protected void initData() {
        shopId = getIntent().getStringExtra("shopId");
        appointmentId = getIntent().getStringExtra("appointmentId");
        String token = (String) SharedPreferencesUtils.getSP(this, "Token", "Token");
        getData(shopId, appointmentId, token);
    }


    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true).titleBar(mLlCommonLayout).init();
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(mLayout);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_exprience_appointment_result;
    }

    @Override
    protected void initView() {
        mLayoutManager.showLoading();
        mTvTitleText.setText("约课成功");
    }

    @Override
    protected void setListener() {

    }


    @OnClick({R.id.ll_title_back, R.id.btn_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                this.finish();
                break;
            case R.id.btn_return:
                this.finish();
                break;
        }
    }

    /**
     * @param resultInfoBean
     */
    private void initViewData(ExperienceAppointmentResultInfoBean resultInfoBean) {
        String teacherName = "";
        String time = DateUtils.times2(resultInfoBean.getCourseDetail().getStart() + "") + "-" + DateUtils.times2(resultInfoBean.getCourseDetail().getEnd() + "");
        for (ExperienceAppointmentResultInfoBean.CourseDetailBean.TutorListBean teacher : resultInfoBean.getCourseDetail().getTutorList()) {
            teacherName = teacher.getName() + " " + teacherName;
        }
        tvOrderId.setText("订单编号: " + appointmentId);
        tvLessonName.setText("课程名称: " + resultInfoBean.getCourseDetail().getLessonName());
        tvClassroomName.setText("课程教室: " + resultInfoBean.getCourseDetail().getClassroomName());
        tvLessonTeacher.setText("课程老师: " + teacherName);
        tvLessonTime.setText("课程时间: " + time);
        tvLessonAddress.setText("地址: " + resultInfoBean.getShopInfo().getAddress());
        tvLessonPhone.setText("联系电话 :" + resultInfoBean.getShopInfo().getMobile() + "");
        mLayoutManager.showContent();
    }

    /**
     * @param shopId
     * @param appointmentId
     * @param token
     */
    public void getData(String shopId, String appointmentId, String token) {
        Map map = new HashMap();
        map.put("shopId", shopId);
        map.put("appointmentId", appointmentId);
        map.put("token", token);
        OkHttpUtils.post().url(ApiConstants.Urls.APPOINTMENTED_COURSE_INFO).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mLayoutManager.showNetError();
            }
            @Override
            public void onResponse(String response, int id) {
                ResultBean bean = JSON.parseObject(response, ResultBean.class);
                if (bean.getCode().equals("1")) {
                    ExperienceAppointmentResultInfoBean resultInfoBean = JSON.parseObject(bean.getData(), ExperienceAppointmentResultInfoBean.class);
                    initViewData(resultInfoBean);
                }
            }
        });
    }
}
