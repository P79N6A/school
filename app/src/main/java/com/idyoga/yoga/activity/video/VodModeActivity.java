package com.idyoga.yoga.activity.video;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alivc.player.AliVcMediaPlayer;
import com.alivc.player.MediaPlayer;
import com.alivc.player.VcPlayerLog;
import com.aliyun.vodplayerview.utils.NetWatchdog;
import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.utils.Formatter;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Vod demo
 */
public class VodModeActivity extends BaseActivity {
    private static final String TAG = VodModeActivity.class.getSimpleName();
    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
    private SurfaceView mSurfaceView;
    private Button playBtn;
    private Button pauseBtn;
    private Button replayBtn;
    private Button stopBtn;

    private CheckBox muteOnBtn;

    private RadioGroup scaleModeGroup;
    private RadioButton scaleModeFit;
    private RadioButton scaleModeFill;

    private RadioGroup speedGroup;
    private RadioButton speed125;
    private RadioButton speed10;
    private RadioButton speed15;
    private RadioButton speed20;
    private float speed = 1.0f;

    private RadioGroup rotateGroup;
    private RadioButton rotate0;
    private RadioButton rotate90;
    private RadioButton rotate180;
    private RadioButton rotate270;
    private MediaPlayer.VideoRotate roate = MediaPlayer.VideoRotate.ROTATE_0;


    private RadioGroup mirrorGroup;
    private RadioButton mirrorN;
    private RadioButton mirrorH;
    private RadioButton mirrorV;
    private MediaPlayer.VideoMirrorMode mirrorMode = MediaPlayer.VideoMirrorMode.VIDEO_MIRROR_MODE_NONE;

    private TextView positionTxt;
    private TextView durationTxt;
    private SeekBar progressBar;

    private SeekBar brightnessBar;
    private SeekBar volumeBar;

    private TextView videoWidthTxt;
    private TextView videoHeightTxt;

    private boolean mMute = false;
    private List<String> logStrs = new ArrayList<>();

    private AliVcMediaPlayer mPlayer;

    private boolean inSeek = false;
    private boolean isCompleted = false;

//    private String mUrl = "http://player.alicdn.com/video/aliyunmedia.mp4";
    private String mUrl = "http://v.hq-xl.com/d649ec4c183942f8872e07f033f22816/109e5142974f4b1cb3226dca5fc66744-b03cfbeb06a8384f6672cc73ba9e21cc-sd.m3u8";//M3U8

