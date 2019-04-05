package com.idyoga.yoga.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.model.ShopItemCourseBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.DateUtils;
import com.idyoga.yoga.utils.YogaViewUtil;

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
public class ShopItemCourseAdapterV2 extends CommonAdapter<ShopItemCourseBean> {
    List<ShopItemCourseBean> mBeanList;
    int type;

    public ShopItemCourseAdapterV2(Context context, List<ShopItemCourseBean> mBeanList, int layoutId, int type) {
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
        holder.setText(R.id.tv_course_name, bean.getLessonName());
        Glide.with(mContext)
                .load(bean.getImage())
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into((ImageView) holder.getView(R.id.iv_img));
        TextView textView = holder.getView(R.id.tv_course_tutor);
        TextView textView1 = holder.getView(R.id.tv_course_time);
        if (bean.getType() == 1) {
            textView.setText(bean.getTutorName());
            textView1.setText(CommonUtils.getDateStringByTimeSTamp(Long.valueOf(bean.getStart_time()), "yyyy-MM-dd") + " " +
                    CommonUtils.getDateStringByTimeSTamp(Long.valueOf(bean.getStart_time()), "HH:mm") + "-" +
                    CommonUtils.getDateStringByTimeSTamp(Long.valueOf(bean.getEnd_time()), "HH:mm"));
        } else {
            textView.setText("时长：" + DateUtils.secondToTime(bean.getTime() * 60));
            textView1.setText("自选时间");
            textView1.setTextSize(10);
            textView1.setTextColor(mContext.getResources().getColor(R.color.theme_1));
            textView1.setBackgroundResource(R.drawable.bg_shop_01);
        }
    }
}