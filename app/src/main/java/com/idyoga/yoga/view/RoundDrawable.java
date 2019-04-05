package com.idyoga.yoga.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author weilgu
 * @time 2019/3/29 13:42
 * @des ${TODO}
 */

public class RoundDrawable extends Drawable {

    private Paint mPaint;
    private float mRadius;
    private float mAlpha;
    private RectF mRectF;

    public RoundDrawable(float radius, float alpha) {
        mRadius = radius;
        mAlpha = alpha;
        mRectF = new RectF();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setColor(Color.WHITE);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawRoundRect(mRectF, mRadius, mRadius, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void setBounds(@NonNull Rect bounds) {
        super.setBounds(bounds);
        mRectF.set(bounds);
    }

    @Override
    public void getOutline(@NonNull Outline outline) {
        super.getOutline(outline);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            outline.setRoundRect(getBounds(), mRadius);
            outline.setAlpha(mAlpha);
        }
    }
}
