package com.idyoga.yoga.activity.home;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.presenter.ICommonRequestPresenter;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.common.yogaplayer.IPlayerImpl;
import com.idyoga.yoga.common.yogaplayer.OrientationUtil;
import com.idyoga.yoga.common.yogaplayer.PlayState;
import com.idyoga.yoga.common.yogaplayer.YogaVideoPlayer;
import com.idyoga.yoga.model.HomeSubjectItemBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.VideoPlayBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import vip.devkit.library.JsonUtils;
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
public class SubjectVideoActivity extends BaseActivity implements ICommonViewUi {
    @BindView(R.id.mYogaPlayer)
    YogaVideoPlayer mYogaVideoPlayer;
    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R.id.ll_title_right)
    LinearLayout mLlTitleRight;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.tv_video_name)
    TextView mTvVideoName;
    @BindView(R.id.tv_video_grade)
    TextView mTvVideoGrade;
    @BindView(R.id.tv_video_introduce)
    TextView mTvVideoDescription;
    @BindView(R.id.sv_view)
    ScrollView mScrollView;
    private HomeSubjectItemBean mItemBean;
    private String videoId;
    private List<String> videoIdList = new ArrayList<>();
    private ICommonRequestPresenter iCommonRequestPresenter;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar
                .titleBar(mLlCommonLayout)
                .flymeOSStatusBarFontColor("#333333")
                .statusBarDarkFont(true) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void initData() {
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            String json = mBundle.getString("subjectItemBean");
            if (!CommonUtils.isBlank(json)) {
                mItemBean = JsonUtils.fromJson(json, HomeSubjectItemBean.class);
            }
            videoId = mBundle.getString("videoId");
        }
        if (mItemBean != null) {
            for (int i = 0; i < mItemBean.getVideoGroup().size(); i++) {
                for (int j = 0; j < mItemBean.getVideoGroup().get(i).getVideoGroupAction().size(); j++) {
                    videoIdList.add(mItemBean.getVideoGroup().get(i).getVideoGroupAction().get(j).getVideo());
                }
            }
            Logcat.i("videoIdList:" + videoIdList.size());
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.video_activity;
    }

    @Override
    protected void initView() {
        iCommonRequestPresenter = new CommonRequestPresenterImpl(mContext, this);
        toRequest(ApiConstants.EventTags.HOME_SUBJECT_GET_VIDEOID);
        mTvTitleText.setText(mItemBean.getTitle());
        mTvVideoGrade.setText(mItemBean.getGrade());
        mTvVideoName.setText(mItemBean.getTitle());
        mTvVideoDescription.setText(mItemBean.getDescription());
        mYogaVideoPlayer.setmHostActivity(this);
        mYogaVideoPlayer.setPlayerController(playerImpl);
        mYogaVideoPlayer.setTitle(mItemBean.getTitle());
//        mYogaVideoPlayer.setPlayerBackground(mItemBean.getImage_url());


    }

    @Override
    protected void setListener() {
    }

    /**
     * 播放器事件回调
     */
    private IPlayerImpl playerImpl = new IPlayerImpl() {

        @Override
        public void onNetWorkError() {
            ToastUtil.showToast("网络错误");
        }

        @Override
        public void onBack() {
            mYogaVideoPlayer.onStop();
            mBackPressed();
        }

        @Override
        public void onError() {
//            showToast("播放器发生异常");
        }
    };

    @Override
    public void toRequest(int eventTag) {
        if (eventTag == ApiConstants.EventTags.HOME_SUBJECT_GET_VIDEOID) {
            Map<String, String> map = new HashMap<>();
                map.put("videoId", videoId);
            Logcat.e("参数："+map.toString());
            iCommonRequestPresenter.request(eventTag, mContext, ApiConstants.Urls.HOME_SUBJECT_GET_VIDEOID, map);
        }
    }

    @Override
    public void getRequestData(int eventTag, String result) {
        if (eventTag == ApiConstants.EventTags.HOME_SUBJECT_GET_VIDEOID) {
            ResultBean resultBean = JSON.parseObject(result, ResultBean.class);
            if (resultBean.getCode().equals("1") && resultBean.getData() != null) {
                VideoPlayBean videoPlayBean = JSON.parseObject(resultBean.getData(), VideoPlayBean.class);
                mYogaVideoPlayer.setUrl(videoPlayBean.getPlayUrl());
                mYogaVideoPlayer.onStop();
                Logcat.i("播放器初始化成功");
            } else {
                ToastUtil.showToast(resultBean.getMsg());
            }
        }
    }


    @Override
    public void onRequestFailureException(int eventTag, String msg) {

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mYogaVideoPlayer != null) {
            mYogaVideoPlayer.updateActivityOrientation();
        }
        //切换为竖屏
        if (newConfig.orientation == this.getResources().getConfiguration().ORIENTATION_PORTRAIT) {
            mLlCommonLayout.setVisibility(View.VISIBLE);
            mScrollView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return false;
                }
            });
        } else if (newConfig.orientation == this.getResources().getConfiguration().ORIENTATION_LANDSCAPE) {//切换为横屏
            mLlCommonLayout.setVisibility(View.GONE);
            mScrollView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
        }
    }

    /**
     * 重写返回方法
     */
    private void mBackPressed() {
        // 全屏播放时,单击左上角返回箭头,先回到竖屏状态,再关闭
        // 这里功能最好跟onBackPressed()操作一致
        int orientation = OrientationUtil.getOrientation(SubjectVideoActivity.this);
        if (orientation == OrientationUtil.HORIZONTAL) {
            OrientationUtil.forceOrientation(SubjectVideoActivity.this, OrientationUtil.VERTICAL);
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        mBackPressed();
    }

    @OnClick(R.id.ll_title_back)
    public void onViewClicked() {
        mYogaVideoPlayer.onStop();
        finish();
    }
    @Override
    protected void onStop() {
        if (mYogaVideoPlayer!=null){
            mYogaVideoPlayer.onStop();
        }
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        if (mYogaVideoPlayer!=null){
            mYogaVideoPlayer.onDestroy();
        }
        super.onDestroy();
    }

    View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return false;
        }
    };
}


