package com.idyoga.yoga.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.model.ExperienceClassShopBean;
import com.idyoga.yoga.model.ExperienceCourseClassBean;
import com.idyoga.yoga.model.HomeExperienceBean;
import com.idyoga.yoga.model.HomeExperienceShopBean;
import com.idyoga.yoga.model.ShopItemCourseBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.DateUtils;

import java.util.List;

import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;

/**
 * 文 件 名: ShopItemCourseAdapter
 * 创 建 人: By k
 * 创建日期: 2018/5/22 16:03
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class ShopItemCourseAdapter extends CommonAdapter<ShopItemCourseBean> {
    List<ShopItemCourseBean> mBeanList;
    int type;

    public ShopItemCourseAdapter(Context context, List<ShopItemCourseBean> mBeanList, int layoutId, int type) {
        super(context, mBeanList, layoutId);
        this.mBeanList = mBeanList;
        this.type = type;
    }

    public void setBeanList(List<ShopItemCourseBean> beanList) {
        this.mBeanList = beanList;
        notifyDataSetChanged();
    }

    @Override
    public void convert(ViewHolder holder, ShopItemCourseBean bean, int position) {
        holder.setText(R.id.tv_course_name, bean.getLessonName()+"");
        TextView textView = holder.getView(R.id.tv_course_time);
        if (bean.getType() == 1) {
            textView.setText(CommonUtils.getDateStringByTimeSTamp(Long.valueOf(bean.getStart_time()), "MM/dd") + " " +
                    CommonUtils.getDateStringByTimeSTamp(Long.valueOf(bean.getStart_time()), "HH:mm") + "-" +
                    CommonUtils.getDateStringByTimeSTamp(Long.valueOf(bean.getEnd_time()), "HH:mm"));
        } else if (type == 700) {
            textView.setText(CommonUtils.getDateStringByTimeSTamp(Long.valueOf(bean.getStart_time()), "MM/dd") + " " +
                    CommonUtils.getDateStringByTimeSTamp(Long.valueOf(bean.getStart_time()), "HH:mm") + "-" +
                    CommonUtils.getDateStringByTimeSTamp(Long.valueOf(bean.getEnd_time()), "HH:mm"));
        } else {
            textView.setText("预约");
        }
    }
}