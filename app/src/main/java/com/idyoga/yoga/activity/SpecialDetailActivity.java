package com.idyoga.yoga.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.shop.ShopDetailActivity;
import com.idyoga.yoga.adapter.SpecialDetailAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.listener.OnRecyclerItemClickListener;
import com.idyoga.yoga.model.EquityCourseShopListBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.SpecialDetailData;
import com.idyoga.yoga.utils.ViewUtil;
import com.idyoga.yoga.view.RecycleViewDivider;
import com.idyoga.yoga.view.decoration.VerticalDividerItemDecoration;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * @author weilgu
 * @time 2019/3/21 9:23
 * @des 专题详情
 */

public class SpecialDetailActivity extends BaseActivity {

    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.rlv_special_content)
    RecyclerView mRlvSpecialContent;
    //专题id
    private int mSubjectId;
    private String mCityId;
    private int pageIndex = 1;
    private SpecialDetailAdapter mAdapter;
    private SpecialDetailData mBean;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).flymeOSStatusBarFontColor("#333333").init();
    }

    @Override
    protected void initData() {
        mCityId = (String) SharedPreferencesUtils.getSP(this, "cityId", "");
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mSubjectId = extras.getInt("subjectId");
        }

        getSpecialData();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_special_detail_layout;
    }

    private void bindView(SpecialDetailData bean) {
        mAdapter.setDatas(bean);
    }

    @Override
    protected void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRlvSpecialContent.setLayoutManager(layoutManager);
        mAdapter = new SpecialDetailAdapter(this);
        mRlvSpecialContent.setAdapter(mAdapter);
        mRlvSpecialContent.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.HORIZONTAL,
                ViewUtil.dp2px(this,10), Color.parseColor("#f8f8f8")));
    }

    @Override
    protected void setListener() {
        mAdapter.setRecyclerItemListener(new OnRecyclerItemClickListener() {
            @Override
            public void onClickListener(View view,int position) {
                List<SpecialDetailData.ShopListBean> shopList = mBean.getShopList();
                SpecialDetailData.ShopListBean listBean = shopList.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("shopId", listBean.getShopId() + "");
                bundle.putString("name", listBean.getShopName()+ "");
                startActivity(ShopDetailActivity.class, bundle);
            }
        });
    }

    public void getSpecialData() {
        Map map = new HashMap();
        map.put("cityId", mCityId + "");
        map.put("subjectId", mSubjectId + ""); //区域id
        map.put("page", pageIndex + "");
        map.put("size", 10 + "");
        showLoading("正在加载数据...");
        Logcat.e("获取专题详情数据：" + map.toString());
        OkHttpUtils.get().url(ApiConstants.Urls.SPECIAL_DETAIL_DATA)
                .params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Logcat.e("获取专题详情数据：" + e);
                dismissLoading();
                setEmptyView(2);
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoading();
                Logcat.e("获取店铺下权益课：" + response);
                ResultBean resultBean = JSON.parseObject(response, ResultBean.class);
                if (resultBean.getCode().equals("1")) {
                    mBean = JSON.parseObject(resultBean.getData(), SpecialDetailData.class);
                    bindView(mBean);
                }
            }
        });
    }

    public void setEmptyView(int type) {
        View emptyView = null;
        if (type == 0) {/**加载中...*/
            emptyView = View.inflate(this, R.layout.layout_common_loading, null);
        } else if (type == 1) {/**空数据...*/
            emptyView = View.inflate(this, R.layout.layout_card_child_empty, null);
            ((TextView) emptyView.findViewById(R.id.tv_hint)).setText("无数据...");
        } else if (type == 2) {/**网络异常...*/
            emptyView = View.inflate(this, R.layout.yoga_layout_net_error, null);
            emptyView.findViewById(R.id.tv_retry).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setEmptyView(0);
                }
            });
        }
    }

    @OnClick(R.id.ll_title_back)
    public void onViewClicked() {
        finish();
    }
}
