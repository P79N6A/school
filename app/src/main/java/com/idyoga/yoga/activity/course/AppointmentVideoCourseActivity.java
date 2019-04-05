package com.idyoga.yoga.activity.course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.MainActivity;
import com.idyoga.yoga.activity.home.SubjectListActivity;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.presenter.ICommonRequestPresenter;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.UserAppointmentVideoCourseBean;
import com.idyoga.yoga.model.UserInfoBean;
import com.idyoga.yoga.utils.DateUtils;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.UserUtil;
import com.idyoga.yoga.view.YogaLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import vip.devkit.library.Logcat;
import vip.devkit.library.StringUtil;

/**
 * 文 件 名: AppointmentVideoCourseActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/11
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class AppointmentVideoCourseActivity extends BaseActivity implements ICommonViewUi {
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
    private UserInfoBean mUserInfoBean;
    private List<UserAppointmentVideoCourseBean> mBeanList = new ArrayList<>();
    private CommonAdapter mAdapter;

    @Override
    protected void initData() {
        mUserInfoBean = UserUtil.getUserBean(this);
        toRequest(ApiConstants.EventTags.USER_APPOINTMENT_VIDEO_COURSE_LIST);
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
        return R.layout.activity_appointment_video_course;
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("订阅的视频课程");
        mLayoutManager.setEmpty(R.layout.layout_conslut_course_empty);
        mLayoutManager.initView(YogaLayoutManager.EMPTY)
                .setText(R.id.tv_content,"没有订阅视频课程，快去订阅吧")
                .setText(R.id.tv_go,"立即订阅")
                .setVisible(R.id.iv_img,View.VISIBLE);
        mLayoutManager.showLoading();
        mLvList.setAdapter(mAdapter = new CommonAdapter<UserAppointmentVideoCourseBean>(this, mBeanList, R.layout.item_video_course_list) {
            @Override
            public void convert(ViewHolder holder, UserAppointmentVideoCourseBean bean, int position) {
                Glide.with(mContext)
                        .load(bean.getImage_url()).placeholder(R.drawable.img_course).error(R.drawable.img_course)
                        .into((ImageView) holder.getView(R.id.iv_img));
                String tutor = "";
                if (!StringUtil.isEmpty(bean.getTutor_name())) {
                    tutor = bean.getTutor_name();
                }
                holder.setText(R.id.tv_course_name, bean.getTitle())
                        .setText(R.id.tv_course_tutor, "导师：" + tutor)
                        .setText(R.id.tv_course_time, "时长：" + DateUtils.secToTime(bean.getTime()) + "");
            }
        });
    }

    @Override
    protected void setListener() {
        mLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Logcat.e("mBeanList:" + mBeanList.get(i).getTitle());
                Bundle bundle = new Bundle();
                bundle.putString("videoId", mBeanList.get(i).getId() + "");
                Intent intent = new Intent(AppointmentVideoCourseActivity.this,SubjectListActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,800);
            }
        });
        mLayoutManager.setOnInflateListener(new YogaLayoutManager.OnInflateListener() {
            @Override
            public void onInflate(int viewType, View view) {
                switch (view.getId()) {
                    case R.id.tv_go:
                        Bundle bundle = new Bundle();
//                        bundle.putString("fTag","f1");
                        EventBus.getDefault().post(new PostResult("toMain","1"));
                        startActivity(MainActivity.class,bundle);
                        finish();
                        break;
                }
            }
        });
    }


    private void initViewData(List<UserAppointmentVideoCourseBean> beanList) {
        mBeanList.clear();
        if (beanList.size() == 0) {
            mLayoutManager.showEmpty();
        } else {
            mBeanList.addAll(beanList);
            mLayoutManager.showContent();
            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void toRequest(int eventTag) {
        Map map = new HashMap();
        if (eventTag == ApiConstants.EventTags.USER_APPOINTMENT_VIDEO_COURSE_LIST) {
            map.put("token", mUserInfoBean.getToken() + "");
            Logcat.e("订阅的视频课程："+map.toString());
            iCommonRequestPresenter.request(eventTag, this, ApiConstants.Urls.USER_APPOINTMENT_VIDEO_COURSE_LIST, map);
        }
    }

    @Override
    public void getRequestData(int eventTag, String result) {
        Logcat.e("订阅的视频课程："+eventTag+"/"+result);
        ResultBean bean = JSON.parseObject(result, ResultBean.class);
        if (bean.getCode().equals("1") && bean.getData() != null || !bean.getData().equals("[]")) {
            if (eventTag == ApiConstants.EventTags.USER_APPOINTMENT_VIDEO_COURSE_LIST) {
                List<UserAppointmentVideoCourseBean> beanList = JSON.parseArray(bean.getData(), UserAppointmentVideoCourseBean.class);
                initViewData(beanList);
            }
        } else {
            mLayoutManager.showError();
            ToastUtil.showToast(bean.getMsg());
        }
    }


    @Override
    public void onRequestFailureException(int eventTag, String msg) {
        mLayoutManager.showNetError();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==800){
            toRequest(ApiConstants.EventTags.USER_APPOINTMENT_VIDEO_COURSE_LIST);
        }
    }
}
