package com.idyoga.yoga.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.home.AppointmentIntroductionActivity;
import com.idyoga.yoga.activity.shop.ShopCourseInfoActivity;
import com.idyoga.yoga.listener.OnItemClickListener;
import com.idyoga.yoga.model.SearchResultBean;
import com.idyoga.yoga.model.ShopCourseListBeanV2;
import com.idyoga.yoga.model.ShopItemCourseBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

public class SearchCourseListAdapter extends BaseQuickAdapter<SearchResultBean.CourseListBeanX, BaseViewHolder> {


    private SearchItemCourseAdapter courseAdapter;
    private String keyword = "";
    private List setList;
    private ListView listView;
    private Map<Integer, Boolean> mMap = new HashMap();
    private List<BaseViewHolder> mHolderList = new ArrayList<>();

    public SearchCourseListAdapter(int layoutResId, @Nullable List<SearchResultBean.CourseListBeanX> data, String keyword) {
        super(layoutResId, data);
        this.keyword = keyword;
        for (int i = 0; i < data.size(); i++) {
            mMap.put(data.get(i).getId(), false);
        }
    }

    public void setData(@Nullable List<SearchResultBean.CourseListBeanX> data) {
        this.mData = data;
        Logcat.i("data:" + data.size());
        for (int i = 0; i < data.size(); i++) {
            mMap.put(data.get(i).getId(), false);
        }
        notifyDataSetChanged();
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    protected void convert(final BaseViewHolder helper, SearchResultBean.CourseListBeanX item) {
        final SearchResultBean.CourseListBeanX bean = mData.get(helper.getLayoutPosition());
        Glide.with(mContext)
                .load(bean.getLogopath())
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into((ImageView) helper.getView(R.id.iv_img));
        helper.setText(R.id.tv_shop_name, bean.getName() + "")
                .setText(R.id.tv_address, bean.getAddress())
                .setText(R.id.tv_distance, bean.getCompare());
        mHolderList.add(helper);
        final List<ShopItemCourseBean> beanList = new ArrayList();
        if (!ListUtil.isEmpty(bean.getCourseList())) {
            List<ShopItemCourseBean> list = JSON.parseArray(JSON.toJSONString(bean.getCourseList()), ShopItemCourseBean.class);
            beanList.addAll(list);
        }
        TextView textView = helper.getView(R.id.tv_footer_view);
        if (ListUtil.isEmpty(bean.getCourseList())) {
            textView.setVisibility(View.GONE);
            textView.setText("暂未排课");
        } else {
            if (bean.getCourseList().size() > 2) {
                textView.setVisibility(View.VISIBLE);
                textView.setText("查看更多");
            } else {
                textView.setVisibility(View.GONE);
            }
        }
        listView = helper.getView(R.id.lv_list);
        setList = new ArrayList();
        courseAdapter = new SearchItemCourseAdapter(mContext, beanList, R.layout.item_shop_course_item_v2, keyword);
        listView.setAdapter(courseAdapter);
        courseAdapter.notifyDataSetChanged();
        if (null != mMap.get(bean.getId()) && mMap.get(bean.getId())) {
            helper.setText(R.id.tv_footer_view, "关闭更多显示");
            Logcat.i("关闭更多显示 1");
            if (beanList.size() > 5) {
                Logcat.i("关闭更多显示 2");
                setList.clear();
                for (int i = 0; i < 5; i++) {
                    setList.add(beanList.get(i));
                }
                courseAdapter.setBeanList(setList);
                courseAdapter.notifyDataSetChanged();
            } else {
                Logcat.i("关闭更多显示 3");
                courseAdapter.setBeanList(beanList);
                courseAdapter.notifyDataSetChanged();
            }
        } else {
            if (beanList.size() > 2) {
                setList.clear();
                for (int i = 0; i < 2; i++) {
                    setList.add(beanList.get(i));
                }
                courseAdapter = new SearchItemCourseAdapter(mContext, setList, R.layout.item_shop_course_item_v2, keyword);
                listView.setAdapter(courseAdapter);
                courseAdapter.notifyDataSetChanged();
            } else {
                courseAdapter.setBeanList(beanList);
                courseAdapter.notifyDataSetChanged();
            }
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView getV = (TextView) view;
                switch (getV.getText().toString()) {
                    case "查看更多":
                        Logcat.i("查看更多" + beanList.size());
                        mMap.put(bean.getId(), true);
                        helper.setText(R.id.tv_footer_view, "关闭更多显示");
                        break;
                    case "关闭更多显示":
                        Logcat.i("关闭更多显示");
                        mMap.put(bean.getId(), false);
                        helper.setText(R.id.tv_footer_view, "查看更多");
                        break;
                    case "暂未排课":
                        Logcat.i("暂未排课");
                        helper.setText(R.id.tv_footer_view, "暂未排课");
                        break;
                }
                notifyItemChanged(helper.getAdapterPosition());
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String shopId = (String) SharedPreferencesUtils.getSP(mContext, "ShopId", "");
                String shopId =bean.getId()+"";
                ShopItemCourseBean courseBean = (ShopItemCourseBean) adapterView.getAdapter().getItem(i);
                Intent intent;
                Bundle bundle = new Bundle();
                bundle.putString("shopId", shopId + "");
                intent = new Intent(mContext, AppointmentIntroductionActivity.class);
                bundle.putString("lessonId", courseBean.getId() + "");
                bundle.putString("courseId", courseBean.getId() + "");
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

}
