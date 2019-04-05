package com.idyoga.yoga.activity.card;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.UserCardDetailItemAdapter;
import com.idyoga.yoga.adapter.UserCardRangeAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.presenter.ICommonRequestPresenter;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.UserCardDetailPermission;
import com.idyoga.yoga.model.VIPCardBean;
import com.idyoga.yoga.view.YogaLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

/**
 * 文 件 名: CardDetailsActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/3/22
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class CardCourseRangeActivity extends BaseActivity implements ICommonViewUi {
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
    @BindView(R.id.tab_view)
    TabLayout mTabView;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.lv_list)
    ListView mLvList;
    private int userId;
    private String cityId,shopId;
    private String cardId;
    private String token;

    private VIPCardBean mVIPCardBean;
    private UserCardRangeAdapter mRangeAdapter;
    private UserCardDetailItemAdapter mListAdapter;
    private UserCardDetailPermission mPermissionBean;
    private List<UserCardDetailPermission.ShopLessonListBean> mBeanList = new ArrayList<>();
    private List<UserCardDetailPermission.CardCityListBean> mCityListBeans = new ArrayList<>();
    private int index = 0;
    boolean isAddCity = true;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).flymeOSStatusBarFontColor("#333333").statusBarDarkFont(true).init();
    }


    @Override
    protected ICommonRequestPresenter initICommonViewUi() {
        return iCommonRequestPresenter = new CommonRequestPresenterImpl(this, this);
    }

    @Override
    protected void initData() {
        userId = (int) SharedPreferencesUtils.getSP(this, "UserId", 0);
        cityId= (String) SharedPreferencesUtils.getSP(this,"cityId","");
        token = (String) SharedPreferencesUtils.getSP(this, "Token", "");
        shopId = (String) SharedPreferencesUtils.getSP(this, "shopId", "");
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mVIPCardBean = mBundle.getParcelable("cardBean");
            shopId = mVIPCardBean.getShop_id() + "";
            cardId = mBundle.getString("cardId");
            ////http://testyogabook.hq-xl.com/mall/platformcard/appGetPlatformCardCity
            toRequest(ApiConstants.EventTags.USER_CARD_PERMISSION);
        }
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(mRvList);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_card_range;
    }

    @Override
    protected void initView() {
        mLayoutManager.showLoading();
        mLayoutManager.setEmpty(R.layout.layout_card_child_empty);
        mLayoutManager.initView(YogaLayoutManager.EMPTY).setText(R.id.tv_hint,"暂无该区域下可使用范围");
        if (mVIPCardBean != null) {
            mTvTitleText.setText(mVIPCardBean.getCard_name() + "的课程范围");
        } else {
            mTvTitleText.setText("课程范围");
        }
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mListAdapter = new UserCardDetailItemAdapter(R.layout.item_layout_list, mBeanList ,mRvList);
        mRvList.setAdapter(mListAdapter);
    }

    @Override
    protected void setListener() {
        mTabView.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                initViewData(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initViewData(int position) {
        showLoading("加载中");
        mBeanList.clear();
        if (mRangeAdapter!=null){
            mRangeAdapter.notifyDataSetChanged();
        }
        if (mListAdapter != null) {
            mListAdapter.notifyDataSetChanged();
        }
        if (!ListUtil.isEmpty(mCityListBeans)) {
            cityId = mCityListBeans.get(position).getCity_id() + "";
            toRequest(ApiConstants.EventTags.USER_CARD_PERMISSION);
        }
    }

    @OnClick({R.id.ll_title_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
        }
    }


    private void initCardPermissionData(UserCardDetailPermission mPermissionBean) {
        if (isAddCity) {
            for (int i = 0; i < mPermissionBean.getCardCityList().size(); i++) {
                mTabView.addTab(mTabView.newTab().setText(mPermissionBean.getCardCityList().get(i).getName()));
            }
            isAddCity = false;
        }
        mBeanList.addAll(mPermissionBean.getShopLessonList());
        if (ListUtil.isEmpty(mPermissionBean.getShopLessonList())){
            mLayoutManager.showEmpty();
        }else {
            mLayoutManager.showContent();
        }
        mCityListBeans.addAll(mPermissionBean.getCardCityList());
        Logcat.i("mBeanList:" + mBeanList.size() + "/" + mPermissionBean.getShopLessonList().size());
        mListAdapter.setNewData(mPermissionBean.getShopLessonList());
        mListAdapter.notifyDataSetChanged();
        dismissLoading();
    }

    @Override
    public void toRequest(int eventTag) {
        if (eventTag == ApiConstants.EventTags.USER_CARD_PERMISSION) {
            Map map = new HashMap();
            map.put("userId", userId + "");
            map.put("token", token + "");
            map.put("membershipId", cardId + "");
            //map.put("cityId",cityId);
            Logcat.i("提交的参数：" + eventTag + "/" + map.toString());
            iCommonRequestPresenter.request(eventTag, this, ApiConstants.Urls.USER_CARD_PERMISSION_V2, map);
        }
    }

    @Override
    public void getRequestData(int eventTag, String result) {
        Logcat.i("返回的数据：" + eventTag + "/");
        dismissLoading();
        ResultBean resultBean = JSON.parseObject(result, ResultBean.class);
        if (resultBean.getCode().equals("1") && resultBean.getData() != null) {
            if (eventTag == ApiConstants.EventTags.USER_CARD_PERMISSION) {
                mPermissionBean = JSON.parseObject(resultBean.getData(), UserCardDetailPermission.class);
                if (mPermissionBean != null) {
                    mLayoutManager.showContent();
                    initCardPermissionData(mPermissionBean);
                } else {
                    mLayoutManager.showEmpty();
                }
            }
        } else {
            mLayoutManager.showEmpty();
        }

    }


    @Override
    public void onRequestFailureException(int eventTag, String msg) {
        mLayoutManager.showNetError();
    }

}
