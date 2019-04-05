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

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.model.AdvertiBean;

import de.greenrobot.event.EventBus;

/**
 * @author weilgu
 * @time 2019/2/15 11:23
 * @des 广告弹出窗
 */

public class AdvertiDialog extends Dialog {
    private Context mContext;
    private AdvertiBean mBean;

    public AdvertiDialog(@NonNull Context context) {
        super(context, R.style.getMsm_dialog);
        this.mContext = context;
    }

    public AdvertiDialog(@NonNull Context context, AdvertiBean bean) {
        super(context, R.style.getMsm_dialog);
        this.mContext = context;
        this.mBean = bean;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_adverti_layout);

        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
        ImageView ivClose = findViewById(R.id.iv_close);
        ImageView ivAdverti = findViewById(R.id.iv_adverti);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdvertiDialog.this.dismiss();
            }
        });
        ivAdverti.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new PostResult("advertiDetail",mBean.getUrl()));
                dismiss();
            }
        });
        Glide.with(mContext)
                .load(mBean.getImage_url())
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into(ivAdverti);
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
