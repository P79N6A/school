package com.idyoga.yoga.activity.course;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.UserStudyCourseAdapter;
import com.idyoga.yoga.adapter.base.BaseDelegateAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.model.VideoCourseListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import vip.devkit.library.Logcat;
import vip.devkit.view.common.suklib.view.FlowLayout.FlowLayout;
import vip.devkit.view.common.suklib.view.FlowLayout.TagAdapter;
import vip.devkit.view.common.suklib.view.FlowLayout.TagFlowLayout;

/**
 * 文 件 名: UserStudyCourseActivity
 * 创 建 人: By k
 * 创建日期: 2018/5/9 09:28
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class UserStudyCourseActivity extends BaseActivity implements ICommonViewUi {
    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.rl_list)
    RecyclerView mRvList;


    DelegateAdapter adapters;
    BaseDelegateAdapter studyCourseAdapter, buyCourseAdapter;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).flymeOSStatusBarFontColor("#333333").init();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_study_course;
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("练习列表");
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRvList.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        mRvList.setLayoutManager(layoutManager);
        adapters = new DelegateAdapter(layoutManager, true);// 主 /父 adapter
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setItemCount(1);
        linearLayoutHelper.setMarginBottom(100);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add("ITEM" + i);
        }
        initViewTypeAll();
        mRvList.setAdapter(adapters);
        Logcat.i("TAG");
    }

    private void initViewTypeAll() {
        initStudyCourse();
        initBuyCourse();
        // Context context, LayoutHelper layoutHelper, int layoutId, int count, int viewTypeItem) {
    }

    private void initStudyCourse() {
        studyCourseAdapter = new BaseDelegateAdapter(this, new LinearLayoutHelper(), R.layout.item_user_course_video, 0, 1) {
            @Override
            public void onBindViewHolder(final BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (holder.getAdapterPosition() == 0) {
                    holder.getView(R.id.rl_course_layout).setVisibility(View.VISIBLE);
                } else {
                    holder.getView(R.id.rl_course_layout).setVisibility(View.GONE);
                }
                Logcat.i("mCount:"+mCount);
                if (position==mCount-1){
                    holder.getView(R.id.ll_layout).setVisibility(View.VISIBLE);
                }else {
                    holder.getView(R.id.ll_layout).setVisibility(View.GONE);
                }
                holder.setText(R.id.tv_count, "10套")
                        .setText(R.id.tv_course_name, "111")
                        .setText(R.id.tv_course_count, position + "/10");
                holder.getView(R.id.tv_del).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        studyCourseAdapter.setCount(10);
                        studyCourseAdapter.notifyDataSetChanged();
                    }
                });
                holder.getView(R.id.ll_layout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        studyCourseAdapter.setCount(10);
                        studyCourseAdapter.notifyDataSetChanged();
                    }
                });
            }
        };
        adapters.addAdapter(studyCourseAdapter);
        studyCourseAdapter.setCount(5);
        studyCourseAdapter.notifyDataSetChanged();
    }

    private void initBuyCourse() {
        buyCourseAdapter = new BaseDelegateAdapter(this, new LinearLayoutHelper(), R.layout.item_user_course_buy, 10, 2) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                Logcat.i("po:" + position + "/" + holder.getAdapterPosition());
                if (position == 0) {
                    holder.getView(R.id.rl_layout).setVisibility(View.VISIBLE);
                } else {
                    holder.getView(R.id.rl_layout).setVisibility(View.GONE);
                }
                Glide.with(mContext).load("").placeholder(R.drawable.img_course).error(R.drawable.img_course).into((ImageView) holder.getView(R.id.iv_img));
                holder.setText(R.id.tv_course_name, "购买的课程：" + position)
                        .setText(R.id.tv_course_tutor, "导师：")
                        .setText(R.id.tv_course_time, "时间：" + "")
//                        .setText(R.id.tv_course_price_1, mContext.getString(R.string.￥, "99"));
                        .setText(R.id.tv_course_price_1, "￥" + "99");
                holder.setVisible(R.id.tv_course_price_2, false);
                TagFlowLayout flowLayout = holder.getView(R.id.tag_view);
                List list = new ArrayList();
                flowLayout.setAdapter(new TagAdapter<VideoCourseListBean.VideoLabelBean>(list) {
                    @Override
                    public View getView(FlowLayout parent, int position, VideoCourseListBean.VideoLabelBean videoLabelBean) {
                        LayoutInflater mInflater = LayoutInflater.from(mContext);
                        TextView tv = (TextView) mInflater.inflate(R.layout.layout_option_tag, parent, false);
                        tv.setText(videoLabelBean.getName());
                        tv.setTextColor(Color.parseColor("#333333"));
                        tv.setBackgroundResource(R.drawable.bg_serach_list_course_tag);
                        return tv;
                    }
                });

            }
        };
        adapters.addAdapter(buyCourseAdapter);
    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.ll_title_back, R.id.tv_title_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.tv_title_text:
                break;
        }
    }


    @Override
    public void toRequest(int eventTag) {

    }

    @Override
    public void getRequestData(int eventTag, String result) {

    }

    @Override
    public void onRequestFailureException(int eventTag, String msg) {

    }

}