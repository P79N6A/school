package com.idyoga.yoga.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


import com.idyoga.yoga.R;
import com.idyoga.yoga.listener.DialogClickListener;
import com.idyoga.yoga.utils.YogaViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 文 件 名: CommonDialog
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/4/23
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class CommonDialog extends Dialog {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_msg)
    TextView mTvMsg;
    @BindView(R.id.tv_sure)
    Button mBtnSure;
    @BindView(R.id.tv_cancel)
    Button mBtnCancel;
    private Context mContext;
    private String dialogTitle;
    private String dialogMsg;
    private DialogClickListener mClickListener;

    public void setClickListener(DialogClickListener clickListener) {
        mClickListener = clickListener;
    }

    public void setDialogData(String dialogTitle, String dialogMsg) {
        this.dialogTitle = dialogTitle;
        this.dialogMsg = dialogMsg;
        initView();
    }

    public CommonDialog(@NonNull Context context) {
        super(context, R.style.common_dialog);
        this.mContext = context;
        initView();
    }

    public CommonDialog(@NonNull Context context, String dialogTitle, String dialogMsg) {
        super(context, R.style.common_dialog);
        this.mContext = context;
        this.dialogTitle = dialogTitle;
        this.dialogMsg = dialogMsg;
        initView();
    }

    private void setListener() {
        viewHolder.mTvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener!=null){
                    mClickListener.onSure();
                }
                dismiss();
            }
        });
        viewHolder.mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener!=null){
                    mClickListener.onCancel();
                }
                dismiss();
            }
        });
    }

    ViewHolder viewHolder;

    private void initView() {
        View view = View.inflate(mContext, R.layout.dialog_common, null);
        setContentView(view);
        viewHolder = new ViewHolder(view);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        params.width = YogaViewUtil.getScreenWidth(mContext) / 2 + YogaViewUtil.getScreenWidth(mContext) / 4;
        window.setAttributes(params);
        window.setWindowAnimations(R.style.popupwindow_anim_style);
        setListener();
        viewHolder.mTvTitle.setText(dialogTitle);
        viewHolder.mTvMsg.setText(dialogMsg);
    }
    class ViewHolder {
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_msg)
        TextView mTvMsg;
        @BindView(R.id.tv_sure)
        TextView mTvSure;
        @BindView(R.id.tv_cancel)
        TextView mTvCancel;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
