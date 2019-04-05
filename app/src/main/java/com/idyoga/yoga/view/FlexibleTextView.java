package com.idyoga.yoga.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.utils.SpannableHelper;

import vip.devkit.library.Logcat;

/**
 * @author weilgu
 * @time 2019/3/5 9:38
 * @des 可伸缩的TextView
 */

public class FlexibleTextView extends RelativeLayout implements View.OnClickListener {

    private TextView mTv;
    private ImageButton mBtn;
    //大于3行,显示展开的图标
    private int mMaxCollapsedLines = 3;
    private int mCopyMaxCollapsedLines ;
    //文本的高度
    private int mTextHeightWithMaxLines;
    private boolean mIsOpen = false;
    private int mMaxHeight;
    private String mContent;
    private String mReplace = "...                                         ";
    private int mMarginBetweenTxtAndBottom;
    private int mMinHeight;
    private int mCopyMinHeight;


    public FlexibleTextView(Context context) {
        this(context,null);
    }

    public FlexibleTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlexibleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AlignTextView);
        mMaxCollapsedLines = typedArray.getInteger(R.styleable.FlexibleTextView_flexible_max_line, 3);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        findView();
    }

    private void findView() {
        mCopyMaxCollapsedLines = mMaxCollapsedLines;
        mTv = (TextView) getChildAt(0);
        mContent = mTv.getText().toString();
        mBtn = (ImageButton) getChildAt(1);
        mBtn.setVisibility(GONE);
        mBtn.setOnClickListener(this);
    }

    //ImageButton 的点击事件
    @Override
    public void onClick(View v) {
        mIsOpen = mIsOpen ? false : true;
        ValueAnimator animator = null;
        if (!mIsOpen){
            requestLayout();
//            animator = ValueAnimator.ofInt(mTv.getLineCount(),mMaxLines);
            //startOpenAni();
        }else{
            requestLayout();
//            animator = ValueAnimator.ofInt(mTv.getLineCount(),mCopyMaxCollapsedLines);
            //startCloseAni();
        }
        /*Logcat.i("getLineCount() = " + mMaxCollapsedLines + ",mMaxLines = " + mMaxLines + ",mCopyMaxCollapsedLines = " + mCopyMaxCollapsedLines);
        animator.setDuration(3000);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Logcat.i("startCloseAni " + animation.getAnimatedValue());
                int lineNum  = (int) animation.getAnimatedValue();
                //mTv.setMaxHeight(height);
                mMaxCollapsedLines = lineNum;
                Logcat.i("mMaxCollapsedLines = " + mMaxCollapsedLines);
                //mTv.setHeight(mMinHeight);
                //mTv.getLayoutParams().height = height;
                //getLayoutParams().height = height;
                //requestLayout();
            }
        });*/
        /*animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                requestLayout();
            }
        });*/
    }

    private void startCloseAni(ValueAnimator animator) {
        animator.setDuration(1000);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Logcat.i("startCloseAni " + animation.getAnimatedValue());
                int height  = (int) animation.getAnimatedValue();
                getLayoutParams().height = height;
                mTv.getLayoutParams().height = height;
                requestLayout();
                //int height = (int) animation.getAnimatedValue();
//                mTv.setHeight(height);
//                requestLayout();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                requestLayout();
            }
        });
    }

    private void startOpenAni() {
        int viewHeight = getRealTextViewHeight(mTv);
        int minHeight = getRealTextViewMinHeight(mTv);
        ValueAnimator animator = ValueAnimator.ofInt(mMinHeight,mTextHeightWithMaxLines);
        animator.setDuration(300);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Logcat.i("startOpenAni " + animation.getAnimatedValue());
                int height  = (int) animation.getAnimatedValue();
                mTv.setHeight(height);
//                int height = (int) animation.getAnimatedValue();
//                mTv.setHeight(height);
//                requestLayout();
            }
        });

    }

    int i = 1;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mBtn.setVisibility(GONE);
        mTv.setMaxLines(Integer.MAX_VALUE);
        mTv.setText(mContent);
        /*mMaxHeight = mTv.getHeight();
        mMinHeight = getTextViewHeight(mMaxCollapsedLines);
        mCopyMinHeight = mMinHeight;*/
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mTv.getLineCount() <= mMaxCollapsedLines){
            return;
        }
        mTextHeightWithMaxLines = getRealTextViewHeight(mTv);
        //mMinHeight = getRealTextViewMinHeight(mTv);
        //mTv.setHeight(mMinHeight);
        if (!mIsOpen){
            mTv.setMaxLines(mCopyMaxCollapsedLines);
            Layout layout = mTv.getLayout();
            int lineStart = layout.getLineStart(mMaxCollapsedLines - 1);
            int lineEnd = layout.getLineEnd(mMaxCollapsedLines - 1);
            //第3行显示一半的内容,后面加...
            String substring = mContent.substring(0, (lineEnd + lineStart) / 2);
            String newContent = substring + "...";
            mTv.setText(newContent);
        }else{
            //如果最后一行是满行,或者最后一行的内容被图标遮挡,就换行
            boolean isFillLine = sendIsFillLine();
            if (!isFillLine){
                mTv.setText(mContent + "\n" + "");
            }
        }
        int lineHeight = mTv.getLineHeight();
        ViewGroup.LayoutParams lp = mBtn.getLayoutParams();
        lp.width = lineHeight;
        lp.height = lineHeight;
        mBtn.setLayoutParams(lp);
        mBtn.setVisibility(VISIBLE);
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);

    }

    //判断最后一行的内容是否会被图标遮挡
    private boolean sendIsFillLine() {
        TextPaint paint = mTv.getPaint();
        Layout layout = mTv.getLayout();
        String content = mTv.getText().toString();
        int lineCount = mTv.getLineCount();
        int lineHeight = mTv.getLineHeight();
        int endLineStart = layout.getLineStart(lineCount - 1);
        int endLineEnd = layout.getLineEnd(lineCount - 1);
        Rect rect = new Rect();
        paint.getTextBounds(content,endLineStart,endLineEnd,rect);
        //最后一行的内容宽度
        int endWidth = rect.width();
        int lineStart = layout.getLineStart(lineCount - 2);
        int lineEnd = layout.getLineEnd(lineCount - 2);
        Rect rect1 = new Rect();
        paint.getTextBounds(content,lineStart,lineEnd,rect1);
        //倒数第二行的内容宽度
        int width = rect1.width();
        if (width - endWidth > lineHeight){
            Logcat.i("========================================if");
            return true;
        }
        Logcat.i("========================================if out");
        return false;
    }

    private int ellipsize(TextPaint tp, CharSequence cs, int line, int lineWidth) {
        StaticLayout layout = new StaticLayout(cs, tp, lineWidth, Layout.Alignment.ALIGN_NORMAL,
                1.0f, 0.0f, true);
        int count = layout.getLineCount();
        int pos = -1;

        if (count > line) {
            int start = layout.getLineStart(line - 1);
            final int range[] = {0};
            TextUtils.ellipsize(cs.subSequence(start, cs.length()), tp, lineWidth,
                    TextUtils.TruncateAt.END, false, new TextUtils.EllipsizeCallback() {
                        @Override
                        public void ellipsized(int start, int end) {
                            range[0] = start;  // 单行文本缩略起始
                        }
                    });
            pos = start + range[0];
        }
        return pos;
    }

    private int getRealTextViewHeight(@Nullable TextView tv) {
        int textHeight = tv.getLayout().getLineTop(tv.getLineCount());
        Logcat.i("----------------textHeight-------------" + textHeight);
        int padding = tv.getCompoundPaddingTop() + tv.getCompoundPaddingBottom();
        Logcat.i("----------------getRealTextViewHeight-----padding--------" + padding);
        return textHeight + padding;
    }

    private int getRealTextViewMinHeight(@Nullable TextView tv){

        int minHeight = mTv.getLayout().getLineTop(mMaxCollapsedLines);
        Logcat.i("----------------minHeight-------------" + minHeight);
        int padding = tv.getCompoundPaddingTop() + tv.getCompoundPaddingBottom();
        Logcat.i("----------------getRealTextViewMinHeight-----padding--------" + padding);
        return minHeight + padding;
    }

    private int getTextViewHeight(int lineNum){
        int minHeight = mTv.getLayout().getLineTop(lineNum);
        Logcat.i("----------------minHeight-------------" + minHeight);
        int padding = mTv.getCompoundPaddingTop() + mTv.getCompoundPaddingBottom();
        Logcat.i("----------------getRealTextViewMinHeight-----padding--------" + padding);
        return minHeight + padding;
    }
}
