package com.idyoga.yoga.comm;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

import com.alivc.player.AliVcMediaPlayer;
import com.alivc.player.VcPlayerLog;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.baidu.android.pushservice.PushSettings;
import com.baidu.mapapi.SDKInitializer;
import com.facebook.stetho.Stetho;
import com.idyoga.yoga.activity.loading.WelcomeActivity;
import com.squareup.leakcanary.LeakCanary;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import vip.devkit.common.share.ShareConfig;
import vip.devkit.common.share.ShareManager;
import vip.devkit.library.Logcat;
import vip.devkit.library.Utils;
import vip.devkit.view.common.ImgPicker.ImagePicker;
import vip.devkit.view.common.ImgPicker.view.CropImageView;

import static com.idyoga.yoga.comm.AppContext.countImg;

/**
 * 文 件 名: AppConfig
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/1
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注： APP 初始化以及常用配置
 */
public class AppConfig {

    Context mContext;
    static AppConfig mAppConfig;

    public AppConfig() {
        mAppConfig = this;
        mContext = AppContext.getInstance();
    }

    public static synchronized AppConfig getInstance() {
        if (mAppConfig == null) {
            mAppConfig = new AppConfig();
        }
        return mAppConfig;

    }

    /**
     *
     */
    public void initAppConfig() {
        /**
         * 初始化Util
         */
        Utils.init(mContext);
        /**
         * LogCat
         */
        Logcat.init(true, "Yoga");


        initOkHttp();
        /**
         * 数据库
         */
        initRealm();
        /**
         *  缓存
         */
        //  SharedPreferencesUtil.init(mContext, "App");
        /**
         *  扫码 ，生成二维码
         */
        ZXingLibrary.initDisplayOpinion(mContext);

        /**
         *分享
         **/
        initShare();
        /**
         *   默认图片选择器
         */
        initImgPicker(countImg);
        /**
         *
         * 初始化百度推送
         */
        initBdPush(true);

        /**
         *  异常操作
         */
        initExceptionControl(false, false);
        /**
         *  LeakCanary
         */
        initLeakCanary(true, false);

        initStetho(mContext);

        /**
         *AliVcMediaPlayer基础播放sdk
         */
        VcPlayerLog.enableLog();
        AliVcMediaPlayer.init(mContext);

        initShare();


        SDKInitializer.initialize(AppContext.getInstance());


        /**
         *showUETMenu
         */
        initDebug();
    }

    private void initOkHttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("Yoga"))
                .connectTimeout(20000L, TimeUnit.MILLISECONDS)
                .readTimeout(20000L, TimeUnit.MILLISECONDS)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    private void initDebug() {
    }

    private void initStetho(Context context) {
        Stetho.initializeWithDefaults(context);
    }

    private void initShare() {
        ShareConfig config = ShareConfig.instance()
                .qqId(Constants.QQ_APP_ID)
                .wxId(Constants.WX_APP_ID)
                .weiboId(Constants.WB_APP_ID)
                .wxSecret(Constants.WX_SECRET_ID)
                .weiboRedirectUrl(Constants.WB_APP_ID);
        ShareManager.init(config);
    }


    private void initRealm() {
    }

    /**
     * 方法公共  方便使用场景设置数量
     *
     * @param countImg
     */
    public void initImgPicker(int countImg) {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new YogaPickerLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(false);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(countImg);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

    private void initBdPush(boolean isDebug) {
        PushSettings.enableDebugMode(mContext, isDebug);
        PushManager.startWork(mContext, PushConstants.LOGIN_TYPE_API_KEY, "5vzriHi3TxZD4hj64GbAYYS279PpPx1P");
    }

    private void initExceptionControl(boolean isDebug, boolean isEnable) {
        if (isEnable) {
            Thread.setDefaultUncaughtExceptionHandler(restartHandler);
        } else {
        }
    }

    private void initLeakCanary(boolean isDebug, boolean isEnable) {
        if (isEnable) {
            LeakCanary.install(AppContext.getInstance());
        } else {
        }
    }


    @TargetApi(9)
    protected void setStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
    }

    public void restartApp() {
        Intent intent = new Intent(mContext, WelcomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
//        System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    private Thread.UncaughtExceptionHandler restartHandler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable ex) {
            restartApp();
        }
    };


    /**
     * 适配解决方案
     */
    private static float sNonCompatDensity;
    private static float sNonCompatScaledDensity;

    /**
     * @param activity
     * @param application
     * @param w 设计图宽度
     */
    public static void setCostomDensity(@NonNull final Activity activity, @NonNull final Application application,int w) {
        final DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        if (sNonCompatDensity == 0) {
            sNonCompatDensity = displayMetrics.density;
            sNonCompatScaledDensity = displayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration configuration) {
                    if (configuration != null && configuration.fontScale > 0) {
                        sNonCompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {
                }
            });
        }
        final float targetDensity = displayMetrics.widthPixels / w;
        final float targetScaledDensity = targetDensity * (sNonCompatScaledDensity / sNonCompatDensity);
        final int targetDensityDpi = (int) (160 * targetDensity);

        displayMetrics.density=targetDensity;
        displayMetrics.scaledDensity=targetScaledDensity;
        displayMetrics.densityDpi=targetDensityDpi;

        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density=targetDensity;
        activityDisplayMetrics.scaledDensity=targetScaledDensity;
        activityDisplayMetrics.densityDpi=targetDensityDpi;
    }
}
