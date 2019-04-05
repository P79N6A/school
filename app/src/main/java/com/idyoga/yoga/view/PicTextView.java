package com.idyoga.yoga.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.idyoga.yoga.R;
import com.idyoga.yoga.utils.ViewUtil;

/**
 * @author weilgu
 * @time 2019/2/28 16:32
 * @des 显示有原价 和现在的价格的textview
 */

public class PicTextView extends AppCompatTextView {

    private Paint mMessagePaint;
    private Paint mNewPicPaint;
    private Paint mOldPicPaint;
    private Paint mSymbol;
    private float mMessage_text_size;
    private float mNew_pic_text_size;
    private float mOld_pic_text_size;
    private int mMessage_text_color;
    private int mNew_pic_text_color;
    private int mOld_pic_text_color;

    public PicTextView(Context context) {
        this(context,null);
    }

    public PicTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mMessagePaint = new Paint();
        mNewPicPaint = new Paint();
        mOldPicPaint = new Paint();
        mSymbol = new Paint(); //符号
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PicTextView);
        mMessage_text_size = typedArray.getDimension(R.styleable.PicTextView_message_text_size, ViewUtil.sp2px(context, 13));
        mNew_pic_text_size = typedArray.getDimension(R.styleable.PicTextView_new_pic_text_size, ViewUtil.sp2px(context, 13));
        mOld_pic_text_size = typedArray.getDimension(R.styleable.PicTextView_old_pic_text_size, ViewUtil.sp2px(context, 11));
        mMessage_text_color = typedArray.getColor(R.styleable.PicTextView_message_text_color, Color.parseColor("#333333"));
        mNew_pic_text_color = typedArray.getColor(R.styleable.PicTextView_new_pic_text_color, Color.parseColor("#E23C3D"));
        mOld_pic_text_color = typedArray.getColor(R.styleable.PicTextView_old_pic_text_color, Color.parseColor("#999999"));
        mMessagePaint.setColor(mMessage_text_color);
        mMessagePaint.setTextSize(mMessage_text_size);
        mNewPicPaint.setColor(mNew_pic_text_color);
        mNewPicPaint.setTextSize(mNew_pic_text_size);
        mSymbol.setColor(mNew_pic_text_color);
        mSymbol.setTextSize(ViewUtil.sp2px(context,11));
        mOldPicPaint.setColor(mOld_pic_text_color);
        mOldPicPaint.setTextSize(mOld_pic_text_size);
    }

    public PicTextView setMessage_text_size(float message_text_size) {
        mMessage_text_size = message_text_size;
        return this;
    }

    public PicTextView setNew_pic_text_size(float new_pic_text_size) {
        mNew_pic_text_size = new_pic_text_size;
        return this;
    }

    public PicTextView setOld_pic_text_size(float old_pic_text_size) {
        mOld_pic_text_size = old_pic_text_size;
        return this;
    }

    public PicTextView setMessage_text_color(int message_text_color) {
        mMessage_text_color = message_text_color;
        return this;
    }

    public PicTextView setNew_pic_text_color(int new_pic_text_color) {
        mNew_pic_text_color = new_pic_text_color;
        return this;
    }

    public PicTextView setOld_pic_text_color(int old_pic_text_color) {
        mOld_pic_text_color = old_pic_text_color;
        return this;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawText();
    }
}
