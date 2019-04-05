package com.idyoga.yoga.holder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.VideoFItemInAdapter;
import com.idyoga.yoga.model.VideoHomeData;

import org.w3c.dom.Text;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/12 16:13
 * @des ${TODO}
 */

public class VideoHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private RelativeLayout mRlRoot;
    private TextView mHeadName;
    private TextView mTv;
    private RecyclerView mRlvVideoList;
    private VideoFItemInAdapter mAdapter;

    public VideoHolder(View itemView) {
        this(itemView,null);
    }

    public VideoHolder(View itemView, Context context) {
        super(itemView);
        this.mContext = context;
        mRlRoot = itemView.findViewById(R.id.rl_root);
        mTv = itemView.findViewById(R.id.tv);
        mHeadName = itemView.findViewById(R.id.tv_head_name);
        mRlvVideoList = itemView.findViewById(R.id.rlv_video_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mAdapter = new VideoFItemInAdapter(mContext);
        mRlvVideoList.setLayoutManager(layoutManager);
        mRlvVideoList.setAdapter(mAdapter);
    }

    public void bindView(VideoHomeData.VideoBean videoBean){
        String name = videoBean.getName();
        mHeadName.setText(name + "");
        List<VideoHomeData.VideoList> videoList = videoBean.getVideoList();
        mAdapter.setDatas(videoList);
    }
}
