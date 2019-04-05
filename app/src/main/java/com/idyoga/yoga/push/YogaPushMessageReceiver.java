package com.idyoga.yoga.push;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.baidu.android.pushservice.PushManager;
import com.baidu.android.pushservice.PushMessageReceiver;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.utils.JsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;
import vip.devkit.library.Logcat;

/**
 * 文 件 名: YogaPushMessageReceiver
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/4/26
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 * <p>
 * Push消息处理receiver。请编写您需要的回调函数， 一般来说： onBind是必须的，用来处理startWork返回值；
 * onMessage用来接收透传消息； onSetTags、onDelTags、onListTags是tag相关操作的回调；
 * onNotificationClicked在通知被点击时回调； onUnbind是stopWork接口的返回值回调
 * 返回值中的errorCode，解释如下：
 * 0 - Success
 * 10001 - Network Problem
 * 10101  Integrate Check Error
 * 30600 - Internal Server Error
 * 30601 - Method Not Allowed
 * 30602 - Request Params Not Valid
 * 30603 - Authentication Failed
 * 30604 - Quota Use Up Payment Required
 * 30605 -Data Required Not Found
 * 30606 - Request Time Expires Timeout
 * 30607 - Channel Token Timeout
 * 30608 - Bind Relation Not Found
 * 30609 - Bind Number Too Many
 * 当您遇到以上返回错误时，如果解释不了您的问题，请用同一请求的返回值requestId和errorCode联系我们追查问题。
 */


public class YogaPushMessageReceiver extends PushMessageReceiver {

    private int type = 0;

    /**
     * 调用PushManager.startWork后，sdk将对push
     * server发起绑定请求，这个过程是异步的。绑定请求的结果通过onBind返回。 如果您需要用单播推送，需要把这里获取的channel
     * id和user id上传到应用server中，再调用server接口用channel id和user id给单个手机或者用户推送。
     *
     * @param context   BroadcastReceiver的执行Context
     * @param errorCode 绑定接口返回值，0 - 成功
     * @param appid     应用id。errorCode非0时为null
     * @param userId    应用user id。errorCode非0时为null
     * @param channelId 应用channel id。errorCode非0时为null
     * @param requestId 向服务端发起的请求id。在追查问题时有用；
     * @return none
     */
    @Override
    public void onBind(Context context, int errorCode, String appid, String userId, String channelId, String requestId) {
//        Logcat.i(" \nerrorCode:" + errorCode + " \nappId:" + appid + " \nuserId:" + userId + " \nchannelId:" + channelId + " \nrequestId:" + requestId + "/\n" + 1, "pushBindDevice", false);
//        if (errorCode == 0 && !PrefUtils.getBoolean(context, "pushBindDevice", false)) {
//            bindDevice(context, userId, channelId);
//        }
    }

    @Override
    public void onUnbind(Context context, int i, String s) {
        Logcat.i("onUnbind:" + i + "/" + s);


    }

    @Override
    public void onSetTags(Context context, int i, List<String> list, List<String> list1, String s) {
        Logcat.i("onSetTags:" + i + "/" + list.toString() + "/" + list1.toString() + "/" + list.size() + "/" + list1.size());
//        list1.add(PrefUtils.getShopId(context));
        Logcat.i("setTags:" + list.toString() + "/" + list1.toString());
    }


    @Override
    public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {
        Logcat.i("onDelTags:" + i + "/" + list.size() + "/" + list1.size() + "/" + list.toString() + "/" + list1.toString());
        List<String> tagList = new ArrayList<>();
        PushManager.delTags(context, tagList);
    }

    @Override
    public void onListTags(Context context, int i, List<String> list, String s) {
        Logcat.i("onListTags:" + i + "/" + s + "/" + list.toString() + "/" + list.size() + "/" + s);
    }

    @Override
    public void onMessage(Context context, String s, String s1) {
        Logcat.i("onMessage:" + "/" + s + "/" + "/" + s1);
        type = Integer.valueOf(JsonUtil.parserSingleData(s, "type"));
        toNext(context, type, "");
    }

    @Override
    public void onNotificationClicked(Context context, String s, String s1, String s2) {
        Logcat.i("onNotificationClicked:" + "/" + s + "/" + s1 + "/" + s2);
        String json = s2.replace("\\\"", "\"")
                .replace("\"{", "{")
                .replace("}\"", "}");
        type = Integer.valueOf(JsonUtil.parserSingleData(s2, "type"));
        Logcat.i("onNotificationClicked:" + type + "/" + json);
        toNext(context, type, json);
    }

    /**
     * 接收通知到达的函数。
     *
     * @param context             上下文
     * @param title               推送的通知的标题
     * @param description         推送的通知的描述
     * @param customContentString 自定义内容，为空或者json字符串
     */
    @Override
    public void onNotificationArrived(Context context, String title, String description, String customContentString) {
        Logcat.i("onNotificationArrived:" + "/" + title + "/" + description + "/" + customContentString);
//        EventBus.getDefault().post(new PostResult("push",title));

    }


    /**
     * 推送操作
     *
     * @param context
     * @param type
     * @param json
     */
    private void toNext(Context context, int type, String json) {
        Bundle mBundle;
        Intent intent = new Intent();
        switch (type) {
            case 1:
            case 9:
//                EventBus.getDefault().post(new PostResult("signIn"));
                Logcat.i("发送通知");
                break;
        }
    }

    /**
     * 前后端绑定设备
     *
     * @param context
     * @param deviceId
     * @param channelId
     */
    private void bindDevice(final Context context, String deviceId, String channelId) {
        Map map = new HashMap();
        map.put("uuid", "");
        map.put("userId", "");
        map.put("deviceId", deviceId);
        map.put("channelId ", channelId);
        Logcat.i("提交的推送绑定：" + map.toString());
//        HttpHelper.getInstance().post(context, "", map, new OkHttpResponseHandler<String>(context) {
//            @Override
//            public void onResponse(Request request, String json) {
//                super.onResponse(request, json);
//                ResultBean2 resultBean =JSON.parseObject(json,ResultBean2.class);
//                if (resultBean.getCode().equals("1")){
//                    PrefUtils.putBoolean(context, "pushBindDevice", true);
//                }
//            }
//        });
    }
}
