package com.idyoga.yoga.common.http.type2.presenter.impl;

import android.content.Context;


import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.interactor.ICommonRequestInteractor;
import com.idyoga.yoga.common.http.type2.interactor.impl.CommonRequestInteractorImpl;
import com.idyoga.yoga.common.http.type2.presenter.ICommonRequestPresenter;
import com.idyoga.yoga.common.http.type2.listeners.IRequestListener;


import java.util.Map;


public class CommonRequestPresenterImpl implements ICommonRequestPresenter, IRequestListener {

    public Context context;
    public ICommonViewUi iCommonViewUi;
    public ICommonRequestInteractor iCommonRequestInteractor;

    public CommonRequestPresenterImpl(Context context, ICommonViewUi iCommonViewUi) {
        this.context = context;
        this.iCommonViewUi = iCommonViewUi;
        iCommonRequestInteractor = new CommonRequestInteractorImpl(this);
    }

    @Override
    public void request(int eventTag, Context context, String url, Map<String, String> params) {
        iCommonRequestInteractor.request(eventTag, context, url, params);
    }

    @Override
    public void requestGet(int eventTag, Context context, String url, Map<String, String> params) {
        iCommonRequestInteractor.requestGet(eventTag, context, url, params);
    }


    @Override
    public void onSuccess(int eventTag, String data) {
        iCommonViewUi.getRequestData(eventTag, data);
//        if (HttpStatusUtil.getStatus(data) || eventTag < 0) {
//            iCommonViewUi.getRequestData(eventTag, data);
//        } else {
//            if (HttpStatusUtil.isRelogin(data)) {
//                try {
//                    JSONObject object = new JSONObject(data);
//                    String msg = object.getString("msg");
//                    CommonToast.makeText(context, msg,1);
//                } catch (Exception e) {
//
//                }
////                LoginMsgHelper.exitLogin(context);
////                LoginMsgHelper.reLogin(context); // 重启到登录页面
////                EventBus.getDefault().post(new PostResult(EventBusTags.LOGOUT));
//            }  else {
////                iCommonViewUi.onRequestSuccessException(eventTag, HttpStatusUtil.getStatusMsg(data));
//                iCommonViewUi.onRequestSuccessException(eventTag, HttpStatusUtil.isShowToastStr(eventTag, data));
//            }
//        }
    }

    @Override
    public void onError(int eventTag, String msg) {
        iCommonViewUi.onRequestFailureException(eventTag, msg);
    }

}
