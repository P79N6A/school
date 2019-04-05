package com.idyoga.yoga.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author weilgu
 * @time 2019/3/13 17:26
 * @des ${TODO}
 */

public class CoureseFunctionFragment extends BaseFragment {

    @BindView(R.id.rlv_tag)
    RecyclerView mRlvTag;
    @BindView(R.id.ll_down)
    LinearLayout mLlDown;
    @BindView(R.id.view)
    View mView;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_course_function_layout;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRlvTag.setLayoutManager(layoutManager);

    }

    @OnClick(R.id.ll_down)
    public void onViewClicked() {

    }
}
