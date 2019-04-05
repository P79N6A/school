package com.idyoga.yoga.fragment.child;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.shop.ShopDetailActivity;
import com.idyoga.yoga.activity.web.YogaWebActivity;
import com.idyoga.yoga.adapter.ShopListAdapter;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.listener.OnVerticalScrollListener;
import com.idyoga.yoga.model.CityBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.ShopListBean;
import com.idyoga.yoga.utils.BannerGlideImageLoader;
import com.idyoga.yoga.utils.YogaViewUtil;
import com.idyoga.yoga.view.YogaLayoutManager;
import com.idyoga.yoga.view.decoration.HorizontalDividerItemDecoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import de.greenrobot.event.EventBus;
import okhttp3.Request;
import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.view.common.banner.BannerConfig;
import vip.devkit.view.common.banner.BannerV;
import vip.devkit.view.common.banner.listener.OnBannerListener;

/**
 * 文 件 名: FragmentCourseByVideo
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/9
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class FragmentShop extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.srl_Refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.bv_view)
    BannerV mBvView;
    @BindView(R.id.csv_view)
    NestedScrollView mNsvView;
    @BindView(R.id.ll_layout)
    LinearLayout mLlLayout;
    private String shopId;
    private String cityId;
    private String latitude = "23.16";
    private String longitude = "214.23";
    private boolean isLoad, isLoadMore = true, isAddTag = true;
    private int pageIndex = 1;
    private String type = "1";
    private ShopListBean.ShopBean mShopBean;
    private List<ShopListBean.ShopBean> mBeanList = new ArrayList<>();
    private List<String> mStringList = new ArrayList<>();
    private List<ShopListBean.BannerListBean> mBannerList = new ArrayList<>();
    private List<View> mViewList = new ArrayList<>();
    private ShopListAdapter mAdapter;
    private boolean isTop=true;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_shop;
    }


    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(mNsvView);
    }

    @Override
    protected void initData() {
        super.initData();
        EventBus.getDefault().post(new PostResult("toTop", "0"));
        cityId = (String) SharedPreferencesUtils.getSP(mActivity, "cityId", "");
        latitude = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "latitude", "");
        longitude = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "longitude", "");
        if (getUserVisibleHint()) {
            getData(pageIndex);
        }
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAdapter = new ShopListAdapter(R.layout.item_experience_class_shop, mBeanList);
        mRvList.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(mActivity)
                .color(Color.parseColor("#f4f4f4"))
                .size(YogaViewUtil.dp2px(mActivity, 10))
                .build());
        mRvList.setHasFixedSize(true);
        mRvList.setNestedScrollingEnabled(false);
        mRvList.setAdapter(mAdapter);
    }

    @Override
    protected void onFirstVisible() {
        super.onFirstVisible();
        mLayoutManager.showLoading();
        getData(pageIndex);
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        if (isTop){
            EventBus.getDefault().post(new PostResult("toTop", "0"));
        }else {
            EventBus.getDefault().post(new PostResult("toTop", "1"));
        }
        if (isLoad) {
            initEmpty();
            getData(pageIndex);
            isLoad = false;
        }
    }


//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        Logcat.i("setUserVisibleHint:"+isVisibleToUser+"/"+isTop);
//        if (!isVisibleToUser){
//            EventBus.getDefault().post(new PostResult("toTop", "0"));
//        }
//    }


    private void getData(int pageIndex) {
        latitude = (String) SharedPreferencesUtils.getSP(mActivity, "latitude", "");
        longitude = (String) SharedPreferencesUtils.getSP(mActivity, "longitude", "");
        Map map = new HashMap();
        map.put("cityId", cityId);
        map.put("lat", latitude);
        map.put("long", longitude);
        map.put("type", type);
        map.put("page", pageIndex + "");
        map.put("size", "15");
        Logcat.i("瑜伽馆提交的参数：" + map.toString());
        HttpClient.post(ApiConstants.Urls.SHOP_LIST, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                ResultBean bean = JSON.parseObject(content, ResultBean.class);
                if (bean.getCode().equals("1")) {
                    ShopListBean shopListBean = JSON.parseObject(bean.getData(), ShopListBean.class);
                    initViewData(shopListBean);
                } else {
                    mLayoutManager.showNetError();
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                mLayoutManager.showNetError();
            }
        });
    }

    private void initViewData(ShopListBean bean) {
        if (pageIndex == 1) {
            mStringList.clear();
            mBannerList.clear();
            mBeanList.clear();
        }
        if (isAddTag && !ListUtil.isEmpty(bean.getBannerList())) {
            mBannerList.addAll(bean.getBannerList());
            for (int i = 0; i < bean.getBannerList().size(); i++) {
                mStringList.add(bean.getBannerList().get(i).getImage());
            }
            initBannerView(mStringList);
            isAddTag = false;
        }
        mBeanList.addAll(bean.getShop());
        if (mBeanList.size() > 0) {
            mLayoutManager.showContent();
        } else {
            mLayoutManager.showEmpty();
        }
        for (int i = 0; i < mViewList.size(); i++) {
            mAdapter.removeFooterView(mViewList.get(i));
        }
        View view = null;
        if (bean.getShop().size() == 15) {
            view = View.inflate(mActivity, R.layout.view_loading_footer, null);
        } else if (bean.getShop().size() < 15) {
            isLoadMore = false;
            if (pageIndex==1){
                if (bean.getShop().size()>3){
                    view = View.inflate(mActivity, R.layout.view_list_footer, null);
                }
            }else {
                view = View.inflate(mActivity, R.layout.view_list_footer, null);
            }
        }
        if (view != null) {
            mViewList.add(view);
            mAdapter.addFooterView(view);
            mAdapter.setData(mBeanList);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void initEmpty() {
        pageIndex = 1;
        mBeanList.clear();
        mBannerList.clear();
        mStringList.clear();
        isAddTag = true;
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }


    private void initBannerView(List<String> list) {
        mBvView.setBannerStyle(1);
        mBvView.setImageLoader(new BannerGlideImageLoader());
        mBvView.setImages(list);
        mBvView.isAutoPlay(true);
        mBvView.setDelayTime(3000);
        mBvView.setIndicatorGravity(BannerConfig.CENTER);
        if (list.size() > 0) {
            mBvView.start();
        }
    }

    @Override
    protected void setListener() {
        super.setListener();
        mBvView.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("getUrl", mBannerList.get(position).getUrl());
                startActivityWithExtras(YogaWebActivity.class, bundle);
            }
        });
        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initEmpty();
                getData(pageIndex);
                mSrlRefresh.setRefreshing(false);
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                ShopListBean.ShopBean shopBean = (ShopListBean.ShopBean) adapter.getData().get(position);
                bundle.putString("shopId", shopBean.getId() + "");
                bundle.putString("name", shopBean.getName() + "");
                startActivityWithExtras(ShopDetailActivity.class, bundle);
            }
        });
//
        mNsvView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    Logcat.i("Scroll DOWN");
                }
                if (scrollY < oldScrollY) {
                    Logcat.i("Scroll UP");
                }
                if (scrollY > YogaViewUtil.getScreenWidth(mActivity) / 2) {
                    EventBus.getDefault().post(new PostResult("toTop", "1"));
                    isTop=false;
                }
                if (scrollY == 0) {
                    isTop=true;
                    EventBus.getDefault().post(new PostResult("toTop", "0"));
                    Logcat.i("TOP SCROLL");
                }
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    Logcat.i("BOTTOM SCROLL");
                    if (isLoadMore) {
                        ++pageIndex;
                        getData(pageIndex);
                    }
                }
            }
        });
    }


    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        if (postResult.getTag().equals("setAddress")) {
            CityBean.RecentCityBean cityBean = (CityBean.RecentCityBean) postResult.getResult();
            cityId = cityBean.getId() + "";
            if (getUserVisibleHint()) {
                initEmpty();
                getData(pageIndex);
                isLoad = false;
            } else {
                isLoad = true;
            }
        } else if (postResult.getTag().equals("HOME_TOP") && getUserVisibleHint()) {
            Logcat.i("TOP:" + this.getClass().getName());
            if (mNsvView != null) {
                mNsvView.smoothScrollTo(0, 0);
                mNsvView.post(new Runnable() {
                    @Override
                    public void run() {
                        mNsvView.smoothScrollTo(0, 0);
                    }
                });
            }
        }
    }


}