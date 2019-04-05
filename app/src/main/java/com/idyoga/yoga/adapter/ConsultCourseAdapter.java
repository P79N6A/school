package com.idyoga.yoga.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.course.ConsultCourseDetailActivity;
import com.idyoga.yoga.activity.course.StayConfirmCourseDetailActivity;
import com.idyoga.yoga.adapter.course.UserCourseListItemAdapter;
import com.idyoga.yoga.model.ConsultCourseBean;
import com.idyoga.yoga.model.UserCourseBean;

import java.util.List;
import java.util.Map;

import vip.devkit.library.Logcat;

/**
 * 文 件 名: ConsultCourseAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/26
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ConsultCourseAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    Map<String, List<ConsultCourseBean>> mListMap;
    List<ConsultCourseBean> mBeanList;
    String tag;
    public ConsultCourseAdapter(int layoutResId, @Nullable List<String> data, Map<String, List<ConsultCourseBean>> mListMap,String tag) {
        super(layoutResId, data);
        this.mListMap = mListMap;
        this.tag=tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    protected void convert(BaseViewHolder helper, String s) {
        helper.setText(R.id.tv_user_course_title, s);
        ListView itemList = helper.getView(R.id.iv_user_course_item);
        itemList.setPressed(false);
        mBeanList = mListMap.get(s);
        Logcat.i("mBeanList:" + mBeanList.size());
        ConsultCourseListItemAdapter itemAdapter = new ConsultCourseListItemAdapter(mContext, mBeanList, R.layout.item_consult_course_item,tag);
        itemList.setAdapter(itemAdapter);
        itemAdapter.notifyDataSetChanged();
        setListener(mBeanList, itemList);
    }

    private void setListener(final List<ConsultCourseBean> beanList, ListView itemList) {
        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle mBundle = new Bundle();
                mBundle.putString("expectId", beanList.get(position).getId() + "");
                Intent intent = new Intent(mContext, ConsultCourseDetailActivity.class);
                intent.putExtras(mBundle);
                mContext.startActivity(intent);
            }
        });
    }
}
