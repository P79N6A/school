package com.idyoga.yoga.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.idyoga.yoga.comm.AppContext;

/**
 * 作者 by K
 * 时间：on 2017/8/21 14:09
 * 邮箱 by  vip@devkit.vip
 * <p/>
 * 类用途：
 * 最后修改：
 */
public class ThreadUtils {
    /**
     * 在主线程中运行的线程
     */
    public static void runOnUiThread(Runnable action) {
        AppContext.mHandler.post(action);
    }

    public static void runOnChildThread(Runnable action) {
        new Thread(action).start();
    }
    private static Executor executor = Executors.newSingleThreadExecutor();
    private static Handler handler = new Handler(Looper.getMainLooper());

    public static void executeSubThread(Runnable runnable) {
        executor.execute(runnable);
    }

    public static void executeMainThread(Runnable runnable) {
        handler.post(runnable);
    }

}
