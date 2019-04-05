package com.idyoga.yoga.activity.course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author weilgu
 * @time 2019/3/14 15:39
 * @des 预约课程成功,
 */

public class OrderCourseSuccessActivity extends BaseActivity {

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
    @BindView(R.id.tv_see_detail)
    TextView mTvSeeDetail;
    @BindView(R.id.tv_again)
    TextView mTvAgain;
    private String userId;
    private String mLessonId;
    private String mLessonType;
    private Bundle mBundle;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).init();
    }

    @Override
    protected void initData() {
        /*bundle.putString("lessonId",courseId);
          bundle.putString("lessonType",mIsSetCourse);*/
        Intent intent = getIntent();
        mBundle = intent.getExtras();
        if (mBundle != null){
            userId = mBundle.getString("userId");
            mLessonId = mBundle.getString("lessonId");
            mLessonType = mBundle.getString("lessonType");
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_order_course_success;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.ll_title_back, R.id.tv_see_detail, R.id.tv_again})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.tv_see_detail:
                startActivity(AppointmentCourseDetailActivity.class,mBundle);
                finish();
                break;
            case R.id.tv_again:
                finish();
                break;
        }
    }
}
