package com.idyoga.yoga.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.idyoga.yoga.common.modle.PostResult;

import java.util.List;

import de.greenrobot.event.EventBus;

public class CardPageAdapter extends PagerAdapter {


    private List<View> viewList;
    private List<String> titleList;
    public CardPageAdapter(List<View> viewList, List<String> titleList) {
        this.viewList = viewList;
        this.titleList = titleList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    /**
     * @return 返回页面的个数
     */
    @Override
    public int getCount() {
        if (viewList != null){
            return viewList.size();
        }
        return 0;
    }

    /**
     * 判断对象是否生成界面
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
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
        initTest();
    }

    private void initTest() {

    }

    /**
     * 接受EventBus 广播
     */
    public void onEvent(PostResult postResult) {


    }
}
