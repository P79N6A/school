package com.idyoga.yoga.adapter;

import android.content.Context;
import android.widget.ListView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.model.UserCardDetailPermission;

import java.util.ArrayList;
import java.util.List;

import vip.devkit.library.Logcat;

/**
 * 文 件 名: UserCardRangeAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/17
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class UserCardRangeAdapter extends CommonAdapter<UserCardDetailPermission.ShopLessonListBean>implements UserCardItemAdapter.onLoadClickListener {
    List<ViewHolder> mHolderList = new ArrayList<>();

    public UserCardRangeAdapter(Context context, List<UserCardDetailPermission.ShopLessonListBean> mBeanList, int layoutId) {
        super(context, mBeanList, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, UserCardDetailPermission.ShopLessonListBean item, int position) {
            mHolderList.add(holder);

        holder.setText(R.id.tv_shop_name, item.getShopName())
                .setText(R.id.tv_num, "剩余次数：" + item.getShopValidTime());
        ListView listView = holder.getView(R.id.lv_list);
        List<UserCardDetailPermission.ShopLessonListBean.LessonBean> beanList = new ArrayList();
        if (item.getLesson().size() > 3) {
            for (int i = 0; i < item.getLesson().size(); i++) {
                if (i < 3) {
                    beanList.add(item.getLesson().get(i));
                }
            }
        } else {
            beanList.addAll(item.getLesson());
        }
        UserCardItemAdapter mItemAdapter = new UserCardItemAdapter(mContext, beanList,
                R.layout.item_layout_card_item, item.getLesson().size(), position);
        listView.setAdapter(mItemAdapter);
        mItemAdapter.notifyDataSetChanged();
        mItemAdapter.setLoadClickListener(this);
    }

    @Override
    public void onLoad(int itemPosition) {
        ViewHolder viewHolder = mHolderList.get(itemPosition);
        if (viewHolder != null) {
            Logcat.i("加载更多... 2 " + itemPosition);
            ListView listView = viewHolder.getView(R.id.lv_list);
            UserCardItemAdapter adapter = (UserCardItemAdapter) listView.getAdapter();
            adapter.setBeanList(mBeanList.get(itemPosition).getLesson());
        }
    }
}
