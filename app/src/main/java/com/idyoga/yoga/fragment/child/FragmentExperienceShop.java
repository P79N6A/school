package com.idyoga.yoga.fragment.child;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.course.ExperienceCourseListActivity;
import com.idyoga.yoga.activity.shop.ShopDetailActivity;
import com.idyoga.yoga.activity.web.YogaGymDetailsActivity;
import com.idyoga.yoga.adapter.ExperienceClassCourseAdapter;
import com.idyoga.yoga.adapter.ExperienceClassShopAdapter;
import com.idyoga.yoga.adapter.ExperienceShopAdapter;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.presenter.ICommonRequestPresenter;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.listener.OnVerticalScrollListener;
import com.idyoga.yoga.model.ExperienceClassShopBean;
import com.idyoga.yoga.model.ExperienceCourseClassBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.utils.YogaViewUtil;
import com.idyoga.yoga.view.RecycleViewDivider;
import com.idyoga.yoga.view.YogaLayoutManager;
import com.idyoga.yoga.view.decoration.HorizontalDividerItemDecoration;
import com.idyoga.yoga.view.decoration.VerticalDividerItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * 文 件 名: FragmentExperienceShop
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/29
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class FragmentExperienceShop extends BaseFragment implements ICommonViewUi {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    private int pageIndex = 1;
    private String cityId;
    private String tagId, shopId;
    private YogaLayoutManager mYogaLayoutManager;
    private ExperienceClassShopAdapter mAdapter;
    private boolean isLoadMore = true;
    private String latitude = "23.16";
    private String longitude = "214.23";
    private ExperienceCourseListActivity activity;
    private  List<ExperienceClassShopBean> mBeanList = new ArrayList();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (ExperienceCourseListActivity) context;
        Bundle bundle = activity.getIntent().getExtras();
        tagId = bundle.getString("classId");
        Logcat.i("tagId:" + tagId);
    }

    @Override
    protected void initData() {
        cityId = (String) SharedPreferencesUtils.getSP(getActivity(), "cityId", "");
        shopId = (String) SharedPreferencesUtils.getSP(getContext(), "shopId", "");
        latitude = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "latitude", "");
        longitude = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "longitude", "");
        toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_CLASS_SHOP);

    }

    @Override
    protected void onFirstVisible() {
        super.onFirstVisible();
    }

    @Override
    protected ICommonRequestPresenter initICommonViewUi() {
        return iCommonRequestPresenter = new CommonRequestPresenterImpl(getActivity(), this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.layout_common_list;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mYogaLayoutManager = YogaLayoutManager.wrap(mRvList);
        mYogaLayoutManager.showLoading();
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        viewPool.setMaxRecycledViews(1, 20);
        mRvList.setRecycledViewPool(viewPool);
        mRvList.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvList.addItemDecoration(new HorizontalDividerItemDecoration
                .Builder(mActivity)
                .size(YogaViewUtil.dp2px(mActivity,10))
                .color(Color.parseColor("#f4f4f4"))
                .build());
        mAdapter = new ExperienceClassShopAdapter(R.layout.item_experience_class_shop, mBeanList);
        mRvList.setAdapter(mAdapter);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("shopId",mBeanList.get(position).getId()+"");
                bundle.putString("name",mBeanList.get(position).getName()+"");
                bundle.putString("getUrl", mBeanList.get(position).getUrl());
//                startActivity(YogaGymDetailsActivity.class, bundle);
                startActivityWithExtras(ShopDetailActivity.class,bundle);
            }
        });

        mRvList.addOnScrollListener(new OnVerticalScrollListener() {
            @Override
            public void onScrolledToBottom() {
                super.onScrolledToBottom();
                if (isLoadMore) {
                    Logcat.i("加载更多");
                    ++pageIndex;
                    toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_CLASS_SHOP);
                }
            }
        });
    }

    private void initViewData(List<ExperienceClassShopBean> beanList) {
        if (pageIndex == 1 && ListUtil.isEmpty(beanList)) {
            beanList = new ArrayList<>();
            mYogaLayoutManager.showEmpty();
        } else {
            mYogaLayoutManager.showContent();
        }
        if (!ListUtil.isEmpty(beanList)) {
            mBeanList.addAll(beanList);
        }
        mAdapter.setData(mBeanList);
        if (beanList.size() < 10) {
            isLoadMore = false;
            View view = View.inflate(mActivity, R.layout.view_list_footer, null);
            mAdapter.addFooterView(view);
        }
    }

    @Override
    public void toRequest(int eventTag) {
        if (eventTag == ApiConstants.EventTags.HOME_EXPERIENCE_CLASS_SHOP) {
            Map<String, String> map = new HashMap();
            map.put("lat", latitude + "");
            map.put("long", longitude + "");
            map.put("cityId", cityId);
            map.put("shopId", shopId);
            map.put("tagId", tagId);
            map.put("size", "10");
            map.put("page", pageIndex + "");
            Logcat.i("提交的参数：" + map.toString());
            iCommonRequestPresenter.request(eventTag, mActivity, ApiConstants.Urls.HOME_EXPERIENCE_CLASS_SHOP, map);
        }
    }

    @Override
    public void getRequestData(int eventTag, String result) {
        Logcat.i("eventTag:" + eventTag + "/" + result);
        ResultBean resultBean = JSON.parseObject(result, ResultBean.class);
        if (resultBean.getCode().equals("1") && resultBean.getData() != null) {
            if (eventTag == ApiConstants.EventTags.HOME_EXPERIENCE_CLASS_SHOP) {
                List<ExperienceClassShopBean> beanList = JSON.parseArray(resultBean.getData(), ExperienceClassShopBean.class);
                Logcat.i("pageIndex:" + pageIndex + "/" + beanList.size());
                initViewData(beanList);
            }
        }
    }


    @Override
    public void onRequestFailureException(int eventTag, String msg) {

    }

}
