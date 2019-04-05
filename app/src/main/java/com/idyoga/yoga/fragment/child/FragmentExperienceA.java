package com.idyoga.yoga.fragment.child;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.course.ExperienceCourseClassifyActivity;
import com.idyoga.yoga.activity.course.ExperienceCourseListActivity;
import com.idyoga.yoga.activity.home.AppointmentIntroductionActivity;
import com.idyoga.yoga.activity.shop.ShopDetailActivity;
import com.idyoga.yoga.activity.web.YogaWebActivity;
import com.idyoga.yoga.adapter.ExperienceCourseAdapter;
import com.idyoga.yoga.adapter.ExperienceShopAdapter;
import com.idyoga.yoga.adapter.GuidePageAdapter;
import com.idyoga.yoga.adapter.base.BaseDelegateAdapter;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.presenter.ICommonRequestPresenter;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.listener.OnItemClickListener;
import com.idyoga.yoga.listener.OnVerticalScrollListener;
import com.idyoga.yoga.model.HomeExperienceBean;
import com.idyoga.yoga.model.HomeExperienceCourseBean;
import com.idyoga.yoga.model.HomeExperienceShopBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.address.AddressBean;
import com.idyoga.yoga.utils.BannerGlideImageLoader;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.DialogUtil;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.view.CustomScrollView;
import com.idyoga.yoga.view.CustomViewPager;
import com.idyoga.yoga.view.RecycleViewDivider;
import com.idyoga.yoga.view.YogaLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import vip.devkit.library.DensityUtil;
import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.NetUtils;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.view.common.banner.BannerConfig;
import vip.devkit.view.common.banner.BannerV;
import vip.devkit.view.common.banner.listener.OnBannerListener;
import vip.devkit.view.common.tab.AdvancedPagerSlidingTabStrip;

import static com.idyoga.yoga.view.LoadMoreListView.GONE;
import static com.idyoga.yoga.view.LoadMoreListView.VISIBLE;

