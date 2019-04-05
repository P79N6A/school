/**
 * ****************************** Copyright (c)*********************************\
 * *
 * *                 (c) Copyright 2017, DevKit.vip, china, qd. sd
 * *                          All Rights Reserved
 * *
 * *                           By(K)
 * *
 * *-----------------------------------版本信息------------------------------------
 * * 版    本: V0.1
 * *
 * *------------------------------------------------------------------------------
 * *******************************End of Head************************************\
 */
package com.idyoga.yoga.utils.update;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.comm.Constants;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.view.LoadingDialog;
import com.idyoga.yoga.model.CheckUpdateBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;
import vip.devkit.library.AppUtils;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

/**
 * 文 件 名: UpdateApkUtil
 * 创 建 人: By k
 * 创建日期: 2018/3/29 22:33
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class UpdateApkUtil {
    @BindView(R.id.tv_app)
    TextView mTvApp;
    @BindView(R.id.tv_app_version)
    TextView mTvAppVersion;
    @BindView(R.id.tv_file_size)
    TextView mTvFileSize;
    @BindView(R.id.tv_updata_time)
    TextView mTvUpdataTime;
    @BindView(R.id.tv_updata_log)
    TextView mTvUpdataLog;
    @BindView(R.id.tv_cancel)
    TextView mTvCancel;
    @BindView(R.id.tv_sure)
    TextView mTvSure;
    @BindView(R.id.view_line)
    View mViewLine;
    @BindView(R.id.ll_layout)
    LinearLayout mLlLayout;
    SeekBar mSeekBar;
    private Context mContext;
    private boolean isToast = false;
    private Dialog mDialog;
    private Dialog mDownloadDialog;
    private CheckUpdateBean updateBean;
    int getVersion;
    int currentVersion;
    LoadingDialog loadingDialog;

    public UpdateApkUtil(Context context) {
        this.mContext = context;
        versionCheckUpdate(mContext);
    }

    public UpdateApkUtil(Context context, boolean toast) {
        this.mContext = context;
        this.isToast = toast;
        versionCheckUpdate(mContext);
        if (toast) {
            showLoading();
        }
    }

    public UpdateApkUtil(Context context, int hour) {
        this.mContext = context;
        String lastTime = (String) SharedPreferencesUtils.getSP(mContext, "appUpdate", "");
        if (CommonUtils.isBlank(lastTime)) {
            SharedPreferencesUtils.setSP(mContext, "appUpdate", CommonUtils.genTimeStamp() + "");
            versionCheckUpdate(mContext);
        } else {
            Long time = CommonUtils.genTimeStamp();
            int i = (int) (time - Long.valueOf(lastTime) / (1000 * 60 * 60 * hour));
            if (i > 1) {
                versionCheckUpdate(mContext);
                SharedPreferencesUtils.setSP(mContext, "appUpdate", CommonUtils.genTimeStamp() + "");
            }
        }
    }


    public void showDialog(CheckUpdateBean updateBean) {
        mDialog = new Dialog(mContext);
        mDialog.setContentView(R.layout.dialog_updata);
        ButterKnife.bind(this, mDialog);
        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 宽度设置为屏幕的0.8
        mDialog.getWindow().setAttributes(lp);
        mDialog.getWindow().setGravity(Gravity.CENTER);
        mDialog.setCanceledOnTouchOutside(false);
        if (updateBean.getConstraint().contains("true")) {
            mLlLayout.setWeightSum(1);
            mDialog.setCancelable(false);
            mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                    if (keyCode == KeyEvent.KEYCODE_SEARCH) {
                        return true;
                    }
                    return false;
                }
            });
            mViewLine.setVisibility(View.GONE);
            mTvCancel.setVisibility(View.GONE);
        }
        initView(updateBean);

    }


    private void initView(CheckUpdateBean mCheekUpdateBean) {
        mTvApp.setText("瑜伽学院 " + mCheekUpdateBean.getNewVersion());
        mTvUpdataLog.setText(mCheekUpdateBean.getContent());
        mTvUpdataTime.setText(
                CommonUtils.getDateStringByTimeSTamp((long) mCheekUpdateBean.getUpdateTime(), "yyyy:MM:dd"));
        mTvAppVersion.setText(mCheekUpdateBean.getNewVersion());
        getVersion = Integer.parseInt(mCheekUpdateBean.getNewVersionCode());
        currentVersion = AppUtils.getAppVersionCode(mContext, mContext.getPackageName());
        mTvFileSize.setText("");
        int s = getVersion - currentVersion;
        Logcat.i("版本差:" + s + "\n当前版本：" + currentVersion + "\n最新版本：" + getVersion);
        if (isToast == true) {
        }
        if (getVersion > currentVersion) {
            mDialog.show();
        } else {
            if (isToast == true) {
                ToastUtil.showToast("当前版本是最新版本");
            }
        }
    }

    @OnClick({R.id.tv_cancel, R.id.tv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                mDialog.dismiss();
                break;
            case R.id.tv_sure:
                mDialog.dismiss();
                showDownloadDialog();
                break;
        }
    }

    private void showDownloadDialog() {
        mDownloadDialog = new Dialog(mContext);
        mDownloadDialog.setContentView(R.layout.dialog_updata_progress);
        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 宽度设置为屏幕的0.8
        mDialog.getWindow().setAttributes(lp);
        mDialog.getWindow().setGravity(Gravity.CENTER);
        mSeekBar = (SeekBar) mDownloadDialog.findViewById(R.id.sb_bar);
        mSeekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        downloadApkFile();
    }

    /**
     * 全量更新
     */
    private void downloadApkFile() {
        OkHttpUtils.get().url(updateBean.getDownloadApkUri()).build().execute(new FileCallBack(Constants.APK_STORE_FOLDER, mContext.getPackageName() + updateBean.getNewVersionCode() + ".apk") {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(mContext, "下载失败，请重试", Toast.LENGTH_SHORT).show();
                mDownloadDialog.dismiss();
                if (mDialog != null)
                    mDialog.show();
            }

            @Override
            public void inProgress(float progress, long total, int id) {
                super.inProgress(progress, total, id);
                Logcat.i("--------------" + progress + "/" + total);
                if (mDialog != null)
                    mDialog.dismiss();
                mDownloadDialog.show();
                mSeekBar.setProgress((int) (100 * progress));

            }

            @Override
            public void onResponse(File response, int id) {
                Logcat.i("file download ：" + response.getName() + "/" + response.getPath());
                if (response != null) {
                    mDownloadDialog.dismiss();
                    Util.installApk(mContext, response.getPath());
                }
            }
        });


