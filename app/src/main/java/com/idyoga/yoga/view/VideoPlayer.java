/*
 ******************************* Copyright (c)*********************************\
 **
 **                 (c) Copyright 2018, 珠海现联瑜君岚运营管理有限公司, china, qd. sd
 **                                All Rights Reserved
 **
 **                           By(珠海现联瑜君岚运营管理有限公司)
 ********************************End of Head************************************\
 */
package com.idyoga.yoga.view;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.alivc.player.AliVcMediaPlayer;
import com.alivc.player.MediaPlayer;
import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.video.ILPlayerImpl;
import com.idyoga.yoga.common.yogaplayer.AnimationImpl;
import com.idyoga.yoga.common.yogaplayer.OrientationUtil;
import com.idyoga.yoga.utils.LUtil;
import com.idyoga.yoga.utils.ViewUtil;

import java.util.List;

import vip.devkit.library.Logcat;


/**
 * File   Name: LivePlayer
 * Create Date: 2018/10/29 17:54
 * Describe   :
 * Author     : By k
 * E-mail     : vip@devkit.vip
 * VersionName: 1
 * VersionCode: V 1.0
 * Code Update:（author - time）
 * Update Describe：
 */
public class VideoPlayer extends RelativeLayout implements View.OnClickListener {

    private String TAG = "Live: " + this.getClass().getSimpleName() + ":";

    SurfaceView mSurfaceView;//
    ProgressBar mLoadingView;//
    ImageView mIvThumb;
    ImageView mIvStart;//
    LinearLayout mStartLayout;//
    LinearLayout mRetryLayout;//
    RelativeLayout mRlController;//
    LinearLayout mBarrageLayout;//
    TextView mTvCurrentDuration;//
    SeekBar mSbHorizontalView;//

    private String mUrl = null;
    private boolean isEnableLog;
    private boolean inSeek = false;
    private boolean isCompleted = false;
    private boolean isShowVerticalBar = false;
    private AliVcMediaPlayer mMediaPlayer;
    private int mCurOrientation;
    private final int CODE_VIDEO_PROGRESS = 0; //更新进度条
    private final int CODE_VIDEO_AUTO_HIDE = 1; //自动隐藏控制控件
    private static final int TIME_AUTO_HIDE_BARS_DELAY = 3800;
    private int iconPause = R.drawable.yoga_player_pause;
    private int iconPlay = R.drawable.yoga_player_play;
    private int iconShrink = R.drawable.yoga_player_shrink;
    private int iconExpand = R.drawable.yoga_player_expand;

    private Animation mEnterFromTop;
    private Animation mEnterFromBottom;
    private Animation mExitFromTop;
    private Animation mExitFromBottom;

    private Activity mActivity;
    private ILPlayerImpl mIPlayerImpl;

    public VideoPlayer(Context context) {
        this(context, null);
    }

    public VideoPlayer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoPlayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void setUrl(String url) {
        this.mUrl = url;
    }

    public void setEnableLog(boolean enableLog) {
        this.isEnableLog = enableLog;
    }

    public void setIPlayerImpl(ILPlayerImpl IPlayerImpl) {
        mIPlayerImpl = IPlayerImpl;
    }

    public void init() {
        mActivity = LUtil.getAppCompActivity(getContext());
        inflate(getContext(), R.layout.player_layout, this);
        mSurfaceView = findViewById(R.id.surface_view);//
        mLoadingView = findViewById(R.id.loading_view);//
        mStartLayout = findViewById(R.id.start_layout);//
        mIvStart = findViewById(R.id.iv_start);//
        //
        mIvThumb = findViewById(R.id.iv_thumb);
                                                // tv_replay_text
        mRetryLayout = findViewById(R.id.retry_layout);//
        mRlController = findViewById(R.id.rl_player_controller);//
        mBarrageLayout = findViewById(R.id.ll_barrage_layout);//
        mTvCurrentDuration = findViewById(R.id.tv_duration_current);//
        mSbHorizontalView = findViewById(R.id.sb_horizontal_view);//

        mBarrageLayout.setVisibility(GONE);
        mRetryLayout.setVisibility(GONE);
        mStartLayout.setVisibility(VISIBLE);
        mRetryLayout.setOnClickListener(this);
        mStartLayout.setOnClickListener(this);

        initAnimation();
        initSeekBar();
        initSurfaceView();
        initPlayer();
        setListener();
    }

