package com.idyoga.yoga.activity.shop;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
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
import com.idyoga.yoga.activity.course.OrderCourseListActivity;
import com.idyoga.yoga.activity.home.AppointmentIntroductionActivity;
import com.idyoga.yoga.activity.home.SubjectListActivity;
import com.idyoga.yoga.activity.lbs.LbsActivity;
import com.idyoga.yoga.activity.tutor.TutorDetailActivity;
import com.idyoga.yoga.activity.user.LoginActivity;
import com.idyoga.yoga.adapter.ShopChildShopAdapter;
import com.idyoga.yoga.adapter.ShopDetailAdapter;
import com.idyoga.yoga.adapter.ShopHotCourseAdapter;
import com.idyoga.yoga.adapter.ShopImageAdapter;
import com.idyoga.yoga.adapter.ShopRecommendCourseAdapter;
import com.idyoga.yoga.adapter.ShopTutorAdapter;
import com.idyoga.yoga.adapter.ShopVideoCourseAdapter;
import com.idyoga.yoga.adapter.VipCardAdapter;
import com.idyoga.yoga.adapter.base.BaseDelegateAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.Constants;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.listener.OnItemClickListener;
import com.idyoga.yoga.model.LessonListBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.ShopDetailBean;
import com.idyoga.yoga.model.ShopDetailDataBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.LoginUtil;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.ViewUtil;
import com.idyoga.yoga.utils.YogaViewUtil;
import com.idyoga.yoga.view.MyGridItemDecoration;
import com.idyoga.yoga.view.YogaLayoutManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

