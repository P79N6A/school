package com.idyoga.yoga.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

/**
 * Created by mjh on 2019/1/7.
 */

public class LoginFragmentAdapter extends FragmentPagerAdapter {
    private List<String> mStringList;
    private List<Fragment> mFragments;
    FragmentManager fm;

    public LoginFragmentAdapter(FragmentManager fm,List<String> stringList,List<Fragment> fragments) {
        super(fm);
        this.fm = fm;
        this.mStringList = stringList;
        this.mFragments = fragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStringList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        FragmentTransaction transaction = fm.beginTransaction();

        for (int i=0;i<mFragments.size();i++) {

            if (i == position) {
                mFragments.get(position);
            } else {
                transaction.show(mFragments.get(i));
            }
        }
        transaction.commit();
//        transaction.hide(mFragments.get(position - 1)).show(mFragments.get(position)).commit(); // 隐藏当前的fragment，显示下一个
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments != null ? mFragments.size() : 0;
    }
}
