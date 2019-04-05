package com.idyoga.yoga.lbs;

import android.location.LocationListener;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.fragment.BaseWebFragment;
import com.idyoga.yoga.listener.DBLocationListener;

import de.greenrobot.event.EventBus;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * 文 件 名: YogaLocationListener
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/7
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class YogaLocationListener extends BDAbstractLocationListener {
    @Override
    public void onReceiveLocation(BDLocation location) {
        double latitude = location.getLatitude();    //获取纬度信息
        double longitude = location.getLongitude();    //获取经度信息
        SharedPreferencesUtils.setSP(AppContext.getInstance(), "latitude", latitude);
        SharedPreferencesUtils.setSP(AppContext.getInstance(), "longitude", longitude);
        EventBus.getDefault().post(new PostResult("lbs",location));

    }

}
