package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.holder.VideoHolder;
import com.idyoga.yoga.model.VideoHomeData;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/21 11:23
 * @des ${TODO}
 */

public class ItemVideoHomeList extends BaseAdapter{//RecyclerView.Adapter<VideoHolder> {

    private Context mContext;
    private final LayoutInflater mFrom;
//    private List<VideoHomeData.VideoBean> mDatas;
    private List<VideoHomeData.VideoList> mDatas;

    public ItemVideoHomeList(Context context) {
        mContext = context;
        mFrom = LayoutInflater.from(mContext);
    }

//    public void setDatas(List<VideoHomeData.VideoBean> datas) {
    public void setDatas(List<VideoHomeData.VideoList> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return  mDatas != null ? mDatas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = mFrom.inflate(R.layout.item_video_fragment_layout,parent,false);
            holder.mRlRoot = convertView.findViewById(R.id.rl_root);
            holder.mTv = convertView.findViewById(R.id.tv);
            holder.mHeadName = convertView.findViewById(R.id.tv_head_name);
            holder.mRlvVideoList = convertView.findViewById(R.id.rlv_video_list);
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            holder.mAdapter = new VideoFItemInAdapter(mContext);
            holder.mRlvVideoList.setLayoutManager(layoutManager);
            holder.mRlvVideoList.setAdapter(holder.mAdapter);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
//        VideoHomeData.VideoBean videoBean = mDatas.get(position);
        VideoHomeData.VideoList videoBean = mDatas.get(position);
        //String name = videoBean.getName();
        holder.mHeadName.setText(videoBean.getTitle() + "");

        /*List<VideoHomeData.VideoList> videoList = videoBean.getVideoList();
        holder.mAdapter.setDatas(videoList);*/
        return convertView;
    }

    class ViewHolder {
        private RelativeLayout mRlRoot;
        private TextView mHeadName;
        private TextView mTv;
        private RecyclerView mRlvVideoList;
        private VideoFItemInAdapter mAdapter;
    }

    /*
    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoHolder(mFrom.inflate(R.layout.item_video_fragment_layout,parent,false),mContext);
    }

    @Override
    public void onBindViewHolder(VideoHolder holder, int position) {
        VideoHomeData.VideoBean videoBean = mDatas.get(position);
        holder.bindView(videoBean);
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }*/

}
