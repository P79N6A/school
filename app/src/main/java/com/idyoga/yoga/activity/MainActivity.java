package com.idyoga.yoga.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.lbs.SetCityUtil;
import com.idyoga.yoga.activity.shop.ShopDetailActivity;
import com.idyoga.yoga.activity.shop.ShopImagesActivity;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.fragment.CourseFragment;
import com.idyoga.yoga.fragment.CreditFragment;
import com.idyoga.yoga.fragment.HomeFragment;
import com.idyoga.yoga.fragment.MineFragment;
import com.idyoga.yoga.fragment.VideoFragment;
import com.idyoga.yoga.fragment.child.FragmentCourseByVideo;
import com.idyoga.yoga.fragment.child.FragmentExperienceC;
import com.idyoga.yoga.fragment.child.FragmentHomeRecommend;
import com.idyoga.yoga.lbs.YogaLocationListener;
import com.idyoga.yoga.model.CityBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.receiver.GPS_Receiver;
import com.idyoga.yoga.receiver.IGpsChangerCallback;
import com.idyoga.yoga.utils.AppUpdate;
import com.idyoga.yoga.utils.PermissionUtil;
import com.idyoga.yoga.utils.YogaViewUtil;
import com.idyoga.yoga.utils.update.UpdateApkUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import vip.devkit.library.GetAppSign;
import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.StringUtil;
import vip.devkit.view.common.bar.ImmersionBar;


/**
 * 作者 by K
 * 时间：on 2017/8/28 15:01
 * 邮箱 by  vip@devkit.vip
 * <p/>
 * 类用途：
 * 最后修改：
 */
