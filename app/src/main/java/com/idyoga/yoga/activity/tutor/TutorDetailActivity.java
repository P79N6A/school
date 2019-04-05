package com.idyoga.yoga.activity.tutor;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.home.AppointmentIntroductionActivity;
import com.idyoga.yoga.activity.home.SubjectListActivity;
import com.idyoga.yoga.activity.shop.ShopDetailActivity;
import com.idyoga.yoga.adapter.TutorRecommendCourseAdapter;
import com.idyoga.yoga.adapter.TutorVideoCourseAdapter;
import com.idyoga.yoga.adapter.VipCardAdapter;
import com.idyoga.yoga.adapter.base.BaseDelegateAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.lbs.LbsUtil;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.ShopDetailBean;
import com.idyoga.yoga.model.TeacherInfoBean;
import com.idyoga.yoga.model.TutorDetailBean;
import com.idyoga.yoga.utils.BannerGlideImageLoader;
import com.idyoga.yoga.utils.DateUtils;
import com.idyoga.yoga.view.YogaLayoutManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;
import vip.devkit.library.DensityUtil;
import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;
import vip.devkit.view.common.banner.BannerV;
import vip.devkit.view.common.banner.listener.OnBannerListener;
import vip.devkit.view.common.suklib.view.FlowLayout.FlowLayout;
import vip.devkit.view.common.suklib.view.FlowLayout.TagAdapter;
import vip.devkit.view.common.suklib.view.FlowLayout.TagFlowLayout;

/**
 * 文 件 名: TutorDetailActivity
 * 创 建 人: By k
 * 创建日期: 2018/5/14 15:49
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class TutorDetailActivity extends BaseActivity {
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
    @BindView(R.id.srl_Refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.tv_call)
    TextView mTvCall;
    @BindView(R.id.rl_root)
    RelativeLayout mRoot;
    /*@BindView(R.id.rl_layout)
    RelativeLayout mRlLayout;*/
    String shopId, tutorId;
