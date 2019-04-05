package com.idyoga.yoga.view.csstv;

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.widget.TextView;
/**
 * 文 件 名: CustomLinkMovementMethod
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/25
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class CustomLinkMovementMethod extends LinkMovementMethod {

    @Override
    public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_UP ||
                action == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            x -= widget.getTotalPaddingLeft();
            y -= widget.getTotalPaddingTop();

            x += widget.getScrollX();
            y += widget.getScrollY();

            Layout layout = widget.getLayout();
            int line = layout.getLineForVertical(y);
            int off = layout.getOffsetForHorizontal(line, x);

            ClickableSpan[] link = buffer.getSpans(off, off, ClickableSpan.class);

            if (link.length != 0) {
                if (action == MotionEvent.ACTION_UP) {
                    link[0].onClick(widget);

                } else if (action == MotionEvent.ACTION_DOWN) {
                    Selection.setSelection(buffer,
                            buffer.getSpanStart(link[0]),
                            buffer.getSpanEnd(link[0]));
                }

                if(widget instanceof CSSTextView){
                    ((CSSTextView)widget).isClickSpan = true;
                }

                return true;
            } else {
                Selection.removeSelection(buffer);
                super.onTouchEvent(widget, buffer, event);
                return false;
            }
        }
        return super.onTouchEvent(widget, buffer, event);
    }

    public static CustomLinkMovementMethod getInstance() {
        if(sInstance == null){
            sInstance = new CustomLinkMovementMethod();
        }
        return sInstance;
    }


    private static CustomLinkMovementMethod sInstance;
}
