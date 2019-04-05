package com.idyoga.yoga.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author weilgu
 * @time 2019/3/22 14:30
 * @des ${TODO}
 */

public class PageFragmentAdapter extends FragmentPagerAdapter {

    public PageFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