/**
 * 文 件 名: FragmentExperience
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/17
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class FragmentExperienceA extends BaseFragment implements ICommonViewUi {
    @BindView(R.id.srl_Refresh)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.bv_view)
    BannerV mBannerV;
    @BindView(R.id.gv_view)
    GridView mGvView;
    @BindView(R.id.iv_top)
    ImageView mIvTop;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.sv_view)
    NestedScrollView mSvView;
//    @BindView(R.id.ll_list_layout)
//    LinearLayout mLayout;
    DelegateAdapter adapters;
    VirtualLayoutManager mVirtualLayoutManager;
    List<DelegateAdapter.Adapter> mAdapters;
    List<String> mBvList = new ArrayList<>();
    List<HomeExperienceBean.TagListBean> mTagListBeanList = new ArrayList<>();
    List<HomeExperienceBean.RecommendCourseList> mMarketCourseBeanList = new ArrayList<>();
    List<HomeExperienceCourseBean> mCourseBeanList = new ArrayList<>();
    List<HomeExperienceShopBean> mShopBeanList = new ArrayList<>();
    private BaseDelegateAdapter mBannerAdapter, mMenuAdapter, mListAdapter, mTabListAdapter;
    private HomeExperienceBean mExperienceBean;
    private String cityId = "";
    private String latitude;
    private String longitude;
    private String mShopId = "";
    private int coursePageIndex = 1, shopPageIndex = 1;
    private boolean isShopLoadMore = true, isCourseLoadMore = true;
    private boolean isLoadTag;
    private View viewShop, viewCourse;
    private ListView mLvShopList, mLvCourseList;
    private RecyclerView mRvCourseList, mRvShopList;
    private ExperienceShopAdapter mShopAdapter;
    private ExperienceCourseAdapter mCourseAdapter;
    private CommonAdapter mCommonAdapter, courseAdapter;
    private Map<Integer, List<HomeExperienceShopBean>> shopMap = new HashMap<>();
    private String getShopId;
    BaseQuickAdapter mAdapter;

    @Override
    protected ICommonRequestPresenter initICommonViewUi() {
        return iCommonRequestPresenter = new CommonRequestPresenterImpl(mActivity, this);
    }

    @Override
    protected void initData() {
        mShopId = (String) SharedPreferencesUtils.getSP(mActivity, "shopId", "");
        getShopId = mShopId;
        cityId = (String) SharedPreferencesUtils.getSP(mActivity, "cityId", "");
        latitude = (String) SharedPreferencesUtils.getSP(mActivity, "latitude", "");
        longitude = (String) SharedPreferencesUtils.getSP(mActivity, "longitude", "");
        if (getUserVisibleHint()) {
            toRequest(ApiConstants.EventTags.HOME_EXPERIENCE);
            toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_SHOP);
            toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_COURSE);
        }
    }


    @Override
    protected void onVisible() {
        super.onVisible();
        if (!getShopId.equals(mShopId)) {
            mShopId = getShopId;
            showLoading("加载...");
            initEmpty();
            mSvView.scrollTo(0, 0);
            mSvView.scrollBy(0, 0);
            toRequest(ApiConstants.EventTags.HOME_EXPERIENCE);
            toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_SHOP);
            toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_COURSE);
        }
    }


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_experience2;
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(mRvList);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mLayoutManager.showLoading();
        initEmpty();
        initClassifyMenu();
        initRv();
    }

    private void initRv() {
        mVirtualLayoutManager = new VirtualLayoutManager(mActivity);
//        mVirtualLayoutManager.setNoScrolling(true);
        mRvList.setLayoutManager(mVirtualLayoutManager);
        mRvList.setBackgroundColor(getResources().getColor(R.color.white));
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRvList.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(1, 5);
        viewPool.setMaxRecycledViews(2, 15);
        adapters = new DelegateAdapter(mVirtualLayoutManager, true);
        initAllTypeView();
        adapters.setAdapters(mAdapters);
        mRvList.setAdapter(adapters);
    }

    private void initAllTypeView() {
        mAdapters = new LinkedList<>();
        initMenuView();
        initListView();
    }

    private void initMenuView() {
        mMenuAdapter = new BaseDelegateAdapter(mActivity,  new StickyLayoutHelper(), R.layout.item_experience_list_menu, 1, 1) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
            }
        };
        mAdapters.add(mMenuAdapter);
    }

    private void initListView() {
        mListAdapter = new BaseDelegateAdapter(mActivity,  new LinearLayoutHelper(), R.layout.item_tutor_list, 15, 2) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
            }
        };
        mAdapters.add(mListAdapter);
    }

    private void initClassifyMenu() {
        mGvView.setAdapter(mCommonAdapter = new CommonAdapter<HomeExperienceBean.TagListBean>(getActivity(), mTagListBeanList, R.layout.item_experience_menu) {
            @Override
            public void convert(ViewHolder holder, HomeExperienceBean.TagListBean tagListBean, int position) {
                if (!ListUtil.isEmpty(mTagListBeanList) && mTagListBeanList.get(position) != null) {
                    ImageView imageView = holder.getView(R.id.iv_img);
                    if (position == 7 && mTagListBeanList.get(position).getName().equals("查看更多")) {
                        holder.setImageResource(R.id.iv_img, R.drawable.icon_15);
                    } else {
                        Glide.with(mActivity)
                                .load(mTagListBeanList.get(position).getImage_url())
                                .placeholder(R.drawable.img_05)
                                .error(R.drawable.img_05)
                                .into(imageView);
                    }
                    holder.setText(R.id.tv_menu_name, mTagListBeanList.get(position).getName());
                }
            }
        });

    }


    /**
     * @param bean 更新view
     */
    private void initViewData(HomeExperienceBean bean) {
        if (bean == null) {
            return;
        }
        mBvList.clear();
        mTagListBeanList.clear();
        mMarketCourseBeanList.clear();
        for (int i = 0; i < bean.getBannerList().size(); i++) {
            mBvList.add(bean.getBannerList().get(i).getImage());
        }
        if (!ListUtil.isEmpty(bean.getTagList())) {
            if (bean.getTagList().size() >= 8) {
                for (int i = 0; i < 7; i++) {
                    mTagListBeanList.add(bean.getTagList().get(i));
                }
                HomeExperienceBean.TagListBean moreBean = new HomeExperienceBean.TagListBean();
                moreBean.setName("查看更多");
                moreBean.setId(0);
                mTagListBeanList.add(7, moreBean);
            } else {
                mTagListBeanList.addAll(bean.getTagList());
            }
        }
        if (mBvList.size() > 0) {
            mBannerV.setVisibility(VISIBLE);
            initBannerView(mBvList);
        } else {
            mBannerV.setVisibility(GONE);
        }
        mCommonAdapter.notifyDataSetChanged();
    }

    private void initBannerView(List<String> list) {
        mBannerV.setBannerStyle(1);
        mBannerV.setImageLoader(new BannerGlideImageLoader());
        mBannerV.setImages(list);
        mBannerV.isAutoPlay(true);
        mBannerV.setDelayTime(3000);
        mBannerV.setIndicatorGravity(BannerConfig.CENTER);
        if (list.size() > 0) {
            mBannerV.start();
        }
    }

    /**
     * 初始化list page
     */
    void initEmpty() {
        isShopLoadMore = true;
        isCourseLoadMore = true;
        coursePageIndex = 1;
        shopPageIndex = 1;
        mBvList.clear();
        mTagListBeanList.clear();
        mMarketCourseBeanList.clear();
        mShopBeanList.clear();
        mCourseBeanList.clear();

        if (mBannerAdapter != null) {
            mBannerAdapter.notifyDataSetChanged();
        }
        if (mMenuAdapter != null) {
            mMenuAdapter.notifyDataSetChanged();
        }
        if (mShopAdapter != null) {
            mShopAdapter.notifyDataSetChanged();
        }
        if (courseAdapter != null) {
            courseAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void toRequest(int eventTag) {
        latitude = (String) SharedPreferencesUtils.getSP(mActivity, "latitude", "");
        longitude = (String) SharedPreferencesUtils.getSP(mActivity, "longitude", "");
        Map map = new HashMap();
        if (eventTag == ApiConstants.EventTags.HOME_EXPERIENCE) {
            map.put("cityId", cityId + "");
            map.put("shopId", mShopId + "");
            map.put("lat", latitude + "");
            map.put("long", longitude + "");
            map.put("page", 1 + "");
            map.put("size", 10 + "");
            Logcat.i("权益课 菜单  首页提交的参数：" + map.toString() + "/" + ApiConstants.Urls.HOME_EXPERIENCE);
            iCommonRequestPresenter.request(eventTag, mActivity, ApiConstants.Urls.HOME_EXPERIENCE, map);
        } else if (eventTag == ApiConstants.EventTags.HOME_EXPERIENCE_SHOP) {
            map.put("cityId", cityId + "");
            map.put("shopId", mShopId + "");
            map.put("lat", latitude + "");
            map.put("long", longitude + "");
            map.put("page", shopPageIndex + "");
            map.put("size", 10 + "");
            Logcat.i("权益课SHOP 首页 提交的参数：" + map.toString() + "/" + ApiConstants.Urls.HOME_EXPERIENCE_SHOP);
            iCommonRequestPresenter.request(eventTag, mActivity, ApiConstants.Urls.HOME_EXPERIENCE_SHOP, map);
        } else if (eventTag == ApiConstants.EventTags.HOME_EXPERIENCE_COURSE) {
            map.put("cityId", cityId + "");
            map.put("shopId", mShopId + "");
            map.put("lat", latitude + "");
            map.put("long", longitude + "");
            map.put("page", coursePageIndex + "");
            map.put("size", 10 + "");
            Logcat.i("权益课Course 首页 提交的参数：" + map.toString() + "/" + ApiConstants.Urls.HOME_EXPERIENCE_COURSE);
            iCommonRequestPresenter.request(eventTag, mActivity, ApiConstants.Urls.HOME_EXPERIENCE_COURSE, map);
        }
    }

    @Override
    public void getRequestData(int eventTag, String result) {
        dismissLoading();
        Logcat.i("eventTag：" + eventTag + "/" + "");
        ResultBean resultBean = JSON.parseObject(result, ResultBean.class);
        if (resultBean.getCode().equals("1")) {
            if (eventTag == ApiConstants.EventTags.HOME_EXPERIENCE_COURSE) {
                List<HomeExperienceCourseBean> beanList = JSON.parseArray(resultBean.getData(), HomeExperienceCourseBean.class);
            } else if (eventTag == ApiConstants.EventTags.HOME_EXPERIENCE_SHOP) {
                List<HomeExperienceShopBean> beanList = JSON.parseArray(resultBean.getData(), HomeExperienceShopBean.class);
            } else if (eventTag == ApiConstants.EventTags.HOME_EXPERIENCE) {
                mExperienceBean = JSON.parseObject(resultBean.getData(), HomeExperienceBean.class);
                initViewData(mExperienceBean);
            }
        }
    }

    @Override
    public void onRequestFailureException(int eventTag, String msg) {
        dismissLoading();
        mLayoutManager.showNetError();
    }


    int tabTag = 1;

    @Override
    protected void setListener() {
        super.setListener();
        mMenuAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int type, View view, int position) {
                mSvView.smoothScrollBy(0,150);
                mSvView.scrollTo(0,150);
            }
        });
        mBannerV.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("getUrl", mBvList.get(position));
                startActivityWithExtras(YogaWebActivity.class, bundle);
            }
        });
        mGvView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Bundle bundle = new Bundle();
                if (position == 7 && mTagListBeanList.get(position).getName().equals("查看更多")) {
                    startActivityWithExtras(ExperienceCourseClassifyActivity.class, bundle);
                } else {
                    bundle.putString("classId", mTagListBeanList.get(position).getId() + "");
                    bundle.putString("className", mTagListBeanList.get(position).getName() + "");
                    startActivityWithExtras(ExperienceCourseListActivity.class, bundle);
                }
            }
        });
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initEmpty();
                latitude = (String) SharedPreferencesUtils.getSP(mActivity, "latitude", "");
                longitude = (String) SharedPreferencesUtils.getSP(mActivity, "longitude", "");
                toRequest(ApiConstants.EventTags.HOME_EXPERIENCE);
                toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_SHOP);
                toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_COURSE);
                mRefreshLayout.setRefreshing(false);
            }
        });
