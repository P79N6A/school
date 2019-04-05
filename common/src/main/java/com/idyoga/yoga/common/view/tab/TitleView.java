package com.idyoga.yoga.common.view.tab;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;
/**
 * 文 件 名: TitleView
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 创建日期: 2018/3/19
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class TitleView  extends FrameLayout {

    private GestureDetector gestureDetector;
    private DoubleSingleClickListener mDoubleSingleClickListener;

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        gestureDetector = new GestureDetector(getContext(), new MyGestureDetector());
        setClickable(true);
    }

    private final class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            if (mDoubleSingleClickListener != null) {
                mDoubleSingleClickListener.onDoubleTap(e);
            }
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            if (mDoubleSingleClickListener != null) {
                mDoubleSingleClickListener.onSingleTapConfirmed(e);
            }
            return super.onSingleTapConfirmed(e);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public interface DoubleSingleClickListener {
        void onDoubleTap(MotionEvent e);

        void onSingleTapConfirmed(MotionEvent e);
    }

    public void setDoubleSingleClickListener(DoubleSingleClickListener mDoubleSingleClickListener) {
        this.mDoubleSingleClickListener = mDoubleSingleClickListener;
    }
}