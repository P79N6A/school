package com.idyoga.yoga.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.MainActivity;
import com.idyoga.yoga.activity.SpecialDetailActivity;
import com.idyoga.yoga.model.HomePageData;
import com.idyoga.yoga.model.HomeRecommendBean;
import com.idyoga.yoga.utils.ViewUtil;
import com.idyoga.yoga.view.RoundDrawable;
import com.idyoga.yoga.view.ShadowLayout;

import java.util.List;

import vip.devkit.library.Logcat;

/**
 * @author weilgu
 * @time 2019/3/11 14:33
 * @des 首页 专题 adapter
 */

public class SpecialVideoAdapter extends RecyclerView.Adapter<SpecialVideoAdapter.ViewHolder> {

    private Context mContext;
    private final LayoutInflater mFrom;
    private List<HomePageData.SubjectListBean> mDatas;

    public SpecialVideoAdapter(Context context) {
        mContext = context;
        mFrom = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mFrom.inflate(R.layout.item_special_video,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        HomePageData.SubjectListBean subjectListBean = mDatas.get(position);
        String image_url = subjectListBean.getImage_url();
        holder.mCardView.setTag(position);
        Glide.with(mContext)
                .load(image_url)
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into(holder.mIvVideo);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (int) holder.mCardView.getTag();
                HomePageData.SubjectListBean listBean = mDatas.get(tag);
                int subjectId = listBean.getSubjectId();
                Bundle bundle = new Bundle();
                bundle.putInt("subjectId",subjectId);
                MainActivity ac = (MainActivity) mContext;
                Intent intent = new Intent(ac, SpecialDetailActivity.class);
                intent.putExtras(bundle);
                ac.startActivity(intent);
            }
        });
        holder.mCardView.setBackground(new RoundDrawable(ViewUtil.dp2px(mContext,5),0.35f));
    }

    public void setDatas(List<HomePageData.SubjectListBean> datas){
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIvVideo;
        private ShadowLayout mCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            mIvVideo = itemView.findViewById(R.id.iv_video);
            mCardView = itemView.findViewById(R.id.cv_root);
        }
    }
}
