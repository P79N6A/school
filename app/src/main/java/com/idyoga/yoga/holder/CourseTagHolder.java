package com.idyoga.yoga.holder;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.home.AppointmentIntroductionActivity;
import com.idyoga.yoga.adapter.HomeFrPagerAdapter;
import com.idyoga.yoga.fragment.CoureseFunctionFragment;
import com.idyoga.yoga.fragment.CourseDetailFragment;

import java.util.ArrayList;
import java.util.List;

import vip.devkit.view.common.tab.AdvancedPagerSlidingTabStrip;

/**
 * @author weilgu
 * @time 2019/3/13 16:27
 * @des ${TODO}
 */

public class CourseTagHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private AdvancedPagerSlidingTabStrip mApsts;
    private ViewPager mViewPager;
    private List<String> mTabList = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();

    public CourseTagHolder(View itemView) {
        this(itemView,null);
    }

    public CourseTagHolder(View itemView, Context context) {
        super(itemView);
        this.mContext = context;
        mApsts = itemView.findViewById(R.id.home_tabs);
        mViewPager = itemView.findViewById(R.id.home_vp_content);
        mTabList.add("课程详情");
        mTabList.add("功效介绍");
        fragments.add(new CourseDetailFragment());
        fragments.add(new CoureseFunctionFragment());
        android.support.v4.app.FragmentManager manager = ((AppointmentIntroductionActivity) mContext).getSupportFragmentManager();
        mViewPager.setAdapter(new HomeFrPagerAdapter(manager, mTabList, fragments));
    }
}
