package com.idyoga.yoga.activity.user;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.shop.ShopDetailActivity;
import com.idyoga.yoga.adapter.FollowAdater;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.listener.OnVerticalScrollListener;
import com.idyoga.yoga.listener.YogaRvScrollerListener;
import com.idyoga.yoga.model.Aaaaa;
import com.idyoga.yoga.model.FollowShopBean;
import com.idyoga.yoga.model.FollowShopTime;
import com.idyoga.yoga.model.LessonListBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.ShopDetailDataBean;
import com.idyoga.yoga.utils.DateUtils;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.view.SwipeListLayout;
import com.idyoga.yoga.view.YogaLayoutManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import okhttp3.Request;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * @author weilgu
 * @time 2019/3/7 16:26
 * @des 我关注的瑜伽馆
 */

public class FollowShopActivity extends BaseActivity {

    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.rlv_content)
    RecyclerView mRlvContent;
    @BindView(R.id.ll_title_right)
    LinearLayout mLlTitleRight;
    @BindView(R.id.sll)
    SwipeRefreshLayout mSll;
    private boolean isLoadMore = false;
    private int page = 1;
    private FollowAdater mFollowAdater;
    private HashMap<String, List<FollowShopBean>> mHashMap = new HashMap<>();
    private List<FollowShopBean> mDatas = new ArrayList<>();
    private List<String> mKeys = new ArrayList<>();
    //关注的时间
    private static final int FOLLOW_SHOP_TIME = 0;
    //店铺数据
    private static final int FOLLOW_SHOP = 1;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).statusBarDarkFont(true).init();
    }

    @Override
    protected void initData() {
        getShops();
        mDatas = new ArrayList<>();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_follow_layout;
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("我关注的瑜伽馆");
        mLlTitleRight.setVisibility(View.GONE);
        mRlvContent.clearFocus();
        mRlvContent.setFocusable(false);
        View emptyView = View.inflate(this, R.layout.layout_conslut_course_empty, null);
        TextView content = emptyView.findViewById(R.id.tv_content);
        content.setText("您还没有加入任何瑜伽馆");
        ImageView ima = emptyView.findViewById(R.id.iv_img);
        ima.setImageDrawable(getResources().getDrawable(R.drawable.follow_shop_empty));
        emptyView.findViewById(R.id.tv_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                EventBus.getDefault().post(new PostResult("2HomeShopList"));
            }
        });
        mFollowAdater = new FollowAdater(mDatas, this);
        mFollowAdater.setEmptyView(emptyView);
        View loadView = View.inflate(this, R.layout.view_loading_footer, null);//View.inflate(this,R.layout.view_loading_footer,null)
        mFollowAdater.addFooterView(loadView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRlvContent.setLayoutManager(layoutManager);
        mRlvContent.setAdapter(mFollowAdater);
        //mFollowAdater.disableLoadMoreIfNotFullPage();
    }

    @Override
    protected void setListener() {
        mRlvContent.addOnScrollListener(new OnVerticalScrollListener() {
            @Override
            public void onScrolledToBottom() {
                super.onScrolledToBottom();
                page += 1;
                isLoadMore = true;
                getShops();
            }
        });
        /*mFollowAdater.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page += 1;
                isLoadMore = true;
                getShops();
            }
        });*/
        mFollowAdater.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<FollowShopBean> data = mFollowAdater.getData();
                FollowShopBean shopBean = data.get(position);
                int itemType = shopBean.getItemType();
                if (itemType == 1){
                    int shopId = shopBean.getShopId();
                    String shopName = shopBean.getShopName();
                    Bundle bundle = new Bundle();
                    bundle.putString("shopId",shopId + "");
                    bundle.putString("shopName",shopName + "");
                    startActivity(ShopDetailActivity.class,bundle);
                }
            }
        });
        mSll.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mHashMap.clear();
                mKeys.clear();
                getShops();
                mSll.setRefreshing(false);
            }
        });
    }

    private void getShops() {
        int userId = (int) SharedPreferencesUtils.getSP(this, "UserId", 0);
        Map map = new HashMap();
        map.put("userId", userId + "");
        map.put("page", page + "");
        map.put("size", "10");
        showLoading("正在加载...");
        HttpClient.post(ApiConstants.Urls.USER_ATTENTION_SHOP_LIST, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                dismissLoading();
                Logcat.e("预约结果：" + content);
                ResultBean bean = JSON.parseObject(content, ResultBean.class);
                List<FollowShopBean> lessonListBeans = JSON.parseArray(bean.getData(), FollowShopBean.class);
                bindView(lessonListBeans);
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                dismissLoading();
            }
        });
    }

    private void bindView(List<FollowShopBean> lessonListBeans) {
        mFollowAdater.removeAllFooterView();
        View view = null;
        if (lessonListBeans.size() == 10) {
            view = View.inflate(this, R.layout.view_loading_footer, null);
        } else if (lessonListBeans.size() < 10) {
            isLoadMore = false;
            if (lessonListBeans.size() < 2) {
                mRlvContent.setBackgroundColor(Color.parseColor("#f8f8f8"));
            }
        }
        if (view != null) {
            mFollowAdater.addFooterView(view);
        }
        if (lessonListBeans != null) {
            for (int i = 0; i < lessonListBeans.size(); i++) {
                FollowShopBean bean = lessonListBeans.get(i);
                int create_time = bean.getCreate_time();
                String followDay = DateUtils.timeDay(String.valueOf(create_time));
                //判断mKey里是否有相同的关注日期
                int index = -1;
                for (int j = 0; j < mKeys.size(); j++) {
                    if (mKeys.get(j).equals(followDay)) {
                        index = j;
                    }
                }
                if (index != -1) {
                    List<FollowShopBean> beans = mHashMap.get(mKeys.get(index));
                    beans.add(bean);
                    bean.setItemType(FOLLOW_SHOP);
                } else {
                    List<FollowShopBean> shops = new ArrayList<>();
                    shops.add(bean);
                    bean.setItemType(FOLLOW_SHOP);
                    mHashMap.put(followDay, shops);
                    mKeys.add(followDay);
                }
            }
        }
        mDatas.clear();
        for (String key : mHashMap.keySet()) {
            FollowShopBean bean = new FollowShopBean();
            bean.setTime(key);
            bean.setItemType(FOLLOW_SHOP_TIME);
            mDatas.add(bean);
            mDatas.addAll(mHashMap.get(key));
        }
        if (mFollowAdater != null) {
            /*if (isLoadMore) {
                mFollowAdater.setNewData(mDatas);
            } else {
                mFollowAdater.notifyDataSetChanged();
            }
            isLoadMore = false;*/
            mFollowAdater.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.ll_title_back)
    public void onViewClicked() {
        finish();
    }
}
