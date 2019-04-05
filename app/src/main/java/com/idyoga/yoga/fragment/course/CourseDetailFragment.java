package com.idyoga.yoga.fragment.course;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseFragment;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.RichType;

import butterknife.BindView;

/**
 * Created by mjh on 2019/1/21.
 * 课程详情里的显示课程详情的fragment
 */

public class CourseDetailFragment extends BaseFragment {

    @BindView(R.id.tv_content)
    TextView mTvContent;
    private String mContent;

    public void setContent(String content) {
        this.mContent = content;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_course_detail_layout;
    }

    @Override
    protected void initData() {
        super.initData();
        RichText.from("data").type(RichType.html).bind(this).into(mTvContent);
    }
}
