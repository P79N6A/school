package com.idyoga.yoga.activity.shop;

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
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.ShopOfflineCourseAdapter;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.presenter.ICommonRequestPresenter;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.ShopOfflineCourseListBean;
import com.idyoga.yoga.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * 文 件 名: ShopOfflineCourseActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/21
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注： 店铺线下课程
 */
public class ShopOfflineCourseActivity extends BaseActivity implements ICommonViewUi {
    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R.id.ll_title_right)
    LinearLayout mLlTitleRight;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.tv_tag_a)
    TextView mTvTagA;
    @BindView(R.id.iv_tag_a)
    ImageView mIvTagA;
    @BindView(R.id.ll_tag_a)
    LinearLayout mLlTagA;
    @BindView(R.id.tv_tag_b)
    TextView mTvTagB;
    @BindView(R.id.iv_tag_b)
    ImageView mIvTagB;
    @BindView(R.id.ll_tag_b)
    LinearLayout mLlTagB;
    @BindView(R.id.tv_tag_c)
    TextView mTvTagC;
    @BindView(R.id.iv_tag_c)
    ImageView mIvTagC;
    @BindView(R.id.ll_tag_c)
    LinearLayout mLlTagC;
    @BindView(R.id.srl_Refresh)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    private String shopName;
    private String shopId;
    private String cityId;
    private String mType = "1";
    private int pageIndex = 1;
    private List<ShopOfflineCourseListBean> mBeanList = new ArrayList<>();
    private ShopOfflineCourseAdapter mCourseAdapter;

    @Override
    protected ICommonRequestPresenter initICommonViewUi() {
        return iCommonRequestPresenter = new CommonRequestPresenterImpl(this,this);
    }

    @Override
    protected void initData() {
        showLoading("加载中...", true);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            shopId = bundle.getString("shopId");
            shopName = bundle.getString("shopName");
            cityId = (String) SharedPreferencesUtils.getSP(this,"cityId","");
        }
        toRequest(ApiConstants.EventTags.SHOP_OFFLINE_COURSE_ALL);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).flymeOSStatusBarFontColor("#333333").init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_shop_offline_course;
    }

    @Override
    protected void initView() {
        mTvTitleText.setText(shopName + "的线下课程");
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mCourseAdapter= new ShopOfflineCourseAdapter(R.layout.item_shop_offlline_course,mBeanList);
        mRvList.setAdapter(mCourseAdapter);
        mCourseAdapter.notifyDataSetChanged();
    }

    @Override
    protected void setListener() {
        mCourseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Logcat.i("点击了："+position);
            }
        });
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCourseAdapter.notifyDataSetChanged();
                mRefreshLayout.setRefreshing(false);
            }
        });
    }
    @Override
    public void toRequest(int eventTag) {
        if (eventTag == ApiConstants.EventTags.SHOP_OFFLINE_COURSE_ALL) {
            Map map = new HashMap();
            map.put("type", mType);
            map.put("shopId", shopId+"");
            map.put("shopId", 19287727+"");
            map.put("cityId",cityId+"");
            map.put("page", pageIndex + "");
            map.put("size", "20");
            Logcat.i("提交的参数："+map.toString());
            iCommonRequestPresenter.request(eventTag, this, ApiConstants.Urls.SHOP_OFFLINE_COURSE_ALL, map);
        }
    }
    @Override
    public void getRequestData(int eventTag, String result) {
        Logcat.i("eventTag:"+eventTag+" \n result:"+result);
        dismissLoading();
        ResultBean bean = JSON.parseObject(result, ResultBean.class);
        if (bean.getCode().equals("1")) {
            if (eventTag == ApiConstants.EventTags.SHOP_OFFLINE_COURSE_ALL) {
                List<ShopOfflineCourseListBean> beanList = JSON.parseArray(bean.getData(), ShopOfflineCourseListBean.class);
                if (pageIndex == 1 && beanList.size() == 0) {
                }
                if (beanList.size() > 20) {
                }
                mBeanList.addAll(beanList);
                if (pageIndex==1){
                }else {
                    mCourseAdapter.setNewData(beanList);
                }
                mCourseAdapter.notifyDataSetChanged();
            }
        }else {
            ToastUtil.showToast(bean.getMsg());
        }
    }
    @Override
    public void onRequestFailureException(int eventTag, String msg) {

    }

    int tagA, tagB, tagC;
    @OnClick({R.id.ll_title_back, R.id.ll_tag_a, R.id.ll_tag_b, R.id.ll_tag_c})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.ll_tag_a:
                if (tagA == 0) {
                    mType = "1";
                    mIvTagA.setImageResource(R.drawable.icon_sort_up);
                    mBeanList.clear();
                    toRequest(ApiConstants.EventTags.SHOP_OFFLINE_COURSE_ALL);
                } else if (tagA == 1) {
                    mType = "2";
                    mIvTagA.setImageResource(R.drawable.icon_sort_down);
                    mBeanList.clear();
                    toRequest(ApiConstants.EventTags.SHOP_OFFLINE_COURSE_ALL);
                }
                tagA = (tagA + 1) % 2;
                break;
            case R.id.ll_tag_b:
                if (tagB == 0) {
                    mType = "3";
                    mIvTagB.setImageResource(R.drawable.icon_sort_up);
                    mBeanList.clear();
                    toRequest(ApiConstants.EventTags.SHOP_OFFLINE_COURSE_ALL);
                } else if (tagB == 1) {
                    mType = "4";
                    mIvTagB.setImageResource(R.drawable.icon_sort_down);
                    mBeanList.clear();
                    toRequest(ApiConstants.EventTags.SHOP_OFFLINE_COURSE_ALL);
                }
                tagB = (tagB + 1) % 2;
                break;
            case R.id.ll_tag_c:
                if (tagC == 0) {
                    mType = "5";
                    mIvTagC.setImageResource(R.drawable.icon_sort_up);
                    mBeanList.clear();
                    toRequest(ApiConstants.EventTags.SHOP_OFFLINE_COURSE_ALL);
                } else if (tagC == 1) {
                    mType = "6";
                    mIvTagC.setImageResource(R.drawable.icon_sort_down);
                    mBeanList.clear();
                    toRequest(ApiConstants.EventTags.SHOP_OFFLINE_COURSE_ALL);
                }
                tagC = (tagC + 1) % 2;
                break;
        }
    }
}
