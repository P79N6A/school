package com.idyoga.yoga.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.home.SubjectListActivity;
import com.idyoga.yoga.activity.loading.SetCityActivity;
import com.idyoga.yoga.activity.search.SearchLeadActivity;
import com.idyoga.yoga.activity.video.VideoDetailActivity;
import com.idyoga.yoga.adapter.VideoFragmentAdapter;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.model.HomePageData;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.VideoHomeData;
import com.idyoga.yoga.utils.BannerGlideImageLoader;
import com.idyoga.yoga.utils.ViewUtil;
import com.idyoga.yoga.view.RecycleViewDivider;
import com.idyoga.yoga.view.YogaBanner;
import com.idyoga.yoga.view.YogaLayoutManager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import vip.devkit.library.DensityUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;
import vip.devkit.view.common.banner.BannerConfig;
import vip.devkit.view.common.banner.listener.OnBannerListener;

/**
 * 作者 by K
 * 时间：on 2018/2/20
 * 邮箱 by  vip@devkit.vip
 * <p/>
 * 类用途：
 * 最后修改：
 */
public class VideoFragment extends BaseFragment {
    @BindView(R.id.iv_address)
    ImageView mIvAddress;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.ll_address)
    LinearLayout mLlAddress;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.ll_search_title)
    RelativeLayout mLlSearchTitle;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.srl_Refresh)
    SwipeRefreshLayout mSrlRefresh;
    /*@BindView(R.id.bv_view)
    YogaBanner mBvView;*/
    /*@BindView(R.id.ll_head_banner)
    LinearLayout mLlHeadBanner;*/
    /*@BindView(R.id.abl_layout)
    AppBarLayout mAblLayout;*/
    /*@BindView(R.id.cl_layout)
    CoordinatorLayout mClLayout;*/
    Unbinder unbinder;
    private String cityName;
    private String mCityId;
    private VideoFragmentAdapter mAdapter;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlSearchTitle)
                .keyboardEnable(true)
                .flymeOSStatusBarFontColor("#333333")
                .init();
    }

    @Override
    protected void initData() {
        super.initData();
        Logcat.i("Item:" + getUserVisibleHint());
        cityName = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "cityName", "");
        getData();
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(mRvList);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_course;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mLayoutManager.showLoading();
        initRecyclerView();
        mCityId = (String) SharedPreferencesUtils.getSP(getContext(), "cityId", "");
        mTvAddress.setText(StringUtil.isEmpty(cityName) ? "" : cityName);
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvList.setLayoutManager(layoutManager);
        //mRvList.addItemDecoration(new RecycleViewDivider(getContext(),LinearLayoutManager.HORIZONTAL,ViewUtil.dp2px(getContext(),10), Color.parseColor("#f8f8f8")));
        mAdapter = new VideoFragmentAdapter(getContext());
        mRvList.setAdapter(mAdapter);
    }

    @Override
    protected void onFirstVisible() {
        super.onFirstVisible();
        if (mLayoutManager != null) {
            mLayoutManager.showLoading();
        }
    }

    @Override
    protected void setListener() {
        super.setListener();
        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
        mRvList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int i = (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                mSrlRefresh.setEnabled(i <= 0);
                //super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                SubjectListActivity.class
            }
        });

        /*mType0Adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int type, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("videoId", mType0List.get(position).getId() + "");
                startActivityWithExtras(SubjectListActivity.class, bundle);
            }
        });
        mType1Adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int type, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("videoId", mType1List.get(position).getId() + "");
                startActivityWithExtras(SubjectListActivity.class, bundle);
            }
        });
        mType2Adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int type, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("videoId", mType2List.get(position).getId() + "");
                startActivityWithExtras(SubjectListActivity.class, bundle);
            }
        });
        mType3Adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int type, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("videoId", mType3List.get(position).getId() + "");
                startActivityWithExtras(SubjectListActivity.class, bundle);
            }
        });*/
    }

    @OnClick({R.id.ll_address, R.id.et_search, R.id.iv_search})
    public void onViewClicked(View view) {
        Bundle bundle;
        switch (view.getId()) {
            case R.id.ll_address:
                bundle = new Bundle();
                bundle.putString("fromTag", "other");
                startActivityWithExtras(SetCityActivity.class, bundle);
                break;
            case R.id.et_search:
                startActivityWithoutExtras(SearchLeadActivity.class);
                break;
            case R.id.iv_search:
                startActivityWithoutExtras(SearchLeadActivity.class);
                break;
        }
    }

    private void initViewData(VideoHomeData bean) {
        /*final List<HomePageData.BannerBean> banners = bean.getBanner();
        ArrayList<String> strings = new ArrayList<>();
        for (HomePageData.BannerBean banner:banners){
            String image_url = banner.getImage_url();
            strings.add(image_url);
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mBvView.getLayoutParams();
        layoutParams.height = DensityUtil.dp2px(getContext(), 120);
        mBvView.setLayoutParams(layoutParams);
        mBvView.setBannerStyle(1);
        mBvView.setImageLoader(new BannerGlideImageLoader());
        mBvView.setImages(strings);
        mBvView.isAutoPlay(true);
        mBvView.setDelayTime(3000);
        mBvView.setYogaClipToPadding(false);
        mBvView.setIndicatorGravity(BannerConfig.CENTER);
        mBvView.setLeftRightPadding(ViewUtil.dp2px(getContext(),20));
        mBvView.setLeftRightMargin(ViewUtil.dp2px(getContext(),10));
        mBvView.start();
        bannerScrollEvent();
        mBvView.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("getUrl", banners.get(position).getUrl());
                //startActivityWithExtras(YogaWebActivity.class, bundle);
            }
        });*/
//        mAdapter.setDatas(bean.getVideo());
        mAdapter.setDatas(bean);
        mLayoutManager.showContent();
    }

    /*private void bannerScrollEvent(){
        //设置viewPager 滑动过程中的page大小变化
        mBvView.setBannerPageTransformer(new ViewPager.PageTransformer() {
            private static final float MIN_SCALE = 0.85f;

            @Override
            public void transformPage(View page, float position) {
                if (position < -1 || position > 1) {
                    page.setScaleY(MIN_SCALE);

                } else if (position <= 1) { // [-1,1]  [-1,0] [0,1]
                    if (position < 0) {
                        float scaleX = 1 + 0.15f * position;
                        page.setScaleY(scaleX);
                    } else {
                        float scaleX = 1 - 0.15f * position;
                        page.setScaleY(scaleX);
                    }
                }
            }
        });
    }*/

    public void getData() {
        Map map = new HashMap();
        map.put("cityId", mCityId + "");
        OkHttpUtils.post().url(ApiConstants.Urls.VIDEO_HOME_PAGE).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mSrlRefresh.setRefreshing(false);
                mLayoutManager.showNetError();
            }

            @Override
            public void onResponse(String response, int id) {
                mSrlRefresh.setRefreshing(false);
                ResultBean bean = JSON.parseObject(response, ResultBean.class);
                if (bean.getCode().equals("1")) {
                    VideoHomeData videoHomeData = JSON.parseObject(bean.getData(), VideoHomeData.class);
                    if (videoHomeData != null) {
                        List<VideoHomeData.VideoBean> videos = videoHomeData.getVideo();
                        if (videos != null){
                            for (VideoHomeData.VideoBean video:videos){
                                video.setBeanType(0);
                            }
                        }
                        initViewData(videoHomeData);
                    }
                }
            }
        });
    }

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        if (postResult.getTag().equals("toVideoDetail")){
            startActivityWithExtras(VideoDetailActivity.class, (Bundle) postResult.getResult());
        }
    }
}
