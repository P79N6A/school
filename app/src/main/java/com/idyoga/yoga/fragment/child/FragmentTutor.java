package com.idyoga.yoga.fragment.child;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.util.TouchEventUtil;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.shop.ShopDetailActivity;
import com.idyoga.yoga.activity.tutor.TutorDetailActivity;
import com.idyoga.yoga.activity.web.YogaWebActivity;
import com.idyoga.yoga.adapter.ShopListAdapter;
import com.idyoga.yoga.adapter.TutorListAdapter;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.listener.OnItemClickListener;
import com.idyoga.yoga.listener.OnVerticalScrollListener;
import com.idyoga.yoga.model.CityBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.TutorInfoBean;
import com.idyoga.yoga.utils.BannerGlideImageLoader;
import com.idyoga.yoga.utils.YogaViewUtil;
import com.idyoga.yoga.view.MyGridItemDecoration;
import com.idyoga.yoga.view.YogaLayoutManager;
import com.idyoga.yoga.view.decoration.HorizontalDividerItemDecoration;
import com.idyoga.yoga.view.decoration.VerticalDividerItemDecoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
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
public class FragmentTutor extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {
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
    private String cityId;
    private String latitude = "23.16";
    private String longitude = "214.23";
    private boolean isTop=true;
    private boolean isLoad, isLoadMore = true, isAddTag = true;
    private int pageIndex = 1;
    private String type = "1";
    private List<TutorInfoBean.TutorBean> mBeanList = new ArrayList<>();
    private List<TutorInfoBean.BannerListBean> mBannerBeanList = new ArrayList<>();
    private List<String> mStringList = new ArrayList<>();
    private TutorListAdapter mAdapter;
    private TutorInfoBean mTutorInfoBean;

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
        mRvList.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvList.addItemDecoration(new HorizontalDividerItemDecoration
                .Builder(mActivity)
                .size(YogaViewUtil.dp2px(mActivity, 10))
                .color(Color.parseColor("#f4f4f4"))
                .build());
        mRvList.setHasFixedSize(true);
        mRvList.setNestedScrollingEnabled(false);
        mAdapter = new TutorListAdapter(R.layout.item_tutor_layout, mBeanList);
        mAdapter.setOnItemClickListener(this);
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
        if (isTop) {
            EventBus.getDefault().post(new PostResult("toTop", "0"));
        } else {
            EventBus.getDefault().post(new PostResult("toTop", "1"));
        }
        if (isLoad) {
            initEmpty();
            getData(pageIndex);
            isLoad = false;
        }
    }

    private void getData(int pageIndex) {
        latitude = (String) SharedPreferencesUtils.getSP(mActivity, "latitude", "");
        longitude = (String) SharedPreferencesUtils.getSP(mActivity, "longitude", "");
        Map map = new HashMap();
        map.put("cityId", cityId);
        map.put("lat", latitude);
        map.put("long", longitude);
        map.put("type", type);
        map.put("page", pageIndex + "");
        map.put("size", "10");
        Logcat.i("瑜伽馆提交的参数：" + map.toString());
        HttpClient.post(ApiConstants.Urls.HOME_TUTOR_LIST, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                ResultBean bean = JSON.parseObject(content, ResultBean.class);
                if (bean.getCode().equals("1")) {
                    TutorInfoBean infoBean = JSON.parseObject(bean.getData(), TutorInfoBean.class);
                    initViewData(infoBean);
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

    private void initViewData(TutorInfoBean bean) {
        if (pageIndex == 1) {
            mStringList.clear();
            mBeanList.clear();
        }
        if (isAddTag && !ListUtil.isEmpty(bean.getBannerList())) {
            mBannerBeanList.addAll(bean.getBannerList());
            for (int i = 0; i < bean.getBannerList().size(); i++) {
                mStringList.add(bean.getBannerList().get(i).getImage());
            }
            initBannerView(mStringList);
            isAddTag = false;
        }
        mBeanList.addAll(bean.getTutor());
        if (mBeanList.size() > 0) {
            mLayoutManager.showContent();
        } else {
            mLayoutManager.showEmpty();
        }
        mAdapter.removeAllFooterView();
        View view = null;
        if (bean.getTutor().size() == 10) {
            view = View.inflate(mActivity, R.layout.view_loading_footer, null);
        } else if (bean.getTutor().size() < 10) {
            isLoadMore = false;
            if (bean.getTutor().size() > 4) {
                view = View.inflate(mActivity, R.layout.view_list_footer, null);
            }
        }
        mAdapter.setData(mBeanList);
        if (view != null) {
            mAdapter.addFooterView(view);
        }
        mAdapter.notifyDataSetChanged();
    }

    private void initEmpty() {
        pageIndex = 1;
        mBeanList.clear();
        mBannerBeanList.clear();
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
//                bundle.putString("getUrl","https://platform.hq-xl.com/admin/pub/login.html");
                bundle.putString("getUrl", mBannerBeanList.get(position).getUrl());
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
        mNsvView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    Logcat.i("Scroll DOWN");
                    EventBus.getDefault().post(new PostResult("toTop", "TAG"));
                }
                if (scrollY < oldScrollY) {
                    Logcat.i("Scroll UP");
                }
                if (scrollY > YogaViewUtil.getScreenWidth(mActivity) / 2) {
                    isTop=false;
                    EventBus.getDefault().post(new PostResult("toTop", "1"));
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
        mAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int type, View view, int position) {
                Logcat.i("点击了：" + position);
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

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Logcat.i("点击了：" + position);
        Bundle bundle = new Bundle();
        TutorInfoBean.TutorBean tutorBean = (TutorInfoBean.TutorBean) adapter.getData().get(position);
        bundle.putString("tutorId", tutorBean.getId() + "");
        bundle.putString("shopId", tutorBean.getShop_id() + "");
        startActivityWithExtras(TutorDetailActivity.class, bundle);

    }
}
