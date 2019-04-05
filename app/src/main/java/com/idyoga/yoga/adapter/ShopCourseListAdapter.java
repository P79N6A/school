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
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.home.AppointmentIntroductionActivity;
import com.idyoga.yoga.activity.shop.ShopCourseInfoActivity;
import com.idyoga.yoga.adapter.base.BaseDelegateAdapter;
import com.idyoga.yoga.listener.OnItemClickListener;
import com.idyoga.yoga.model.HomeExperienceShopBean;
import com.idyoga.yoga.model.ShopCourseListBeanV2;
import com.idyoga.yoga.model.ShopItemCourseBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

public class ShopCourseListAdapter extends DelegateAdapter.Adapter<BaseViewHolder> {


    protected List<ShopCourseListBeanV2> mBeanList;
    protected int mLayoutId = -1;
    protected LayoutHelper mLayoutHelper;
    private Context mContext;
    private DelegateAdapter mDelegateAdapter;
    private ShopItemCourseAdapterV2 courseAdapter;
    private ListView listView;
    private List setList;
    private int  itemViewType;
    private Map<Integer, Boolean> mMap = new HashMap();
    private List<BaseViewHolder> mHolderList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public ShopCourseListAdapter(Context context, LayoutHelper layoutHelper, List<ShopCourseListBeanV2> beanList, int layoutId, int itemViewType) {
        this.mContext = context;
        this.mBeanList = beanList;
        this.mLayoutId = layoutId;
        this.itemViewType =itemViewType;
        this.mLayoutHelper = layoutHelper;
        for (int i = 0; i < mBeanList.size(); i++) {
            mMap.put(mBeanList.get(i).getId(), false);
        }
    }

    public void setAdapter(DelegateAdapter adapter) {
        this.mDelegateAdapter = adapter;
    }

    public void setData(@Nullable List<ShopCourseListBeanV2> data) {
        this.mBeanList = data;
        Logcat.i("data:" + data.size());
        for (int i = 0; i < data.size(); i++) {
            mMap.put(data.get(i).getId(), false);
        }
        notifyDataSetChanged();
    }

    public void setNewData(@Nullable List<ShopCourseListBeanV2> data) {
        mBeanList.addAll(data);
        for (int i = 0; i < data.size(); i++) {
            mMap.put(data.get(i).getId(), false);
        }
        notifyDataSetChanged();
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutId != -1) {
            return new BaseViewHolder(LayoutInflater.from(mContext).inflate(mLayoutId, parent, false));

        }
        return null;
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {
        final ShopCourseListBeanV2 bean = mBeanList.get(position);
        Glide.with(mContext)
                .load(mBeanList.get(position).getLogopath())
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into((ImageView) holder.getView(R.id.iv_img));
        holder.setText(R.id.tv_shop_name, bean.getName() + "")
                .setText(R.id.tv_address, bean.getAddress() )
                .setText(R.id.tv_distance, bean.getCompare());
        mHolderList.add(holder);
        final List<ShopItemCourseBean> beanList = new ArrayList();
        if (!ListUtil.isEmpty(bean.getCourse())) {
            List<ShopItemCourseBean> list = JSON.parseArray(JSON.toJSONString(bean.getCourse()), ShopItemCourseBean.class);
            beanList.addAll(list);
        }
        TextView textView = holder.getView(R.id.tv_footer_view);
        if (ListUtil.isEmpty(bean.getCourse())) {
            textView.setVisibility(View.GONE);
            holder.getView(R.id.ll_layout_more).setVisibility(View.GONE);
            textView.setText("暂未排课");
        } else {
            if (bean.getCourse().size() > 2) {
                textView.setVisibility(View.VISIBLE);
                holder.getView(R.id.ll_layout_more).setVisibility(View.VISIBLE);
                textView.setText("查看更多");
            } else {
                textView.setVisibility(View.GONE);
                holder.getView(R.id.ll_layout_more).setVisibility(View.GONE);
            }
        }
        listView = holder.getView(R.id.lv_list);
        setList = new ArrayList();
        courseAdapter = new ShopItemCourseAdapterV2(mContext, beanList, R.layout.item_shop_course_item_v2, 2);
        listView.setAdapter(courseAdapter);
        courseAdapter.notifyDataSetChanged();
        if (null != mMap.get(bean.getId()) && mMap.get(bean.getId())) {
            holder.setText(R.id.tv_footer_view, "关闭更多显示");
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
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView getV = (TextView) view;
                switch (getV.getText().toString()) {
                    case "查看更多":
                        Logcat.i("查看更多" + beanList.size());
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
                mDelegateAdapter.notifyDataSetChanged();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String shopId = (String) SharedPreferencesUtils.getSP(mContext, "shopId", "");
                ShopItemCourseBean courseBean = beanList.get(i);
//                ShopItemCourseBean courseBean = (ShopItemCourseBean) listView.getAdapter().getItem(i);
                Intent intent;
                Bundle bundle = new Bundle();
                if (courseBean.getType() == 1) {
                    bundle.putString("shopId", shopId + "");
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
        });
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(0,view,position);
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return itemViewType;
    }

    @Override
    public int getItemCount() {
        return ListUtil.isEmpty(mBeanList) ? 0 : mBeanList.size();
    }
}
