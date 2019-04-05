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
package com.idyoga.yoga.activity;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.HFDemoRecyclerviewAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.model.HomePageData;

import java.util.List;

import butterknife.BindView;
import vip.devkit.library.Logcat;

/**
 * 文 件 名: TestActivity
 * 创 建 人: By k
 * 创建日期: 2018/3/25 15:53
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class TestActivity extends BaseActivity {

    /*@BindView(R.id.rlv)
    RecyclerView mRlv;*/

    private String mCityId;
    String mUserId;
    String mLatitude;
    String mLongitude;
    private int mPage = 1;
    private HFDemoRecyclerviewAdapter mAdapter;

    @Override
    protected void initData() {
        /*mCityId = (String) SharedPreferencesUtils.getSP(this, "cityId", "");
        mUserId = String.valueOf((int) SharedPreferencesUtils.getSP(this, "UserId", 0));
        mLatitude = (String) SharedPreferencesUtils.getSP(this, "latitude", "");
        mLongitude = (String) SharedPreferencesUtils.getSP(this, "longitude", "");
        toRequest();*/
    }

    @Override
    protected int setLayoutId() {
        return R.layout.a_test;
    }


    @Override
    protected void initView() {
        /*mAdapter = new HFDemoRecyclerviewAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRlv.setLayoutManager(manager);
        mRlv.setAdapter(mAdapter);*/
    }

    private boolean isLoadMore = false;

    @Override
    protected void setListener() {
        /*mRlv.setOnScrollListener(new RecyclerView.OnScrollListener() {

            private int mLastPosition;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = manager.getItemCount();
                int visibleItemCount = manager.getChildCount();
                if (visibleItemCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE && mLastPosition >= totalItemCount - 1){
                    //mAdapter.setShowLoadMore(true);
                    isLoadMore = true;
                    onLoadMore();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                mLastPosition = layoutManager.findLastVisibleItemPosition();
            }
        });*/
    }

    private void onLoadMore() {
        mPage += 1;
        toRequest();
    }

    private void toRequest() {
        /*Map map = new HashMap<>();
        map.put("userId", mUserId + "");
        map.put("cityId", mCityId + "");
        map.put("lat", mLatitude + "");
        map.put("long", mLongitude + "");
        map.put("page", mPage + "");
        map.put("size", "10");
        OkHttpUtils.get().url(ApiConstants.Urls.EQUITY_COURSE_PAGE_DATA).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoading();
            }

            @Override
            public void onResponse(String response, int id) {
                Logcat.e("权益课课 getHeadInfo :" + mCityId + "/" + response);
                dismissLoading();
                response = response.replace("\"data\":[]", "\"data\":null");
                ResultBean resultBean = JSON.parseObject(response, ResultBean.class);
                if ("1".equals(resultBean.getCode())){
                    HomePageData mHomeBean = JSON.parseObject(resultBean.getData(), HomePageData.class);
                    if (mHomeBean != null) {
                        initViewData(mHomeBean);
                    }
                }
            }
        });*/
    }

    private void initViewData(HomePageData mHomeBean) {
        //mAdapter
        HomePageData.JumpUrlArrBean jumpUrlArr = mHomeBean.getJumpUrlArr();
        HomePageData.UserDataBean userData = mHomeBean.getUserData();
        if (userData != null && jumpUrlArr != null){
            userData.setSignUrl(jumpUrlArr.getSignUrl());
        }
        //mAdapter.setShowLoadMore(false);
        Logcat.i("-------------------------------------------isLoadMore = " + isLoadMore);
        if (!isLoadMore){
            mAdapter.setDatas(mHomeBean);
        }else{
            mAdapter.addData(mHomeBean);
            isLoadMore = false;
        }
//        WaitViewController.from(getStateViewRoot()).removeChilds();
    }

    class MyAdapter extends BaseMultiItemQuickAdapter<Ad,BaseViewHolder>{

        public MyAdapter(List<Ad> data) {
            super(data);
            //addItemType();
        }

        @Override
        protected void convert(BaseViewHolder helper, Ad item) {

        }
    }

    class Ad implements MultiItemEntity {

        @Override
        public int getItemType() {
            return 0;

        }
    }
}
