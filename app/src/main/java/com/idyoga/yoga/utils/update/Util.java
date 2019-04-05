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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;

import vip.devkit.library.Logcat;

/**
 * 文 件 名: Util
 * 创 建 人: By k
 * 创建日期: 2018/3/29 22:43
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class Util {
    public static final boolean DEBUG = true;
    private static final String TAG = Util.class.getSimpleName();

    private static String bytes2Hex(byte[] src) {
        char[] res = new char[src.length * 2];
        final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        for (int i = 0, j = 0; i < src.length; i++) {
            res[j++] = hexDigits[src[i] >>> 4 & 0x0f];
            res[j++] = hexDigits[src[i] & 0x0f];
        }

        return new String(res);
    }

    private static String getMd5ByFile(File file) {
        String value = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);

            MessageDigest digester = MessageDigest.getInstance("MD5");
            byte[] bytes = new byte[8192];
            int byteCount;
            while ((byteCount = in.read(bytes)) > 0) {
                digester.update(bytes, 0, byteCount);
            }
            value = bytes2Hex(digester.digest());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    /**
     * 判断文件的MD5是否为指定值
     *
     * @param file
     * @param md5
     * @return
     */
    public static boolean checkMd5(File file, String md5) {
        if (TextUtils.isEmpty(md5)) {
            throw new RuntimeException("md5 cannot be empty");
        }
        String fileMd5 = getMd5ByFile(file);
        if (DEBUG) {
            Log.d(TAG, String.format("file's md5=%s, real md5=%s", fileMd5, md5));
        }
        if (md5.equals(fileMd5)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断文件的MD5是否为指定值
     *
     * @param filePath
     * @param md5
     * @return
     */
    public static boolean checkMd5(String filePath, String md5) {
        return checkMd5(new File(filePath), md5);
    }


    /**
     * 获取已安装apk的PackageInfo
     *
     * @param context
     * @param packageName
     * @return
     */
    public static PackageInfo getInstalledApkPackageInfo(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> apps = pm.getInstalledPackages(PackageManager.GET_SIGNATURES);

        Iterator<PackageInfo> it = apps.iterator();
        while (it.hasNext()) {
            PackageInfo packageinfo = it.next();
            String thisName = packageinfo.packageName;
            if (thisName.equals(packageName)) {
                return packageinfo;
            }
        }

        return null;
    }

    /**
     * 判断apk是否已安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isInstalled(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return installed;
    }

    /**
     * 获取已安装Apk文件的源Apk文件
     * 如：/data/app/com.sh.app.apk
     *
     * @param context
     * @param packageName
     * @return
     */
    public static String getSourceApkPath(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName))
            return null;

        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
            return appInfo.sourceDir;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 安装Apk
     *
     * @param context
     * @param apkPath
     */
    public static void installApk(Context context, String apkPath) {
        File file = new File(apkPath);
        if (!file.exists()) {
            return;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
        } else {
            uri = Uri.fromFile(file);
        }
        intent.setDataAndType(uri,
                "application/vnd.android.package-archive");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Logcat.e("8.0");
        }
        context.startActivity(intent);
        ((Activity) context).finish();


    }
/*
    *//**
     * 合并apk文件
     *
     * @param
     *//*
    public static void mergeApk(final Context mContext, final String patchPath) {
        try {
            PackageManager pm = mContext.getPackageManager();
            ApplicationInfo appInfo = pm.getApplicationInfo(mContext.getPackageName(), 0);
            final String oldPath = appInfo.sourceDir;//旧版本路径
            final File newApkFile = new File(Contants.APK_STORE_FOLDER + mContext.getPackageName() + "_new.apk");
            final File patchFile = new File(patchPath);
            if (!patchFile.exists()) {
                showToast(mContext, "请将差分包" + mContext.getPackageName() + ".patch保存到sdcard");
                return;
            }
            if (oldPath.isEmpty()) {
                showToast(mContext, "APP 老版本不存在");
                return;
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int result = JniApi.bspatch(oldPath, newApkFile.getAbsolutePath(), patchFile.getAbsolutePath());
                    Log.i("result:", "合并返回码：" + result);
                    if (result == 0) {

                        showToast(mContext, "增量升级成功，即将安装");
                        installApk(mContext, newApkFile.getAbsolutePath());
                    } else {
                        showToast(mContext, "合并失败");
                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}

