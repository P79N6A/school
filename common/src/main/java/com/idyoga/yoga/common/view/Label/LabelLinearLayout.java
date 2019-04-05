package com.idyoga.yoga.common.view.Label;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 文 件 名: LabelLinearLayout
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/3/22
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class LabelLinearLayout extends LinearLayout {
    private LabelViewHelper mLabelViewHelper;
    private boolean mLabelVisable = true;

    public LabelLinearLayout(Context context) {
        this(context, null);
    }

    public LabelLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LabelLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLabelViewHelper = new LabelViewHelper(context, attrs);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (mLabelVisable) {
            mLabelViewHelper.drawLabel(this, canvas);
        }
    }

    public void setTextContent(String content) {
        mLabelViewHelper.setTextContent(content);
        invalidate();
    }

    public void setTextTitle(String title) {
        mLabelViewHelper.setTextTitle(title);
        invalidate();
    }

    public void setLabelBackGroundColor(int color) {

        mLabelViewHelper.setLabelBackGroundColor(color);
        invalidate();
    }

    public void setLabelVisable(boolean visable) {
        mLabelVisable = visable;
        postInvalidate();
    }
}
