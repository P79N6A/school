package com.idyoga.yoga.fragment.child;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.search.SearchActivity;
import com.idyoga.yoga.adapter.SearchCourseAdapter;
import com.idyoga.yoga.adapter.SearchCourseListAdapter;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.listener.OnVerticalScrollListener;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.SearchResultBean;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.YogaViewUtil;
import com.idyoga.yoga.view.MyGridItemDecoration;
import com.idyoga.yoga.view.RecycleViewDivider;
import com.idyoga.yoga.view.YogaLayoutManager;
import com.idyoga.yoga.view.decoration.HorizontalDividerItemDecoration;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;
import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * 文 件 名: FragmentSearchCourse
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/7/13
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class FragmentSearchCourse extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.srl_Refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.ll_content_layout)
    LinearLayout mLayout;
    String keyword, cityId;
    String latitude, longitude;
    SearchActivity mActivity;
    SearchResultBean mSearchResultBean;
    List<SearchResultBean.CourseListBeanX> mBeanList = new ArrayList<>();
    SearchCourseListAdapter mAdapter;
    boolean isLoad, isLoadMoreTag = true, isLoadMore = true;
    int pageIndex = 1;

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
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(mRvList);
    }

    @Override
    protected void onFirstVisible() {
        super.onFirstVisible();
        getData();
        initView(this.getView());
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        if (isLoad) {
            initEmpty();
            getData();
            isLoad = false;
        }
    }

    private void initEmpty() {
        pageIndex = 1;
        mBeanList.clear();
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
        isLoadMoreTag = true;
        isLoadMore = true;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.layout_common_rv;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        view.setBackgroundColor(Color.parseColor("#f4f4f4"));
//        mRvList.setBackgroundColor(Color.parseColor("#f4f4f4"));
        mLayoutManager.showLoading();
        mLayoutManager.setEmpty(R.layout.layout_card_child_empty);
        mLayoutManager.initView(YogaLayoutManager.EMPTY).setText(R.id.tv_hint,"没有搜索结果");
        RecycleViewDivider divider = new RecycleViewDivider(
                mActivity, LinearLayoutManager.VERTICAL, 10, Color.parseColor("#f4f4f4"));
        mRvList.addItemDecoration(divider);
        mRvList.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new SearchCourseListAdapter(R.layout.item_shop_course_list_v2, mBeanList, keyword);
        mRvList.setAdapter(mAdapter);
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
            public void onScrolledDown() {
                super.onScrolledDown();
                mSrlRefresh.setEnabled(false);
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
                if (isLoadMore && isLoadMoreTag) {
                    isLoadMoreTag = false;
                    pageIndex=1;
                    getData();
                }
            }
        });
    }


    private void getData() {
        Map map = new HashMap();
        map.put("keyword", keyword + "");
        map.put("cityId", cityId + "");
        map.put("type", "2");
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
                Logcat.i("返回参数："+response);
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
        if (pageIndex==1){
            initEmpty();
        }
        if (mSearchResultBean != null && !ListUtil.isEmpty(mSearchResultBean.getCourseList())) {
            Logcat.i("" + mSearchResultBean.getCourseList().size());
            mBeanList.addAll(mSearchResultBean.getCourseList());
            mLayoutManager.showContent();
        } else {
            mLayoutManager.showEmpty();
        }
        mAdapter.removeAllFooterView();
        View view = null;
        if (!ListUtil.isEmpty(bean.getCourseList()) &&bean.getCourseList().size() == 10) {
            view = View.inflate(mActivity, R.layout.view_loading_footer, null);
            mAdapter.addFooterView(view);
        } else {
            isLoadMore = false;
            if (pageIndex==1){
                if (!ListUtil.isEmpty(bean.getCourseList())&&bean.getCourseList().size()>3) {
                    view = View.inflate(mActivity, R.layout.view_list_footer, null);
                    mAdapter.addFooterView(view);
                }
            }else {
                if (!ListUtil.isEmpty(bean.getCourseList())) {
                    view = View.inflate(mActivity, R.layout.view_list_footer, null);
                    mAdapter.addFooterView(view);
                }
            }
        }
        mAdapter.setKeyword(keyword);
        mAdapter.notifyDataSetChanged();
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
