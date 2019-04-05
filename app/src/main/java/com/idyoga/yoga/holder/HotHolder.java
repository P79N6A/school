package com.idyoga.yoga.holder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.SpecialVideoAdapter;
import com.idyoga.yoga.model.HomePageData;
import com.idyoga.yoga.model.HomeRecommendBean;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/11 15:38
 * @des home 专题
 */

public class HotHolder extends BaseHolder{//RecyclerView.ViewHolder  {

    private Context mContext;
    private TextView mHeadName;
    //private View mInclude;
    private RecyclerView mRlvVideo;
    private RelativeLayout mRlRoot;
    private SpecialVideoAdapter mVideoAdapter;

    public HotHolder(View itemView) {
        this(itemView,null);
    }

    @Override
    public void bindView(Object data) {
        List<HomePageData.SubjectListBean> listBeans = (List<HomePageData.SubjectListBean>) data;
        mVideoAdapter.setDatas(listBeans);
        mHeadName.setText("专题活动");
    }

    public HotHolder(View itemView, Context context){
        super(itemView);
        this.mContext = context;
        initView(itemView);
    }

    private void initView(View itemView) {
        mHeadName = (TextView)itemView.findViewById(R.id.tv_head_name);
        //mInclude = itemView.findViewById(R.id.i_head);
        mRlRoot = (RelativeLayout)itemView.findViewById(R.id.rl_root);
        mRlvVideo = (RecyclerView) itemView.findViewById(R.id.rlv_hot_video);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRlvVideo.setLayoutManager(layoutManager);
        mVideoAdapter = new SpecialVideoAdapter(mContext);
        mRlvVideo.setAdapter(mVideoAdapter);
    }

    /*public void bindView(List<HomePageData.SubjectListBean> beans){
        mVideoAdapter.setDatas(beans);
        mHeadName.setText("专题活动");
    }*/
}
