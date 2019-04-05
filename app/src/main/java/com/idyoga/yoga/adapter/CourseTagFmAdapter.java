package com.idyoga.yoga.adapter;

import android.content.Context;
import android.content.IntentFilter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.model.CoureseDetailInfo;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/22 15:03
 * @des ${TODO}
 */

public class CourseTagFmAdapter extends RecyclerView.Adapter {

    private int mOrientation = 0;
    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;
    private Context mContext;
    private final LayoutInflater mFrom;
    private List<CoureseDetailInfo.Label> mLabels;

    public CourseTagFmAdapter(Context context){
        this.mContext = context;
        mFrom = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HORIZONTAL){
            return new HorViewHolder(mFrom.inflate(R.layout.item_course_hor_layout,parent,false));
        }else{
            return new VerViewHolder(mFrom.inflate(R.layout.item_course_ver_layout,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CoureseDetailInfo.Label label = mLabels.get(position);
        int itemViewType = getItemViewType(position);
        if (itemViewType == HORIZONTAL){
            ((HorViewHolder)holder).bindView(label);
        }else{
            ((VerViewHolder)holder).bindView(label);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mOrientation == 0){
            return HORIZONTAL ;
        }else{
            return VERTICAL;
        }
    }

    @Override
    public int getItemCount() {
        return mLabels != null ? mLabels.size() : 0;
    }

    public void setOrientation(boolean isHorizontal){
        if (isHorizontal){
            mOrientation = 0;
        }else{
            mOrientation = 1;
        }
        notifyDataSetChanged();
    }

    public void setDatas(List<CoureseDetailInfo.Label> labelList){
        this.mLabels = labelList;
        notifyDataSetChanged();
    }

    class HorViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_ima;
        private TextView tv_tag_name;

        public HorViewHolder(View itemView) {
            super(itemView);
            iv_ima = itemView.findViewById(R.id.iv_ima);
            tv_tag_name = itemView.findViewById(R.id.tv_tag_name);
        }

        public void bindView(CoureseDetailInfo.Label label){
            String image_url = label.getImage_url();
            Glide.with(mContext)
                    .load(image_url)
                    .placeholder(R.drawable.img_course)
                    .error(R.drawable.img_course)
                    .into(iv_ima);
            tv_tag_name.setText(label.getName() + "");
        }
    }

    class VerViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_ima;
        private TextView tv_tag_name;
        private TextView tv_tag_detail;

        public VerViewHolder(View itemView) {
            super(itemView);
            iv_ima = itemView.findViewById(R.id.iv_tag_image);
            tv_tag_name = itemView.findViewById(R.id.tv_tag_name);
            tv_tag_detail = itemView.findViewById(R.id.tv_tag_detail);
        }

        public void bindView(CoureseDetailInfo.Label label) {
            String image_url = label.getImage_url();
            Glide.with(mContext)
                    .load(image_url)
                    .placeholder(R.drawable.img_course)
                    .error(R.drawable.img_course)
                    .into(iv_ima);
            tv_tag_name.setText(label.getName() + "");
            tv_tag_detail.setText(label.getDescription() + "");
        }
    }
}