    private void initPlayer() {
        mMediaPlayer = new AliVcMediaPlayer(getContext(), mSurfaceView);
        if (isEnableLog) {
            mMediaPlayer.enableNativeLog();
        }
    }

    /**
     * 标题栏，控制栏动画效果
     */
    private void initAnimation() {
        mEnterFromTop = AnimationUtils.loadAnimation(getContext(), R.anim.enter_from_top);
        mEnterFromBottom = AnimationUtils.loadAnimation(getContext(), R.anim.enter_from_bottom);
        mExitFromTop = AnimationUtils.loadAnimation(getContext(), R.anim.exit_from_top);
        mExitFromBottom = AnimationUtils.loadAnimation(getContext(), R.anim.exit_from_bottom);


        mEnterFromTop.setAnimationListener(new AnimationImpl() {
            @Override
            public void onAnimationEnd(Animation animation) {
                super.onAnimationEnd(animation);
            }
        });
        mEnterFromBottom.setAnimationListener(new AnimationImpl() {
            @Override
            public void onAnimationEnd(Animation animation) {
                super.onAnimationEnd(animation);
                mRlController.setVisibility(VISIBLE);
            }
        });
        mExitFromTop.setAnimationListener(new AnimationImpl() {
            @Override
            public void onAnimationEnd(Animation animation) {
                super.onAnimationEnd(animation);
            }
        });
        mExitFromBottom.setAnimationListener(new AnimationImpl() {
            @Override
            public void onAnimationEnd(Animation animation) {
                super.onAnimationEnd(animation);
                mRlController.setVisibility(GONE);
            }
        });
    }

    public void setIvThumb(String imagePath) {

        Glide.with(mActivity)
                .load(imagePath)
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into(mIvThumb);
    }

