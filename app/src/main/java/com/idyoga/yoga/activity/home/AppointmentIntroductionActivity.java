package com.idyoga.yoga.activity.home;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Layout;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.user.AccountActivity;
import com.idyoga.yoga.adapter.CourseDetailAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.model.CoureseDetailInfo;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.ShopMarketCourseDetailBean;
import com.idyoga.yoga.model.UserInfoBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.DateUtils;
import com.idyoga.yoga.utils.DialogUtil;
import com.idyoga.yoga.utils.HtmlBuilder;
import com.idyoga.yoga.utils.LoginUtil;
import com.idyoga.yoga.utils.SpannableHelper;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.UserUtil;
import com.idyoga.yoga.view.YogaLayoutManager;
import com.idyoga.yoga.view.csstv.CSSTextView;
import com.idyoga.yoga.view.csstv.MTextView;
import com.idyoga.yoga.view.dialog.CommonDialog;

import java.io.IOException;
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
import vip.devkit.view.common.pile.PileLayout;
import vip.devkit.view.common.suklib.view.FlowLayout.FlowLayout;
import vip.devkit.view.common.suklib.view.FlowLayout.TagAdapter;
import vip.devkit.view.common.suklib.view.FlowLayout.TagFlowLayout;


public class AppointmentIntroductionActivity extends BaseActivity {

