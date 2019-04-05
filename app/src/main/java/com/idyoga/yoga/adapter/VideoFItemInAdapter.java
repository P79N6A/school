package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.model.VideoHomeData;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/12 16:23
 * @des ${TODO}
 */

public class VideoFItemInAdapter extends RecyclerView.Adapter<VideoFItemInAdapter.ViewHolder> {
    private Context mContext;
    private final LayoutInflater mFrom;
    private List<VideoHomeData.VideoList> mDatas;

    public VideoFItemInAdapter(Context context) {
        mContext = context;
        mFrom = LayoutInflater.from(mContext);
    }

    @Override
    public VideoFItemInAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mFrom.inflate(R.layout.item_in_video_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(VideoFItemInAdapter.ViewHolder holder, int position) {
        VideoHomeData.VideoList videoList = mDatas.get(position);
        String title = videoList.getTitle();
        String image_url = videoList.getImage_url();
        int videoId = videoList.getVideoId();
        holder.mVideoDescribe.setText(title);
        Glide.with(mContext)
                .load(image_url)
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into(holder.mIvVideo);
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public void setDatas(List<VideoHomeData.VideoList> videoList) {
        mDatas = videoList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIvVideo;
        private ImageView mPlayvideo;
        private TextView mVideoDescribe;

        public ViewHolder(View itemView) {
            super(itemView);
            mIvVideo = itemView.findViewById(R.id.iv_video);
            mPlayvideo = itemView.findViewById(R.id.iv_play_video);
            mVideoDescribe = itemView.findViewById(R.id.tv_video_describe);
        }
    }
}
