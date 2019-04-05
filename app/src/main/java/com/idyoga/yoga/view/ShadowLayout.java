package com.idyoga.yoga.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;

import com.idyoga.yoga.R;

/**
 * @author weilgu
 * @time 2019/3/29 13:38
 * @des ${TODO}
 */

public class ShadowLayout extends FrameLayout {
    private RectF mRectF;
    private Path mPath;
    private Paint mPaint;
    private float mRadius;
    private float mAlpha;

    public ShadowLayout(@NonNull Context context) {
        this(context,null);
    }

    public ShadowLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        final TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ShadowLayout, 0, 0);
        mRadius = a.getDimensionPixelSize(R.styleable.ShadowLayout_shadow_radius, 0);
        mAlpha = a.getFloat(R.styleable.ShadowLayout_shadow_alpha, 1f);
        if (mAlpha < 0) {
            mAlpha = 0;
        } else if (mAlpha > 1f) {
            mAlpha = 1f;
        }
        a.recycle();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        mRectF = new RectF();
        mPath = new Path();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mRectF.set(0, 0, getWidth(), getHeight());
        mPath.addRect(mRectF, Path.Direction.CCW);
        mPath.addRoundRect(mRectF, mRadius, mRadius, Path.Direction.CW);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        final int layerId = canvas.saveLayer(mRectF, null, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
        canvas.drawPath(mPath, mPaint);
        canvas.restoreToCount(layerId);
    }

    @Override
    public void setBackground(Drawable background) {
        if (background instanceof RoundDrawable) {
            super.setBackground(background);
        } else {
            super.setBackground(new RoundDrawable(mRadius, mAlpha));
        }
    }
}
