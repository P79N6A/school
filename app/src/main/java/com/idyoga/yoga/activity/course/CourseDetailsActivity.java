package com.idyoga.yoga.activity.course;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.comm.NetWorkConstant;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.UserCourseBean;
import com.idyoga.yoga.model.UserInfoBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.EnumUtil;
import com.idyoga.yoga.utils.GlideImgManager;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.UserUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * 文 件 名: CourseActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/3/22
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class CourseDetailsActivity extends BaseActivity {
    @BindView(R.id.iv_course_img)
    ImageView mIvCourseImg;
    @BindView(R.id.tv_course_title)
    TextView mTvCourseTitle;
    @BindView(R.id.tv_course_name)
    TextView mTvCourseName;
    @BindView(R.id.tv_state)
    TextView mTvState;
    @BindView(R.id.tv_course_address)
    TextView mTvCourseAddress;
    @BindView(R.id.tv_course_time)
    TextView mTvCourseTime;
    @BindView(R.id.tv_teacher_name)
    TextView mTvTeacherName;
    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    private UserCourseBean mUserCourseBean;
    private String UserId, Token, ShopId;
    private int stateType;
    private UserInfoBean mUserInfoBean;
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).statusBarDarkFont(true).init();
    }

    @Override
    protected void initData() {
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mUserCourseBean = mBundle.getParcelable("bean");
            stateType = mBundle.getInt("stateType");
        }
        mUserInfoBean = UserUtil.getUserBean(this);
        ShopId = (String) SharedPreferencesUtils.getSP(this, "shopId", "");
        getDetail(mUserCourseBean.getCourseId()+"",mUserCourseBean.getShop_id()+"");
    }

    @Override
    protected int setLayoutId() {
        return R.layout.user_activity_course_detail;
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("课程详情");
        mTvState.setText(EnumUtil.COURSE_STATE.getSate(stateType));
        if (mUserCourseBean != null) {
            mTvCourseTitle.setText(mUserCourseBean.getLessonName());
            mTvCourseName.setText(mUserCourseBean.getLessonName());
            mTvCourseAddress.setText("教室：" + mUserCourseBean.getClassroomName());
            mTvCourseTime.setText("时间："+
                    CommonUtils.getDateStringByTimeSTamp(mUserCourseBean.getStart(),"yyyy-MM-dd")+"("
            +CommonUtils.getDateStringByTimeSTamp(mUserCourseBean.getStart(),"HH:mm")+"-"
            +CommonUtils.getDateStringByTimeSTamp(mUserCourseBean.getEnd(),"HH:mm")+")");
            mTvTeacherName.setText("老师："+mUserCourseBean.getTutorName());
            GlideImgManager.glideLoader(this, mUserCourseBean.getLessonImg(), R.drawable.default_course_img, mIvCourseImg);

        }
    }

    @Override
    protected void setListener() {

    }


    @OnClick(R.id.ll_title_back)
    public void onViewClicked() {
        finish();
    }

    private void getDetail(String courseId,  String shopId) {
        Map map = new HashMap();
        map.put("token", mUserInfoBean.getToken()+"");
        map.put("courseId", courseId+"");
        map.put("shopId", shopId+"");
        Logcat.e("提交的参数："+map.toString());
        HttpClient.post(NetWorkConstant.USER_COURSE_LIST_DETAIL, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                Logcat.e("课程详情："+content);
                ResultBean resultBean = JSON.parseObject(content, ResultBean.class);
                if (resultBean.getCode().equals("1")) {
                } else {
                    ToastUtil.showToast(resultBean.getMsg());
                }
            }
            @Override
            public void onFailure(Request request, IOException e) {

            }
        });
    }

}
