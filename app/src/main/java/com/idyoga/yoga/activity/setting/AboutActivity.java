package com.idyoga.yoga.activity.setting;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 文 件 名: TestUploadFileActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/12
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class AboutActivity extends BaseActivity {
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
    @BindView(R.id.ll_web_layout)
    RelativeLayout mLlWebLayout;
    @BindView(R.id.tv_app_version)
    TextView tvAppVersion;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).init();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("关于我们");
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String versionName = packageInfo.versionName;
            tvAppVersion.setText(versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setListener() {

    }


    @OnClick({R.id.ll_title_back, R.id.ll_web_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.ll_web_layout:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