//        new AsyncTask<Void, Integer, Void>() {
//
//            @Override
//            protected void onPreExecute() {
//                mDownloadDialog.show();
//            }
//
//            @Override
//            protected void onProgressUpdate(Integer[] values) {
//                int progress = values[0];
//                if (progress == -1) {
//                    Toast.makeText(mContext, "网络异常或者手机储存空间不够", Toast.LENGTH_SHORT).show();
//                    mDownloadDialog.dismiss();
//                    if (mDialog != null)
//                        mDialog.show();
//                } else if (progress == 100) {
//                    mSeekBar.setProgress(progress);
//                    mDownloadDialog.dismiss();
//                    // TODO Auto-generated method stub
//                    File file = new File(Constants.APK_STORE_FOLDER + mContext.getPackageName() + updateBean.getNewVersionCode() + ".apk");
//                    if (!file.exists()) {
//                        return;
//                    }
//                    mDownloadDialog.dismiss();
//                    Util.installApk(mContext, file.getPath());
//                } else {
//                    mSeekBar.setProgress(progress);
////                    progressDialog.show();
//                }
//            }
//
//            @Override
//            protected Void doInBackground(Void... arg0) {
//                try {
//                    URL url = new URL(updateBean.getDownloadApkUri());
//                    HttpURLConnection conn = (HttpURLConnection) url
//                            .openConnection();
//                    conn.connect();
//                    int length = conn.getContentLength();
//                    InputStream inputStream = conn.getInputStream();
//                    File file = new File(Constants.APK_STORE_FOLDER);
//                    if (!file.exists()) {
//                        file.mkdir();
//                    }
//                    File ApkFile = new File(Constants.APK_STORE_FOLDER
//                            + mContext.getPackageName() + updateBean.getNewVersionCode() + ".apk");
//                    FileOutputStream fileOutputStream = new FileOutputStream(ApkFile);
//                    int count = 0;
//                    byte buffer[] = new byte[1024];
//                    while (true) {
//                        int data = inputStream.read(buffer);
//                        count += data;
//                        int progress = (int) (((float) count / length) * 100);
//                        if (progress < 100 && progress > 0)
//                            publishProgress(progress);
//                        if (data <= 0) {
//                            // 下载完成通知安装
//                            publishProgress(100);
//                            break;
//                        }
//                        fileOutputStream.write(buffer, 0, data);
//                    }
//
//                    fileOutputStream.close();
//                    inputStream.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    publishProgress(-1);
//                }
//                return null;
//            }
//
//        }.execute();
    }

    /**
     * * 版本更新
     */
    public void versionCheckUpdate(final Context mContext) {
        Map<String, String> map = new HashMap<>();
        map.put("code", AppUtils.getAppVersionCode(mContext, mContext.getPackageName()) + "");
        map.put("type", "2");
        map.put("appType", "2");
        HttpClient.post("https://platform.hq-xl.com/mall/Index_group/versionsUpdate", map, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                dismissLaoding();
                Logcat.i("更新：" + statusCode + "/" + content);
                try {
                    JSONObject jsonObject = new JSONObject(content);
                    String jsonBean = jsonObject.getString("data");
                    if (jsonObject.getString("code").equals("1") && !StringUtil.isEmpty(jsonBean) && !jsonBean.equals("[]")) {
                        updateBean = JSON.parseObject(jsonBean, CheckUpdateBean.class);
                        if (updateBean != null) {
                            showDialog(updateBean);
                        } else {
                            if (isToast == true) {
                                ToastUtil.showToast("当前版本是最新版本");
                            }
                            Logcat.e("获取更新失败" + jsonObject.getString("msg"));
                        }
                    } else {
                        if (isToast == true) {
                            ToastUtil.showToast("当前版本是最新版本");
                        }
                        Logcat.e("获取更新失败" + jsonObject.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                dismissLaoding();
            }
        });
    }

    void showLoading() {
        loadingDialog = new LoadingDialog(mContext, R.style.loading_dialog);
        loadingDialog.show();
    }

    void dismissLaoding() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }
}
