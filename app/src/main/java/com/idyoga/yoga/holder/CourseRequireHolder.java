package com.idyoga.yoga.holder;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.model.CoureseDetailInfo;
import com.idyoga.yoga.model.HomePageData;
import com.idyoga.yoga.utils.BannerGlideImageLoader;
import com.idyoga.yoga.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import vip.devkit.library.DensityUtil;
import vip.devkit.view.common.banner.BannerConfig;
import vip.devkit.view.common.banner.BannerV;
import vip.devkit.view.common.banner.listener.OnBannerListener;

/**
 * @author weilgu
 * @time 2019/3/13 16:35
 * @des ${TODO}
 */

public class CourseRequireHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private BannerV mImages;

    public CourseRequireHolder(View itemView, Context context) {
        super(itemView);
        this.mContext = context;
        mImages = itemView.findViewById(R.id.iv_shop_images);
    }


    public void bindView(final List<CoureseDetailInfo.Banner> beans) {
        ArrayList<String> images = new ArrayList<>();
        for (CoureseDetailInfo.Banner bean:beans){
            String image_url = bean.getImage_url();
            images.add(image_url);
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mImages.getLayoutParams();
        layoutParams.height = DensityUtil.dp2px(mContext, 120);
        mImages.setLayoutParams(layoutParams);
        mImages.setBannerStyle(1);
        mImages.setImageLoader(new BannerGlideImageLoader());
        mImages.setImages(images);
        mImages.isAutoPlay(true);
        mImages.setDelayTime(3000);
        mImages.start();
        mImages.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
//                Bundle bundle = new Bundle();
//                bundle.putString("getUrl", beans.get(position).getImage_url());
                //startActivityWithExtras(YogaWebActivity.class, bundle);
            }
        });
    }
}
