package com.idyoga.yoga.activity.tutor;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.TutorRecommendListAdapter;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.listener.OnItemClickListener;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.TutorListBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * 文 件 名: TutorListActivity
 * 创 建 人: By k
 * 创建日期: 2018/5/14 15:49
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class TutorListActivity extends BaseActivity implements ICommonViewUi {
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
    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    String mShopId;
    String mType = "1";
    String mMainstreamIdJson = "";

    List<TutorListBean.TutorBean> mTutorBeanList = new ArrayList<>();
    List<TutorListBean.TutorBean> mRecommendBeanList = new ArrayList<>();
    TutorRecommendListAdapter mRecommendAdapter;
    @BindView(R.id.tv_tag_a)
    TextView mTvTagA;
    @BindView(R.id.iv_tag_a)
    ImageView mIvTagA;
    @BindView(R.id.ll_tag_a)
    LinearLayout mLlTagA;
    @BindView(R.id.tv_tag_b)
    TextView mTvTagB;
    @BindView(R.id.iv_tag_b)
    ImageView mIvTagB;
    @BindView(R.id.ll_tag_b)
    LinearLayout mLlTagB;
    @BindView(R.id.tv_select_classify)
    TextView mTvSelectClassify;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar
                .flymeOSStatusBarFontColor("#333333")
                .titleBar(mLlCommonLayout)
                .init();
    }

    @Override
    protected void initData() {
        mShopId = (String) SharedPreferencesUtils.getSP(this, "shopId", "");

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_tutor_list;
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("导师列表");
        initTagEmpty();
        iCommonRequestPresenter = new CommonRequestPresenterImpl(this, this);
        toRequest(ApiConstants.EventTags.TUTOR_LIST);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRvList.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        mRvList.setLayoutManager(layoutManager);
        DelegateAdapter adapters = new DelegateAdapter(layoutManager, true);// 主 /父 adapter
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        mRecommendAdapter = new TutorRecommendListAdapter(this, mTutorBeanList, linearLayoutHelper, 1);
//        adapters.addAdapter(mRecommendAdapter);
        mRvList.setAdapter(adapters);
    }

    private void initTagEmpty() {
        mIvTagA.setImageResource(R.drawable.icon_sort);
        mIvTagB.setImageResource(R.drawable.icon_sort);
    }


    @Override
    protected void setListener() {

    }

    @Override
    public void toRequest(int eventTag) {
        showLoading("加载中...", true);
        if (eventTag == ApiConstants.EventTags.TUTOR_LIST) {
            Map map = new HashMap();
            map.put("shopId", "19287727");
            map.put("mainstreamIdJson", mMainstreamIdJson);
            map.put("type", mType);
            Logcat.i("请求参数：" + map.toString());
            iCommonRequestPresenter.request(eventTag, this, ApiConstants.Urls.TUTOR_LIST, map);
        }
    }

    @Override
    public void getRequestData(int eventTag, String result) {
        dismissLoading();
        Logcat.i("eventTag:" + eventTag + " result :" + result);
        ResultBean resultBean = JSON.parseObject(result, ResultBean.class);
        if (eventTag == ApiConstants.EventTags.TUTOR_LIST) {
            if (resultBean.getCode().equals("1") && resultBean.getData() != null) {
                TutorListBean tutorListBean = JSON.parseObject(resultBean.getData(), TutorListBean.class);
                if (tutorListBean.getTutor() != null && tutorListBean.getTutor().size() > 0) {
                    mTutorBeanList.addAll(tutorListBean.getTutor());
                }
                if (tutorListBean.getRecommendTutor() != null && tutorListBean.getRecommendTutor().size() > 0) {
//                    mRecommendBeanList.addAll(tutorListBean.getRecommendTutor());
                }
            }
        }
    }


    @Override
    public void onRequestFailureException(int eventTag, String msg) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logcat.i("requestCode:" + requestCode + "/" + requestCode);
        if (requestCode == 700 && resultCode == 700) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    String json = bundle.getString("tagList");
                    if (json != null) {
                        mTvSelectClassify.setTextColor(Color.parseColor("#b86caf"));
                        mMainstreamIdJson = json;
                        mTutorBeanList.clear();
                        mRecommendBeanList.clear();
                        toRequest(ApiConstants.EventTags.TUTOR_LIST);
                    }
                }
            }
        }
    }

    int tagA = 1, tagB = 1;

    @OnClick({R.id.ll_title_back, R.id.ll_tag_a, R.id.ll_tag_b, R.id.tv_select_classify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.ll_tag_a:
                if (tagA == 0) {
                    mType = "2";
                    mIvTagA.setImageResource(R.drawable.icon_sort_up);
                    mTutorBeanList.clear();
                    mRecommendBeanList.clear();
                    toRequest(ApiConstants.EventTags.TUTOR_LIST);
                } else if (tagA == 1) {
                    mType = "1";
                    mIvTagA.setImageResource(R.drawable.icon_sort_down);
                    mTutorBeanList.clear();
                    mRecommendBeanList.clear();
                    toRequest(ApiConstants.EventTags.TUTOR_LIST);
                }
                tagA = (tagA + 1) % 2;
                break;
            case R.id.ll_tag_b:
                if (tagB == 0) {
                    mType = "4";
                    mIvTagB.setImageResource(R.drawable.icon_sort_up);
                    mTutorBeanList.clear();
                    mRecommendBeanList.clear();
                    toRequest(ApiConstants.EventTags.TUTOR_LIST);
                } else if (tagB == 1) {
                    mType = "3";
                    mIvTagB.setImageResource(R.drawable.icon_sort_down);
                    mTutorBeanList.clear();
                    mRecommendBeanList.clear();
                    toRequest(ApiConstants.EventTags.TUTOR_LIST);
                }
                tagB = (tagB + 1) % 2;
                break;
            case R.id.tv_select_classify:
                Intent intent = new Intent(this, TutorSelectActivity.class);
                startActivityForResult(intent, 700);
                break;
        }
    }

}