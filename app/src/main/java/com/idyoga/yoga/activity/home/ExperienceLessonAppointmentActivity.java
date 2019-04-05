package com.idyoga.yoga.activity.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.course.AppointmentCourseDetailActivity;
import com.idyoga.yoga.activity.course.OrderCourseListActivity;
import com.idyoga.yoga.activity.course.OrderCourseSuccessActivity;
import com.idyoga.yoga.activity.shop.ShopMarketCourseAppointmentActivity;
import com.idyoga.yoga.adapter.CardAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.model.BaseCourseBean;
import com.idyoga.yoga.model.CourseListData;
import com.idyoga.yoga.model.CourseUserInfo;
import com.idyoga.yoga.model.ExperienceCourseAppointmentBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.ResultIdBean;
import com.idyoga.yoga.model.SomeCourseBean;
import com.idyoga.yoga.utils.DateUtils;
import com.idyoga.yoga.utils.JsonUtil;
import com.idyoga.yoga.utils.LoginUtil;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.YogaViewUtil;
import com.idyoga.yoga.view.YogaLayoutManager;

import java.io.IOException;
import java.security.spec.PSSParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;
import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

/*预约课程*/
public class ExperienceLessonAppointmentActivity extends BaseActivity {

    @BindView(R.id.tv_course_name)
    TextView mCourseName;
    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_shop_name)
    TextView mTvShopName;
    @BindView(R.id.tv_shop_address)
    TextView mTvShopAddress;
    @BindView(R.id.tv_select_time)
    TextView mTvSelectTime;
    @BindView(R.id.tv_select_teacher)
    TextView mTvSelectTeacher;
    @BindView(R.id.tv_people_num)
    TextView mTvPeopleNum;
    @BindView(R.id.tv_mobile)
    TextView mTvMobile;
    @BindView(R.id.tv_card)
    TextView mTvCard;
    @BindView(R.id.tv_message)
    EditText mTvMessage;
    @BindView(R.id.tv_next)
    TextView mTvNext;
    @BindView(R.id.ll_foot_layout)
    RelativeLayout mLlFootLayout;
    @BindView(R.id.rl_root)
    RelativeLayout mRlRoot;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.iv_img)
    ImageView mIvImg;
    @BindView(R.id.tv_course_time)
    TextView mCourseTime;
    @BindView(R.id.rl_select_card)
    RelativeLayout mRlSelectCard;

    private String shopId = "";
    private String courseId = "";
    private String token = "";
    private int mSelectIndex = -1;

    private ExperienceCourseAppointmentBean mCourseAppointmentBean;
    private List<ExperienceCourseAppointmentBean.UsingMembershipBean> cardListBean;
    private int mUserId;
    private int mIsSetCourse;
    private CourseUserInfo mCourseUserInfo;
    private String mTime;
    private Bundle mBundle;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true);
        mImmersionBar.titleBar(mLlCommonLayout).init();
    }

    private void bindView(CourseUserInfo courseUserInfo) {
        if (courseUserInfo != null){
            mIsSetCourse = courseUserInfo.getIsSetCourse();
            CourseUserInfo.LessonInfoBean lessonInfo = courseUserInfo.getLessonInfo();
            CourseUserInfo.ShopInfoBean shopInfo = courseUserInfo.getShopInfo();
            CourseUserInfo.UserInfoBean userInfo = courseUserInfo.getUserInfo();
            mTvShopName.setText(shopInfo.getShopName());
            mTvShopAddress.setText(shopInfo.getShopAddress());
            mCourseName.setText(lessonInfo.getLessonName());
            mCourseTime.setText(lessonInfo.getLessonTime() != 0 ? lessonInfo.getLessonTime() + "分钟" : "");
            Glide.with(mContext)
                    .load(lessonInfo.getLessonImage())
                    .placeholder(R.drawable.img_course)
                    .error(R.drawable.img_course)
                    .into(mIvImg);
            mTvMobile.setText(userInfo.getUserMobile());
        }
    }

    @Override
    protected void initData() {
        mBundle = getIntent().getExtras();
        if (mBundle != null) {
            shopId = mBundle.getString("shopId");
            courseId = mBundle.getString("lessonId");
            mUserId = (int) SharedPreferencesUtils.getSP(this, "UserId", 0);
            token = (String) SharedPreferencesUtils.getSP(this, "Token", "");
            getData(shopId, courseId, token);
        } else {
            Logcat.e("参数异常");
        }
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_exprience_appointment_info;
    }

    @Override
    protected void initView() {
        //        mTvTitleText.setText("预约课程");
        //        mTvShopMobile.setVisibility(View.VISIBLE);
    }

    private void initPayView(List<CourseUserInfo.UserInfoBean.CardBean> beans) {

        for (int i = 0; i < beans.size(); i++) {
            CourseUserInfo.UserInfoBean.CardBean bean = beans.get(i);
            RadioButton button = new RadioButton(this);
            button.setText(bean.getCard_name());
            button.setButtonDrawable(null);
            Drawable drawable = getResources().getDrawable(R.drawable.cb_common);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            button.setCompoundDrawables(null, null, drawable, null);
            //mRgView.addView(button);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    YogaViewUtil.dp2px(this, 50));
            layoutParams.setMargins(YogaViewUtil.dp2px(this, 12), 0, YogaViewUtil.dp2px(this, 12), 0);
            button.setLayoutParams(layoutParams);
        }
    }

    //获取会员卡,课程信息
    private void getData(String shopId, String courseId, String token) {
        Map map = new HashMap();
        map.put("shopId", shopId);
        map.put("lessonId", courseId);
        map.put("userId", mUserId + "");
        mLayoutManager.showLoading();
        HttpClient.post(ApiConstants.Urls.COLLEGE_APPOINTMENT_PAGEDATA, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                ResultBean bean = JSON.parseObject(content, ResultBean.class);
                if (bean.getCode().equals("1")){
                    mCourseUserInfo = JSON.parseObject(bean.getData(), CourseUserInfo.class);
                    bindView(mCourseUserInfo);
                    mLayoutManager.showContent();
                }else{
                    ToastUtil.showToast(bean.getMsg());
                    mLayoutManager.showEmpty();
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                mLayoutManager.showError();
            }
        });
    }

    @Override
    protected void setListener() {

    }

    //预约课程
    private void appointmentCourse(String membershipId) {
        if (mIsSetCourse == 0) {//自选时间  添加咨询
            showLoading("加载中...", true);
            Map map = new HashMap();
            map.put("shopId", shopId + "");
            map.put("lessonId", courseId + "");
            map.put("userId", mUserId + "");
            map.put("expectTime", mTime + "");
            map.put("membershipId", membershipId + "");
            Log.d("appointmentLesson", "appointmentLesson: " + shopId + "===" + courseId + "===" + membershipId + "===" + token);
            HttpClient.post(ApiConstants.Urls.ADD_USER_LESSON_CONSULT, map, new HttpResponseHandler() {
                @Override
                public void onSuccess(String content) {
                    super.onSuccess(content);
                    dismissLoading();
                    Logcat.e("预约结果：" + content);
                    ResultBean bean = JSON.parseObject(content, ResultBean.class);
                    if (bean.getCode().equals("1")){
                        ResultIdBean resultIdBean = JSON.parseObject(bean.getData(), ResultIdBean.class);

                        Bundle bundle = new Bundle();
                        bundle.putString("userId",mUserId + "");
                        bundle.putString("lessonId",resultIdBean.getId());
                        bundle.putString("lessonType",mIsSetCourse + "");
                        startActivity(OrderCourseSuccessActivity.class,bundle);
                        //ToastUtil.showToast("提交成功");
                        finish();
                    }
                }

                @Override
                public void onFailure(Request request, IOException e) {
                    super.onFailure(request, e);
                    dismissLoading();
                }
            });
        }
    }


    @OnClick({R.id.ll_title_back, R.id.tv_next,R.id.rl_time,R.id.rl_select_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.rl_time: //MemberCardListDialog
                if (mIsSetCourse == 0) {//自选时间
                    startActivity(ShopMarketCourseAppointmentActivity.class,10050,new Bundle());
                } else { //已排期 选择排课
                    getArrangeCourse();
                }
                break;
            case R.id.rl_select_card: //MemberCardListDialog
                if (mCourseUserInfo != null){
                    final List<CourseUserInfo.UserInfoBean.CardBean> cardList = mCourseUserInfo.getUserInfo().getPlatformCardList();
                    View contentView = view.inflate(this,R.layout.dialog_menber_card_list_layout, null);
                    final BottomSheetDialog sheetDialog = new BottomSheetDialog(this, R.style.card_dialog);
                    sheetDialog.setCancelable(true);
                    sheetDialog.setContentView(contentView);
                    sheetDialog.show();
                    final ListView lv = contentView.findViewById(R.id.lv_card);
                    final CardAdapter cardAdapter = new CardAdapter(this, cardList);
                    lv.setAdapter(cardAdapter);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            CourseUserInfo.UserInfoBean.CardBean cardBean = cardList.get(position);
                            for (int i = 0; i < cardList.size(); i++) {
                                if (i != position){
                                    CourseUserInfo.UserInfoBean.CardBean bean = cardList.get(i);
                                    bean.setSelect(false);
                                }else{
                                    if (cardBean.isSelect()){
                                        cardBean.setSelect(false);
                                        mSelectIndex = -1;
                                    }else{
                                        cardBean.setSelect(true);
                                        mTvCard.setText(cardBean.getCard_name());
                                        mSelectIndex = i;
                                        sheetDialog.dismiss();
                                    }
                                }
                            }
                            cardAdapter.notifyDataSetChanged();
                        }
                    });
                    lv.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (!lv.canScrollVertically(-1)){
                                lv.requestDisallowInterceptTouchEvent(false);
                            }else{
                                lv.requestDisallowInterceptTouchEvent(true);
                            }
                            return false;
                        }
                    });
                    RelativeLayout close = contentView.findViewById(R.id.rl_close);
                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sheetDialog.dismiss();
                        }
                    });
                }
                break;
            case R.id.tv_next:
                if (!LoginUtil.checkLogin(this))return;
                List<CourseUserInfo.UserInfoBean.CardBean> cardList = mCourseUserInfo.getUserInfo().getPlatformCardList();
                if (mSelectIndex == -1){
                    ToastUtil.showToast("请选择需要支付的卡");
                    return;
                }
                if (mTime == null){
                    ToastUtil.showToast("请选择时间");
                    return;
                }
                CourseUserInfo.UserInfoBean.CardBean cardBean = cardList.get(mSelectIndex);
                int card_id = cardBean.getCard_id();
                if (mCourseUserInfo.getIsSetCourse() == 0){
                    appointmentCourse(String.valueOf(card_id));
                }else if(mCourseUserInfo.getIsSetCourse() == 1){
                    appointment(String.valueOf(card_id));
                }
                break;
        }
    }

    //预约已排期课程
    private void appointment(String membershipId) {
        showLoading("加载中...", true);
        Map map = new HashMap();
        map.put("shopId", shopId + "");
        map.put("courseId", courseId + "");
        map.put("membershipId", membershipId + "");
        map.put("token", token + "");
        Log.d("appointmentLesson", "appointmentLesson: " + shopId + "===" + courseId + "===" + membershipId + "===" + token);
        HttpClient.post(ApiConstants.Urls.APPOINTMENT_LESSON_V2, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                dismissLoading();
                Logcat.e("预约结果：" + content);
                ResultBean bean = JSON.parseObject(content, ResultBean.class);
                if (bean.getCode().equals("1")) {

                    Bundle bundle = new Bundle();
                    bundle.putString("userId",mUserId + "");
                    bundle.putString("lessonId",JsonUtil.parserSingleData(bean.getData(), "appId") + "");
                    bundle.putString("lessonType",mIsSetCourse + "");
                    startActivity(OrderCourseSuccessActivity.class,bundle);

                    /*Bundle mBundle = new Bundle();
                    mBundle.putString("stateType", "");
                    mBundle.putString("courseId", courseId+ "");
                    mBundle.putString("shopId", shopId + "");
                    mBundle.putString("appointmentId", JsonUtil.parserSingleData(bean.getData(), "appId") + "");
                    startActivity(AppointmentCourseDetailActivity.class, mBundle);*/
                    finish();
                } else {
                    ToastUtil.showToast(bean.getMsg());
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                dismissLoading();
            }
        });
    }

    //获取已排课 课程表
    private void getArrangeCourse() {  //TODO
        mBundle.putString("action","1");
        startActivity(OrderCourseListActivity.class,mBundle);
    }

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        if (postResult.getTag().equals("selectCourseTime")){
            String time = (String) postResult.getResult();
            mTime = DateUtils.datass(time);
            Logcat.i("时间 == " + time  + "  mTime = " + mTime);
            if (mTvSelectTime != null){
                mTvSelectTime.setText(time);
            }
        }else if(postResult.getTag().equals("selectCourse")){
            BaseCourseBean bean = (BaseCourseBean) postResult.getResult();
            if (bean.getBeanType() == 1){
                SomeCourseBean someCourse = (SomeCourseBean) bean; //某节课的排课
                String time = DateUtils.timet(String.valueOf(someCourse.getStart_time()));
                mTime = DateUtils.datass(time);
                Logcat.i("时间 == " + time  + "  mTime = " + mTime);
                if (mTvSelectTime != null){
                    mTvSelectTime.setText(time);
                }
            }
        }else if(postResult.getTag().equals("selectCourseTime")){
            SomeCourseBean data = (SomeCourseBean) postResult.getResult();
            long start_time = data.getStart_time();
            long end_time = data.getEnd_time();
            String startDate = DateUtils.timet(String.valueOf(start_time));
            mTime = DateUtils.datass(startDate);
            String endDate = DateUtils.timet(String.valueOf(end_time));
            String[] split = endDate.split(" ");
            if (split != null && split.length >= 2){
                startDate += split[1];
            }
            mTvSelectTime.setText(startDate);
            List<SomeCourseBean.Tutor> tutors = data.getTutor();
            if (tutors != null){
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < tutors.size(); i++) {
                    SomeCourseBean.Tutor tutor = tutors.get(i);
                    sb.append(tutor.getName());
                    if (i < tutors.size() - 1){
                        sb.append(",");
                    }
                }
                mTvSelectTeacher.setText(sb.toString());
            }
            int last_number = data.getLast_number();
            mTvPeopleNum.setText(last_number != 0 ? "剩余" + last_number + "名额" : "");
        }
    }
}