    @BindView(R.id.rl_layout)
    RelativeLayout mRlLayout;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.ll_title_right)
    LinearLayout mLlTileRight;
    @BindView(R.id.tv_title_right)
    TextView mTvRightText;
    @BindView(R.id.rlv_course_info)
    RecyclerView mRvList;
    @BindView(R.id.ll_foot_layout)
    RelativeLayout mFootLayout;
    @BindView(R.id.tv_next)
    TextView mTvNext;
    private CoureseDetailInfo mCourseDetailBean;
    private String shopId, lessonId, token, lat, lng;
    private String tag;
    private List<String> pileList = new ArrayList<>();
    private int mPage = 1;
    private CourseDetailAdapter mAdapter;
    private Bundle mBundle;

    @Override
    protected void initData() {
        mBundle = getIntent().getExtras();
        if (mBundle != null) {
            tag= mBundle.getString("tag");
            shopId = mBundle.getString("shopId");//"19287727";
            lessonId = mBundle.getString("lessonId");//"104";
            token = (String) SharedPreferencesUtils.getSP(this, "Token", "");
            lat = (String) SharedPreferencesUtils.getSP(this, "latitude", "");
            lng = (String) SharedPreferencesUtils.getSP(this, "longitude", "");
            Logcat.i("接收到的值：" + shopId + "/" + lessonId + "/" + token + "/" + lat + "/" + lng);
            getData(shopId, lessonId, token, lat, lng);
        } else {
            Logcat.e("参数为空");
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_exprience_appointment_introduction;
    }


    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).init();
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(mRlLayout);
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("课程详情");
        mLayoutManager.showLoading();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvList.setLayoutManager(layoutManager);
        mAdapter = new CourseDetailAdapter(this);
        mRvList.setAdapter(mAdapter);
        if (!TextUtils.isEmpty(tag)&&tag.equals("close")){
            //mLlFootLayout.setVisibility(View.GONE);
        }
    }

    private void initViewData(CoureseDetailInfo courseDetailBean) {
        this.mCourseDetailBean = courseDetailBean;
        if (courseDetailBean != null) {
            mAdapter.setDatas(courseDetailBean);
        }
    }

    //获取课程详情
    private void getData(String shopId, String lessonId, String token, String lat, String lng) {
        Map map = new HashMap();
        map.put("shopId", shopId);//"19287727" );//+ shopId);
        map.put("lessonId", lessonId);//"104" );//+ lessonId);
        map.put("page", mPage + "");
        map.put("size", "5");
        Logcat.e("权益课详情提交的参数:" + map.toString());
        HttpClient.post(ApiConstants.Urls.COURSE_DETAIL_DATA, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                Logcat.e("权益课详情:" + content);
                ResultBean bean = JSON.parseObject(content, ResultBean.class);
                if (bean.getCode().equals("1")) {
                    mCourseDetailBean = JSON.parseObject(bean.getData(), CoureseDetailInfo.class);
                    initViewData(mCourseDetailBean);
                    mLayoutManager.showContent();
                } else {
                    ToastUtil.showToast(bean.getMsg());
                    mLayoutManager.showEmpty();
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                mLayoutManager.showNetError();
            }
        });
    }

    @Override
    protected void setListener() {
        //mPage

    }

    @Override
    protected void checkNetState(Context mContext) {
        super.checkNetState(mContext);
    }

    @OnClick({R.id.ll_title_back, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.tv_next:
                if (!LoginUtil.checkLogin(this)) return;
                /*UserInfoBean userInfoBean = UserUtil.getUserBean(this);
                if (null != userInfoBean && userInfoBean.getType().equals("true")) {
                    showTips();
                    return;
                }*/
                //预约课程
                //mBundle.putString("time",String.valueOf(mCourseDetailBean));
//                Bundle bundle = new Bundle();
//                bundle.putString("shopId", shopId);
//                bundle.putString("courseId", lessonId);
//                String image = mCourseDetailBean.getImage();
//                String name = mCourseDetailBean.getName();
                //bundle.putString("name", mCourseDetailBean.getShopInfo().getName());
//                bundle.putString("address", mCourseDetailBean.getShopInfo().getAddress());
//                bundle.putString("distance", mCourseDetailBean.getShopInfo().getCompare() + "");
//                bundle.putString("mobile", mCourseDetailBean.getShopInfo().getMobile());
                startActivity(ExperienceLessonAppointmentActivity.class, mBundle);
                break;
        }
    }

    private void showTips() {
        DialogUtil.wrap(this).setData("提示", "请先去完善个人信息").setActionClickListener(new DialogUtil.onActionClickListener() {
            @Override
            public void action(int viewType, Dialog dialog, View view) {
                if (viewType == DialogUtil.SURE) {
                    Bundle bundle = new Bundle();
                    startActivity(AccountActivity.class, bundle);
                    dialog.dismiss();
                } else {
                    dialog.dismiss();
                }
            }
        }).init().show();
    }

    private String autoSplitText(final TextView tv) {
        final String rawText = tv.getText().toString(); //原始文本
        final Paint tvPaint = tv.getPaint(); //paint，包含字体等信息
        final float tvWidth = tv.getWidth() - tv.getPaddingLeft() - tv.getPaddingRight(); //控件可用宽度

        //将原始文本按行拆分
        String[] rawTextLines = rawText.replaceAll("\r", "").split("\n");
        StringBuilder sbNewText = new StringBuilder();
        for (String rawTextLine : rawTextLines) {
            if (tvPaint.measureText(rawTextLine) <= tvWidth) {
                //如果整行宽度在控件可用宽度之内，就不处理了
                sbNewText.append(rawTextLine);
            } else {
                //如果整行宽度超过控件可用宽度，则按字符测量，在超过可用宽度的前一个字符处手动换行
                float lineWidth = 0;
                for (int cnt = 0; cnt != rawTextLine.length(); ++cnt) {
                    char ch = rawTextLine.charAt(cnt);
                    lineWidth += tvPaint.measureText(String.valueOf(ch));
                    if (lineWidth <= tvWidth) {
                        sbNewText.append(ch);
                    } else {
                        sbNewText.append("\n");
                        lineWidth = 0;
                        --cnt;
                    }
                }
            }
            sbNewText.append("\n");
        }

        //把结尾多余的\n去掉
        if (!rawText.endsWith("\n")) {
            sbNewText.deleteCharAt(sbNewText.length() - 1);
        }

        return sbNewText.toString();
    }

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        if (postResult.getTag().equals("2CourseDetail")){
            Bundle bundle = (Bundle) postResult.getResult();
            bundle.putString("shopId",shopId);
            startActivity(AppointmentIntroductionActivity.class,bundle);
        }
    }
}
