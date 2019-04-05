package com.idyoga.yoga.base;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.baidu.location.BDLocation;
import com.baidu.mobstat.StatService;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.shop.ShopImagesActivity;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.common.http.type2.presenter.ICommonRequestPresenter;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.common.push.PushService;
import com.idyoga.yoga.common.util.NetworkHandler;
import com.idyoga.yoga.common.util.OkhttpRequestUtil;
import com.idyoga.yoga.common.util.ToastUtils;
import com.idyoga.yoga.common.view.LoadingDialog;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.DialogUtil;
import com.idyoga.yoga.view.YogaLayoutManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import pub.devrel.easypermissions.EasyPermissions;
import rx.annotations.Beta;
import vip.devkit.library.Logcat;
import vip.devkit.library.NetUtils;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;
import vip.devkit.view.common.bar.ImmersionBar;

/**
 * 作者 by K
 * 时间：on 2017/8/28 15:58
 * 邮箱 by  vip@devkit.vip
 * <p/>
 * 类用途：
 * 最后修改：
 */
public abstract class BaseActivity extends AppCompatActivity {
    private long lastBackKeyDownTick = 0;
    public static final long MAX_DOUBLE_BACK_DURATION = 1500;
    protected LoadingDialog loadingDialog;//加载中
    protected Context mContext;
    protected Unbinder unbinder;
    protected ImmersionBar mImmersionBar;
    protected YogaLayoutManager mLayoutManager;
    private WindowManager.LayoutParams layoutParams;
    private PushService.MyBinder myBinder;
    protected NetworkHandler uiHandler;

