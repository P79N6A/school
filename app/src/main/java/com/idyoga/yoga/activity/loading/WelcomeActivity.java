package com.idyoga.yoga.activity.loading;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.idyoga.yoga.activity.MainActivity;
import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.lbs.YogaLocationListener;

import java.util.Timer;
import java.util.TimerTask;

import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

/**
 * 程序入口，启动app欢迎界面，延时功能和判断功能
 */
public class WelcomeActivity extends BaseActivity {
    private SharedPreferences sharepreferences;//实例化 SharedPreferences
    private SharedPreferences.Editor editor;


    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.init();
    }

    @Override
    public boolean isCheckNetState() {
        return  false;
    }

    @Override
    protected void initData() {
        initLocation();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        sharepreferences = this.getSharedPreferences("check", MODE_PRIVATE);// 初始化 SharedPreferences 储存
        editor = sharepreferences.edit();
        proInspect(this);
        isFirstStart(this);
        initProData(this);
    }

    @Override
    protected void setListener() {

    }

    /**
     * 重新安装/或更新之后删除数据库的时候
     *
     * @param welcomeActivity
     */
    private void initProData(WelcomeActivity welcomeActivity) {
        try {
            PackageManager packageManager = getApplicationContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(this.getPackageName(), 0);
            //应用装时间
            long firstInstallTime = packageInfo.firstInstallTime;
            //应用最后一次更新时间
            long lastUpdateTime = packageInfo.lastUpdateTime;
            Logcat.i("first install time : " + firstInstallTime + " last update time :" + lastUpdateTime);
            if (lastUpdateTime > firstInstallTime) {
                if (lastUpdateTime == System.currentTimeMillis()) {
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    // TODO: 2017/8/29  程序安检

    /**
     * 用于初始化/检查程序
     *
     * @param mContext
     */
    private void proInspect(Context mContext) {

    }

    /**
     * @param mContext
     */
    public void isFirstStart(Context mContext) {
        boolean isFirstS = sharepreferences.getBoolean("isFirstStart", true);
        if (isFirstS) {
            proStartActivity(mContext, true);
            editor.putBoolean("isFirstStart", false);//第一次启动后
            editor.commit();
        } else {
            proStartActivity(mContext, false);
        }
    }

    /**
     * @param mContext
     * @param isFirst
     */
    private void proStartActivity(final Context mContext, final boolean isFirst) {
        final String isSetAddress = (String) SharedPreferencesUtils.getSP(this,"setAddress","");
        final Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (isFirst||StringUtil.isEmpty(isSetAddress)) {
                    Bundle bundle = new Bundle();
                    startActivity(SetCityActivity.class,bundle);
                } else {
                    startActivity(new Intent(mContext, MainActivity.class));
                }
                finish();
            }
        };
        // 设置不同的延迟启动时间
        if (isFirst|| StringUtil.isEmpty(isSetAddress)) {
            timer.schedule(task, 1000 * 1);
        } else {
            timer.schedule(task, 1000 * 1);
        }
    }

    /**
     *定位
     */
    protected void initLocation() {
        final LocationClient mLocationClient = new LocationClient(this);
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
        mLocationClient.registerLocationListener(new YogaLocationListener(){
            @Override
            public void onReceiveLocation(BDLocation location) {
                super.onReceiveLocation(location);
                double latitude = location.getLatitude();    //获取纬度信息
                double longitude = location.getLongitude();    //获取经度信息
                SharedPreferencesUtils.setSP(AppContext.getInstance(), "latitude", latitude);
                SharedPreferencesUtils.setSP(AppContext.getInstance(), "longitude", longitude);
            }
        });
        mLocationClient.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mLocationClient.stop();
            }
        }, 5000);
    }



}

