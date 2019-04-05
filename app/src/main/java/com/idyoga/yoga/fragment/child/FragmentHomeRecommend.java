package com.idyoga.yoga.fragment.child;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.MainActivity;
import com.idyoga.yoga.activity.shop.YogaShoppingActivity;
import com.idyoga.yoga.activity.web.YogaWebActivity;
import com.idyoga.yoga.adapter.HFRecyclerviewAdapter;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.presenter.ICommonRequestPresenter;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.listener.OnVerticalScrollListener;
import com.idyoga.yoga.listener.YogaRvScrollerListener;
import com.idyoga.yoga.model.AdvertiBean;
import com.idyoga.yoga.model.HomePageData;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.UserSignInBean;
import com.idyoga.yoga.utils.DateUtils;
import com.idyoga.yoga.utils.ShowAdverti;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.view.YogaLayoutManager;
import com.idyoga.yoga.view.dialog.AdvertiDialog;
import com.idyoga.yoga.view.loading.ValidateSmsDialog;
import com.idyoga.yoga.view.loading.WaitViewController;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import de.greenrobot.event.EventBus;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

public class FragmentHomeRecommend extends BaseFragment implements ICommonViewUi {
    @BindView(R.id.srl_Refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.layout_fragments)
    LinearLayout mLayoutF;
    private boolean isTop =true;
    private String cityId;
    private String mUserId;
    private HFRecyclerviewAdapter mAdapter;
    private String mLatitude;
    private String mLongitude;
    private int mPage = 1;
    private HomePageData mHomeBean;
    private boolean mIsLoadMore = false;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.flymeOSStatusBarFontColor("#333333").statusBarDarkFont(true).init();
    }

    @Override
    protected ICommonRequestPresenter initICommonViewUi() {
        return iCommonRequestPresenter = new CommonRequestPresenterImpl(mActivity, this);
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(mSrlRefresh);
    }

    @Override
    protected void initData() {
        super.initData();
        Logcat.i("-----------initData() --- " + getActivity());
        toRequest(900);
        toRequest(911);
        if (!StringUtil.isEmpty(mUserId)) {
            toRequest(901);
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home_frecommend;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mLayoutManager.showLoading();
        WaitViewController.from(getStateViewRoot()).renderChilds();
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvList.setLayoutManager(layoutManager);
        mAdapter = new HFRecyclerviewAdapter(getActivity());
        mRvList.setAdapter(mAdapter);
    }

    @Override
    protected void onFirstVisible() {
        super.onFirstVisible();
    }

    @Override
    public void onResume() {
        super.onResume();
        toRequest(900);
    }

    @Override
    protected void onVisible() {
        super.onVisible();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logcat.i("setUserVisibleHint:"+isVisibleToUser+"/"+isTop);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                toRequest(900);
                mPage = 1;
                mSrlRefresh.setRefreshing(false);
            }
        });
        mRvList.addOnScrollListener(new OnVerticalScrollListener(){
            @Override
            public void onScrolledToBottom() {
                super.onScrolledToBottom();
                mIsLoadMore = true;
                mPage += 1;
                toRequest(900);
            }
        });
    }

    private void initViewData(HomePageData homeRecommendBean) {
        HomePageData.JumpUrlArrBean jumpUrlArr = homeRecommendBean.getJumpUrlArr();
        HomePageData.UserDataBean userData = homeRecommendBean.getUserData();
        if (userData != null && jumpUrlArr != null){
            userData.setSignUrl(jumpUrlArr.getSignUrl());
        }
        if (homeRecommendBean.getUserData() == null){
            HomePageData.UserDataBean userDataBean = new HomePageData.UserDataBean();
            userDataBean.setLogin(false);
            homeRecommendBean.setUserData(userDataBean);
        }else{
            homeRecommendBean.getUserData().setLogin(true);
        }
        if (mIsLoadMore){
            mAdapter.addData(mHomeBean);
            mIsLoadMore = false;
        }else{
            mAdapter.setDatas(homeRecommendBean);
        }
        WaitViewController.from(getStateViewRoot()).removeChilds();
    }

    @Override
    public void toRequest(int eventTag) {
        Logcat.i("网络请求：" + eventTag);
        cityId = (String) SharedPreferencesUtils.getSP(mActivity, "cityId", "");
        mLatitude = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "latitude", "");
        mLongitude = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "longitude", "");
        mUserId = String.valueOf((int) SharedPreferencesUtils.getSP(mActivity, "UserId", 0));
        Map map = new HashMap<>();
        if (eventTag == 900) {
            map.put("userId", mUserId + "");
            map.put("cityId", cityId + "");
            map.put("lat", mLatitude + "");
            map.put("long", mLongitude + "");
            map.put("page", mPage + "");
            map.put("size", "10");
            iCommonRequestPresenter.requestGet(eventTag, mActivity, ApiConstants.Urls.HOME_PAGE_DATA, map);
        } else if (eventTag == ApiConstants.EventTags.HOME_SUBJECT_CLOCK) {
            showLoading("签到中...");
            map.put("userId", mUserId + "");
            Logcat.i("签到数据：" + map.toString() + "/" + ApiConstants.Urls.HOME_SUBJECT_CLOCK);
            iCommonRequestPresenter.requestGet(eventTag, mActivity, ApiConstants.Urls.HOME_USER_CLOCK, map);
        } else if(eventTag == 911){
            map.put("cityId", cityId + "");
            map.put("type", "0");
            iCommonRequestPresenter.requestGet(eventTag, mActivity, ApiConstants.Urls.YOGA_ADVERTI, map);
        }
    }

    @Override
    public void getRequestData(int eventTag, String result) {
        Logcat.i("网络请求：" + eventTag + "/" + result);
        dismissLoading();
        ResultBean resultBean = JSON.parseObject(result, ResultBean.class);
        if (resultBean.getCode().equals("1")) {
            if (eventTag == 900) {
                Logcat.i("json:" + result);
                //HomeRecommendBean homeRecommendBean = JSON.parseObject(resultBean.getData(), HomeRecommendBean.class);
                mHomeBean = JSON.parseObject(resultBean.getData(), HomePageData.class);
                if (mHomeBean != null) {
                    initViewData(mHomeBean);
                }
            } else if (eventTag == ApiConstants.EventTags.HOME_SUBJECT_CLOCK) { //
                final UserSignInBean userSignInBean = JSON.parseObject(resultBean.getData(), UserSignInBean.class);
                Logcat.i("---------------mUserSignInBean" + userSignInBean.toString());
                EventBus.getDefault().post(new PostResult("homeSignInResult",userSignInBean));
            } else if(eventTag == 911){
                AdvertiBean adverti = JSON.parseObject(resultBean.getData(), AdvertiBean.class);
                //HomeAdverti
                ShowAdverti.getInstance().showAdverti(adverti,getActivity(),"HomeAdverti");
            }
        } else {
            ToastUtil.showToast(resultBean.getMsg());
        }
    }

    @Override
    public void onRequestFailureException(int eventTag, String msg) {
        mLayoutManager.showNetError();
    }

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        Logcat.i("-----------onEvent()" + postResult);
        if (postResult.getTag().equals("homeSignIn")) {
            toRequest(ApiConstants.EventTags.HOME_SUBJECT_CLOCK);
        } else if (postResult.getTag().equals("exchangerPoint")){ //去兑换
            String signUrl = mHomeBean.getJumpUrlArr().getSignUrl();
            Bundle bundle = new Bundle();
            bundle.putString("url",signUrl);
            startActivityWithExtras(YogaShoppingActivity.class,bundle);
        } else if(postResult.getTag().equals("advertiDetail")){
            //跳去广告页
            Bundle bundle = new Bundle();
            bundle.putString("getUrl",(String) postResult.getResult());
            startActivityWithExtras(YogaWebActivity.class,bundle);
        } else if(postResult.getTag().equals("bannerDetail")){
            startActivityWithExtras(YogaWebActivity.class, (Bundle) postResult.getResult());
        }
    }

    /*
    private void initVLayout() {
        mAdapters = new LinkedList<>();
        layoutManager = new VirtualLayoutManager(mActivity);
        mRvList.setLayoutManager(layoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRvList.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(1, 20);
        viewPool.setMaxRecycledViews(2, 20);
        viewPool.setMaxRecycledViews(3, 20);
        viewPool.setMaxRecycledViews(4, 20);
        viewPool.setMaxRecycledViews(5, 20);
        delegateAdapter = new DelegateAdapter(layoutManager, true);
        mRvList.setAdapter(delegateAdapter);
        initAllTypeView();
        delegateAdapter.setAdapters(mAdapters);
        mRvList.setAdapter(delegateAdapter);
    }

    //初始化所有类型的view
    private void initAllTypeView() {
        initBannerView();
        initMenuView();
        initHotViewHead();
        initHotVideoView();
        initOtherViewHead();
        initOtherVideoView();
    }

    //view  头部
    private void initHotViewHead() {
        mHotHeadAdapter = new BaseDelegateAdapter(mActivity, new LinearLayoutHelper(), R.layout.item_home_view_head, 0, 5) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.setText(R.id.tv_head_name, "热门精品");
            }
        };
        mAdapters.add(mHotHeadAdapter);
    }

    //hot video course
    private void initHotVideoView() {
        StaggeredGridLayoutHelper layoutHelper = new StaggeredGridLayoutHelper(2);
        mHotVideoAdapter = new BaseDelegateAdapter(mActivity, layoutHelper, R.layout.item_video_type_5, 0, 3) {
            @Override
            public void onBindViewHolder(BaseViewHolder helper, int position) {
                super.onBindViewHolder(helper, position);
                ViewGroup.LayoutParams layoutParams = helper.itemView.getLayoutParams();
                layoutParams.width = YogaViewUtil.getScreenWidth(mActivity) / 2;
                if (position == 0) {
                    layoutParams.height = YogaViewUtil.dp2px(mActivity, 250);
                } else if (position == 1) {
                    layoutParams.height = YogaViewUtil.dp2px(mActivity, 145);
                } else if (position == 2) {
                    layoutParams.height = YogaViewUtil.dp2px(mActivity, 105);
                }
                helper.itemView.setLayoutParams(layoutParams);
                Glide.with(mActivity).load(mHotVideoListBean.get(position).getImage_url()).placeholder(R.drawable.img_course).error(R.drawable.img_course).into((ImageView) helper.getView(R.id.iv_img));
                helper.setText(R.id.tv_title, mHotVideoListBean.get(position).getTitle()).setText(R.id.tv_number, mHotVideoListBean.get(position).getNumber() + "人已学习");
            }
        };
        mAdapters.add(mHotVideoAdapter);
    }

    //view  头部
    private void initOtherViewHead() {
        mOtherVideoAdapter= new BaseDelegateAdapter(mActivity, new LinearLayoutHelper(), R.layout.item_home_view_head, 0, 5) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.setText(R.id.tv_head_name, "推荐系列");
            }
        };
        mAdapters.add(mOtherVideoAdapter);
    }

    //Other video course
    private void initOtherVideoView() {
        GridLayoutHelper layoutHelper = new GridLayoutHelper(2);
        layoutHelper.setBgColor(Color.WHITE);
        //        layoutHelper.setMarginLeft(YogaViewUtil.dp2px(mActivity, 12));
        //        layoutHelper.setMarginRight(YogaViewUtil.dp2px(mActivity, 12));
        layoutHelper.setPaddingLeft(YogaViewUtil.dp2px(mActivity, 12));
        layoutHelper.setPaddingRight(YogaViewUtil.dp2px(mActivity, 12));
        layoutHelper.setAutoExpand(false);
        layoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int tag = getStartPosition() + 2;
                if (position < tag) {
                    return 1;
                } else {
                    return 2;
                }
            }
        });
        mOtherVideoAdapter = new BaseDelegateAdapter(mActivity, layoutHelper, R.layout.item_video_type_6, 0, 3) {
            @Override
            public void onBindViewHolder(BaseViewHolder helper, int position) {
                super.onBindViewHolder(helper, position);
                if (position < 2) {
                    helper.getView(R.id.rl_layout).setVisibility(View.VISIBLE);
                    helper.getView(R.id.ll_layout_item).setVisibility(View.GONE);
                    Glide.with(mActivity).load(mOtherVideoListBean.get(position).getImage_url()).placeholder(R.drawable.img_course).error(R.drawable.img_course).into((ImageView) helper.getView(R.id.iv_img_1));
                    helper.setText(R.id.tv_title, mOtherVideoListBean.get(position).getTitle());
                    if (position == 0) {
                        helper.getConvertView().setPadding(0, 0, YogaViewUtil.dp2px(mActivity, 5), 0);
                    } else if (position == 1) {
                        helper.getConvertView().setPadding(YogaViewUtil.dp2px(mActivity, 5), 0, 0, 0);
                    }
                } else {
                    helper.getView(R.id.rl_layout).setVisibility(View.GONE);
                    helper.getView(R.id.ll_layout_item).setVisibility(View.VISIBLE);
                    helper.setText(R.id.tv_title_2, mOtherVideoListBean.get(position).getTitle())
                            .setText(R.id.tv_course_tutor, mOtherVideoListBean.get(position).getTutor_name())
                            .setText(R.id.tv_course_time, "时长:" + DateUtils.secToTime(mOtherVideoListBean.get(position).getTime()) + "");
                    Glide.with(mActivity).load(mOtherVideoListBean.get(position).getImage_url()).placeholder(R.drawable.img_course).error(R.drawable.img_course).into((ImageView) helper.getView(R.id.iv_img_2));
                }
            }
        };
        mAdapters.add(mOtherVideoAdapter);
    }


    //banner
    private void initBannerView() {
        mBannerAdapter = new BaseDelegateAdapter(mActivity, new LinearLayoutHelper(), R.layout.layout_banner, 1, 1) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                YogaBanner banner = holder.getView(R.id.bv_view);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) banner.getLayoutParams();
                layoutParams.height = DensityUtil.dp2px(mActivity, 120);
                banner.setLayoutParams(layoutParams);
                banner.setBannerStyle(1);
                banner.setImageLoader(new BannerGlideImageLoader());
                banner.setImages(mBvList);
                banner.isAutoPlay(true);
                banner.setDelayTime(3000);
                banner.setIndicatorGravity(BannerConfig.CENTER);
                banner.setLeftRightPadding(ViewUtil.dp2px(getContext(),20));
                banner.setLeftRightMargin(ViewUtil.dp2px(getContext(),10));
                banner.start();
                bannerScrollEvent(banner);
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Bundle bundle = new Bundle();
                        bundle.putString("getUrl", homeRecommendBean.getBannerList().get(position).getUrl());
                        startActivityWithExtras(YogaWebActivity.class, bundle);
                    }
                });
            }
        };
        mAdapters.add(mBannerAdapter);
    }

    private void bannerScrollEvent(YogaBanner banner){
        //设置viewPager 滑动过程中的page大小变化
        banner.setBannerPageTransformer(new ViewPager.PageTransformer() {

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
    }

    //菜单
    private void initMenuView() {
        mMenuAdapter = new BaseDelegateAdapter(mActivity, new LinearLayoutHelper(), R.layout.layout_home_menu, 1, 1) {
            @Override
            public void onBindViewHolder(final BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                //                ProgressBar mProgressBar = holder.getView(R.id.seek_bar);
                ImageView imageView = holder.getView(R.id.iv_home_hp);
                TextView mTvSignIn = holder.getView(R.id.tv_sign_in);
                RelativeLayout mRlStudyCourse = holder.getView(R.id.rl_study_course);
                if (!LoginUtil.checkLogin(mActivity, false)) {
                    Glide.with(mActivity).load(R.drawable.img_06).into(imageView);
                    holder.getView(R.id.rl_user_state).setVisibility(View.GONE);
                    holder.getView(R.id.tv_go_login).setVisibility(View.VISIBLE);
                } else {
                    if (null != mUserSignInfoBean) {
                        String isClock = mUserSignInfoBean.getIsClock();
                        if (mUserSignInfoBean != null && !StringUtil.isEmpty(isClock) && isClock.equals("1")) {
                            mTvSignIn.setText("已打卡");
                            mTvSignIn.setBackgroundResource(R.drawable.bg_a_10);
                            mTvSignIn.setEnabled(false);
                        } else {
                            mTvSignIn.setText("打卡");
                            mTvSignIn.setEnabled(true);
                        }
                    }
                    holder.getView(R.id.rl_user_state).setVisibility(View.VISIBLE);
                    holder.getView(R.id.tv_go_login).setVisibility(View.GONE);
                    if (signTag.equals("a") && mUserSignInfoBean != null) {
                        holder.setText(R.id.tv_name, mUserSignInfoBean.getName() + "");
                        GlideImgManager.glideLoader(mActivity, mUserSignInfoBean.getHead_pic(), imageView);//头像
                        Logcat.i("加载签到数据：" + signTag);
                        //                        holder.setText(R.id.tv_sign_num, "累计打卡" + mUserSignInfoBean.getUserClockCount() + "天");
                        holder.setText(R.id.tv_points, mUserSignInfoBean.getIntegralNumber() + "积分");
                        holder.setText(R.id.tv_num_day, mUserSignInfoBean.getUserClockNumber() + "/7");
                        //                        mProgressBar.setProgress(mUserSignInfoBean.getUserClockNumber());
                    } else if (!signTag.equals("a") && mUserSignInBean != null) {
                        signTag = "a";
                        //                        holder.setText(R.id.tv_sign_num, "累计打卡" + (mUserSignInfoBean.getUserClockCount() + 1) + "天");
                        holder.setText(R.id.tv_points, mUserSignInBean.getUserIntegralNumber() + " 积分");
                        holder.setText(R.id.tv_num_day, mUserSignInBean.getUserClockNumber() + "/7");
                        //                        mProgressBar.setProgress(mUserSignInBean.getUserClockNumber());
                        mTvSignIn.setText("已打卡");
                        mTvSignIn.setBackgroundResource(R.drawable.bg_a_10);
                        mTvSignIn.setEnabled(false);
                    }
                }
                View.OnClickListener clickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (view.getId()) {
                            case R.id.tv_menu_1:
                                Bundle bundle = new Bundle();
                                bundle.putString("getUrl", "http://wxyoga.hq-xl.com/contact/index");
                                startActivityWithExtras(ConsultActivity.class, bundle);
                                break;
                            case R.id.tv_menu_2:
                                HomeFragment homeFragment = HomeFragment.getInstance();
                                if (homeFragment != null) {
                                    homeFragment.setHomeTabs(1);
                                }
                                MainActivity activity = (MainActivity) getActivity();
                                if (activity != null){
                                    activity.showFragment(2);
                                }
                                break;
                            case R.id.tv_menu_3:

                                Intent intent = new Intent(getActivity(), YogaShoppingActivity.class);
                                startActivity(intent);
                                MainActivity mainActivity = (MainActivity) mActivity;
                                mainActivity.showFragment(2);
                                break;
                            case R.id.tv_sign_in:
                                showLoading("签到中...");
                                toRequest(ApiConstants.EventTags.HOME_SUBJECT_CLOCK);
                                break;
                            case R.id.iv_home_hp:
                                if (!LoginUtil.checkLogin(mActivity)) return;
                                break;
                            case R.id.tv_go_login:
                                if (!LoginUtil.checkLogin(mActivity)) return;
                                break;
                            case R.id.rl_study_course:
                                if (!LoginUtil.checkLogin(mActivity)) return;
                                startActivityWithoutExtras(CourseActivity.class);
                                break;
                        }
                    }
                };
                imageView.setOnClickListener(clickListener);
                holder.getView(R.id.tv_menu_1).setOnClickListener(clickListener);
                holder.getView(R.id.tv_menu_2).setOnClickListener(clickListener);
                holder.getView(R.id.tv_menu_3).setOnClickListener(clickListener);
                holder.getView(R.id.tv_sign_in).setOnClickListener(clickListener);
                holder.getView(R.id.tv_go_login).setOnClickListener(clickListener);
                holder.getView(R.id.rl_study_course).setOnClickListener(clickListener);
            }
        };
        mAdapters.add(mMenuAdapter);
    }
    */

    /* mOtherVideoAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int type, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("videoId", mOtherVideoListBean.get(position).getId() + "");
                startActivityWithExtras(SubjectListActivity.class, bundle);
            }
        });
        mHotVideoAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int type, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("videoId", mHotVideoListBean.get(position).getId() + "");
                startActivityWithExtras(SubjectListActivity.class, bundle);
            }
        });
        mRvList.addOnScrollListener(new OnVerticalScrollListener() {
            @Override
            public void onScrolledToTop() {
                super.onScrolledToTop();
                isTop=true;
                EventBus.getDefault().post(new PostResult("toTop", "0"));
            }

            @Override
            public void onScrolledDown(int dx, int dy) {
                super.onScrolledDown(dx, dy);
                Logcat.i("--------------dx:" + dx + "/" + dy);
                if (dy > YogaViewUtil.getScreenWidth(mActivity)) {
                    isTop=false;
                    EventBus.getDefault().post(new PostResult("toTop", "1"));
                }
            }
        });*/
}
