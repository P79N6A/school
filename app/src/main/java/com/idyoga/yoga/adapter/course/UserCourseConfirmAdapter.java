package com.idyoga.yoga.adapter.course;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.course.AppointmentCourseDetailActivity;
import com.idyoga.yoga.activity.course.CourseCommentActivity;
import com.idyoga.yoga.activity.course.CourseDetailsActivity;
import com.idyoga.yoga.activity.course.StayConfirmCourseDetailActivity;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.listener.OnItemClickListener;
import com.idyoga.yoga.model.UserCourseBean;

import java.util.List;
import java.util.Map;

import vip.devkit.library.Logcat;

/**
 * 文 件 名: UserCourseAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/3/23
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class UserCourseConfirmAdapter extends CommonAdapter<String> {
    List<UserCourseBean> mBeanList;
    Map<String, List<UserCourseBean>> mListMap;
    int type;
    private OnItemClickListener mItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public UserCourseConfirmAdapter(Context context, List<String> mStringList, Map<String, List<UserCourseBean>> mListMap, int layoutId, int type) {
        super(context, mStringList, layoutId);
        this.mListMap = mListMap;
        this.type = type;
    }

    @Override
    public void convert(ViewHolder holder, String s, int i) {
        holder.setText(R.id.tv_user_course_title, s);
        ListView itemList = holder.getView(R.id.iv_user_course_item);
        itemList.setPressed(false);//去掉焦点
        mBeanList = mListMap.get(s);
        Logcat.i("mBeanList:" + mBeanList.size());
        UserCourseConfirmListItemAdapter itemAdapter = new UserCourseConfirmListItemAdapter(mContext, mBeanList, R.layout.item_user_course_confirm_item);
        itemList.setAdapter(itemAdapter);
        itemAdapter.notifyDataSetChanged();
        setListener(mBeanList, itemList);
    }

    protected void setListener(final List<UserCourseBean> mBeanList, final ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle mBundle = new Bundle();
                mBundle.putParcelable("bean", mBeanList.get(position));
                mBundle.putInt("stateType", type);
                mBundle.putString("CourseId",mBeanList.get(position).getCourseId()+"");
                mBundle.putString("ShopId",mBeanList.get(position).getShop_id()+"");
                Intent intent = new Intent(mContext, StayConfirmCourseDetailActivity.class);
//                                Intent intent = new Intent(mContext, AppointmentCourseDetailActivity.class);
//                Intent intent = new Intent(mContext, AppointmentCourseDetailActivity.class);
                intent.putExtras(mBundle);
                mContext.startActivity(intent);
            }
        });
    }
}
