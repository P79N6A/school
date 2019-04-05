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
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vip.devkit.library.ListUtil;


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
public class FrPagerAdapter extends PagerAdapter {
    private List<View> mViewList;

    public FrPagerAdapter(List<View> mViewList) {
        this.mViewList = mViewList;
    }
    /**
     * @return 返回页面的个数
     */
    @Override
    public int getCount() {
        if ( mViewList!= null) {
            return mViewList.size();
        }
        return 0;
    }

    /**
     * 判断对象是否生成界面
     *
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 初始化position位置的界面
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        return mViewList.get(position);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }
}
