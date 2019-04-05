package com.idyoga.yoga.activity.card;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.ChildCardAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.presenter.ICommonRequestPresenter;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.model.ChildCardListBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.UserInfoBean;
import com.idyoga.yoga.model.VIPCardBean;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.UserUtil;
import com.idyoga.yoga.view.YogaLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * 文 件 名: CardDetailsActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/3/22
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注： 权益卡列表
 */
public class ChildCardListActivity extends BaseActivity implements ICommonViewUi {
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
    private VIPCardBean mCardBean;
    private String cardId, userId, shopId, cardName;
    private ChildCardListBean mChildCardListBean;
    private List<ChildCardListBean.CardListBean> mBeanList = new ArrayList<>();
    private ChildCardAdapter mAdapter;
    private String cityId;

    @Override
    protected void initData() {
        mBeanList.clear();
        cityId = (String) SharedPreferencesUtils.getSP(mContext, "cityId", "");
        UserInfoBean bean = UserUtil.getUserBean(this);
        if (bean != null) {
            userId = UserUtil.getUserBean(this).getId() + "";
        }
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mCardBean = bundle.getParcelable("cardBean");
            cardId = bundle.getString("cardId");
            shopId = bundle.getString("shopId");
            cardName = bundle.getString("cardName");
        }
        Logcat.e("userId:" + userId + "/" + cardId + "/" + shopId);
        toRequest(ApiConstants.EventTags.USER_CARD_CHILD_LIST);
    }

    @Override
    protected ICommonRequestPresenter initICommonViewUi() {
        return iCommonRequestPresenter = new CommonRequestPresenterImpl(this, this);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).init();
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(mLvList);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_card_child_list;
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("附属卡列表");
        mLayoutManager.showLoading();
        mAdapter = new ChildCardAdapter(this, mBeanList, R.layout.item_child_card);
        mLvList.setAdapter(mAdapter);
    }

    @Override
    protected void setListener() {
        mLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                if (mBeanList.get(i).isAdd()) {
                    bundle.putString("membershipId", cardId + "");
                    bundle.putString("cardName", cardName + "");
                    startActivity(AddChildCardActivity.class, 777, bundle);
                } else {
                    Logcat.e("点击事件");
                }
            }
        });
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

    /**
     * @param bean
     */
    private void initViewData(ChildCardListBean bean) {
        mLayoutManager.showContent();
        for (int i = 0; i < bean.getCardList().size(); i++) {
            mBeanList.add(bean.getCardList().get(i));
        }
        /**
         * 可添加的附属卡
         */
        for (int i = 0; i < bean.getValidAnnexNum(); i++) {
            mBeanList.add(new ChildCardListBean.CardListBean(true));
        }
        mAdapter.setCardName(mCardBean.getCard_name());
        Logcat.e("mBeanList:" + mBeanList.size() + "/" + bean.getValidAnnexNum() + "/" + bean.getAnnexNum() + "/" + bean.getCardList().size());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void toRequest(int eventTag) {
        Map map = new HashMap();
        if (eventTag == ApiConstants.EventTags.USER_CARD_CHILD_LIST) {
            map.put("userId", userId + "");
            map.put("token", UserUtil.getUserBean(this).getToken() + "");
//            map.put("cardId", cardId + "");
            map.put("membershipId", cardId + "");
//            map.put("cityId", cityId + "");
            Logcat.i("附属卡提交的参数：" + map.toString());
            iCommonRequestPresenter.request(eventTag, this, ApiConstants.Urls.USER_CARD_CHILD_LIST_V2, map);
        }
    }


    @Override
    public void getRequestData(int eventTag, String result) {
        Logcat.i("附属卡返回数据：" + eventTag + "/" + result);
        ResultBean bean = JSON.parseObject(result, ResultBean.class);
        if (bean.getCode().equals("1")) {
            if (eventTag == ApiConstants.EventTags.USER_CARD_CHILD_LIST) {
                mChildCardListBean = JSON.parseObject(bean.getData(), ChildCardListBean.class);
                initViewData(mChildCardListBean);
            }
        } else {
            ToastUtil.showToast(bean.getMsg());
        }
    }


    @Override
    public void onRequestFailureException(int eventTag, String msg) {
        mLayoutManager.showNetError();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 777) {
            initData();
        }
    }
}
