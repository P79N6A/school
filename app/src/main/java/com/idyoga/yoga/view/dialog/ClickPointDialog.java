package com.idyoga.yoga.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idyoga.yoga.common.modle.PostResult;

import de.greenrobot.event.EventBus;


/**
 * @author weilgu
 * @time 2019/2/15 11:23
 * @des 首页签到成功 提示框
 */

public class ClickPointDialog extends Dialog {

    private Context mContext;
    private String mPoints;

    public ClickPointDialog(@NonNull Context context) {
//        super(context, com.idyoga.yoga.R.style.Clock_dialog);
        super(context, com.idyoga.yoga.R.style.card_dialog);
        this.mContext = context;
    }

    public ClickPointDialog(@NonNull Context context, String points) {
//        super(context, com.idyoga.yoga.R.style.Clock_dialog);
        super(context, com.idyoga.yoga.R.style.card_dialog);
        this.mContext = context;
        this.mPoints = points;
    }

    public ClickPointDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(com.idyoga.yoga.R.layout.activity_validate_msm);
        ImageView close = findViewById(com.idyoga.yoga.R.id.iv_close);
        TextView hint = findViewById(com.idyoga.yoga.R.id.tv_hint);
        hint.setText("明天继续签到\n可以获得10积分哦");
        RelativeLayout exchanger = findViewById(com.idyoga.yoga.R.id.rl_exchanger);

        exchanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new PostResult("exchangerPoint"));
            }
        });
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    public void onEvent(PostResult postResult){

    }
}
