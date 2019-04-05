package com.idyoga.yoga.listener;

import android.view.View;
import android.view.ViewGroup;

import com.idyoga.yoga.view.BgDarkPopupWindow;

public interface OnPopupWindowListener<T> {
    void onItemClick(BgDarkPopupWindow popupWindow, View view, T t, int position);
}