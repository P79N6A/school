package com.idyoga.yoga.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.idyoga.yoga.R;
import com.idyoga.yoga.model.DialogCallback;
import com.idyoga.yoga.utils.YogaViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 文 件 名: ConfirmDialog
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/9/27
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ConfirmDialog extends Dialog {
    private Context mContext;
    private String dialogMsg;
    private DialogCallback mCallback;

    public ConfirmDialog setDialogCallback(DialogCallback callback) {
        mCallback = callback;
        return this;
    }

    public ConfirmDialog setMsg(String msg) {
        this.dialogMsg = msg;
        return this;
    }

    public ConfirmDialog(@NonNull Context context) {
        super(context, R.style.common_dialog);
        this.mContext = context;
    }

    public ConfirmDialog(@NonNull Context context, String dialogMsg) {
        super(context, R.style.common_dialog);
        this.mContext = context;
        this.dialogMsg = dialogMsg;
    }

    public ConfirmDialog build(){
        initView();
        return this;
    }

    @Override
    public void show() {
        super.show();
    }


    private void setListener() {
        viewHolder.mTvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onSure(ConfirmDialog.this);
                dismiss();
            }
        });
    }

    ViewHolder viewHolder;
    private void initView() {
        View view = View.inflate(mContext, R.layout.dialog_confirm, null);
        setContentView(view);
        viewHolder = new ViewHolder(view);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        params.width = YogaViewUtil.getScreenWidth(mContext) / 2 + YogaViewUtil.getScreenWidth(mContext) / 4;
        window.setAttributes(params);
        window.setWindowAnimations(R.style.popupwindow_anim_style);
        setListener();
        viewHolder.mTvMsg.setText(dialogMsg+"");
    }

    class ViewHolder {
        @BindView(R.id.tv_msg)
        TextView mTvMsg;
        @BindView(R.id.tv)
        TextView mTvSure;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

