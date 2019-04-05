package com.idyoga.yoga.holder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.MainActivity;
import com.idyoga.yoga.activity.course.CourseActivity;
import com.idyoga.yoga.activity.shop.YogaShoppingActivity;
import com.idyoga.yoga.activity.web.ConsultActivity;
import com.idyoga.yoga.model.HomePageData;
import com.idyoga.yoga.model.UserSignInBean;
import com.idyoga.yoga.model.UserSignInfoBean;
import com.idyoga.yoga.utils.GlideImgManager;
import com.idyoga.yoga.utils.LoginUtil;

import java.util.Map;

import vip.devkit.library.StringUtil;
import vip.devkit.view.common.dtextview.DrawableTextView;

/**
 * @author weilgu
 * @time 2019/3/11 15:34
 * @des home 登录信息 ,打卡,等
 */

public class SignHolder extends BaseHolder implements View.OnClickListener{ // RecyclerView.ViewHolder implements View.OnClickListener {

    private Context mContext;
    private DrawableTextView mMenu_1;
    private DrawableTextView mMenu_2;
    private DrawableTextView mMenu_3;
    private RelativeLayout mStudy;
    private RelativeLayout mRlRob;
    private HomePageData.JumpUrlArrBean mBean;

    public SignHolder(View itemView) {
        this(itemView, null);
    }

    @Override
    public void bindView(Object data) {
        this.mBean = (HomePageData.JumpUrlArrBean) data;
    }

    public SignHolder(View itemView, Context context) {
        super(itemView);
        this.mContext = context;
        initView(itemView);
    }

    private void initView(View itemView) {
        mMenu_1 = (DrawableTextView) itemView.findViewById(R.id.tv_menu_1);
        mMenu_2 = (DrawableTextView) itemView.findViewById(R.id.tv_menu_2);
        mMenu_3 = (DrawableTextView) itemView.findViewById(R.id.tv_menu_3);
        mRlRob = itemView.findViewById(R.id.rl_rob);
        mStudy = (RelativeLayout) itemView.findViewById(R.id.rl_study_course);
        mMenu_1.setOnClickListener(this);
        mMenu_2.setOnClickListener(this);
        mMenu_3.setOnClickListener(this);
        mStudy.setOnClickListener(this);
        mRlRob.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MainActivity activity = (MainActivity) mContext;
        switch (view.getId()) {
            case R.id.tv_menu_1:
                Bundle bundle = new Bundle();
                bundle.putString("getUrl", "http://wxyoga.hq-xl.com/contact/index");
                Intent intent = new Intent(activity, ConsultActivity.class);
                intent.putExtras(bundle);
                activity.startActivity(intent);
                break;
            case R.id.tv_menu_2:
                if (activity != null) {
                    activity.showFragment(2);
                }
                break;
            case R.id.tv_menu_3:
                Intent intent3 = new Intent(activity, YogaShoppingActivity.class);
                activity.startActivity(intent3);
                break;
            case R.id.rl_study_course:
                    /*if (!LoginUtil.checkLogin(mActivity)) return;
                    startActivityWithoutExtras(CourseActivity.class);*/
                break;

            case R.id.rl_rob:
                String integralMallUrl = mBean.getIntegralMallUrl();
                if (!LoginUtil.checkLogin(activity))
                    return;
                Intent intent1 = new Intent(activity, YogaShoppingActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("url", integralMallUrl);
                intent1.putExtras(bundle1);
                activity.startActivity(intent1);
                break;
        }
    }
}