    private void initSeekBar() {

        mSbHorizontalView.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mMediaPlayer != null) {
                    mMediaPlayer.seekTo(seekBar.getProgress());
                    startUpdateTimer();
                    if (isCompleted) {
                        inSeek = false;
                    } else {
                        inSeek = true;
                    }
                }
            }
        });


    }

    /**
     *
     */
    private void initSurfaceView() {
        mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            public void surfaceCreated(SurfaceHolder holder) {
//                holder.setType(SurfaceHolder.SURFACE_TYPE_GPU);
                holder.setKeepScreenOn(true);
                // Important: surfaceView changed from background to front, we need reset surface to mediaplayer.
                // 对于从后台切换到前台,需要重设surface;部分手机锁屏也会做前后台切换的处理
                if (mMediaPlayer != null) {
                    mMediaPlayer.setVideoSurface(holder.getSurface());
                }
            }

            public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
                if (mMediaPlayer != null) {
                    mMediaPlayer.setSurfaceChanged();
                }
            }

            public void surfaceDestroyed(SurfaceHolder holder) {
                if (mMediaPlayer != null) {
//                    mMediaPlayer.releaseVideoSurface();
                }
            }
        });
        mSurfaceView.setOnClickListener(this);
    }

    boolean isFistPlayer = true;

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.start_layout) {
            if (TextUtils.isEmpty(mUrl)) {
                Logcat.e("播放mUrl设置为空");
                return;
            }
            if (isCompleted) {
                isCompleted = false;//jz_pause_pressed
                mIvStart.setImageResource(R.drawable.jz_pause_pressed);
                if (mIPlayerImpl != null) {
                    mIPlayerImpl.onPlayer();
                }
                //重播
                mMediaPlayer.stop();
                mMediaPlayer.prepareAndPlay(mUrl);
                sendAutoHideBarsMsg();
                return;
            }
            Log.i(TAG, isFistPlayer + "/ " + "start :" + mUrl);
            if (isFistPlayer) {
                if (mIPlayerImpl != null) {
                    mIPlayerImpl.onPlayer();
                }
                sendAutoHideBarsMsg();
                isFistPlayer = false;
                mIvStart.setImageResource(R.drawable.jz_pause_pressed);
                mIvStart.setVisibility(View.GONE);
                mLoadingView.setVisibility(View.VISIBLE);
                mMediaPlayer.prepareToPlay(mUrl);
            } else {
                if (mMediaPlayer.isPlaying()) {
                    if (mIPlayerImpl != null) {
                        mIPlayerImpl.onPause();
                    }
                    mIvStart.setImageResource(R.drawable.jz_play_pressed);
                    mMediaPlayer.pause();
                } else {
                    //隐藏视频封面
                    mIvThumb.setVisibility(GONE);

                    mIvStart.setImageResource(R.drawable.jz_pause_pressed);
                    mMediaPlayer.play();
                    sendAutoHideBarsMsg();
                    if (mIPlayerImpl != null) {
                        mIPlayerImpl.onPlayer();
                    }
                }
            }
        } else if (id == R.id.surface_view) {
            if (mRlController.getVisibility() == VISIBLE) {
                forceShowOrHideBars(false);
            } else {
                forceShowOrHideBars(true);
            }
        }
    }


    private void setListener() {
        mMediaPlayer.setPreparedListener(new MediaPlayer.MediaPlayerPreparedListener() {
            @Override
            public void onPrepared() {
                mMediaPlayer.play();
                showVideoProgressInfo();
                Logcat.i("准备完成时触发");
                if (mIPlayerImpl != null) {
                    mIPlayerImpl.onPrepared();
                }
            }
        });
        mMediaPlayer.setFrameInfoListener(new MediaPlayer.MediaPlayerFrameInfoListener() {
            @Override
            public void onFrameInfoListener() {
                mLoadingView.setVisibility(View.GONE);
                mIvStart.setVisibility(View.VISIBLE);
                Logcat.i("首帧显示时触发");
                if (mIPlayerImpl != null) {
                    mIPlayerImpl.onFrameInfo();
                }
            }
        });
        mMediaPlayer.setCompletedListener(new MediaPlayer.MediaPlayerCompletedListener() {
            @Override
            public void onCompleted() {
                if (mIPlayerImpl != null) {
                    mIPlayerImpl.onComplete();
                }
                completed();
                //视频正常播放完成时触发
            }
        });
        mMediaPlayer.setSeekCompleteListener(new MediaPlayer.MediaPlayerSeekCompleteListener() {
            @Override
            public void onSeekCompleted() {
                //视频seek完成时触发
            }
        });
        mMediaPlayer.setStoppedListener(new MediaPlayer.MediaPlayerStoppedListener() {
            @Override
            public void onStopped() {
                Logcat.i("---------使用stop接口时触发");
                if (mIPlayerImpl != null) {
                    mIPlayerImpl.onStop();
                }
            }
        });
//        mMediaPlayer.setCircleStartListener(new MediaPlayer.MediaPlayerCircleStartListener() {
//            @Override
//            public void onCircleStart() {
//                if (mIPlayerImpl != null) {
//                    mIPlayerImpl.onCircleStart();
//                }
//            }
//        });
        mMediaPlayer.setErrorListener(new MediaPlayer.MediaPlayerErrorListener() {
            @Override
            public void onError(int i, String s) {
                Logcat.i("---------" + i + "/" + s);
                if (mIPlayerImpl != null) {
                    mIPlayerImpl.onError(i, s);
                }
            }
        });
        mMediaPlayer.setSeekCompleteListener(new MediaPlayer.MediaPlayerSeekCompleteListener() {
            @Override
            public void onSeekCompleted() {
//                Logcat.e("播放结束");
//                if (mIPlayerImpl != null) {
//                    mIPlayerImpl.onComplete();
//                }
//                onCompleted();
            }
        });

    }


    /**
     * 发送message给handler,自动隐藏标题栏
     */
    private void sendAutoHideBarsMsg() {
        //  初始自动隐藏标题栏和控制栏
        mHandler.removeMessages(CODE_VIDEO_AUTO_HIDE);
        mHandler.sendEmptyMessageDelayed(CODE_VIDEO_AUTO_HIDE, TIME_AUTO_HIDE_BARS_DELAY);
    }

    private void showVideoProgressInfo() {
        if (mMediaPlayer != null) {
            int curPosition = (int) mMediaPlayer.getCurrentPosition();
            int duration = (int) mMediaPlayer.getDuration();
            int bufferPosition = mMediaPlayer.getBufferPosition();
            Log.d(TAG, "curPosition = " + curPosition + " , duration = " + duration + " ， inSeek = " + inSeek);
            mSbHorizontalView.setMax(duration);
            mSbHorizontalView.setSecondaryProgress(bufferPosition);
            mSbHorizontalView.setProgress(curPosition);
//            mTvCurrentDuration.setTextColor(Color.parseColor("#ff0000"));
            mTvCurrentDuration.setText("" + LUtil.stringForTime(curPosition) + "/" + (LUtil.stringForTime(duration)));
        }
        startUpdateTimer();
    }


    /**
     * 更新屏幕方向
     */
    public void updateActivityOrientation() {
        Activity activity = LUtil.getAppCompActivity(getContext());
        int orientation = OrientationUtil.getOrientation(activity);
        Log.i(TAG, "屏幕方向 ：" + orientation);

        if (orientation == OrientationUtil.HORIZONTAL) {
            OrientationUtil.forceOrientation(activity, OrientationUtil.VERTICAL);
            ViewGroup.LayoutParams surfaceViewLayoutParams = this.getLayoutParams();
            surfaceViewLayoutParams.height = ViewUtil.dp2px(activity, 200f);
            surfaceViewLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            mBarrageLayout.setVisibility(GONE);
        } else {
            OrientationUtil.forceOrientation(activity, OrientationUtil.HORIZONTAL);
//            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            this.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);
            ViewGroup.LayoutParams surfaceViewLayoutParams = this.getLayoutParams();
            surfaceViewLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            surfaceViewLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            mBarrageLayout.setVisibility(VISIBLE);
        }


//        if (orientation == Configuration.ORIENTATION_PORTRAIT) {//转为竖屏了。
//            //显示状态栏
//            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            activity.getWindow().clearFlags(Window.FEATURE_NO_TITLE);//隐藏应用的标题栏
//            this.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
//            //设置view的布局，宽高之类
//            ViewGroup.LayoutParams surfaceViewLayoutParams = this.getLayoutParams();
//            surfaceViewLayoutParams.height = (int) (getWight(activity) * 9.0f / 16);
//            surfaceViewLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {                //转到横屏了。
//            //隐藏状态栏
//            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            mSurfaceView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_FULLSCREEN);
//            //设置view的布局，宽高
//            ViewGroup.LayoutParams surfaceViewLayoutParams = mSurfaceView.getLayoutParams();
//            surfaceViewLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
//            surfaceViewLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        }

    }

    private void startUpdateTimer() {
        mHandler.removeMessages(CODE_VIDEO_PROGRESS);
        mHandler.sendEmptyMessageDelayed(CODE_VIDEO_PROGRESS, 1000);
    }

    private void stopUpdateTimer() {
        mHandler.removeMessages(CODE_VIDEO_PROGRESS);
    }

    /**
     * 直接显隐标题栏和控制栏
     */
    private void forceShowOrHideBars(boolean show) {
        if (show) {
            mRlController.setVisibility(VISIBLE);
        } else {
            mRlController.setVisibility(GONE);
        }
    }

    /**
     * 竖屏时候是否需要显示标题栏
     *
     * @return 返回true 需要显示 false：不需要显示
     */
    private boolean isVerticalShow() {
        if (mActivity == null) return false;
        int orientation = OrientationUtil.getOrientation(mActivity);
        if (orientation == OrientationUtil.VERTICAL) {
            return isShowVerticalBar;
        } else
            return true;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CODE_VIDEO_PROGRESS:
                    showVideoProgressInfo();
                    break;
                case CODE_VIDEO_AUTO_HIDE:
                    forceShowOrHideBars(false);
                    break;
                default:
                    break;
            }
        }
    };

    /*public void addBarrages(List<Model> models) {
        if (!LUtil.isListEmpty(models) && mXAdapter != null) {
            try {
                Logcat.e("添加弹幕");
                for (int i = 0; i < models.size(); i++) {
                    Logcat.e("models:" + models.get(i).getShowTime() + "/" + models.get(i).getType());
                }
                mBarrageContainerView.addBarrageIntoCachePool(models);
            } catch (Exception e) {
                e.printStackTrace();
                Logcat.e("Exception" + e);
            }
        } else {

        }
    }*/

    /*public void addBarrage(Model model) {
        if (model != null && mXAdapter != null) {
            try {
                mBarrageContainerView.addBarrage(model);
            } catch (Exception e) {
                e.printStackTrace();
                Logcat.e("Exception" + e);
            }
        }
    }*/

    /*public void addDM(Model model, int speed, int gravity) {
        if (model != null && mXAdapter != null) {
            try {
                Logcat.e("发射弹幕");
                mBarrageContainerView.setSpeed(speed);
                mBarrageContainerView.setGravity(BarrageContainerView.GRAVITY_FULL);
                mBarrageContainerView.addBarrage(model);
                Logcat.e("发射弹幕 end");
            } catch (Exception e) {
                e.printStackTrace();
                Logcat.e("Exception" + e);
            }
        }
    }*/


    /**
     * @return 当前时间
     */
    public int getCurrentPosition() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    /**
     * @return 总时间
     */
    public int getDuration() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getDuration();
        }
        return 0;
    }


    private void completed() {
        isCompleted = true;
        mIvStart.setImageResource(R.drawable.jz_restart_normal);
        showVideoProgressInfo();
        stopUpdateTimer();
    }

    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    /**
     * 释放播放器
     */
    public void reset() {
        if (mMediaPlayer != null) {
            isFistPlayer = true;
            mMediaPlayer.reset();
            mTvCurrentDuration.setText("00:00/00:00");
            mSbHorizontalView.setProgress(0);
            mIvStart.setImageResource(R.drawable.jz_play_pressed);
            mRlController.setVisibility(VISIBLE);
            if (mHandler != null) {
                mHandler.removeMessages(CODE_VIDEO_AUTO_HIDE);
                mHandler.removeMessages(CODE_VIDEO_PROGRESS);
            }
        }
    }


    public void player() {
        if (mStartLayout != null) {
            Logcat.i("设置自动播放 player" + mUrl);
            mStartLayout.performClick();
        }
    }


    boolean isPause;

    public void pause() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            isPause = true;
        }
    }

    public void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
        }
    }

    public void resume() {
        if (mMediaPlayer != null && isPause) {
            mMediaPlayer.resume();
            isPause=false;
        }
    }

    public void destroy() {
        /*if (mBarrageContainerView != null) {
            mBarrageContainerView.resetBarrageProgress();
            mBarrageContainerView.destroy();
        }*/
        if (mHandler != null) {
            mHandler.removeMessages(CODE_VIDEO_AUTO_HIDE);
            mHandler.removeMessages(CODE_VIDEO_PROGRESS);
        }
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.destroy();
        }
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return true;
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Logcat.e("----------------123123123-------4-----");
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            Logcat.e("----------------123123123-------4-----");
            updateActivityOrientation();
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    protected void showKeyboard(boolean isShow, Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null == imm)
            return;
        if (isShow) {
            if (activity.getCurrentFocus() != null) {
                //有焦点打开
                imm.showSoftInput(activity.getCurrentFocus(), 0);
            } else {
                //无焦点打开
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            }
        } else {
            if (activity.getCurrentFocus() != null) {
                //有焦点关闭
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            } else {
                //无焦点关闭
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        }
    }


}
