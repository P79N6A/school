package com.idyoga.yoga.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IntDef;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.view.YogaLayoutManager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import vip.devkit.library.Logcat;
import vip.devkit.library.StringUtil;


public class DialogUtil {
    @IntDef({SURE, CANCEL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ViewType {

    }

    public static final int SURE = 0x00000000;
    public static final int CANCEL = 0x00000001;
    private Context mContext;
    private Dialog mDialog;
    private String title;
    private String content;
    private boolean isDismiss = true;
    private onActionClickListener mActionClickListener;


    public static DialogUtil wrap(Context context) {
        return new DialogUtil(context);
    }

    public DialogUtil(Context mContext) {
        this.mContext = mContext;

    }

    public DialogUtil setData(String title, String content) {
        this.title = title;
        this.content = content;
        return this;
    }

    public DialogUtil setData(String title, String content, boolean isDismiss) {
        this.title = title;
        this.content = content;
        this.isDismiss = isDismiss;
        return this;
    }

    public DialogUtil setActionClickListener(onActionClickListener actionClickListener) {
        this.mActionClickListener = actionClickListener;
        return this;
    }

    public DialogUtil init(){
        if (!StringUtil.isEmpty(title)&&!StringUtil.isEmpty(title)){
            createDialog();
        }else {
            Logcat.e("请先设置初始化数据");
        }
        return this;
    }

    public void show() {
        if (mDialog != null) {
            mDialog.show();
        }
    }

    public void dismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    public void createDialog() {
        mDialog = new Dialog(mContext);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = View.inflate(mContext, R.layout.dialog_common, null);
        mDialog.setContentView(view);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(mDialog.getWindow().getAttributes());
        lp.width = YogaViewUtil.getScreenWidth(mContext) / 2 + YogaViewUtil.getScreenWidth(mContext) / 4;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mDialog.getWindow().setAttributes(lp);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (!StringUtil.isEmpty(title)) {
            ((TextView) mDialog.findViewById(R.id.tv_title)).setText(title);
        }
        if (!StringUtil.isEmpty(content)) {
            ((TextView) mDialog.findViewById(R.id.tv_msg)).setText(content);

        }
        mDialog.findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mActionClickListener != null) {
                    mActionClickListener.action(SURE, mDialog, view);
                }
            }
        });
        if (isDismiss) {
            mDialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
        } else {
            mDialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mActionClickListener != null) {
                        mActionClickListener.action(CANCEL, mDialog, view);
                    }
                }
            });
        }
    }


    public interface onActionClickListener {
        void action(@ViewType int viewType, Dialog dialog, View view);
    }
}
