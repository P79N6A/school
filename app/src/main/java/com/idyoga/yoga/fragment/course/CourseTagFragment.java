package com.idyoga.yoga.fragment.course;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.CourseTagFmAdapter;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.model.CoureseDetailInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by mjh on 2019/1/21.
 */

public class CourseTagFragment extends BaseFragment {

    @BindView(R.id.rlv_tag)
    RecyclerView mRlvTag;
    @BindView(R.id.ll_more)
    LinearLayout mLlMore;
    private List<CoureseDetailInfo.Label> mLabelList;
    private LinearLayoutManager mLayoutManager;
    private CourseTagFmAdapter mTagFmAdapter;
    private String mLabels;

    @Override
    protected void initData() {
        super.initData();
        if (mLabels != null){
            mLabelList = JSON.parseArray(mLabels, CoureseDetailInfo.Label.class);
            if (mTagFmAdapter != null){
                mTagFmAdapter.setDatas(mLabelList);
            }
        }
    }

    public void setLabels(String labels){
        this.mLabels = labels;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRlvTag.setLayoutManager(mLayoutManager);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mTagFmAdapter = new CourseTagFmAdapter(getContext());
        mRlvTag.setAdapter(mTagFmAdapter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_course_tag_layout;
    }

    @OnClick(R.id.ll_more)
    public void onViewClicked() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLlMore.setVisibility(View.GONE);
        mTagFmAdapter.setOrientation(false);
    }
}
