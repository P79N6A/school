/*
******************************* Copyright (c)*********************************\
**
**                 (c) Copyright 2018, 珠海现联瑜君岚运营管理有限公司, china, qd. sd
**                                All Rights Reserved
**
**                           By(珠海现联瑜君岚运营管理有限公司)
********************************End of Head************************************\
*/
package com.idyoga.yoga.utils;

import android.app.Dialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.Constants;
import com.idyoga.yoga.model.CheckUpdateBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.view.layoutmanager.LViewHolder;
import com.king.app.updater.AppUpdater;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.NotActiveException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import okhttp3.Call;
import vip.devkit.library.AppUtils;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * File   Name: UpdateUtil
 * Create Date: 2018/11/9 15:45
 * Describe   :
 * Author     : By k
 * E-mail     :vip@devkit.vip
 * VersionName: 1
 * VersionCode: V 1.0
 * Code Update:（author - time）
 * Update Describe：
 */
public class AppUpdate {

    private Context mContext;
    private int hour = 1;
    private boolean isToast = false;
    private boolean upgradePeriod = false;
    private boolean isNotification = false;
    private CheckUpdateBean updateBean;

    private Dialog mUpdateDialog;
    private Dialog mDownloadDialog;

    public static AppUpdate with(Context context) {
        return new AppUpdate(context);
    }

    public AppUpdate(Context context) {
        this.mContext = context;
    }

    public AppUpdate setHour(int hour) {
        this.hour = hour;
        return this;
    }

    /**
     * @param toast
     * @return
     */
    public AppUpdate setToast(boolean toast) {
        isToast = toast;
        return this;
    }

    /**
     * @param upgradePeriod
     */
    public void setUpgradePeriod(boolean upgradePeriod) {
        this.upgradePeriod = upgradePeriod;
    }

    /**
     * @param isNotification
     * @return
     */
    public AppUpdate setNotification(boolean isNotification) {
        this.isNotification = isNotification;
        return this;
    }


    public void init() {
        if (upgradePeriod) {
            String lastTime = (String) SharedPreferencesUtils.getSP(mContext, "appUpdate", "");
            if (TextUtils.isEmpty(lastTime)) {
                SharedPreferencesUtils.setSP(mContext, "appUpdate", System.currentTimeMillis() / 1000 + "");
                checkUpdate();
            } else {
                Long time = System.currentTimeMillis() / 1000;
                int i = (int) (time - Long.valueOf(lastTime) / (1000 * 60 * 60 * hour));
                if (i > 1) {
                    checkUpdate();
                    SharedPreferencesUtils.setSP(mContext, "appUpdate", System.currentTimeMillis() / 1000 + "");
                }
            }
        } else {
            checkUpdate();
        }
    }


    public void checkUpdate() {
        Map<String, String> map = new HashMap<>();
        map.put("code", AppUtils.getAppVersionCode(mContext, mContext.getPackageName()) + "");
        map.put("type", "2");
        map.put("appType", "2");
        OkHttpUtils.get().url("https://platform.hq-xl.com/mall/Index_group/versionsUpdate").params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (isToast) {
                    //ToastUtil.showToast("检测失败，请重试");
                }
            }

