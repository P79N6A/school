package com.idyoga.yoga.fragment.child.course;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.MainActivity;
import com.idyoga.yoga.adapter.VipCardAdapter;
import com.idyoga.yoga.adapter.course.UserCourseAdapter;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.comm.NetWorkConstant;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.fragment.child.FragmentConsult;
import com.idyoga.yoga.model.CourseListAdapter;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.UserCourseBean;
import com.idyoga.yoga.utils.CommonUtils;
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
import de.greenrobot.event.EventBus;
import okhttp3.Request;
import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

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
public class FragmentExperienceCourseAppointment extends BaseFragment {

    @BindView(R.id.rlv_course_list)
    RecyclerView mLvCourseList;
    @BindView(R.id.srl_view)
    SwipeRefreshLayout mRefreshLayout;

    private List<UserCourseBean> beanList = new ArrayList<>();
    private List<String> mStringList = new ArrayList<>();
    private Map<String, List<UserCourseBean>> mListMap = new HashMap<>();
    private CourseListAdapter mAdapter;
    private String UserId, Token, ShopId,status="4";
    private YogaLayoutManager mYogaLayoutManager;

    public static FragmentExperienceCourseAppointment getInstance(String tag) {
        FragmentExperienceCourseAppointment fragment = new FragmentExperienceCourseAppointment();
        Bundle bundle = new Bundle();
        bundle.putString("status", tag);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void onFirstVisible() {
        super.onFirstVisible();
        Bundle bundle = getArguments();
        if (bundle!=null){
            status = bundle.getString("status");
        }
        Token = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "Token", "");
        Logcat.i("" + UserId + "/" + Token + "/" + status);
        if (!CommonUtils.isBlank(Token)) {
            getCourse(status,Token);
        } else {
            Logcat.e("缺少参数");
        }
    }

    @Override
    protected void initData() {
        super.initData();
        Bundle bundle = getArguments();
        if (bundle!=null){
            status = bundle.getString("status");
        }
        Token = (String) SharedPreferencesUtils.getSP(mActivity, "Token", "");
        if (getUserVisibleHint()&&!CommonUtils.isBlank(Token)){ //&&status.equals("1")
            getCourse(status,Token);
        }
        Logcat.i("" + UserId + "/" + Token + "/" + ShopId);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_experience_course_layout;
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(mRefreshLayout);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mLayoutManager.setEmpty(R.layout.layout_conslut_course_empty);
        Bundle bundle = getArguments();
        status = bundle.getString("status");
        //mLayoutManager.setEmpty(R.layout.layout_conslut_course_empty);
        if (!StringUtil.isEmpty(status)){
            if(status.equals("0")){
                mLayoutManager.initView(YogaLayoutManager.EMPTY).setText(R.id.tv_content,"暂无待确定的课程");
            }else if (status.equals("1")){
                mLayoutManager.initView(YogaLayoutManager.EMPTY).setText(R.id.tv_content,"暂无待上课的课程");
            }else if (status.equals("2")){
                mLayoutManager.initView(YogaLayoutManager.EMPTY).setText(R.id.tv_content,"暂无已取消的课程");
            }else if (status.equals("3")){
                mLayoutManager.initView(YogaLayoutManager.EMPTY).setText(R.id.tv_content,"暂无已结束的课程");
            }else {
                mLayoutManager.initView(YogaLayoutManager.EMPTY).setText(R.id.tv_go,"暂无数据");
            }
            mLayoutManager.initView(YogaLayoutManager.EMPTY).setText(R.id.tv_go,"去看看");
        }
        mLayoutManager.showLoading();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLvCourseList.setLayoutManager(layoutManager);
        mAdapter = new CourseListAdapter(getContext());
        mAdapter.setType(status);
        mLvCourseList.setAdapter(mAdapter);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                beanList.clear();
                getCourse(status, Token);
                mRefreshLayout.setRefreshing(false);
            }
        });
