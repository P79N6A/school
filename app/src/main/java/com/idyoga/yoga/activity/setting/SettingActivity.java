package com.idyoga.yoga.activity.setting;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.user.UpdatePwdActivity;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.listener.DialogClickListener;
import com.idyoga.yoga.model.UserInfoBean;
import com.idyoga.yoga.utils.AppUpdate;
import com.idyoga.yoga.utils.LoginUtil;
import com.idyoga.yoga.utils.ThreadUtils;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.update.UpdateApkUtil;
import com.idyoga.yoga.view.dialog.CommonDialog;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import vip.devkit.library.AppUtils;
import vip.devkit.library.CleanUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * 文 件 名: TestUploadFileActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/12
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class SettingActivity extends BaseActivity {
    /*@BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R.id.ll_title_right)
    LinearLayout mLlTitleRight;
    @BindView(R.id.ll_common_layout)
    LinearLayout mLlCommonLayout;
    @BindView(R.id.rl_account_safe)
    RelativeLayout mRlAccountSafe;
    @BindView(R.id.rl_clear)
    RelativeLayout mRlClear;
    @BindView(R.id.rl_about)
    RelativeLayout mRlAbout;
    @BindView(R.id.tv_cache_size)
    TextView mTvCacheSize;
    @BindView(R.id.btn_login_out)
    Button mBtnLoginOut;
    String userId, Token;
    @BindView(R.id.tv_version_name)
    TextView mTvVersionName;
    @BindView(R.id.rl_check_update)
    RelativeLayout mRlCheckUpdate;*/

    Timer timer;
    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R.id.ll_title_right)
    LinearLayout llTitleRight;
    @BindView(R.id.ll_common_layout)
    RelativeLayout llCommonLayout;
    @BindView(R.id.rl_account_safe)
    RelativeLayout mRlAccountSafe;
    @BindView(R.id.tv_cache_size)
    TextView mTvCacheSize;
    /*@BindView(R.id.iv_cache)
    ImageView mIvCache;*/
    @BindView(R.id.rl_clear)
    RelativeLayout rlClear;
    @BindView(R.id.tv_version_name)
    TextView mTvVersionName;
    /*@BindView(R.id.iv_update)
    ImageView mIvUpdate;*/
    @BindView(R.id.rl_check_update)
    RelativeLayout mRlCheckUpdate;
    @BindView(R.id.rl_about)
    RelativeLayout mRlAbout;
    @BindView(R.id.btn_login_out)
    Button mBtnLoginOut;
    String userId, Token;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(llCommonLayout).statusBarDarkFont(true).init();
    }

    @Override
    protected void initData() {
        userId = String.valueOf((int) SharedPreferencesUtils.getSP(this, "UserId", 0));
        Token = (String) SharedPreferencesUtils.getSP(this, "Token", "");
    }

    @Override
    protected int setLayoutId() {
        return R.layout.setting_activity;
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("设置");
        llCommonLayout.setBackgroundColor(getResources().getColor(R.color.white));
        mTvVersionName.setText(AppUtils.getAppVersionName(this, this.getPackageName()));
        initLoginState();
        File getCache = SettingActivity.this.getApplication().getCacheDir();
        try {
            long cache = CleanUtil.getFolderSize(getCache);
            mTvCacheSize.setText(CleanUtil.getFormatSize(cache));
            Logcat.i("缓存文件大小：" + cache + " 格式化之后：" + CleanUtil.getFormatSize(cache));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检测登录状态
     */
    private void initLoginState() {
        if (LoginUtil.checkLogin(this, false)) {
            mBtnLoginOut.setVisibility(View.VISIBLE);
        } else {
            mBtnLoginOut.setVisibility(View.GONE);
        }
    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.ll_title_back, R.id.rl_account_safe, R.id.rl_clear, R.id.rl_check_update, R.id.rl_about, R.id.btn_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                //setResult();
                finish();
                break;
            case R.id.rl_account_safe:
                if (!LoginUtil.checkLogin(this)) return;
                startActivityWithoutExtras(UpdatePwdActivity.class);
                break;
            case R.id.rl_clear:
                cleanCache();
                break;
            case R.id.rl_check_update:
                AppUpdate.with(this).setToast(true).checkUpdate();
                break;
            case R.id.rl_about:
                startActivityWithoutExtras(AboutActivity.class);
                break;
            case R.id.btn_login_out:
                showLoginOutDialog();
                break;
        }
    }

    /**
     * 清理缓存
     */
    private void cleanCache() {
        ToastUtil.showToast("缓存清理中...");
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                File getCache = SettingActivity.this.getApplication().getCacheDir();
                cleanFile(getCache);
                ThreadUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast("缓存清理完成");
                        File getCache = SettingActivity.this.getApplication().getCacheDir();
                        long cache = 0;
                        try {
                            cache = CleanUtil.getFolderSize(getCache);
                            mTvCacheSize.setText(CleanUtil.getFormatSize(cache));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        timer.schedule(task, 1500 * 1);
    }

    /**
     * show login out dialog
     */
    public void showLoginOutDialog() {
        final CommonDialog dialog = new CommonDialog(this, "注意", "您确定要退出登录？");
        dialog.setClickListener(new DialogClickListener() {
            @Override
            public void onCancel() {
                dialog.dismiss();
            }

            @Override
            public void onSure() {
                LoginOut();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * 退出
     */
    private void LoginOut() {
        ToastUtil.showToast("退出登录中...");
//        Map map =SharedPreferencesUtils.getAllKeyValue(mContext);
//        List<String> listKey = new ArrayList<>();
//        for (Object key:map.keySet()) {
//            if (key.equals("appUpdate")||key.equals("ShopId")||key.equals("AddressName")||key.equals("cityId")||key.equals("latitude")||key.equals("longitude")){
//            }else {
//                listKey.add((String) key);
//            }
//        }
//        for (int i = 0; i <listKey.size() ; i++) {
//            SharedPreferencesUtils.removeSP(this,listKey.get(i));
//        }
        //
        String userInfoBeanStr = (String) SharedPreferencesUtils.getSP(this, "UserInfoBean", "");
        UserInfoBean userInfoBean = JSON.parseObject(userInfoBeanStr, UserInfoBean.class);
        String headimgurl = userInfoBean.getHeadimgurl();
        String avatar_url = userInfoBean.getAvatar_url();
        SharedPreferencesUtils.setSP(this, "avatar_url", avatar_url == null ? "" : avatar_url);
        SharedPreferencesUtils.setSP(this, "headimgurl", headimgurl == null ? "" : headimgurl);
        SharedPreferencesUtils.removeSP(this, "UserId");
        SharedPreferencesUtils.removeSP(this, "Token");
        SharedPreferencesUtils.removeSP(this, "UserInfoBean");
        SharedPreferencesUtils.removeSP(this, "Mobile");
        SharedPreferencesUtils.removeSP(this, "unionid");
        /*try {
            Thread.sleep(1500);
            initLoginState();//更新UI
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        EventBus.getDefault().post(new PostResult("loginOut"));
        ToastUtil.showToast("退出登录成功");
    }

    /**
     * 清除目录下的所有文件
     *
     * @param cacheDir
     */
    public void cleanFile(File cacheDir) {
        try {
            File[] files = cacheDir.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    files[i].delete();
                }
                if (files[i].isDirectory()) {
                    cleanFile(files[i]);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
