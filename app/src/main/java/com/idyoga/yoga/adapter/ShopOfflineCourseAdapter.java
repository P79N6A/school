package com.idyoga.yoga.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.model.HomeExprienceTypeClassBean;
import com.idyoga.yoga.model.ShopOfflineCourseListBean;
import com.idyoga.yoga.utils.CommonUtils;

import java.util.List;

import vip.devkit.library.Logcat;

/**
 * 文 件 名: ShopOfflineCourseAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/21
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ShopOfflineCourseAdapter extends BaseQuickAdapter<ShopOfflineCourseListBean,BaseViewHolder> {

    public ShopOfflineCourseAdapter(int layoutResId, @Nullable List<ShopOfflineCourseListBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, ShopOfflineCourseListBean item) {
        ImageView imageView = helper.getView(R.id.iv_img);
        Glide.with(mContext).load(item.getLessonImage()).error(R.drawable.default_course_img).into(imageView);
        helper.setText(R.id.tv_course_name, item.getLessonName())
                .setText(R.id.tv_course_tutor, "导师："+item.getTutorName())
                .setText(R.id.tv_course_price_1, item.getPrice() + "")
                .setText(R.id.tv_course_price_2, item.getPrice() + "")
                .setText(R.id.tv_course_time, "时间："+
                        CommonUtils.getDateStringByTimeSTamp(Long.valueOf(item.getStart_time()),"MM/dd")+" "+
                        CommonUtils.getDateStringByTimeSTamp(Long.valueOf(item.getStart_time()),"HH:mm")+"-"+
                        CommonUtils.getDateStringByTimeSTamp(Long.valueOf(item.getEnd_time()),"HH:mm"));
    }
}
