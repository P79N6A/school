package com.idyoga.yoga.utils;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

import com.idyoga.yoga.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 验证码倒计时
 */
public class TextTimeUtils {
    private int time=60;
    private int runTime=time;
    private Timer timer;
    private String tag;
    private TextView btnSure;
    private String textColor = "#cccccc";


    public TextTimeUtils(TextView btnSure) {
        super();
        this.btnSure = btnSure;
    }
    public TextTimeUtils(Button btnSure, int time) {
        super();
        this.btnSure = btnSure;
        this.time=time;
    }public TextTimeUtils(Button btnSure, String tag) {
        super();
        this.btnSure = btnSure;
        this.tag=tag;
    }
    public  void StopTimer(){
        if (timer!=null){
            timer.cancel();
        }
        runTime=time;
        btnSure.setTextColor(Color.parseColor("#8338F9"));
//        btnSure.setBackgroundResource(R.drawable.bg_login_et);
        btnSure.setText("发送验证码");
        btnSure.setEnabled(true);
    }

    public void setTextColor(String textColor){
        this.textColor = "textColor";
    }

    public void RunTimer(){
        timer=new Timer();
        TimerTask task=new TimerTask() {

            @Override
            public void run(){
                runTime--;
                Message msg=handler.obtainMessage();
                msg.what=1;
                handler.sendMessage(msg);

            }
        };
        timer.schedule(task, 100, 1000);
    }

    private Handler handler =new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if(runTime>0){
                        btnSure.setEnabled(false);
                        if (tag!=null){
                            btnSure.setText(tag+"("+runTime+")");
                        }else {
                            btnSure.setTextColor(Color.parseColor("#cccccc"));
//                            btnSure.setBackgroundResource(R.drawable.bg_a_08);
                            btnSure.setText("重新发送("+runTime+")");
                        }
                    }else{
                        timer.cancel();
                        btnSure.setEnabled(true);
                        btnSure.setTextColor(Color.parseColor("#404b69"));
//                        btnSure.setBackgroundResource(R.drawable.bg_login_et);
                        btnSure.setText("重发");
                        runTime=time;
                    }
                    break;
                default:
                    break;
            }

        };
    };
}
