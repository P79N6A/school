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
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.home.AppointmentIntroductionActivity;
import com.idyoga.yoga.activity.web.YogaWebActivity;
import com.idyoga.yoga.adapter.HomeFrPagerAdapter;
import com.idyoga.yoga.adapter.ShopMarketCourseDateAdapter;
import com.idyoga.yoga.adapter.base.BaseDelegateAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.Constants;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.fragment.course.CourseDetailFragment;
import com.idyoga.yoga.fragment.course.CourseTagFragment;
import com.idyoga.yoga.listener.OnLoadMoreListener;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.ShopMarketCourseInfoBean;
import com.idyoga.yoga.utils.BannerGlideImageLoader;
import com.idyoga.yoga.utils.DateUtils;
import com.idyoga.yoga.utils.LoginUtil;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.view.TableVIew;
import com.idyoga.yoga.view.csstv.CSSTextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import vip.devkit.library.DensityUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.view.common.banner.BannerConfig;
import vip.devkit.view.common.banner.BannerV;
import vip.devkit.view.common.banner.listener.OnBannerListener;
import vip.devkit.view.common.suklib.view.FlowLayout.TagFlowLayout;

/**
 * 文 件 名: ShopCourseInfoActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/22
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ShopCourseInfoActivity extends BaseActivity implements OnLoadMoreListener, EasyPermissions.PermissionCallbacks {
   /* @BindView(R.id.rlv_content)
    RecyclerView rlvContent;
    @BindView(R.id.ll_left_back)
    LinearLayout llLeftBack;
    @BindView(R.id.ll_title_right_btn)
    LinearLayout llTitleRightBtn;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.ll_foot_layout)
    RelativeLayout llFootLayout;*/

    /*@BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R.id.ll_title_right)
    LinearLayout mLlTitleRight;
    @BindView(R.id.ll_common_layout)
    LinearLayout mLlCommonLayout;
    @BindView(R.id.iv_img)
    ImageView mIvImg;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tag_view)
    TagFlowLayout mTagView;
    @BindView(R.id.tv_shop_name)
    CSSTextView mTvShopName;
    @BindView(R.id.tv_address)
    CSSTextView mTvAddress;
    @BindView(R.id.wv_view)
    WebView mWebView;
    @BindView(R.id.iv_call)
    ImageView mIvCall;
    @BindView(R.id.tv_next)
    TextView mTvNext;
    @BindView(R.id.ll_foot_layout)
    RelativeLayout mLlFootLayout;
    @BindView(R.id.rl_layout)
    RelativeLayout mRlLayout;
    @BindView(R.id.lv_list)
    ListView mLvList;
    @BindView(R.id.tv_time_null)
    TextView mTvTimeNull;*/

    private String parentShopId, shopId, courseId;
    private String lat, lng;
    private ShopMarketCourseInfoBean mCourseInfoBean;
    private ShopMarketCourseDateAdapter mAdapter;
    private List<ShopMarketCourseInfoBean.CourseTimeBean> mAllBeanList = new ArrayList<>();
    private List<ShopMarketCourseInfoBean.CourseTimeBean> mBeanList = new ArrayList<>();
    private BaseDelegateAdapter mBannerAdapter;
    private List<DelegateAdapter.Adapter> adapters;
    private VirtualLayoutManager virtualLayoutManager;
    private DelegateAdapter delegateAdapter;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        //mImmersionBar.titleBar(mLlCommonLayout).init();
    }

    @Override
    protected void initData() {
        mBeanList.clear();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            parentShopId = bundle.getString("parentShopId");
            shopId = bundle.getString("shopId");
            courseId = bundle.getString("courseId");
            lat = (String) SharedPreferencesUtils.getSP(mContext, "latitude", "");
            lng = (String) SharedPreferencesUtils.getSP(mContext, "longitude", "");
            getData(shopId, courseId, lat, lng);
        }
    }

    /*@Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(mRlLayout);
    }*/

    @Override
    protected int setLayoutId() {
        return R.layout.activity_shop_course_info;
    }


    @Override
    protected void initView() {
//        llTitleRightBtn.setVisibility(View.GONE);
        mLayoutManager.showLoading();
        mAdapter = new ShopMarketCourseDateAdapter(this, mBeanList, R.layout.item_shop_market_course);
        mAdapter.setLoadMoreListener(this);
        //mLvList.setAdapter(mAdapter);
    }

    private void initViewData(ShopMarketCourseInfoBean courseInfoBean) {

        if (courseInfoBean != null) {
//            mTvTitleText.setText(courseInfoBean.getLessonName() + "");
//            mTvShopName.setText("瑜伽馆："+courseInfoBean.getShopName());
            String d = "(" +courseInfoBean.getCompare()+"km)";
//            mTvAddress.setText("地址："+courseInfoBean.getAddress()+d);
//            mTvAddress.setTextArrColor(d, Color.parseColor("#999999"));
//            mTvAddress.setTextArrSize(d, 12);
            //Glide.with(this).load(courseInfoBean.getLessonImage()).placeholder(R.drawable.img_course).error(R.drawable.img_course).into(mIvImg);
//            mTvName.setText(courseInfoBean.getLessonName());
//            mTvTime.setText(DateUtils.secToTime(courseInfoBean.getTime()));
//            mWebView.loadUrl(courseInfoBean.getUrl());
//            WebSettings webSettings = mWebView.getSettings();
//            webSettings.setJavaScriptEnabled(true);
//            webSettings.setUseWideViewPort(true);
//            webSettings.setLoadWithOverviewMode(true);
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });*/
            mAllBeanList.addAll(courseInfoBean.getCourseTime());
            /*if (mAllBeanList.size() > 0) {
                mTvTimeNull.setVisibility(View.GONE);
                mLvList.setVisibility(View.VISIBLE);
            } else {
                mTvTimeNull.setVisibility(View.VISIBLE);
                mLvList.setVisibility(View.GONE);
            }*/
            if (mAllBeanList.size() > 3) {
                mAdapter.setCount(mAllBeanList.size());
                for (int i = 0; i < 3; i++) {
                    mBeanList.add(mAllBeanList.get(i));
                }
            } else {
                mAdapter.setCount(mAllBeanList.size());
                mBeanList.addAll(mAllBeanList);
            }
            mAdapter.notifyDataSetChanged();
            mLayoutManager.showContent();
        }
        /*adapters = new LinkedList<>();
        virtualLayoutManager = new VirtualLayoutManager(this);
        rlvContent.setLayoutManager(virtualLayoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        rlvContent.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);
        viewPool.setMaxRecycledViews(1, 20);
        viewPool.setMaxRecycledViews(2, 20);
        viewPool.setMaxRecycledViews(3, 20);
        viewPool.setMaxRecycledViews(4, 20);
        delegateAdapter = new DelegateAdapter(virtualLayoutManager, true);
        rlvContent.setAdapter(delegateAdapter);
        initAllTypeView();
        delegateAdapter.setAdapters(adapters);
        rlvContent.setAdapter(delegateAdapter);*/
    }

    /*private void initAllTypeView() {
        initBannerView();
        initDetail();
        initCourseDeteil();
        initCourseRequire();

    }*/

    //上课须知
    /*private void initCourseRequire() {
        BaseDelegateAdapter courseRequireAdapter = new BaseDelegateAdapter(ShopCourseInfoActivity.this, new LinearLayoutHelper(), R.layout.item_course_require_layout, 1, 3) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);

            }
        };
        adapters.add(courseRequireAdapter);
    }*/

    //课程详情及功效介绍
    /*private void initCourseDeteil() {
        BaseDelegateAdapter courseNameAdapter = new BaseDelegateAdapter(ShopCourseInfoActivity.this, new LinearLayoutHelper(), R.layout.item_course_detail_layout, 1, 2) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                TableVIew tableVIew = holder.getView(R.id.table_view);
                ViewPager detailContent = holder.getView(R.id.vp_detail_content);
                ArrayList<String> tables = new ArrayList<>();
                tables.add("课程详情");
                tables.add("功效介绍");
                ArrayList<Fragment> fragments = new ArrayList<>();
                fragments.add(new CourseDetailFragment());
                fragments.add(new CourseTagFragment());
                HomeFrPagerAdapter homeFrPagerAdapter = new HomeFrPagerAdapter(getSupportFragmentManager(), tables, fragments);
                detailContent.setAdapter(homeFrPagerAdapter);
                detailContent.setCurrentItem(0);
            }
        };
        adapters.add(courseNameAdapter);
    }*/

    //课程名及课程介绍
    /*private void initDetail() {
        BaseDelegateAdapter courseNameAdapter = new BaseDelegateAdapter(ShopCourseInfoActivity.this, new LinearLayoutHelper(), R.layout.item_course_name_layout, 1, 1) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                TextView courseName = holder.getView(R.id.tv_course_name);
                TextView courseIntroduce = holder.getView(R.id.tv_course_introduce);
                TextView arrangeCourse = holder.getView(R.id.tv_arrange_course);
                TextView eserveNum = holder.getView(R.id.tv_eserve_num);
            }
        };
        adapters.add(courseNameAdapter);
    }*/

    /*private void initBannerView() {
        //设置数字指示器
        mBannerAdapter = new BaseDelegateAdapter(ShopCourseInfoActivity.this, new LinearLayoutHelper(), R.layout.layout_banner, 1, 0) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                BannerV mBanner = holder.getView(R.id.bv_view);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mBanner.getLayoutParams();
                layoutParams.height = DensityUtil.dp2px(ShopCourseInfoActivity.this, 266);
                mBanner.setLayoutParams(layoutParams);
                mBanner.setBannerStyle(2);//设置数字指示器
                mBanner.setImageLoader(new BannerGlideImageLoader());
                mBanner.setImages(new ArrayList<String>());
                mBanner.isAutoPlay(true);
                mBanner.setDelayTime(3000);
                mBanner.setIndicatorGravity(BannerConfig.RIGHT);
                mBanner.start();
                mBanner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {

                    }
                });
            }
        };
        adapters.add(mBannerAdapter);
    }*/

    @Override
    protected void setListener() {
        /*mLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                Logcat.i("-------"+mBeanList.get(i).getShop_id()+"/"+mBeanList.get(i).getId());
                bundle.putString("shopId", mBeanList.get(i).getShop_id()+ "");
                bundle.putString("lessonId",mBeanList.get(i).getId()+ "");
                startActivity(AppointmentIntroductionActivity.class, bundle);
            }
        });*/
    }

    @OnClick({R.id.ll_title_back, R.id.iv_call, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.iv_call:
                initPermission();
                callPhone(Constants.SERVICE_TELL);
                break;
            case R.id.tv_next:
                if (!LoginUtil.checkLogin(this)) return;
                Bundle bundle = new Bundle();
                bundle.putString("shopId", shopId);
                bundle.putString("courseId", courseId);
                startActivity(ShopMarketCourseAppointmentActivity.class, bundle);
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


    private void getData(String shopId, String courseId, String lat, String lng) {
        Map map = new HashMap();
        map.put("shopId", shopId + "");
        map.put("lessonId", courseId + "");
        map.put("lng", lng + "");
        map.put("lat", lat + "");
        Logcat.i("瑜伽馆：" + map.toString());
        HttpClient.post(ApiConstants.Urls.SHOP_MARKET_COURSE_INFO, map, new HttpResponseHandler() {
            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                mLayoutManager.showNetError();
            }

            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                Logcat.i("返回数据：" + content);
                ResultBean bean = JSON.parseObject(content, ResultBean.class);
                if (bean.getCode().equals("1")) {
                    mCourseInfoBean = JSON.parseObject(bean.getData(), ShopMarketCourseInfoBean.class);
                    initViewData(mCourseInfoBean);
                } else {
                    ToastUtil.showToast(bean.getMsg());
                }
            }
        });

    }


    @Override
    public void onLoadMore(int type, View view, int position) {
        mBeanList.clear();
        mBeanList.addAll(mAllBeanList);
        mAdapter.notifyDataSetChanged();
    }

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

   /* @OnClick({R.id.ll_left_back, R.id.ll_foot_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_left_back:
                finish();
                break;
            case R.id.ll_foot_layout:
                if (!LoginUtil.checkLogin(this)) return;
                Bundle bundle = new Bundle();
                bundle.putString("shopId", shopId);
                bundle.putString("courseId", courseId);
                startActivity(ShopMarketCourseAppointmentActivity.class, bundle);
                break;
        }
    }*/
}
