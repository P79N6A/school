package com.idyoga.yoga.activity.course;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import vip.devkit.library.DensityUtil;
import vip.devkit.view.common.suklib.view.FlowLayout.TagFlowLayout;

/**
 * 文 件 名: OfflineCourseBuyActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/29
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class OfflineCourseBuyActivity extends BaseActivity {

    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R.id.ll_title_right)
    LinearLayout mLlTitleRight;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.iv_img)
    ImageView mIvImg;
    @BindView(R.id.tv_course_name)
    TextView mTvCourseName;
    @BindView(R.id.tv_course_tutor)
    TextView mTvCourseTutor;
    @BindView(R.id.tv_course_time)
    TextView mTvCourseTime;
    @BindView(R.id.rl_itemView)
    RelativeLayout mRlItemView;
    @BindView(R.id.tag_view)
    TagFlowLayout mTagView;
    @BindView(R.id.ll_layout_item)
    LinearLayout mLlLayoutItem;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_tell)
    TextView mTvTell;
    @BindView(R.id.tv_buy_card)
    TextView mTvBuyCard;
    @BindView(R.id.rg_view)
    RadioGroup mRgView;
    @BindView(R.id.iv_share)
    ImageView mIvShare;
    @BindView(R.id.tv_consult)
    ImageView mTvConsult;
    @BindView(R.id.tv_next)
    TextView mTvNext;
    @BindView(R.id.ll_foot_layout)
    RelativeLayout mLlFootLayout;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).flymeOSStatusBarFontColor("#333333").init();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_course_offline_buy;
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("支付");
        mTvCourseName.setText("现联瑜伽课");
        mTvCourseName.setText("Jacket");
        mTvCourseTime.setText("");
        mTvAddress.setText("上课地点：广州市科韵路0001号");
        mTvTime.setText("上课时间：2018-5-30 8:00");
        mTvTell.setText("联系方式：400-15465");
        Glide.with(this)
                .load("https://img.alicdn.com/tps/TB1BQh7LpXXXXcJXFXXXXXXXXXX-198-46.gif")
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into(mIvImg);

        for (int i = 0; i < 3; i++) {
            RadioButton button = (RadioButton) LayoutInflater.from(this).inflate(R.layout.layout_radiobutton, mRgView, false);
            button.setText("权益卡" + i);
            mRgView.addView(button);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
            layoutParams.weight = LinearLayout.LayoutParams.MATCH_PARENT;
            layoutParams.height = DensityUtil.dp2px(this, 48);
            layoutParams.setMargins(DensityUtil.dp2px(this, 15), 0, DensityUtil.dp2px(this, 15), 0);
            button.setLayoutParams(layoutParams);
        }
    }

    @Override
    protected void setListener() {

    }


    @OnClick({R.id.ll_title_back, R.id.tv_buy_card, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.tv_buy_card:
                showBuyCardDialog("400-123456");
                break;
            case R.id.tv_next:
                ToastUtil.showToast("敬请期待");
//                showPayDialog();
                break;
        }
    }

    Dialog dialog;

    public void showBuyCardDialog(String tellNum) {
        dialog = new Dialog(mContext, R.style.quick_option_dialog);
        dialog.setContentView(R.layout.dialog_buy_card);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = DensityUtil.dp2px(this, 270);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setWindowAnimations((R.style.popWindow_anim_style));
        ((TextView) dialog.findViewById(R.id.tv_tell)).setText(tellNum);
        dialog.findViewById(R.id.tv_cancel_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public void showPayDialog() {
        Dialog dialog = new Dialog(mContext, R.style.quick_option_dialog);
        dialog.setContentView(R.layout.dialog_share);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.CENTER);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setWindowAnimations((R.style.popWindow_anim_style));
        dialog.show();
    }

}
