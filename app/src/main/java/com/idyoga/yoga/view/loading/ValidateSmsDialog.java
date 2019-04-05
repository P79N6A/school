package com.idyoga.yoga.view.loading;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.utils.ToastUtil;

import de.greenrobot.event.EventBus;

/**
 * @author weilgu
 * @time 2019/2/15 11:23
 * @des 奖励会员积分 的短信验证码弹出框
 */

public class ValidateSmsDialog extends Dialog {
    private Context mContext;
    private String mMobile;

    public ValidateSmsDialog(@NonNull Context context) {
        super(context, R.style.getMsm_dialog);
        this.mContext = context;
    }

    public ValidateSmsDialog(@NonNull Context context, String mobile) {
        super(context, R.style.getMsm_dialog);
        this.mContext = context;
        this.mMobile = mobile;
    }

    public ValidateSmsDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_msm);

        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
        ImageView ivClose = findViewById(R.id.iv_close);
        TextView tvHint = findViewById(R.id.tv_hint);
        RelativeLayout rlSee = findViewById(R.id.rl_exchanger);
        if (mMobile != null){
            tvHint.setText("明天继续签到\n可以获得10积分哦");
        }
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateSmsDialog.this.dismiss();
            }
        });
        rlSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new PostResult("exchangerPoint"));
                dismiss();
            }
        });
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void onEvent(PostResult postResult){

    }
}
