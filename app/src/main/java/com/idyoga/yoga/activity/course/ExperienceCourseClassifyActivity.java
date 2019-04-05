package com.idyoga.yoga.activity.course;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.fastjson.JSON;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.shop.ShopExperienceCourseActivity;
import com.idyoga.yoga.adapter.base.BaseDelegateAdapter;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.presenter.ICommonRequestPresenter;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.lbs.YogaLocationListener;
import com.idyoga.yoga.listener.OnItemClickListener;
import com.idyoga.yoga.model.ClassifyBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.utils.PermissionUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * 文 件 名: ExperienceCourseListActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/19
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 *
 */
public class ExperienceCourseClassifyActivity extends BaseActivity implements ICommonViewUi {
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
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private DelegateAdapter delegateAdapter;
    private List<ClassifyBean> mBeanList = new ArrayList<>();
    private List<DelegateAdapter.Adapter> mAdapters;
    private String mShopId,cityId;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).flymeOSStatusBarFontColor("#333333").init();
    }

    @Override
    protected ICommonRequestPresenter initICommonViewUi() {
        return iCommonRequestPresenter = new CommonRequestPresenterImpl(mContext,this);
    }

    @Override
    protected void initData() {
        showLoading("加载中");

        mShopId = (String) SharedPreferencesUtils.getSP(this,"ShopId","");
        cityId = (String) SharedPreferencesUtils.getSP(this,"cityId","");
        toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_CLASS);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_classify_all;
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("全部分类");
        mAdapters = new ArrayList<>();
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        mRvList.setLayoutManager(layoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRvList.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(1, 20);
        delegateAdapter= new DelegateAdapter(layoutManager, true);
        initAllTypeView();
        mRvList.setAdapter(delegateAdapter);
    }

    BaseDelegateAdapter mAdapter;
    private void initAllTypeView() {
        GridLayoutHelper layoutHelper = new GridLayoutHelper(4);
        layoutHelper.setGap(0);
        layoutHelper.setHGap(0);
        layoutHelper.setAutoExpand(false);
        layoutHelper.setMargin(15,5,15,5);
        layoutHelper.setBgColor(Color.parseColor("#ffffff"));
        mAdapter = new BaseDelegateAdapter(this, layoutHelper, R.layout.item_classify_all, 0, 1) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ImageView imageView = holder.getView(R.id.iv_img);
                Glide.with(mContext)
                        .load(mBeanList.get(position).getImage_url())
                        .placeholder(R.drawable.img_05)
                        .error(R.drawable.img_05)
                        .into(imageView);
                holder.setText(R.id.tv_name, mBeanList.get(position).getName());
            }
        };
        delegateAdapter.addAdapter(mAdapter);
    }

    @Override
    protected void setListener() {
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int type, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("classId",mBeanList.get(position).getId()+"");
                bundle.putString("className",mBeanList.get(position).getName()+"");
//                startActivity(ExperienceCourseListActivity.class,bundle);
                startActivity(ShopExperienceCourseActivity.class,bundle);
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

    @Override
    public void toRequest(int eventTag) {
        if (eventTag == ApiConstants.EventTags.HOME_EXPERIENCE_CLASS) {
            Map map = new HashMap();
            map.put("shopId",mShopId);
            map.put("cityId",cityId);
            iCommonRequestPresenter.request(eventTag, this, ApiConstants.Urls.HOME_EXPERIENCE_CLASS_V2, map);
        }
    }

    @Override
    public void getRequestData(int eventTag, String result) {
        dismissLoading();
        Logcat.i("eventTag:"+eventTag);
        ResultBean bean = JSON.parseObject(result, ResultBean.class);
        if (bean.getCode().equals("1") && bean.getData() != null || bean.getData() != "[]") {
            if (eventTag == ApiConstants.EventTags.HOME_EXPERIENCE_CLASS) {
                List<ClassifyBean> beanList = JSON.parseArray(bean.getData(), ClassifyBean.class);
                mBeanList.addAll(beanList);
                mAdapter.setCount(mBeanList.size());
                mAdapter.notifyDataSetChanged();
            }
        }
    }


    @Override
    public void onRequestFailureException(int eventTag, String msg) {

    }

}
