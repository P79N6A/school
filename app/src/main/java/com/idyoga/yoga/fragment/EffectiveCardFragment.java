package com.idyoga.yoga.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.card.ActivationCardActivity;
import com.idyoga.yoga.activity.card.CardDetailsActivity;
import com.idyoga.yoga.adapter.VipCardAdapter;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.VIPCardBean;
import com.idyoga.yoga.utils.ToastUtil;
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
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

/**
 * Created by mjh on 2019/1/23.
 * 权益卡 有效
 */

public class EffectiveCardFragment extends BaseFragment {

    @BindView(R.id.tv_no_card)
    TextView tvNoCard;
    @BindView(R.id.tv_activa_card)
    TextView tvActivaCard;
    @BindView(R.id.tv_exchange)
    TextView tvExchange;
    @BindView(R.id.ll_no_card)
    RelativeLayout llNoCard;
    @BindView(R.id.lv_card_list)
    ListView lvCardList;
    private String mUserId;
    private String mToken;
    private String mShopId;
    private List<VIPCardBean> mValidList = new ArrayList<>();
    private VipCardAdapter vipCardAdapter;
    private YogaLayoutManager wrap;
    private static final int NOT_1 = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NOT_1:
                    if (vipCardAdapter != null){
                        vipCardAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    };

    @Override
    protected int setLayoutId() {
        return R.layout.user_layout_card;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected void initData() {
        super.initData();
        mUserId = (String) SharedPreferencesUtils.getSP(getContext(), "Mobile", "");
        mToken = (String) SharedPreferencesUtils.getSP(getContext(), "Token", "");
        mShopId = (String) SharedPreferencesUtils.getSP(getContext(), "shopId", "");
        vipCardAdapter = new VipCardAdapter(getActivity(), mValidList, R.layout.item_user_card_1, 1);
        lvCardList.setAdapter(vipCardAdapter);
        getCardDataValid();
    }

    @Override
    protected void setListener() {
        lvCardList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VIPCardBean vipCardBean = mValidList.get(position);
                if (vipCardBean.isActivation()) {
                    Intent intent = new Intent(getActivity(), ActivationCardActivity.class);
                    startActivityForResult(intent, 10003);
                } else {
                    Bundle mBundle = new Bundle();
                    mBundle.putString("type", "Valid");
                    mBundle.putParcelable("Bean", vipCardBean);
                    Intent intent = new Intent(getActivity(), CardDetailsActivity.class);
                    intent.putExtras(mBundle);
                    startActivity(intent);
                }
            }
        });
    }

    private void getCardDataValid() {
        Map<String, String> map = new HashMap();
        map.put("token", mToken);
        map.put("status", "0");
        final String str = map.toString();
        showLoading();
        HttpClient.post(ApiConstants.Urls.USER_CARD_LIST_V2, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                Logcat.i("\n接口地址：" + "" + "\n提交的参数：" + str + "\n" + "返回码：" + statusCode + "\n" + "返回参数：" + content);
                ResultBean resultBean = JSON.parseObject(content, ResultBean.class);
                dismissLoading();
                if (resultBean.getCode().equals("1")) {
                    if (!StringUtil.isEmpty(resultBean.getData())) {
                        List<VIPCardBean> beanList = JSON.parseArray(resultBean.getData(), VIPCardBean.class);
                        if (beanList.size() > 0) {
                            mValidList.clear();
                            mValidList.addAll(beanList);
                            VIPCardBean vipCardBean = new VIPCardBean();
                            vipCardBean.setActivation(true);
                            mValidList.add(vipCardBean);
                            //wrap.showContent();
                            showHaveDataLayout();
                            Message msg = new Message();
                            msg.what = NOT_1;
                            msg.obj = new Bundle();
                            handler.sendMessage(msg);
                        } else {
                            showNoDataLayout();
                        }
                    } else {
                        showNoDataLayout();
                    }
                } else {
                    ToastUtil.showToast(resultBean.getMsg());
                    //showHaveDataLayout();
                    //wrap.showEmpty();
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                //wrap.showEmpty();
                dismissLoading();
                ToastUtil.showToast("网络错误");
                //showHaveDataLayout();
            }
        });
    }

    private void showHaveDataLayout() {
        if (llNoCard != null && lvCardList != null) {
            lvCardList.setVisibility(View.VISIBLE);
            llNoCard.setVisibility(View.GONE);
        }
    }

    private void showNoDataLayout() {
        if (llNoCard != null && lvCardList != null) {
            lvCardList.setVisibility(View.GONE);
            llNoCard.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //unbinder.unbind();
    }

    @OnClick({R.id.tv_activa_card, R.id.tv_exchange})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_activa_card:
                Intent intent = new Intent(getActivity(), ActivationCardActivity.class);
                startActivityForResult(intent, 10005);
                break;
            case R.id.tv_exchange:
                ToastUtil.showToast("功能正在开发中,敬请期待");
                break;
        }
    }
}
