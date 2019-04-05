package com.idyoga.yoga.listener;

import android.view.View;
import android.view.ViewGroup;

import com.idyoga.yoga.view.BgDarkPopupWindow;

public interface OnPopupWindowMoreListener<T> {
    void onItemClick(BgDarkPopupWindow popupWindow, View view, T t, int position, int childPosition);

}
