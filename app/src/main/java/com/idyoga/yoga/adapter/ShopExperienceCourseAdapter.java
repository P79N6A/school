package com.idyoga.yoga.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import com.idyoga.yoga.model.EquityCourseShopListBean;
import com.idyoga.yoga.model.ShopCourseListBeanV2;
import com.idyoga.yoga.model.ShopItemCourseBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * 文 件 名: ShopItemCourseAdapter
 * 创 建 人: By k
 * 创建日期: 2018/5/22 16:03
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */

//public class ShopExperienceCourseAdapter extends BaseQuickAdapter<ShopCourseListBeanV2, BaseViewHolder> {
public class ShopExperienceCourseAdapter extends BaseQuickAdapter<EquityCourseShopListBean, BaseViewHolder> {

    private ShopItemCourseAdapterV2 courseAdapter;
    private ListView listView;
    private List setList;
    private Map<Integer, Boolean> mMap = new HashMap();
    private List<BaseViewHolder> mHelperList = new ArrayList<>();

    public ShopExperienceCourseAdapter(int layoutResId, @Nullable List<EquityCourseShopListBean> data) {
        super(layoutResId, data);
        for (int i = 0; i < data.size(); i++) {
            mMap.put(data.get(i).getShop_id(), false);
        }
    }

    public void setData(@Nullable List<EquityCourseShopListBean> data) {
        this.mData = data;
        Logcat.i("data:" + data.size());
        for (int i = 0; i < data.size(); i++) {
            mMap.put(data.get(i).getShop_id(), false);
        }
        notifyDataSetChanged();
    }

    public void setNewData(@Nullable List<EquityCourseShopListBean> data) {
        this.mData.addAll(data);
        for (int i = 0; i < data.size(); i++) {
            mMap.put(data.get(i).getShop_id(), false);
        }
        notifyDataSetChanged();
    }


    @Override
    protected void convert(final BaseViewHolder helper, EquityCourseShopListBean item) {
        final EquityCourseShopListBean bean = mData.get(helper.getAdapterPosition());
        if (bean==null){
            return;
        }
        Glide.with(mContext)
                .load(bean.getLogopath())
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into((ImageView) helper.getView(R.id.iv_img));
        helper.setText(R.id.tv_shop_name, bean.getName() + "")
                .setText(R.id.tv_address, bean.getAddress());
        String compare = bean.getCompare();
        if (compare == null || compare.isEmpty()){
            helper.getView(R.id.tv_distance).setVisibility(View.GONE);
        }else {
            helper.setText(R.id.tv_distance,compare + "km");
        }
        mHelperList.add(helper);
        ArrayList<String> imagePaths = new ArrayList<>();
        String lessonImage = bean.getLessonImage();
        String shopImage = bean.getShopImage();
        String studentMienImage = bean.getStudentMienImage();
        imagePaths.add(lessonImage);
        imagePaths.add(shopImage);
        imagePaths.add(studentMienImage);
        RecyclerView rlv = helper.getView(R.id.rlv_shop_image);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        rlv.setLayoutManager(layoutManager);
        ShopImaAdapter adapter = new ShopImaAdapter(mContext);
        rlv.setAdapter(adapter);
        adapter.setShopId(bean.getShop_id());
        adapter.setDatas(imagePaths);

        /*final List<ShopItemCourseBean> beanList = new ArrayList();
        if (!ListUtil.isEmpty(bean.getCourse())) {
            List<ShopItemCourseBean> list = JSON.parseArray(JSON.toJSONString(bean.getCourse()), ShopItemCourseBean.class);
            beanList.addAll(list);
        }
        RecyclerView rlv = helper.getView(R.id.rlv_shop_image);
        ShopItemCourseRlvAdapter itemCourseRlvAdapter = new ShopItemCourseRlvAdapter(mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rlv.setLayoutManager(linearLayoutManager);
        rlv.setAdapter(itemCourseRlvAdapter);*/

        //TODO 返回的瑜伽馆信息不全,无瑜伽馆图片,学员图片,团购,秒杀信息,无标签信息
        /*TextView textView = helper.getView(R.id.tv_footer_view);
        if (ListUtil.isEmpty(bean.getCourse())) {
            textView.setVisibility(View.GONE);
            helper.getView(R.id.ll_layout_more).setVisibility(View.GONE);
            textView.setText("暂未排课");
        } else {
            if (bean.getCourse().size() > 2) {
                textView.setVisibility(View.VISIBLE);
                helper.getView(R.id.ll_layout_more).setVisibility(View.VISIBLE);
                textView.setText("查看更多");
            } else {
                textView.setVisibility(View.GONE);
                helper.getView(R.id.ll_layout_more).setVisibility(View.GONE);
            }
        }*/
        /*listView = helper.getView(R.id.lv_list);
        setList = new ArrayList();
        courseAdapter = new ShopItemCourseAdapterV2(mContext, beanList, R.layout.item_shop_course_item_v2, 2);
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
                courseAdapter = new ShopItemCourseAdapterV2(mContext, setList, R.layout.item_shop_course_item_v2, 2);
                listView.setAdapter(courseAdapter);
                courseAdapter.notifyDataSetChanged();
            } else {
                courseAdapter.setBeanList(beanList);
                courseAdapter.notifyDataSetChanged();
            }
        }*/
        /*textView.setOnClickListener(new View.OnClickListener() {
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
        });*/
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String shopId = (String) SharedPreferencesUtils.getSP(mContext, "shopId", "");
                ShopItemCourseBean courseBean = beanList.get(i);
//                ShopItemCourseBean courseBean = (ShopItemCourseBean) listView.getAdapter().getItem(i);
                Intent intent;
                Bundle bundle = new Bundle();
                if (courseBean.getType() == 1) {
                    bundle.putString("shopId", courseBean.getShop_id() + "");
                    intent = new Intent(mContext, AppointmentIntroductionActivity.class);
                } else {
                    bundle.putString("shopId", bean.getId() + "");
                    intent = new Intent(mContext, ShopCourseInfoActivity.class);
                }
                bundle.putString("lessonId", courseBean.getId() + "");
                bundle.putString("courseId", courseBean.getId() + "");
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });*/
    }


}
