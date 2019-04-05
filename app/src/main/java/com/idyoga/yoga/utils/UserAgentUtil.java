package com.idyoga.yoga.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import vip.devkit.library.AppUtils;
import vip.devkit.library.DateUtil;
import vip.devkit.library.DeviceUtil;

/**
 * 文 件 名: UserAgentUtil
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/16
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class UserAgentUtil {


    Context mContext;

    public static UserAgentUtil init(Context context) {
        return new UserAgentUtil(context);
    }

    public UserAgentUtil(Context context) {
        mContext = context;
    }

    public String getUserAgent() {
        Map<String, String> map = new HashMap<>();
        map.put("appVersion", AppUtils.getAppVersionName(mContext, mContext.getPackageName() + "") + "");
        map.put("deviceType", "Android");
        map.put("deviceName", DeviceUtil.getDevice() + "");
        map.put("deviceUUID", DeviceUtil.getSerial() + "");
        map.put("systemName", DeviceUtil.getDisplayVersion() + "");
        map.put("systemVersion", DeviceUtil.getBuildVersionRelease() + "");
        map.put("netType", CommonUtils.GetNetworkType(mContext) + "");
        map.put("IP", getIPAddress(mContext) +"");
        map.put("accessTime", DateUtil.genTimeStamp()+"");
        String userAgent = map.toString().replace("{","");
        userAgent =userAgent.replace("}","");
        userAgent = userAgent.replace("=","/");
        return userAgent;
    }

    public static String getIPAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                return ipAddress;
            }
        } else {
            //当前无网络连接,请在设置中打开网络
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }

}
