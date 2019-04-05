package com.idyoga.yoga.activity.card;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.UserCardAdapter;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.presenter.ICommonRequestPresenter;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.UserVipCardBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * 代码恢复原来的  暂时备份
 */
public class MembershipCardActivity2 extends BaseActivity implements ICommonViewUi {
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
    @BindView(R.id.lv_list)
    ListView mLvList;
    private int pageIndex = 1,UserId;
    private String a, Token, ShopId;
    private UserCardAdapter mAdapter;
    private List<UserVipCardBean> mBeanList = new ArrayList<>();
    private static final int NOT_1 = 1, NOT_2 = 2;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NOT_1:
                    if (mAdapter != null) {
                        mAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    };

    @Override
    protected ICommonRequestPresenter initICommonViewUi() {
        return iCommonRequestPresenter = new CommonRequestPresenterImpl(this, this);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).statusBarDarkFont(true).init();
    }

    @Override
    protected void initData() {
        showLoading("加载中...", true);
        UserId = (int) SharedPreferencesUtils.getSP(this, "UserId", 0);
        Token = (String) SharedPreferencesUtils.getSP(this, "Token", "");
        ShopId = (String) SharedPreferencesUtils.getSP(this, "shopId", "");
        toRequest(ApiConstants.EventTags.USER_CARD_LIST);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_vip_card;
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("我的会员卡");
        mAdapter = new UserCardAdapter(this, mBeanList, R.layout.item_user_card, 1);
        mLvList.setAdapter(mAdapter);
    }

    @Override
    protected void setListener() {
        mLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle mBundle = new Bundle();
                mBundle.putString("shopId", mBeanList.get(position).getShop_id()+"");
                mBundle.putString("courseId",  mBeanList.get(position).getId()+"");
                startActivity(CardDetailsActivity.class, mBundle);
            }
        });
    }

    @OnClick(R.id.ll_title_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @OnClick({R.id.ll_title_back, R.id.ll_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.ll_title_right:
                break;
        }
    }

    @Override
    public void toRequest(int eventTag) {
        if (eventTag == ApiConstants.EventTags.USER_CARD_LIST) {
            Map map = new HashMap();
            map.put("token", Token + "");
            map.put("shopId", ShopId + "");
            map.put("status","2");
            Logcat.i("提交的参数："+map.toString()+"/"+ ApiConstants.Urls.USER_CARD_LIST);
            iCommonRequestPresenter.request(eventTag, this, ApiConstants.Urls.USER_CARD_LIST, map);
        }
    }

    @Override
    public void getRequestData(int eventTag, String result) {
        Logcat.i("获取的数据："+eventTag+"/"+result);
        dismissLoading();
        ResultBean bean = JSON.parseObject(result, ResultBean.class);
        if (bean.getCode().equals("1") && bean.getData() != null) {
            if (eventTag == ApiConstants.EventTags.USER_CARD_LIST) {
                List<UserVipCardBean> beanList = JSON.parseArray(bean.getData(),UserVipCardBean.class);
                if (pageIndex==1&&beanList.size()==0){
                }
                if (beanList.size()<20){
                }
                if (beanList.size()>0){
                    mBeanList.addAll(beanList);
                    mAdapter.notifyDataSetChanged();
                }
            }
        }

    }


    @Override
    public void onRequestFailureException(int eventTag, String msg) {

    }

}
