package com.idyoga.yoga.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.location.BDLocation;
import com.idyoga.yoga.R;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.common.http.type2.presenter.ICommonRequestPresenter;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.common.modle.PostResult;
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
import vip.devkit.library.Logcat;
import vip.devkit.library.NetUtils;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;
import vip.devkit.view.common.bar.ImmersionBar;

/**
 * 作者 by K
 * 时间：on 2017/8/31 16:31
 * 邮箱 by  vip@devkit.vip
 * <p/>
 * 类用途：
 * 最后修改：
 */
public abstract class BaseFragment extends Fragment {

    public Activity mActivity;
    protected View mRootView;
    private LoadingDialog loadingDialog;//加载中
    protected ImmersionBar mImmersionBar;
    protected NetworkHandler uiHandler;
    public ICommonRequestPresenter iCommonRequestPresenter;
    protected boolean isVisible;
    protected boolean isFirstVisible = true;
    private boolean isCreateView =false;
    protected YogaLayoutManager mLayoutManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            mActivity = (BaseActivity) context;
        } else {
            mActivity = (Activity) context;
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayoutId(), container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        uiHandler = createNetWorkHandler();
        initBaseLayout();
        if (isImmersionBarEnabled())
            initImmersionBar();
        if (isBindEventBusHere()) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
        }
        initICommonViewUi();
        initView(view);
        initData();
        setListener();
        checkNetState(mActivity);
        isCreateView=true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //unbinder.unbind();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
        if (isBindEventBusHere()) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null)
            mImmersionBar.init();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()&&isCreateView==true) {
            if (isFirstVisible) {
                onFirstVisible();
                isFirstVisible = false;
            }
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 可见
     */

    protected void onVisible() {
    }

    /**
     * 第一次可见
     */
    protected void onFirstVisible() {

    }

    /**
     * 不可见
     */
    protected void onInvisible() {

    }

    /**
     * Sets layout id.
     *
     * @return the layout id
     */
    protected abstract int setLayoutId();

    /**
     * 是否在Fragment使用沉浸式
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /**
     * 是否绑定eventBus
     */
    protected boolean isBindEventBusHere() {
        return true;
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true);//解决部分手机默认白色状态栏
        mImmersionBar.flymeOSStatusBarFontColor("#333333");
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }

    protected ICommonRequestPresenter initICommonViewUi() {
        return iCommonRequestPresenter = new CommonRequestPresenterImpl(mActivity, null);
    }

    /**
     * View 的根布局，默认是整个界面，如果需要变换可以重写此方法
     */
    @Deprecated
    public View getStateViewRoot() {
        return mRootView;
    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    /**
     * view与数据绑定
     */
    protected void initView(View view) {

    }

    /**
     * 初始化多状态布局View
     */
    protected YogaLayoutManager initLayoutManager() {
        return null;
    }

    /**
     * 初始化多状态布局View
     */
    protected void initBaseLayout() {
        if (initLayoutManager() != null) {
            mLayoutManager = initLayoutManager();
            mLayoutManager.setNetError(R.layout.yoga_layout_net_error);
            mLayoutManager.showContent();
            mLayoutManager.setOnInflateListener(new YogaLayoutManager.OnInflateListener() {
                @Override
                public void onInflate(int viewType, View view) {
                    switch (view.getId()) {
                        case R.id.tv_retry:
                            if (NetUtils.isConnected(mActivity)) {
                                initData();
                                initView(getStateViewRoot());
                            } else {
                                DialogUtil.wrap(mActivity)
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
                            checkNetState(mActivity);
                            break;
                    }
                }
            });
        }
    }

    /**
     * 设置监听
     */
    protected void setListener() {

    }

    /**
     * 找到activity的控件
     *
     * @param <T> the type parameter
     * @param id  the id
     * @return the t
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T findActivityViewById(@IdRes int id) {
        return (T) mActivity.findViewById(id);
    }


    /**
     * startActivity
     *
     * @param clazz
     */
    protected void startActivityWithoutExtras(Class<?> clazz) {
        Intent intent = new Intent(mActivity, clazz);
        startActivity(intent);
    }

    /**
     * startActivity  putExtras（Bundle）
     *
     * @param clazz
     * @param extras
     */
    protected void startActivityWithExtras(Class<?> clazz, Bundle extras) {
        Intent intent = new Intent(mActivity, clazz);
        intent.putExtras(extras);
        startActivity(intent);
    }

    /**
     * startActivity  putExtras（Bundle）
     *
     * @param clazz
     * @param extras
     */
    protected void startActivityWithExtras(Class<?> clazz, int m, Bundle extras) {
        Intent intent = new Intent(mActivity, clazz);
        intent.putExtras(extras);
        startActivityForResult(intent, m);
    }

    /**
     * startActivity  putExtras（Bundle）
     *
     * @param clazz
     */
    protected void startActivityWithExtras(Class<?> clazz, int m) {
        Intent intent = new Intent(mActivity, clazz);
        startActivityForResult(intent, m);
    }

    /**
     * 重启当前Activity
     */
    private void reStartActivity() {
        Intent intent = mActivity.getIntent();
        mActivity.finish();
        startActivity(intent);
    }

    /**
     * @param mContext
     */
    protected void checkNetState(Context mContext) {
        Logcat.i("NetStatus:" + NetUtils.isNetworkAvailable(mContext));
        if (initLayoutManager() != null) {
            if (!NetUtils.isNetworkAvailable(mContext)) {
                mLayoutManager.showNetError();
            } else {
                mLayoutManager.showContent();
            }
        }
    }

    /**
     * 初始化  加载
     *
     * @param str       提示内容
     * @param isOnTouch 点击外部是否关闭
     */
    public void showLoading(String str, boolean isOnTouch) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(mActivity, R.style.loading_dialog);
            if (!StringUtil.isEmpty(str)) {
                loadingDialog.setText(str);
            } else {
                loadingDialog.setText("加载中，请稍等...");
            }
        }
        loadingDialog.setCanceledOnTouchOutside(isOnTouch);
        loadingDialog.show();
    }

    /**
     * 初始化  加载
     *
     * @param str 提示内容
     */
    public void showLoading(String str) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(mActivity, R.style.loading_dialog);
            if (null != str) {
                loadingDialog.setText(str);
            } else {
                loadingDialog.setText("加载中，请稍等...");
            }
        }
        loadingDialog.setCanceledOnTouchOutside(true);
        loadingDialog.show();
    }

    /**
     * 初始化  加载
     */
    public void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(mActivity, R.style.loading_dialog);
            loadingDialog.setText("加载中，请稍等...");
        }
        loadingDialog.setCanceledOnTouchOutside(true);
        loadingDialog.show();
    }

    public void dismissLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    /**
     * 接受EventBus 广播
     */
    public void onEvent(PostResult postResult) {
        if (postResult.getTag().equals("lbs")) {
            BDLocation bdLocation = (BDLocation) postResult.getResult();
            SharedPreferencesUtils.setSP(AppContext.getInstance(), "latitude", bdLocation.getLatitude());
            SharedPreferencesUtils.setSP(AppContext.getInstance(), "longitude", bdLocation.getLongitude());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @SuppressLint("HandlerLeak")
    public NetworkHandler createNetWorkHandler() {
        return new NetworkHandler(mActivity) {
            @Override
            public void dispatchMessage(Message msg) {
                Logcat.i("-------------------  baseFragment ");
                if (msg.what == OkhttpRequestUtil.NETWORK_ERROR) {
                    ToastUtils.showToast(mActivity, "网络错误");
                } else if (msg.what == OkhttpRequestUtil.NONE_DATA) {
                    ToastUtils.showToast(mActivity, "数据为空");
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
     * 检查是否登录,未登录返回false
     * @return
     */
    protected boolean checkLoginState() {
        String Token = (String) SharedPreferencesUtils.getSP(getContext(), "Token", "Token");
        int UserId = (int) SharedPreferencesUtils.getSP(getContext(), "UserId", 0);
        if (UserId == 0 || CommonUtils.isBlank(Token)) {
            return false;
        } else {
            return true;
        }
    }
}