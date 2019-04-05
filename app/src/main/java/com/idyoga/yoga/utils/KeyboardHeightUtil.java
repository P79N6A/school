package com.idyoga.yoga.utils;

import android.graphics.Rect;
import android.view.View;

/**
 * Created by Administrator on 2019/01/17.
 * 全屏或者沉浸式时,布局随软键盘弹出上移 防止软键盘弹出遮挡控件
 */

public class KeyboardHeightUtil {

    private static KeyboardHeightUtil mInstance;

    public static KeyboardHeightUtil getInstance(){
        if (mInstance == null){
            mInstance = new KeyboardHeightUtil();
        }
        return mInstance;
    }

    /**
     * @param rootView 跟布局view对象
     * @param btnView 布局中最下面一个view对象
     *
     * 使用注意,view必须attach到window上,否则获取的可见视图大小可能不准确
     * 如不能在activity/fragment/dialog的onCreate方法中使用
     */
    public void getVisibleWindowHeight(int rootHeight,View rootView, View btnView) {
        //获取可视窗口的大小
        Rect rect = new Rect();
        rootView.getWindowVisibleDisplayFrame(rect);
        //获取跟布局的高度
//        int screenHeight = rootView.getRootView().getHeight();
        int screenHeight = rootHeight;
        int mainInvisibleHeight = Math.abs(screenHeight - rect.bottom);
        /*if (KeybordS.isSoftInputShow(activity)){
            //获取view相对于父空间的坐标(左上角的坐标)
            int[] location = new int[2];
            btnView.getLocationInWindow(location);
            //控件相对于父控件的右下角的坐标,y轴
            int rightBtnY = location[1] + btnView.getHeight();
            int srollHeight = rightBtnY - rect.bottom;
            rootView.scrollTo(0,srollHeight);
        } else {
            rootView.scrollTo(0, 0);
        }*/
        if (mainInvisibleHeight > screenHeight / 5) {
            //获取view相对于父空间的坐标(左上角的坐标)
            int[] location = new int[2];
            btnView.getLocationInWindow(location);
            //控件相对于父控件的右下角的坐标,y轴
            int rightBtnY = location[1] + btnView.getHeight();
            int srollHeight = rightBtnY - rect.bottom;
            rootView.scrollTo(0,srollHeight);
        } else {
            rootView.scrollTo(0, 0);
        }
    }
}
