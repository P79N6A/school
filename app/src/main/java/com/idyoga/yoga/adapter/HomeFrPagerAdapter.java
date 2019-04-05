/*
******************************* Copyright (c)*********************************\
**
**                 (c) Copyright 2017, DevKit.vip, china, qd. sd
**                          All Rights Reserved
**
**                           By(K)
********************************End of Head************************************\
*/
package com.idyoga.yoga.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;


/**
 * 文 件 名: HomeFrPagerAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/3/19
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class HomeFrPagerAdapter extends FragmentPagerAdapter {
    private List<String> mStringList;
    private List<Fragment> mFragments;
    FragmentManager fm;

    public HomeFrPagerAdapter(FragmentManager fm, List<String> mStringList, ArrayList<Fragment> mFragments) {
        super(fm);
        this.fm = fm;
        this.mStringList = mStringList;
        this.mFragments = mFragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStringList.get(position);
    }

    /**
     * @return 返回页面的个数
     */
    @Override
    public int getCount() {
        if (mStringList != null) {
            return mStringList.size();
        }
        return 0;
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
}