//    private TutorDetailBean mTutorDetailBean;
    private TeacherInfoBean mTutorDetailBean;
    private List<DelegateAdapter.Adapter> mAdapters;
    private DelegateAdapter delegateAdapter;
    private VirtualLayoutManager layoutManager;
    private BaseDelegateAdapter mTutorDetailAdapter, mVideoAdapter, mShopAdapter;
    private List<TutorDetailBean.VideoBean> mVideoBeanList = new ArrayList<>();
    private List<String> mStringList = new ArrayList<>();
    private Map<String, List<TutorDetailBean.CourseBean>> mTabListMap;
    private int mPage = 1;
    private List<TeacherInfoBean.TeacherIma> mTutorImage;
    private List<TeacherInfoBean.TeacherCourse> mTutorCourse = new ArrayList<>();
    private TeacherInfoBean.TutorDetailBean mTutorDetail;

    @Override
    protected void initData() {
        mTabListMap = new TreeMap<>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        return obj1.compareTo(obj2);
                    }
                });
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            shopId = bundle.getString("shopId");
            tutorId = bundle.getString("tutorId");
            getData(shopId, tutorId);
        }
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).init();
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
//        return YogaLayoutManager.wrap(mRlLayout);
        return YogaLayoutManager.wrap(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_tutor_detail;
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("导师");
        mLayoutManager.showLoading();
        mLlCommonLayout.setBackgroundColor(getResources().getColor(R.color.title_alpha));
        initVLayout();
    }

    /**
     * 初始view
     */
    private void initVLayout() {
        mAdapters = new LinkedList<>();
        layoutManager = new VirtualLayoutManager(this);
        mRvList.setLayoutManager(layoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRvList.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(1, 5);
        viewPool.setMaxRecycledViews(2, 20);
        viewPool.setMaxRecycledViews(3, 20);
        viewPool.setMaxRecycledViews(4, 20);
        viewPool.setMaxRecycledViews(5, 20);
        delegateAdapter = new DelegateAdapter(layoutManager, true);
        mRvList.setAdapter(delegateAdapter);
        initAllTypeView();
        delegateAdapter.setAdapters(mAdapters);
        mRvList.setAdapter(delegateAdapter);
    }

    private void initAllTypeView() {
        initTutorDetailView();
        initRecommendCourseView();
        initVideoCourseView();
        initShopInfo();
    }

    /**
     * banner
     */
    private void initTutorDetailView() {
        mTutorDetailAdapter = new BaseDelegateAdapter(this, new LinearLayoutHelper(), R.layout.layout_tutor_detail, 0, 1) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (mTutorImage != null && mTutorImage.size() > 0){
                    ArrayList<String> images = new ArrayList<>();
                    for (int i = 0; i < mTutorImage.size(); i++) {
                        images.add(mTutorImage.get(i).getImage_url());
                    }
                    BannerV mImages = holder.getView(R.id.vp);
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mImages.getLayoutParams();
                    layoutParams.height = DensityUtil.dp2px(mContext, 220);
                    mImages.setLayoutParams(layoutParams);
                    mImages.setBannerStyle(1);
                    mImages.setImageLoader(new BannerGlideImageLoader());
                    mImages.setImages(images);
                    mImages.isAutoPlay(true);
                    mImages.setDelayTime(3000);
                    mImages.start();
                    mImages.setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            //                Bundle bundle = new Bundle();
                            //                bundle.putString("getUrl", beans.get(position).getImage_url());
                            //startActivityWithExtras(YogaWebActivity.class, bundle);
                        }
                    });
                }
                /*if (mTutorDetailBean != null) {

                    if (StringUtil.isEmpty(mTutorDetailBean.getIntroduce())) {
                        holder.setText(R.id.tv_introduce, "导师背景：暂无详细介绍");
                    } else {
                        holder.setText(R.id.tv_introduce, "导师背景：" + mTutorDetailBean.getIntroduce() + "");
                    }
                    StringBuffer lesson = new StringBuffer();
                    StringBuffer type = new StringBuffer();
                    if (ListUtil.isEmpty(mTutorDetailBean.getLesson())) {
                        lesson.append("暂未填写");
                    } else {
                        for (TutorDetailBean.LessonBean b : mTutorDetailBean.getLesson()) {
                            lesson.append(b.getLessonName()).append(" ");
                        }
                    }
                    if (ListUtil.isEmpty(mTutorDetailBean.getMainstream())) {
                        type.append("暂未填写");
                    } else {
                        for (TutorDetailBean.MainstreamBean b : mTutorDetailBean.getMainstream()) {
                            type.append(b.getMainstreamName()).append(" ");
                        }
                    }
                    Glide.with(mContext).load(mTutorDetailBean.getImage()).placeholder(R.drawable.img_course).error(R.drawable.img_course).into((ImageView) holder.getView(R.id.iv_img));
                    holder.setText(R.id.tv_tutor_name, mTutorDetailBean.getName())
                            .setText(R.id.tv_type, "所属流派：" + type)
                            .setText(R.id.tv_excellent, "擅长领域：" + lesson);
                    TagFlowLayout flowLayout = holder.getView(R.id.tag_view);
                    if (ListUtil.isEmpty(mTutorDetailBean.getLabelList())) {
                        flowLayout.setVisibility(View.GONE);
                    } else {
                        flowLayout.setVisibility(View.VISIBLE);
                    }
                    flowLayout.setAdapter(new TagAdapter<TutorDetailBean.LabelListBean>(mTutorDetailBean.getLabelList()) {
                        @Override
                        public View getView(FlowLayout parent, int position, TutorDetailBean.LabelListBean labelListBean) {
                            LayoutInflater inflater = LayoutInflater.from(mContext);
                            TextView tv = (TextView) inflater.inflate(R.layout.option_item, parent, false);
                            tv.setText(labelListBean.getName());
                            tv.setTextSize(10f);
                            tv.setTextColor(Color.parseColor("#b86caf"));
                            tv.setBackgroundResource(R.drawable.bg_shop_01);
                            return tv;
                        }
                    });

                }*/
            }
        };
        mAdapters.add(mTutorDetailAdapter);
    }

    /**
     * 已排期的权益课   线下课
     *
     * 擅长课程,证书,经历,个人介绍
     */
    BaseDelegateAdapter mRecommendAdapter;
    private void initRecommendCourseView() {
//        mRecommendAdapter = new TutorRecommendCourseAdapter(this, new LinearLayoutHelper(), R.layout.item_recommend_course, 0, 2){
        mRecommendAdapter = new BaseDelegateAdapter(this, new LinearLayoutHelper(), R.layout.item_recommend_course, 0, 2){
            @Override
            public void onBindViewHolder(BaseViewHolder helper, int position) {
                super.onBindViewHolder(helper, position);
                if (mTutorDetail != null){ //iv_teacher_head
                    Glide.with(mContext)
                            .load(mTutorDetail.getImage())
                            .placeholder(R.drawable.img_course)
                            .error(R.drawable.img_course)
                            .into((ImageView) helper.getView(R.id.iv_teacher_head));
                    helper.setText(R.id.tv_name,mTutorDetail.getName() != null ? mTutorDetail.getName() : "");
                    helper.setText(R.id.tv_experience,mTutorDetail.getWorking_years() != null ? mTutorDetail.getWorking_years() : "");
                    View view = helper.getView(R.id.tv_speciality_course);
                    view.setVisibility(View.GONE);
                    //helper.setText(R.id.tv_speciality_course,)
                    helper.setText(R.id.tv_certificate,mTutorDetail.getExperience() != null ? mTutorDetail.getExperience() : "");
                    helper.setText(R.id.wv,mTutorDetail.getIntroduce() != null ? mTutorDetail.getIntroduce() : "");
                }
            }
        };
        mAdapters.add(mRecommendAdapter);
    }

    /**
     * 视频课程
     *
     * 导师近期课程
     */
    private void initVideoCourseView() {
        mVideoAdapter = new BaseDelegateAdapter(this, new LinearLayoutHelper(), R.layout.item_layout_rv_list, 0, 3) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, final int position) {
                super.onBindViewHolder(holder, position);
                final TeacherInfoBean.TeacherCourse teacherCourse = mTutorCourse.get(position);
                if (position != 0){
                    holder.getView(R.id.rl_title).setVisibility(View.GONE);
                }else{
                    holder.getView(R.id.rl_title).setVisibility(View.VISIBLE);
                }
                Glide.with(mContext)
                        .load(mTutorDetail.getImage())
                        .placeholder(R.drawable.img_course)
                        .error(R.drawable.img_course)
                        .into((ImageView) holder.getView(R.id.iv_course_image));
                holder.setText(R.id.tv_course_name,teacherCourse.getName() != null ? teacherCourse.getName() : "");
                long start_time = teacherCourse.getStart_time();
                String timeslash = DateUtils.timeslash(String.valueOf(start_time));
                holder.setText(R.id.tv_tiem,timeslash);
                holder.setText(R.id.tv_people_num,teacherCourse.getNumber() + "");
                holder.setText(R.id.tv_order_num,teacherCourse.getAppointmentNum() + "");
                holder.getView(R.id.cl_content).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int course_id = teacherCourse.getCourse_id();
                        Bundle bundle = new Bundle();
                        bundle.putString("lessonId",String.valueOf(course_id));
                        bundle.putString("shopId",shopId);
                        startActivity(AppointmentIntroductionActivity.class,bundle);
                    }
                });
                /*holder.setText(R.id.tv_head_name, "推荐视频课程").setText(R.id.tv_all, "查看全部课程");
                RecyclerView mRvView = holder.getView(R.id.rv_list);
                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mRvView.setLayoutManager(layoutManager);
                TutorVideoCourseAdapter adapter = new TutorVideoCourseAdapter(
                        R.layout.item_video_course_list, mVideoBeanList);
                mRvView.setAdapter(adapter);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putString("videoId", null + "");
                        startActivity(SubjectListActivity.class, bundle);
                    }
                });
                holder.getView(R.id.tv_all).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString("shopId", shopId + "");
                        bundle.putString("tutorId", mVideoBeanList.get(position).getId() + "");
                        startActivity(TutorVideoCourseActivity.class, bundle);
                    }
                });*/
            }
        };
        mAdapters.add(mVideoAdapter);
    }

    /**
     *  到底了
     */
    private void initShopInfo() {
        mShopAdapter = new BaseDelegateAdapter(this, new LinearLayoutHelper(), R.layout.item_tutor_shop, 0, 4) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, final int position) {
                super.onBindViewHolder(holder, position);
                /*if (mTutorDetailBean != null && mTutorDetailBean.getShop() != null) {
                    Logcat.i("mTutorDetailBean：" + mTutorDetailBean.getShop().toString());
                    String longitude = (String) SharedPreferencesUtils.getSP(mContext, "longitude", "");
                    String latitude = (String) SharedPreferencesUtils.getSP(mContext, "latitude", "");
                    Logcat.i("经纬度：" + longitude + "/" + latitude);
                    String distances = LbsUtil.getDistance(
                            Double.valueOf(longitude),
                            Double.valueOf(latitude),
                            Double.valueOf(mTutorDetailBean.getShop().getLongitude()),
                            Double.valueOf(mTutorDetailBean.getShop().getLatitude()));
                    holder.setText(R.id.tv_head_name, "所属瑜伽馆")
                            .setText(R.id.tv_all, "")
                            .setText(R.id.tv_shop_name, mTutorDetailBean.getShop().getName())
                            .setText(R.id.tv_comment_num, mTutorDetailBean.getShop().getShopCommentCount() + "条评价")
                            .setText(R.id.tv_address, mTutorDetailBean.getShop().getAddress())
                            .setText(R.id.tv_distance, distances)
                            .getView(R.id.rl_itemView)
                            .setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("shopId", mTutorDetailBean.getShop().getId() + "");
                                    bundle.putString("name", mTutorDetailBean.getShop().getName() + "");
                                    bundle.putString("getUrl", "");
                                    startActivity(ShopDetailActivity.class, bundle);
                                }
                            });
                    Glide.with(mContext).load(mTutorDetailBean.getShop().getLogopath())
                            .placeholder(R.drawable.img_course).error(R.drawable.img_course)
                            .into((ImageView) holder.getView(R.id.iv_img));
                }*/
            }
        };
        mAdapters.add(mShopAdapter);
    }


    @Override
    protected void setListener() {
        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                empty();
                getData(shopId, tutorId);
                mSrlRefresh.setRefreshing(false);
            }
        });
    }

    @OnClick({R.id.ll_title_back, R.id.tv_call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.tv_call: //TODO 联系客服

                break;
        }
    }

    public void empty() {
        /*mVideoBeanList.clear();
        mStringList.clear();
        mTabListMap.clear();*/
        mTutorCourse.clear();
        mTutorImage.clear();
        /*if (mShopAdapter != null) {
            mShopAdapter.notifyDataSetChanged();
        }
        if (mVideoAdapter != null) {
            mVideoAdapter.notifyDataSetChanged();
        }
        if (mTutorDetailAdapter != null) {
            mTutorDetailAdapter.notifyDataSetChanged();
        }
        if (mRecommendAdapter != null) {
            mRecommendAdapter.notifyDataSetChanged();
        }*/
    }

    private void initViewData(TeacherInfoBean infoBean) {
        this.mTutorDetailBean = infoBean;
        mTutorCourse.clear();
        mTutorDetail = infoBean.getTutorDetail();
        mTutorImage = infoBean.getTutorImage();
        mTutorCourse = infoBean.getTutorCourse();

        mTvTitleText.setText(mTutorDetail.getName());
        initRecommendCourseDate();
        if (!ListUtil.isEmpty(mTutorCourse)) {
            mVideoAdapter.setCount(mTutorCourse.size());
        }
        mShopAdapter.setCount(1);
        mTutorDetailAdapter.setCount(1);
        mRecommendAdapter.setCount(1);
        mShopAdapter.notifyDataSetChanged();
        mVideoAdapter.notifyDataSetChanged();
        mTutorDetailAdapter.notifyDataSetChanged();
        mRecommendAdapter.notifyDataSetChanged();
        mLayoutManager.showContent();
    }

    private void initRecommendCourseDate() {
        List<TutorDetailBean.CourseBean> listBeans = new ArrayList();
//        listBeans.addAll(mTutorDetailBean.getCourse());
        for (int i = 0; i < listBeans.size(); i++) {
            String a = VipCardAdapter.getDateStringByTimeSTamp((long) listBeans.get(i).getStart(), "MM月dd日");
            if (!mStringList.contains(a)) {
                mStringList.add(a);
            }
        }
//        for (int i = 0; i < mStringList.size(); i++) {
//            List<TutorDetailBean.CourseBean> beanList1 = new ArrayList<>();
//            for (int j = 0; j < mTutorDetailBean.getCourse().size(); j++) {
//                String b = VipCardAdapter.getDateStringByTimeSTamp((long) mTutorDetailBean.getCourse().get(j).getStart(), "MM月dd日");
//                if (mStringList.get(i).equals(b)) {
//                    beanList1.add(mTutorDetailBean.getCourse().get(j));
//                }
//            }
//            mTabListMap.put(mStringList.get(i), beanList1);
//        }
        for (int i = 0; i < mStringList.size(); i++) {
            List<TutorDetailBean.CourseBean> beanList1 = new ArrayList<>();
            for (int j = 0; j < listBeans.size(); j++) {
                String b = VipCardAdapter.getDateStringByTimeSTamp((long) listBeans.get(j).getStart(), "MM月dd日");
                if (mStringList.get(i).equals(b)) {
//                    beanList1.add(mTutorDetailBean.getCourse().get(j));
                }
            }
            mTabListMap.put(mStringList.get(i), beanList1);
        }
        for (String key : mTabListMap.keySet()) {
            Logcat.e("Map ：" + key);
        }
//        mRecommendAdapter.setTabListMap(mTabListMap, mTutorDetailBean.getShop().getId() + "");
    }

    private void getData(String shopId, String tutorId) {
        Map map = new HashMap();
        map.put("shopId", shopId);
        map.put("tutorId", tutorId);
        map.put("page", mPage + "");
        map.put("size", "5");
        HttpClient.post(ApiConstants.Urls.TEACH_INFO_DETAIL, map, new HttpResponseHandler() {
            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                mLayoutManager.showNetError();
            }
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                ResultBean bean = JSON.parseObject(content, ResultBean.class);
                if (bean.getCode().equals("1")) {
                    mTutorDetailBean = JSON.parseObject(bean.getData(), TeacherInfoBean.class);
                    initViewData(mTutorDetailBean);
                }
            }
        });
        /*HttpClient.post(ApiConstants.Urls.TUTOR_DETAIL, map, new HttpResponseHandler() {
            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                mLayoutManager.showNetError();
            }
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                ResultBean bean = JSON.parseObject(content, ResultBean.class);
                if (bean.getCode().equals("1")) {
                    mTutorDetailBean = JSON.parseObject(bean.getData(), TutorDetailBean.class);
                    initViewData(mTutorDetailBean);
                }
            }
        });*/
    }
}
