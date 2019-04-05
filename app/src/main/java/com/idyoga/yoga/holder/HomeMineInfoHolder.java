package com.idyoga.yoga.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.MainActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.model.HomePageData;
import com.idyoga.yoga.model.HomeRecommendBean;
import com.idyoga.yoga.model.UserSignInBean;
import com.idyoga.yoga.utils.LoginUtil;
import com.idyoga.yoga.view.loading.ValidateSmsDialog;

import de.greenrobot.event.EventBus;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * @author weilgu
 * @time 2019/3/20 15:10
 * @des ${TODO}
 */

public class HomeMineInfoHolder extends BaseHolder implements View.OnClickListener{ //RecyclerView.ViewHolder implements View.OnClickListener {

    private Context mContext;
    private ImageView mHead;
    private TextView mName;
    private TextView mTvLoginBtn;
    private TextView mSignIn;
    private TextView mPsints;
    private TextView mDayNum;
    private TextView mGoLogin;
    private RelativeLayout mLlLogin;
    private RelativeLayout mUserState;

    public HomeMineInfoHolder(View itemView, Context context) {
        super(itemView);
        this.mContext = context;
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        mHead = (ImageView)itemView.findViewById(R.id.iv_home_hp);
        mName = (TextView)itemView.findViewById(R.id.tv_name);
        mTvLoginBtn = (TextView)itemView.findViewById(R.id.tv_login_btn);
        mSignIn = (TextView)itemView.findViewById(R.id.tv_sign_in);
        mPsints = (TextView)itemView.findViewById(R.id.tv_points);
        mDayNum = (TextView)itemView.findViewById(R.id.tv_num_day);
        mGoLogin = (TextView)itemView.findViewById(R.id.tv_go_login);
        mUserState = itemView.findViewById(R.id.rl_user_state);
        mLlLogin = itemView.findViewById(R.id.ll_login);
        mTvLoginBtn.setOnClickListener(this);//本周已连续签到4天
        mGoLogin.setOnClickListener(this);
        mSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_sign_in: //打卡
                //打卡,成功后显示打卡成功dialog ClickPointDialog
                EventBus.getDefault().post(new PostResult("homeSignIn"));
//                EventBus.getDefault().post(new PostResult("homeSignInResult"));
                Logcat.i("**************************onClick***" + Thread.currentThread().getName());
                //new ValidateSmsDialog((MainActivity)mContext,"88888888888").show();
                break;
            case R.id.iv_home_hp:
                if (!LoginUtil.checkLogin(mContext)) return;
                break;
            case R.id.tv_login_btn:
                if (!LoginUtil.checkLogin(mContext)) return;
                break;
            case R.id.ll_login:
                if (!LoginUtil.checkLogin(mContext)) return;
                break;
        }
    }

    public void onEvent(PostResult postResult) {
        if (postResult.getTag().equals("homeSignInResult")) {
            Logcat.i("**************************onEvent***" + Thread.currentThread().getName());
            UserSignInBean bean = (UserSignInBean)postResult.getResult();
            int integralNumber = bean.getUserIntegralNumber();
            mPsints.setText("我的积分: " + integralNumber);
            new ValidateSmsDialog((MainActivity)mContext,"111111111").show();
        } else if(postResult.getTag().equals("")){

        }
    }

    @Override
    public void bindView(Object data) {
        HomePageData.UserDataBean bean = (HomePageData.UserDataBean) data;
        if (bean.isLogin()){
            mUserState.setVisibility(View.VISIBLE);
            mLlLogin.setVisibility(View.GONE);
            String head_pic = bean.getHead_pic();
            mName.setText(bean.getName());
            mDayNum.setText("本周已连续签到" + bean.getUserClockNumber() + "天");
            mPsints.setText("我的积分: " + bean.getIntegralNumber());
            Glide.with(mContext)
                    .load(head_pic)
                    .placeholder(R.drawable.img_06)
                    .error(R.drawable.img_06)
                    .into(mHead);
        }else{//未登录的情况
            mUserState.setVisibility(View.GONE);
            mLlLogin.setVisibility(View.VISIBLE);
            mHead.setImageDrawable(mContext.getResources().getDrawable(R.drawable.img_06));
        }

    }
}