/**
 * 文 件 名: ShopDetailActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/21
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ShopDetailActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.srl_Refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.rl_layout)
    RelativeLayout mRvLayout;
    @BindView(R.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R.id.ll_title_right)
    LinearLayout mLlTitleRight;
    @BindView(R.id.tv_call)
    TextView mTvCall;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.v)
    View mView;
    private List<DelegateAdapter.Adapter> mAdapters;
    private DelegateAdapter delegateAdapter;
    private BaseDelegateAdapter mShopDetailAdapter, mHotCourseAdapter, mTutorAdapter, mVideoAdapter, mChildAdapter;
    private VirtualLayoutManager layoutManager;
    private List<String> mBannerList = new ArrayList<>();
    private List<ShopDetailBean.TutorBean> mTutorBeanList = new ArrayList<>();
    private List<ShopDetailBean.VideoBean> mVideoBeanList = new ArrayList<>();
    private List<ShopDetailBean.ChainShopListBean> mShopListBeanList = new ArrayList<>();
    private List<ShopDetailBean.RecommendCourseListBean> mRecommendCourseBeanList = new ArrayList<>();
    private List<ShopDetailBean.LessonListBean> mLessonListBeans = new ArrayList<>();
    private List<String> mStringList = new ArrayList<>();
    private Map<String, List<ShopDetailBean.RecommendCourseListBean>> mTabListMap;
    private String shopId, shopName, cityId;
    private String lat, lng, time;
    //private ShopDetailBean detailBean;
    private ShopDetailDataBean detailBean;
    private List<Object> mDatas = new ArrayList<>();
    private ShopDetailAdapter mShopDetailAdapter1;
    private int mIsAttention;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).init();
    }

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
            shopId = bundle.getString("shopId");//"19287727";//29619723
            shopName = bundle.getString("shopName");
            lat = (String) SharedPreferencesUtils.getSP(mContext, "latitude", "");
            lng = (String) SharedPreferencesUtils.getSP(mContext, "longitude", "");
            cityId = (String) SharedPreferencesUtils.getSP(mContext, "cityId", "");
            time = "" + System.currentTimeMillis() / 1000;
            getData(shopId, lat, lng, time, cityId);
        }
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(mRvLayout);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_shop_detail;
    }

    @Override
    protected void initView() {
        mTvTitleText.setVisibility(View.INVISIBLE);
        if (StringUtil.isEmpty(shopName)) {
            mTvTitleText.setText("瑜伽馆详情");
        } else {
            mTvTitleText.setText(shopName);
        }
        ViewGroup.LayoutParams llLp = mLlTitleRight.getLayoutParams();
        llLp.width = ViewUtil.dp2px(getApplicationContext(), 87);
        ViewGroup.LayoutParams tvLp = mTvTitleRight.getLayoutParams();
        tvLp.width = ViewUtil.dp2px(getApplicationContext(), 75);
        tvLp.height = ViewUtil.dp2px(getApplicationContext(), 30);
        mLayoutManager.showLoading();
        initVLayout();
    }

    //布局头部的图片是否显示至状态栏
    private void cententIsShowToTile(boolean isShow) {
        if (!isShow){
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.addRule(RelativeLayout.BELOW,mView.getId());
        }
    }

    /**
     * 初始view
     */
    private void initVLayout() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvList.setLayoutManager(layoutManager);
        mShopDetailAdapter1 = new ShopDetailAdapter(this);
        mRvList.setAdapter(mShopDetailAdapter1);
    }

    /**
     * 瑜伽馆其他信息
     */
    private void initShopDetailView() {
        mShopDetailAdapter = new BaseDelegateAdapter(this, new LinearLayoutHelper(), R.layout.layout_shop_detail, 0, 1) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (detailBean != null) {
                    /*Logcat.i("d:" + detailBean.getShopInfo().toString());
                    if (StringUtil.isEmpty(detailBean.getShopInfo().getIntroduce())) {
                        holder.setText(R.id.tv_introduce, "暂无简介");
                    } else {
                        holder.setText(R.id.tv_introduce, detailBean.getShopInfo().getIntroduce() + "");

                    }
                    ShopImageAdapter shopImageAdapter = new ShopImageAdapter(mContext);
                    RecyclerView recyclerView = (RecyclerView) holder.getView(R.id.rlv_shop_image);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                    layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(shopImageAdapter);*/

                    /*Glide.with(mContext).load(detailBean.getShopInfo().getLogopath()).placeholder(R.drawable.img_course).error(R.drawable.img_course).into((ImageView) holder.getView(R.id.iv_img));
                    holder.setText(R.id.tv_shop_name, detailBean.getShopInfo().getName())
                            .setText(R.id.tv_shop_address, detailBean.getShopInfo().getAddress())
                            .setText(R.id.tv_nav, detailBean.getShopInfo().getCompare() + "km")
                            .getView(R.id.rl_address).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle bundle = new Bundle();
                            if (detailBean.getShopInfo() != null) {
                                bundle.putString("address", detailBean.getShopInfo().getAddress());
                                bundle.putString("latitude", detailBean.getShopInfo().getLatitude());
                                bundle.putString("longitude", detailBean.getShopInfo().getLongitude());
                                bundle.putString("shopName", detailBean.getShopInfo().getName());
                                bundle.putString("shopAddress", detailBean.getShopInfo().getAddress());
                                startActivity(LbsActivity.class, bundle);
                            }
                        }
                    });*/
                }
            }
        };
        mAdapters.add(mShopDetailAdapter);
    }

    /*private void initAllTypeView() {
        initShopDetailView();
        initTutorView();
        initHotCourseView();
        initRecommendCourseView();
        initVideoCourseView();
        initChildShopView();
    }*/

    /**
     * 导师
     */
    private void initTutorView() {
        mTutorAdapter = new BaseDelegateAdapter(this, new LinearLayoutHelper(), R.layout.item_layout_rv_list, 0, 2) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.setText(R.id.tv_head_name, "导师风采");
                RecyclerView mRvView = holder.getView(R.id.rv_list);
                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                mRvView.setLayoutManager(layoutManager);
                ShopTutorAdapter homeTutorAdapter = new ShopTutorAdapter(R.layout.item_home_tutor, mTutorBeanList);
                mRvView.setAdapter(homeTutorAdapter);
                homeTutorAdapter.notifyDataSetChanged();
                //老师详情
                /*homeTutorAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putString("getUrl", "http://wxyoga.hq-xl.com/tutor/detail?shopId=" + detailBean.getShopInfo().getId() + "&tutorId=" + mTutorBeanList.get(position).getId());
                        bundle.putString("shopId", detailBean.getShopInfo().getId() + "");
                        bundle.putString("tutorId", mTutorBeanList.get(position).getId() + "");
                        //                        startActivity(TutorDetailsActivity.class, bundle);
                        startActivity(TutorDetailActivity.class, bundle);
                    }
                });
                //馆的全部老师
                holder.getView(R.id.tv_all).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString("shopId", detailBean.getShopInfo().getId() + "");
                        startActivity(ShopTutorActivity.class, bundle);
                    }
                });*/
            }
        };
        mAdapters.add(mTutorAdapter);
    }

    /**
     * 热门权益课
     */
    private void initHotCourseView() {
        mHotCourseAdapter = new BaseDelegateAdapter(this, new LinearLayoutHelper(), R.layout.item_layout_rv_list, 0, 3) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, final int position) {
                super.onBindViewHolder(holder, position);
                holder.setText(R.id.tv_head_name, "推荐权益课程").setText(R.id.tv_all, "查看全部课程");
                RecyclerView mRvView = holder.getView(R.id.rv_list);
                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mRvView.setLayoutManager(layoutManager);
                MyGridItemDecoration decoration = new MyGridItemDecoration();
                decoration.setColor(Color.parseColor("#f2f2f2"));
                decoration.setSize(2);
                decoration.setPaddingV(YogaViewUtil.dp2px(mContext, 12), YogaViewUtil.dp2px(mContext, 12));
                mRvView.addItemDecoration(decoration);
                ShopHotCourseAdapter hotCourseAdapter = new ShopHotCourseAdapter(R.layout.item_shop_hot_course_layout, mLessonListBeans);
                mRvView.setAdapter(hotCourseAdapter);
                /*hotCourseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putString("shopId", detailBean.getShopInfo().getId() + "");
                        bundle.putString("courseId", detailBean.getLessonList().get(position).getId() + "");
                        startActivity(ShopCourseInfoActivity.class, bundle);
                    }
                });
                holder.getView(R.id.tv_all).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString("shopId", detailBean.getShopInfo().getId() + "");
                        startActivity(ShopMarketCourseActivity.class, bundle);
                    }
                });*/
            }
        };
        mAdapters.add(mHotCourseAdapter);
    }

    /**
     * 已排期的权益课
     */
    ShopRecommendCourseAdapter mRecommendAdapter;

    private void initRecommendCourseView() {
        mRecommendAdapter = new ShopRecommendCourseAdapter(this, new LinearLayoutHelper(), R.layout.item_recommend_course, 0, 4);
        mAdapters.add(mRecommendAdapter);
    }

    /**
     * 视频课程
     */
    private void initVideoCourseView() {
        mVideoAdapter = new BaseDelegateAdapter(this, new LinearLayoutHelper(), R.layout.item_layout_rv_list, 0, 5) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.setText(R.id.tv_head_name, "推荐视频课程").setText(R.id.tv_all, "查看全部课程");
                RecyclerView mRvView = holder.getView(R.id.rv_list);
                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mRvView.setLayoutManager(layoutManager);
                ShopVideoCourseAdapter adapter = new ShopVideoCourseAdapter(
                        R.layout.item_video_course_list, mVideoBeanList);
                mRvView.setAdapter(adapter);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putString("videoId", mVideoBeanList.get(position).getId() + "");
                        startActivity(SubjectListActivity.class, bundle);
                    }
                });
                holder.getView(R.id.tv_all).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        /*Bundle bundle = new Bundle();
                        bundle.putString("shopId", detailBean.getShopInfo().getId() + "");
                        startActivity(ShopVideoCourseActivity.class, bundle);*/
                    }
                });
            }
        };
        mAdapters.add(mVideoAdapter);
    }


    /**
     * 连锁瑜伽馆
     */
    private void initChildShopView() {
        mChildAdapter = new BaseDelegateAdapter(this, new LinearLayoutHelper(), R.layout.item_layout_rv_list, 0, 5) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.setText(R.id.tv_head_name, "连锁瑜伽馆");
                RecyclerView mRvView = holder.getView(R.id.rv_list);
                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                mRvView.setLayoutManager(layoutManager);
                ShopChildShopAdapter childShopAdapter = new ShopChildShopAdapter(R.layout.item_shop_child_shop, mShopListBeanList);
                mRvView.setAdapter(childShopAdapter);
                childShopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putString("shopId", mShopListBeanList.get(position).getId() + "");
                        bundle.putString("name", mShopListBeanList.get(position).getName() + "");
                        bundle.putString("getUrl", "");
                        startActivity(ShopDetailActivity.class, bundle);
                        finish();
                    }
                });
            }
        };
        mAdapters.add(mChildAdapter);
    }


    @Override
    protected void setListener() {
        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(shopId, lat, lng, time, cityId);
                mSrlRefresh.setRefreshing(false);
            }
        });
        /*mRecommendAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int type, View view, int position) {
                Logcat.i("选择的ID:" + view.getId() + "/" + position);
            }
        });*/
    }

    @OnClick({R.id.ll_title_back, R.id.tv_call,R.id.tv_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.tv_call:
                initPermission();
                callPhone(Constants.SERVICE_TELL);
                break;
            case R.id.tv_title_right:
                getFollowShop();
                break;
        }
    }

    /**
     * 请求CALL权限码
     */
    public static final int REQUEST_CALL_PERM = 101;

    @AfterPermissionGranted(REQUEST_CALL_PERM)
    private void callPhone(String mobile) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Intent phoneIntent = new Intent("android.intent.action.CALL",
                    Uri.parse("tel:" + mobile));
            startActivity(phoneIntent);
        } else {
            if (EasyPermissions.hasPermissions(this, Manifest.permission.CALL_PHONE)) {
                Intent phoneIntent = new Intent("android.intent.action.CALL",
                        Uri.parse("tel:" + mobile));
                startActivity(phoneIntent);
            }
        }
    }

    private static String[] permissions = new String[]{Manifest.permission.CALL_PHONE};

    void initPermission() {
        for (String permission : permissions) {
            int checkSelfPermission = ContextCompat.checkSelfPermission(this, permission);
            if (checkSelfPermission == PackageManager.PERMISSION_DENIED) {//未申请
                ActivityCompat.requestPermissions(this, permissions, 100);
                Logcat.i("initPermission:1");
            }
        }
        Logcat.i("initPermission:2");
    }


    void initEmpty() {
        detailBean = null;
        mLessonListBeans.clear();
        mTutorBeanList.clear();
        mRecommendCourseBeanList.clear();
        mVideoBeanList.clear();
        mBannerList.clear();
    }