//        mLvCourseList.setOnScrollChangeListener();
        /*mLvCourseList.setOnScrollListener(new AbsListView.OnScrollListener() {
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
        });*/
        mLayoutManager.setOnInflateListener(new YogaLayoutManager.OnInflateListener() {
            @Override
            public void onInflate(int viewType, View view) {
                if (view.getId()==R.id.tv_go){
                    Bundle bundle = new Bundle();
                    bundle.putString("FTag", "0_1");
                    EventBus.getDefault().post(new PostResult("FTag", "0_1"));
                    startActivityWithExtras(MainActivity.class, bundle);
                    getActivity().finish();
                }
            }
        });
    }

    private void getCourse(String status,String token) {
        if (mLayoutManager!=null){
            mLayoutManager.showLoading();
        }
        Map map = new HashMap();
        map.put("token", token+"");
        map.put("status", status+"");
        HttpClient.post(ApiConstants.Urls.PLATFORM_USER_ORDER_CENTER, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
//                Logcat.i("\n接口地址：" + "" + "\n提交的参数：" + str + "\n" + "返回码：" + statusCode + "\n" + "返回参数：" + content);
                ResultBean resultBean = JSON.parseObject(content, ResultBean.class);
                if (resultBean.getCode().equals("1")) {
                    List<UserCourseBean> list = JSON.parseArray(resultBean.getData(), UserCourseBean.class);
                    beanList.clear();
                    beanList.addAll(list);
                    HandleData(beanList);
                } else {
                    ToastUtil.showToast(resultBean.getMsg());
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                mLayoutManager.showNetError();
            }
        });
        /*HttpClient.post(ApiConstants.Urls.APPOINTMENT_GET_COURSE_LIST, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
//                Logcat.i("\n接口地址：" + "" + "\n提交的参数：" + str + "\n" + "返回码：" + statusCode + "\n" + "返回参数：" + content);
                ResultBean resultBean = JSON.parseObject(content, ResultBean.class);
                if (resultBean.getCode().equals("1")) {
                    List<UserCourseBean> list = JSON.parseArray(resultBean.getData(), UserCourseBean.class);
                    beanList.clear();
                    beanList.addAll(list);
                    HandleData(beanList);
                } else {
                    ToastUtil.showToast(resultBean.getMsg());
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                mLayoutManager.showNetError();
            }
        });*/
    }

    private void HandleData(List<UserCourseBean> list) {
        if (ListUtil.isEmpty(list)) {
            mLayoutManager.showEmpty();
        } else {
            mLayoutManager.showContent();
        }
        mStringList.clear();
        mListMap.clear();
        //将开始时间用list包装起来
        List<UserCourseBean> users = null;
        HashMap<String, List<UserCourseBean>> hashMap = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            UserCourseBean bean = list.get(i);
            String a = VipCardAdapter.getDateStringByTimeSTamp( bean.getTime(), "yyyy年MM月dd日 hh时");
            if (a != null){
                if (hashMap.keySet().size() == 0){
                    users = new ArrayList<>();
                    users.add(bean);
                    hashMap.put(a,users);
                    mStringList.add(a);
                }else{
                    String time = null;
                    for (String key:hashMap.keySet()){
                        Logcat.i("-----------------key-----" + key  + "-----a = " + a);
                        if (a.equals(key)){
                            time = key;
                        }
                    }
                    if (time != null){
                        List<UserCourseBean> datas = hashMap.get(time);
                        datas.add(bean);
                    }else{
                        users = new ArrayList<>();
                        mStringList.add(a);
                        users.add(bean);
                        hashMap.put(a,users);
                    }
                }
            }

            /*if (!time.equals(a)){
                users = new ArrayList<>();
                mStringList.add(a);
                time = a;
                users.add(bean);
                hashMap.put(time,users);
            }else{
                users.add(bean);
            }*/
        }
        mAdapter.setDatas(mStringList,hashMap);
        //mAdapter.notifyDataSetChanged();
        //相同时间的用一个list包装起来 time:list
       /* for (int i = 0; i < mStringList.size(); i++) {
            List<UserCourseBean> beanList1 = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                String b = VipCardAdapter.getDateStringByTimeSTamp(list.get(j).getStart(), "yyyy年MM月dd日 hh时");
                if (mStringList.get(i).equals(b)) {
                    beanList1.add(list.get(j));
                }
            }
            mListMap.put(mStringList.get(i), beanList1);
        }
        Map<String, List<UserCourseBean>> map = new TreeMap<>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        return obj1.compareTo(obj2);
                    }
                });
        map.putAll(mListMap);
        mListMap.clear();
        mListMap.putAll(map);
        if (mStringList.size() > 0) {
            Message msg = new Message();
            msg.what = STAY_NOT_1;
            msg.obj = new Bundle();
            handler.sendMessage(msg);
        }*/
    }
    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        if (postResult.getTag().equals("600")){
            beanList.clear();
            getCourse(status, Token);
            mRefreshLayout.setRefreshing(false);
        }
    }
}
