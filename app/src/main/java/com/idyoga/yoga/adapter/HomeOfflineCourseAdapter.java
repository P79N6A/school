package com.idyoga.yoga.adapter;

import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.common.yogaplayer.DensityUtil;
import com.idyoga.yoga.model.HomeRecommendBean;
import com.idyoga.yoga.utils.YogaViewUtil;

import java.util.List;

/**
 * 文 件 名: HomeVideoCourseAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/18
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class HomeOfflineCourseAdapter extends BaseQuickAdapter<HomeRecommendBean.VideoListBean,BaseViewHolder>{


    public HomeOfflineCourseAdapter(int layoutResId, @Nullable List<HomeRecommendBean.VideoListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeRecommendBean.VideoListBean videoBean) {
        ViewGroup.LayoutParams layoutParams =  helper.getConvertView().getLayoutParams();
        layoutParams.width = DensityUtil.dip2px(mContext,105);
        layoutParams.height = YogaViewUtil.getViewHeight(helper.getConvertView());
        helper.getConvertView().setLayoutParams(layoutParams);
        helper.setText(R.id.tv_course_name,videoBean.getName());
        ImageView imageView = helper.getView(R.id.iv_img);
        Glide.with(mContext).load(videoBean.getName()).placeholder(R.drawable.img_course).error(R.drawable.img_course).into(imageView);
    }
}
