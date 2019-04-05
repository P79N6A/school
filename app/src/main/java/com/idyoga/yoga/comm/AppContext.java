package com.idyoga.yoga.comm;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.StrictMode;
import android.support.multidex.MultiDex;

import com.alivc.player.AliVcMediaPlayer;
import com.alivc.player.VcPlayerLog;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.baidu.android.pushservice.PushSettings;
import com.idyoga.yoga.activity.MainActivity;
import com.idyoga.yoga.activity.loading.WelcomeActivity;
import com.idyoga.yoga.activity.user.LoginActivity;
import com.squareup.leakcanary.LeakCanary;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import vip.devkit.common.share.ShareConfig;
import vip.devkit.common.share.ShareManager;
import vip.devkit.library.Logcat;
import vip.devkit.library.Utils;
import vip.devkit.view.common.ImgPicker.ImagePicker;
import vip.devkit.view.common.ImgPicker.loader.ImgPickerLoader;
import vip.devkit.view.common.ImgPicker.view.CropImageView;

/**
 * 作者 by K
 * 时间：on 2017/8/21 10:58
 * 邮箱 by  vip@devkit.vip
 * <p/>
 * 类用途：
 * 最后修改：
 */
public class AppContext extends Application {

    public static Application application;
    public static Handler mHandler;
    private static AppContext app;
    public static Context mContext;
    public static int countImg = 9;

    public AppContext() {
        app = this;
    }

    public static synchronized AppContext getInstance() {
        if (app == null) {
            app = new AppContext();
        }
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();
        application = this;
        mContext = this;
        AppConfig.getInstance().initAppConfig();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
