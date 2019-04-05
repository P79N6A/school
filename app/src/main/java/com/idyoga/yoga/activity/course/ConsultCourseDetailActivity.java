package com.idyoga.yoga.activity.course;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.model.ConsultCourseDetailBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.DateUtils;
import com.idyoga.yoga.utils.GlideImgManager;
import com.idyoga.yoga.view.YogaLayoutManager;
import com.idyoga.yoga.view.csstv.CSSTextView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;
import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.view.common.suklib.view.FlowLayout.TagFlowLayout;

/**
 * 文 件 名: ConsultCourseDetailActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/26
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ConsultCourseDetailActivity extends BaseActivity {
    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.ll_title_right)
    LinearLayout mLlTitleRight;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.iv_img)
    ImageView mIvImg;
    @BindView(R.id.tv_course_name)
    TextView mTvCourseName;
    @BindView(R.id.tv_course_tutor)
    TextView mTvCourseTutor;
    @BindView(R.id.tv_course_time)
    TextView mTvCourseTime;
    @BindView(R.id.rl_itemView)
    RelativeLayout mRlItemView;
    @BindView(R.id.tag_view)
    TagFlowLayout mTagView;
    @BindView(R.id.ll_layout_item)
    LinearLayout mLlLayoutItem;
    @BindView(R.id.tv_shop_name)
    CSSTextView mTvShopName;
    @BindView(R.id.tv_shop_address)
    CSSTextView mTvShopAddress;
    @BindView(R.id.tv_shop_mobile)
    CSSTextView mTvShopMobile;
    @BindView(R.id.tv_time_type)
    TextView mTvTimeType;
    @BindView(R.id.tv_expect)
    TextView mTvExpect;
    @BindView(R.id.sv_view)
    ScrollView mSvView;
    @BindView(R.id.tv_next)
    TextView mTvNext;
    @BindView(R.id.ll_foot_layout)
    RelativeLayout mLlFootLayout;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    private String expectId = "";
    private String userId;
    private ConsultCourseDetailBean mDetailBean;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).flymeOSStatusBarFontColor("#333333").statusBarDarkFont(true).init();
    }

    @Override
    protected void initData() {
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            expectId = mBundle.getString("expectId");
            userId = String.valueOf((int) SharedPreferencesUtils.getSP(mContext, "UserId", 0));
            Logcat.i("tag:" + expectId + "/" + userId);
            getData(userId, expectId);
        } else {
            Logcat.i("缺少参数");
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_consult_course_detail;
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(mSvView);
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("我的咨询");
        mLayoutManager.showLoading();
    }

    @Override
    protected void setListener() {
    }

    private void initViewData(ConsultCourseDetailBean detailBean) {
        if (detailBean != null) {
            mLayoutManager.showContent();
        }
        mTvTitleText.setText(detailBean.getLessonName());
        GlideImgManager.glideLoaders(this, detailBean.getImage(), mIvImg);
        mTvCourseName.setText(detailBean.getLessonName());
        mTvCourseTime.setText("");
        mTvShopName.setText("瑜伽馆：" + detailBean.getShopName());
        mTvCourseTutor.setText("时长：" + DateUtils.secondToTime(detailBean.getLessonTime() * 60));
        String mDistance = "(距您 " + 0 + "km)";
        String address = "地址: " + detailBean.getAddress();
        mTvShopAddress.setText(address);
        mTvShopAddress.setTextArrColor("地址：", Color.parseColor("#333333"));
        String mobile = " 4009020929";
        mTvShopMobile.setText("客服电话：" + mobile);
        mTvShopMobile.setTextArrColor("客服电话：", Color.parseColor("#333333"));
        mTvExpect.setText(CommonUtils.getDateStringByTimeSTamp((long) detailBean.getExpectTime(), "yyyy年MM月dd日 HH:mm:ss"));
        mTvNext.setText(detailBean.getType());
        if (detailBean.getType().equals("待上课")) {
            mTvTimeType.setText("确定上课时间");
            mTvExpect.setText(CommonUtils.getDateStringByTimeSTamp((long) detailBean.getTime(), "yyyy年MM月dd日 HH:mm:ss"));
        }
    }

    private void getData(String userId, String expectId) {
        Map map = new HashMap();
        map.put("userId", userId);
        map.put("id", expectId);
        HttpClient.post(ApiConstants.Urls.CONSULT_LESSON_DETAIL, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                Logcat.e("咨询订单详情：" + content);
                ResultBean bean = JSON.parseObject(content, ResultBean.class);
                if (bean.getCode().equals("1")) {
                    mDetailBean = JSON.parseObject(bean.getData(), ConsultCourseDetailBean.class);
                    initViewData(mDetailBean);
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
            }
        });
    }

    @OnClick({R.id.ll_title_back, R.id.ll_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.ll_title_right:
                break;
        }
    }

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
    }

}
