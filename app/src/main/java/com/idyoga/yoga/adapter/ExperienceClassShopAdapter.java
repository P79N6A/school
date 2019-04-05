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
import com.idyoga.yoga.model.ExperienceClassShopBean;
import com.idyoga.yoga.model.ExperienceCourseClassBean;
import com.idyoga.yoga.model.HomeExperienceBean;
import com.idyoga.yoga.model.HomeExperienceShopBean;
import com.idyoga.yoga.model.ShopItemCourseBean;
import com.idyoga.yoga.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * 文 件 名: ExperienceClassCourseAdapter
 * 创 建 人: By k
 * 创建日期: 2018/5/22 15:00
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class ExperienceClassShopAdapter extends BaseQuickAdapter<ExperienceClassShopBean, BaseViewHolder> {
    private ShopItemCourseAdapter courseAdapter;
    private ListView listView;
    private List setList;
    private Map<Integer, Boolean> mMap = new HashMap();
    private List<ExperienceClassShopBean> data;

    public ExperienceClassShopAdapter(int layoutResId, @Nullable List<ExperienceClassShopBean> data) {
        super(layoutResId, data);
        this.data = data;
        for (int i = 0; i < data.size(); i++) {
            mMap.put(data.get(i).getId(), false);
        }
    }

    @Override
    public void setNewData(@Nullable List<ExperienceClassShopBean> data) {
        super.setNewData(data);
        for (int i = 0; i < data.size(); i++) {
            mMap.put(data.get(i).getId(), false);
        }
    }

    public void addData(List<ExperienceClassShopBean> newData) {
        super.addData(newData);
        notifyDataSetChanged();
        List list = new ArrayList();
        mData.addAll(newData);
        for (int i = 0; i < list.size(); i++) {
            ExperienceClassShopBean bean = (ExperienceClassShopBean) list.get(i);
            mMap.put(bean.getId(), false);
        }
    }

    public void setData(@Nullable List<ExperienceClassShopBean> data) {
        this.mData = data;
        for (int i = 0; i < data.size(); i++) {
            mMap.put(data.get(i).getId(), false);
        }
        notifyDataSetChanged();
    }


    @Override
    protected void convert(final BaseViewHolder helper, final ExperienceClassShopBean bean) {
        if (bean != null) {
            final List<ShopItemCourseBean> beanList = new ArrayList();
            if (!ListUtil.isEmpty(bean.getCourse())) {
                List<ShopItemCourseBean> list = JSON.parseArray(JSON.toJSONString(bean.getCourse()), ShopItemCourseBean.class);
                beanList.addAll(list);
            }
            if (!ListUtil.isEmpty(bean.getLesson())) {
                List<ShopItemCourseBean> list = JSON.parseArray(JSON.toJSONString(bean.getLesson()), ShopItemCourseBean.class);
                beanList.addAll(list);
            }
            Glide.with(mContext).load(bean.getLogopath()).placeholder(R.drawable.img_course).error(R.drawable.img_course).into((ImageView) helper.getView(R.id.iv_img));
            helper.setText(R.id.tv_shop_name, bean.getName())
                    .setText(R.id.tv_comment_num, bean.getShopCommentCount() + "条评价")
                    .setText(R.id.tv_address, bean.getAddress() + "")
                    .setText(R.id.tv_distance, bean.getCompare() + "");
            helper.setVisible(R.id.tv_distance, true);
            TextView textView = helper.getView(R.id.tv_footer_view);
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
            setList = new ArrayList();
            listView = helper.getView(R.id.lv_list);
            listView.clearFocus();
            courseAdapter = new ShopItemCourseAdapter(mContext, beanList, R.layout.item_experience_shop_course, 1);
            listView.setAdapter(courseAdapter);
            courseAdapter.notifyDataSetChanged();
            if (mMap.get(bean.getId())) {
                helper.setText(R.id.tv_footer_view, "关闭更多显示");
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
                    courseAdapter = new ShopItemCourseAdapter(mContext, setList, R.layout.item_experience_shop_course, 1);
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
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String shopId = (String) SharedPreferencesUtils.getSP(mContext, "shopId", "");
                ShopItemCourseBean courseBean = (ShopItemCourseBean) listView.getAdapter().getItem(i);
                Intent intent;
                if (courseBean.getType() == 1) {
                    intent = new Intent(mContext, AppointmentIntroductionActivity.class);
                } else {
                    intent = new Intent(mContext, ShopCourseInfoActivity.class);
                }
                Bundle bundle = new Bundle();
                bundle.putString("shopId", courseBean.getShop_id() + "");
                bundle.putString("lessonId", courseBean.getId() + "");
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }
}