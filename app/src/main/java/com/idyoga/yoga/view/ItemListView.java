package com.idyoga.yoga.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

public class ItemListView extends ListView {
    public ItemListView(Context context, AttributeSet attrs) {

        super(context, attrs);

    }

    public ItemListView(Context context) {

        super(context);

    }

    public ItemListView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);

    }

    @Override

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