//        mSvView.setOnScrollChangeListener(mScrollChangeListener);
        mLayoutManager.setOnInflateListener(new YogaLayoutManager.OnInflateListener() {
            @Override
            public void onInflate(int viewType, View view) {
                switch (view.getId()) {
                    case R.id.tv_retry:
                        if (NetUtils.isConnected(mActivity)) {
                            initEmpty();
                            mSvView.scrollTo(0, 0);
                            mSvView.scrollBy(0, 0);
                            toRequest(ApiConstants.EventTags.HOME_EXPERIENCE);
                            toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_SHOP);
                            toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_COURSE);
                        } else {
                            DialogUtil.wrap(mActivity)
                                    .setData("设置网络", "是否去设置网络")
                                    .setActionClickListener(new DialogUtil.onActionClickListener() {
                                        @Override
                                        public void action(int viewType, Dialog dialog, View view) {
                                            Intent intent = new Intent(Settings.ACTION_SETTINGS);
                                            startActivity(intent);
                                            dialog.dismiss();
                                        }
                                    })
                                    .init()
                                    .show();
                        }
                        break;
                }
            }
        });
    }

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        if (postResult.getTag().equals("address")) {
            AddressBean.CityBean cityBean = (AddressBean.CityBean) postResult.getResult();
            getShopId = cityBean.getShop_id() + "";
            cityId = cityBean.getId() + "";
            if (getUserVisibleHint()) {
                mShopId = cityBean.getShop_id() + "";
                showLoading("加载...");
                initEmpty();
                mSvView.scrollTo(0, 0);
                mSvView.scrollBy(0, 0);
                toRequest(ApiConstants.EventTags.HOME_EXPERIENCE);
                toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_SHOP);
                toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_COURSE);
            }
        }
    }

    @OnClick({R.id.iv_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_top:
                mSvView.scrollTo(0, 0);
                break;
        }
    }

    public CustomScrollView.OnScrollChangeListener mScrollChangeListener = new CustomScrollView.OnScrollChangeListener() {
        @Override
        public void onScrollChanged(CustomScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            mRefreshLayout.setEnabled(v.getScrollY() == 0);
            if (scrollY > oldScrollY) {
                Logcat.i("Scroll DOWN");
                mIvTop.setVisibility(View.VISIBLE);
            }
            if (scrollY < oldScrollY) {
                Logcat.i("Scroll UP");
                mIvTop.setVisibility(View.GONE);
            }
            if (scrollY == 0) {
                Logcat.i("TOP SCROLL");
                mIvTop.setVisibility(View.GONE);
            }
            if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                Logcat.i("BOTTOM SCROLL");
                mIvTop.setVisibility(View.VISIBLE);
                if (tabTag == 1) {
                    mShopAdapter.loadMoreComplete();
                    Logcat.i("瑜伽馆加载更多" + "isLoadTag:" + isLoadTag + "/" + isShopLoadMore);
                    if (isShopLoadMore && isLoadTag == true) {
                        toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_SHOP);
                        isLoadTag = false;
                    }
                } else {
                    Logcat.i("课程加载更多");
                    if (isCourseLoadMore) {
                        ++coursePageIndex;
                        toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_COURSE);
                    }
                }
            }
        }
    };
}