package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.base.BaseDelegateAdapter;
import com.idyoga.yoga.model.UserCardDetailPermission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;

/**
 * 文 件 名: UserCardDetailItemAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/18
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class UserCardDetailItemAdapter extends BaseQuickAdapter<UserCardDetailPermission.ShopLessonListBean, BaseViewHolder> {

    private List setList;
    private UserCardItemAdapter mItemAdapter;
    private List<BaseViewHolder> mHolderList = new ArrayList<>();
    private Map<Integer, Boolean> mMap = new HashMap();
    private RecyclerView mRvList;

    public UserCardDetailItemAdapter(int layoutResId, @Nullable List<UserCardDetailPermission.ShopLessonListBean> data, RecyclerView mRvList) {
        super(layoutResId, data);
        this.mRvList = mRvList;
        if (!ListUtil.isEmpty(data)) {
            for (int i = 0; i < data.size(); i++) {
                mMap.put(data.get(i).getShopId(), false);
            }
        }
    }

    @Override
    public void setNewData(@Nullable List<UserCardDetailPermission.ShopLessonListBean> data) {
        super.setNewData(data);
        for (int i = 0; i < data.size(); i++) {
            mMap.put(data.get(i).getShopId(), false);
        }
        notifyDataSetChanged();
    }

    @Override
    protected void convert(final BaseViewHolder holder, final UserCardDetailPermission.ShopLessonListBean item) {
        mHolderList.add(holder);
        holder.setText(R.id.tv_shop_name, item.getShopName());
        TextView mTvNum = holder.getView(R.id.tv_num);
        if (item.getShopHasTime()==0) {
            mTvNum.setText("剩余次数：无限次" );
        }else {
            mTvNum.setText("剩余次数：" + item.getShopValidTime());
        }
        ListView listView = holder.getView(R.id.lv_list);
        TextView textView = holder.getView(R.id.tv_load_all);
        if (ListUtil.isEmpty(item.getLesson())) {
            textView.setVisibility(View.GONE);
            textView.setText("暂未排课");
        } else {
            if (item.getLesson().size() > 3) {
                textView.setVisibility(View.VISIBLE);
                textView.setText("查看更多");
            } else {
                textView.setVisibility(View.GONE);
            }
        }
        List<UserCardDetailPermission.ShopLessonListBean.LessonBean> beanList = new ArrayList();
        setList = new ArrayList();
        mItemAdapter = new UserCardItemAdapter(mContext, beanList, R.layout.item_layout_card_item, item.getLesson().size(), holder.getAdapterPosition());
        listView.setAdapter(mItemAdapter);
        if (!ListUtil.isEmpty(item.getLesson())) {
            beanList.addAll(item.getLesson());
        }
        mItemAdapter.notifyDataSetChanged();
        if (null != mMap.get(item.getShopId()) && mMap.get(item.getShopId())) {
            Logcat.i("a");
            holder.setText(R.id.tv_load_all, "关闭更多显示");
            Logcat.i("关闭更多显示");
            if (beanList.size() > 3) {
                setList.clear();
                for (int i = 0; i < 3; i++) {
                    setList.add(beanList.get(i));
                }
                mItemAdapter.setBeanList(setList);
                mItemAdapter.notifyDataSetChanged();
            } else {
                mItemAdapter.setBeanList(beanList);
                mItemAdapter.notifyDataSetChanged();
            }
        } else {
            Logcat.i("b");
            if (beanList.size() > 3) {
                setList.clear();
                for (int i = 0; i < 3; i++) {
                    setList.add(beanList.get(i));
                }
                mItemAdapter = new UserCardItemAdapter(mContext, setList, R.layout.item_layout_card_item, item.getLesson().size(), holder.getAdapterPosition());
                listView.setAdapter(mItemAdapter);
                mItemAdapter.notifyDataSetChanged();
            } else {
                mItemAdapter.setBeanList(beanList);
                mItemAdapter.notifyDataSetChanged();
            }
        }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView getV = (TextView) view;
                switch (getV.getText().toString()) {
                    case "查看更多":
                        mMap.put(item.getShopId(), true);
                        holder.setText(R.id.tv_load_all, "关闭更多显示");
                        break;
                    case "关闭更多显示":
                        mMap.put(item.getShopId(), false);
                        holder.setText(R.id.tv_load_all, "查看更多");
                        break;
                }
                notifyItemChanged(holder.getAdapterPosition());
                mRvList.scrollToPosition(holder.getAdapterPosition());
            }
        });
    }
}
