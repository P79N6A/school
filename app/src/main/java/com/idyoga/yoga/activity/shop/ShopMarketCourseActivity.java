package com.idyoga.yoga.activity.shop;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.listener.OnVerticalScrollListener;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.ShopMarketCourseBean;
import com.idyoga.yoga.utils.DateUtils;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.YogaViewUtil;
import com.idyoga.yoga.view.MyGridItemDecoration;
import com.idyoga.yoga.view.RecycleViewDivider;
import com.idyoga.yoga.view.YogaLayoutManager;
import com.idyoga.yoga.view.decoration.HorizontalDividerItemDecoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;
import vip.devkit.library.Logcat;

/**
 * 文 件 名: ShopMarketCourseActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/22
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ShopMarketCourseActivity extends BaseActivity {
    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.srl_Refresh)
    SwipeRefreshLayout mSrlRefresh;
    private String shopId;
    private int pageIndex = 1;
    private List<ShopMarketCourseBean> mBeanList = new ArrayList<>();
    private BaseQuickAdapter mAdapter;
    private boolean isLoadMore=true,isLoadTag=true;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).init();
    }

    @Override
    protected void initData() {
        mBeanList.clear();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            shopId = bundle.getString("shopId");
            getData(shopId, pageIndex);
        } else {
            Logcat.e("shopId 为空");
        }
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(mRvList);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_shop_course_list;
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("馆的全部权益课程");
        mLayoutManager.showLoading();
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.addItemDecoration(new HorizontalDividerItemDecoration
                .Builder(this)
                .size(YogaViewUtil.dp2px(this, 10))
                .color(Color.parseColor("#f4f4f4"))
                .build());
        mRvList.setAdapter(mAdapter = new BaseQuickAdapter<ShopMarketCourseBean, BaseViewHolder>(R.layout.item_shop_hot_course_layout, mBeanList) {
            @Override
            protected void convert(BaseViewHolder helper, ShopMarketCourseBean bean) {
                helper.setText(R.id.tv_course_name, bean.getName())
                        .setText(R.id.tv_course_time, DateUtils.secToTime(bean.getTime()) + "");
                ImageView imageView = helper.getView(R.id.iv_img);
                Glide.with(mContext).load(bean.getImage()).placeholder(R.drawable.img_course).error(R.drawable.img_course).into(imageView);
            }
        });
    }

    void initEmpty() {
        pageIndex = 1;
        mBeanList.clear();
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void setListener() {
        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initEmpty();
                getData(shopId, pageIndex);
                mSrlRefresh.setRefreshing(false);
            }
        });
        mRvList.addOnScrollListener(new OnVerticalScrollListener() {
            @Override
            public void onScrolledToBottom() {
                super.onScrolledToBottom();
                if (isLoadMore&&isLoadTag){
                    isLoadTag=false;
                    ++pageIndex;
                    getData(shopId, pageIndex);
                }
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("shopId", mBeanList.get(position).getShop_id() + "");
                bundle.putString("courseId", mBeanList.get(position).getId() + "");
                startActivity(ShopCourseInfoActivity.class, bundle);
            }
        });
    }

    private void getData(String shopId, int pageIndex) {
        Map map = new HashMap();
        map.put("page", pageIndex + "");
        map.put("size", "15");
        map.put("shopId", shopId);
        HttpClient.post(ApiConstants.Urls.SHOP_MARKET_COURSE_LIST, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                Logcat.e("返回内容：" + content);
                ResultBean bean = JSON.parseObject(content, ResultBean.class);
                if (bean.getCode().equals("1")) {
                    List<ShopMarketCourseBean> list = JSON.parseArray(bean.getData(), ShopMarketCourseBean.class);
                    initViewData(list);
                } else {
                    ToastUtil.showToast(bean.getMsg());
                    mLayoutManager.showError();
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                mLayoutManager.showNetError();
            }
        });

    }

    private void initViewData(List<ShopMarketCourseBean> list) {
        if (pageIndex == 1) {
            initEmpty();
        }
        mBeanList.addAll(list);
        if (mBeanList.size() > 0) {
            mLayoutManager.showContent();
        } else {
            mLayoutManager.showEmpty();
        }
        if (list.size()<15){
            isLoadMore=false;
        }
        mAdapter.notifyDataSetChanged();
        isLoadTag=true;
    }


    @OnClick({R.id.ll_title_back, R.id.ll_common_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
