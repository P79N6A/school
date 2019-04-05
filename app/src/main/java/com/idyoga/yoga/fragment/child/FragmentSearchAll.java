package com.idyoga.yoga.fragment.child;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.search.SearchActivity;
import com.idyoga.yoga.activity.shop.ShopDetailActivity;
import com.idyoga.yoga.activity.tutor.TutorDetailActivity;
import com.idyoga.yoga.adapter.SearchCourseListAdapter;
import com.idyoga.yoga.adapter.SearchTutorAdapter;
import com.idyoga.yoga.adapter.base.BaseDelegateAdapter;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.listener.OnItemClickListener;
import com.idyoga.yoga.listener.OnVerticalScrollListener;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.SearchResultBean;
import com.idyoga.yoga.model.ShopListBean;
import com.idyoga.yoga.model.TutorInfoBean;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.YogaViewUtil;
import com.idyoga.yoga.view.MyGridItemDecoration;
import com.idyoga.yoga.view.YogaLayoutManager;
import com.idyoga.yoga.view.csstv.CSSTextView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;
import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.view.common.ImgPicker.view.GridSpacingItemDecoration;

/**
 * 文 件 名: FragmentSearchAll
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/7/13
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class FragmentSearchAll extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.srl_Refresh)
    SwipeRefreshLayout mSrlRefresh;
    String keyword, cityId;
    String latitude, longitude;
    SearchActivity mActivity;
    SearchResultBean mSearchResultBean;
    private List<DelegateAdapter.Adapter> mAdapters;
    private BaseDelegateAdapter mTutorAdapter, mShopAdapter, mCourseAdapter;
    private VirtualLayoutManager mVirtualLayoutManager;
    private DelegateAdapter delegateAdapter;
    private boolean isLoad;

    private List<SearchResultBean.ShopListBean> mShopListBeans = new ArrayList<>();
    private List<SearchResultBean.TutorListBean> mTutorListBeans = new ArrayList<>();
    private List<SearchResultBean.CourseListBeanX> mCourseListBeans = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SearchActivity) {
            mActivity = (SearchActivity) context;
            keyword = mActivity.getKeyword();
        } else {
            throw new IllegalArgumentException("activity must implements FragmentInteraction");
        }

    }


    @Override
    protected void initData() {
        super.initData();
        cityId = (String) SharedPreferencesUtils.getSP(mActivity, "cityId", "");
        latitude = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "latitude", "");
        longitude = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "longitude", "");
        getData();
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(mRvList);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.layout_common_rv;
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        if (isLoad) {
            getData();
        }
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        view.setBackgroundColor(Color.parseColor("#f4f4f4"));
        mLayoutManager.setEmpty(R.layout.layout_card_child_empty);
        mLayoutManager.initView(YogaLayoutManager.EMPTY).setText(R.id.tv_hint, "没有搜索结果");
        initRvView();
    }


    private void initRvView() {
        mLayoutManager.showLoading();
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRvList.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(1, 20);
        viewPool.setMaxRecycledViews(2, 20);
        viewPool.setMaxRecycledViews(3, 20);
        mVirtualLayoutManager = new VirtualLayoutManager(mActivity);
        mRvList.setLayoutManager(mVirtualLayoutManager);
        delegateAdapter = new DelegateAdapter(mVirtualLayoutManager, true);
        initAllTypeView();
        delegateAdapter.setAdapters(mAdapters);
        mRvList.setAdapter(delegateAdapter);
    }

    private void initAllTypeView() {
        mAdapters = new LinkedList<>();
        initShopView();
        initTutorView();
        initCourseView();
    }

    private void initShopView() {
        mShopAdapter = new BaseDelegateAdapter(mActivity, new LinearLayoutHelper(), R.layout.item_search_shop, 0, 0) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position == 0) {
                    holder.getView(R.id.rl_layout).setVisibility(View.VISIBLE);
                } else {
                    holder.getView(R.id.rl_layout).setVisibility(View.GONE);
                }
                if (!ListUtil.isEmpty(mShopListBeans) && position == mShopListBeans.size()-1) {
                    holder.getView(R.id.view_tag).setVisibility(View.VISIBLE);
                } else {
                    holder.getView(R.id.view_tag).setVisibility(View.GONE);
                }
                holder.setText(R.id.tv_title, "相关瑜伽馆")
                        .setText(R.id.tv_shop_name, mShopListBeans.get(position).getName())
                        .setText(R.id.tv_address, mShopListBeans.get(position).getAddress())
                        .setText(R.id.tv_distance, mShopListBeans.get(position).getCompare() + "km");
                Glide.with(mContext).load(mShopListBeans.get(position).getLogopath())
                        .placeholder(R.drawable.img_course).error(R.drawable.img_course).into((ImageView) holder.getView(R.id.iv_img));
                CSSTextView cssTextView = holder.getView(R.id.tv_shop_name);
                cssTextView.setTextArrColor(keyword, Color.parseColor("#b66bb1"));
                holder.getView(R.id.rl_layout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mActivity.setTab(1);
                    }
                });
            }

            @Override
            public int getItemCount() {
                return mShopListBeans.size();
            }
        }

        ;
        mAdapters.add(mShopAdapter);
    }

    private void initTutorView() {
        mTutorAdapter = new BaseDelegateAdapter(mActivity, new LinearLayoutHelper(), R.layout.item_search_item, 0, 1) {
            private OnItemClickListener mOnItemClickListener;

            public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
                mOnItemClickListener = onItemClickListener;
            }

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                TextView textView = holder.getView(R.id.tv_title);
                textView.setText("相关导师");
                holder.getView(R.id.rl_layout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mActivity.setTab(3);
                    }
                });
                RecyclerView recyclerView = holder.getView(R.id.rv_list);
                LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(layoutManager);
                SearchTutorAdapter adapter = new SearchTutorAdapter(R.layout.item_search_tutor, mTutorListBeans, keyword);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick(1, view, position);
                        }
                    }
                });
            }
        };
        mAdapters.add(mTutorAdapter);
    }

    private void initCourseView() {
        mCourseAdapter = new BaseDelegateAdapter(mActivity, new LinearLayoutHelper(), R.layout.item_search_item, 0, 2) {
            private OnItemClickListener mOnItemClickListener;

            public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
                mOnItemClickListener = onItemClickListener;
            }

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                TextView textView = holder.getView(R.id.tv_title);
                textView.setText("相关权益课");
                holder.getView(R.id.rl_layout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mActivity.setTab(2);
                    }
                });
                RecyclerView recyclerView = holder.getView(R.id.rv_list);
                recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
                Logcat.e("-----------" + mCourseListBeans.size());
                SearchCourseListAdapter adapter = new SearchCourseListAdapter(R.layout.item_shop_course_list_v2, mCourseListBeans, keyword);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick(1, view, position);
                        }
                    }
                });
            }
        };
        mAdapters.add(mCourseAdapter);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initEmpty();
                getData();
                mSrlRefresh.setRefreshing(false);
            }
        });
        mRvList.addOnScrollListener(new OnVerticalScrollListener() {
            @Override
            public void onScrolledToTop() {
                super.onScrolledToTop();
                mSrlRefresh.setEnabled(true);
            }

            @Override
            public void onScrolledUp() {
                super.onScrolledUp();
                mSrlRefresh.setEnabled(false);
            }

            @Override
            public void onScrolledToBottom() {
                super.onScrolledToBottom();
                mSrlRefresh.setEnabled(false);
            }

            @Override
            public void onScrolledDown() {
                super.onScrolledDown();
                mSrlRefresh.setEnabled(false);
            }
        });
        mShopAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int type, View view, int position) {
                Bundle bundle = new Bundle();
                SearchResultBean.ShopListBean beanX = mShopListBeans.get(position);
                bundle.putString("shopId", beanX.getId() + "");
                bundle.putString("name", beanX.getName() + "");
                startActivityWithExtras(ShopDetailActivity.class, bundle);
            }
        });
        mTutorAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int type, View view, int position) {
                Bundle bundle = new Bundle();
                SearchResultBean.TutorListBean tutorBean = mTutorListBeans.get(position);
                bundle.putString("tutorId", tutorBean.getId() + "");
                bundle.putString("shopId", tutorBean.getShop_id() + "");
                startActivityWithExtras(TutorDetailActivity.class, bundle);
            }
        });
        mCourseAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int type, View view, int position) {
                Bundle bundle = new Bundle();
                SearchResultBean.CourseListBeanX beanX = mCourseListBeans.get(position);
                bundle.putString("shopId", beanX.getId() + "");
                bundle.putString("name", beanX.getName() + "");
                startActivityWithExtras(ShopDetailActivity.class, bundle);
            }
        });
    }


    private void getData() {
        if (mLayoutManager != null) {
            mLayoutManager.showLoading();
        }
        Map map = new HashMap();
        map.put("keyword", keyword + "");
        map.put("cityId", cityId + "");
        map.put("type", "0");
        map.put("lat", latitude + "");
        map.put("long", longitude + "");
        map.put("page", 1 + "");
        map.put("size", 15 + "");
        Logcat.i("搜索提交的参数：" + map.toString());
        OkHttpUtils.get().url(ApiConstants.Urls.SEARCH_LIST).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mLayoutManager.showNetError();
            }

            @Override
            public void onResponse(String response, int id) {
                ResultBean bean = JSON.parseObject(response, ResultBean.class);
                if (bean.getCode().equals("1")) {
                    mSearchResultBean = JSON.parseObject(bean.getData(), SearchResultBean.class);
                    initViewData(mSearchResultBean);
                }
            }
        });

    }

    private void initViewData(SearchResultBean bean) {
        this.mSearchResultBean = bean;
        if (mSearchResultBean == null) {
            mLayoutManager.showEmpty();
            return;
        } else {
            mLayoutManager.showContent();
        }
        if (ListUtil.isEmpty(mSearchResultBean.getCourseList()) && ListUtil.isEmpty(mSearchResultBean.getTutorList()) && ListUtil.isEmpty(mSearchResultBean.getShopList())) {
            mLayoutManager.showEmpty();
        }
        if (!ListUtil.isEmpty(bean.getShopList())) {
            mShopListBeans.addAll(bean.getShopList());
        }
        if (!ListUtil.isEmpty(bean.getTutorList())) {
            mTutorListBeans.addAll(bean.getTutorList());
            mTutorAdapter.setCount(1);
        }
        if (!ListUtil.isEmpty(bean.getCourseList())) {
            mCourseListBeans.addAll(bean.getCourseList());
            mCourseAdapter.setCount(1);
        }
        if (mTutorAdapter != null) {
            mTutorAdapter.notifyDataSetChanged();
        }
        if (mShopAdapter != null) {
            mShopAdapter.notifyDataSetChanged();
        }
        if (mCourseAdapter != null) {
            mCourseAdapter.notifyDataSetChanged();
        }
        Logcat.e("mCourseListBeans:" + mCourseListBeans.size());
    }


    private void initEmpty() {
        mShopListBeans.clear();
        mTutorListBeans.clear();
        mCourseListBeans.clear();
        if (mShopAdapter != null) {
            mShopAdapter.setCount(0);
            mShopAdapter.notifyDataSetChanged();
        }
        if (mTutorAdapter != null) {
            mTutorAdapter.setCount(0);
            mTutorAdapter.notifyDataSetChanged();
        }
        if (mCourseAdapter != null) {
            mCourseAdapter.setCount(0);
            mCourseAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        if (postResult.getTag().equals("searchTag")) {
            initEmpty();
            this.keyword = postResult.getResult().toString();
            if (getUserVisibleHint()) {
                getData();
            } else {
                Logcat.i("等待显示之后加载");
                isLoad = true;
            }
        }
    }
}
