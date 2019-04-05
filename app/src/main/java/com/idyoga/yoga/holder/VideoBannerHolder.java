package com.idyoga.yoga.holder;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.idyoga.yoga.R;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.model.HomePageData;
import com.idyoga.yoga.utils.BannerGlideImageLoader;
import com.idyoga.yoga.utils.ViewUtil;
import com.idyoga.yoga.view.YogaBanner;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import vip.devkit.library.DensityUtil;
import vip.devkit.view.common.banner.BannerConfig;
import vip.devkit.view.common.banner.listener.OnBannerListener;

/**
 * @author weilgu
 * @time 2019/3/11 15:30
 * @des home banner
 */

public class VideoBannerHolder extends RecyclerView.ViewHolder {

    private YogaBanner mBanner;
    private Context mContext;

    public VideoBannerHolder(View itemView) {
        this(itemView,null);
        mBanner = (YogaBanner)itemView.findViewById(R.id.bv_view);
    }

    public void bindView(ArrayList<HomePageData.BannerBean> data) {
        final List<HomePageData.BannerBean> beans = (List<HomePageData.BannerBean>) data;
        ArrayList<String> images = new ArrayList<>();
        for (HomePageData.BannerBean bean:beans){
            String image_url = bean.getImage_url();
            images.add(image_url);
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mBanner.getLayoutParams();
        layoutParams.height = DensityUtil.dp2px(mContext, 120);
        mBanner.setLayoutParams(layoutParams);
        mBanner.setBannerStyle(1);
        mBanner.setImageLoader(new BannerGlideImageLoader());
        mBanner.setImages(images);
        mBanner.isAutoPlay(true);
        mBanner.setDelayTime(3000);
        mBanner.setYogaClipToPadding(false);
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.setLeftRightPadding(ViewUtil.dp2px(mContext,20));
        mBanner.setLeftRightMargin(ViewUtil.dp2px(mContext,10));
        mBanner.start();
        bannerScrollEvent();
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("getUrl", beans.get(position).getUrl());
                EventBus.getDefault().post(new PostResult("bannerDetail",bundle));
//                startActivityWithExtras(YogaWebActivity.class, bundle);
            }
        });
    }

    public VideoBannerHolder(View itemView, Context context) {
        super(itemView);
        this.mBanner = (YogaBanner)itemView.findViewById(R.id.bv_view);
        this.mContext = context;
    }

    private void bannerScrollEvent(){
        //设置viewPager 滑动过程中的page大小变化
        mBanner.setBannerPageTransformer(new ViewPager.PageTransformer() {

            private static final float MIN_SCALE = 0.85f;

            @Override
            public void transformPage(View page, float position) {
                if (position < -1 || position > 1) {
                    page.setScaleY(MIN_SCALE);

                } else if (position <= 1) { // [-1,1]  [-1,0] [0,1]
                    if (position < 0) {
                        float scaleX = 1 + 0.15f * position;
                        page.setScaleY(scaleX);
                    } else {
                        float scaleX = 1 - 0.15f * position;
                        page.setScaleY(scaleX);
                    }
                }
            }
        });
    }
}
