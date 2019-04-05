/**
 * ****************************** Copyright (c)*********************************\
 * *
 * *                 (c) Copyright 2017, DevKit.vip, china, qd. sd
 * *                          All Rights Reserved
 * *
 * *                           By(K)
 * *
 * *-----------------------------------版本信息------------------------------------
 * * 版    本: V0.1
 * *
 * *------------------------------------------------------------------------------
 * *******************************End of Head************************************\
 */
package com.idyoga.yoga.activity.home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.web.YogaWebActivity;
import com.idyoga.yoga.adapter.HomeSubjectAdapter;
import com.idyoga.yoga.adapter.HomeSubjectComAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.presenter.ICommonRequestPresenter;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.common.view.CustomPopupWindow;
import com.idyoga.yoga.listener.OnBooleanListener;
import com.idyoga.yoga.model.HomeSubjectItemBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.UserInfoBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.DateUtils;
import com.idyoga.yoga.utils.GlideImgManager;
import com.idyoga.yoga.utils.LoginUtil;
import com.idyoga.yoga.utils.ShareUtil;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.UserUtil;
import com.idyoga.yoga.view.YogaLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.ViewUtils;
import vip.devkit.view.common.pile.PileLayout;

/**
 * 文 件 名: SubjectListActivity
 * 创 建 人: By k
 * 创建日期: 2018/3/24 21:14
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class SubjectListActivity extends BaseActivity implements ICommonViewUi {
    @BindView(R.id.sv_view)
    NestedScrollView mNsvView;
    @BindView(R.id.iv_hp_img)
    ImageView mIvHpImg;
    @BindView(R.id.pl_view)
    PileLayout mPlView;
    @BindView(R.id.rl_horizontal_list)
    RecyclerView mRlHList;
    @BindView(R.id.lv_video_list)
    ListView mLvVideoList;
    @BindView(R.id.tv_subject_title)
    TextView mTvSubjectTitle;
    @BindView(R.id.tv_subject_number)
    TextView mTvSubjectNumber;
    @BindView(R.id.rl_subject_title)
    RelativeLayout mRlSubjectTitle;
    @BindView(R.id.tv_course_introduce)
    TextView mTvCourseIntroduce;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.rl_course_more)
    RelativeLayout mRlCourseMore;
    @BindView(R.id.tv_join_number)
    TextView mTvJoinNumber;
    @BindView(R.id.tv_difficulty)
    TextView mTvDifficulty;
    @BindView(R.id.tv_course_time)
    TextView mTvCourseTime;
    @BindView(R.id.tv_course_function)
    TextView mTvCourseFunction;
    @BindView(R.id.tv_course_total)
    TextView mTvCourseTotal;
    @BindView(R.id.ll_bar_back)
    LinearLayout mLlBarBack;
    @BindView(R.id.tv_bar_title)
    TextView mTvBarTitle;
    @BindView(R.id.iv_bar_back)
    ImageView mIvBack;
    @BindView(R.id.iv_bar_right)
    ImageView mIvBarRight;
    @BindView(R.id.ll_bar_right)
    LinearLayout mLlBarRight;
    @BindView(R.id.ll_common_bar)
    LinearLayout mLlCommonBar;
    @BindView(R.id.rv_list)
    RecyclerView mRlList;
    @BindView(R.id.tv_subscribe)
    TextView mTvSubscribe;
    private YogaLayoutManager mYogaLayoutManager;
    private HomeSubjectComAdapter mComAdapter;
    private HomeSubjectAdapter mAdapter;
    private List<String> pileList = new ArrayList<>();
    private List<HomeSubjectItemBean.EquipmentBean> mEquipmentBeans = new ArrayList<>();
    private List<HomeSubjectItemBean.VideoGroupBean> mVideoListBeans = new ArrayList<>();
    private ICommonRequestPresenter iCommonRequestPresenter;
    private String UserId, Token, ShopId;
    private String videoId;
    private boolean isSubscribe;//是否订阅
    private HomeSubjectItemBean mItemBean;
    private String url, title, ImgUrl;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonBar).flymeOSStatusBarFontColor("#333333").init();
    }

    @Override
    protected void initData() {
        UserId = (String) SharedPreferencesUtils.getSP(this, "Mobile", "");
        UserInfoBean bean = UserUtil.getUserBean(this);
        if (bean != null) {
            Token = bean.getToken();
        }
        ShopId = (String) SharedPreferencesUtils.getSP(this, "shopId", "");
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            videoId = mBundle.getString("videoId");
            url = mBundle.getString("getUrl");
        } else {
            Logcat.e("缺少参数：videoId");
        }
        Logcat.i("接收到的数据：" + videoId + "");

    }

    @Override
    protected int setLayoutId() {
        return R.layout.home_activity_subject_list;
    }

    @Override
    protected void initView() {
        mYogaLayoutManager = YogaLayoutManager.wrap(mNsvView);
        mYogaLayoutManager.showLoading();
        mIvBarRight.setImageResource(R.drawable.icon_more_white);
        iCommonRequestPresenter = new CommonRequestPresenterImpl(mContext, this);
        toRequest(ApiConstants.EventTags.HOME_SUBJECT_ITEM);
        mIvBarRight.setVisibility(View.VISIBLE);
        initList();
        setViewData();
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRlHList.setLayoutManager(linearLayoutManager);
        mComAdapter = new HomeSubjectComAdapter(R.layout.item_home_subject_com, mEquipmentBeans);
        mRlHList.setAdapter(mComAdapter);
        mAdapter = new HomeSubjectAdapter(mContext, mVideoListBeans, R.layout.item_home_subject);
        mLvVideoList.setPressed(false);//去掉焦点
        mLvVideoList.setFocusableInTouchMode(false);
        mLvVideoList.requestFocus();
        mLvVideoList.setAdapter(mAdapter);
    }

    private void setViewData() {
        if (mItemBean != null) {
            if (mItemBean.getIs_order() == 0) {
                isSubscribe = false;
                mTvSubscribe.setText("订阅");
                mTvSubscribe.setVisibility(View.VISIBLE);
            } else {
                isSubscribe = true;
                mTvSubscribe.setVisibility(View.GONE);
                mTvSubscribe.setText("取消订阅");
            }
            initPileData();
            int total = 0;
            for (int i = 0; i < mItemBean.getVideoGroup().size(); i++) {
                total += mItemBean.getVideoGroup().get(i).getVideoGroupAction().size();
            }
            mPlView.setErrorImg(R.drawable.ic_user_hp);
            mPlView.setViewSize(35);
            mPlView.setFlag(false);
            mPlView.setSpWidth(25);
            mPlView.setData(pileList);
            GlideImgManager.glideLoader(mContext, mItemBean.getImage_url(), R.mipmap.app_logo, mIvHpImg);
            mTvCourseIntroduce.setText(mItemBean.getDescription());
            mTvCourseTime.setText("时长：" + DateUtils.secToTime(mItemBean.getTime()));
            mTvSubjectTitle.setText(mItemBean.getTitle());
            mTvSubjectNumber.setText("共有" + mItemBean.getNumber() + "人学习");
            mTvCourseFunction.setText("功效：" + mItemBean.getEfficacy() + "");
            mTvDifficulty.setText("难度：" + mItemBean.getGrade() + "");
            mTvJoinNumber.setText(mItemBean.getNumber() + "");
            mTvCourseTotal.setText("全课程 " + total + "节");
            mAdapter.setSubjectItemBean(CommonUtils.GsonToJson(mItemBean));
            dismissLoading();
            mYogaLayoutManager.showContent();
        }
    }

    /**
     *
     */
    private void initPileData() {
        if (!ListUtil.isEmpty(mItemBean.getHeadPortrait())) {
            if (mItemBean.getHeadPortrait().size() > 6) {
                for (int i = 0; i < 6; i++) {
                    pileList.add(mItemBean.getHeadPortrait().get(i).getImage_url());
                }
            } else {
                for (int i = 0; i < mItemBean.getHeadPortrait().size(); i++) {
                    pileList.add(mItemBean.getHeadPortrait().get(i).getImage_url());
                }
            }
        }
    }


    @Override
    protected void setListener() {
        mNsvView.setOnScrollChangeListener(mScrollChangeListener);
        mComAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle mBundle = new Bundle();
                mBundle.putString("getUrl", mEquipmentBeans.get(position).getUrl());
                startActivity(YogaWebActivity.class, mBundle);
            }
        });
    }


    private void initViewData(HomeSubjectItemBean itemBean) {
        mVideoListBeans.addAll(itemBean.getVideoGroup());
        mEquipmentBeans.addAll(itemBean.getEquipment());
        mComAdapter.notifyDataSetChanged();
        mAdapter.notifyDataSetChanged();
        setViewData();
    }

    @Override
    public void toRequest(int eventTag) {
        Map<String, String> map = new HashMap<>();
        if (eventTag == ApiConstants.EventTags.HOME_SUBJECT_ITEM) {
            map.put("videoId", videoId);
            map.put("token", Token + "");
            iCommonRequestPresenter.requestGet(eventTag, mContext, ApiConstants.Urls.HOME_SUBJECT_ITEM, map);
        } else if (eventTag == ApiConstants.EventTags.USER_CANCEL_APPOINTMENT_VIDEO_COURSE) {
            map.put("token", Token + "");
            map.put("videoId", videoId + "");
            Logcat.e("取消预约：" + map.toString());
            mTvSubscribe.setVisibility(View.VISIBLE);
            iCommonRequestPresenter.requestGet(eventTag, mContext, ApiConstants.Urls.USER_CANCEL_APPOINTMENT_VIDEO_COURSE, map);
        } else if (eventTag == ApiConstants.EventTags.USER_APPOINTMENT_VIDEO_COURSE) {
            map.put("videoId", videoId + "");
            map.put("token", Token + "");
            Logcat.e("预约：" + map.toString());
            mTvSubscribe.setVisibility(View.GONE);
            iCommonRequestPresenter.requestGet(eventTag, mContext, ApiConstants.Urls.USER_APPOINTMENT_VIDEO_COURSE, map);
        }
    }

    @Override
    public void getRequestData(int eventTag, String result) {
        Logcat.i("json:" + eventTag + "/" + result);
        dismissLoading();
        ResultBean resultBean = JSON.parseObject(result, ResultBean.class);
        if (resultBean.getCode().equals("1")) {
            if (eventTag == ApiConstants.EventTags.HOME_SUBJECT_ITEM) {
                mItemBean = JSON.parseObject(resultBean.getData(), HomeSubjectItemBean.class);
                initViewData(mItemBean);
            } else if (eventTag == ApiConstants.EventTags.USER_CANCEL_APPOINTMENT_VIDEO_COURSE) {
                isSubscribe = false;
                mItemBean.setIs_order(0);
                ToastUtil.showToast("取消订阅成功");
                mTvSubscribe.setText("订阅");
            } else if (eventTag == ApiConstants.EventTags.USER_APPOINTMENT_VIDEO_COURSE) {
                isSubscribe = true;
                mTvSubscribe.setText("取消订阅");
                mItemBean.setIs_order(1);
                ToastUtil.showToast(resultBean.getMsg());
            }
        } else {
            ToastUtil.showToast(resultBean.getMsg());
        }
    }


    @Override
    public void onRequestFailureException(int eventTag, String msg) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(800);
        finish();
    }

    @OnClick({R.id.rl_course_more, R.id.ll_bar_back, R.id.iv_bar_right, R.id.tv_subscribe})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_course_more:
                ToastUtil.showToast("待开放");
                break;
            case R.id.ll_bar_back:
                setResult(800);
                finish();
                break;
            case R.id.iv_bar_right:
                if (!LoginUtil.checkLogin(this)) return;
                Logcat.e("订阅状态：" + isSubscribe);
                showSubscribeDialog(isSubscribe);
                break;
            case R.id.tv_subscribe:
                if (!LoginUtil.checkLogin(this)) return;
                if (isSubscribe) {
                    showLoading("订阅中...");
                    toRequest(ApiConstants.EventTags.USER_CANCEL_APPOINTMENT_VIDEO_COURSE);
                } else {
                    showLoading("取消订阅中...");
                    toRequest(ApiConstants.EventTags.USER_APPOINTMENT_VIDEO_COURSE);
                }
                break;
            case 1000:
                Logcat.i("分享");
                ShareUtil.with(this)
                        .setShareTitle(mItemBean.getTitle())
                        .setShareUrl("http://wxyoga.hq-xl.com/course/vd_detail?videoId=" + videoId)
                        .setShareDescription(mItemBean.getDescription())
                        .setShareImgUrl(mItemBean.getImage_url())
                        .build()
                        .share();
                break;
        }
    }

    /**
     * 订阅、取消订阅
     *
     * @param isSubscribe 是否订阅
     */
    private void showSubscribeDialog(boolean isSubscribe) {
        PopupWindow popupWindow = new PopupWindow();
        View view = View.inflate(this, R.layout.dialog_subscribe, null);
        ViewHolder viewHolder = new ViewHolder(view, popupWindow, isSubscribe);
        viewHolder.setBooleanListener(new OnBooleanListener() {
            @Override
            public void onCallback(int type, boolean b, String content) {
                if (b) {
                    toRequest(ApiConstants.EventTags.USER_CANCEL_APPOINTMENT_VIDEO_COURSE);
                } else {
                    toRequest(ApiConstants.EventTags.USER_APPOINTMENT_VIDEO_COURSE);
                }
            }
        });
        popupWindow.setContentView(view);
        popupWindow.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setAnimationStyle(com.idyoga.yoga.common.R.style.select_anim);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        popupWindow.setBackgroundDrawable(dw);
        CustomPopupWindow.backgroundAlpha(SubjectListActivity.this, 0.5f);//0.0-1.0
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                CustomPopupWindow.backgroundAlpha(SubjectListActivity.this, 1f);
            }
        });
        popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    /**
     * 滑动事件
     */
    public NestedScrollView.OnScrollChangeListener mScrollChangeListener = new NestedScrollView.OnScrollChangeListener() {
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            // TODO Auto-generated method stub
            if (scrollY <= 0) {
                mLlCommonBar.setBackgroundColor(Color.argb((int) 0, 144, 151, 166));
                mIvBack.setImageResource(R.drawable.home_submenu_nav_btn_back);
                mIvBarRight.setImageResource(R.drawable.icon_more_white);
                mTvBarTitle.setText("");
                initImmersionBar();
            } else if (scrollY > 0 && scrollY <= ViewUtils.getViewHeight(mIvHpImg)) {
                float scale = (float) scrollY / ViewUtils.getViewHeight(mIvHpImg);
                float alpha = (255 * scale);
                mLlCommonBar.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                mIvBack.setImageResource(R.drawable.ic_blank_back);
                mIvBarRight.setImageResource(R.drawable.icon_more_black);
                mTvBarTitle.setText("");
                mImmersionBar.statusBarDarkFont(true)
                        .flymeOSStatusBarFontColor("#333333")
                        .titleBar(mLlCommonBar).init();
            } else {
                mLlCommonBar.setBackgroundColor(Color.argb(255, 255, 255, 255));
                mImmersionBar.statusBarDarkFont(true)
                        .flymeOSStatusBarFontColor("#333333")
                        .titleBar(mLlCommonBar).init();
                mTvBarTitle.setTextColor(getResources().getColor(R.color.text_color));
                mIvBack.setImageResource(R.drawable.ic_blank_back);
                mIvBarRight.setImageResource(R.drawable.icon_more_black);
                if (!CommonUtils.isBlank(mItemBean.getTitle())) {
                    mTvBarTitle.setText(mItemBean.getTitle());
                }
            }
        }
    };


    static class ViewHolder {
        @BindView(R.id.tv_select_text)
        TextView mTvSelectText;
        @BindView(R.id.ll_cancel)
        LinearLayout mLlCancel;
        boolean isSubscribe;
        PopupWindow mPopupWindow;
        OnBooleanListener mBooleanListener;

        public void setBooleanListener(OnBooleanListener booleanListener) {
            mBooleanListener = booleanListener;
        }

        ViewHolder(View view, PopupWindow mPopupWindow, boolean isSubscribe) {
            ButterKnife.bind(this, view);
            this.isSubscribe = isSubscribe;
            this.mPopupWindow = mPopupWindow;
            if (isSubscribe) {
                mTvSelectText.setText("取消订阅");
            } else {
                mTvSelectText.setText("订阅");
            }
        }
        @OnClick({R.id.tv_select_text, R.id.ll_cancel})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.tv_select_text:
                    if (mBooleanListener != null) {
                        mBooleanListener.onCallback(0, isSubscribe, mTvSelectText.getText().toString());
                    }
                    mPopupWindow.dismiss();
                    break;
                case R.id.ll_cancel:
                    mPopupWindow.dismiss();
                    break;

            }
        }
    }

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        if (postResult.getTag().equals("loginIn")){
            UserInfoBean bean = (UserInfoBean) postResult.getResult();
            if (bean != null) {
                Token = bean.getToken();
            }
        }
    }
}