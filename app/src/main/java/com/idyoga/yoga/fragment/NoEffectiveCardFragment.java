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
import com.idyoga.yoga.activity.card.CardDetailsActivity;
import com.idyoga.yoga.adapter.VipCardAdapter;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.VIPCardBean;
import com.idyoga.yoga.view.YogaLayoutManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.Unbinder;
import okhttp3.Request;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

/**
 * Created by mjh on 2019/1/23.
 */

public class NoEffectiveCardFragment extends BaseFragment {

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
    Unbinder unbinder;
    private String mToken;
    private String mUserId;
    private String mShopId;
    private List<VIPCardBean> mInValidList = new ArrayList<>();
    private YogaLayoutManager mYogaLayoutManager2;
    private static final int NOT_1 = 1, NOT_2 = 2;
    private VipCardAdapter vipCardAdapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NOT_2:
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
        mYogaLayoutManager2 = YogaLayoutManager.wrap(lvCardList);
        vipCardAdapter = new VipCardAdapter(getActivity(), mInValidList, R.layout.item_user_card_1, 1);
        lvCardList.setAdapter(vipCardAdapter);
        getCardDataValid();
    }

    @Override
    protected void setListener() {
        super.setListener();
        lvCardList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle mBundle = new Bundle();
                mBundle.putString("type", "Invalid");
                mBundle.putParcelable("Bean", mInValidList.get(position));
                Intent intent = new Intent(getActivity(), CardDetailsActivity.class);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });
    }

    private void getCardDataValid() {
        Map<String, String> map = new HashMap();
        map.put("token", mToken);
        map.put("status", "1");//1
        final String str = map.toString();
        HttpClient.post(ApiConstants.Urls.USER_CARD_LIST_V2, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                Logcat.i("\n接口地址：" + "" + "\n提交的参数：" + str + "\n" + "返回码：" + statusCode + "\n" + "返回参数：" + content);
                ResultBean resultBean = JSON.parseObject(content, ResultBean.class);
                if (resultBean.getCode().equals("1")) {
                    if (!StringUtil.isEmpty(resultBean.getData())) {
                        List<VIPCardBean> beanList = JSON.parseArray(resultBean.getData(), VIPCardBean.class);
                        if (beanList.size() > 0) {
                            mInValidList.clear();
                            mInValidList.addAll(beanList);
                            mYogaLayoutManager2.showContent();
                            showHaveDataLayout();
                            Message msg = new Message();
                            msg.what = NOT_2;
                            msg.obj = new Bundle();
                            handler.sendMessage(msg);
                        } else {
                            showHaveDataLayout();
                            mYogaLayoutManager2.showEmpty();
                        }
                    } else {
                        mYogaLayoutManager2.showEmpty();
                    }
                } else {
                    showHaveDataLayout();
                    mYogaLayoutManager2.showEmpty();
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                showHaveDataLayout();
                mYogaLayoutManager2.showEmpty();
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
}
