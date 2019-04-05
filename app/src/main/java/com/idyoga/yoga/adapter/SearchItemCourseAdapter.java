package com.idyoga.yoga.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.model.ShopItemCourseBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.DateUtils;
import com.idyoga.yoga.view.csstv.CSSTextView;

import java.util.List;

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
public class SearchItemCourseAdapter extends CommonAdapter<ShopItemCourseBean> {
    List<ShopItemCourseBean> mBeanList;
    String keyword = "";

    public SearchItemCourseAdapter(Context context, List<ShopItemCourseBean> mBeanList, int layoutId, String keyword) {
        super(context, mBeanList, layoutId);
        this.mBeanList = mBeanList;
        this.keyword = keyword;
    }

    public void setBeanList(List<ShopItemCourseBean> beanList) {
        this.mBeanList = beanList;
        notifyDataSetChanged();
    }

    @Override
    public void convert(ViewHolder holder, ShopItemCourseBean bean, int position) {
        Logcat.e("b"+bean.getLessonName()+"/"+keyword);
        CSSTextView cssTextView = holder.getView(R.id.tv_course_name);
        cssTextView.setText(bean.getLessonName());
        cssTextView.setTextArrColor(keyword, Color.parseColor("#ba6bb1"));
        Glide.with(mContext).load(bean.getImage()).placeholder(R.drawable.img_course)
                .error(R.drawable.img_course).into((ImageView) holder.getView(R.id.iv_img));
        TextView textView = holder.getView(R.id.tv_course_tutor);
        TextView textView1 = holder.getView(R.id.tv_course_time);
        textView.setText(bean.getTutorName());
        textView1.setText(CommonUtils.getDateStringByTimeSTamp(Long.valueOf(bean.getStart_time()), "MM/dd") + " " +
                CommonUtils.getDateStringByTimeSTamp(Long.valueOf(bean.getStart_time()), "HH:mm") + "-" +
                CommonUtils.getDateStringByTimeSTamp(Long.valueOf(bean.getEnd_time()), "HH:mm"));
    }
}