    public ICommonRequestPresenter iCommonRequestPresenter;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 连接断开
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (PushService.MyBinder) service;
            myBinder.startPushService();
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏应用的标题栏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置禁止横屏
        if (isFlagFullscreen()) {
            initFlagFullscreen();
        }
        setContentView(setLayoutId());
//        unbinder =
        ButterKnife.bind(this);
        mContext = this;
        initBase();
        initData();
        initView();
        setListener();
        StatService.autoTrace(this);
        // 获取测试设备ID
        String testDeviceId = StatService.getTestDeviceId(this);
        Logcat.i("============testDeviceId=============" + testDeviceId);
    }

    private void initBase() {
        uiHandler = createNetWorkHandler();

        /**检测app版本**/
//        Beta.checkUpgrade(false, true);
        /**多状态布局*/
        initBaseLayout();
        initICommonViewUi();
        if (isCheckNetState()) {
            checkNetState(this);
        }
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
        if (isBindEventBusHere()) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
        }
        Intent bindIntent = new Intent(this, PushService.class);
        bindService(bindIntent, connection, BIND_AUTO_CREATE);
    }

    protected abstract void initData();

    protected abstract int setLayoutId();

    protected abstract void initView();

    protected abstract void setListener();

    protected YogaLayoutManager initLayoutManager() {
        return null;
    }

    protected ICommonRequestPresenter initICommonViewUi() {
        return iCommonRequestPresenter = new CommonRequestPresenterImpl(this, null);
    }

    /**
     * 是否绑定eventBus
     */
    protected boolean isBindEventBusHere() {
        return true;
    }

    /***
     * 用于在初始化View之前做一些事
     */
    protected final void init() {

    }


    protected <T extends View> T $(int id) {
        return (T) super.findViewById(id);
    }

    /**
     * startActivity
     *
     * @param clazz
     */
    protected void startActivityWithoutExtras(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }


    /**
     * startActivity  putExtras（Bundle）
     *
     * @param clazz
     * @param extras
     */
    protected void startActivity(Class<?> clazz, Bundle extras) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(extras);
        startActivity(intent);
    }

    /**
     * startActivity  putExtras（Bundle）
     *
     * @param clazz
     * @param requestCode
     * @param extras
     */
    protected void startActivity(Class<?> clazz, int requestCode, Bundle extras) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(extras);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivity  putExtras（Bundle）
     *
     * @param clazz
     * @param code
     */
    protected void startActivity(Class<?> clazz, int code) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, code);
    }

    /**
     * 重启当前Activity
     */
    private void reStartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        long currentTick = System.currentTimeMillis();
        if (currentTick - lastBackKeyDownTick > MAX_DOUBLE_BACK_DURATION) {
            lastBackKeyDownTick = currentTick;
        } else {
            finish();
            System.exit(0);
        }
    }

    /**
     * @param mContext
     */
    protected void checkNetState(Context mContext) {
        Logcat.i("NetStatus:" + NetUtils.isNetworkAvailable(mContext));
        if (initLayoutManager()!=null){
            if (!NetUtils.isNetworkAvailable(mContext)) {
                mLayoutManager.showNetError();
            } else {
                mLayoutManager.showContent();
            }
        }
    }

    /**
     * 初始化多状态布局View
     */
    protected void initBaseLayout() {
        if (initLayoutManager() == null) {
            mLayoutManager = YogaLayoutManager.wrap(this);
        } else {
            mLayoutManager = initLayoutManager();
        }
        mLayoutManager.setNetError(R.layout.yoga_layout_net_error);
        mLayoutManager.showContent();
        mLayoutManager.setOnInflateListener(new YogaLayoutManager.OnInflateListener() {
            @Override
            public void onInflate(int viewType, View view) {
                switch (view.getId()) {
                    case R.id.tv_retry:
                        if (NetUtils.isConnected(mContext)) {
                            initData();
                            initView();
                        } else {
                            DialogUtil.wrap(mContext)
                                    .setData("设置网络", "是否去设置网络")
                                    .setActionClickListener(new DialogUtil.onActionClickListener() {
                                        @Override
                                        public void action(int viewType, Dialog dialog, View view) {
                                            Intent intent = new Intent(Settings.ACTION_SETTINGS);
                                            startActivity(intent);
                                            dialog.dismiss();
                                        }
                                    })
                                    .init()
                                    .show();
                        }
                        checkNetState(getApplicationContext());
                        break;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissLoading();
        //unbinder.unbind();
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //在BaseActivity里销毁
        unbindService(connection);// 解除绑定，断开连接
        if (isBindEventBusHere()) {
            EventBus.getDefault().unregister(this);
        }


    }

    //设置android app 的字体大小不受系统字体大小改变的影响
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    /**
     * 初始化  加载
     *
     * @param str       提示内容
     * @param isOnTouch 点击外部是否关闭
     */
    public void showLoading(String str, boolean isOnTouch) {
        if (!isFinishing()) {
            loadingDialog = new LoadingDialog(mContext, R.style.loading_dialog);
            loadingDialog.setCanceledOnTouchOutside(isOnTouch);
            if (StringUtil.isEmpty(str)) {
                loadingDialog.setText("加载中，请稍等...");
            } else {
                loadingDialog.setText(str);
            }
            loadingDialog.show();
        }
    }

    /**
     * 初始化  加载
     */
    public void showLoading() {
        if (!isFinishing()) {
            loadingDialog = new LoadingDialog(mContext, R.style.loading_dialog);
            loadingDialog.setText("加载中，请稍等...");
            loadingDialog.setCanceledOnTouchOutside(true);
            loadingDialog.show();
        }
    }

    /**
     * 初始化  加载
     *
     * @param str 提示内容
     */
    public void showLoading(String str) {
        if (!isFinishing()) {
//            loadingDialog =LoadingDialog.getInstance(this);
            loadingDialog = new LoadingDialog(mContext, R.style.loading_dialog);
            if (StringUtil.isEmpty(str)) {
                loadingDialog.setText("加载中，请稍等...");
            } else {
                loadingDialog.setText(str);
            }
            loadingDialog.setCanceledOnTouchOutside(true);
            loadingDialog.show();
        }
    }

    public void dismissLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    public void dismissLoading(boolean isUiThread) {
            if (loadingDialog != null && loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
    }

    /**
     * 初始化是否全屏
     */
    protected void initFlagFullscreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.flymeOSStatusBarFontColor("#333333");
        mImmersionBar.statusBarDarkFont(true);//解决部分手机默认白色状态栏
        mImmersionBar.init();
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /**
     * Is CheckNetState  enabled boolean.
     *
     * @return the boolean
     */
    public boolean isCheckNetState() {
        return true;
    }

    /**
     * 接受EventBus 广播
     */
    public void onEvent(PostResult postResult) {
        if (postResult.getTag().equals("lbs")) {
            BDLocation bdLocation= (BDLocation) postResult.getResult();
            SharedPreferencesUtils.setSP(AppContext.getInstance(), "latitude", bdLocation.getLatitude());
            SharedPreferencesUtils.setSP(AppContext.getInstance(), "longitude", bdLocation.getLongitude());
        }
    }


    /**
     * 初始化字体 备用
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    /**
     * 是否全屏
     *
     * @return
     */
    public boolean isFlagFullscreen() {
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @SuppressLint("HandlerLeak")
    public NetworkHandler createNetWorkHandler() {
        return new NetworkHandler(this) {
            @Override
            public void dispatchMessage(Message msg) {
                Logcat.i("------------------- dispatchMessage ");
                if (msg.what == OkhttpRequestUtil.NETWORK_ERROR) {
                    ToastUtils.showToast(BaseActivity.this, "网络错误");
                } else if (msg.what == OkhttpRequestUtil.NONE_DATA) {
                    ToastUtils.showToast(BaseActivity.this, "数据为空");
                } else {
                    networkResponse(msg);
                }
            }
        };
    }

    /**
     * @param msg
     */
    protected void networkResponse(Message msg) {

    }

    /**
     *  检查是否登录,未登录返回false
     */
    protected boolean checkLoginState() {
        String Token = (String) SharedPreferencesUtils.getSP(this, "Token", "Token");
        int UserId = (int) SharedPreferencesUtils.getSP(this, "UserId", 0);
        if (UserId == 0 || CommonUtils.isBlank(Token)) {
            return false;
        } else {
            return true;
        }
    }
}