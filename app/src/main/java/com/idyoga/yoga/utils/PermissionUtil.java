package com.idyoga.yoga.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;


import java.util.ArrayList;
import java.util.List;

import vip.devkit.library.Logcat;

/**
 * 文 件 名: PermissionUtil
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/7
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class PermissionUtil {
    /**
     * 需要申请的权限
     */
    public static String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,//内存读写
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
    };

    /**
     * @param context
     * @return 检测权限
     */
    public static String[] checkPermission(Context context) {
        List<String> data = new ArrayList<>();//存储未申请的权限
        for (String permission : permissions) {
            int checkSelfPermission = ContextCompat.checkSelfPermission(context, permission);
            if (checkSelfPermission == PackageManager.PERMISSION_DENIED) {//未申请
                data.add(permission);
            }
        }
        return data.toArray(new String[data.size()]);
    }
    /**
     * @param context
     * @return 检测权限
     */
    public static String[] checkPermission(Context context,String[] permissions ) {
        List<String> data = new ArrayList<>();//存储未申请的权限
        for (String permission : permissions) {
            int checkSelfPermission = ContextCompat.checkSelfPermission(context, permission);
            if (checkSelfPermission == PackageManager.PERMISSION_DENIED) {//未申请
                data.add(permission);
            }
        }
        return data.toArray(new String[data.size()]);
    }
    /**
     * @param context
     * @return 检测权限
     */
    public static boolean checkPermission(Context context,String permissions ) {
        List<String> data = new ArrayList<>();//存储未申请的权限
            int checkSelfPermission = ContextCompat.checkSelfPermission(context, permissions);
            if (checkSelfPermission == PackageManager.PERMISSION_DENIED) {//未申请
                return false;
            }else {
                return true;
            }
    }

    /**
     * 初始化权限事件
     */
    public static void initPermission(Activity activity) {
        String[] permissions = checkPermission(activity);
        for (int i = 0; i < permissions.length; i++) {
            Logcat.e("you申请权限:" + permissions.length + "/" + permissions[i]);
        }
        if (permissions.length == 0) {
            Logcat.e("go ...");
        } else {
            Logcat.e("需要申请权限");
            ActivityCompat.requestPermissions(activity, permissions, 100);
        }
    }

    public void checkPermissionM(String Permission) {
        if (Build.VERSION.SDK_INT <Build.VERSION_CODES.M) {

        }
    }







    /**
     * 返回true 表示可以使用  返回false表示不可以使用
     */
    public boolean cameraIsCanUse() {
        boolean isCanUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
            Camera.Parameters mParameters = mCamera.getParameters(); //针对魅族手机
            mCamera.setParameters(mParameters);
        } catch (Exception e) {
            isCanUse = false;
        }

        if (mCamera != null) {
            try {
                mCamera.release();
            } catch (Exception e) {
                e.printStackTrace();
                return isCanUse;
            }
        }
        return isCanUse;
    }


}
