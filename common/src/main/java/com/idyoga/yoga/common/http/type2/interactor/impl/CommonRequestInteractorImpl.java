package com.idyoga.yoga.common.http.type2.interactor.impl;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;


import com.idyoga.yoga.common.http.type2.HttpHelper;
import com.idyoga.yoga.common.http.type2.OkHttpResponseHandler;
import com.idyoga.yoga.common.http.type2.interactor.ICommonRequestInteractor;
import com.idyoga.yoga.common.http.type2.listeners.IRequestListener;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Map;

import okhttp3.Request;
import vip.devkit.library.Logcat;

public class CommonRequestInteractorImpl implements ICommonRequestInteractor {

    public IRequestListener iRequestListener;

    public CommonRequestInteractorImpl(IRequestListener iRequestListener) {
        this.iRequestListener = iRequestListener;
    }

    @Override
    public void request(final int eventTag, final Context context, final String url, Map<String, String> map) {


        HttpHelper.getInstance().post(context, url, map, new OkHttpResponseHandler<String>(context) {

            @Override
            public void onResponse(Request request, String json) {
                super.onResponse(request, json);

                System.out.println("请求结果》" + url + " ______" + "" + json);

                if (!isActivityEnd(context)) {
                    iRequestListener.onSuccess(eventTag, json);
                }
            }

            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);

                if (!isActivityEnd(context)) {
                    iRequestListener.onError(eventTag, e.getMessage());
                }
            }

            @Override
            public void onBefore() {
                super.onBefore();

            }

            @Override
            public void onAfter() {
                super.onAfter();
            }
        });
    }


    @Override
    public void requestGet(final int eventTag, final Context context, final String url, Map<String, String> map) {


        HttpHelper.getInstance().get(context, url, map, new OkHttpResponseHandler<String>(context) {

            @Override
            public void onResponse(Request request, String json) {
                super.onResponse(request, json);

                Logcat.i("请求结果》" + url + "______" + "" + json);
                if (!isActivityEnd(context)) {
                    iRequestListener.onSuccess(eventTag, json);
                }
            }

            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);

                if (!isActivityEnd(context)) {
                    iRequestListener.onError(eventTag, e.getMessage());
                }
            }

            @Override
            public void onBefore() {
                super.onBefore();

            }

            @Override
            public void onAfter() {
                super.onAfter();
            }
        });
    }


    /**
     * 根据context判断activity是否已经结束
     *
     * @param context
     * @return
     */
    public boolean isActivityEnd(final Context context) {
        Activity activity ;
        if (context != null) {
            try {
                    activity = (Activity) context;
                    if (activity == null || activity.isFinishing()) {
                        System.out.println("context为null了");
                        return true;
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private Activity getActivity(Context mContext) {
        Context context = mContext;
        while (!(context instanceof Activity) && context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        Logcat.e("Unable to get Activity.");
        return null;
    }
}
