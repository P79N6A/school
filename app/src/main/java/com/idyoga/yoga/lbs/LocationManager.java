package com.idyoga.yoga.lbs;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.idyoga.yoga.comm.AppContext;

/**
 * 文 件 名: LocationManager
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/7
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class LocationManager {
    Context mContext;
    YogaLocationListener mYogaLocationListener ;
    LocationClient mLocationClient ;

    public static LocationManager init(Context context,YogaLocationListener yogaLocationListener) {
        return new LocationManager(context,yogaLocationListener);
    }

    public LocationManager(Context context,YogaLocationListener yogaLocationListener) {
        this.mContext = context;
        this.mYogaLocationListener = yogaLocationListener;
        initLocation();
    }



    private void initLocation() {
        mLocationClient = new LocationClient(mContext);
        mLocationClient.registerLocationListener(mYogaLocationListener);
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
    }

    public void onStart() {
        if (mLocationClient != null) {
            mLocationClient.start();
        }
    }

    /**
     * 重启
     */
    public void reStart() {
        if (mLocationClient != null) {
            mLocationClient.restart();
        }
    }

    public void onStop() {
        if (mLocationClient != null) {
            mLocationClient.stop();
        }
    }

}
