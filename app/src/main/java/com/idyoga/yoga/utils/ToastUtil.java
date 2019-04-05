package com.idyoga.yoga.utils;

import android.widget.Toast;

import com.idyoga.yoga.comm.AppContext;

/**
 * 作者 by K
 * 时间：on 2017/8/28 16:30
 * 邮箱 by  vip@devkit.vip
 * <p/>
 * 类用途：
 * 最后修改：
 */
public class ToastUtil {
    private static Toast toast;
    /**
     * 强大的吐司，能够连续弹的吐司
     * @param text
     */
    public static void showToast(String text){
        if(toast==null){
            toast = Toast.makeText(AppContext.getInstance(), text, Toast.LENGTH_SHORT);
        }else {
            toast.setText(text);//如果不为空，则直接改变当前toast的文本
        }
        toast.show();
    }


}
