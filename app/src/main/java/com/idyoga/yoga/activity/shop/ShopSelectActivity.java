package com.idyoga.yoga.activity.shop;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.TutorSelectBean;
import com.idyoga.yoga.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import vip.devkit.library.Logcat;
import vip.devkit.view.common.suklib.view.FlowLayout.FlowLayout;
import vip.devkit.view.common.suklib.view.FlowLayout.TagAdapter;
import vip.devkit.view.common.suklib.view.FlowLayout.TagFlowLayout;

/**
 * 文 件 名: ClassifyChoiceActivity
 * 创 建 人: By k
 * 创建日期: 2018/5/14 09:21
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注： 导师的筛选
 */
public class ShopSelectActivity extends BaseActivity implements ICommonViewUi {

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

    List<TutorSelectBean> mBeanList = new ArrayList<>();
    @BindView(R.id.tag_view)
    TagFlowLayout mTagView;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).flymeOSStatusBarFontColor("#333333").init();
    }

    @Override
    protected void initData() {
        showLoading("加载中...", true);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_shop_select;
    }

    @Override
    protected void initView() {
        iCommonRequestPresenter = new CommonRequestPresenterImpl(mContext, this);
        toRequest(ApiConstants.EventTags.TUTOR_SELECT);
        mTvTitleText.setText("瑜伽馆筛选");
        mTvTitleRight.setText("完成");
        mTagView.setAdapter(mTagAdapter);

    }

    @Override
    protected void setListener() {

    }


    @OnClick({R.id.ll_title_back, R.id.ll_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.ll_title_right:
                if (mTagView.getSelectedList().size() == 0) {
                    ToastUtil.showToast("请选择筛选类别");
                } else {
                    List list = new ArrayList();
                    list.addAll(mTagView.getSelectedList());
                    Collections.sort(list);
                    Bundle bundle = new Bundle();
                    bundle.putString("tagList", list.toString());
                    Intent intent = new Intent(this, ShopListActivity.class);
                    intent.putExtras(bundle);
                    setResult(700, intent);
                    finish();
                }
                break;
        }
    }

    @Override
    public void toRequest(int eventTag) {
        if (eventTag == ApiConstants.EventTags.TUTOR_SELECT) {
            Map<String, String> map = new HashMap<>();
            iCommonRequestPresenter.requestGet(eventTag, mContext, ApiConstants.Urls.TUTOR_SELECT, map);
        }
    }

    @Override
    public void getRequestData(int eventTag, String result) {
        dismissLoading();
        Logcat.i("返回的JSON :" + result);
        ResultBean resultBean = JSON.parseObject(result, ResultBean.class);
        if (eventTag == ApiConstants.EventTags.TUTOR_SELECT) {
            if (resultBean.getCode().equals("1") && resultBean.getData() != null) {
                List<TutorSelectBean> beanList = JSON.parseArray(resultBean.getData(), TutorSelectBean.class);
                mBeanList.addAll(beanList);
                mTagAdapter.notifyDataChanged();
            }
        }
    }


    @Override
    public void onRequestFailureException(int eventTag, String msg) {

    }


    TagAdapter<TutorSelectBean> mTagAdapter = new TagAdapter<TutorSelectBean>(mBeanList) {
        @Override
        public View getView(FlowLayout parent, int position, TutorSelectBean tutorSelectBean) {
            LayoutInflater mInflater = LayoutInflater.from(mContext);
            TextView tv = (TextView) mInflater.inflate(R.layout.option_item, parent, false);
            tv.setText(tutorSelectBean.getName());
            tv.setTextColor(Color.parseColor("#333333"));
//                tv.setBackgroundResource(R.drawable.bg_serach_tag);
            return tv;
        }
    };

}