package com.idyoga.yoga.activity.video;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.OtherVideoListAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.model.PlayerVideoBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.VideoDetailBean;
import com.idyoga.yoga.view.VideoPlayer;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * @author weilgu
 * @time 2019/3/29 18:02
 * @des 视频详情
 */

public class VideoDetailActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.ll_common_layout)
    LinearLayout mCommonLayut;
    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.player_view)
    VideoPlayer mPlayerView;
    @BindView(R.id.tv_tag)
    TextView mTvTag;
    @BindView(R.id.tv_course_name)
    TextView mTvCourseName;
    @BindView(R.id.tv_description_switch)
    TextView mTvDescriptionSwitch;
    @BindView(R.id.iv_description_switch)
    ImageView mIvDescriptionSwitch;
    @BindView(R.id.ll_description_switch)
    LinearLayout mLlDescriptionSwitch;
    @BindView(R.id.tv_count)
    TextView mTvCount;
    @BindView(R.id.tv_free)
    TextView mTvFree;
    @BindView(R.id.tv_term)
    TextView mTvTerm;
    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.ll_description)
    LinearLayout mLlDescription;
    @BindView(R.id.ll_order_video)
    LinearLayout mLlOrderVideo;
    @BindView(R.id.ll_content_layout)
    LinearLayout mLlContentLayout;
    @BindView(R.id.rlv_other_video)
    RecyclerView mRlvOtherVideo;
    private OtherVideoListAdapter mAdapter;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String videoId = "";
        if (extras != null) {
            videoId = extras.getString("videoId");
        }
        getHeadInfo(videoId);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mCommonLayut)
                .flymeOSStatusBarFontColor("#333333")
                .init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_vcourse_details;
    }

    @Override
    protected void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRlvOtherVideo.setLayoutManager(layoutManager);
        mAdapter = new OtherVideoListAdapter(this);
        mRlvOtherVideo.setAdapter(mAdapter);
    }

    @Override
    protected void setListener() {

    }

    private void initViewData(VideoDetailBean detailBean) {
        mTitle.setText(detailBean.getTitle());
        mTvCourseName.setText(detailBean.getTitle());
        mTvCount.setText("共有" + detailBean.getPitch_number() + "节 | " + detailBean.getNumber() + "人在学");
        mTvFree.setText(detailBean.getIs_free() == 0 ? "付费" : "免费");
        mTvTerm.setText(detailBean.getHas_time() + "天内可以观看");
        /*//视频封面图
        String image_url = detailBean.getImage_url();
        mPlayerView.setIvThumb(image_url);*/
        //视频描述
        mWebView.loadData(detailBean.getDescription(),"text/html","utf-8");
        List<VideoDetailBean.RecommendVideoBean> recommendVideo = detailBean.getRecommendVideo();
        mAdapter.setData(recommendVideo);
        List<VideoDetailBean.Action> action = detailBean.getAction();
        for (VideoDetailBean.Action bean:action){
            if (bean.getVideo() != null){
                getVideoPath(bean.getVideo());
                break;
            }
        }
        /*if (action.size() > 0){
            getVideoPath(action.get(0).getVideo());
        }*/
    }

    //获取视频播放链接
    private void getVideoPath(String video) {
        //https://t.p.idyoga.cn/mall/Aliyun_video/liveGetReplayUrl
        Integer userId = (Integer) SharedPreferencesUtils.getSP(this, "UserId", 0);
        Map map = new HashMap();
        map.put("videoId",video);
        mLayoutManager.showLoading();
        OkHttpUtils.post().url(ApiConstants.Urls.VIDEO_PLAYER_PATH).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoading();
                mLayoutManager.showError();
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoading();
                ResultBean resultBean = JSON.parseObject(response, ResultBean.class);
                if (resultBean.getCode().equals("1")) {
                    mLayoutManager.showContent();
                    PlayerVideoBean bean = JSON.parseObject(resultBean.getData(), PlayerVideoBean.class);
                    playerVideo(bean);
                } else {
                    mLayoutManager.showEmpty();
                }
            }
        });
    }

    //播放视频
    private void playerVideo(PlayerVideoBean bean) {
        //视频封面图
        String image_url = bean.getCover();
        mPlayerView.setIvThumb(image_url);
        mPlayerView.setUrl(bean.getPlayUrl());
        mPlayerView.player();
    }

    /**
     * 获取头部
     */
    public void getHeadInfo(String videoId) {
        Integer userId = (Integer) SharedPreferencesUtils.getSP(this, "UserId", 0);
        Map map = new HashMap();
        map.put("userId", userId + "");
        map.put("videoId", videoId + "");
        mLayoutManager.showLoading();
        OkHttpUtils.post().url(ApiConstants.Urls.VIDEO_DETAIL).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoading();
                mLayoutManager.showError();
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoading();
                ResultBean resultBean = JSON.parseObject(response, ResultBean.class);
                if (resultBean.getCode().equals("1")) {
                    mLayoutManager.showContent();
                    VideoDetailBean detailBean = JSON.parseObject(resultBean.getData(), VideoDetailBean.class);
                    initViewData(detailBean);
                } else {
                    mLayoutManager.showEmpty();
                }
            }
        });
    }

    @OnClick({R.id.ll_title_back, R.id.ll_description_switch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.ll_description_switch:
                int visibility = mLlDescription.getVisibility();
                if (visibility == View.VISIBLE){
                    mLlOrderVideo.setVisibility(View.VISIBLE);
                    mLlDescription.setVisibility(View.GONE);
                    mIvDescriptionSwitch.setImageDrawable(getResources().getDrawable(R.drawable.icon_more));  //向右箭头
                }else{
                    mLlOrderVideo.setVisibility(View.GONE);
                    mLlDescription.setVisibility(View.VISIBLE);
                    //mIvDescriptionSwitch 向下箭头
                    mIvDescriptionSwitch.setImageDrawable(getResources().getDrawable(R.drawable.icon_more_down));
                }
                break;
        }
    }

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        if (postResult.getTag().equals("toOtherVideoDetail")){
            startActivity(VideoDetailActivity.class, (Bundle) postResult.getResult());
        }
    }
}
