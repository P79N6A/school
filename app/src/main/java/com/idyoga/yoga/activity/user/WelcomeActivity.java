package com.idyoga.yoga.activity.user;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_head)
    ImageView ivHead;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String headUrl = intent.getStringExtra("headUrl");
        if (!TextUtils.isEmpty(headUrl)){
            Glide.with(this).load(headUrl)
                    .placeholder(R.drawable.def_head)//加载成功前显示的图片
                    .error(R.drawable.def_head)//加载发生错误时显示的图片
                    .fallback(R.drawable.def_head)//url为空时
                    .into(ivHead);
        }else{
            ivHead.setImageDrawable(getResources().getDrawable(R.drawable.def_head));
        }
        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                });
            }
        };
        timer.schedule(task,2500);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_welcome2;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {

    }
}
