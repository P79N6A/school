package com.idyoga.yoga.activity.video;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.VideoClassifyAdapter;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.presenter.ICommonRequestPresenter;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.VideoClassifyBean;
import com.idyoga.yoga.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;

/**
 * 文 件 名: ClassifyChoiceActivity
 * 创 建 人: By k
 * 创建日期: 2018/5/14 09:21
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class VideoClassifyActivity extends BaseActivity implements ICommonViewUi {


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
    VideoClassifyAdapter mAdapter;
    List<VideoClassifyBean> mBeanList = new ArrayList<>();

    @Override
    protected ICommonRequestPresenter initICommonViewUi() {
        return iCommonRequestPresenter = new CommonRequestPresenterImpl(this, this);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).flymeOSStatusBarFontColor("#333333").init();
    }

    @Override
    protected void initData() {
        showLoading("加载中...");
        toRequest(ApiConstants.EventTags.VIDEO_CLASSIFY);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_video_classify;
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("筛选");
        mTvTitleRight.setText("完成");
        mAdapter = new VideoClassifyAdapter(this, mBeanList, R.layout.item_video_classify);
        mLvList.setAdapter(mAdapter);
    }

    @Override
    protected void setListener() {
        mLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


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
                Logcat.i("TagList:" + mAdapter.getClassify().toString());
                if (ListUtil.isEmpty(mAdapter.getClassify())) {
                    ToastUtil.showToast("请选择类别");
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("classify",mAdapter.getClassify().toString());
                    startActivity(VideoClassifyListActivity.class, bundle);
                }
                break;
        }
    }


    private void initViewData(List<VideoClassifyBean> beanList) {
        Logcat.i("beanList:" + beanList.size());
        mBeanList.addAll(beanList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void toRequest(int eventTag) {
        Map map = new HashMap();
        if (eventTag == ApiConstants.EventTags.VIDEO_CLASSIFY) {
            map.put("type", "4");
            iCommonRequestPresenter.request(eventTag, this, ApiConstants.Urls.VIDEO_CLASSIFY, map);
        }

    }

    @Override
    public void getRequestData(int eventTag, String result) {
        dismissLoading();
        Logcat.i("e:" + eventTag + "/" + result);
        ResultBean bean = JSON.parseObject(result, ResultBean.class);
        if (bean.getCode().equals("1") && bean.getData() != null) {
            if (eventTag == ApiConstants.EventTags.VIDEO_CLASSIFY) {
                List<VideoClassifyBean> mBeanList = JSON.parseArray(bean.getData(), VideoClassifyBean.class);
                if (mBeanList.size() > 0) {
                    initViewData(mBeanList);
                }
            }
        }
    }


    @Override
    public void onRequestFailureException(int eventTag, String msg) {

    }

}