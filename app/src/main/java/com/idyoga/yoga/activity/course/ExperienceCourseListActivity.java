package com.idyoga.yoga.activity.course;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
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
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.home.AppointmentIntroductionActivity;
import com.idyoga.yoga.activity.web.YogaGymDetailsActivity;
import com.idyoga.yoga.adapter.ExperienceClassCourseAdapter;
import com.idyoga.yoga.adapter.ExperienceClassShopAdapter;
import com.idyoga.yoga.adapter.ExperienceCourseAdapter;
import com.idyoga.yoga.adapter.GuidePageAdapter;
import com.idyoga.yoga.adapter.HomeFrPagerAdapter;
import com.idyoga.yoga.adapter.base.BaseDelegateAdapter;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.presenter.ICommonRequestPresenter;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.fragment.child.FragmentExperienceCourse;
import com.idyoga.yoga.fragment.child.FragmentExperienceShop;
import com.idyoga.yoga.listener.OnItemClickListener;
import com.idyoga.yoga.listener.OnVerticalScrollListener;
import com.idyoga.yoga.model.ExperienceCourseClassBean;
import com.idyoga.yoga.model.ResultBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.view.common.tab.AdvancedPagerSlidingTabStrip;

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
 */
public class ExperienceCourseListActivity extends BaseActivity implements ICommonViewUi {
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
    AdvancedPagerSlidingTabStrip mTabView;
    @BindView(R.id.vp_view)
    ViewPager mVpView;


    private String mShopId;
    private String mTagId, className;
    private String mType;
    private String cityId;
    private int pageIndex = 1;
    private boolean isLoadMore = true;
    private List<ExperienceCourseClassBean.CourseListBean> mCourseBeanList = new ArrayList<>();
    private List<ExperienceCourseClassBean.ShopBean> mShopBeanList = new ArrayList<>();

    private List<String> mStringList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ExperienceClassShopAdapter mShopAdapter;
    private ExperienceClassCourseAdapter mCourseAdapter;

    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).flymeOSStatusBarFontColor("#333333").init();
    }

    @Override
    protected ICommonRequestPresenter initICommonViewUi() {
        return iCommonRequestPresenter = new CommonRequestPresenterImpl(mContext, this);
    }

    @Override
    protected void initData() {
        mCourseBeanList.clear();
        mShopBeanList.clear();
        Bundle bundle = getIntent().getExtras();
        mShopId = (String) SharedPreferencesUtils.getSP(this, "shopId", "");
        cityId = (String) SharedPreferencesUtils.getSP(this, "cityId", "");
        if (bundle != null) {
            mTagId = bundle.getString("classId");
            className = bundle.getString("className");
        } else {
            Logcat.e("参数为空");
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_course_e_list;
    }

    @Override
    protected void initView() {
        mStringList.clear();
        mFragments.clear();
        mTvTitleText.setText(className);
        mStringList.add("附近的馆");
        mStringList.add("附近的课");
        mFragments.add(new FragmentExperienceShop());
        mFragments.add(new FragmentExperienceCourse());
        mVpView.setAdapter(new HomeFrPagerAdapter(getSupportFragmentManager(), mStringList, mFragments));
        mTabView.setViewPager(mVpView);
    }

    @Override
    protected void setListener() {

    }


    private void initViewData(ExperienceCourseClassBean classBean) {
        if (pageIndex == 1) {
            mCourseBeanList.clear();
            mShopBeanList.clear();
        }
        if (pageIndex == 1 && classBean.getCourseList().size() == 0) {

        }
        if (classBean.getCourseList().size() < 20) {
            isLoadMore = false;
        }
        if (pageIndex == 1 && classBean.getShop().size() == 0) {

        }
        if (classBean.getShop().size() < 20) {
            isLoadMore = false;
        }
        mCourseBeanList.addAll(classBean.getCourseList());
        mShopBeanList.addAll(classBean.getShop());

        mCourseAdapter.notifyDataSetChanged();
        mShopAdapter.notifyDataSetChanged();

    }

    @Override
    public void toRequest(int eventTag) {
        if (eventTag == ApiConstants.EventTags.HOME_EXPERIENCE_CLASS_LIST) {
            Map map = new HashMap();
            map.put("shopId", mShopId);
            map.put("tagId", mTagId);
            map.put("cityId", cityId);
            map.put("page", pageIndex + "");
            map.put("size", "20");
            Logcat.i("权益 分类 提交的参数：" + map.toString());
            iCommonRequestPresenter.request(eventTag, this, ApiConstants.Urls.HOME_EXPERIENCE_CLASS_LIST, map);
        }
    }

    @Override
    public void getRequestData(int eventTag, String result) {
        dismissLoading();
        Logcat.i("eventTag:" + eventTag + "/" + result);
        ResultBean bean = JSON.parseObject(result, ResultBean.class);
        if (bean.getCode().equals("1") && bean.getData() != null || bean.getData() != "[]") {
            if (eventTag == ApiConstants.EventTags.HOME_EXPERIENCE_CLASS_LIST) {
                ExperienceCourseClassBean classBean = JSON.parseObject(bean.getData(), ExperienceCourseClassBean.class);
                if (classBean != null) {
                    initViewData(classBean);
                }
            }
        }
    }


    @Override
    public void onRequestFailureException(int eventTag, String msg) {

    }



    @OnClick({R.id.ll_title_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
        }
    }

}
