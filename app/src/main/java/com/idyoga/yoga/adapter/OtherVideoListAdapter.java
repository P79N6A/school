package com.idyoga.yoga.adapter;

import android.content.Context;
import android.os.Bundle;
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
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.model.VideoDetailBean;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * @author weilgu
 * @time 2019/4/2 16:28
 * @des
 */

public class OtherVideoListAdapter extends RecyclerView.Adapter<OtherVideoListAdapter.ViewHolder> {

    private Context mContext;
    private List<VideoDetailBean.RecommendVideoBean> mDatas;
    private final LayoutInflater mFrom;

    public OtherVideoListAdapter(Context context) {
        mContext = context;
        mFrom = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mFrom.inflate(R.layout.item_other_video_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (position == 0){
            holder.mLlTitle.setVisibility(View.VISIBLE);
        }else{
            holder.mLlTitle.setVisibility(View.GONE);
        }
        VideoDetailBean.RecommendVideoBean videoBean = mDatas.get(position);
        Glide.with(mContext)
                .load(videoBean.getImage_url())
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into(holder.mIvVideoImage);
        holder.mTvName.setText(videoBean.getTitle());
        String price = videoBean.getPrice();
        if (price != null && price.equals("0.00")){
            holder.mTvFree.setText("免费");
        }else if (price != null){
            holder.mTvFree.setText(price + "元");
        }
        holder.mTvVideoOther.setText(videoBean.getTutor_name());
        holder.mTvVideoNum.setText("共" + videoBean.getNumber() + "节 | " + videoBean.getStudy_number() + "人学习");
        holder.mRlotherVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                VideoDetailBean.RecommendVideoBean bean = mDatas.get(adapterPosition);
                int id = bean.getId();
                Bundle bundle = new Bundle();
                bundle.putString("videoId",String.valueOf(id));
                EventBus.getDefault().post(new PostResult("toOtherVideoDetail",bundle));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public void setData(List<VideoDetailBean.RecommendVideoBean> recommendVideo) {
        this.mDatas = recommendVideo;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout mLlTitle;
        private RelativeLayout mRlotherVideo;
        private ImageView mIvVideoImage;
        private TextView mTvName;
        private TextView mTvVideoOther;
        private TextView mTvVideoNum;
        private TextView mTvFree;

        public ViewHolder(View itemView) {
            super(itemView);
            mLlTitle = itemView.findViewById(R.id.ll_title);
            mRlotherVideo  = itemView.findViewById(R.id.rl_other_video);
            mIvVideoImage = itemView.findViewById(R.id.iv_video_image);
            mTvName = itemView.findViewById(R.id.tv_name);
            mTvVideoOther = itemView.findViewById(R.id.tv_video_other);
            mTvVideoNum = itemView.findViewById(R.id.tv_video_num);
            mTvFree = itemView.findViewById(R.id.tv_free);
        }
    }
}
