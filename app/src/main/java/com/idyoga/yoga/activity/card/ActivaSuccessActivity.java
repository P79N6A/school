package com.idyoga.yoga.activity.card;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.MainActivity;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.common.modle.PostResult;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class ActivaSuccessActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.btn_see)
    Button btnSee;
    @BindView(R.id.btn_bespoke)
    Button btnBespoke;
    @BindView(R.id.iv_back_yellow)
    RelativeLayout ivBackYellow;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(rlTitle).init();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_activa_success;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.iv_back, R.id.btn_see, R.id.btn_bespoke})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_see:
                startActivityWithoutExtras(MembershipCardActivity.class);
                finish();
                break;
            case R.id.btn_bespoke:
                startActivityWithoutExtras(MainActivity.class);
                PostResult postResult = new PostResult();
                postResult.setTag("toBespokeCourse");
                EventBus.getDefault().post(postResult);
                finish();
                break;
        }
    }
}
