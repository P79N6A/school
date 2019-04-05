package com.idyoga.yoga.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.vlayout.LayoutHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.home.AppointmentIntroductionActivity;
import com.idyoga.yoga.activity.shop.ShopCourseInfoActivity;
import com.idyoga.yoga.activity.shop.ShopMarketCourseActivity;
import com.idyoga.yoga.adapter.base.BaseDelegateAdapter;
import com.idyoga.yoga.model.ShopDetailBean;
import com.idyoga.yoga.utils.YogaViewUtil;
import com.idyoga.yoga.view.MyGridItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * 文 件 名: ShopRecommendCourseAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/21
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ShopRecommendCourseAdapter extends BaseDelegateAdapter {

    private ShopRecommendCourseItemAdapter mAdapter;
    private List<ShopDetailBean.RecommendCourseListBean> listBeans = new ArrayList<>();
    private Map<String, List<ShopDetailBean.RecommendCourseListBean>> mTabListMap;
    private List<String> mTabList = new ArrayList<>();
    private boolean idAddTab = true;
    private int index = 0;
    public ShopRecommendCourseAdapter(Context context, LayoutHelper layoutHelper, int layoutId, int count, int viewTypeItem) {
        super(context, layoutHelper, layoutId, count, viewTypeItem);
    }

    public void setTabListMap(Map<String, List<ShopDetailBean.RecommendCourseListBean>> map) {
        this.mTabListMap = map;
        listBeans.clear();
        mTabList.clear();
        for (String key : mTabListMap.keySet()) {
            mTabList.add(key);
        }
    }


    @Override
    public void onBindViewHolder(BaseViewHolder helper, int position) {
        super.onBindViewHolder(helper, position);
        helper.setText(R.id.tv_head_name, "权益课程排期").setText(R.id.tv_all, "");
        TabLayout mTabView = helper.getView(R.id.tab_view);
        if (idAddTab) {
            idAddTab = false;
            for (String key : mTabListMap.keySet()) {
                mTabView.addTab(mTabView.newTab().setText(key));
                mTabList.add(key);
            }
        }
        listBeans.clear();
        listBeans.addAll(mTabListMap.get(mTabList.get(index)));
        Logcat.i("index:" + index + "/" + mTabList.get(index) + "/" + mTabListMap.get(mTabList.get(index)).size());
        RecyclerView mRvView = helper.getView(R.id.rv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvView.setLayoutManager(layoutManager);
        MyGridItemDecoration decoration = new MyGridItemDecoration();
        decoration.setColor(Color.parseColor("#f2f2f2"));
        decoration.setSize(2);
        decoration.setPaddingV(YogaViewUtil.dp2px(mContext,12),YogaViewUtil.dp2px(mContext,12));
        mRvView.addItemDecoration(decoration);
        mAdapter = new ShopRecommendCourseItemAdapter(R.layout.item_shop_recommend_course, listBeans);
        mRvView.setAdapter(mAdapter);
        initTabSelect(mTabView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String shopId = (String) SharedPreferencesUtils.getSP(mContext, "shopId", "");
                Intent intent = new Intent(mContext, AppointmentIntroductionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("shopId", listBeans.get(position).getShop_id() + "");
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
