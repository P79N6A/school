package com.idyoga.yoga.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 文 件 名: CustomScrollView
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/7
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class CustomScrollView extends ScrollView {

    private OnScrollChangeListener mOnScrollChangeListener;

    /**
     * 设置滚动接口
     *
     * @param
     */

    public void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        mOnScrollChangeListener = onScrollChangeListener;
    }

    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 定义一个滚动接口
     */

    public interface OnScrollChangeListener {
        void onScrollChanged(CustomScrollView scrollView, int l, int t, int oldl, int oldt);
    }

    /**
     * 当scrollView滑动时系统会调用该方法,并将该回调放过中的参数传递到自定义接口的回调方法中,
     * 达到scrollView滑动监听的效果
     */
    @Override
    protected void onScrollChanged(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        super.onScrollChanged(scrollX, scrollY, oldScrollX, oldScrollY);
        if (mOnScrollChangeListener != null) {
            mOnScrollChangeListener.onScrollChanged(this, scrollX, scrollY, oldScrollX, oldScrollY);

        }
    }

}
