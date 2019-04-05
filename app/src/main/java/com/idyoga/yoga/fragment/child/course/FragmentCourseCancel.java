package com.idyoga.yoga.fragment.child.course;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.VipCardAdapter;
import com.idyoga.yoga.adapter.course.UserCourseAdapter;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.NetWorkConstant;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.UserCourseBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.view.YogaLayoutManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Request;
import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * 文 件 名: FragmentExperienceCourseAppointment
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 创建日期: 2018/3/19
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class FragmentCourseCancel extends BaseFragment {
    @BindView(R.id.lv_course_list)
    ListView mLvCourseList;
    @BindView(R.id.srl_view)
    SwipeRefreshLayout mRefreshLayout;
    private List<UserCourseBean> beanList = new ArrayList<>();
    private List<String> mStringList = new ArrayList<>();
    private Map<String, List<UserCourseBean>> mListMap = new HashMap<>();
    private UserCourseAdapter mAdapter;
    private String UserId, Token, ShopId;
    private static final int NOT_1 = 1, NOT_2 = 2;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NOT_1:
                    mAdapter.notifyDataSetChanged();
                    break;
                case NOT_2:
                    break;
            }
        }
    };

    @Override
    protected void initData() {
        super.initData();
        UserId = (String) SharedPreferencesUtils.getSP(mActivity, "Mobile", "");
        Token = (String) SharedPreferencesUtils.getSP(mActivity, "Token", "");
        ShopId = (String) SharedPreferencesUtils.getSP(mActivity, "shopId", "");
        if (!CommonUtils.isBlank(UserId) && !CommonUtils.isBlank(Token) && !CommonUtils.isBlank(ShopId)) {
            getCourse(Token, ShopId);
        } else {
            Logcat.e("缺少参数");
        }
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(mLvCourseList);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.user_fragment_course;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mLayoutManager.showLoading();
        mAdapter = new UserCourseAdapter(mActivity, mStringList, mListMap, R.layout.item_user_course_confirm, "2");
        mLvCourseList.setAdapter(mAdapter);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                beanList.clear();
                getCourse(Token, ShopId);
                mRefreshLayout.setRefreshing(false);
            }
        });
        mLvCourseList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                boolean enable = false;
                if (mLvCourseList != null && mLvCourseList.getChildCount() > 0) {
                    boolean firstItemVisible = mLvCourseList.getFirstVisiblePosition() == 0;
                    boolean topOfFirstItemVisible = mLvCourseList.getChildAt(0).getTop() == 0;
                    enable = firstItemVisible && topOfFirstItemVisible;
                }
                mRefreshLayout.setEnabled(enable);

            }
        });
    }

    private void getCourse(String token, String shopId) {
        if (mLayoutManager != null) {
            mLayoutManager.showLoading();
        }
        Map map = new HashMap();
        map.put("token", token);
        map.put("status", "2");
        map.put("shopId", shopId);
        final String str = map.toString();
        HttpClient.post(ApiConstants.Urls.APPOINTMENT_GET_COURSE_LIST, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                Logcat.i("\n接口地址：" + "" + "\n提交的参数：" + str + "\n" + "返回码：" + statusCode + "\n" + "返回参数：" + content);
                ResultBean resultBean = JSON.parseObject(content, ResultBean.class);
                if (resultBean.getCode().equals("1")) {
                    List<UserCourseBean> list = JSON.parseArray(resultBean.getData(), UserCourseBean.class);
                    beanList.addAll(list);
                    HandleData(beanList);
                } else {
                    ToastUtil.showToast(resultBean.getMsg());
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
            }
        });
    }

    private void HandleData(List<UserCourseBean> list) {
        if (ListUtil.isEmpty(list)) {
            mLayoutManager.showEmpty();
        } else {
            mLayoutManager.showContent();
        }
        for (int i = 0; i < list.size(); i++) {
            String a = VipCardAdapter.getDateStringByTimeSTamp(list.get(i).getStart(), "MM月dd日");
            if (!mStringList.contains(a)) {
                mStringList.add(a);
            }
        }
        for (int i = 0; i < mStringList.size(); i++) {
            List<UserCourseBean> beanList1 = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                String b = VipCardAdapter.getDateStringByTimeSTamp(list.get(j).getStart(), "MM月dd日");
                if (mStringList.get(i).equals(b)) {
                    beanList1.add(list.get(j));
                }
            }
            mListMap.put(mStringList.get(i), beanList1);
        }
        if (mStringList.size() > 0) {
            Message msg = new Message();
            msg.what = NOT_1;
            msg.obj = new Bundle();
            handler.sendMessage(msg);
        } else {
        }
    }

}