    private NetWatchdog netWatchdog;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_log, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.log) {

            LayoutInflater inflater = LayoutInflater.from(this);
            View view = inflater.inflate(R.layout.view_log, null);

            TextView textview = (TextView) view.findViewById(R.id.log);
            if (mPlayer != null) {
                for (String log : logStrs) {
                    textview.append("     " + log + "\n");
                }
            }
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(getString(R.string.player_log));
            alertDialog.setView(view);
            alertDialog.setPositiveButton(getString(R.string.ok), null);
            AlertDialog alert = alertDialog.create();
            alert.show();
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_vod_mode);

        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);

        playBtn = (Button) findViewById(R.id.play);
        stopBtn = (Button) findViewById(R.id.stop);
        pauseBtn = (Button) findViewById(R.id.pause);
        replayBtn = (Button) findViewById(R.id.replay);

        //不可见的view们
        findViewById(R.id.snapshot).setVisibility(View.GONE);
        findViewById(R.id.auto_play_layout).setVisibility(View.GONE);
        findViewById(R.id.change_quality_layout).setVisibility(View.GONE);

        muteOnBtn = (CheckBox) findViewById(R.id.muteOn);
        muteOnBtn.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    mMute = true;
                    if (mPlayer != null) {
                        mPlayer.setMuteMode(mMute);
                    }
                    volumeBar.setProgress(0);
                } else {
                    mMute = false;
                    if (mPlayer != null) {
                        mPlayer.setMuteMode(mMute);
                    }
                    volumeBar.setProgress(mPlayer.getVolume());
                }
            }


        });

        scaleModeGroup = (RadioGroup) findViewById(R.id.scalingMode);
        scaleModeFit = (RadioButton) findViewById(R.id.fit);
        scaleModeFill = (RadioButton) findViewById(R.id.fill);

        scaleModeFit.setChecked(true);

        scaleModeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == scaleModeFit.getId()) {
                    if (mPlayer != null) {
                        mPlayer.setVideoScalingMode(MediaPlayer.VideoScalingMode.VIDEO_SCALING_MODE_SCALE_TO_FIT);
                    }
                } else if (checkedId == scaleModeFill.getId()) {
                    if (mPlayer != null) {
                        mPlayer.setVideoScalingMode(MediaPlayer.VideoScalingMode.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
                    }
                }
            }
        });

        speedGroup = (RadioGroup) findViewById(R.id.speedgroup);
        speed125 = (RadioButton) findViewById(R.id.speed125);
        speed10 = (RadioButton) findViewById(R.id.speed10);
        speed15 = (RadioButton) findViewById(R.id.speed15);
        speed20 = (RadioButton) findViewById(R.id.speed20);
        speed10.setChecked(true);
        speedGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
                if (checkedId == R.id.speed125) {
                    speed = 1.25f;
                } else if (checkedId == R.id.speed10) {
                    speed = 1.0f;
                } else if (checkedId == R.id.speed15) {
                    speed = 1.5f;
                } else if (checkedId == R.id.speed20) {
                    speed = 2.0f;
                }

                if (mPlayer != null) {
                    mPlayer.setPlaySpeed(speed);
                }
            }
        });


        rotateGroup = (RadioGroup) findViewById(R.id.rotationgroup);
        rotate0 = (RadioButton) findViewById(R.id.rotate0);
        rotate90 = (RadioButton) findViewById(R.id.rotate90);
        rotate180 = (RadioButton) findViewById(R.id.rotate180);
        rotate270 = (RadioButton) findViewById(R.id.rotate270);
        rotate0.setChecked(true);
        rotateGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
                if (checkedId == R.id.rotate0) {
                    roate = MediaPlayer.VideoRotate.ROTATE_0;
                } else if (checkedId == R.id.rotate90) {
                    roate = MediaPlayer.VideoRotate.ROTATE_90;
                } else if (checkedId == R.id.rotate180) {
                    roate = MediaPlayer.VideoRotate.ROTATE_180;
                } else if (checkedId == R.id.rotate270) {
                    roate = MediaPlayer.VideoRotate.ROTATE_270;
                }

                if (mPlayer != null) {
                    mPlayer.setRenderRotate(roate);
                }
            }
        });

        mirrorGroup = (RadioGroup) findViewById(R.id.mirrorroup);
        mirrorN = (RadioButton) findViewById(R.id.mirrornormal);
        mirrorH = (RadioButton) findViewById(R.id.mirrorh);
        mirrorV = (RadioButton) findViewById(R.id.mirrorv);
        mirrorN.setChecked(true);
        mirrorGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
                if (checkedId == R.id.mirrornormal) {
                    mirrorMode = MediaPlayer.VideoMirrorMode.VIDEO_MIRROR_MODE_NONE;
                } else if (checkedId == R.id.mirrorh) {
                    mirrorMode = MediaPlayer.VideoMirrorMode.VIDEO_MIRROR_MODE_HORIZONTAL;
                } else if (checkedId == R.id.mirrorv) {
                    mirrorMode = MediaPlayer.VideoMirrorMode.VIDEO_MIRROR_MODE_VERTICAL;
                }

                if (mPlayer != null) {
                    mPlayer.setRenderMirrorMode(mirrorMode);
                }
            }
        });

        positionTxt = (TextView) findViewById(R.id.currentPosition);
        durationTxt = (TextView) findViewById(R.id.totalDuration);
        progressBar = (SeekBar) findViewById(R.id.progress);
        brightnessBar = (SeekBar) findViewById(R.id.brightnessProgress);
        volumeBar = (SeekBar) findViewById(R.id.volumeProgress);

        videoWidthTxt = (TextView) findViewById(R.id.width);
        videoHeightTxt = (TextView) findViewById(R.id.height);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logStrs.add(format.format(new Date()) + getString(R.string.log_start_get_data));


                start();

                if (mMute) {
                    mPlayer.setMuteMode(mMute);
                }
                mPlayer.setPlaySpeed(speed);
                brightnessBar.setProgress(mPlayer.getScreenBrightness());
                logStrs.add(format.format(new Date()) + getString(R.string.log_strart_play));
                volumeBar.setProgress(mPlayer.getVolume());
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlayer.isPlaying()) {
                    pause();
                    pauseBtn.setText(R.string.resume_button);
                } else {
                    resume();
                    pauseBtn.setText(R.string.pause_button);
                }
            }
        });

        replayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isCompleted = false;
                inSeek = false;
                replay();
            }
        });


        progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mPlayer != null) {
                    seekTo(seekBar.getProgress());
                    logStrs.add(format.format(new Date()) + getString(R.string.log_seek_start));

                    Log.d("lfj0929", "onStopTrackingTouch , inSeek= " + inSeek);
                }
            }
        });

        brightnessBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (fromUser && mPlayer != null) {
                    mPlayer.setScreenBrightness(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (fromUser && mPlayer != null) {
                    mPlayer.setVolume(progress);
                    muteOnBtn.setChecked(false);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        Log.e("lfj0930", "VodmodeAtivity onCreate()");
        mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            public void surfaceCreated(SurfaceHolder holder) {
//                holder.setType(SurfaceHolder.SURFACE_TYPE_GPU);
                holder.setKeepScreenOn(true);
                Log.e("lfj0930", "surfaceCreated ");
                // Important: surfaceView changed from background to front, we need reset surface to mediaplayer.
                // 对于从后台切换到前台,需要重设surface;部分手机锁屏也会做前后台切换的处理
                if (mPlayer != null) {
                    mPlayer.setVideoSurface(holder.getSurface());
                }

            }

            public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {

                Log.e("lfj0930", "surfaceChanged ");
                if (mPlayer != null) {
                    mPlayer.setSurfaceChanged();
                }
            }

            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.e("lfj0930", "surfaceDestroyed ");
//                if (mPlayer != null) {
//                    mPlayer.releaseVideoSurface();
//                }
            }
        });

        initVodPlayer();
        setPlaySource();
        netWatchdog = new NetWatchdog(this);
        netWatchdog.setNetChangeListener(new NetWatchdog.NetChangeListener() {
            @Override
            public void onWifiTo4G() {
                if (mPlayer.isPlaying()) {
                    pause();
                }
                if (netChangeDialog == null || !netChangeDialog.isShowing()) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(VodModeActivity.this);
                    alertDialog.setTitle(getString(R.string.net_change_to_4g));
                    alertDialog.setMessage(getString(R.string.net_change_to_continue));
                    alertDialog.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            int playerState = mPlayer.getPlayerState();
                            if(playerState == AliVcMediaPlayer.STOPPED ){
                                seekTo(progressBar.getProgress());
                                mPlayer.prepareAndPlay(mUrl);
                            }
                            else  if (playerState == AliVcMediaPlayer.PAUSED) {
                                resume();
                            } else {
                                stop();
                                seekTo(progressBar.getProgress());
                                start();
                            }
                        }
                    });
                    alertDialog.setNegativeButton(getString(R.string.no), null);
                    netChangeDialog = alertDialog.create();
                    netChangeDialog.show();
                }

                Toast.makeText(VodModeActivity.this.getApplicationContext(), R.string.net_change_to_4g, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void on4GToWifi() {
                Toast.makeText(VodModeActivity.this.getApplicationContext(), R.string.net_change_to_wifi, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNetDisconnected() {
                Toast.makeText(VodModeActivity.this.getApplicationContext(), R.string.net_disconnect, Toast.LENGTH_SHORT).show();
            }
        });
        netWatchdog.startWatch();
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_vod_mode;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    private void seekTo(int position) {
        if (mPlayer == null) {
            return;
        }

        if (isCompleted) {
            inSeek = false;
            return ;
        }


        if (lastSeekTime < 0) {
            lastSeekTime = System.currentTimeMillis();

            inSeek = true;
            mPlayer.seekTo(position);
        } else {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastSeekTime > 1000) {//1000ms
                inSeek = true;
                mPlayer.seekTo(position);
                lastSeekTime = currentTime;
            }
        }

    }

    private long lastSeekTime = -1;

    private AlertDialog netChangeDialog = null;

    private void initVodPlayer() {
        mPlayer = new AliVcMediaPlayer(this, mSurfaceView);
//开启缓存
//        String sdDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/test_save_cache";
//        mPlayer.setPlayingCache(true, sdDir, 60 * 60 /*时长, s */, 300 /*大小，MB*/);
//开启循环播放
//        mPlayer.setCirclePlay(true);

        mPlayer.setPreparedListener(new MyPrepareListener(this));
        mPlayer.setPcmDataListener(new MyPcmDataListener(this));
        mPlayer.setCircleStartListener(new MyCircleStartListener(this));
        mPlayer.setFrameInfoListener(new MyFrameInfoListener(this));
        mPlayer.setErrorListener(new MyErrorListener(this));
        mPlayer.setCompletedListener(new MyCompletedListener(this));
        mPlayer.setSeekCompleteListener(new MySeekCompleteListener(this));
        mPlayer.setStoppedListener(new MyPlayerStoppedListener(this));
        //打开、关闭播放器日志
        mPlayer.enableNativeLog();
//        mPlayer.disableNativeLog();
    }

    private static class MyPrepareListener implements MediaPlayer.MediaPlayerPreparedListener {
        private WeakReference<VodModeActivity> vodModeActivityWeakReference;

        public MyPrepareListener(VodModeActivity vodModeActivity) {
            vodModeActivityWeakReference = new WeakReference<VodModeActivity>(vodModeActivity);
        }

        @Override
        public void onPrepared() {
            VodModeActivity vodModeActivity = vodModeActivityWeakReference.get();
            if (vodModeActivity != null) {
                vodModeActivity.onPrepared();
            }
        }
    }

    private void onPrepared() {
        Toast.makeText(VodModeActivity.this.getApplicationContext(), R.string.toast_prepare_success, Toast.LENGTH_SHORT).show();
        logStrs.add(format.format(new Date()) + getString(R.string.log_prepare_success));

        mPlayer.play();
        inSeek = false;
        pauseBtn.setText(R.string.pause_button);
    }

    private static class MyPcmDataListener implements MediaPlayer.MediaPlayerPcmDataListener {

        private WeakReference<VodModeActivity> vodModeActivityWeakReference;

        public MyPcmDataListener(VodModeActivity vodModeActivity) {
            vodModeActivityWeakReference = new WeakReference<VodModeActivity>(vodModeActivity);
        }


        @Override
        public void onPcmData(byte[] bytes, int i) {
            VodModeActivity vodModeActivity = vodModeActivityWeakReference.get();
            if (vodModeActivity != null) {
                vodModeActivity.onPcmData(bytes, i);
            }
        }
    }

    private void onPcmData(byte[] bytes, int i) {
        //pcm数据获取到了
    }

    private static class MyCircleStartListener implements MediaPlayer.MediaPlayerCircleStartListener {
        private WeakReference<VodModeActivity> vodModeActivityWeakReference;

        public MyCircleStartListener(VodModeActivity vodModeActivity) {
            vodModeActivityWeakReference = new WeakReference<VodModeActivity>(vodModeActivity);
        }

        @Override
        public void onCircleStart() {

            VodModeActivity vodModeActivity = vodModeActivityWeakReference.get();
            if (vodModeActivity != null) {
                vodModeActivity.onCircleStart();
            }
        }
    }

    private void onCircleStart() {
        //循环播放开始
        Log.d("lfj0929", "MediaPlayerCircleStartListener onCircleStart  ");
    }

    private static class MyErrorListener implements MediaPlayer.MediaPlayerErrorListener {

        private WeakReference<VodModeActivity> vodModeActivityWeakReference;

        public MyErrorListener(VodModeActivity vodModeActivity) {
            vodModeActivityWeakReference = new WeakReference<VodModeActivity>(vodModeActivity);
        }


        @Override
        public void onError(int i, String msg) {
            VodModeActivity vodModeActivity = vodModeActivityWeakReference.get();
            if (vodModeActivity != null) {
                vodModeActivity.onError(i, msg);
            }
        }
    }

    private void onError(int i, String msg) {
        pause();
        Toast.makeText(VodModeActivity.this.getApplicationContext(), getString(R.string.toast_fail_msg) + msg, Toast.LENGTH_SHORT).show();
    }

    private static class MyCompletedListener implements MediaPlayer.MediaPlayerCompletedListener {

        private WeakReference<VodModeActivity> vodModeActivityWeakReference;

        public MyCompletedListener(VodModeActivity vodModeActivity) {
            vodModeActivityWeakReference = new WeakReference<VodModeActivity>(vodModeActivity);
        }

        @Override
        public void onCompleted() {
            VodModeActivity vodModeActivity = vodModeActivityWeakReference.get();
            if (vodModeActivity != null) {
                vodModeActivity.onCompleted();
            }
        }
    }

    private void onCompleted() {
        isCompleted = true;
        showVideoProgressInfo();
        stopUpdateTimer();
    }

    private static class MySeekCompleteListener implements MediaPlayer.MediaPlayerSeekCompleteListener {


        private WeakReference<VodModeActivity> vodModeActivityWeakReference;

        public MySeekCompleteListener(VodModeActivity vodModeActivity) {
            vodModeActivityWeakReference = new WeakReference<VodModeActivity>(vodModeActivity);
        }

        @Override
        public void onSeekCompleted() {
            VodModeActivity vodModeActivity = vodModeActivityWeakReference.get();
            if (vodModeActivity != null) {
                vodModeActivity.onSeekCompleted();
            }
        }
    }

    private void onSeekCompleted() {
        logStrs.add(format.format(new Date()) + getString(R.string.log_seek_completed));
        inSeek = false;
        Log.d("lfj0929", "MediaPlayerSeekCompleteListener inSeek = " + inSeek);
    }

    private static class MyPlayerStoppedListener implements MediaPlayer.MediaPlayerStoppedListener {

        private WeakReference<VodModeActivity> vodModeActivityWeakReference;

        public MyPlayerStoppedListener(VodModeActivity vodModeActivity) {
            vodModeActivityWeakReference = new WeakReference<VodModeActivity>(vodModeActivity);
        }

        @Override
        public void onStopped() {
            VodModeActivity vodModeActivity = vodModeActivityWeakReference.get();
            if (vodModeActivity != null) {
                vodModeActivity.onStopped();
            }
        }
    }

    private void onStopped() {
        logStrs.add(format.format(new Date()) + getString(R.string.log_play_stopped));
    }

    private static class MyFrameInfoListener implements MediaPlayer.MediaPlayerFrameInfoListener {

        private WeakReference<VodModeActivity> vodModeActivityWeakReference;

        public MyFrameInfoListener(VodModeActivity vodModeActivity) {
            vodModeActivityWeakReference = new WeakReference<VodModeActivity>(vodModeActivity);
        }

        @Override
        public void onFrameInfoListener() {
            VodModeActivity vodModeActivity = vodModeActivityWeakReference.get();
            if (vodModeActivity != null) {
                vodModeActivity.onFrameInfoListener();
            }
        }
    }

    private void onFrameInfoListener() {
        inSeek = false;
        showVideoProgressInfo();
        showVideoSizeInfo();

        updateLogInfo();
    }

    private void updateLogInfo() {
        Map<String, String> debugInfo = mPlayer.getAllDebugInfo();
        long createPts = 0;
        if (debugInfo.get("create_player") != null) {
            String time = debugInfo.get("create_player");
            createPts = (long) Double.parseDouble(time);
            logStrs.add(format.format(new Date(createPts)) + getString(R.string.log_player_create_success));
        }
        if (debugInfo.get("open-url") != null) {
            String time = debugInfo.get("open-url");
            long openPts = (long) Double.parseDouble(time) + createPts;
            logStrs.add(format.format(new Date(openPts)) + getString(R.string.log_open_url_success));
        }
        if (debugInfo.get("find-stream") != null) {
            String time = debugInfo.get("find-stream");
            long findPts = (long) Double.parseDouble(time) + createPts;
            logStrs.add(format.format(new Date(findPts)) + getString(R.string.log_request_stream_success));
        }
        if (debugInfo.get("open-stream") != null) {
            String time = debugInfo.get("open-stream");
            long openPts = (long) Double.parseDouble(time) + createPts;
            logStrs.add(format.format(new Date(openPts)) + getString(R.string.log_start_open_stream));
        }
        logStrs.add(format.format(new Date()) + getString(R.string.log_first_frame_played));
    }

    private void setPlaySource() {
//        mUrl = getIntent().getStringExtra("url");
    }

    private void showVideoSizeInfo() {
        videoWidthTxt.setText(getString(R.string.video_width) + mPlayer.getVideoWidth() + " , ");
        videoHeightTxt.setText(getString(R.string.video_height) + mPlayer.getVideoHeight() + "   ");
    }

    private void showVideoProgressInfo() {
        if (mPlayer != null ) {
            int curPosition = (int) mPlayer.getCurrentPosition();
            int duration = (int) mPlayer.getDuration();
            int bufferPosition = mPlayer.getBufferPosition();
            Log.d("lfj0929", "curPosition = " + curPosition + " , duration = " + duration + " ， inSeek = " + inSeek);

            if (!inSeek) {
                positionTxt.setText(Formatter.formatTime(curPosition));
                durationTxt.setText(Formatter.formatTime(duration));
                progressBar.setMax(duration);
                progressBar.setSecondaryProgress(bufferPosition);
                progressBar.setProgress(curPosition);
            }
        }
        startUpdateTimer();
    }

    private void startUpdateTimer() {
        progressUpdateTimer.removeMessages(0);
        progressUpdateTimer.sendEmptyMessageDelayed(0, 1000);
    }

    private void stopUpdateTimer() {
        progressUpdateTimer.removeMessages(0);
    }

    private Handler progressUpdateTimer = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            showVideoProgressInfo();
        }
    };

    private void start() {

        Log.e("lfj0929", "VodmodeAtivity start() mPlayer  =  " + mPlayer);
        if (mPlayer != null) {
            mPlayer.prepareToPlay(mUrl);
        }
    }

    private void pause() {
        if (mPlayer != null) {
            mPlayer.pause();
            pauseBtn.setText(R.string.resume_button);
        }
    }

    private void stop() {

        if (mPlayer != null) {
            mPlayer.stop();
        }
    }

    private void resume() {
        if (mPlayer != null) {
            VcPlayerLog.d("lfj0927", "mPlayer.play");
            mPlayer.play();
            pauseBtn.setText(R.string.pause_button);
        }
    }

    private void destroy() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.destroy();
        }
    }

    private void replay() {
        Log.d("lfj0929", "VodmodeAtivity replay()");
        stop();
        start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("lfj0930", "VodmodeAtivity onResume()");

    }

    @Override
    protected void onStop() {
        super.onStop();
        //when view goto background,will pausethe player, so we save the player's status here,
        // and when activity resumed, we resume the player's status.
        savePlayerState();
        Log.e("lfj0930", "VodmodeAtivity onStop()");
    }

    private void savePlayerState() {
        if (mPlayer.isPlaying()) {
            //we pause the player for not playing on the background
            // 不可见，暂停播放器
            pause();
        }
    }

    @Override
    protected void onDestroy() {
        stop();
        destroy();
        stopUpdateTimer();
        progressUpdateTimer = null;
        netWatchdog.stopWatch();
        Log.e("lfj0930", "VodmodeAtivity onDestroy()");
        super.onDestroy();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {                //转为竖屏了。
            //显示状态栏
            this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            mSurfaceView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

            //设置view的布局，宽高之类
            ViewGroup.LayoutParams surfaceViewLayoutParams = mSurfaceView.getLayoutParams();
            surfaceViewLayoutParams.height = (int) (getWight(this) * 9.0f / 16);
            surfaceViewLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;

        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {                //转到横屏了。
            //隐藏状态栏
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            mSurfaceView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN);
            //设置view的布局，宽高
            ViewGroup.LayoutParams surfaceViewLayoutParams = mSurfaceView.getLayoutParams();
            surfaceViewLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            surfaceViewLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;

        }
    }

    public static int getWight(Context mContext) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        return screenWidth;
    }
}
