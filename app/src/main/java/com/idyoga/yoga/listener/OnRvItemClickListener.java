package com.idyoga.yoga.listener;

import android.view.View;
import android.view.ViewGroup;

public interface OnRvItemClickListener<T> {
    void onItemClick(ViewGroup parent, View view, T t, int position);
}
