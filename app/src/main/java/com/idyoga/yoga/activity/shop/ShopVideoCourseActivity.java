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
import com.idyoga.yoga.model.ShopTutorBean;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.view.MyGridItemDecoration;
import com.idyoga.yoga.view.YogaLayoutManager;

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
 * 文 件 名: ShopVideoCourseActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/22
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ShopVideoCourseActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener {
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
    private List<ShopTutorBean> mBeanList = new ArrayList<>();
    private BaseQuickAdapter mAdapter;

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
        mTvTitleText.setText("馆的全部视频课程");
        mLayoutManager.showLoading();
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        MyGridItemDecoration decoration = new MyGridItemDecoration();
        decoration.setColor(Color.parseColor("#f2f2f2"));
        decoration.setSize(5);
        mRvList.addItemDecoration(decoration);
        mRvList.setAdapter(mAdapter = new BaseQuickAdapter<ShopTutorBean, BaseViewHolder>(R.layout.item_shop_hot_course_layout, mBeanList) {
            @Override
            protected void convert(BaseViewHolder helper, ShopTutorBean bean) {
                Glide.with(mContext)
                        .load(bean.getImage())
                        .placeholder(R.drawable.img_course)
                        .error(R.drawable.img_course)
                        .into((ImageView) helper.getView(R.id.iv_img));
            }
        });
        mAdapter.setOnLoadMoreListener(this, mRvList);
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
                ++pageIndex;
                getData(shopId, pageIndex);
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    private void getData(String shopId, int pageIndex) {
        Map map = new HashMap();
        map.put("page", pageIndex + "");
        map.put("size", "20");
        map.put("shopId", shopId);
        HttpClient.post(ApiConstants.Urls.SHOP_VIDEO_COURSE_LIST, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                Logcat.e("返回内容：" + content);
                ResultBean bean = JSON.parseObject(content, ResultBean.class);
                if (bean.getCode().equals("1")) {
                    List<ShopTutorBean> list = JSON.parseArray(bean.getData(), ShopTutorBean.class);
                    initViewData(list);
                } else {
                    ToastUtil.showToast(bean.getMsg());
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                mLayoutManager.showNetError();
            }
        });

    }

    private void initViewData(List<ShopTutorBean> list) {
        if (pageIndex == 1) {
            initEmpty();
        }
        mBeanList.addAll(list);
        if (mBeanList.size() > 0) {
            mLayoutManager.showContent();
        } else {
            mLayoutManager.showEmpty();
        }
        mAdapter.notifyDataSetChanged();
    }


    @OnClick({R.id.ll_title_back, R.id.ll_common_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
        }
    }

    boolean isErr = true;
    int mCurrentCounter;

    @Override
    public void onLoadMoreRequested() {
        mRvList.setEnabled(false);
        mRvList.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mCurrentCounter >= mBeanList.size()) {
                    mAdapter.loadMoreEnd();
                } else {
                    if (isErr) {
                        mCurrentCounter = mAdapter.getData().size();
                        mAdapter.loadMoreComplete();
                    } else {
                        isErr = true;
                        ToastUtil.showToast("加载更多失败，请重试");
                        mAdapter.loadMoreFail();
                    }
                }
                mRvList.setEnabled(true);
            }
        }, 1000);

    }
}
