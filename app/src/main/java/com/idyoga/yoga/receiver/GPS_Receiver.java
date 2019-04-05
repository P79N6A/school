package com.idyoga.yoga.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;

/**
 * @author weilgu
 * @time 2019/3/4 15:25
 * @des 监听gps 开启,关闭
 */

public class GPS_Receiver {

    private Context mContext ;
    private Receiver receiver ;
    private IGpsChangerCallback mCallback ;
    private String GPS_ACTION = "android.location.PROVIDERS_CHANGED" ;

    public GPS_Receiver( Context context,IGpsChangerCallback callback){
        this.mContext = context;
        this.mCallback = callback;
        observeWifiSwitch();
    }

    private void observeWifiSwitch(){
        IntentFilter filter = new IntentFilter();
        filter.addAction( GPS_ACTION );
        receiver = new Receiver() ;
        mContext.registerReceiver(receiver, filter);
    }

    class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches( GPS_ACTION )) {
                if ( mCallback != null ){
                    mCallback.gpsSwitchState( gpsIsOpen( context ));
                }
            }
        }
    }

    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     * @param context
     * @return true 表示开启
     */
    public boolean gpsIsOpen(final Context context) {
        LocationManager locationManager
                = (LocationManager) context.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通过WLAN或移动网络(4G/3G/2G)确定的位置
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        }
        return false;
    }
}
