package com.idyoga.yoga.holder;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.ItemVideoHomeList;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.model.VideoHomeData;
import com.idyoga.yoga.utils.LoginUtil;
import com.idyoga.yoga.view.ItemListView;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * @author weilgu
 * @time 2019/3/21 10:59
 * @des ${TODO}
 */

public class VideoListHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private RecyclerView rlv_video;
    private final VideoImageAdapter mImageAdapter;
    private RelativeLayout rl_root;
    private TextView tv_head_name;
    //    private ItemListView rlv_video;
//    private final ItemVideoHomeList mHomeList;

    public VideoListHolder(View itemView, Context context) {
        super(itemView);
        this.mContext = context;
        rlv_video = itemView.findViewById(R.id.rlv_video);
        rl_root = itemView.findViewById(R.id.rl_root);
        tv_head_name = itemView.findViewById(R.id.tv_head_name);

        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rlv_video.setLayoutManager(manager);
        mImageAdapter = new VideoImageAdapter(mContext);
        rlv_video.setAdapter(mImageAdapter);
       /* mHomeList = new ItemVideoHomeList(mContext);
        rlv_video.setAdapter(mHomeList);*/
        //mHomeList = new ItemVideoHomeList(mContext);
        //rlv_video.setAdapter(mHomeList);
    }

//    public void bindView(ArrayList<VideoHomeData.VideoBean> beans) {
    public void bindView(List<VideoHomeData.VideoList> beans,VideoHomeData.VideoBean bean) {
//        mHomeList.setDatas(beans);
        tv_head_name.setText(bean.getName());
        mImageAdapter.setDatas(beans);
    }

    class VideoImageAdapter extends RecyclerView.Adapter<VideoImageAdapter.ViewHolder>{

        private List<VideoHomeData.VideoList> mBeans;
        private Context mContext ;
        private final LayoutInflater mFrom;

        public VideoImageAdapter(Context context){
            this.mContext = context;
            mFrom = LayoutInflater.from(mContext);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mFrom.inflate(R.layout.item_in_video_layout,parent,false));
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            VideoHomeData.VideoList videoList = mBeans.get(position);
            String title = videoList.getTitle();
            String image_url = videoList.getImage_url();
            holder.mVideoDescribe.setText(title);
            Glide.with(mContext)
                    .load(image_url)
                    .placeholder(R.drawable.img_course)
                    .error(R.drawable.img_course)
                    .into(holder.mIvVideo);
            holder.mLlVideoRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!LoginUtil.checkLogin(mContext)) return;
                    VideoHomeData.VideoList video = mBeans.get(holder.getAdapterPosition());
                    int videoId = video.getVideoId();
                    Bundle bundle = new Bundle();
                    bundle.putString("videoId",String.valueOf(videoId));
                    EventBus.getDefault().post(new PostResult("toVideoDetail",bundle));
                }
            });

        }

        @Override
        public int getItemCount() {
            return mBeans != null ? mBeans.size() : 0;
        }

        public void setDatas(List<VideoHomeData.VideoList> beans) {
            this.mBeans = beans;
            notifyDataSetChanged();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private ImageView mIvVideo;
            private ImageView mPlayvideo;
            private TextView mVideoDescribe;
            private LinearLayout mLlVideoRoot;

            public ViewHolder(View itemView) {
                super(itemView);
                mIvVideo = itemView.findViewById(R.id.iv_video);
                mPlayvideo = itemView.findViewById(R.id.iv_play_video);
                mVideoDescribe = itemView.findViewById(R.id.tv_video_describe);
                mLlVideoRoot = itemView.findViewById(R.id.ll_video_root);
            }
        }
    }
}