public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks ,IGpsChangerCallback{
    @BindView(R.id.content)
    FrameLayout mContent;
    @BindView(R.id.ll_home)
    LinearLayout mLlHome;
    @BindView(R.id.ll_category)
    LinearLayout mLlCategory;
    @BindView(R.id.ll_service)
    LinearLayout mLlService;
    @BindView(R.id.ll_mine)
    LinearLayout mLlMine;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.iv_home)
    ImageView mIvHome;
    @BindView(R.id.tv_top)
    TextView mTvTop;
    @BindView(R.id.iv_course)
    ImageView mIvCourse;
    private HomeFragment mHomeFragment;
    private FragmentCourseByVideo mVideoCourseFragment;
    private VideoFragment mVideoFragment;
    private CourseFragment mCourseFragment;
    private CreditFragment mCreditFragment;
    private MineFragment mMineFragment;
    private FragmentExperienceC mFragmentExperienceC;
    private FragmentHomeRecommend mFragmentHomeRecommend;
    private boolean mIsShowShopList = false;
    //    private boolean wxPlayIsCancel = false;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).flymeOSStatusBarFontColor("#333333").init();
    }

    static MainActivity mainActivity;

    public MainActivity() {
        mainActivity = this;
    }

    public static synchronized MainActivity getInstance() {
        if (mainActivity == null) {
            mainActivity = new MainActivity();
        }
        return mainActivity;
    }

    @Override
    protected void initData() {
        if (!ListUtil.isEmpty(SetCityUtil.init(mContext).getRecentCityBean())) {
            Logcat.i("---" + GetAppSign.getSign(this, this.getPackageName()) + "/" +
                    SetCityUtil.init(mContext).getRecentCityBean().size());
        }

        /**更新地址数据*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                upDataAddressData();
            }
        }, 60*1000);
        //开启gps 开启/关闭的监听
        GPS_Receiver gps_receiver = new GPS_Receiver(this, this);
    }


    @Override
    protected void initView() {
//        AppConfig.getInstance().setCostomDensity(this,AppContext.getInstance(),720);
        selectedFragment(0);
        tabSelected(mLlHome);
        /**检测app版本**/
        PermissionUtil.initPermission(this);
        initPermissionR();
    }

    @AfterPermissionGranted(REQUEST_CAMERA_PERM)
    public void initPermissionR() {
        if (EasyPermissions.hasPermissions(this, PermissionUtil.permissions)) {
            initCheckUpdate();
            initLocation();
        } else {
            ActivityCompat.requestPermissions(this, PermissionUtil.permissions, 100);
        }
    }

    LocationClient mLocationClient;
    /**
     * 定位
     */
    protected void initLocation() {
        mLocationClient = new LocationClient(this);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIgnoreKillProcess(false);
        option.SetIgnoreCacheException(false);
        option.setWifiCacheTimeOut(5 * 60 * 1000);
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(new YogaLocationListener());
        mLocationClient.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mLocationClient.stop();
            }
        }, 5000);
    }

    /**
     * 检测更新
     */
    private void initCheckUpdate() {
        AppUpdate.with(this).setToast(true).setHour(24).checkUpdate();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setListener() {
    }

    public void selectedFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        switch (position) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    transaction.add(R.id.content, mHomeFragment);
                } else {
                    Logcat.i("--------------------- TAG:" + mHomeFragment.isVisible());
                    if (mHomeFragment.isVisible() && isTop == false) {
                        mIvHome.setImageResource(R.drawable.menu_home_select);
                        mTvTop.setText("首页");
                        EventBus.getDefault().post(new PostResult("HOME_TOP", "TAG"));
                    } else {
                        transaction.show(mHomeFragment);
                    }
                }
                break;
            case 1:
                if (mVideoFragment == null) {
                    mVideoFragment = new VideoFragment();
                    transaction.add(R.id.content, mVideoFragment);
                } else {
                    transaction.show(mVideoFragment);
                }
                break;
            case 2:
                if (mFragmentExperienceC == null){
                    mFragmentExperienceC = new FragmentExperienceC();
                    transaction.add(R.id.content,mFragmentExperienceC);
                }else{
                    transaction.show(mFragmentExperienceC);
                }

                /*if (mCreditFragment == null) {
                    mCreditFragment = new CreditFragment();
                    transaction.add(R.id.content, mCreditFragment);
                } else {
                    transaction.show(mCreditFragment);
                }
                EventBus.getDefault().post(new PostResult("showCreditFragment"));
                break;*/
                break;
            case 3:
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                    transaction.add(R.id.content, mMineFragment);
                } else {
                    transaction.show(mMineFragment);
                }
                break;
        }
        if (mFragmentHomeRecommend != null && !mFragmentHomeRecommend.isHidden()) {
            mIvHome.setImageResource(R.drawable.menu_home_select);
            mTvTop.setText("首页");
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mHomeFragment != null)
            transaction.hide(mHomeFragment);
        if (mVideoFragment != null)
            transaction.hide(mVideoFragment);
        if (mFragmentExperienceC != null)
            transaction.hide(mFragmentExperienceC);
        /*if (mCreditFragment != null)
            transaction.hide(mCreditFragment);*/
        if (mMineFragment != null)
            transaction.hide(mMineFragment);
    }

    private void tabSelected(LinearLayout linearLayout) {
        mLlHome.setSelected(false);
        mLlCategory.setSelected(false);
        mLlService.setSelected(false);
        mLlMine.setSelected(false);
        linearLayout.setSelected(true);
    }

    @OnClick({R.id.ll_home, R.id.ll_category, R.id.ll_service, R.id.ll_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_home:
                selectedFragment(0);
                tabSelected(mLlHome);
                break;
            case R.id.ll_category:
                selectedFragment(1);
                tabSelected(mLlCategory);
                break;
            case R.id.ll_service:
                selectedFragment(2);
                tabSelected(mLlService);
                break;
            case R.id.ll_mine:
                selectedFragment(3);
                tabSelected(mLlMine);
                break;
        }
    }

    public void showFragment(int position) {
        switch (position) {
            case 0:
                selectedFragment(0);
                tabSelected(mLlHome);
                break;
            case 1:
                selectedFragment(1);
                tabSelected(mLlCategory);
                break;
            case 2:
                selectedFragment(2);
                tabSelected(mLlService);
                break;
            case 3:
                selectedFragment(3);
                tabSelected(mLlMine);
                break;
            default:
                selectedFragment(0);
                tabSelected(mLlHome);
                break;
        }
    }

    public void showFragment(int position, int childPosition) {
        HomeFragment homeFragment = HomeFragment.getInstance();
        switch (position) {
            case 0:
                selectedFragment(0);
                tabSelected(mLlHome);
                break;
            case 1:
                selectedFragment(1);
                tabSelected(mLlCategory);
                break;
            case 2:
                selectedFragment(2);
                tabSelected(mLlService);
                break;
            case 3:
                selectedFragment(3);
                tabSelected(mLlMine);
                break;
            default:
                selectedFragment(0);
                tabSelected(mLlHome);
                break;
        }
        homeFragment.setHomeTabs(childPosition);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mIsShowShopList){
            showFragment(2);
            mIsShowShopList = false;
        }
        Intent intent = getIntent();
        if (intent != null) {
            Bundle mBundle = intent.getExtras();
            if (mBundle != null) {
                String fTag = mBundle.getString("fTag");
                if (!StringUtil.isEmpty(fTag) && fTag.equals("f1")) {
                    showFragment(1);
                    mBundle.remove(fTag);
                    mBundle.clear();
                }
            }
        }
    }

    boolean isTop = true;

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        Logcat.i("onEvent:" + postResult.getTag() + "/" + postResult.getResult());
        if (postResult.getTag().equals("toMain")) {
            if (postResult.getResult().equals("1")) {
                showFragment(1);
            }
        }else if(postResult.getTag().equals("2ShopImagesActivity")){
            Bundle bundle = (Bundle) postResult.getResult();
            startActivity(ShopImagesActivity.class,bundle);
        }else if(postResult.getTag().equals("2ShopDetailActivity")){
            Bundle bundle = (Bundle) postResult.getResult();
            startActivity(ShopDetailActivity.class,bundle);
        }else if(postResult.getTag().equals("2HomeShopList")){
            mIsShowShopList = true;
        }
        /*else if (postResult.getTag().equals("toTop_home")) {
            Logcat.i("isTop:"+isTop);
            if (isTop) {
                mIvHome.setImageResource(R.drawable.menu_home_select);
                mTvTop.setText("首页");
            }else {
                mIvHome.setImageResource(R.drawable.icon_plane);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mIvHome.getLayoutParams();
                layoutParams.weight = YogaViewUtil.getViewWidth(mIvCourse);
                layoutParams.height = YogaViewUtil.getViewHeight(mIvCourse);
                mIvHome.setLayoutParams(layoutParams);
                mTvTop.setText("返回顶部");
            }
        } else if (postResult.getTag().equals("toTop")) {
            if (postResult.getResult().equals("0")) {
                mIvHome.setImageResource(R.drawable.menu_home_select);
                mTvTop.setText("首页");
                isTop = true;
            } else if (postResult.getResult().equals("1")) {
                mIvHome.setImageResource(R.drawable.icon_plane);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mIvHome.getLayoutParams();
                layoutParams.weight = YogaViewUtil.getViewWidth(mIvCourse);
                layoutParams.height = YogaViewUtil.getViewHeight(mIvCourse);
                mIvHome.setLayoutParams(layoutParams);
                mTvTop.setText("返回顶部");
                isTop = false;
            }
        }*/else if("bespokeCourse".equals(postResult.getTag())){
            selectedFragment(0);
            /*Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mIvHome != null && mTvTop != null){
                                mIvHome.setImageResource(R.drawable.menu_home_select);
                                mTvTop.setText("首页");
                                HomeFragment homeFragment = HomeFragment.getInstance();
                                if (homeFragment != null) {
                                    homeFragment.setHomeTabs(0);
                                }
                            }
                        }
                    });
                }
            };
            timer.schedule(task,100);*/

        }else if("wxPayCancel".equals(postResult.getTag())){

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//如果返回键按下
            //Logcat.i("----------mainActivity---onKeyDown " + mCreditFragment.isVisible());
            if (mCreditFragment != null && mCreditFragment.isVisible()){
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("keyCode",keyCode);
                hashMap.put("event",event);
                EventBus.getDefault().post(new PostResult("onKeyDown",hashMap));
                return true;
            }
            //此处写退向后台的处理
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 请求CAMERA权限码
     */
    public static final int REQUEST_CAMERA_PERM = 101;

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Logcat.e("允许：" + requestCode + "/" + perms.size() + "/" + perms.toString());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Logcat.e("禁止：" + requestCode + "/" + perms.size() + "/" + perms.toString());
    }

    public void upDataAddressData() {
        HttpClient.get(ApiConstants.Urls.GET_CITY, new HashMap<String, String>(), new HttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                Logcat.i("返回的城市数据：" + content);
                ResultBean bean = JSON.parseObject(content, ResultBean.class);
                if (bean.getCode().equals("1")) {
                    CityBean mCityBean = JSON.parseObject(bean.getData(), CityBean.class);
                    SetCityUtil.setCityBean(mContext, mCityBean);
                } else {
                    mLayoutManager.showNetError();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10002){
            mIvHome.setImageResource(R.drawable.menu_home_select);
            mTvTop.setText("首页");

            HomeFragment homeFragment = HomeFragment.getInstance();
            if (homeFragment != null) {
                homeFragment.setHomeTabs(1);
            }
        }
    }

    @Override
    public void gpsSwitchState(boolean gpsOpen) {
        Logcat.i("----------------gpsSwitchState------------- gpsOpen = " + gpsOpen);
//        initLocation();
        initPermissionR();
    }
}