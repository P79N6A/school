package com.idyoga.yoga.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.model.ShopDetailBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.DateUtils;

import java.util.List;

/**
 * 文 件 名: ShopRecommendCourseItemAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/21
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ShopRecommendCourseItemAdapter extends BaseQuickAdapter<ShopDetailBean.RecommendCourseListBean, BaseViewHolder> {
    public ShopRecommendCourseItemAdapter(int layoutResId, @Nullable List<ShopDetailBean.RecommendCourseListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopDetailBean.RecommendCourseListBean bean) {
        helper.setText(R.id.tv_course_name, bean.getLessonName())
                .setText(R.id.tv_course_tutor, "导师：" + bean.getTutorName())
                .setText(R.id.tv_course_time, "时间：" +CommonUtils.getDateStringByTimeSTamp(Long.valueOf(bean.getStart()), "MM/dd") + " " +
                        CommonUtils.getDateStringByTimeSTamp(Long.valueOf(bean.getStart()), "HH:mm") + "-" +
                        CommonUtils.getDateStringByTimeSTamp(Long.valueOf(bean.getEnd()), "HH:mm"));
        Glide.with(mContext).load(bean.getLessonImage()).placeholder(R.drawable.img_course).error(R.drawable.img_course).into((ImageView) helper.getView(R.id.iv_img));
    }
}
