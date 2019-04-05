package com.idyoga.yoga.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.idyoga.yoga.R;

/**
 * @author weilgu
 * @time 2019/3/5 15:08
 * @des ${TODO}
 */

public class IvAdapter extends BaseAdapter {
    private Context mContext;
    public IvAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 30;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_iv_layout, parent, false);
        return inflate;
    }
}
