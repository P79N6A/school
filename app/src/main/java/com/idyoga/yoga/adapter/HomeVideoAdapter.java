package com.idyoga.yoga.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.idyoga.yoga.activity.home.SubjectListActivity;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;

import java.util.List;

import com.idyoga.yoga.R;
import com.idyoga.yoga.model.HomeSubjectBean;


public class HomeVideoAdapter extends CommonAdapter<HomeSubjectBean> {
    List<HomeSubjectBean> mBeanList;
    Context mContext;

    public HomeVideoAdapter(Context context, List<HomeSubjectBean> mStringList, int layoutId) {
        super(context, mStringList, layoutId);
        this.mContext = context;
        this.mBeanList = mStringList;
    }

    @Override
    public void convert(ViewHolder holder, HomeSubjectBean homeSubjectBean, int i) {
        holder.setText(R.id.tv_home_list_title, homeSubjectBean.getName());
        ListView itemList = holder.getView(R.id.iv_home_item_list);
        if (itemList != null) {
            itemList.setPressed(false);//去掉焦点
            itemList.setFocusableInTouchMode(false);
            itemList.requestFocus();
            HomeVideoListItemAdapter itemAdapter = new HomeVideoListItemAdapter(mContext, homeSubjectBean.getVideo(), R.layout.item_home_subject_item);
            itemList.setAdapter(itemAdapter);
            itemAdapter.notifyDataSetChanged();
            setListener(mBeanList, itemList, i);
        }
    }

    protected void setListener(final List<HomeSubjectBean> mBeanList, ListView listView, final int i) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle mBundle = new Bundle();
                mBundle.putString("videoId", mBeanList.get(i).getVideo().get(position).getId() + "");
                Intent intent = new Intent(mContext, SubjectListActivity.class);
                intent.putExtras(mBundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }
}
