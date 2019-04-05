package com.idyoga.yoga.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseFragment;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.RichType;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author weilgu
 * @time 2019/3/13 17:21
 * @des 课程详情
 */

public class CourseDetailFragment extends BaseFragment {


    @BindView(R.id.tv_content)
    TextView mTvContent;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_course_detail_layout;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected void initData() {
        super.initData();
        RichText.from("data").type(RichType.html).bind(this).into(mTvContent);
        //.errorImage() // 设置加载失败的错误图
        //.placeHolder(placeHolder) // 设置加载中显示的占位图
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //RichText.clear();
    }
}
