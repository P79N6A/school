package com.idyoga.yoga.activity.video;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.common.yogaplayer.OrientationUtil;
import com.idyoga.yoga.model.HomeSubjectItemBean;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vip.devkit.library.Logcat;

/**
 * 文 件 名: VideoActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/3/26
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class VideoActivity extends BaseActivity {
    @BindView(R.id.ll_common_layout)
    RelativeLayout mRlBar;
    //    private String mUrl = "http://player.alicdn.com/video/aliyunmedia.mp4";
    private String path = "http://v.hq-xl.com/d649ec4c183942f8872e07f033f22816/109e5142974f4b1cb3226dca5fc66744-b03cfbeb06a8384f6672cc73ba9e21cc-sd.m3u8";//M3U8
    private HomeSubjectItemBean mItemBean;
    private List<String> videoId = new ArrayList<>();

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mRlBar).init();
    }

    @Override
    public boolean isFlagFullscreen() {
        return true;
    }
    //如果当前Activity的启动模式是singleTask, 重定onNewIntent方法可以保证接收到的其它Activity传过来的值是最新的。

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Logcat.i("tag", "onNewIntent执行了");
        Bundle mBundle = intent.getExtras();
        if (mBundle != null) {
            mItemBean = mBundle.getParcelable("subjectItemBean");
            Logcat.i("---------1--:" + mItemBean.getTitle());
        }
    }

    @Override
    protected void initData() {
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mItemBean = mBundle.getParcelable("subjectItemBean");
            Logcat.i("----------2-:" + mItemBean.getTitle());
        }

    }

    @Override
    protected int setLayoutId() {
        return R.layout.video_activity;
    }

    @Override
    protected void initView() {
        mRlBar.setVisibility(View.VISIBLE);
    }


    @Override
    protected void setListener() {
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //切换为竖屏
        if (newConfig.orientation == this.getResources().getConfiguration().ORIENTATION_PORTRAIT) {
            mRlBar.setVisibility(View.VISIBLE);
        } else if (newConfig.orientation == this.getResources().getConfiguration().ORIENTATION_LANDSCAPE) {//切换为横屏
            mRlBar.setVisibility(View.GONE);
        }


    }

    private void mBackPressed() {
        // 全屏播放时,单击左上角返回箭头,先回到竖屏状态,再关闭
        // 这里功能最好跟onBackPressed()操作一致
        int orientation = OrientationUtil.getOrientation(VideoActivity.this);
        if (orientation == OrientationUtil.HORIZONTAL) {
            OrientationUtil.forceOrientation(VideoActivity.this, OrientationUtil.VERTICAL);
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        mBackPressed();
    }

}

