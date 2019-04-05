package com.idyoga.yoga.activity.shop;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.fragment.CreditFragment;
import com.idyoga.yoga.model.AdvertiBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.utils.ShowAdverti;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * @author weilgu
 * @time 2019/2/27 11:30
 * @des
 */

public class YogaShoppingActivity extends BaseActivity {


    @BindView(R.id.fl_content)
    FrameLayout mFlContent;
    private Bundle mExtras;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.flymeOSStatusBarFontColor("#333333").init();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mExtras = intent.getExtras();

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_yoga_shopping_layout;
    }

    @Override
    protected void initView() {
        CreditFragment creditFragment = new CreditFragment();
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mExtras != null){
            creditFragment.setArguments(mExtras);
        }
        transaction.replace(R.id.fl_content,creditFragment);
        transaction.commit();
    }

    @Override
    protected void setListener() {

    }
}
