package com.idyoga.yoga.fragment.child;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.MainActivity;
import com.idyoga.yoga.activity.course.ConsultCourseDetailActivity;
import com.idyoga.yoga.adapter.ConsultCourseAdapter;
import com.idyoga.yoga.adapter.VipCardAdapter;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.fragment.HomeFragment;
import com.idyoga.yoga.model.ConsultCourseBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.UserCourseBean;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.view.YogaLayoutManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import okhttp3.Request;
import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

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
public class FragmentConsult extends BaseFragment {


    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.srl_Refresh)
    SwipeRefreshLayout mSrlRefresh;
    private String userId;
    private String tag = "1";
    private List<ConsultCourseBean> mBeanList = new ArrayList<>();
    private List<String> mStringList = new ArrayList<>();
    private Map<String, List<ConsultCourseBean>> mListMap = new HashMap<>();
    private ConsultCourseAdapter mAdapter;


    public static FragmentConsult getInstance(String tag) {
        FragmentConsult fragment = new FragmentConsult();
        Bundle bundle = new Bundle();
        bundle.putString("tag", tag);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initData() {
        super.initData();
        mBeanList.clear();
        Bundle bundle = getArguments();
        tag = bundle.getString("tag");
        userId = String.valueOf((int) SharedPreferencesUtils.getSP(mActivity, "UserId", -1));
        getData(userId + "", tag);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.layout_rv_list;
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(mRvList);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        Bundle bundle = getArguments();
        tag = bundle.getString("tag");
        mLayoutManager.setEmpty(R.layout.layout_conslut_course_empty);
        if (!StringUtil.isEmpty(tag)){
            if (tag.equals("1")){
                mLayoutManager.initView(YogaLayoutManager.EMPTY).setText(R.id.tv_content,"暂无咨询中的课程");
            }else if (tag.equals("2")){
                mLayoutManager.initView(YogaLayoutManager.EMPTY).setText(R.id.tv_content,"暂无待上课的课程");
            }else if (tag.equals("3")){
                mLayoutManager.initView(YogaLayoutManager.EMPTY).setText(R.id.tv_content,"暂无已经关闭的课程");
            }else {
                mLayoutManager.initView(YogaLayoutManager.EMPTY).setText(R.id.tv_go,"暂无数据");
            }
            mLayoutManager.initView(YogaLayoutManager.EMPTY).setText(R.id.tv_go,"立即咨询");
        }
        mLayoutManager.showLoading();
        mRvList.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new ConsultCourseAdapter(R.layout.item_user_course_confirm, mStringList, mListMap, tag);
        mRvList.setAdapter(mAdapter);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mBeanList.clear();
                mAdapter.notifyDataSetChanged();
                mLayoutManager.showLoading();
                getData(userId, tag);
                mSrlRefresh.setRefreshing(false);
            }
        });
        mLayoutManager.setOnInflateListener(new YogaLayoutManager.OnInflateListener() {
            @Override
            public void onInflate(int viewType, View view) {
                switch (view.getId()) {
                    case R.id.tv_go:
                        Bundle bundle = new Bundle();
                        bundle.putString("FTag", "0_1");
                        EventBus.getDefault().post(new PostResult("FTag", "0_1"));
                        startActivityWithExtras(MainActivity.class, bundle);
                        getActivity().finish();
                        break;
                }
            }
        });
    }

    private void initViewData(List<ConsultCourseBean> beanList) {
        mStringList.clear();
        mListMap.clear();
        if (ListUtil.isEmpty(beanList)) {
            mLayoutManager.showEmpty();
        } else {
            mLayoutManager.showContent();
        }
        for (int i = 0; i < beanList.size(); i++) {
            String a = VipCardAdapter.getDateStringByTimeSTamp((long) beanList.get(i).getExpectTime(), "MM月dd日");
            if (!mStringList.contains(a)) {
                mStringList.add(a);
            }
        }
        /**先排序在倒序*/
        Collections.sort(mStringList);
        Collections.reverse(mStringList);
        for (int i = 0; i < mStringList.size(); i++) {
            List<ConsultCourseBean> beanList1 = new ArrayList<>();
            for (int j = 0; j < beanList.size(); j++) {
                String b = VipCardAdapter.getDateStringByTimeSTamp((long) beanList.get(j).getExpectTime(), "MM月dd日");
                if (mStringList.get(i).equals(b)) {
                    beanList1.add(beanList.get(j));
                }
            }
            mListMap.put(mStringList.get(i), beanList1);
        }
        Map<String, List<ConsultCourseBean>> map = new TreeMap<>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        return obj1.compareTo(obj2);
                    }
                });
        map.putAll(mListMap);
        mListMap.clear();
        mListMap.putAll(map);

        mBeanList.addAll(beanList);
        mAdapter.setTag(tag);
        mAdapter.notifyDataSetChanged();
    }

    private void getData(String userId, String tag) {
        Map map = new HashMap();
        map.put("userId", userId+"");
        map.put("status", tag+"");
        HttpClient.post(ApiConstants.Urls.CONSULT_LESSON_LIST, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                ResultBean bean = JSON.parseObject(content, ResultBean.class);
                if (bean.getCode().equals("1")) {
                    List<ConsultCourseBean> beanList = JSON.parseArray(bean.getData(), ConsultCourseBean.class);
                    initViewData(beanList);
                }
            }
            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                mLayoutManager.showNetError();
            }
        });
    }
}
