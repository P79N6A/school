package com.idyoga.yoga.fragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.baidu.location.BDLocation;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.user.LoginActivity;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.comm.Constants;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.common.view.LoadingDialog;
import com.idyoga.yoga.common.yogaweb.LogUtils;
import com.idyoga.yoga.utils.LoginUtil;
import com.idyoga.yoga.utils.ToastUtil;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.List;

import de.greenrobot.event.EventBus;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

/**
 * Created by mjh on 2019/1/8.
 */

public class BaseLoginFragment extends Fragment {
    protected IWXAPI mWxapi;
    private LoadingDialog loadingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isBindEventBusHere()) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
        }
        //创建微信api并注册到微信
        mWxapi = WXAPIFactory.createWXAPI(getContext(), Constants.WX_APP_ID);
        mWxapi.registerApp(Constants.WX_APP_ID);
    }

    /**
     * 是否绑定eventBus
     */
    protected boolean isBindEventBusHere() {
        return true;
    }

    protected void sendWxLoginRequest(){
        //先判断是否安装微信APP,按照微信的说法，目前移动应用上微信登录只提供原生的登录方式，需要用户安装微信客户端才能配合使用。
        if (!checkInstalledWx()){
            ToastUtil.showToast("未安装微信,请先安装微信");
            return ;
        }
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo";
        mWxapi.sendReq(req);
        //getActivity().finish();
    }

//    判断是否安装微信APP,
    public boolean checkInstalledWx(){//isWXAppSupportAPI()
        if(mWxapi.isWXAppInstalled()) {
            return true;
        } else {
            final PackageManager packageManager = getContext().getPackageManager();// 获取packagemanager
            List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
            if (pinfo != null) {
                for (int i = 0; i < pinfo.size(); i++) {
                    String pn = pinfo.get(i).packageName;
                    if (pn.equalsIgnoreCase("com.tencent.mm")) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /**
     * 初始化  加载
     *
     * @param str 提示内容
     */
    public void showLoading(String str) {
//            loadingDialog =LoadingDialog.getInstance(this);
        loadingDialog = new LoadingDialog(getContext(), R.style.loading_dialog);
        if (StringUtil.isEmpty(str)) {
            loadingDialog.setText("加载中，请稍等...");
        } else {
            loadingDialog.setText(str);
        }
        loadingDialog.setCanceledOnTouchOutside(true);
        loadingDialog.show();
    }

    public void dismissLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dismissLoading();
    }

    public void onEvent(PostResult postResult) {
        if ("FinishLoginActivity".equals(postResult.getTag())) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (getActivity() != null){
                        getActivity().finish();
                    }
                }
            });
        }
    }
}
