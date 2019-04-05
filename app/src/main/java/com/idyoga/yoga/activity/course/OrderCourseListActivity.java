package com.idyoga.yoga.activity.course;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.home.AppointmentIntroductionActivity;
import com.idyoga.yoga.adapter.CalendarAdapter;
import com.idyoga.yoga.adapter.FollowAdater;
import com.idyoga.yoga.adapter.OrderCourseListAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.listener.OnVerticalScrollListener;
import com.idyoga.yoga.model.BaseCourseBean;
import com.idyoga.yoga.model.CourseListData;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.SomeCourseBean;
import com.idyoga.yoga.model.State;
import com.idyoga.yoga.utils.DateUtils;
import com.idyoga.yoga.utils.JsonUtil;
import com.idyoga.yoga.utils.ToastUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import okhttp3.Request;
import vip.devkit.library.Logcat;

/**
 * @author weilgu
 * @time 2019/3/15 14:21
 * @des 预约课程列表
 */

public class OrderCourseListActivity extends BaseActivity {

    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_time)
    TextView mTitle;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.ll_left)
    LinearLayout mLlLeft;
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.rlv_date)
    RecyclerView mRlvDate;
    private String mAction; //0 为瑜伽馆课程表 1,某种课程的课程表
    private String mShopId;
    private String mLessonId;
    private int mPage = 1;
    private String time = "";
    private CalendarAdapter mAdapter1;
    private final List<State> DATAS = new ArrayList<>();
    private List<State> mDatas = new ArrayList<>();
    private String mYear;
    private String mMonth;
    private static int sDataNum ;
    private String mDay;
    private OrderCourseListAdapter mAdapter;
    private List<BaseCourseBean> mCourseBeans = new ArrayList<>();
    private String mTimeMillis;
    private boolean isLoadMore = false;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).init();
    }

    @Override
    protected void initData() {
        time = DateUtils.getTodayDateYmd();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAction = extras.getString("action");
            mShopId = extras.getString("shopId");
            mLessonId = extras.getString("lessonId");
        }
        mTimeMillis = String.valueOf(System.currentTimeMillis());
        getCourseDatas();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_order_course_list_layout;
    }

    @Override
    protected void initView() {
        if (mAction.equals("0")){
            mTvTitleText.setText("课程表");
        } else if(mAction.equals("1")){

        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRlv.setLayoutManager(layoutManager);
        mAdapter = new OrderCourseListAdapter(this,mCourseBeans,R.layout.item_order_course_list_layout);
        /*if (mAction != null && "0".equals(mAction)) {
            View loadView = View.inflate(this, R.layout.view_loading_footer, null);//View.inflate(this,R.layout.view_loading_footer,null)
            mAdapter.addFooterView(loadView);
        }*/
        mRlv.setAdapter(mAdapter);

        LinearLayoutManager dataLayoutManager = new LinearLayoutManager(this);
        dataLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mAdapter1 = new CalendarAdapter(this);
        mRlvDate.setLayoutManager(dataLayoutManager);
        mRlvDate.setAdapter(mAdapter1);
    }

    @Override
    protected void setListener() {
        mAdapter1.setOnCalendarClickListener(new CalendarAdapter.OnCalendarClickListener() {
            @Override
            public void onClick(int position, String data) {
                if (position < 9){
                    mDay = "0" + (position + 1);
                }else{
                    mDay = String.valueOf(position + 1);
                }
                time = mYear + mMonth + mDay;
                mTimeMillis = DateUtils.datasss(time);
                getCourseDatas();
                Logcat.i("--------------- ymd = " + mYear + mMonth + mDay);
            }
        });
        if (mAction != null && "0".equals(mAction) && mCourseBeans.size() >= 10) {
            mRlv.addOnScrollListener(new OnVerticalScrollListener() {
                @Override
                public void onScrolledToBottom() {
                    super.onScrolledToBottom();
                    mPage += 1;
                    isLoadMore = true;
                    getShopCourseList();
                }
            });
        }
    }

    private void getCourseDatas() {
        if (mAction != null && "0".equals(mAction)) {
            getShopCourseList();
        }else if (mAction != null && "1".equals(mAction)){
            getSomeCourseDatas();
        }
    }

    int i = 1;
    //获取瑜伽馆 某节课的课程表
    private void getSomeCourseDatas() {
        Logcat.i("--------------------------getSomeCourseDatas---" + i + " shopId = " + mShopId + " lessonId = " + mLessonId + " time = " + time);
        i += 1;
        showLoading("加载中...", true);
        Map map = new HashMap();
        map.put("shopId", mShopId + "");
        map.put("lessonId", mLessonId + "");
        map.put("time", mTimeMillis);
        HttpClient.post(ApiConstants.Urls.COLLEGE_SETCOURSE_LESSON_LIST, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                dismissLoading();
                Logcat.e("预约结果：" + content);
                ResultBean bean = JSON.parseObject(content, ResultBean.class);//if (content.contains("<!DOCTYPE html>")) return;
                if (bean.getCode().equals("1")) {
                    List<SomeCourseBean> someCourseBeans = JSON.parseArray(bean.getData(), SomeCourseBean.class);
                    if (someCourseBeans != null){
                        mCourseBeans.clear();
                        mCourseBeans.addAll(someCourseBeans);
                        for (int i = 0; i < someCourseBeans.size(); i++) {
                            SomeCourseBean courseBean = someCourseBeans.get(i);
                            courseBean.setBeanType(1);
                        }
                    }
                    bindSomeCourse(someCourseBeans);
                } else {
                    ToastUtil.showToast(bean.getMsg());
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                dismissLoading();
            }
        });
    }

    private void bindSomeCourse(List<SomeCourseBean> someCourseBeans) {
        if (mAction.equals("1") && someCourseBeans != null && someCourseBeans.size() > 0){
            SomeCourseBean listData = someCourseBeans.get(0);
            mTvTitleText.setText(listData.getLesson_name());
        }
        if (mAdapter != null){
//            mAdapter.setDatas(mCourseBeans);
            mAdapter.notifyDataSetChanged();
        }
    }

    //获取瑜伽馆 课程表
    private void getShopCourseList() {
        Map map = new HashMap();
        map.put("shopId", mShopId + "");
        map.put("page", mPage + "");
        map.put("size", "10");
        map.put("time", time + "");
        showLoading("正在获取数据");
        Logcat.i("提交的参数：" + map.toString());
        HttpClient.post(ApiConstants.Urls.SHOP_COURSR_LIST_DATA, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                dismissLoading();
                Logcat.i("Bean:" + content);
                if (content.contains("<!DOCTYPE html>")) return;
                ResultBean bean = JSON.parseObject(content, ResultBean.class);
                if (bean.getCode().equals("1")) {
                    List<CourseListData> datas = JSON.parseArray(bean.getData(), CourseListData.class);
                    if (datas != null){
                        //mCourseBeans.clear();
                        mCourseBeans.addAll(datas);
                        for (int i = 0; i < datas.size(); i++) {
                            datas.get(i).setBeanType(0);
                        }
                    }
                    bindView(datas);
                } else {
                    ToastUtil.showToast(bean.getMsg());
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                dismissLoading();
            }
        });
    }

    private void bindView(List<CourseListData> datas) {
//        mCourseBeans.addAll(datas);
        mAdapter.removeAllFooterView();
        View view = null;
        if (mCourseBeans.size() == 10) {
            view = View.inflate(this, R.layout.view_loading_footer, null);
        } else if (mCourseBeans.size() < 10) {
            isLoadMore = false;
            if (mCourseBeans.size() < 2) {
                mRlv.setBackgroundColor(Color.parseColor("#f8f8f8"));
            }
        }
        if (view != null) {
            mAdapter.addFooterView(view);
        }
        if (mAdapter != null){
            if (isLoadMore){
                mAdapter.setNewData(mCourseBeans);
            }else{
                //mAdapter.setDatas(mCourseBeans);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @OnClick({R.id.ll_left, R.id.iv_right,R.id.ll_title_back})
    public void onViewClicked(View view) {
        String timeStr = mTitle.getText().toString();
        String sub = timeStr.substring(0, 4);
        switch (view.getId()) {
            case R.id.ll_left:
                Integer s = Integer.valueOf(mMonth);
                s -= 1;
                if (s < 1){
                    s = 12;
                    if (Integer.valueOf(sub) - 1 >= 1970){
                        mYear = String.valueOf(Integer.valueOf(sub) - 1 );
                    }else{
                        return;
                    }
                }
                mMonth = String.valueOf(s);
                sDataNum = getDataNum(String.valueOf(s), mYear);
                mDatas.clear();
                for (int i = 0; i < sDataNum; i++) {
                    mDatas.add(DATAS.get(i));
                }
                mTitle.setText(mYear + " 年 " + s + " 月");
                mAdapter1.setDataNum(mDatas);
                break;
            case R.id.iv_right:
                Integer ss = Integer.valueOf(mMonth);
                ss += 1;
                if (ss > 12){
                    ss = 1;
                    if (Integer.valueOf(sub) + 1 <= 2119){
                        mYear = String.valueOf(Integer.valueOf(sub) + 1);
                    }else{
                        return;
                    }
                }
                mMonth = String.valueOf(ss);
                sDataNum = getDataNum(String.valueOf(ss), mYear);
                mDatas.clear();
                for (int i = 0; i < sDataNum; i++) {
                    mDatas.add(DATAS.get(i));
                }
                mTitle.setText(mYear + " 年 " + ss + " 月");
                mAdapter1.setDataNum(mDatas);
                break;
            case R.id.ll_title_back:
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initCalenderData();
    }

    public void initCalenderData() {
        for (int i = 0; i < 31; i++) {
            DATAS.add(new State());
        }
        long timeMillis = System.currentTimeMillis();
        String time = DateUtils.time(String.valueOf(timeMillis / 1000));
        String[] split1 = time.split(" ");
        Logcat.i("---------------split1-----------" + split1);
        if (split1 != null && split1.length >= 2 ){
            String[] split = split1[0].split("-");
            mYear = split[0]; //年
            mMonth = split[1]; //月
            mDay = split[2];//日
            mTitle.setText(mYear + " 年 " + mMonth + " 月");
            sDataNum = getDataNum(mMonth, mYear);
            for (int i = 0; i < sDataNum; i++) {
                State state = DATAS.get(i);
                state.setData(i);
                if ((i + 1 ) == Integer.valueOf(mDay)){
                    state.setClick(true);
                }
                mDatas.add(state);
            }
            mAdapter1.setDataNum(mDatas);
            mRlvDate.scrollToPosition(Integer.valueOf(mDay) - 1);
        }
    }

    private int getDataNum(String s, String year) {
        int data = 0;
        switch (Integer.valueOf(s)){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return data = 31;
            case 2:
                if (Integer.valueOf(year) % 400 == 0 ){
                    return data = 29;
                }else if(Integer.valueOf(year) % 100 != 0 && Integer.valueOf(year) % 4 == 0){
                    return data = 29;
                }else{
                    return data = 28;
                }
            case 4:
            case 6:
            case 9:
            case 11:
                return data = 30;
        }
        return 0;
    }

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        if (postResult.getTag().equals("selectCourse")){
            SomeCourseBean data = (SomeCourseBean) postResult.getResult();
            PostResult courseTime = new PostResult("selectCourseTime",data);
            EventBus.getDefault().post(courseTime);
            finish();
        } else if(postResult.getTag().equals("2CourseDetail")){
            Bundle bundle = (Bundle) postResult.getResult();
            bundle.putString("shopId",mShopId);
            startActivity(AppointmentIntroductionActivity.class,bundle);
        }
    }
}
