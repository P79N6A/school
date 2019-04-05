package com.idyoga.yoga.fragment.child;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseFragment;

import butterknife.BindView;

/**
 * 文 件 名: FragmentCourseContent
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 创建日期: 2018/3/19
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class FragmentContent extends BaseFragment {


    @BindView(R.id.tv_tag)
    TextView mTvTag;

    public static FragmentContent getInstance(Bundle bundle) {
        FragmentContent fragment = new FragmentContent();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

}
