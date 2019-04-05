package com.idyoga.yoga.fragment.child;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.course.ExperienceCourseListActivity;
import com.idyoga.yoga.activity.home.AppointmentIntroductionActivity;
import com.idyoga.yoga.adapter.ExperienceClassCourseAdapter;
import com.idyoga.yoga.adapter.ExperienceClassShopAdapter;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.presenter.ICommonRequestPresenter;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.listener.OnVerticalScrollListener;
import com.idyoga.yoga.model.ExperienceClassCourseBean;
import com.idyoga.yoga.model.ExperienceCourseClassBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.view.RecycleViewDivider;
import com.idyoga.yoga.view.YogaLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * 文 件 名: FragmentExperienceCourse
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/29
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class FragmentExperienceCourse extends BaseFragment implements ICommonViewUi {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
   YogaLayoutManager mYogaLayoutManager;
    List<ExperienceClassCourseBean> mBeanList = new ArrayList();
    private int pageIndex = 1;
    private String cityId;
    private String tagId;
    private String mShopId;
    private ExperienceClassCourseAdapter mCourseAdapter;
    private boolean isLoadMore = true;
    ExperienceCourseListActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (ExperienceCourseListActivity) context;
        Bundle bundle = activity.getIntent().getExtras();
        tagId = bundle.getString("classId");
        Logcat.i("tagId:" + tagId);
    }


    @Override
    protected void initData() {
        cityId = (String) SharedPreferencesUtils.getSP(getActivity(), "cityId", "");
        mShopId = (String) SharedPreferencesUtils.getSP(getActivity(), "shopId", "");
        toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_CLASS_COURSE);

    }

    @Override
    protected void onFirstVisible() {
        super.onFirstVisible();
    }

    @Override
    protected ICommonRequestPresenter initICommonViewUi() {
        return iCommonRequestPresenter = new CommonRequestPresenterImpl(getActivity(), this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.layout_common_list;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mYogaLayoutManager= YogaLayoutManager.wrap(mRvList);
        mYogaLayoutManager.showLoading();
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        viewPool.setMaxRecycledViews(1, 20);
        mRvList.setRecycledViewPool(viewPool);
        mRvList.setLayoutManager(new LinearLayoutManager(mActivity));
        mCourseAdapter = new ExperienceClassCourseAdapter(R.layout.item_experience_class_course, mBeanList);
        mRvList.setAdapter(mCourseAdapter);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mCourseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Logcat.i("lessonId:" + mBeanList.get(position).getId() + "/" + mShopId);
                for (int i = 0; i < mBeanList.size(); i++) {
                    Logcat.i("mBeanList lessonId:" + mBeanList.get(i).getId());
                }
                Bundle bundle = new Bundle();
                bundle.putString("shopId", mShopId + "");
                bundle.putString("lessonId", mBeanList.get(position).getId() + "");
                startActivityWithExtras(AppointmentIntroductionActivity.class, bundle);
            }
        });

        mRvList.addOnScrollListener(new OnVerticalScrollListener() {
            @Override
            public void onScrolledToBottom() {
                super.onScrolledToBottom();
                if (isLoadMore) {
                    ++pageIndex;
                    toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_CLASS_COURSE);
                }
            }
        });
    }

    private void initViewData(  List<ExperienceClassCourseBean> beanList ) {
        Logcat.i("" + beanList.size());
        if (pageIndex == 1 && ListUtil.isEmpty(beanList)) {
            beanList = new ArrayList<>();
            mYogaLayoutManager.showEmpty();
        }else {
            mYogaLayoutManager.showContent();
        }
        if (!ListUtil.isEmpty(beanList)) {
            mBeanList.addAll(beanList);
        }
        mCourseAdapter.setNewData(beanList);
        if (beanList.size() < 15) {
            isLoadMore = false;
            View view = View.inflate(mActivity,R.layout.view_list_footer,null);
            mCourseAdapter.addFooterView(view);
        }
        mCourseAdapter.notifyDataSetChanged();
    }

    @Override
    public void toRequest(int eventTag) {
        if (eventTag == ApiConstants.EventTags.HOME_EXPERIENCE_CLASS_COURSE) {
            Map<String, String> map = new HashMap();
            map.put("cityId", cityId + "");
            map.put("shopId", mShopId + "");
            map.put("tagId", tagId + "");
            map.put("size", "15");
            map.put("page", pageIndex + "");
            Logcat.i("提交的参数：" + map.toString());
            iCommonRequestPresenter.request(eventTag, mActivity, ApiConstants.Urls.HOME_EXPERIENCE_CLASS_COURSE, map);
        }
    }

    @Override
    public void getRequestData(int eventTag, String result) {
        Logcat.i("eventTag:" + eventTag + "/" + result);
        ResultBean resultBean = JSON.parseObject(result, ResultBean.class);
        if (resultBean.getCode().equals("1") && resultBean.getData() != null) {
            if (eventTag == ApiConstants.EventTags.HOME_EXPERIENCE_CLASS_COURSE) {
                List<ExperienceClassCourseBean> beanList = JSON.parseArray(resultBean.getData(), ExperienceClassCourseBean.class);
                initViewData(beanList);
            }
        }
    }


    @Override
    public void onRequestFailureException(int eventTag, String msg) {

    }

}
