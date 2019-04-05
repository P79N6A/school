package com.idyoga.yoga.fragment.child;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.card.CardDetailsActivity;
import com.idyoga.yoga.adapter.UserCardAdapter;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.base.BaseFragment;
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
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * 文 件 名: FragmentCourseContent
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 创建日期: 2018/3/19
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class FragmentUserCard extends BaseFragment implements ICommonViewUi {


    @BindView(R.id.lv_list)
    ListView mLvList;
    String tag = "0";
    private String Token, ShopId;
    private int pageIndex = 1;
    private UserCardAdapter mAdapter;
    private List<UserVipCardBean> mBeanList = new ArrayList<>();

    @Override
    protected ICommonRequestPresenter initICommonViewUi() {
        return iCommonRequestPresenter = new CommonRequestPresenterImpl(getActivity(), this);
    }

    public static FragmentUserCard getInstance(Bundle bundle) {
        FragmentUserCard fragment = new FragmentUserCard();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initData() {
        super.initData();
        Token = (String) SharedPreferencesUtils.getSP(mActivity, "Token", "");
        ShopId = (String) SharedPreferencesUtils.getSP(mActivity, "shopId", "");
        Bundle bundle = getArguments();
        if (bundle != null) {
            tag = bundle.getString("tag");
        }
        Logcat.i("Tag:" + tag);
        toRequest(ApiConstants.EventTags.USER_CARD_LIST);
    }


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAdapter = new UserCardAdapter(mActivity, mBeanList, R.layout.item_user_card, 1);
        mLvList.setAdapter(mAdapter);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle mBundle = new Bundle();
                mBundle.putString("shopId", mBeanList.get(position).getShop_id() + "");
                mBundle.putString("courseId", mBeanList.get(position).getId() + "");
                startActivityWithExtras(CardDetailsActivity.class, mBundle);
            }
        });
    }

    @Override
    public void toRequest(int eventTag) {
            Map<String, String> map = new HashMap();
            map.put("token", Token + "");
            map.put("shopId", ShopId + "");
            map.put("status", tag + "");
            Logcat.i("提交的参数：" + eventTag + "/" + map.toString() + "/" + ApiConstants.Urls.USER_CARD_LIST);
            iCommonRequestPresenter.request(eventTag, mActivity, ApiConstants.Urls.USER_CARD_LIST, map);
    }

    @Override
    public void getRequestData(int eventTag, String result) {
        Logcat.i("获取的数据：" + eventTag + "/" + result);
        dismissLoading();
        ResultBean bean = JSON.parseObject(result, ResultBean.class);
        if (bean.getCode().equals("1") && bean.getData() != null) {
            if (eventTag == ApiConstants.EventTags.USER_CARD_LIST) {
                List<UserVipCardBean> beanList = JSON.parseArray(bean.getData(), UserVipCardBean.class);
                if (pageIndex == 1 && beanList.size() == 0) {
                }
                if (beanList.size() < 20) {
                }
                if (beanList.size() > 0) {
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
