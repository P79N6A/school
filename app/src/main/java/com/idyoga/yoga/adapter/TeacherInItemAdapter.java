package com.idyoga.yoga.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.model.TutorListBeans;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * @author weilgu
 * @time 2019/3/13 11:14
 * @des ${TODO}
 */

public class TeacherInItemAdapter extends RecyclerView.Adapter<TeacherInItemAdapter.ViewHolder> {

    private Context mContext;
    private final LayoutInflater mFrom;
    private List<TutorListBeans> mBeans;

    public TeacherInItemAdapter(Context context) {
        mContext = context;
        mFrom = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mFrom.inflate(R.layout.item_teacher_in_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TutorListBeans listBeans = mBeans.get(position);
        String image = listBeans.getImage();
        String name = listBeans.getName();
        Glide.with(mContext)
                .load(image)
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into(holder.mTeaImage);
        holder.mTeaName.setText(name);
        holder.mLlTeach.setTag(position);
        holder.mLlTeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("tutorId",String.valueOf(listBeans.getTutorId()));
                EventBus.getDefault().post(new PostResult("toTeachInfoDetail",bundle));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeans != null ? mBeans.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mTeaImage;
        private TextView mTeaName;
        private LinearLayout mLlTeach;

        public ViewHolder(View itemView) {
            super(itemView);
            mTeaImage = itemView.findViewById(R.id.iv_teacher_iamge);
            mTeaName = itemView.findViewById(R.id.tv_teacher_name);
            mLlTeach = itemView.findViewById(R.id.ll_teach);
        }
    }

    public void setData(List<TutorListBeans> beans){
        this.mBeans = beans;
        notifyDataSetChanged();
    }
}
