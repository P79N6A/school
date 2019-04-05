package com.idyoga.yoga.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.home.AppointmentIntroductionActivity;
import com.idyoga.yoga.activity.shop.ShopCourseInfoActivity;
import com.idyoga.yoga.lbs.LbsUtil;
import com.idyoga.yoga.model.HomeExperienceBean;
import com.idyoga.yoga.model.HomeExperienceShopBean;
import com.idyoga.yoga.model.HomeExperienceShopBean;
import com.idyoga.yoga.model.ShopItemCourseBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

public class ExperienceShopAdapter extends BaseQuickAdapter<HomeExperienceShopBean, BaseViewHolder> {

    private ShopItemCourseAdapter courseAdapter;
    private ListView listView;
    private List setList;
    private Map<Integer, Boolean> mMap = new HashMap();

    public ExperienceShopAdapter(int layoutResId, @Nullable List<HomeExperienceShopBean> data) {
        super(layoutResId, data);
        for (int i = 0; i < data.size(); i++) {
            mMap.put(data.get(i).getId(), false);
        }
    }

    public void setData(@Nullable List<HomeExperienceShopBean> data) {
        this.mData = data;
        Logcat.i("data:" + data.size());
        for (int i = 0; i < data.size(); i++) {
            mMap.put(data.get(i).getId(), false);
        }
        notifyDataSetChanged();
    }

    @Override
    public void setNewData(@Nullable List<HomeExperienceShopBean> data) {
        mData.addAll(data);
        for (int i = 0; i < data.size(); i++) {
            mMap.put(data.get(i).getId(), false);
        }
        notifyDataSetChanged();
    }

    List<BaseViewHolder> mHolderList = new ArrayList<>();

    @Override
    protected void convert(final BaseViewHolder holder, final HomeExperienceShopBean bean) {
        mHolderList.add(holder);
        final List<ShopItemCourseBean> beanList = new ArrayList();
        if (!ListUtil.isEmpty(bean.getCourse())) {
            List<ShopItemCourseBean> list = JSON.parseArray(JSON.toJSONString(bean.getCourse()), ShopItemCourseBean.class);
            beanList.addAll(list);
        }
        if (!ListUtil.isEmpty(bean.getLesson())) {
            List<ShopItemCourseBean> list = JSON.parseArray(JSON.toJSONString(bean.getLesson()), ShopItemCourseBean.class);
            beanList.addAll(list);
        }
        for (int i = 0; i <beanList.size() ; i++) {
            Logcat.i("po"+holder.getAdapterPosition()+"type"+beanList.get(i).getType());
        }
        Glide.with(mContext).load(bean.getLogopath()).placeholder(R.drawable.img_course).error(R.drawable.img_course).into((ImageView) holder.getView(R.id.iv_img));
        holder.setText(R.id.tv_shop_name, bean.getName())
                .setText(R.id.tv_comment_num, bean.getShopCommentCount() + "条评价")
                .setText(R.id.tv_address, bean.getAddress())
                .setText(R.id.tv_distance, bean.getCompare() + "");
        holder.setVisible(R.id.tv_distance, true);
        TextView textView = holder.getView(R.id.tv_footer_view);
        if (ListUtil.isEmpty(bean.getCourse())) {
            textView.setVisibility(View.GONE);
            textView.setText("暂未排课");
        } else {
            if (bean.getCourse().size() > 2) {
                textView.setVisibility(View.VISIBLE);
                textView.setText("查看更多");
            } else {
                textView.setVisibility(View.GONE);
            }
        }
        listView = holder.getView(R.id.lv_list);
        setList = new ArrayList();
        courseAdapter = new ShopItemCourseAdapter(mContext, beanList, R.layout.item_experience_shop_course, 2);
        listView.setAdapter(courseAdapter);
        courseAdapter.notifyDataSetChanged();
        if (null != mMap.get(bean.getId()) && mMap.get(bean.getId())) {
            holder.setText(R.id.tv_footer_view, "关闭更多显示");
            Logcat.i("关闭更多显示");
            if (beanList.size() > 5) {
                setList.clear();
                for (int i = 0; i < 5; i++) {
                    setList.add(beanList.get(i));
                }
                courseAdapter.setBeanList(setList);
                courseAdapter.notifyDataSetChanged();
            } else {
                courseAdapter.setBeanList(beanList);
                courseAdapter.notifyDataSetChanged();
            }
        } else {
            if (beanList.size() > 2) {
                setList.clear();
                for (int i = 0; i < 2; i++) {
                    setList.add(beanList.get(i));
                }
                courseAdapter = new ShopItemCourseAdapter(mContext, setList, R.layout.item_experience_shop_course, 2);
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
                        Logcat.i("查看更多");
                        mMap.put(bean.getId(), true);
                        holder.setText(R.id.tv_footer_view, "关闭更多显示");
                        break;
                    case "关闭更多显示":
                        Logcat.i("关闭更多显示");
                        mMap.put(bean.getId(), false);
                        holder.setText(R.id.tv_footer_view, "查看更多");
                        break;
                    case "暂未排课":
                        Logcat.i("暂未排课");
                        holder.setText(R.id.tv_footer_view, "暂未排课");
                        break;
                }
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String shopId = (String) SharedPreferencesUtils.getSP(mContext, "shopId", "");
                ShopItemCourseBean courseBean =    beanList.get(i);
//                ShopItemCourseBean courseBean = (ShopItemCourseBean) listView.getAdapter().getItem(i);
                Intent intent;
                Bundle bundle = new Bundle();
                if (courseBean.getType() == 1) {
                    bundle.putString("shopId",shopId + "");
                    intent = new Intent(mContext, AppointmentIntroductionActivity.class);
                } else {
                    bundle.putString("shopId",bean.getId() + "");
                    intent = new Intent(mContext, ShopCourseInfoActivity.class);
                }
                bundle.putString("lessonId",courseBean.getId() + "");
                bundle.putString("courseId",courseBean.getId() + "");
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

}