//    private void initViewData(ShopDetailBean detailBean) {
    private void initViewData(ShopDetailDataBean detailBean) {
        initEmpty();
        this.detailBean = detailBean;
        mShopDetailAdapter1.setData(detailBean);
        mIsAttention = detailBean.getIsAttention();
        initTitleRightText(mIsAttention);
    }

    private void initTitleRightText(int isAttention) {
        if (isAttention == 0){ //没关注
            mTvTitleRight.setText("关注该馆");
            mTvTitleRight.setTextColor(getResources().getColor(R.color.white));
            mTvTitleRight.setBackground(getResources().getDrawable(R.drawable.shop_detail_title_right_bg));
        }else if(isAttention == 1){
            mTvTitleRight.setText("取消关注");
            mTvTitleRight.setTextColor(getResources().getColor(R.color.white));
            mTvTitleRight.setBackground(getResources().getDrawable(R.drawable.shop_detail_title_right_bg));
        }
    }

    private void getData(String shopId, String lat, String lng, String time, String cityId) {
        Map map = new HashMap();
        map.put("cityId", cityId + "");
        map.put("shopId", shopId + "");
        map.put("lat", lat + "");
        map.put("long", lng + "");
        boolean loginState = checkLoginState();
        if (loginState){
            String userId = String.valueOf((int) SharedPreferencesUtils.getSP(this, "UserId", 0));
            map.put("userId", userId + "");
        }
        Logcat.i("提交的参数：" + map.toString());
        HttpClient.post(ApiConstants.Urls.SHOP_DETAIL_INFO, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                Logcat.i("Bean:" + content);
                ResultBean bean = JSON.parseObject(content, ResultBean.class);
                if (bean.getCode().equals("1")) {
                    detailBean = JSON.parseObject(bean.getData(), ShopDetailDataBean.class);
                    initViewData(detailBean);
                } else {
                    ToastUtil.showToast(bean.getMsg());
                }
                mLayoutManager.showContent();
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                mLayoutManager.showNetError();
            }
        });
    }

    //关注 取消 瑜伽馆
    private void getFollowShop() {
        if (!LoginUtil.checkLogin(this)) return;
        Map map = new HashMap();
        String userId = String.valueOf((int) SharedPreferencesUtils.getSP(this, "UserId", 0));
        map.put("shopId", shopId + "");
        map.put("userId", userId + "");
        //showLoading("正在提交数据");
        Logcat.i("提交的参数：" + map.toString());
        HttpClient.post(ApiConstants.Urls.FOLLOW_SHOP, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                //dismissLoading();
                Logcat.i("Bean:" + content);
                ResultBean bean = JSON.parseObject(content, ResultBean.class);
                if (bean.getCode().equals("1")){
                    if (mIsAttention == 0){
                        initTitleRightText(1);
                        mIsAttention = 1;
                    }else{
                        initTitleRightText(0);
                        mIsAttention = 0;
                    }
                }else{
                    ToastUtil.showToast(bean.getMsg());
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                //dismissLoading();
                mLayoutManager.showNetError();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Logcat.i("权限回调 onPermissionsGranted");
        callPhone(Constants.SERVICE_TELL);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Logcat.i("权限回调 onPermissionsDenied");
        ToastUtil.showToast("请开启拨打电话权限");
    }

    @OnClick(R.id.ll_common_layout)
    public void onViewClicked() {

    }

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        if (postResult.getTag().equals("startAppointmentIntroductionActivity")){
            Bundle bundle = (Bundle) postResult.getResult();
            bundle.putString("shopId",shopId);
            startActivity(AppointmentIntroductionActivity.class,bundle);
        }else if(postResult.getTag().equals("toTeachInfoDetail")){
            Bundle bundle = (Bundle) postResult.getResult();
            bundle.putString("shopId",shopId);
            startActivity(TutorDetailActivity.class,bundle);
        }/*else if(postResult.getTag().equals("2ShopImagesActivity")){
            Bundle bundle = new Bundle();
            bundle.putString("shopId",shopId);
            startActivity(ShopImagesActivity.class,bundle);
        }*/else if(postResult.getTag().equals("2OrderCourseListActivity")){
            Bundle bundle = (Bundle) postResult.getResult();
            bundle.putString("shopId",shopId);
            startActivity(OrderCourseListActivity.class,bundle);
        }else if(postResult.getTag().equals("notifyShopAdapterChanger")){
            mShopDetailAdapter1.notifyDataSetChanged();
        }
    }
}