            @Override
            public void onResponse(String response, int id) {
                Logcat.i("------------" + response);
                if (TextUtils.isEmpty(response)) {
                    Logcat.e("获取更新失败 返回数据为空");
                    return;
                }
                ResultBean bean = JSON.parseObject(response, ResultBean.class);
                if (bean.getCode().equals("1")) {
                    updateBean = JSON.parseObject(bean.getData(), CheckUpdateBean.class);
                    if (updateBean != null) {
                        showUpdate(updateBean);
                    } else {
                        if (isToast == true) {
                            //ToastUtil.showToast("当前版本是最新版本");
                        }
                        Logcat.e("获取更新失败" + bean.getMsg());
                    }
                } else {
                    if (isToast == true) {
                        //ToastUtil.showToast("当前版本是最新版本");
                    }
                    Logcat.e("获取更新失败" + bean.getMsg());
                }
            }
        });

    }

    private void showUpdate(CheckUpdateBean updateBean) {
        mUpdateDialog = new Dialog(mContext);
        View view = View.inflate(mContext, R.layout.dialog_updata, null);
        mUpdateDialog.setContentView(view);
        LViewHolder holder = new LViewHolder(mContext, view);
        WindowManager.LayoutParams lp = mUpdateDialog.getWindow().getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 宽度设置为屏幕的0.8
        mUpdateDialog.getWindow().setAttributes(lp);
        mUpdateDialog.getWindow().setGravity(Gravity.CENTER);
        mUpdateDialog.setCanceledOnTouchOutside(false);
        mUpdateDialog.setCancelable(false);
        mUpdateDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_SEARCH) {
                    return true;
                }
                return false;
            }
        });
        setViewData(holder, updateBean);
    }

    private void setViewData(LViewHolder holder, final CheckUpdateBean updateBean) {
        if (updateBean.getConstraint().contains("true")) {
            LinearLayout linearLayout = holder.getView(R.id.ll_layout);
            linearLayout.setWeightSum(1);
            holder.getView(R.id.tv_cancel).setVisibility(View.GONE);
            holder.getView(R.id.view_line).setVisibility(View.GONE);
        }

        DecimalFormat d = new DecimalFormat("#.##");

//        String size=d.format(updateBean.getApkSize()/1024/1024);
        holder.setText(R.id.tv_app_version, "" + "瑜伽学院 v" + updateBean.getNewVersion())
                .setText(R.id.tv_file_size, "" + updateBean.getApkSize()+"M")
                .setText(R.id.tv_updata_time, "" + getTimes((long) updateBean.getUpdateTime(), "yyyy-MM-dd HH:dd:ss"))
                .setText(R.id.tv_updata_log, "" + updateBean.getContent());
        int newVersion = Integer.parseInt(updateBean.getNewVersionCode());
        int currentVersion = AppUtils.getAppVersionCode(mContext, mContext.getPackageName());
        int s = newVersion - currentVersion;
        Logcat.i("版本差:" + s + "\n当前版本：" + currentVersion + "\n最新版本：" + newVersion);
        if (newVersion > currentVersion) {
            mUpdateDialog.show();
        } else {
            if (isToast == true) {
                //ToastUtil.showToast("当前版本是最新版本");
            }
        }
        holder.getView(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUpdateDialog.dismiss();
            }
        });

        holder.getView(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUpdateDialog.dismiss();
                new AppUpdater(mContext,updateBean.getDownloadApkUri()).start();
//                showDownload(updateBean.getDownloadApkUri());
            }
        });
    }

    SeekBar seekBar;
    private void showDownload(String downloadApkUri) {
        mDownloadDialog = new Dialog(mContext);
        mDownloadDialog.setContentView(R.layout.dialog_updata_progress);
        WindowManager.LayoutParams lp = mDownloadDialog.getWindow().getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 宽度设置为屏幕的0.8
        mDownloadDialog.getWindow().setAttributes(lp);
        mDownloadDialog.getWindow().setGravity(Gravity.CENTER);
        mDownloadDialog.setCanceledOnTouchOutside(false);
        mDownloadDialog.setCancelable(false);
        seekBar = mDownloadDialog.findViewById(R.id.sb_bar);
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        downloadApkFile(downloadApkUri);
    }



    private void downloadApkFile(String downloadApkUri) {
        Logcat.e("下载地址："+downloadApkUri);
        OkHttpUtils.get().url(downloadApkUri).build().execute(new FileCallBack(Constants.APK_STORE_FOLDER,
                mContext.getPackageName() + updateBean.getNewVersionCode() + ".apk") {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(mContext, "下载失败，请重试", Toast.LENGTH_SHORT).show();
                if (mDownloadDialog != null)
                    mDownloadDialog.dismiss();
            }

            @Override
            public void inProgress(float progress, long total, int id) {
                super.inProgress(progress, total, id);
                Logcat.i("--------------" + progress + "/" + total);
                if (mDownloadDialog != null)
                    mDownloadDialog.show();
                if (seekBar!=null){
                    seekBar.setProgress((int) (100 * progress));
                }
            }

            @Override
            public void onResponse(File response, int id) {
                Logcat.i("file download ：" + response.getName() + "/" + response.getPath());
                if (response != null) {
                    mDownloadDialog.dismiss();
//                    UpdateUtil.installApk(mContext, response.getPath());
//                    UpdateUtil.installApk(mContext, response.getPath());
                }
            }
        });


    }


    public static String getTimes(long time, String type) {
        SimpleDateFormat sdr = new SimpleDateFormat(type, Locale.CHINA);
        Date date = new Date();
        date.setTime(time * 1000);
        String times = sdr.format(date);
        return times;
    }

}
