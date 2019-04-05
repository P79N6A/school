package com.idyoga.yoga.activity.course;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.VipCardAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.model.UserCourseBean;
import com.idyoga.yoga.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

/**
 * 文 件 名: CourseCommentActivity
 * 创 建 人: By k
 * 创建日期: 2018/5/14 14:13
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class CourseCommentActivity extends BaseActivity {

    String mCourseId;
    String mShopId;
    int mUserId;
    UserCourseBean mUserCourseBean;
    @BindView(R.id.tv_course_name)
    TextView mTvCourseName;
    @BindView(R.id.tv_course_shop)
    TextView mTvCourseShop;
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
    @BindView(R.id.iv_img)
    ImageView mIvImg;
    @BindView(R.id.tv_course_time)
    TextView mTvCourseTime;
    @BindView(R.id.ll_layout_price)
    LinearLayout mLlLayoutPrice;
    @BindView(R.id.rl_itemView)
    RelativeLayout mRlItemView;
    @BindView(R.id.et_comment)
    EditText mEtComment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).flymeOSStatusBarFontColor("#333333").init();
    }

    @Override
    protected void initData() {
        mUserId = (int) SharedPreferencesUtils.getSP(this, "UserId", 0);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mCourseId = bundle.getString("CourseId");
            mShopId = bundle.getString("ShopId");
            mUserCourseBean = bundle.getParcelable("mUserCourseBean");
            Logcat.i("课程ID：" + mCourseId + "/" + mShopId);
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_course_comment;
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("评论");
        mTvTitleRight.setText("发送");
        if (mUserCourseBean != null) {
            String start = VipCardAdapter.getDateStringByTimeSTamp(mUserCourseBean.getStart(), "HH:mm");
            String end = VipCardAdapter.getDateStringByTimeSTamp(mUserCourseBean.getEnd(), "HH:mm");
            mTvCourseName.setText(mUserCourseBean.getLessonName());
            mTvCourseTime.setText("时  间："+start +"-"+ end);
            mTvCourseShop.setText("瑜伽馆："+mUserCourseBean.getShopName());
        }
    }

    @Override
    protected void setListener() {

    }


    @OnClick({R.id.ll_title_back, R.id.ll_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.ll_title_right:
                String mContent = mEtComment.getText().toString();
                if (StringUtil.isEmpty(mContent)) {
                    ToastUtil.showToast("请输入评论内容");
                } else {
                    submitComment(mContent);
                }
                break;
        }
    }

    private void submitComment(String mContent) {
        Map map = new HashMap();
        map.put("description", mContent);
        map.put("userId", mUserId + "");
        map.put("shopId", mShopId + "");
        map.put("courseId", mCourseId + "");
        map.put("is_show", "0");
        Logcat.i("提交的参数:" + map.toString());
        HttpClient.post("http://testyogabook.hq-xl.com/mall/Lesson_comment/addLessonComment", map, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                if (statusCode == 200) {
                    ToastUtil.showToast("提交成功，谢谢！");
                    finish();
                } else {
                    ToastUtil.showToast("提交失败，请重试");
                }
            }
        });


    }
}