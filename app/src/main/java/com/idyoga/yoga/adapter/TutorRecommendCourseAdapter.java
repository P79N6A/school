package com.idyoga.yoga.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.vlayout.LayoutHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.home.AppointmentIntroductionActivity;
import com.idyoga.yoga.adapter.base.BaseDelegateAdapter;
import com.idyoga.yoga.model.ShopDetailBean;
import com.idyoga.yoga.model.TutorDetailBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * 文 件 名: TutorRecommendCourseAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/21
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class TutorRecommendCourseAdapter extends BaseDelegateAdapter {

    private TutorRecommendCourseItemAdapter mAdapter;
    private List<TutorDetailBean.CourseBean> listBeans = new ArrayList<>();
    private Map<String, List<TutorDetailBean.CourseBean>> mTabListMap;
    private List<String> mTabList = new ArrayList<>();
    private boolean idAddTab = true;
    private int index = 0;
    private String shopId;
    public TutorRecommendCourseAdapter(Context context, LayoutHelper layoutHelper, int layoutId, int count, int viewTypeItem) {
        super(context, layoutHelper, layoutId, count, viewTypeItem);
    }

    public void setTabListMap(Map<String, List<TutorDetailBean.CourseBean>> map,String shopId) {
        this.mTabListMap = map;
        this. shopId= shopId;
        listBeans.clear();
        mTabList.clear();
        for (String key : mTabListMap.keySet()) {
            mTabList.add(key);
        }
    }


    @Override
    public void onBindViewHolder(BaseViewHolder helper, int position) {
        super.onBindViewHolder(helper, position);
        helper.setText(R.id.tv_head_name, "推荐线下课").setText(R.id.tv_all, "查看全部课程");
        TabLayout mTabView = helper.getView(R.id.tab_view);
        if (idAddTab) {
            idAddTab = false;
            for (String key : mTabListMap.keySet()) {
                mTabView.addTab(mTabView.newTab().setText(key));
                mTabList.add(key);
            }
        }
        listBeans.clear();
        if (mTabListMap!=null&&mTabListMap.get(mTabList.get(index))!=null){
            listBeans.addAll(mTabListMap.get(mTabList.get(index)));
            Logcat.i("index:" + index + "/" + mTabList.get(index) + "/" + mTabListMap.get(mTabList.get(index)).size());
        }
        RecyclerView mRvView = helper.getView(R.id.rv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvView.setLayoutManager(layoutManager);
        mAdapter = new TutorRecommendCourseItemAdapter(R.layout.item_shop_recommend_course, listBeans);
        mRvView.setAdapter(mAdapter);
        initTabSelect(mTabView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                String shopId = (String) SharedPreferencesUtils.getSP(mContext, "ShopId", "");
                Intent intent = new Intent(mContext, AppointmentIntroductionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("tag","close");
                bundle.putString("shopId", shopId + "");
                bundle.putString("lessonId", listBeans.get(position).getId() + "");
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    private void initTabSelect(TabLayout mTabView) {
        mTabView.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                index = tab.getPosition();
                notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
