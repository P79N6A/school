package com.idyoga.yoga.listener;


import com.baidu.location.BDLocation;

public interface DBLocationListener {
    /**
     * @param location
     */
    void onReceiveLocation(BDLocation location);
    void onLocation(double latitude,double longitude);
}
